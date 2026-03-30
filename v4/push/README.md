# Push App — Demoiselle v4

Servidor de push via WebSocket com Demoiselle v4.0 (Jakarta EE 10, Java 17+).

## Funcionalidades

- Push de mensagens em tempo real via WebSocket
- Canais de comunicação (UUID ou string customizada)
- Scheduler automático (echo a cada 3 min, hora a cada 1 min)
- API REST para envio de mensagens

## API REST

| Método | Endpoint | Descrição | Auth |
|---|---|---|---|
| POST | `/api/auth` | Login | Não |
| GET | `/api/auth` | Retoken | Sim |
| POST | `/api/push` | Enviar mensagem para canal | Sim |
| GET | `/api/push/{channel}` | Listar usuários do canal | Sim |
| GET | `/api/push/{channel}/count` | Contar usuários do canal | Sim |
| GET | `/api/push/count` | Contar total de conexões | Não |

## WebSocket

Conectar em: `ws://localhost:8080/push/{channel}`

Mensagens JSON:
```json
{"event": "login", "data": "user-id"}
{"event": "count", "data": ""}
{"event": "logout", "data": ""}
```

## Backend

```bash
cd v4/push/backend
mvn clean package -DskipTests
# Deploy target/push.war no WildFly 27+
```

Datasource: `java:/jboss/datasources/ExampleDS` (H2)

Este app não possui frontend web — use um cliente WebSocket para testar.
