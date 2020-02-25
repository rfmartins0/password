Objetivo

Validar se uma senha é correta

Construção

O app foi construído na linguagem Java e uso diversos frameworks e tecnologias, tais como: Swagger, Spring Boot 2 e Spring Actuator e Gradle

Buid

A aplicação pode ser compilada usando o seguinte comando:
"gradle build"

Docker

Caso haja interesse é possível rodar a aplicação por meio de um container docker, já que dentro do projeto há um DockerFile. Seguem os comandos:

"gradle build"
"docker build -t project ."
"docker network create mynewbridge"
"docker run --net mynewbridge -d -p 8080:8080 {id}"

Para saber o id da imagem, basta digitar "docker images". Note que é necessário ter dado o "docker build".

Em seguida o projeto estará disponível no endereço:

http://localhost:8080/swagger-ui.html (Swagger do projeto)
http://localhost:8080/v1/api/password (Endpoint)
http://localhost:8080/actuator/health (Status do Spring Actuator)


Heroku 

O projeto está online, disponível para uso nos seguintes endereços:


https://app-password.herokuapp.com/v1/api/password (Endpoint)
https://app-password.herokuapp.com/swagger-ui.html (Swagger do projeto)
https://app-password.herokuapp.com/actuator/health (Status do Spring Actuator)



