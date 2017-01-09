#/bin/bash

#Assina os arquivos .jar
echo "*** Assinando os arquivos .jar ..."




jarsigner -verify  -verbose -certs -keystore assinadorjar.jks demoiselle-certificate-applet-2.0.0-SNAPSHOT-assinado.jar 

jarsigner -verify  -verbose -certs -keystore assinadorjar.jks  demoiselle-certificate-applet-customizada-1.0.0-assinado.jar

jarsigner -verify  -verbose -certs -keystore assinadorjar.jks  demoiselle-certificate-ca-icpbrasil-2.0.0-SNAPSHOT-assinado.jar

jarsigner -verify  -verbose -certs -keystore assinadorjar.jks  demoiselle-certificate-ca-icpbrasil-homologacao-2.0.0-SNAPSHOT-assinado.jar 

jarsigner -verify  -verbose -certs -keystore assinadorjar.jks  demoiselle-certificate-core-2.0.0-SNAPSHOT-assinado.jar 

jarsigner -verify  -verbose -certs -keystore assinadorjar.jks  demoiselle-certificate-criptography-2.0.0-SNAPSHOT-assinado.jar

jarsigner -verify  -verbose -certs -keystore assinadorjar.jks  demoiselle-certificate-policy-engine-2.0.0-SNAPSHOT-assinado.jar 

jarsigner -verify  -verbose -certs -keystore assinadorjar.jks  demoiselle-certificate-signer-2.0.0-SNAPSHOT-assinado.jar 

jarsigner -verify  -verbose -certs -keystore assinadorjar.jks  demoiselle-certificate-timestamp-2.0.0-SNAPSHOT-assinado.jar 

jarsigner -verify  -verbose -certs -keystore assinadorjar.jks  htmlunit-2.15-assinado.jar

jarsigner -verify  -verbose -certs -keystore assinadorjar.jks  bcpkix-jdk15on-1.51-assinado.jar

jarsigner -verify  -verbose -certs -keystore assinadorjar.jks  bcmail-jdk15on-1.51-assinado.jar

jarsigner -verify  -verbose -certs -keystore assinadorjar.jks  bcprov-jdk15on-1.51-assinado.jar

jarsigner -verify  -verbose -certs -keystore assinadorjar.jks  log4j-1.2.17-assinado.jar

jarsigner -verify  -verbose -certs -keystore assinadorjar.jks  slf4j-log4j12-1.6.1.jar-assinado.jar

jarsigner -verify  -verbose -certs -keystore assinadorjar.jks  slf4j-api-1.6.1-assinado.jar


echo "*** Fim!"



