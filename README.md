# 🛒 Microservicio de Gestión de Productos

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.8-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-4.0.0-blue.svg)](https://maven.apache.org/)
[![MySQL](https://img.shields.io/badge/MySQL-Connector-blue.svg)](https://dev.mysql.com/downloads/connector/j/)

## 📋 Descripción

Microservicio REST desarrollado en Spring Boot para la gestión completa de productos, inventarios y proveedores. Forma parte del ecosistema **PowerFit** y proporciona funcionalidades de CRUD con manejo avanzado de excepciones y documentación automática de API.

## ✨ Características

- 🛍️ **Gestión de productos** (CRUD completo)
- 📦 **Control de inventario** y stock
- 🏭 **Gestión de proveedores**
- 🛡️ **Manejo robusto de excepciones**
- 📚 **Documentación automática con Swagger/OpenAPI**
- 🔄 **Arquitectura RESTful**
- ✅ **Validación de datos con Bean Validation**
- 🧪 **Cobertura de pruebas unitarias e integración**
- 🌱 **Configuración con variables de entorno**

## 🏗️ Arquitectura

El proyecto sigue una arquitectura en capas:

```
├── 🎮 Controller     # Capa de presentación (API REST)
├── 🔧 Service        # Lógica de negocio
├── 📊 Repository     # Acceso a datos
├── 🏷️ Model          # Entidades JPA
├── 📋 DTO            # Objetos de transferencia de datos
└── ⚠️ Exception      # Manejo de excepciones personalizadas
```

## 🛠️ Tecnologías

- **Framework**: Spring Boot 3.5.8
- **Lenguaje**: Java 21 LTS
- **Base de Datos**: MySQL 8.0
- **ORM**: Spring Data JPA / Hibernate
- **Documentación**: SpringDoc OpenAPI (Swagger)
- **Gestión de Dependencias**: Maven
- **Validación**: Jakarta Bean Validation
- **Testing**: JUnit, Spring Boot Test
- **Otras**: Lombok, Spring DevTools, Spring Actuator

## 📁 Estructura del Proyecto

```
src/
├── main/
│   ├── java/com/skarx/producto/
│   │   ├── ProductoApplication.java         # Clase principal
│   │   ├── config/
│   │   │   └── SwaggerConfig.java           # Configuración de Swagger
│   │   ├── controller/
│   │   │   ├── ProductoController.java      # Controlador REST productos
│   │   │   ├── InventarioController.java    # Controlador REST inventario
│   │   │   └── ProveedorController.java     # Controlador REST proveedores
│   │   ├── dto/
│   │   │   ├── ProductoDto.java             # DTO para productos
│   │   │   ├── InventarioDto.java           # DTO para inventario
│   │   │   └── ProveedorDto.java            # DTO para proveedores
│   │   ├── exception/
│   │   │   ├── ProductoNoEncontradoException.java
│   │   │   ├── StockInsuficienteException.java
│   │   │   └── ProveedorNoEncontradoException.java
│   │   ├── handler/
│   │   │   └── GlobalExceptionHandler.java  # Manejo global de excepciones
│   │   ├── model/
│   │   │   ├── Producto.java                # Entidad Producto
│   │   │   ├── Inventario.java              # Entidad Inventario
│   │   │   └── Proveedor.java               # Entidad Proveedor
│   │   ├── repository/
│   │   │   ├── ProductoRepository.java      # Repositorio JPA productos
│   │   │   ├── InventarioRepository.java    # Repositorio JPA inventario
│   │   │   └── ProveedorRepository.java     # Repositorio JPA proveedores
│   │   └── service/
│   │       ├── ProductoService.java         # Interfaz del servicio
│   │       └── ProductoServiceImpl.java     # Implementación del servicio
│   └── resources/
│       ├── application.properties           # Configuración principal
│       ├── application-docker.properties    # Configuración Docker
│       └── data.sql                         # Datos iniciales (opcional)
└── test/
    └── java/com/skarx/producto/
        ├── ProductoApplicationTests.java
        ├── controller/
        │   └── ProductoControllerTest.java
        └── service/
            └── ProductoServiceTest.java
```

## 🚀 Inicio Rápido

### Prerrequisitos

- ☕ Java 21 o superior (recomendado: Eclipse Temurin)
- 🗃️ MySQL 8.0 o superior (local o AWS RDS)
- 📦 Maven 3.9 o superior

### Configuración

1. **Clona el repositorio**

   ```bash
   git clone <url-del-repositorio>
   cd producto
   ```

2. **Configura las variables de entorno**

   Crea un archivo `.env` en la raíz del proyecto:

   ```env
   # Configuración de la base de datos (AWS RDS o local)
   SPRING_DATASOURCE_URL=jdbc:mysql://database-1.c4efjw97jtlo.us-east-1.rds.amazonaws.com:3306/powerfit_producto
   SPRING_DATASOURCE_USERNAME=admin
   SPRING_DATASOURCE_PASSWORD=tu_password_aqui
   SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.cj.jdbc.Driver

   # Configuración de JPA/Hibernate
   SPRING_JPA_HIBERNATE_DDL_AUTO=update
   SPRING_JPA_SHOW_SQL=true
   SPRING_JPA_PROPERTIES_HIBERNATE_FORMAT_SQL=true

   # Puerto del servidor
   SERVER_PORT=8081
   ```

   **Para MySQL local (Laragon/XAMPP)**:

   ```env
   SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/powerfit_producto
   SPRING_DATASOURCE_USERNAME=root
   SPRING_DATASOURCE_PASSWORD=
   SERVER_PORT=8081
   ```

3. **Crea la base de datos**

   ```sql
   CREATE DATABASE powerfit_producto;
   ```

   *(Hibernate creará las tablas automáticamente con `ddl-auto=update`)*

### 🏃 Ejecución Manual (Paso a Paso)

#### Opción 1: Con Maven Wrapper (Recomendado)

```powershell
# Windows PowerShell
cd producto
./mvnw clean install
./mvnw spring-boot:run
```

```bash
# Linux/Mac
cd producto
./mvnw clean install
./mvnw spring-boot:run
```

#### Opción 2: Con Maven Global

```powershell
cd producto
mvn clean install
mvn spring-boot:run
```

#### Opción 3: Ejecutar JAR directamente

```powershell
# 1. Compilar y empaquetar
./mvnw clean package -DskipTests

# 2. Ejecutar JAR
java -jar target/producto-0.0.1-SNAPSHOT.jar
```

#### Opción 4: Con perfil específico

```powershell
# Perfil de producción
$env:SPRING_PROFILES_ACTIVE='prod'; ./mvnw spring-boot:run

# Perfil Docker
$env:SPRING_PROFILES_ACTIVE='docker'; ./mvnw spring-boot:run
```

### ✅ Verificar que está corriendo

Una vez iniciado, verifica:

- **Health Check**: `http://localhost:8081/actuator/health`
- **Swagger UI**: `http://localhost:8081/swagger-ui/index.html`
- **API Docs JSON**: `http://localhost:8081/v3/api-docs`

**Respuesta esperada** de health:

```json
{
  "status": "UP"
}
```

### 🔄 Reiniciar/Detener

- **Ctrl + C** en la terminal para detener
- Ejecutar `./mvnw spring-boot:run` nuevamente para reiniciar

## 📖 Documentación de la API

Una vez que la aplicación esté ejecutándose, puedes acceder a la documentación interactiva de Swagger en:

- **Swagger UI**: `http://localhost:8081/swagger-ui/index.html`
- **OpenAPI JSON**: `http://localhost:8081/v3/api-docs`

### 🔍 Endpoints Principales

#### Productos

| Método   | Endpoint                        | Descripción                 |
| -------- | ------------------------------- | --------------------------- |
| `GET`    | `/api/v1/productos`             | Listar todos los productos  |
| `GET`    | `/api/v1/productos/{id}`        | Obtener producto por ID     |
| `POST`   | `/api/v1/productos`             | Crear nuevo producto        |
| `PUT`    | `/api/v1/productos/{id}`        | Actualizar producto         |
| `DELETE` | `/api/v1/productos/{id}`        | Eliminar producto           |
| `GET`    | `/api/v1/productos/categoria/{categoria}` | Filtrar por categoría |

#### Inventario

| Método   | Endpoint                        | Descripción                 |
| -------- | ------------------------------- | --------------------------- |
| `GET`    | `/api/v1/inventario`            | Listar todo el inventario   |
| `GET`    | `/api/v1/inventario/{id}`       | Obtener inventario por ID   |
| `POST`   | `/api/v1/inventario`            | Crear registro de inventario|
| `PUT`    | `/api/v1/inventario/{id}/stock` | Actualizar stock            |

#### Proveedores

| Método   | Endpoint                        | Descripción                 |
| -------- | ------------------------------- | --------------------------- |
| `GET`    | `/api/v1/proveedores`           | Listar proveedores          |
| `GET`    | `/api/v1/proveedores/{id}`      | Obtener proveedor por ID    |
| `POST`   | `/api/v1/proveedores`           | Crear nuevo proveedor       |
| `PUT`    | `/api/v1/proveedores/{id}`      | Actualizar proveedor        |
| `DELETE` | `/api/v1/proveedores/{id}`      | Eliminar proveedor          |

### 📝 Ejemplo de Creación de Producto

```json
POST /api/v1/productos
Content-Type: application/json

{
  "nombre": "Creatina Monohidratada 300g",
  "categoria": "Suplementos",
  "precio": 19990,
  "descripcion": "Creatina de alta pureza para rendimiento deportivo",
  "image": "https://app-react-powerfit.s3.us-east-1.amazonaws.com/images/creatina-300g.png",
  "stock": 50
}
```

### 📤 Respuesta Exitosa

```json
{
  "id": 13,
  "nombre": "Creatina Monohidratada 300g",
  "categoria": "Suplementos",
  "precio": 19990,
  "descripcion": "Creatina de alta pureza para rendimiento deportivo",
  "image": "https://app-react-powerfit.s3.us-east-1.amazonaws.com/images/creatina-300g.png",
  "stock": 50
}
```

## 🔒 Seguridad

### Mecanismos Implementados

- **Validación de Entrada**: Bean Validation (Jakarta)
  - `@NotBlank`, `@Positive`, `@Min` en DTOs
  - Validación automática con `@Valid` en controllers
  - Prevención de inyección SQL via JPA/Hibernate

- **Manejo de Excepciones**:
  - `GlobalExceptionHandler` centralizado
  - Respuestas estandarizadas sin exponer stack traces
  - Logging estructurado para auditoría

- **Variables de Entorno**:
  - Credenciales de BD en `.env` (nunca en código)
  - Archivos `.env` en `.gitignore`
  - Integración con `spring-dotenv`

- **Protección de Datos**:
  - Validación de stock antes de transacciones
  - Manejo de `StockInsuficienteException`
  - URLs de imágenes validadas (HTTPS requerido)

- **Actuator Endpoints**:
  - `/actuator/health` público
  - Otros endpoints restringidos en producción

### Recomendaciones para Producción

- Implementar autenticación/autorización (JWT + roles)
- HTTPS obligatorio en comunicación con frontend
- Rate limiting para prevenir abuso de API
- Auditoría de cambios en inventario crítico

## 📊 Cobertura de Tests

### Estadísticas Actuales

```bash
./mvnw test
```

**Resultados**:

- ✅ **54 tests** en total
- ✅ **100% tests pasados**
- 📦 **Cobertura estimada**: ~85%

### Tests Incluidos

**Tests Unitarios** (`ProductoServiceTest`):

- CRUD completo de productos
- Validación de stock
- Búsqueda por categoría
- Manejo de excepciones
- Gestión de inventario
- Operaciones con proveedores

**Tests de Integración** (`ProductoControllerTest`):

- Endpoints REST completos
- Validación de request/response
- Códigos HTTP correctos
- Serialización JSON
- Filtros y búsquedas

**Tests de Contexto** (`ProductoApplicationTests`):

- Carga correcta del contexto Spring
- Beans y configuraciones

### Ejecutar con Cobertura

```bash
# Generar reporte Jacoco
./mvnw test jacoco:report

# Ver reporte en: target/site/jacoco/index.html
```

## 🧪 Pruebas

### Ejecutar todas las pruebas

```bash
./mvnw test
```

### Ejecutar pruebas específicas

```bash
# Pruebas del controlador
./mvnw test -Dtest=ProductoControllerTest

# Pruebas del servicio
./mvnw test -Dtest=ProductoServiceTest
```

### Generar reporte de cobertura

```bash
./mvnw jacoco:report
```

## 🏗️ Build y Deployment

### Compilar el proyecto

```bash
./mvnw clean compile
```

### Generar JAR ejecutable

```bash
./mvnw clean package
```

### Ejecutar JAR

```bash
java -jar target/producto-0.0.1-SNAPSHOT.jar
```

## 🔧 Configuración Avanzada

### Perfiles de Spring

- **Por defecto**: `application.properties` (H2 en memoria para desarrollo rápido)
- **Docker**: `application-docker.properties` (MySQL en contenedor)
- **Producción**: Variables de entorno (RDS)

### Datos Iniciales

El archivo `data.sql` en `src/main/resources/` puede contener datos iniciales:

```sql
INSERT INTO producto (nombre, categoria, precio, descripcion, image, stock) VALUES
('Whey Protein 2lb', 'Proteínas', 34990, 'Proteína de suero', 'https://...', 100);
```

### Logging

La aplicación utiliza Logback. Los niveles se configuran en `application.properties`:

```properties
logging.level.com.skarx.producto=DEBUG
logging.level.org.springframework.web=INFO
```

## 🐛 Troubleshooting

### Problemas Comunes

1. **Error de conexión a la base de datos**
   - Verificar que MySQL esté ejecutándose
   - Comprobar credenciales en `.env`
   - Asegurar que la base de datos existe
   - Si usas RDS: verificar Security Group e IP permitida

2. **Puerto ya en uso**
   - Cambiar `SERVER_PORT` en `.env`
   - Verificar procesos: `netstat -ano | findstr :8081` (Windows)

3. **Problemas con variables de entorno**
   - Verificar archivo `.env` en la raíz
   - Comprobar sintaxis de variables

4. **Tests fallan**
   - Asegurar que H2 esté en dependencias para testing
   - Verificar `application-test.properties`

## 🤝 Contribución

1. Fork el proyecto
2. Crea rama feature (`git checkout -b feature/AmazingFeature`)
3. Commit cambios (`git commit -m 'Add AmazingFeature'`)
4. Push a rama (`git push origin feature/AmazingFeature`)
5. Abre Pull Request

## 📋 Convenciones de Código

- Usar **Lombok** para reducir código boilerplate
- Seguir convenciones de nomenclatura de Java
- Documentar métodos públicos con JavaDoc
- Usar `@Valid` para validación de entrada
- Manejar excepciones específicas del dominio

---

<div align="center">
  <sub>Desarrollado con ❤️ usando Spring Boot</sub>
</div>
