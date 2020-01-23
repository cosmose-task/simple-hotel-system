## Running the application locally

```shell
mvn spring-boot:run
```

## Running the tests

The easiest way to deploy the sample application to OpenShift is to use the [OpenShift CLI](https://docs.openshift.org/latest/cli_reference/index.html):

```shell
mvn test
```

## Manually tests

```shell
hotel-cosmose.postman_collection.json
```
Inside this file has been created Postman requests for simplify manually tests.
## Deploying the application on docker 

```shell
docker build -t hotel-cosmose .
```
```shell
docker run -d -it -p 80:8080 --name=hotel-cosmose hotel-cosmose 
```
