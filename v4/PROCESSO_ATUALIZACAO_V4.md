# Processo de Atualização — Exemplos Demoiselle v3 → v4

## 1. Contexto

O Demoiselle 4 (`4.0.0-SNAPSHOT`) migra de Java EE 7 para Jakarta EE 10, com Java 17 como versão mínima. Este documento descreve o processo para recriar os 5 apps de exemplo da v3 na v4.

### Apps a migrar

| App | Descrição | Backend | Frontend v3 |
|---|---|---|---|
| todoList | CRUD de tarefas com autenticação | REST + JPA + JWT | Angular 4 + Webpack |
| cep | Consulta de CEP brasileiro | REST + JPA (read-only) | Angular 5 + CLI |
| livraria | Multitenancy com livros | REST + JPA + JWT | Angular 4 + Webpack |
| forum | Fórum com categorias/tópicos/mensagens | REST + JPA + JWT + WebSocket + Email | Angular 5 + CLI |
| push | Servidor de push via WebSocket | REST + WebSocket + Scheduler | Sem frontend Angular |

### Stack v3 → v4

| Componente | v3 | v4 |
|---|---|---|
| Java | 8 | 17+ |
| Spec | Java EE 7 | Jakarta EE 10 |
| Namespace | `javax.*` | `jakarta.*` |
| Demoiselle Parent | `demoiselle-parent-rest:3.0.x` | `demoiselle-parent-rest:4.0.0-SNAPSHOT` |
| CDI | 1.2 | 4.0 |
| JAX-RS | 2.0 | 3.1 |
| JPA | 2.1 | 3.1 |
| Bean Validation | 1.1 | 3.0 |
| Servlet | 3.1 | 6.0 |
| EJB | 3.2 | 4.0 |
| Swagger | 1.5.x | MicroProfile OpenAPI 3.1 |
| JUnit | 4 | 5 (Jupiter) |
| WildFly Swarm | 2017.6.1 | Removido (usar WildFly 27+) |
| DeltaSpike | Presente | Removido |
| Hibernate Validator | 5.x | 8.0.x |
| jose4j | 0.5.x | 0.9.6 |
| Groovy | 2.4.x | Apache Groovy 4.0.x |

---

## 2. Estrutura da v4

```
v4/
├── todoList/
│   └── backend/
├── cep/
│   └── backend/
├── livraria/
│   └── backend/
├── forum/
│   └── backend/
└── push/
    └── backend/
```

> Nota: Os frontends da v3 usam Angular 4/5 com pacotes `@demoiselle/http` e `@demoiselle/security`. A decisão sobre o frontend da v4 (Angular moderno, React, etc.) é separada deste processo. Este documento foca na migração dos backends Java.

---

## 3. Processo de Migração por App

### Passo 1 — POM (para cada app)

1. Alterar o parent:
```xml
<parent>
    <groupId>org.demoiselle.jee</groupId>
    <artifactId>demoiselle-parent-rest</artifactId>
    <version>4.0.0-SNAPSHOT</version>
</parent>
```

2. Configurar Java 17:
```xml
<plugin>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.13.0</version>
    <configuration>
        <release>17</release>
    </configuration>
</plugin>
```

3. Remover dependência `javax:javaee-endorsed-api:7.0` (não existe mais).

4. Remover profile `wildfly-swarm` inteiro.

5. Atualizar Hibernate para versões Jakarta EE 10 compatíveis:
   - `hibernate-entitymanager` → não necessário (usar `hibernate-core` 6.x se precisar, ou deixar o servidor prover)
   - `hibernate-infinispan` → avaliar necessidade
   - `hibernate-validator` → gerenciado pelo BOM (8.0.1.Final)

6. Substituir Swagger por MicroProfile OpenAPI (se usado):
```xml
<!-- Remover -->
<dependency>
    <groupId>io.swagger</groupId>
    <artifactId>swagger-jaxrs</artifactId>
</dependency>

<!-- Adicionar (se necessário) -->
<dependency>
    <groupId>org.eclipse.microprofile.openapi</groupId>
    <artifactId>microprofile-openapi-api</artifactId>
</dependency>
```

7. Atualizar `maven-war-plugin` para 3.4+.

8. Remover `maven-dependency-plugin` que copiava `javaee-endorsed-api`.


### Passo 2 — Imports javax → jakarta (para cada app)

Substituir todos os imports Java EE:

| Antigo | Novo |
|---|---|
| `javax.enterprise.*` | `jakarta.enterprise.*` |
| `javax.inject.*` | `jakarta.inject.*` |
| `javax.ws.rs.*` | `jakarta.ws.rs.*` |
| `javax.persistence.*` | `jakarta.persistence.*` |
| `javax.validation.*` | `jakarta.validation.*` |
| `javax.ejb.*` | `jakarta.ejb.*` |
| `javax.servlet.*` | `jakarta.servlet.*` |
| `javax.annotation.*` | `jakarta.annotation.*` |
| `javax.json.*` | `jakarta.json.*` |
| `javax.websocket.*` | `jakarta.websocket.*` |
| `javax.mail.*` | `jakarta.mail.*` |

**Atenção**: `javax.script.*` (API do JDK) NÃO deve ser alterado.

Comando para identificar imports a migrar:
```bash
grep -rn "import javax\." --include="*.java" src/
```

### Passo 3 — beans.xml

Atualizar todos os `beans.xml` (em `META-INF/` e `WEB-INF/`):

```xml
<!-- ANTES -->
<beans xmlns="http://xmlns.jcp.org/xml/ns/javaee"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
       http://xmlns.jcp.org/xml/ns/javaee/beans_1_1.xsd"
       bean-discovery-mode="all">
</beans>

<!-- DEPOIS -->
<beans xmlns="https://jakarta.ee/xml/ns/jakartaee"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee
       https://jakarta.ee/xml/ns/jakartaee/beans_4_0.xsd"
       version="4.0" bean-discovery-mode="all">
</beans>
```

### Passo 4 — persistence.xml

Atualizar namespace e versão:

```xml
<!-- ANTES -->
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
             http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd"
             version="2.1">

<!-- DEPOIS -->
<persistence xmlns="https://jakarta.ee/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="https://jakarta.ee/xml/ns/persistence
             https://jakarta.ee/xml/ns/persistence/persistence_3_0.xsd"
             version="3.0">
```

Propriedades Hibernate que mudam:
- `hibernate.transaction.jta.platform` → verificar compatibilidade com WildFly 27+

### Passo 5 — web.xml

Atualizar namespace:

```xml
<!-- ANTES -->
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
         http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">

<!-- DEPOIS -->
<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee
         https://jakarta.ee/xml/ns/jakartaee/web-app_6_0.xsd"
         version="6.0">
```

### Passo 6 — Swagger → OpenAPI (cep, forum)

Apps que usam anotações Swagger:

| Swagger 1.x | MicroProfile OpenAPI |
|---|---|
| `@Api` | `@Tag` |
| `@ApiOperation` | `@Operation` |
| `@ApiParam` | `@Parameter` |
| `@ApiResponse` | `@APIResponse` |
| `import io.swagger.annotations.*` | `import org.eclipse.microprofile.openapi.annotations.*` |

### Passo 7 — Remover WildFly Swarm

- Remover profile `wildfly-swarm` do POM
- Remover dependências `org.wildfly.swarm.*`
- Remover `docker-maven-plugin` do Spotify
- Atualizar README com instruções para WildFly 27+ ou outro servidor Jakarta EE 10

### Passo 8 — Remover jboss-web.xml (avaliar)

O `jboss-web.xml` pode ser mantido para deploy em WildFly 27+, mas o context-root pode ser configurado de outras formas.

### Passo 9 — Remover context.xml

O `META-INF/context.xml` era específico para Tomcat/WildFly Swarm. Avaliar se ainda é necessário.

---

## 4. Detalhes por App

### 4.1 todoList

**Arquivos Java a migrar:**

| Camada | Arquivos |
|---|---|
| Entity | `Todo.java`, `User.java` |
| DAO | `TodoDAO.java`, `UserDAO.java` |
| BC | `TodoBC.java`, `UserBC.java` |
| REST | `AuthREST.java`, `TodoREST.java`, `UserREST.java`, `ConstantsREST.java`, `ApplicationConfig.java` |
| Security | `Credentials.java` |
| Constants | `Perfil.java`, `Status.java` |
| Message | `TodoMessage.java` |

**Mudanças específicas:**
- Imports `javax.*` → `jakarta.*` em todos os arquivos
- `persistence.xml`: atualizar namespace
- `beans.xml`: atualizar namespace
- `web.xml`: atualizar namespace
- Remover profile `wildfly-swarm` do POM
- Remover `javaee-endorsed-api` do POM
- Remover `hibernate-entitymanager`, `hibernate-infinispan` (providos pelo servidor)
- Manter `demoiselle.properties` (compatível)
- Manter `TodoMessage.properties` (compatível)

### 4.2 cep

**Arquivos Java a migrar:**

| Camada | Arquivos |
|---|---|
| Entity | `Cep.java`, `Ip2locationDb11.java`, `Ip2locationDb11PK.java` |
| DAO | `CepDAO.java`, `Ip2LocationDb11DAO.java` |
| BC | `CepBC.java` |
| REST | `CepREST.java`, `CidadeREST.java`, `LogradouroREST.java`, `UfREST.java`, `Ip2LocationREST.java`, `ApplicationConfig.java` |

**Mudanças específicas:**
- Imports `javax.*` → `jakarta.*`
- Swagger → MicroProfile OpenAPI (se anotações Swagger presentes)
- Remover profile `wildfly-swarm`
- Remover `javaee-endorsed-api`
- Atualizar `jboss-web.xml` (usa filtering com `${app.context}`)
- Sem autenticação JWT neste app (mais simples)

### 4.3 livraria

**Arquivos Java a migrar:**

| Camada | Arquivos |
|---|---|
| Entity | `Livro.java`, `User.java` |
| DAO | `LivroDAO.java`, `UserDAO.java` |
| BC | `LivroBC.java`, `UserBC.java` |
| REST | `AuthREST.java`, `LivroREST.java`, `UserREST.java`, `ConstantsREST.java`, `ApplicationConfig.java` |
| Security | `Credentials.java`, `UserRegister.java` |
| Constants | `Perfil.java` |
| Message | `AppMessage.java` |

**Mudanças específicas:**
- Imports `javax.*` → `jakarta.*`
- Multitenancy: verificar compatibilidade com Demoiselle 4
- Hibernate Search 5.7 → avaliar migração para Hibernate Search 6.x (se usado)
- Remover `hibernate-entitymanager`, `hibernate-infinispan`
- Manter scripts SQL (`v3/livraria/sql/`)

### 4.4 forum

**Arquivos Java a migrar:**

| Camada | Arquivos |
|---|---|
| Entity | `Categoria.java`, `Topico.java`, `Mensagem.java`, `User.java`, `Fingerprint.java` |
| DAO | `CategoriaDAO.java`, `TopicoDAO.java`, `MensagemDAO.java`, `UserDAO.java`, `FingerprintDAO.java` |
| BC | `CategoriaBC.java`, `TopicoBC.java`, `MensagemBC.java`, `UserBC.java` |
| REST | `AuthREST.java`, `CategoriaREST.java`, `TopicoREST.java`, `MensagemREST.java`, `UserREST.java`, `ConstantsREST.java`, `ApplicationConfig.java` |
| Security | `Credentials.java`, `Social.java` |
| WebSocket | `PushEndpoint.java`, `PushMessage.java`, `PushMessageDecoder.java`, `PushMessageEncoder.java` |
| Cloud | `CloudMessage.java`, `CloudNotification.java`, `CloudSender.java` |
| Email | `Email.java` |
| Timer | `Timer.java` |
| Constants | `Perfil.java` |
| Message | `AppMessage.java` |

**Mudanças específicas:**
- App mais complexo — maior número de arquivos
- WebSocket: `javax.websocket.*` → `jakarta.websocket.*`
- Email: `javax.mail.*` → `jakarta.mail.*`
- EJB Timer: `javax.ejb.*` → `jakarta.ejb.*`
- Swagger → MicroProfile OpenAPI
- GSON: manter (não é Jakarta EE)
- DKIM: manter (não é Jakarta EE)
- JavaMail: atualizar para Jakarta Mail 2.1

### 4.5 push

**Arquivos Java a migrar:**

| Camada | Arquivos |
|---|---|
| Entity | `User.java` |
| DAO | `UserDAO.java` |
| REST | `AuthREST.java`, `PushREST.java`, `ApplicationConfig.java` |
| Security | `Credentials.java`, `RestMessage.java` |
| WebSocket | `PushConfigurator.java`, `PushEndpoint.java`, `PushMessage.java`, `PushMessageDecoder.java`, `PushMessageEncoder.java` |
| Scheduler | `TimerPush.java` |
| Constants | `Perfil.java` |

**Mudanças específicas:**
- WebSocket: `javax.websocket.*` → `jakarta.websocket.*`
- EJB Timer/Scheduler: `javax.ejb.*` → `jakarta.ejb.*`
- GSON: manter
- Remover profile `wildfly-swarm`

---

## 5. Ordem de Execução Recomendada

A migração deve seguir do app mais simples ao mais complexo:

1. **todoList** — CRUD básico, bom para validar o processo
2. **cep** — Somente leitura, sem autenticação, valida JPA puro
3. **livraria** — Adiciona multitenancy e autenticação
4. **push** — Adiciona WebSocket e Scheduler
5. **forum** — App completo com todas as features

### Para cada app:

```
1. Copiar backend da v3 para v4
2. Atualizar pom.xml (parent, plugins, dependências)
3. Substituir imports javax → jakarta (todos os .java)
4. Atualizar beans.xml, persistence.xml, web.xml
5. Remover/atualizar configs específicas (jboss-web.xml, context.xml)
6. Migrar Swagger → OpenAPI (se aplicável)
7. Compilar (mvn clean compile)
8. Corrigir erros de compilação
9. Testar (mvn clean package)
10. Validar deploy em WildFly 27+
```

---

## 6. Dependências Externas por App

| Dependência | Apps que usam | Ação na v4 |
|---|---|---|
| `hibernate-entitymanager` | todos | Remover (provido pelo servidor) |
| `hibernate-infinispan` | todos | Remover (provido pelo servidor) |
| `hibernate-core` | todos | Remover (provido pelo servidor) |
| `hibernate-validator` | push, cep | Gerenciado pelo BOM (8.0.1.Final) |
| `hibernate-search` | livraria | Migrar para 6.x se usado |
| `io.swagger:swagger-jaxrs` | cep, forum | Substituir por MicroProfile OpenAPI |
| `com.google.code.gson:gson` | push, forum | Manter (não é Jakarta EE) |
| `javax.mail:mail` | forum | Substituir por `jakarta.mail:jakarta.mail-api` |
| `net.markenwerk:utils-mail-dkim` | forum | Manter (verificar compatibilidade) |
| `infinispan-core` | livraria | Avaliar necessidade |

---

## 7. Checklist Geral

- [x] Criar estrutura `v4/` com subpastas por app
- [x] todoList: migrar backend (15 arquivos Java + 6 configs)
- [x] cep: migrar backend (12 arquivos Java + 6 configs)
- [x] livraria: migrar backend (14 arquivos Java + 6 configs)
- [x] push: migrar backend (12 arquivos Java + 6 configs)
- [x] forum: migrar backend (26 arquivos Java + 6 configs)
- [x] Verificar zero imports `javax.*` restantes
- [x] Validar compilação de todos os apps (`mvn clean compile`)
- [x] Validar empacotamento (`mvn clean package`)
- [ ] Testar deploy em WildFly 27+ (pular por enquanto)
- [x] Atualizar READMEs com instruções para v4
- [x] Decisão sobre frontends: Vue 3 + Vite + TypeScript
