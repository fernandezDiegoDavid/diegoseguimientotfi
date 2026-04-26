# Seguimiento TFI - Java Spring MVC + REST

Proyecto personal basado en el bootstrap académico de [gussiciliano/ghsicilianotfi](https://github.com/gussiciliano/ghsicilianotfi), orientado a consolidar buenas prácticas en Spring Boot, arquitectura MVC + API REST, seguridad y evolución incremental del dominio.

Este repositorio no es un "fork espejo": es una implementación propia sobre la que voy a seguir iterando durante el TFI.

## Proyecto Guía y Referencias

- Proyecto base del profesor: [gussiciliano/ghsicilianotfi](https://github.com/gussiciliano/ghsicilianotfi)
- README del proyecto guía (branch master): [README original](https://raw.githubusercontent.com/gussiciliano/ghsicilianotfi/master/README.md)
- Documentación histórica compartida en el repo guía:
- [Versión 2023 - Spring Boot 3.2.4](https://drive.google.com/file/d/1kaVnsLC_FRtTR2C7gyxHSwlyvZD6rtN6/view?usp=drive_link)
- [Versión 2023 - Spring Boot 3.0.4 (Java 17)](https://drive.google.com/file/d/1JF2yve489njnFPkzLGQZDox8KAx8Pobl/view)
- [Versión 2022 - Spring Boot 2.6.4 + Lombok + ModelMapper](https://drive.google.com/file/d/1D_KgYmJ1DpbzrqO6-M5NgyqlLHkIayoS/view)

## Propuesta de Valor de Esta Versión

Sobre el enfoque base de aprendizaje, esta versión personal busca aportar:

1. Integración híbrida MVC + REST en una sola app para comparar y validar ambos estilos.
2. Seguridad con Spring Security (login personalizado, BCrypt, autorización por rol).
3. Dominio académico consistente (Person, Degree, User, UserRole) con persistencia JPA.
4. Mapeo explícito Entidad/DTO con ModelMapper para desacoplar capa web y capa de datos.
5. Base preparada para documentación de API y evolución continua del proyecto.

## Stack Tecnológico

- Java 17
- Spring Boot 3.3.5
- Spring MVC + Thymeleaf
- Spring Data JPA
- Spring Security
- MySQL
- ModelMapper
- Lombok
- Maven Wrapper

## Arquitectura (Resumen)

- Capa web MVC: controladores para vistas Thymeleaf.
- Capa API REST: endpoints bajo /api/v1/.
- Capa de servicio: reglas de negocio y mapeos.
- Capa repositorio: acceso a datos con Spring Data JPA.
- Capa seguridad: configuración central en SecurityConfiguration.

## Funcionalidades Implementadas

### 1) Módulo MVC

- Gestión de personas:
- Listado, alta, edición, baja.
- Búsqueda por nombre y por título (degree).
- Vista parcial para renderizado específico.

- Gestión de títulos (degree):
- Listado y alta.
- Control de acceso por rol administrador.

- Home y navegación:
- Página principal con usuario autenticado.
- Ejemplos de parámetros por query y path variable.

### 2) API REST

- Personas:
- GET /api/v1/persons
- GET /api/v1/persons/{id}
- GET /api/v1/persons/name?name=...
- POST /api/v1/persons
- PUT /api/v1/persons
- DELETE /api/v1/persons/{id}
- GET /api/v1/persons/degrees?degree=...

- Degrees:
- GET /api/v1/degree/all

### 3) Seguridad

- Login personalizado en /login.
- Proceso de autenticación en /loginprocess.
- Logout en /logout.
- Password hashing con BCrypt.
- Restricción por rol en controlador de degrees (ROLE_ADMIN).

## Cómo Ejecutar el Proyecto

## Requisitos

- JDK 17
- MySQL en ejecución
- Git (opcional)

## 1. Clonar repositorio

```bash
git clone <TU_URL_DEL_REPO>
cd diegoseguimientotfi
```

## 2. Configurar variables de entorno

El proyecto utiliza variables en application.yml para la conexión:

- DB_URL
- USERNAME
- PASSWORD

Ejemplo en PowerShell:

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/tfi_db?useSSL=false&serverTimezone=UTC"
$env:USERNAME="root"
$env:PASSWORD="tu_password"
```

## 3. Levantar la aplicación

```bash
./mvnw.cmd spring-boot:run
```

Luego abrir:

- http://localhost:8080/login

## 4. Ejecutar tests

```bash
./mvnw.cmd test
```

Nota: el test de contexto depende de la configuración de datasource. Si DB_URL no empieza por jdbc:, el test falla al inicializar Spring.

## Estado Actual y Mejoras Planeadas

### Estado actual

- Aplicación funcional con módulos MVC + REST.
- Seguridad integrada y control básico por roles.
- Modelo de dominio persistente en MySQL.

### Próximas mejoras (roadmap)

1. Completar CRUD REST de Degree con datos reales (hoy /api/v1/degree/all devuelve datos de ejemplo).
2. Habilitar explícitamente rutas de OpenAPI/Swagger en seguridad para documentación navegable.
3. Agregar inicialización reproducible de usuarios/roles (seed SQL o migraciones).
4. Incorporar tests de integración para controladores y capa de servicios.
5. Auditar y depurar assets frontend no utilizados para reducir deuda técnica visual y de mantenimiento.

## Transparencia Académica

Este repositorio toma como referencia pedagógica el trabajo de cátedra y del profesor, citado arriba. La implementación, organización de código, documentación y evolución funcional de esta versión corresponden a mi proyecto personal de seguimiento del TFI.
