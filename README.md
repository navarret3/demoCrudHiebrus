# Demo Inditex

_DEMO de un Web Service corriendo un CRUD montado en REST_

## Primeros pasos y requisitos previos 📋

_Para este proyecto necesitaremos:_

- Java 8
- Git
- Maven
- Postman (u otro software para hacer peticiones).


### Instalación 🔧

_Únicamente habrá que descargarse el proyecto desde github_

```
git clone https://github.com/navarret3/demoCrudHiebrus.git
```

Ya que no hacemos uso de bases de datos externas y se ha intentado hacer la demo de la forma más simple posible, únicamente habrá que arrancar la aplicación desde la clase principal (DemoApplication.java).


_Una vez tengamos nuestra aplicación corriendo y funcionando, simplemente habría que llamar a los diferentes endpoints con un programa como Postman:_

```
GET
http://localhost:8080/api/products/1
```

## Tests ⚙️

_Para esta aplicación se han hecho 2 tests con Junit y mockito, uno para cubrir el controlador y otro para el service._


## Despliegue 📦

_Como se ha comentado arriba, únicamente necesitaremos correr la aplicación desde la clase DemoApplication ya que está usando un servidor de tomcat embebido._

## Construido con 🛠️

* [SpringBoot](https://spring.io/projects/spring-boot)
* [Maven](https://maven.apache.org/)
* [Java 8](https://www.java.com/es/download/help/java8_es.html)
* [Swagger](https://swagger.io/)

## Autores ✒️

* **Javier Navarrete** - *Demo REST* - [navarret3](https://github.com/navarret3)
