#!/bin/sh

java -Djava.security.egd=file:/dev/./urandom \
-Dspring.cloud.config.uri=$PRIM_CONFIG_URI \
-Dspring.profiles.active=$PROFILE -Dworker.id=$WORKER_ID -jar /app.jar
