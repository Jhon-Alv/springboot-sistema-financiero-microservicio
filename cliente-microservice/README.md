# 🧾 cliente-microservice

Microservicio encargado de gestionar la información de los clientes del sistema financiero. Expone un endpoint para consultar datos personales de los usuarios a partir de su `codigoUnico`.

-----

## 🏗️ Tecnologías

- **Java**: 17
- **Spring Boot**: 3.5.3
- **Spring Framework**: WebFlux, Security + OAuth2, Data MongoDB Reactive
- **Documentación**: Swagger OpenAPI
- **Logging**: Logback + MDC
- **Contenerización**: Docker

-----

## ⚙️ Configuración (`application.yml`)

A continuación se muestra la configuración principal del servicio:

```yaml
server:
  port: 8081

spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/reto-clientedb

```

-----

## 🧩 Base de Datos

- **Motor**: MongoDB
- **Base de Datos**: `reto-clientedb`
- **Colección**: `clientes`

### Ejemplo de Documento

```json
{
  "_id": "687291ad3e3dc4e3f971ea57",
  "codigoUnico": "ABC123456",
  "nombres": "Jhon",
  "apellidos": "Alvarado",
  "tipoDocumento": "DNI",
  "numeroDocumento": "12345678"
}
```

-----

## 🔗 Tracking: Correlation ID

Este microservicio propaga el header `X-Correlation-Id` si lo recibe, o genera uno nuevo si no está presente. Esto asegura la trazabilidad completa de las peticiones a través de los logs, gracias a la configuración de **Logback + MDC**.

-----

## 📄 Endpoints

### GET `/api/clientes/{codigoUnico}`

**Descripción**: Retorna los datos del cliente según su código único.

**Parámetros de URL**:

- `codigoUnico` (string, **requerido**): Código que identifica de forma única al cliente.

**Respuesta Exitosa (200 OK)**:

```json
{
  "codigoUnico": "ABC123456",
  "nombres": "Jhon",
  "apellidos": "Alvarado",
  "tipoDocumento": "DNI",
  "numeroDocumento": "12345678"
}
```

## 📄 Swagger UI

La documentación interactiva de la API está disponible a través de Swagger UI en la siguiente ruta una vez que el servicio esté en ejecución:

📍 **http://localhost:8081/swagger-ui.html**

-----

## 🐳 Docker

### Dockerfile

El microservicio puede ser contenedorizado usando el siguiente `Dockerfile`:

```dockerfile
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/cliente-microservice-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Docker Compose

Para orquestar este servicio junto con sus dependencias (como la base de datos), se puede definir en un archivo `docker-compose.yml` de la siguiente manera:

```yaml
services:
  cliente-service:
    build:
      context: ./cliente-microservice
    ports:
      - "8081:8081"
    depends_on:
      - mongo
```

-----

## 🧪 Pruebas

Para probar el endpoint de forma manual, puedes usar `curl` con un token de autorización válido:

```bash
curl -H "Authorization: Bearer <TU_TOKEN_JWT>" \
     http://localhost:8081/api/clientes/ABC123456
```

-----

## 👨‍💻 Autor

- **Jhon Alvarado**