# To-Do App
Aplicação "To-Do" com Demoiselle v3.0.

- **Auth**: Register, Login/Logout
- **To-Do**: Form, List (CRUD)

# Apps de exemplo
http://demoiselle.io/jee/example/todo/progressive (Versão app celular)

http://demoiselle.io/jee/example/todo/frontend (Versão app desktop)

http://todolist-demoiselle.44fs.preview.openshiftapps.com/ (Servidor Wildfly)



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
cd example/v3/todoList/backend

# Fazer o build
mvn clean package -Pwildfly-swarm

# Inicia a aplicação (backend)
java -jar -Xmx128m target/todo-swarm.jar

# Acesse: http://localhost:8080/

```

## Frontend - Angular
```bash
# Instalar nvm (https://github.com/creationix/nvm#install-script)

# Instalar NodeJS (atualmente v7.1.x)
nvm install stable

# Acessar a pasta "frontend"
cd example/v3/todoList/frontend

# Instalar os módulos dependentes
npm install

# Inicia a aplicação (frontend)
npm start

# Acesse: http://localhost:7070/
```

## Frontend - Progressive (Ionic2)
```bash
# Instalar nvm (https://github.com/creationix/nvm)

# Instalar NodeJS (atualmente v7.1.x)
nvm install stable

# Instalar o Ionic/Cordova
npm install -g ionic cordova

# Acessar a pasta "progressive"
cd example/v3/todoList/progressive

# Instalar os módulos dependentes
npm install

# Inicia a aplicação (ionic)
ionic serve

# Acesse: http://localhost:8100/
```
https://www.gitbook.com/@demoiselle

https://play.google.com/store/apps/details?id=org.demoiselle.todo
