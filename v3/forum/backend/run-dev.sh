#!/bin/bash
# Coloque vari�veis de ambiente
# Build and run
mvn clean package -Pwildfly-swarm && java -jar target/app-swarm.jar
