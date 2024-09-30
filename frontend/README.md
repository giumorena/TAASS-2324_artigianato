# Frontend 'Il bello dell'artigianato'

## Description
This is a responsive web application written using **Next.js** (React framework)
with the starter template for the Next.js App Router Course.

This app uses the **Keycloak** provider for authentication.

## Run frontend inside container
To run the frontend inside containers **also with the backend** follow the instructions in [root](../README.md) folder

## Run frontend locally
First install [Node.js](https://nodejs.org/en)

To install the project's packages, in frontend directory run
```shell
npm install
```
If you want to run the frontend **in development mode**, in frontend directory run
```shell
npm run dev
```
If you want to run the frontend **in production mode**, in frontend directory run
```shell
npm run build
npm run start
```

In order to use the **Keycloak provider** for authentication you may build and run it inside a container, to do this in detach mode (background) in frontend directory run
```shell
docker compose -f docker-compose-keycloack.yml up -d
```
To stop and remove the container, in frontend directory run
```shell
docker compose -f docker-compose-keycloack.yml down
```
You also need to **run the backend** inside containers, to know how to do follow the instructions in [root](../README.md) folder




