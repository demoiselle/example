#/bin/bash


# antes de iniciar deve configurar drivers.config
# buscar alias com o comando abaixo:
# keytool -keystore NONE -storetype PKCS11 -providerClass sun.security.pkcs11.SunPKCS11 -providerArg drivers.config -storepass PASSWORD -list

#Assina os arquivos .jar
echo "*** Assinando os arquivos .jar ..."


jarsigner -keystore NONE -storetype PKCS11 -providerClass sun.security.pkcs11.SunPKCS11 -providerArg drivers.config -storepass PASSWORD -sigfile  -signedjar bcpkix-jdk15on-1.51-assinado.jar -verbose bcpkix-jdk15on-1.51.jar "ALIAS"

jarsigner -keystore NONE -storetype PKCS11 -providerClass sun.security.pkcs11.SunPKCS11 -providerArg drivers.config -storepass PASSWORD -sigfile  -signedjar bcmail-jdk15on-1.51-assinado.jar -verbose bcmail-jdk15on-1.51.jar "ALIAS"

jarsigner -keystore NONE -storetype PKCS11 -providerClass sun.security.pkcs11.SunPKCS11 -providerArg drivers.config -storepass PASSWORD -sigfile  -signedjar bcprov-jdk15on-1.51-assinado.jar -verbose bcprov-jdk15on-1.51.jar "ALIAS"

echo "*** Fim!"

read -s -n 1 -p "Pressione uma tecla para finalizar…"



