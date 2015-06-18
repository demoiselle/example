#/bin/bash


# antes de iniciar deve configurar drivers.config

# buscar alias com o comando abaixo:
# keytool -keystore NONE -storetype PKCS11 -providerClass sun.security.pkcs11.SunPKCS11 -providerArg drivers.config -storepass PASSWORD -list

#Assina os arquivos .jar
echo "*** Assinando os arquivos .jar ..."

jarsigner -keystore NONE -storetype PKCS11 -providerClass sun.security.pkcs11.SunPKCS11 -providerArg drivers.config -storepass PASSWORD -sigfile  -signedjar demoiselle-certificate-applet-2.0.0-SNAPSHOT-assinado.jar -verbose demoiselle-certificate-applet-2.0.0-SNAPSHOT.jar "ALIAS"

jarsigner -keystore NONE -storetype PKCS11 -providerClass sun.security.pkcs11.SunPKCS11 -providerArg drivers.config -storepass PASSWORD -sigfile -signedjar applet-customizada-1.0.0-assinado.jar -verbose applet-customizada-1.0.0.jar "ALIAS"
   

jarsigner -keystore NONE -storetype PKCS11 -providerClass sun.security.pkcs11.SunPKCS11 -providerArg drivers.config -storepass PASSWORD -sigfile -signedjar demoiselle-certificate-ca-icpbrasil-2.0.0-SNAPSHOT-assinado.jar -verbose demoiselle-certificate-ca-icpbrasil-2.0.0-SNAPSHOT.jar "ALIAS"
  
jarsigner -keystore NONE -storetype PKCS11 -providerClass sun.security.pkcs11.SunPKCS11 -providerArg drivers.config -storepass PASSWORD -sigfile -signedjar demoiselle-certificate-ca-icpbrasil-homologacao-2.0.0-SNAPSHOT-assinado.jar -verbose demoiselle-certificate-ca-icpbrasil-homologacao-2.0.0-SNAPSHOT.jar "ALIAS"

jarsigner -keystore NONE -storetype PKCS11 -providerClass sun.security.pkcs11.SunPKCS11 -providerArg drivers.config -storepass PASSWORD -sigfile -signedjar demoiselle-certificate-core-2.0.0-SNAPSHOT-assinado.jar -verbose demoiselle-certificate-core-2.0.0-SNAPSHOT.jar "ALIAS"
   
jarsigner -keystore NONE -storetype PKCS11 -providerClass sun.security.pkcs11.SunPKCS11 -providerArg drivers.config -storepass PASSWORD -sigfile -signedjar demoiselle-certificate-criptography-2.0.0-SNAPSHOT-assinado.jar -verbose demoiselle-certificate-criptography-2.0.0-SNAPSHOT.jar "ALIAS"
   
jarsigner -keystore NONE -storetype PKCS11 -providerClass sun.security.pkcs11.SunPKCS11 -providerArg drivers.config -storepass PASSWORD -sigfile -signedjar demoiselle-certificate-policy-engine-2.0.0-SNAPSHOT-assinado.jar -verbose demoiselle-certificate-policy-engine-2.0.0-SNAPSHOT.jar "ALIAS"

jarsigner -keystore NONE -storetype PKCS11 -providerClass sun.security.pkcs11.SunPKCS11 -providerArg drivers.config -storepass PASSWORD -sigfile -signedjar demoiselle-certificate-signer-2.0.0-SNAPSHOT-assinado.jar -verbose demoiselle-certificate-signer-2.0.0-SNAPSHOT.jar "ALIAS"
  
jarsigner -keystore NONE -storetype PKCS11 -providerClass sun.security.pkcs11.SunPKCS11 -providerArg drivers.config -storepass PASSWORD -sigfile -signedjar demoiselle-certificate-timestamp-2.0.0-SNAPSHOT-assinado.jar -verbose demoiselle-certificate-timestamp-2.0.0-SNAPSHOT.jar "ALIAS"
 
jarsigner -keystore NONE -storetype PKCS11 -providerClass sun.security.pkcs11.SunPKCS11 -providerArg drivers.config -storepass PASSWORD -sigfile -signedjar htmlunit-2.15-assinado.jar -verbose htmlunit-2.15.jar "ALIAS"  

jarsigner -keystore NONE -storetype PKCS11 -providerClass sun.security.pkcs11.SunPKCS11 -providerArg drivers.config -storepass PASSWORD -sigfile -signedjar bcpkix-jdk15on-1.51-assinado.jar -verbose bcpkix-jdk15on-1.51.jar "ALIAS"   

jarsigner -keystore NONE -storetype PKCS11 -providerClass sun.security.pkcs11.SunPKCS11 -providerArg drivers.config -storepass PASSWORD -sigfile -signedjar bcmail-jdk15on-1.51-assinado.jar -verbose bcmail-jdk15on-1.51.jar "ALIAS"

jarsigner -keystore NONE -storetype PKCS11 -providerClass sun.security.pkcs11.SunPKCS11 -providerArg drivers.config -storepass PASSWORD -sigfile -signedjar bcprov-jdk15on-1.51-assinado.jar -verbose bcprov-jdk15on-1.51.jar "ALIAS"   

jarsigner -keystore NONE -storetype PKCS11 -providerClass sun.security.pkcs11.SunPKCS11 -providerArg drivers.config -storepass PASSWORD -sigfile -signedjar log4j-1.2.17-assinado.jar -verbose log4j-1.2.17.jar "ALIAS"   

jarsigner -keystore NONE -storetype PKCS11 -providerClass sun.security.pkcs11.SunPKCS11 -providerArg drivers.config -storepass PASSWORD -sigfile -signedjar slf4j-log4j12-1.6.1.jar-assinado.jar -verbose slf4j-log4j12-1.6.1.jar "ALIAS"   

jarsigner -keystore NONE -storetype PKCS11 -providerClass sun.security.pkcs11.SunPKCS11 -providerArg drivers.config -storepass PASSWORD -sigfile -signedjar slf4j-api-1.6.1-assinado.jar -verbose slf4j-api-1.6.1.jar "ALIAS"
   

echo "*** Fim!"

read -s -n 1 -p "Pressione uma tecla para finalizar…"







