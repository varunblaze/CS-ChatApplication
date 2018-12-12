FROM goyalzz/ubuntu-java-8-maven-docker-image

RUN echo 'deb http://archive.ubuntu.com/ubuntu trusty main' >/etc/apt/sources.list
RUN echo 'deb http://archive.ubuntu.com/ubuntu trusty-security main' >>/etc/apt/sources.list
RUN echo 'deb http://archive.ubuntu.com/ubuntu trusty-updates main' >>/etc/apt/sources.list
RUN echo 'deb http://archive.ubuntu.com/ubuntu trusty universe' >>/etc/apt/sources.list

RUN apt-get update
RUN apt-get install -y --force-yes autoconf \
curl \
unzip
COPY . /tmp/chatapp
WORKDIR /tmp/chatapp
RUN mvn install:install-file -Dfile=/tmp/chatapp/depends/camel-chatscript-2.24.0-SNAPSHOT.jar -DgroupId=org.apache.camel.component  -DartifactId=camel-chatscript -Dversion=2.24.0-SNAPSHOT -Dpackaging=jar 
RUN mvn package
#COPY target/cschatapplication-0.0.1-SNAPSHOT.jar /deployments
RUN set -ex \
    && wget https://github.com/bwilcox-1234/ChatScript/archive/master.zip \
    && unzip master.zip \
    && chmod 755 ./ChatScript-master/BINARIES/LinuxChatScript64
# we want to install the client script so its easy to talk to the bot
#RUN chmod +x /bin/chatscript-client
ENV PORT 1024
ENV DEBIAN_FRONTEND noninteractive

RUN { echo '#!/bin/bash'; \
      echo 'set -e'; \
 	  echo 'cd /tmp/chatapp/ChatScript-master'; \
      echo 'cp -R /tmp/chatapp/RAWDATA/ ./RAWDATA';\
      echo './BINARIES/LinuxChatScript64 &'; \
      echo 'cd /tmp/chatapp/target';\
      echo 'java -jar cschatapplication-0.0.1-SNAPSHOT.jar'; \
    } > /entrypoint-chatscript.sh \
 && chmod +x /entrypoint-chatscript.sh
COPY RAWDATA ./ChatScript-master/RAWDATA/
RUN ls /tmp/chatapp/ChatScript-master/RAWDATA/
EXPOSE 1024 8080
# startup
USER root

CMD ["/bin/echo", "Hello ChatScript"]
CMD ["/entrypoint-chatscript.sh"]
