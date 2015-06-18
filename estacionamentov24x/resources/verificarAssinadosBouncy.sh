#/bin/bash

#Assina os arquivos .jar
echo "*** Assinando os arquivos .jar ..."

jarsigner -verify  -verbose -certs -keystore assinadorjar.jks  bcpkix-jdk15on-1.51-assinado.jar

jarsigner -verify  -verbose -certs -keystore assinadorjar.jks  bcmail-jdk15on-1.51-assinado.jar

jarsigner -verify  -verbose -certs -keystore assinadorjar.jks  bcprov-jdk15on-1.51-assinado.jar


echo "*** Fim!"



