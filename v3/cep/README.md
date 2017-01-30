# CEP App
Aplicação "Consulta CEP" com Demoiselle v3.0.

- **Auth**: (CRUD)
- **Cep**: Open

# Apps de exemplo

https://cep-fwkdemoiselle.rhcloud.com/ (Versão app desktop)

https://cep-fwkdemoiselle.rhcloud.com/swagger/ (Servidor Wildfly API)

https://cep-fwkdemoiselle.rhcloud.com/api/cep?cep=[SEU CEP] (Consulta CEP)


# Instalação

```bash
# Baixar o repositório
git clone https://github.com/demoiselle/example.git --depth=1
```

## Backend
```bash
# Instalar Java 8 

# Instalar o maven 3

# Acessar a pasta "backend"
cd example/v3/cep/backend

# Fazer o build
mvn clean package -Pwildfly-swarm

# Inicia a aplicação (backend)
java -jar -Xmx128m target/cep-swarm.jar

# Acesse: http://localhost:8080/

```

## Frontend - Progressive (Angular 1.5)
```bash
# Instalar nvm (https://github.com/creationix/nvm)

# Instalar NodeJS (atualmente v7.1.x)
nvm install stable

# Instalar 
npm install -g bower grunt-cli 

# Acessar a pasta "progressive"
cd example/v3/cep/progressive

# Inicia a aplicação 
grunt serve

# Acesse: http://localhost:9000/
```
https://www.gitbook.com/@demoiselle

* https://play.google.com/store/apps/details?id=org.demoiselle.cep (não implementado)
