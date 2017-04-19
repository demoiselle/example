FROM demoiselleframework/docker

MAINTAINER Demoiselle Framework <demoiselle.framework@gmail.com>

RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv EA312927
RUN echo "deb http://repo.mongodb.org/apt/ubuntu xenial/mongodb-org/3.2 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-3.2.list

RUN mkdir -p /data/db

RUN apt-get update && \
	apt-get install -y curl mongodb-org && \
	apt-get clean ;

WORKDIR /opt/

ADD https://cep-fwkdemoiselle.rhcloud.com/dados/Cep.json.tar.gz /opt/
RUN tar zxvf /opt/Cep.json.tar.gz
RUN mongoimport -d dados -c cep --type json --file /opt/cep5.json --jsonArray

ADD https://cep-fwkdemoiselle.rhcloud.com/dados/cep-swarm.jar /opt/

RUN java -jar /opt/cep-swarm.jar

EXPOSE 8080
