#/bin/bash

#Assina os arquivos .jar
echo "*** Assinando os arquivos .jar ..."



jarsigner -tsa https://www.freetsa.org -J-Dhttps.proxyHost=https://www.freetsa.org -J-Dhttps.proxyPort=318  -digestalg SHA-1 -keystore assinadorjar.jks -storepass "teste1" -keypass "teste1" -signedjar bcpkix-jdk15on-1.51-assinado.jar bcpkix-jdk15on-1.51.jar Assinadorjar

# jarsigner -tsa http://www.freetsa.org:318 -digestalg SHA-1 -keystore assinadorjar.jks -storepass "teste1" -keypass "teste1" -signedjar bcmail-jdk15on-1.51-assinado.jar bcmail-jdk15on-1.51.jar Assinadorjar

# jarsigner -tsa http://www.freetsa.org:318 -digestalg SHA-1 -keystore assinadorjar.jks -storepass "teste1" -keypass "teste1" -signedjar bcprov-jdk15on-1.51-assinado.jar bcprov-jdk15on-1.51.jar Assinadorjar


echo "*** Fim!"


# https://www.freetsa.org:318

# http://tsa.safecreative.org/

# https://timestamp.geotrust.com/
