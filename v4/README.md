# Demoiselle v4 — Exemplos

Aplicações de exemplo do Framework Demoiselle 4.0 (Jakarta EE 10, Java 17+).

## Pré-requisitos

### Backend
- Java 17+
- Maven 3.9+
- WildFly 27+ (ou servidor Jakarta EE 10 compatível)

### Frontend
- Node.js 18+
- npm 9+

## Apps

| App | Descrição | Backend | Frontend | Porta Dev |
|---|---|---|---|---|
| [todoList](todoList/) | CRUD de tarefas com autenticação | REST + JPA + JWT | Vue 3 | 7070 |
| [cep](cep/) | Consulta de CEP brasileiro | REST + JPA | Vue 3 | 7071 |
| [livraria](livraria/) | Multitenancy com livros | REST + JPA + JWT | Vue 3 | 7072 |
| [forum](forum/) | Fórum com categorias/tópicos/mensagens | REST + JPA + JWT + WebSocket + Email | Vue 3 | 7073 |
| [push](push/) | Servidor de push via WebSocket | REST + WebSocket + Scheduler | — | — |

## Quick Start

### 1. Instalar o framework (se ainda não estiver no Maven local)

```bash
cd ../framework
mvn install -DskipTests
```

### 2. Build do backend (exemplo: todoList)

```bash
cd v4/todoList/backend
mvn clean package -DskipTests
```

### 3. Deploy no WildFly 27+

Copie o WAR gerado em `target/` para a pasta `deployments/` do WildFly.

### 4. Rodar o frontend

```bash
cd v4/todoList/frontend
npm install
npm run dev
```

Acesse http://localhost:7070/

## Stack

### Backend
- Demoiselle 4.0.0-SNAPSHOT (Jakarta EE 10)
- Java 17
- CDI 4.0, JAX-RS 3.1, JPA 3.1, Bean Validation 3.0
- JWT (jose4j 0.9.6)
- Hibernate 6.x (provido pelo servidor)

### Frontend
- Vue 3.4 + Composition API (`<script setup>`)
- TypeScript 5.4
- Vite 5.4
- Pinia 2.1 (gerenciamento de estado)
- Vue Router 4.3
- Axios 1.7

## Migração da v3

A v3 usava Java EE 7 (`javax.*`), Java 8, Angular 4/5, WildFly Swarm e Swagger 1.x.

Principais mudanças na v4:
- `javax.*` → `jakarta.*` em todo o código Java
- Java 8 → Java 17
- WildFly Swarm → WildFly 27+ (Jakarta EE 10)
- Swagger 1.x → removido (usar MicroProfile OpenAPI se necessário)
- DeltaSpike → removido
- JUnit 4 → JUnit 5
- Angular 4/5 → Vue 3 + Vite + TypeScript
- Webpack → Vite

Detalhes completos no [documento de processo de atualização](PROCESSO_ATUALIZACAO_V4.md).

## Estrutura

```
v4/
├── README.md                    # Este arquivo
├── PROCESSO_ATUALIZACAO_V4.md   # Documento de migração v3 → v4
├── todoList/
│   ├── backend/                 # Java (Maven WAR)
│   ├── frontend/                # Vue 3 (Vite)
│   └── README.md
├── cep/
│   ├── backend/
│   ├── frontend/
│   └── README.md
├── livraria/
│   ├── backend/
│   ├── frontend/
│   └── README.md
├── forum/
│   ├── backend/
│   ├── frontend/
│   └── README.md
└── push/
    ├── backend/
    └── README.md
```

## Licença

GNU Lesser General Public License, Version 3 — [LGPL v3](http://www.gnu.org/licenses/lgpl-3.0.txt)
