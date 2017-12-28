#!/bin/bash

# Coloque variáveis de ambiente
# Build and run
mvn clean package -Pwildfly-swarm && java -jar target/biblia-swarm.jar
