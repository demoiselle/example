# todoList
Progressive App CRUD com Demoiselle v 3.0

Baixar o repositório
git clone https://github.com/demoiselle/example.git --depth=1


#### Backend
``` java

Instalar Java 8 
Instalar o maven 3

acessar a pasta
cd v3/todoList/backend

mvn clean package -Pwildfly-swarm && java -jar -Xmx128m target/todo-swarm.jar

http://localhost:8080/

```
#### Frontend - Angular
``` java

Instalar NodeJS 
Instalar mvn

acessar a pasta
cd v3/todoList/frontend

npm install
npm start

http://localhost:7070/
```

#### Frontend - Progressive
``` javascripts
Instalar NodeJS 
Instalar mvn

acessar a pasta
cd v3/todoList/progressive

npm -g ionic cordova
npm install

ionic serve

http://localhost:8100/
```
