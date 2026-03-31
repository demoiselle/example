# Roteiro de Migração — Demoiselle 2.5 → 3.0

## Visão Geral

O Demoiselle 3 é uma reescrita completa do framework. A v2.5 era baseada em JSF + CDI com camadas ManagedBean/BC/DAO, enquanto a v3 adota uma arquitetura REST + SPA com camadas REST/BC/DAO e frontend separado.

| Aspecto | Demoiselle 2.5 | Demoiselle 3.0 |
|---|---|---|
| GroupId Maven | `br.gov.frameworkdemoiselle` | `org.demoiselle.jee` |
| Parent POM | `demoiselle-jsf-parent` | `demoiselle-parent-rest` |
| Java | 6/7 | 8 |
| Spec | Java EE 6 | Java EE 7 |
| Apresentação | JSF 2.x + PrimeFaces + XHTML | REST (JAX-RS) + SPA (Angular) |
| Segurança | JAAS + `@RequiredRole`/`@RequiredPermission` | JWT + `@Authenticated`/`@RequiredRole` |
| Persistência | `JPACrud<E, ID>` | `AbstractDAO<E, ID>` |
| Negócio | `DelegateCrud<E, ID, DAO>` + `@BusinessController` | `AbstractBusiness<E, ID>` |
| Apresentação | `AbstractListPageBean` / `AbstractEditPageBean` | `AbstractREST<E, ID>` |
| Mensagens | `DefaultMessage` + enum | DeltaSpike `@MessageBundle` |
| Config | `@Configuration` (próprio) | `demoiselle.properties` |
| Transação | `@Transactional` (próprio) | `@Transactional` (JTA) |
| Servidor | JBoss AS 7 / WildFly 8 | WildFly 10+ / WildFly Swarm |

## Passo 1 — Reestruturar o Projeto

A v3 separa backend e frontend em projetos distintos:

```
# v2.5 (monolítico)
app/
├── src/main/java/       # BC, DAO, Entity, ManagedBean
├── src/main/webapp/     # XHTML, CSS, JS
└── pom.xml

# v3 (separado)
app/
├── backend/
│   ├── src/main/java/   # REST, BC, DAO, Entity
│   └── pom.xml
└── frontend/            # Angular (separado)
```

## Passo 2 — Atualizar o POM

```xml
<!-- ANTES (v2.5) -->
<parent>
    <groupId>br.gov.frameworkdemoiselle</groupId>
    <artifactId>demoiselle-jsf-parent</artifactId>
    <version>2.5.0</version>
</parent>

<!-- DEPOIS (v3) -->
<parent>
    <groupId>org.demoiselle.jee</groupId>
    <artifactId>demoiselle-parent-rest</artifactId>
    <version>3.0.0</version>
</parent>
```

Remover dependências v2.5:
- `demoiselle-jpa` → incluído no parent-rest
- `demoiselle-report` → não existe na v3
- `demoiselle-validation` → usar Bean Validation padrão
- `demoiselle-junit` → usar JUnit 4 padrão
- PrimeFaces → não necessário (frontend SPA)
- `commons-fileupload` → não necessário

## Passo 3 — Migrar Entidades (Domain → Entity)

As entidades JPA mudam pouco. Manter `javax.persistence.*`.

```java
// v2.5 — package domain
package org.demoiselle.app.domain;

// v3 — package entity
package app.entity;
```

Mudanças:
- Renomear pacote `domain` → `entity`
- Adicionar `@XmlRootElement` (para serialização JAX-RS)
- UUID como ID é comum na v3 (em vez de Long com `@GeneratedValue(SEQUENCE)`)

## Passo 4 — Migrar DAO (Persistence → DAO)

```java
// v2.5
import br.gov.frameworkdemoiselle.template.JPACrud;

public class AutomovelDAO extends JPACrud<Automovel, Long> {
    private static final long serialVersionUID = 1L;
}

// v3
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.demoiselle.jee.crud.AbstractDAO;

public class AutomovelDAO extends AbstractDAO<Automovel, Long> {
    @PersistenceContext
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() { return em; }
}
```

Diferenças:
- `JPACrud` → `AbstractDAO`
- Precisa declarar e expor o `EntityManager` explicitamente
- Não é mais `Serializable`

## Passo 5 — Migrar BC (Business → BC)

```java
// v2.5
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@BusinessController
public class AutomovelBC extends DelegateCrud<Automovel, Long, AutomovelDAO> {
    @Override
    @Transactional
    public Automovel insert(Automovel entity) { ... }
}

// v3
import org.demoiselle.jee.crud.AbstractBusiness;

public class AutomovelBC extends AbstractBusiness<Automovel, Long> {
    // Métodos CRUD herdados automaticamente
}
```

Diferenças:
- `@BusinessController` → não necessário (CDI bean padrão)
- `DelegateCrud<E, ID, DAO>` → `AbstractBusiness<E, ID>` (DAO injetado automaticamente)
- `@Transactional` do Demoiselle → `@Transactional` do JTA (`javax.transaction`)
- `insert()`/`update()`/`delete()` → `persist()`/`mergeFull()`/`remove()`

## Passo 6 — Migrar Apresentação (ManagedBean → REST)

Esta é a maior mudança. As telas JSF/XHTML são substituídas por endpoints REST.

```java
// v2.5 — ManagedBean + XHTML
@ViewController
public class AutomovelListMB extends AbstractListPageBean<Automovel, Long> {
    @Inject private AutomovelBC bc;
    @Override
    protected List<Automovel> handleResultList() { return bc.findAll(); }
}

// v3 — REST endpoint
@Api("Automovel")
@Path("v1/automovel")
public class AutomovelREST extends AbstractREST<Automovel, Long> {
    // CRUD exposto automaticamente via herança
}
```

Diferenças:
- `@ViewController` → `@Path("...")`
- `AbstractListPageBean`/`AbstractEditPageBean` → `AbstractREST<E, ID>`
- XHTML templates → eliminados (frontend SPA separado)
- Navegação JSF → rotas do frontend (Angular/Vue)

## Passo 7 — Migrar Segurança

```java
// v2.5
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.security.RequiredPermission;
import br.gov.frameworkdemoiselle.security.Authenticator;

// v3
import org.demoiselle.jee.security.annotation.Authenticated;
import org.demoiselle.jee.security.annotation.RequiredRole;
// JWT via demoiselle.properties
```

Diferenças:
- JAAS/Form-based → JWT (token no header Authorization)
- `Authenticator` customizado → `UserDAO.login()` retorna `Token`
- `@RequiredPermission` → usar `@RequiredRole` ou lógica no BC
- Configuração em `demoiselle.properties` (chaves RSA, issuer, audience)

## Passo 8 — Migrar Mensagens

```java
// v2.5
import br.gov.frameworkdemoiselle.message.DefaultMessage;
final Message MSG = new DefaultMessage("{chave}");
messageContext.add(MSG.getText());

// v3 (DeltaSpike)
import org.apache.deltaspike.core.api.message.MessageBundle;
import org.apache.deltaspike.core.api.message.MessageTemplate;

@MessageBundle
public interface AppMessage {
    @MessageTemplate("{chave}")
    String chave();
}
```

## Passo 9 — Migrar Configuração

```java
// v2.5
import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.configuration.Configuration;

@Configuration(resource = "estacionamento")
public class EstacionamentoConfig {
    @Name("app.titulo")
    private String titulo;
}

// v3
// Usar demoiselle.properties diretamente
// Ou @Inject @ConfigurationValue("chave")
```

## Passo 10 — Criar Frontend SPA

Na v2.5 o frontend era JSF/XHTML integrado ao backend. Na v3, é um projeto separado (Angular, Vue, etc.) que consome a API REST.

Mapear cada tela XHTML para um componente do frontend:
- `automovel_list.xhtml` → componente de listagem com chamada GET à API
- `automovel_edit.xhtml` → formulário com chamadas POST/PUT à API
- `login.xhtml` → tela de login com POST para `/api/v1/auth`

## Passo 11 — Migrar persistence.xml

```xml
<!-- v2.5 -->
<persistence version="2.0" xmlns="http://java.sun.com/xml/ns/persistence">
    <persistence-unit name="pu" transaction-type="JTA">
        <provider>org.hibernate.ejb.HibernatePersistence</provider>
        ...
    </persistence-unit>
</persistence>

<!-- v3 -->
<persistence version="2.1" xmlns="http://xmlns.jcp.org/xml/ns/persistence">
    <persistence-unit name="AppPU" transaction-type="JTA">
        <provider>org.hibernate.ejb.HibernatePersistence</provider>
        ...
    </persistence-unit>
</persistence>
```

## Passo 12 — Migrar beans.xml

```xml
<!-- v2.5 — interceptors explícitos -->
<beans>
    <interceptors>
        <class>br.gov.frameworkdemoiselle.transaction.TransactionalInterceptor</class>
        <class>br.gov.frameworkdemoiselle.security.RequiredPermissionInterceptor</class>
        <class>br.gov.frameworkdemoiselle.security.RequiredRoleInterceptor</class>
        <class>br.gov.frameworkdemoiselle.exception.ExceptionHandlerInterceptor</class>
    </interceptors>
</beans>

<!-- v3 — vazio (interceptors registrados automaticamente) -->
<beans xmlns="http://java.sun.com/xml/ns/javaee">
</beans>
```

## Checklist

- [ ] Reestruturar projeto (backend/frontend separados)
- [ ] Atualizar POM (parent, groupId, dependências)
- [ ] Migrar entidades (domain → entity)
- [ ] Migrar DAOs (JPACrud → AbstractDAO)
- [ ] Migrar BCs (DelegateCrud → AbstractBusiness)
- [ ] Migrar apresentação (ManagedBean/XHTML → REST endpoints)
- [ ] Migrar segurança (JAAS → JWT)
- [ ] Migrar mensagens (DefaultMessage → DeltaSpike)
- [ ] Migrar configuração
- [ ] Criar frontend SPA
- [ ] Atualizar persistence.xml e beans.xml
- [ ] Testar compilação e deploy
