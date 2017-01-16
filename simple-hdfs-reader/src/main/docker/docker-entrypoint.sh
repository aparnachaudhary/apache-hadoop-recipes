#!/usr/bin/env bash

java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -DhdfsHost=$HDFS_HOST -DhdfsPort=$HDFS_PORT -jar $1.jar