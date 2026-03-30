# CEP App — Demoiselle v4

Aplicação de consulta de CEP brasileiro com Demoiselle v4.0 (Jakarta EE 10, Java 17+).

## Funcionalidades

- Busca por CEP, UF ou cidade
- Listagem de UFs
- Listagem de cidades por UF
- Busca de logradouros por UF e nome
- Geolocalização por IP (IP2Location)

## API REST

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/v1/ceps?cep=XXXXX` | Buscar por CEP |
| GET | `/api/v1/ceps?uf=XX` | Buscar por UF |
| GET | `/api/v1/ceps?cidade=XXX` | Buscar por cidade |
| GET | `/api/v1/ufs` | Listar UFs |
| GET | `/api/v1/cidades/{uf}` | Listar cidades por UF |
| GET | `/api/v1/logradouros/{uf}/{nome}` | Buscar logradouros |
| GET | `/api/v1/ips/v4/{ip}` | Geolocalização por IPv4 |

Sem autenticação — todos os endpoints são públicos.

## Backend

```bash
cd v4/cep/backend
mvn clean package -DskipTests
# Deploy target/cep.war no WildFly 27+
```

Datasource: `java:jboss/datasources/CEPDS` (PostgreSQL com base de CEPs)

## Frontend (Vue 3)

```bash
cd v4/cep/frontend
npm install
npm run dev
```

Acesse: http://localhost:7071/

### Build para produção

```bash
npm run build
# Arquivos gerados em dist/
```
