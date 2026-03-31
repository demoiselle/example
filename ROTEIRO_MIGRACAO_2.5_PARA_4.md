# Roteiro de Migração — Demoiselle 2.5 → 4.0 (Direto)

## Visão Geral

Este roteiro cobre a migração direta do Demoiselle 2.5 para o 4.0, pulando a v3. É a migração mais abrangente: muda a arquitetura (JSF monolítico → REST + SPA), o namespace (Java EE 6 `javax.*` → Jakarta EE 10 `jakarta.*`), a versão do Java (6/7 → 17+) e o modelo de segurança (JAAS → JWT).

| Aspecto | Demoiselle 2.5 | Demoiselle 4.0 |
|---|---|---|
| GroupId Maven | `br.gov.frameworkdemoiselle` | `org.demoiselle.jee` |
| Parent POM | `demoiselle-jsf-parent:2.5.0` | `demoiselle-parent-rest:4.0.0-SNAPSHOT` |
| Java | 6/7 | 17+ |
| Spec | Java EE 6 (`javax.*`) | Jakarta EE 10 (`jakarta.*`) |
| Apresentação | JSF 2.x + PrimeFaces + XHTML | REST (JAX-RS 3.1) + SPA (Vue 3) |
| Segurança | JAAS + `@RequiredRole`/`@RequiredPermission` | JWT + `@Authenticated`/`@RequiredRole` |
| Persistência | `JPACrud<E, ID>` | `AbstractDAO<E, ID>` |
| Negócio | `DelegateCrud<E, ID, DAO>` + `@BusinessController` | `AbstractBusiness<E, ID>` |
| Apresentação | `AbstractListPageBean` / `AbstractEditPageBean` | `AbstractREST<E, ID>` |
| Mensagens | `DefaultMessage` + enum | Interface com default methods |
| Transação | `@Transactional` (Demoiselle) | `@Transactional` (Jakarta JTA) |
| Servidor | JBoss AS 7 / WildFly 8 | WildFly 27+ |
| Frontend | JSF/XHTML integrado | Vue 3 + Vite + TypeScript (separado) |

---

## Fase 1 — Reestruturar o Projeto

Separar backend e frontend:

```
# v2.5 (monolítico)
app/
├── src/main/java/       # BC, DAO, Entity, ManagedBean, Authenticator
├── src/main/webapp/     # XHTML, CSS, JS, faces-config.xml
└── pom.xml

# v4 (separado)
app/
├── backend/
│   ├── src/main/java/   # REST, BC, DAO, Entity, Security
│   ├── src/main/resources/  # demoiselle.properties, persistence.xml
│   ├── src/main/webapp/     # beans.xml, web.xml
│   └── pom.xml
└── frontend/            # Vue 3 + Vite
    ├── src/
    ├── package.json
    └── vite.config.ts
```

Remover completamente:
- Todos os arquivos `.xhtml`
- `faces-config.xml`
- Classes `*MB.java` (ManagedBeans / ViewControllers)
- Converters JSF (`*Converter.java`)
- PrimeFaces e dependências JSF

## Fase 2 — Atualizar o POM

```xml
<!-- ANTES (v2.5) -->
<parent>
    <groupId>br.gov.frameworkdemoiselle</groupId>
    <artifactId>demoiselle-jsf-parent</artifactId>
    <version>2.5.0</version>
</parent>

<!-- DEPOIS (v4) -->
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

Remover todas as dependências v2.5:
- `demoiselle-jpa`, `demoiselle-report`, `demoiselle-validation`, `demoiselle-junit`
- PrimeFaces, `commons-fileupload`, `commons-io`
- Repositório `demoiselle.sourceforge.net`

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

## Fase 3 — Migrar Entidades

```java
// v2.5
package org.demoiselle.app.domain;
import javax.persistence.*;

@Entity
@Table(name="tb_automovel")
public class Automovel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    ...
}

// v4
package app.entity;
import jakarta.persistence.*;

@Entity
@Table(name="tb_automovel")
public class Automovel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    ...
}
```

Mudanças:
- `javax.persistence.*` → `jakarta.persistence.*`
- `javax.validation.*` → `jakarta.validation.*`
- Pacote `domain` → `entity` (convenção v3/v4)

## Fase 4 — Migrar DAO

```java
// v2.5
import br.gov.frameworkdemoiselle.template.JPACrud;

public class AutomovelDAO extends JPACrud<Automovel, Long> {
    private static final long serialVersionUID = 1L;
}

// v4
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.demoiselle.jee.crud.AbstractDAO;

public class AutomovelDAO extends AbstractDAO<Automovel, Long> {
    @PersistenceContext
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() { return em; }
}
```

## Fase 5 — Migrar BC

```java
// v2.5
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.message.MessageContext;

@BusinessController
public class AutomovelBC extends DelegateCrud<Automovel, Long, AutomovelDAO> {
    @Inject private MessageContext messageContext;

    @Override @Transactional
    @RequiredPermission(resource = "automovel", operation = "insert")
    public Automovel insert(Automovel entity) {
        super.insert(entity);
        messageContext.add(InfoMessages.AUTOMOVEL_INSERT_OK.getText());
        return entity;
    }
}

// v4
import org.demoiselle.jee.crud.AbstractBusiness;

public class AutomovelBC extends AbstractBusiness<Automovel, Long> {
    // CRUD herdado. Lógica customizada pode ser adicionada via @Override
}
```

Mapeamento de métodos:
| v2.5 | v4 |
|---|---|
| `insert(entity)` | `persist(entity)` |
| `update(entity)` | `mergeFull(entity)` |
| `delete(id)` | `remove(id)` |
| `findAll()` | `find()` (retorna `Result`) |
| `load(id)` | `find(id)` |

## Fase 6 — Criar REST (substituir ManagedBeans)

Cada ManagedBean/ViewController vira um endpoint REST:

```java
// v2.5 — ManagedBean
@ViewController
public class AutomovelListMB extends AbstractListPageBean<Automovel, Long> {
    @Inject private AutomovelBC bc;
    @Override
    protected List<Automovel> handleResultList() { return bc.findAll(); }
    public String deleteSelection() { ... }
}

// v4 — REST
import jakarta.ws.rs.*;
import jakarta.transaction.Transactional;
import org.demoiselle.jee.crud.AbstractREST;
import org.demoiselle.jee.security.annotation.Authenticated;

@Path("v1/automovel")
@Authenticated
public class AutomovelREST extends AbstractREST<Automovel, Long> {
    // GET, POST, PUT, DELETE herdados automaticamente
}
```

Criar também o `ApplicationConfig`:
```java
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("api")
public class ApplicationConfig extends Application { }
```

## Fase 7 — Migrar Segurança

```java
// v2.5 — Authenticator customizado (JAAS)
import br.gov.frameworkdemoiselle.security.Authenticator;

public class AppAuthenticator implements Authenticator {
    public void authenticate() throws Exception { ... }
    public void unauthenticate() throws Exception { ... }
    public Principal getUser() { ... }
}

// v4 — UserDAO com JWT
import jakarta.inject.Inject;
import org.demoiselle.jee.core.api.security.*;
import org.demoiselle.jee.security.exception.DemoiselleSecurityException;

public class UserDAO extends AbstractDAO<User, String> {
    @Inject private SecurityContext securityContext;
    @Inject private DemoiselleUser loggedUser;
    @Inject private Token token;

    public Token login(Credentials credentials) {
        User user = verifyEmail(credentials.getUsername(), credentials.getPassword());
        loggedUser.setName(user.getFirstName());
        loggedUser.setIdentity(user.getId().toString());
        loggedUser.addRole(user.getPerfil().getValue());
        securityContext.setUser(loggedUser);
        return token;
    }
}
```

Configurar JWT em `demoiselle.properties`:
```properties
demoiselle.security.jwt.type=master
demoiselle.security.jwt.privateKey=-----BEGIN PRIVATE KEY-----...
demoiselle.security.jwt.publicKey=-----BEGIN PUBLIC KEY-----...
demoiselle.security.jwt.timetoLiveMilliseconds=360000000
demoiselle.security.jwt.issuer=App
demoiselle.security.jwt.audience=web
demoiselle.security.jwt.algorithmIdentifiers=RS256
demoiselle.security.corsEnabled=true
```

Criar endpoint de autenticação:
```java
@Path("v1/auth")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class AuthREST {
    @Inject private UserDAO dao;

    @POST
    public Response login(Credentials credentials) {
        return Response.ok().entity(dao.login(credentials).toString()).build();
    }

    @GET @Authenticated
    public Response retoken() {
        return Response.ok().entity(dao.retoken().toString()).build();
    }
}
```

## Fase 8 — Migrar Mensagens

```java
// v2.5
import br.gov.frameworkdemoiselle.message.DefaultMessage;
final Message MSG = new DefaultMessage("{chave}");
messageContext.add(MSG.getText());

// v4 — interface simples
public interface AppMessage {
    default String automovelInsertOk() { return "Automóvel cadastrado com sucesso"; }
    default String automovelDeleteOk() { return "Automóvel removido com sucesso"; }
}
```

## Fase 9 — Atualizar XMLs

### persistence.xml
```xml
<persistence xmlns="https://jakarta.ee/xml/ns/persistence" version="3.0">
    <persistence-unit name="AppPU" transaction-type="JTA">
        <jta-data-source>java:/jboss/datasources/ExampleDS</jta-data-source>
        <class>app.entity.Automovel</class>
        <properties>
            <property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/>
            <property name="hibernate.hbm2ddl.auto" value="create-drop"/>
        </properties>
    </persistence-unit>
</persistence>
```

### beans.xml
```xml
<beans xmlns="https://jakarta.ee/xml/ns/jakartaee" version="4.0" bean-discovery-mode="all">
</beans>
```

### web.xml
```xml
<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee" version="6.0" metadata-complete="false">
</web-app>
```

Remover: `faces-config.xml`, `context.xml` (se não necessário).

## Fase 10 — Criar Frontend Vue 3

Para cada tela XHTML da v2.5, criar um componente Vue:

| XHTML (v2.5) | Vue (v4) | Descrição |
|---|---|---|
| `login.xhtml` | `LoginView.vue` | POST `/api/v1/auth` |
| `automovel_list.xhtml` | `AutomovelView.vue` | GET/POST/PUT/DELETE `/api/v1/automovel` |
| `automovel_edit.xhtml` | (integrado no AutomovelView) | Formulário no mesmo componente |
| `menu.xhtml` | `App.vue` (nav) | Router links |

Stack do frontend:
- Vue 3 + Composition API (`<script setup>`)
- Vite (bundler)
- TypeScript
- Pinia (estado/auth)
- Vue Router
- Axios (HTTP)

## Resumo das Transformações

```
v2.5                          →  v4
─────────────────────────────────────────────────
br.gov.frameworkdemoiselle     →  org.demoiselle.jee
demoiselle-jsf-parent:2.5.0   →  demoiselle-parent-rest:4.0.0-SNAPSHOT
javax.*                        →  jakarta.*
Java 6/7                       →  Java 17
JPACrud                        →  AbstractDAO
DelegateCrud                   →  AbstractBusiness
@BusinessController            →  (CDI bean padrão)
AbstractListPageBean           →  AbstractREST
@ViewController                →  @Path("...")
XHTML + PrimeFaces             →  Vue 3 + Vite
JAAS Authenticator             →  JWT (SecurityContext + Token)
DefaultMessage                 →  Interface com default methods
@Transactional (Demoiselle)    →  @Transactional (jakarta.transaction)
@RequiredPermission            →  @RequiredRole / lógica no BC
faces-config.xml               →  (removido)
beans.xml com interceptors     →  beans.xml vazio (Jakarta CDI 4.0)
JBoss AS 7 / WildFly 8         →  WildFly 27+
```

## Checklist

- [ ] Reestruturar projeto (backend/frontend separados)
- [ ] Atualizar POM (parent, groupId, Java 17, dependências)
- [ ] Migrar entidades (`javax.*` → `jakarta.*`, pacote domain → entity)
- [ ] Migrar DAOs (`JPACrud` → `AbstractDAO`)
- [ ] Migrar BCs (`DelegateCrud` → `AbstractBusiness`)
- [ ] Criar endpoints REST (substituir ManagedBeans)
- [ ] Criar `ApplicationConfig` com `@ApplicationPath`
- [ ] Migrar segurança (JAAS → JWT com `demoiselle.properties`)
- [ ] Criar `AuthREST` e `UserDAO.login()`
- [ ] Migrar mensagens (DefaultMessage → interfaces)
- [ ] Atualizar persistence.xml, beans.xml, web.xml (namespaces Jakarta EE 10)
- [ ] Remover XHTML, faces-config.xml, Converters JSF, PrimeFaces
- [ ] Criar frontend Vue 3 (Login, CRUD views)
- [ ] Compilar backend (`mvn clean package`)
- [ ] Instalar frontend (`npm install && npm run dev`)
- [ ] Testar integração backend + frontend
- [ ] Deploy no WildFly 27+
