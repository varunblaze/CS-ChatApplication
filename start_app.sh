#!/bin/sh
set -x
pwd
unzip ./depends/ChatScriptNLP.zip
chmod 755 ./ChatScriptNLP/ChatScriptNLP/BINARIES/LinuxChatScript64
./ChatScriptNLP/ChatScriptNLP/BINARIES/LinuxChatScript64
java -jar ./target/CSChatApplication-0.0.1-SNAPSHOT.jar 
