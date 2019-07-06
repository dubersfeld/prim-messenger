# prim-messenger
I present here a Java demo of the Minimum Spanning Tree using a parallelizable implementation of Prim algorithm.

Here are the prerequisites to run this demo:

A recent Maven version installed (I use 3.6.0).
A recent Docker version installed (I use 17.12.1-ce)

The algorithm itself is implemented in Java, Javascript is only used for initialization and display on Canvas.

First an undirected graph is randomly created and its largest connected component is extracted (Java side).

Then a minimum spanning tree is built (Java side) with all intermediate results saved as a collection.

This collection is then sent to the browser as a JSON object.

The collection is used for an animated display (browser side).

In this version the algorithm is implemented by three workers running in Docker containers and a supervisor. All communication between supervisor and worker is implemented by a message broker. In this way workers don't expose any port. 

The task partitioning between supervisor and workers closely follows the description given in this textbook:

Guide to Graph Algorithms, K. Erciyes, Springer

Here are the containers used in the demo:

| Service name    | Image                 | Role   | Port exposed |
| --------------- | --------------------- | ------ | ------------ |
| rabbitmq-server | rabbitmq:3-management | broker | 5672         |
| worker-1        | prim/worker           | worker | none         |
| worker-2        | prim/worker           | worker | none         |
| worker-3        | prim/worker           | worker | none         |

# Building Docker image
To build the Docker image run the command `mvn clean package docker:build` in subdirectory worker. It builds a docker image named prim/worker.

# Creating Docker volume
To build the Docker volume needed for RabbitMQ access run the shell `./rabbitVolume.sh` in subdirectory docker/rabbitmq.
Then open a browser and connect to RabbiMQ manager on port 15672. Create two topic exchanges names `primBroadcastAll` and `primReceiveFromAll` then create a user with name `spring` and password `password1234` and grant it access to the two new topics.
Then free the volume by running the shell `./rabbitKill.sh`

# Launching the demo

## Launching the workers
To launch the worker and the RabbitMQ broker run the command `docker-compose up` in subdirectory docker.

## Launching the supervisor
To launch the supervisor run the command `mvn spring-boot:run` in subdirectory supervisor.

When the application has started open a browser and hit URL `http://localhost:8080/minimum-spanning-tree`.

Here are some screen shots that can be seen in this demo.

After graph initialization:
![alt text](images/init.png "Graph initialized")

After the component was found:
![alt text](images/component.png "Component found")

After the end of the animation:
![alt text](images/minimumSpanningTree.png "Minimum Spanning Tree")

The messaging architecture is shown on this picture:
![alt text](images/messaging.png "Messaging architecture")



Dominique Ubersfeld, Cachan, France
