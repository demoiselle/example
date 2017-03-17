FROM demoiselleframework/docker

MAINTAINER Demoiselle Framework <demoiselle.framework@gmail.com>

RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv EA312927
RUN echo "deb http://repo.mongodb.org/apt/ubuntu $(cat /etc/lsb-release | grep DISTRIB_CODENAME | cut -d= -f2)/mongodb-org/3.2 multiverse" | tee /etc/apt/sources.list.d/mongodb-org-3.2.list

RUN mkdir -p /data/db

RUN apt-get update && \
	apt-get install -y curl mongodb-org  && \
	apt-get clean ;

WORKDIR /opt/

RUN mongoimport -d dados -c cep --type json --file cep5.json --jsonArray

# User root user to install software
USER root

# Set the JAVA_HOME variable to make it clear where Java is located
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64

# Expose the ports we're interested in
EXPOSE 8080