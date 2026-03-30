# Forum App — Demoiselle v4

Aplicação de fórum com Demoiselle v4.0 (Jakarta EE 10, Java 17+).

## Funcionalidades

- Cadastro e login de usuários (JWT + social login)
- CRUD de categorias
- CRUD de tópicos vinculados a categorias
- Mensagens em tópicos
- Push via WebSocket
- Envio de email
- Scheduler (timer)

## API REST

| Método | Endpoint | Descrição | Auth |
|---|---|---|---|
| POST | `/api/v1/auth` | Login | Não |
| GET | `/api/v1/auth` | Retoken | Sim |
| POST | `/api/v1/user` | Cadastrar | Não |
| GET | `/api/v1/user` | Listar usuários | Sim |
| GET/POST/PUT/DELETE | `/api/v1/categoria` | CRUD categorias | Sim |
| GET/POST/DELETE | `/api/v1/topico` | CRUD tópicos | Sim |
| GET/POST/DELETE | `/api/v1/mensagem` | CRUD mensagens | Sim |
| GET | `/api/v1/constants/perfil` | Listar perfis | Sim |

## Backend

```bash
cd v4/forum/backend
mvn clean package -DskipTests
# Deploy target/forum.war no WildFly 27+
```

Datasource: `java:/jboss/datasources/ExampleDS` (H2)

Credenciais padrão: `admin@demoiselle.org` / `123456`

## Frontend (Vue 3)

```bash
cd v4/forum/frontend
npm install
npm run dev
```

Acesse: http://localhost:7073/

### Build para produção

```bash
npm run build
```

## Notas

- A assinatura DKIM de emails foi removida por incompatibilidade da biblioteca `utils-mail-dkim` com Jakarta Mail. O envio de email funciona normalmente sem DKIM.
- WebSocket push disponível em `ws://localhost:8080/push/{channel}`
