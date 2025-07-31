# 🧾 producto-microservice

Este microservicio gestiona los productos financieros de los clientes. Su función principal es permitir la consulta de todos los productos asociados a un cliente específico a través de su `codigoUnico`.

-----

## Tecnologías 🏗️

* **Java 17**: Versión del JDK utilizada.
* **Spring Boot 3.5.3**: Framework principal para el desarrollo de la aplicación.
* **Spring WebFlux**: Módulo para la programación reactiva.
* **Spring Data MongoDB Reactive**: Para la interacción reactiva con la base de datos MongoDB.
* **Swagger OpenAPI**: Para la documentación de la API.
* **Logback + MDC**: Para el manejo de logs y el seguimiento de peticiones.
* **Docker**: Para la contenerización de la aplicación.

-----

## Configuración ⚙️

El archivo de configuración principal es `application.yml`.

```yaml
server:
  port: 8082

spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/reto-productosdb
```

-----

## Base de Datos 🧩

* **Motor**: MongoDB
* **Base de Datos**: `reto-productosdb`
* **Colección**: `productos`

### Ejemplos de Documentos

```json
{
  "_id": "687529a39226d4c43aa7377b",
  "codigoUnico": "ABC123456",
  "tipoProducto": "Cuenta de Ahorros",
  "nombreProducto": "Cuenta Joven",
  "saldo": 1500.5
}
```

```json
{
  "_id": "687529b29226d4c43aa7377d",
  "codigoUnico": "ABC123456",
  "tipoProducto": "Tarjeta de Crédito",
  "nombreProducto": "Visa Clásica",
  "saldo": 2300
}
```

-----

## Tracking: Correlation ID 🔗

El microservicio utiliza un **Correlation ID** para facilitar el seguimiento de las solicitudes. Si una petición entrante incluye la cabecera `X-Correlation-Id`, su valor se propagará en todos los logs. Si no se encuentra, se generará un nuevo ID único. Esto es clave para el rastreo distribuido en un ecosistema de microservicios.

-----

## Endpoints 📄

### GET `/api/productos/{codigoUnico}`

* **Descripción**: Devuelve una lista de todos los productos financieros asociados al `codigoUnico` del cliente.
* **Respuesta de ejemplo** (`200 OK`):

<!-- end list -->

```json
[
  {
    "codigoUnico": "ABC123456",
    "tipoProducto": "Cuenta de Ahorros",
    "nombreProducto": "Cuenta Joven",
    "saldo": 1500.5
  },
  {
    "codigoUnico": "ABC123456",
    "tipoProducto": "Tarjeta de Crédito",
    "nombreProducto": "Visa Clásica",
    "saldo": 2300
  }
]
```


## Swagger UI 📄

La documentación interactiva de la API está disponible a través de Swagger UI en la siguiente ruta:

📍 **[http://localhost:8082/swagger-ui.html](https://www.google.com/search?q=http://localhost:8082/swagger-ui.html)**

-----

## Docker 🐳

Puedes construir y ejecutar este microservicio usando Docker.

### Dockerfile

```dockerfile
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/producto-microservice-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Docker Compose

Para orquestar el servicio junto con sus dependencias (como MongoDB), puedes usar un archivo `docker-compose.yml`:

```yaml
  producto-service:
    build:
      context: ./producto-microservice
    ports:
      - "8082:8082"
    depends_on:
      - mongo
```

-----

## Pruebas 🧪

Para probar el endpoint, puedes usar `curl` o cualquier cliente API, asegurándote de incluir tu token de autenticación.

```bash
curl -H "Authorization: Bearer <TOKEN>" \
     http://localhost:8082/api/productos/ABC123456
```

-----

## Autor 👨‍💻

**Jhon Alvarado**