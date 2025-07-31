Claro, aquí tienes el README basado en la información que proporcionaste.

-----

# 🧩 BFF - Backend for Frontend

Este servicio actúa como un **punto único de entrada** para las aplicaciones frontend, centralizando las llamadas hacia los microservicios de clientes y productos. Su función principal es desencriptar el `codigoUnico` recibido, realizar el tracking de la solicitud mediante un `X-Correlation-Id` y devolver un único objeto combinado al cliente final.

## 🏗️ Tecnologías

El proyecto está construido con las siguientes tecnologías:

* **Java 17**
* **Spring Boot 3.5.3**
* Spring WebFlux
* Spring Security + OAuth2 (Auth0)
* Swagger (springdoc-openapi)
* Reactor Context
* MDC + Logback para trazabilidad
* Docker

-----

## ⚙️ Configuración

La configuración principal del servicio se encuentra en `application.yml`:

```yaml
server:
  port: 8080

servicio:
  cliente:
    url: "http://localhost:8081"
  producto:
    url: "http://localhost:8082"

springdoc:
  swagger-ui:
    path: /swagger-ui.html
    enabled: true
  api-docs:
    path: /v3/api-docs


spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: https://dev-jhon-alv.us.auth0.com/
      client:
        registration:
          auth0:
            client-id: DnkqVM9NHiMB0iNb8VExIxvU3dXQQ1GP
            client-secret: jwYpIQH6gstmu0dF4_sSmZx3njNb2hpbqVp6xY4Fw_16SsiYSXiz7SINBRLnV-9D
            scope:
              - openid
              - profile
              - email
            provider: auth0
        provider:
          auth0:
            issuer-uri: https://dev-jhon-alv.us.auth0.com/
            authorization-uri: https://dev-jhon-alv.us.auth0.com/authorize
            token-uri: https://dev-jhon-alv.us.auth0.com/oauth/token
```
-----

## 🔐 Obtención del Token JWT

Para consumir los endpoints del BFF, necesitas un token JWT válido emitido por **Auth0**. Puedes obtenerlo de dos formas:

### 1. Usando Postman o cURL (Client Credentials Flow)
```bash
curl --request POST \
  --url 'https://dev-jhon-alv.us.auth0.com/oauth/token' \
  --header 'content-type: application/json' \
  --data '{
    "client_id": "DnkqVM9NHiMB0iNb8VExIxvU3dXQQ1GP",
    "client_secret": "jwYpIQH6gstmu0dF4_sSmZx3njNb2hpbqVp6xY4Fw_16SsiYSXiz7SINBRLnV-9D",
    "audience": "https://api.bff.example.com",  # Ajustar según tu API Audience en Auth0
    "grant_type": "client_credentials"
  }'
```
-----

## 🔐 Seguridad

Este servicio está protegido y requiere un **token JWT válido** emitido por Auth0 para su consumo. Todos los endpoints están asegurados, a excepción de la documentación de Swagger.

-----

## 🔗 Tracking con Correlation ID

Se incluye un filtro `CorrelationIdFilter` que genera (o propaga si ya existe) un header `X-Correlation-Id`. Este identificador se inyecta en cada llamada `WebClient` hacia los microservicios internos para garantizar la trazabilidad de las operaciones.

En los logs, configurados con `logback-spring.xml`, verás el ID de correlación en cada traza:

```log
2025-07-15 10:10:00.959 [] INFO  c.r.bff.filter.CorrelationIdFilter - Request received with Correlation ID: <CorrelationID>
```

-----

## 📦 Endpoint Principal

### `GET /api/bff/clientes/{codigoUnicoEncriptado}`

**Descripción:**

Devuelve la información consolidada de un cliente junto con sus productos financieros asociados.

**Parámetro de URL:**

* `codigoUnicoEncriptado` (string, required): El código único del cliente, encriptado con el algoritmo AES.

**Respuesta Exitosa (200 OK):**

```json
{
  "codigoUnico": "ABC123456",
  "nombres": "Jhon",
  "apellidos": "Alvarado",
  "tipoDocumento": "DNI",
  "numeroDocumento": "12345678",
  "productos": [
    {
      "tipoProducto": "Cuenta de Ahorros",
      "nombreProducto": "Cuenta Joven",
      "saldo": 1500.5
    },
    {
      "tipoProducto": "Tarjeta de Crédito",
      "nombreProducto": "Visa Clásica",
      "saldo": 2300
    }
  ]
}
```
-----
## 🔐 **Endpoints de Prueba (Encriptación/Desencriptación)**

Estos endpoints están diseñados para **fines de desarrollo y validación**, permitiendo probar el algoritmo AES utilizado para encriptar/desencriptar el `codigoUnico` de clientes.  
*(No exponer en producción)*.

### **`GET /api/test/encrypt/{text}`**
Encripta un texto plano usando AES (el mismo algoritmo que se usa para el `codigoUnico`).

**Ejemplo:**
```bash
curl -H "Authorization: Bearer <TOKEN>" \
     http://localhost:8080/api/test/encrypt/ABC123456
```

**Respuesta (200 OK) Texto encriptado:**
```json
"U2FsdGVkX1+3f4Zq7W4Kb5J7Vg2XzYt0"  
```

---

### **`GET /api/test/decrypt/{cipher}`**
Desencripta un texto previamente encriptado con AES.

**Ejemplo:**
```bash
curl -H "Authorization: Bearer <TOKEN>" \
     http://localhost:8080/api/test/decrypt/U2FsdGVkX1+3f4Zq7W4Kb5J7Vg2XzYt0
```

**Respuesta (200 OK) Texto original desencriptado:**
```json
"ABC123456" 
```

-----

## 📄 Swagger UI

La documentación interactiva de la API está disponible en la siguiente URL una vez que el servicio está en ejecución:

📍 **[http://localhost:8080/swagger-ui.html](https://www.google.com/search?q=http://localhost:8080/swagger-ui.html)**

-----

## 🐳 Docker

El servicio está preparado para ser contenedorizado.

**Dockerfile:**

```dockerfile
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/bff-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**Docker Compose (`docker-compose.yml`):**

Este servicio se levanta con el nombre `bff` y expone el puerto `8080`.

-----

## 🧪 Pruebas

Para probar el endpoint principal, puedes usar `curl` con un token de autorización válido y un código de cliente encriptado:

```bash
curl -H "Authorization: Bearer <TOKEN>" \
     http://localhost:8080/api/bff/clientes/<CODIGO_ENCRIPTADO>
```

-----

## 👨‍💻 Autor

**Jhon Alvarado**