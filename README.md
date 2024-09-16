# Progetto TAASS 2023-24
# "Il bello dell'artigianato"

## Description
This application allows
- **Everyone**
  - To **search** for stores based on multiple search criteria (name, category, region, province,city)
  - To view **information** about a store (name, description, owners, contacts, addresses)
  - To view **comments** posted by users related to a store
  - To view a **sampler** with the most significant products sold in a store
- **Registered users**
  - To **view comments** they have posted
  - To **post a new comment** related to a store
- **Registered craftsmen**
  - To **view comments** posted by users related to their stores
  - To **view and edit** their stores' **sampler** (add new products, edit and delete products from the sampler)

## Architecture overview
This is a **hybrid architecture** consisting of
- **Backend**
  - Written using **Spring Boot**
  - Based on the following **microservices**
    - _Craftsman_, handles information on craftsmen
    - _Sampler_, handles the list of products in the samplers, allowing new products to be added, existing products to be edited and deleted
    - _Craftstore_, allows to search for stores and view information about them such as contact information, comments received, and the list with the most significant products sold (obtained by **directly invoking** the _Sampler_ microservice)
    - _Comment_, handles comments posted by users
    - _User_, handles information on users
  - Uses **relational databases** (MariaDB) to store data
  - Uses a **Eureka Service Registry** where microservices may register
  - Uses an **Api-gateway**, a single access point to services
  - Uses a **RabbitMQ** Message broker which allows microservices to exchange messages asynchronously. When a user posts a comment, the _Comment_ microservice **publishes** the event, to which the _Craftstore_ and _User_ microservices have **subscribed**
- **Frontend**
  - A responsive web application written using **Next.js** (React framework)
  - Uses the **Keycloak** provider for authentication

## Run the app with docker-compose
First install [Docker](https://docs.docker.com/get-docker/) on your machine.

1.To build and run the **entire app** (frontend and backend), with images pulled from Docker Hub, in detach mode (background), in root directory run
```shell    
docker-compose up -d
```   
  The frontend will be running at [http://localhost:3000](http://localhost:3000)

  To stop and remove containers, in root directory run
```shell
docker-compose down
```

  You can also **remove images** by adding the `--rmi` option, **remove all volumes** by adding the `--volumes` option

  Beware that if you remove the volumes **all saved data will be lost**

  For example, if you want to use both options, in root directory run
```shell
docker-compose down --rmi --volumes
```

2.To build and run inside containers **only the backend**, with images pulled from Docker Hub, in detach mode (background), in root directory run
```shell
docker compose -f docker-compose-backend.yml up -d
```
  Then you can **run the frontend locally**, to know how to do follow the instructions in [frontend](frontend/README.md) folder

  To stop and remove backend containers, in root directory run
```shell
docker compose -f docker-compose-backend.yml down
``` 
  You can also **remove images** by adding the `--rmi` option, **remove all volumes** by adding the `--volumes` option
  
  Beware that if you remove the volumes **all saved data will be lost**
  
  For example, if you want to use both options, in root directory run
```shell  
docker compose -f docker-compose-backend.yml down --rmi --volumes
```
## Run the app with Kubernetes
To run Kubernetes you may install [minikube](https://minikube.sigs.k8s.io/docs/start/) or use the one included in [Docker Desktop](https://docs.docker.com/desktop/kubernetes/)

1.To create deployments, services, volumes and networks for the **entire app** (frontend and backend), in kubernetes directory run
```shell 
kubectl apply -f .
```
> **NOTE**: If you use **minikube**, to start the cluster, initially you have to run
> ```shell
> minikube start
> ```

You can **view the state** of deployments, pods and services by running the commands
```shell
kubectl get deployments
kubectl get pods
kubectl get services
```

To forward one local port to a pod port, and thus **expose a service**, run the command
```shell
kubectl port-forward svc/front-prod 3000:3000
```
For example, the above command listen on port 3000 locally, forwarding to 3000 in a pod of frontend service, so the frontend will be running at [http://localhost:3000](http://localhost:3000)

At the end **remember to delete** all deployments and services by running the commands
```shell
kubectl delete --all svc
kubectl delete --all deployment.apps
```
Alternatively you can **delete everything** that was created with the `kubectl apply` command by running in frontend directory
```shell
kubectl delete -f .
```
Beware that this will also delete the pvc (persistent volume claims) and thus **all saved data will be lost**

2.To run with Kubernetes **only the backend**, in kubernetes directory you have to run the `kubectl apply` command **only with the backend .yaml files**
```shell 
kubectl apply -f <filename.yaml>
```
Everything else is the same as described in the previous point.

Then you can **run the frontend locally**, to know how to do follow the instructions in [frontend](frontend/README.md) folder

## Testing the app

While running the app if you want to test it you need to **perform some operations**

### Inserting craftstores
Currently, the frontend allows new users to be added but does not allow new craftstores to be added, so **you have to do it manually** via the backend API, to do this follow the instructions in [craftstores-inserting](craftstores-inserting.md) markdown file

### Creating a realm
Currently, the **keycloak realm** used to manage registered persons (craftsmen or users) **must be created manually** (it is not done automatically), to do this follow the instructions in [realm-creating](frontend/realm-creating.md) markdown file in the frontend folder

## WARNING
>In the frontend, for registration and login to work an extra-host named **host.docker.internal** was used.
<br>In the docker-compose file, the command to add this host and put it equal to host-gateway was inserted. As a consequence in your machine's **hosts file**, which contains the mapping between hosts names and their respective IP addresses, **a new entry will be added transparently**.
<br>Thus **it is best to run the app first with docker-compose**, so the host is added, and then if you want to run it also with kubernetes.
<br>If, despite this, the login doesn't work, or you don't want the hosts file on your machine to be modified, **you can run the frontend locally**, to know how to do follow the instructions in [frontend](frontend/README.md) folder