# Livraria App — Demoiselle v4

Exemplo de multitenancy com Demoiselle v4.0 (Jakarta EE 10, Java 17+).

## Funcionalidades

- Cadastro e login de usuários (JWT)
- CRUD de livros (título, descrição, conteúdo, URL)
- Livros vinculados ao usuário/tenant
- Listagem de usuários

## API REST

| Método | Endpoint | Descrição | Auth |
|---|---|---|---|
| POST | `/api/v1/auth` | Login | Não |
| GET | `/api/v1/auth` | Retoken | Sim |
| POST | `/api/v1/user` | Cadastrar usuário | Não |
| GET | `/api/v1/user` | Listar usuários | Sim |
| GET | `/api/v1/livro` | Listar livros | Sim |
| POST | `/api/v1/livro` | Criar livro | Sim |
| PUT | `/api/v1/livro` | Atualizar livro | Sim |
| DELETE | `/api/v1/livro/{id}` | Remover livro | Sim |
| GET | `/api/v1/constants/perfil` | Listar perfis | Sim |

## Backend

```bash
cd v4/livraria/backend
mvn clean package -DskipTests
# Deploy target/livraria.war no WildFly 27+
```

Datasource: `java:jboss/datasources/LivrariaDS` (H2 em memória por padrão)

Credenciais padrão: `admin@demoiselle.org` / `123456`

## Frontend (Vue 3)

```bash
cd v4/livraria/frontend
npm install
npm run dev
```

Acesse: http://localhost:7072/

### Build para produção

```bash
npm run build
```
