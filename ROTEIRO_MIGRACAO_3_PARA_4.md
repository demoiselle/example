# Roteiro de Migração — Demoiselle 3.0 → 4.0

## Visão Geral

O Demoiselle 4 mantém a mesma arquitetura REST + SPA da v3, mas migra de Java EE 7 para Jakarta EE 10. A estrutura de código (REST/BC/DAO/Entity) permanece a mesma — a migração é principalmente de namespaces e dependências.

| Aspecto | Demoiselle 3.0 | Demoiselle 4.0 |
|---|---|---|
| Java | 8 | 17+ |
| Spec | Java EE 7 (`javax.*`) | Jakarta EE 10 (`jakarta.*`) |
| CDI | 1.2 | 4.0 |
| JAX-RS | 2.0 | 3.1 |
| JPA | 2.1 | 3.1 |
| Bean Validation | 1.1 | 3.0 |
| Servlet | 3.1 | 6.0 |
| EJB | 3.2 | 4.0 |
| WebSocket | 1.0 | 2.1 |
| Servidor | WildFly 10+ / WildFly Swarm | WildFly 27+ |
| Swagger | 1.5.x | Removido (MicroProfile OpenAPI) |
| DeltaSpike | Presente | Removido |
| JUnit | 4 | 5 (Jupiter) |
| Hibernate | 5.x | 6.x (provido pelo servidor) |
| jose4j | 0.5.x | 0.9.6 |

## Passo 1 — Atualizar o POM

```xml
<!-- ANTES -->
<parent>
    <groupId>org.demoiselle.jee</groupId>
    <artifactId>demoiselle-parent-rest</artifactId>
    <version>3.0.0</version>
</parent>

<!-- DEPOIS -->
<parent>
    <groupId>org.demoiselle.jee</groupId>
    <artifactId>demoiselle-parent-rest</artifactId>
    <version>4.0.0-SNAPSHOT</version>
</parent>
```

Configurar Java 17:
```xml
<plugin>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.13.0</version>
    <configuration>
        <release>17</release>
    </configuration>
</plugin>
```

Remover:
- `javax:javaee-endorsed-api:7.0` e o plugin `maven-dependency-plugin` que o copiava
- Profile `wildfly-swarm` inteiro
- Dependências `org.wildfly.swarm.*`
- `io.swagger:swagger-jaxrs`
- `org.hibernate:hibernate-entitymanager` / `hibernate-infinispan` / `hibernate-core` (providos pelo servidor)

Adicionar (scope `provided`):
```xml
<dependency>
    <groupId>jakarta.platform</groupId>
    <artifactId>jakarta.jakartaee-api</artifactId>
    <version>10.0.0</version>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>org.hibernate.orm</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>6.4.4.Final</version>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-annotations</artifactId>
    <version>2.17.0</version>
    <scope>provided</scope>
</dependency>
```

## Passo 2 — Migrar Imports javax → jakarta

Substituir em todos os arquivos `.java`:

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
| `javax.transaction.*` | `jakarta.transaction.*` |

**NÃO migrar**: `javax.script.*`, `javax.crypto.*`, `javax.net.*` (APIs do JDK).

Comando para encontrar imports a migrar:
```bash
grep -rn "import javax\." --include="*.java" src/
```

## Passo 3 — Remover Swagger

```java
// REMOVER
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.jaxrs.config.BeanConfig;

@Api("v1/Todo")  // REMOVER esta anotação
```

O `ApplicationConfig` fica limpo:
```java
@ApplicationPath("api")
public class ApplicationConfig extends Application { }
```

## Passo 4 — Remover DeltaSpike

```java
// ANTES (v3)
import org.apache.deltaspike.core.api.message.MessageBundle;
import org.apache.deltaspike.core.api.message.MessageTemplate;

@MessageBundle
public interface AppMessage {
    @MessageTemplate("{onlyOwner}")
    String onlyOwner();
}

// DEPOIS (v4) — interface simples com default methods
public interface AppMessage {
    default String onlyOwner() {
        return "Você só pode alterar seu próprio dado";
    }
}
```

## Passo 5 — Migrar @Email

```java
// ANTES (v3) — Hibernate Validator
import org.hibernate.validator.constraints.Email;

// DEPOIS (v4) — Jakarta Validation
import jakarta.validation.constraints.Email;
```

## Passo 6 — Remover @XmlRootElement

```java
// REMOVER (não necessário com Jackson)
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement  // REMOVER
```

## Passo 7 — Converter Async para Sync

```java
// ANTES (v3)
@GET
@Asynchronous
public void find(@Suspended final AsyncResponse asyncResponse) {
    asyncResponse.resume(doFind());
}

// DEPOIS (v4) — retorno direto
@GET
public Result find() {
    return bc.find();
}
```

## Passo 8 — Atualizar XMLs

### beans.xml
```xml
<!-- DEPOIS -->
<beans xmlns="https://jakarta.ee/xml/ns/jakartaee"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee
       https://jakarta.ee/xml/ns/jakartaee/beans_4_0.xsd"
       version="4.0" bean-discovery-mode="all">
</beans>
```

### persistence.xml
```xml
<persistence xmlns="https://jakarta.ee/xml/ns/persistence"
             version="3.0">
```

### web.xml
```xml
<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
         version="6.0">
```

## Passo 9 — Atualizar demoiselle.properties

O arquivo `demoiselle.properties` é compatível — não precisa de alterações.

## Passo 10 — Compilar e Testar

```bash
mvn clean compile
mvn clean package -DskipTests
# Deploy no WildFly 27+
```

## Mudanças de Comportamento

- `SecurityContextImpl`: `hasPermission()`, `hasRole()`, `isLoggedIn()` retornam `false` em vez de `NullPointerException` quando não autenticado
- `TokenManagerImpl`: `removeUser()` limpa o token em vez de lançar `UnsupportedOperationException`
- `AbstractDAO`: mensagens de erro internacionalizáveis via message bundles
- `CrudFilter`: projeção de campos suporta profundidade arbitrária

## Checklist

- [ ] Atualizar parent POM para 4.0.0-SNAPSHOT
- [ ] Configurar Java 17
- [ ] Adicionar `jakarta.jakartaee-api`, `hibernate-core`, `jackson-annotations` (provided)
- [ ] Remover dependências obsoletas (Swagger, Hibernate, javaee-endorsed-api, WildFly Swarm)
- [ ] Substituir imports `javax.*` → `jakarta.*`
- [ ] Remover anotações Swagger (@Api, @ApiOperation)
- [ ] Remover DeltaSpike (substituir por interfaces com default methods)
- [ ] Migrar `@Email` de Hibernate Validator para Jakarta Validation
- [ ] Remover `@XmlRootElement`
- [ ] Converter padrão async para sync
- [ ] Atualizar beans.xml, persistence.xml, web.xml
- [ ] Compilar e testar
