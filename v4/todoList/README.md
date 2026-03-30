# To-Do App — Demoiselle v4

Aplicação de gerenciamento de tarefas com Demoiselle v4.0 (Jakarta EE 10, Java 17+).

## Funcionalidades

- Cadastro e login de usuários (JWT)
- CRUD de tarefas (To-Do) vinculadas ao usuário logado
- Controle de status (Agendado, Iniciado, Finalizado)
- Listagem de usuários (admin/gerente)

## API REST

| Método | Endpoint | Descrição | Auth |
|---|---|---|---|
| POST | `/api/v1/auth` | Login | Não |
| GET | `/api/v1/auth` | Retoken | Sim |
| POST | `/api/v1/user` | Cadastrar usuário | Não |
| GET | `/api/v1/user` | Listar usuários | Sim (Admin) |
| GET | `/api/v1/user/{id}` | Buscar usuário | Sim |
| PUT | `/api/v1/user` | Atualizar usuário | Sim |
| DELETE | `/api/v1/user/{id}` | Remover usuário | Sim |
| GET | `/api/v1/todo` | Listar tarefas do usuário | Sim |
| POST | `/api/v1/todo` | Criar tarefa | Sim |
| PUT | `/api/v1/todo` | Atualizar tarefa | Sim |
| DELETE | `/api/v1/todo/{id}` | Remover tarefa | Sim |
| GET | `/api/v1/constants/perfil` | Listar perfis | Sim |
| GET | `/api/v1/constants/status` | Listar status | Sim |

## Backend

```bash
cd v4/todoList/backend
mvn clean package -DskipTests
# Deploy target/todo.war no WildFly 27+
```

Datasource: `java:/jboss/datasources/ExampleDS` (H2 em memória por padrão)

Credenciais padrão: `admin@demoiselle.org` / `123456`

## Frontend (Vue 3)

```bash
cd v4/todoList/frontend
npm install
npm run dev
```

Acesse: http://localhost:7070/

### Build para produção

```bash
npm run build
# Arquivos gerados em dist/
```
