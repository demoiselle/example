# CEP App
Aplicação "Consulta CEP" com Demoiselle v3.0.

- **Auth**: (CRUD)
- **Cep**: Open

Verificação de segurança da api (https://schd.io/4EoM)
Verificação de segurança do demoiselle.org (https://schd.io/30wv)

# Progessive Web

https://www.demoiselle.org/cep/#!/

# Windows Store
https://www.microsoft.com/pt-br/store/p/cep/9pchwwpc3phr?rtc=1

# Docker

https://hub.docker.com/r/demoiselleframework/cep/

docker pull demoiselleframework/cep

docker run -t -i -p 8080:8080 --network="host" demoiselleframework/cep

# Api do servidor

http://cep.demoiselle.estaleiro.serpro.gov.br/app/ (Servidor Wildfly 10.1.0 API)

# Exemplos de consulta (Restful padrão)

```bash
Lista de UF
http://cep.demoiselle.estaleiro.serpro.gov.br/app/api/v1/ufs

Lista de Localidades por UF
http://cep.demoiselle.estaleiro.serpro.gov.br/app/api/v1/cidades/PR

Lista de Logradouros por UF
http://cep.demoiselle.estaleiro.serpro.gov.br/app/api/v1/logradouros/PR/Pioli
```

# Exemplos de consulta (Usando componente CRUD)

 ```bash
 Filtro por Logradouros do Paraná (PR)
 http://cep.demoiselle.estaleiro.serpro.gov.br/app/api/v1/ceps?cep=80520170
  
 Filtro por Logradouros do Paraná (PR)
 http://cep.demoiselle.estaleiro.serpro.gov.br/app/api/v1/ceps?uf=PR
 
 Filtro por Logradouros do Paraná (Curitiba)
 http://cep.demoiselle.estaleiro.serpro.gov.br/app/api/v1/ceps?cidade=Curitiba
 
 Filtro por Logradouros do Paraná (Curitiba) com apenas 11 registros
 http://cep.demoiselle.estaleiro.serpro.gov.br/app/api/v1/ceps?cidade=Curitiba&range=0-10
 
 Filtro por Logradouros do Paraná (Curitiba) ordenado por logradouro
 http://cep.demoiselle.estaleiro.serpro.gov.br/app/api/v1/ceps?cidade=Curitiba&sort=logradouro
 
 Filtro por Logradouros do Paraná (Curitiba) ordenado por bairro
 http://cep.demoiselle.estaleiro.serpro.gov.br/app/api/v1/ceps?cidade=Curitiba&sort=bairroIni
```

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

# Descompactar a Base que está na pasta /data/ para /opt/db/

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

# Rodar os instaladores
npm install && bower install

# Inicia a aplicação 
grunt serve

# Acesse: http://localhost:9000/
```
https://www.gitbook.com/@demoiselle

* https://play.google.com/store/apps/details?id=org.demoiselle.cep (não implementado)
