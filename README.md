# 🧩 Sistema Financiero - Arquitectura de Microservicios

Este proyecto es una solución basada en microservicios para la gestión de clientes y productos financieros. Incluye:

- **BFF (Backend for Frontend)** que centraliza el acceso a la información combinada.
- **Microservicio de Clientes**
- **Microservicio de Productos**
- **MongoDB como base de datos**
- **OAuth2 con Auth0 para seguridad**
- **Traza de solicitudes usando `X-Correlation-Id`**
- **Swagger UI para documentación**
- **Docker Compose para orquestación**

-----

## 📦 Estructura del Proyecto

```
reto-backend/
├── bff/
├── cliente-microservice/
├── producto-microservice/
├── docker-compose.yml
```

-----

## 🚀 Levantar el Proyecto

1.  **Clonar el repositorio:**

    ```bash
    git clone https://github.com/tu-usuario/reto-backend.git
    cd reto-backend
    ```

2.  **Levantar todos los servicios con Docker Compose:**

    ```bash
    docker compose up --build
    ```

    Esto levantará:

    * MongoDB
    * BFF (puerto `8080`)
    * Microservicio Clientes (`8081`)
    * Microservicio Productos (`8082`)

-----

## 🔐 Seguridad

Se utiliza **Auth0** como servidor de autorización para proteger los endpoints usando **JWT**.

* Todos los endpoints requieren un token válido excepto la documentación Swagger.
* Configurado mediante `application.yml` y `SecurityConfig`.

-----

## 📄 Documentación Swagger

| Servicio | URL Swagger |
| :--- | :--- |
| BFF | [http://localhost:8080/swagger-ui.html](https://www.google.com/search?q=http://localhost:8080/swagger-ui.html) |
| Clientes | [http://localhost:8081/webjars/swagger-ui/index.html](https://www.google.com/search?q=http://localhost:8081/webjars/swagger-ui/index.html) |
| Productos | [http://localhost:8082/webjars/swagger-ui/index.html](https://www.google.com/search?q=http://localhost:8082/webjars/swagger-ui/index.html) |

-----

## 🔗 Comunicación y Tracking

Se implementa propagación del header `X-Correlation-Id` desde el **BFF** a cada microservicio para trazabilidad completa de las solicitudes entre servicios.

> Los logs muestran esta correlación de forma automática usando `logback-spring.xml`.

-----

## 🛠️ Tecnologías utilizadas

* **Java 17**
* **Spring Boot 3.5.3**
* **Spring WebFlux**
* **Spring Security + OAuth2**
* **MongoDB Reactive**
* **Docker & Docker Compose**
* **Lombok**
* **Swagger (SpringDoc OpenAPI)**
* **MDC + Logback para trazabilidad**
* **JWT**

-----

## 📌 Datos de ejemplo

La base de datos se inicializa con:

### Base de datos `reto-clientedb`:

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

### Base de datos `reto-productosdb`:

```json
[
  {
    "_id": "687529a39226d4c43aa7377b",
    "codigoUnico": "ABC123456",
    "tipoProducto": "Cuenta de Ahorros",
    "nombreProducto": "Cuenta Joven",
    "saldo": 1500.5
  },
  {
    "_id": "687529b29226d4c43aa7377d",
    "codigoUnico": "ABC123456",
    "tipoProducto": "Tarjeta de Crédito",
    "nombreProducto": "Visa Clásica",
    "saldo": 2300
  }
]
```

-----

## 🤝 Autor

**Jhon Alvarado**

-----