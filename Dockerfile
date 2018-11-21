FROM openjdk:8-jre-slim

RUN yum install -y autoconf \
curl \
vim \
unzip

WORKDIR /tmp/ChatScriptNLP
ADD . /data/ChatScriptNLP

RUN chmod 755 /data/ChatScriptNLP/BINARIES/LinuxChatScript64

RUN { echo '#!/bin/bash'; \
      echo 'set -e'; \
	    echo 'cd /data/ChatScriptNLP/BINARIES'; \
      echo './LinuxChatScript64 &'; \ 
	  echo 'java -jar /deployments/cschatapplication-0.0.1-SNAPSHOT.jar'
    } > /entrypoint-app.sh \
 && chmod +x /entrypoint-app.sh

EXPOSE 1024

# startup
USER root

CMD ["/entrypoint-app.sh"]
