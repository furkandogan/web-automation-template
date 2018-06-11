FROM maven:3.5.2-jdk-9-slim
MAINTAINER furkandogan
#ENV environment preprod
RUN apt-get update && apt-get upgrade -y && apt-get install -y wget unzip
RUN mkdir /automation-test-bdd
COPY entrypoint.sh /
ENV profile preprod