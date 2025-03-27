# User Service

## Descripción

User Service es un microservicio encargado de la gestión de usuarios dentro del ecosistema de la aplicación. Implementa una arquitectura hexagonal para separar la lógica de negocio de la infraestructura, asegurando flexibilidad y mantenibilidad.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot**
- **H2 Database**
- **JWT para autenticación**
- **Arquitectura Hexagonal**
- **Swagger para documentación de APIs**

## Estructura del Proyecto

```
main/
├── java
│   └── com
│       └── devsoft
│           └── user_service
│               ├── DemoApplication.java
│               ├── domain
│               │   ├── entities
│               │   ├── exceptions
│               │   ├── repositories
│               │   ├── services
│               │   └── value_objects
│               ├── infrastructure
│               │   ├── config
│               │   ├── database
│               │   ├── exception
│               │   ├── jwt
│               │   └── rest
│               ├── use_cases
│               └── package-info.java
└── resources
    ├── application.properties
    ├── application-secrets.properties
    ├── banner.txt
```

### Descripción de Módulos

- **Domain**: Contiene entidades, excepciones, puertos de repositorios y servicios.
- **Infrastructure**: Implementaciones de repositorios, configuraciones y adaptadores.
- **Use Cases**: Casos de uso que encapsulan la lógica de negocio.
- **Rest**: Controladores y DTOs para la comunicación con el exterior.

## Endpoints Principales

| Método | Endpoint           | Descripción            |
| ------ | ------------------ | ---------------------- |
| POST   | /usuarios/login    | Iniciar sesión         |
| POST   | /usuarios/register | Registrar un usuario   |
| DELETE | /usuarios/{id}     | Eliminar un usuario    |
| PUT    | /usuarios/{id}     | Actualizar información |

## Configuración

Para ejecutar el servicio, asegúrate de configurar las propiedades en `application.properties` y `application-secrets.properties`. Puedes iniciar la aplicación con:

```bash
mvn spring-boot:run
```

## Licencia

Este proyecto está bajo la licencia MIT.
