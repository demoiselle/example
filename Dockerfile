FROM demoiselleframework/docker

MAINTAINER Demoiselle Framework <demoiselle.framework@gmail.com>

WORKDIR /opt/

ADD https://www.demoiselle.org/cep/db.tar.gz /opt/db/

CMD ["tar -zxvf /opt/db/db.tar.gz"]

ADD https://www.demoiselle.org/cep/cep-swarm.jar /opt/app/

RUN java -jar -Xmx256m -Xms128m app/cep-swarm.jar

EXPOSE 8080
