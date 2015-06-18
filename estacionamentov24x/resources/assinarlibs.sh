#/bin/bash

#Assina os arquivos .jar
echo "*** Assinando os arquivos .jar ..."

jarsigner -keystore assinadorjar.jks -storepass "teste1" -keypass "teste1" -signedjar demoiselle-certificate-applet-2.0.0-SNAPSHOT-assinado.jar demoiselle-certificate-applet-2.0.0-SNAPSHOT.jar AssinadorJar

jarsigner -keystore assinadorjar.jks -storepass "teste1" -keypass "teste1" -signedjar applet-customizada-1.0.0-assinado.jar applet-customizada-1.0.0.jar AssinadorJar

jarsigner -keystore assinadorjar.jks -storepass "teste1" -keypass "teste1" -signedjar demoiselle-certificate-ca-icpbrasil-2.0.0-SNAPSHOT-assinado.jar demoiselle-certificate-ca-icpbrasil-2.0.0-SNAPSHOT.jar AssinadorJar

jarsigner -keystore assinadorjar.jks -storepass "teste1" -keypass "teste1" -signedjar demoiselle-certificate-ca-icpbrasil-homologacao-2.0.0-SNAPSHOT-assinado.jar demoiselle-certificate-ca-icpbrasil-homologacao-2.0.0-SNAPSHOT.jar AssinadorJar

jarsigner -keystore assinadorjar.jks -storepass "teste1" -keypass "teste1" -signedjar demoiselle-certificate-core-2.0.0-SNAPSHOT-assinado.jar demoiselle-certificate-core-2.0.0-SNAPSHOT.jar AssinadorJar

jarsigner -keystore assinadorjar.jks -storepass "teste1" -keypass "teste1" -signedjar demoiselle-certificate-criptography-2.0.0-SNAPSHOT-assinado.jar demoiselle-certificate-criptography-2.0.0-SNAPSHOT.jar AssinadorJar

jarsigner -keystore assinadorjar.jks -storepass "teste1" -keypass "teste1" -signedjar demoiselle-certificate-policy-engine-2.0.0-SNAPSHOT-assinado.jar demoiselle-certificate-policy-engine-2.0.0-SNAPSHOT.jar AssinadorJar

jarsigner -keystore assinadorjar.jks -storepass "teste1" -keypass "teste1" -signedjar demoiselle-certificate-signer-2.0.0-SNAPSHOT-assinado.jar demoiselle-certificate-signer-2.0.0-SNAPSHOT.jar AssinadorJar

jarsigner -keystore assinadorjar.jks -storepass "teste1" -keypass "teste1" -signedjar demoiselle-certificate-timestamp-2.0.0-SNAPSHOT-assinado.jar demoiselle-certificate-timestamp-2.0.0-SNAPSHOT.jar AssinadorJar

jarsigner -keystore assinadorjar.jks -storepass "teste1" -keypass "teste1" -signedjar htmlunit-2.15-assinado.jar htmlunit-2.15.jar AssinadorJar

jarsigner -keystore assinadorjar.jks -storepass "teste1" -keypass "teste1" -signedjar bcpkix-jdk15on-1.51-assinado.jar bcpkix-jdk15on-1.51.jar AssinadorJar

jarsigner -keystore assinadorjar.jks -storepass "teste1" -keypass "teste1" -signedjar bcmail-jdk15on-1.51-assinado.jar bcmail-jdk15on-1.51.jar AssinadorJar

jarsigner -keystore assinadorjar.jks -storepass "teste1" -keypass "teste1" -signedjar bcprov-jdk15on-1.51-assinado.jar bcprov-jdk15on-1.51.jar AssinadorJar

jarsigner -keystore assinadorjar.jks -storepass "teste1" -keypass "teste1" -signedjar log4j-1.2.17-assinado.jar log4j-1.2.17.jar AssinadorJar

jarsigner -keystore assinadorjar.jks -storepass "teste1" -keypass "teste1" -signedjar slf4j-log4j12-1.6.1.jar-assinado.jar slf4j-log4j12-1.6.1.jar AssinadorJar

jarsigner -keystore assinadorjar.jks -storepass "teste1" -keypass "teste1" -signedjar slf4j-api-1.6.1-assinado.jar slf4j-api-1.6.1.jar AssinadorJar

read -s -n 1 -p "Press any key to continue…"

echo "*** Fim!"





