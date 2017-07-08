FROM demoiselleframework/docker

MAINTAINER Demoiselle Framework <demoiselle.framework@gmail.com>

WORKDIR /opt/

ADD https://www.demoiselle.org/cep/db.tar.gz /opt/db/

CMD ["tar -zxvf /opt/db/db.tar.gz -C /opt/db/"]

ADD https://www.demoiselle.org/cep/cep-swarm.jar /opt/app/

RUN java -jar app/cep-swarm.jar

EXPOSE 8080
