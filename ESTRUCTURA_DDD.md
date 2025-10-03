# Estructura del Proyecto VollMed API (DDD)

Este proyecto sigue la arquitectura DDD (Domain-Driven Design). A continuación se explica el propósito de cada carpeta y los archivos que contiene.

## 1. `api/`
Contiene la capa de presentación (controllers) y los DTOs de entrada/salida.

- **controller/**: Controladores REST que exponen los endpoints de la API.
  - `AutenticacionController.java`: Maneja autenticación y generación de tokens.
  - `ConsultaController.java`: Endpoints para consultas médicas.
  - `MedicoController.java`: Endpoints para gestión de médicos.
  - `PacienteController.java`: Endpoints para gestión de pacientes.
- **request/**: DTOs para recibir datos de las peticiones HTTP.
  - Ej: `DatosRegistroMedico.java`, `DatosAutenticacionUsuario.java`, etc.
- **response/**: DTOs para responder a las peticiones HTTP.
  - Ej: `DatosJWTToken.java`, `DatosDetalleConsulta.java`, etc.

## 2. `application/`
Contiene la lógica de aplicación y orquestación de casos de uso.

- **command/**: (Vacío o reservado para comandos de CQRS).
- **dto/**: DTOs internos de la aplicación.
  - `DatosDireccion.java`: Representa la dirección en operaciones internas.
- **query/**: (Vacío o reservado para queries de CQRS).
- **service/**: Servicios de aplicación que coordinan la lógica entre dominio e infraestructura.
  - `GestionConsultaService.java`, `GestionMedicoService.java`, `GestionPacienteService.java`.

## 3. `domain/`
Contiene el núcleo del dominio: entidades, repositorios, servicios y lógica de negocio.

- **event/**: (Vacío o reservado para eventos de dominio).
- **model/**: Entidades y objetos de valor del dominio.
  - **autenticacion/**: `Usuario.java` (entidad de usuario).
  - **consultas_medicas/**: `Consulta.java`, `MotivoCancelamiento.java` (entidad y enum de consultas).
  - **gestion_medica/**: `Especialidad.java`, `Medico.java` (entidad y enum de médicos).
  - **gestion_pacientes/**: `Paciente.java` (entidad de paciente).
  - **shared/**: Objetos de valor y clases base.
    - `AggregateRoot.java`, `Direccion.java`, `DomainException.java`.
- **repository/**: Interfaces de repositorios para acceso a datos.
  - Ej: `MedicoRepository.java`, `PacienteRepository.java`, etc.
- **service/**: Servicios de dominio con lógica de negocio compleja o validaciones.
  - Ej: `CancelacionConsultaService.java`, `ReservaConsultaService.java`, validadores de reglas de negocio.

## 4. `infrastructure/`
Contiene la implementación de detalles técnicos y dependencias externas.

- **configuration/**: Configuraciones técnicas y de seguridad.
  - `SecurityConfigurations.java`: Configuración de seguridad Spring Security.
  - `SecurityFilter.java`: Filtro de autenticación JWT.
- **external/**: (Vacío o reservado para integraciones externas).
- **messaging/**: (Vacío o reservado para mensajería/eventos externos).
- **persistence/**: (Vacío o reservado para implementaciones de repositorios).
- **service/**: Implementaciones de servicios de infraestructura.
  - `AutenticacionService.java`: Servicio de autenticación concreto.
  - `TokenService.java`: Servicio para generación y validación de tokens JWT.

## 5. Archivos raíz
- `ApiApplication.java`: Clase principal de arranque de Spring Boot.

---

### Resumen de DDD aplicado
- **api/**: Interfaz de usuario (entrada/salida HTTP).
- **application/**: Orquestación de casos de uso.
- **domain/**: Lógica de negocio pura, entidades, repositorios y servicios de dominio.
- **infrastructure/**: Implementaciones técnicas, seguridad, persistencia y servicios externos.

Esta estructura permite separar claramente las responsabilidades y facilita la escalabilidad y mantenibilidad del sistema.
