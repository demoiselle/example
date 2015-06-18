#/bin/bash

# verificar as cadeias de certificados
# keytool -v -list -keystore $JAVA_HOME/jre/lib/security/cacerts -storepass changeit > certchain.txt 

#Assina os arquivos .jar
echo "*** Assinando os arquivos do Bouncy .jar ..."

sudo $JAVA_HOME/bin/keytool -import -alias AutoridadeCertificadoraRaizBrasileira -keystore $JAVA_HOME/jre/lib/security/cacerts -storepass changeit -trustcacerts -file AutoridadeCertificadoraRaizBrasileira.cer
sudo $JAVA_HOME/bin/keytool -import -alias AutoridadeCertificadoraRaizBrasileirav2 -keystore $JAVA_HOME/jre/lib/security/cacerts -storepass changeit -trustcacerts -file AutoridadeCertificadoraRaizBrasileirav2.cer
sudo $JAVA_HOME/bin/keytool -import  -alias AutoridadeCertificadoraRaizBrasileirav3 -keystore $JAVA_HOME/jre/lib/security/cacerts -storepass changeit -trustcacerts -file AutoridadeCertificadoraRaizBrasileirav3.cer
sudo $JAVA_HOME/bin/keytool -import -alias AutoridadeCertificadoradoSERPROFinalv3 -keystore $JAVA_HOME/jre/lib/security/cacerts -storepass changeit -trustcacerts -file AutoridadeCertificadoradoSERPROFinalv3.cer
sudo $JAVA_HOME/bin/keytool -import -alias SERVICOFEDERALDEPROCESSAMENTODEDADOSSERPROCETEC -keystore $JAVA_HOME/jre/lib/security/cacerts -storepass changeit -trustcacerts -file SERVICOFEDERALDEPROCESSAMENTODEDADOSSERPROCETEC.cer


echo "*** Fim!"

read -s -n 1 -p "Pressione uma tecla para finalizar…"














