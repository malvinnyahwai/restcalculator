# CALCULATOR REST API

CALCULATOR REST API is a REST web service that provides the basic functionalities of a calculator (sum, subtraction, multiplication and division). It uses RabbitMQ and Spring AMQP for communication intermodule.

## Start environment

### Rabbitmq docker container
```
docker-compose up
```

### Build project
```
mvn clean install
```

### calculatorws-calculator
```
java -jar rest-controller/target/restcontroller-0.0.1-SNAPSHOT
```

## API's
---

### Addition (sum)
```
curl -i "http://localhost:8080/sum?a={num1}&b={num2}"
```
### Example
```
curl -i "http://localhost:8080/sum?a=10&b=5"
...
HTTP/1.1 200 OK Content-Type: application/json
{"result":15}
```

### Subtraction (subtract)
```
curl -i "http://localhost:8080/sub?a={num1}&b={num2}"
```
### Example
```
curl -i "http://localhost:8080/sub?a=10&b=5"
...
HTTP/1.1 200 OK Content-Type: application/json
{"result":5}
```

### Multiplication (multiply)
```
curl -i "http://localhost:8080/multiply?a={num1}&b={num2}"
```
### Example
```
curl -i "http://localhost:8080/sub?a=10&b=5"
...
HTTP/1.1 200 OK Content-Type: application/json
{"result":150}
```

### Division (divide)
```
curl -i "http://localhost:8080/divide?a={num1}&b={num2}"
```
### Example
```
curl -i "http://localhost:8080/sub?a=10&b=5"
...
HTTP/1.1 200 OK Content-Type: application/json
{"result":2}
```
