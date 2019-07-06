#!/bin/bash

docker volume rm prim-rabbitmq-db

docker run --name rabbit_create -d --rm --hostname my-rabbit -p 5672:5672 -p 15672:15672 --mount source=prim-rabbitmq-db,target=/var/lib/rabbitmq rabbitmq:3-management
