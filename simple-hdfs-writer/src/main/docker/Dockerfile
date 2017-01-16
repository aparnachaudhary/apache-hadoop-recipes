#
# Hadoop Recipe docker file
#
# Pull the JDK base image

FROM openjdk:8

MAINTAINER Aparna Chaudhary

ENV HDFS_HOST localhost
ENV HDFS_PORT 9000

ADD simple-hdfs-writer.jar /opt/app.jar

ENV JAVA_OPTS=""

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -DhdfsHost=$HDFS_HOST -DhdfsPort=$HDFS_PORT -jar /opt/app.jar" ]

#CMD ["/bin/bash"]
