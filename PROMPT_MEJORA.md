# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/com/bank/transaction/domain/port/AuthenticationService.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- `src/main/java/com/bank/transaction/infrastructure/adapter/JwtAuthenticationService.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- `src/main/java/com/bank/transaction/infrastructure/security/SecurityConfig.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- `src/main/java/com/bank/transaction/infrastructure/security/JwtTokenUtil.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- `src/main/java/com/bank/transaction/infrastructure/security/JwtAuthenticationFilter.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- `src/main/java/com/bank/transaction/infrastructure/adapter/OAuth2AuthenticationService.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- `src/main/java/com/bank/transaction/infrastructure/exception/AuthenticationException.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/bank/transaction/TransactionApplication.java` — `io.swagger.v3`: El import io.swagger.v3.oas.models.OpenAPI pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/transaction/application/service/TransactionService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/transaction/infrastructure/config/OpenApiConfig.java` — `io.swagger.v3`: El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/transaction/infrastructure/exception/GlobalExceptionHandler.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/transaction/infrastructure/adapter/JpaTransactionRepository.java` — `SpringDataTransactionRepository.findByAccountIdOrderByTimestampDesc`: Se invoca `findByAccountIdOrderByTimestampDesc` sobre `SpringDataTransactionRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/infrastructure/adapter/JpaTransactionRepository.java` — `SpringDataTransactionRepository.findByAccountIdAndTimestampBetweenOrderByTimestampDesc`: Se invoca `findByAccountIdAndTimestampBetweenOrderByTimestampDesc` sobre `SpringDataTransactionRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/infrastructure/adapter/JpaTransactionRepository.java` — `SpringDataTransactionRepository.findByAmountGreaterThanOrderByTimestampDesc`: Se invoca `findByAmountGreaterThanOrderByTimestampDesc` sobre `SpringDataTransactionRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/application/service/TransactionService.java` — `Transaction.id`: Se invoca `id` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/application/service/TransactionService.java` — `Transaction.amount`: Se invoca `amount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/application/service/TransactionService.java` — `Transaction.isPresent`: Se invoca `isPresent` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/application/service/TransactionService.java` — `Transaction.get`: Se invoca `get` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/application/service/TransactionService.java` — `Transaction.accountId`: Se invoca `accountId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/application/service/TransactionService.java` — `Transaction.type`: Se invoca `type` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/application/service/TransactionService.java` — `Transaction.description`: Se invoca `description` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/application/service/TransactionService.java` — `Transaction.timestamp`: Se invoca `timestamp` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/application/service/TransactionService.java` — `Transaction.authToken`: Se invoca `authToken` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `UpdateStatusRequest.accountId`: Se invoca `accountId` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `UpdateStatusRequest.amount`: Se invoca `amount` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `UpdateStatusRequest.type`: Se invoca `type` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `UpdateStatusRequest.description`: Se invoca `description` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `Transaction.id`: Se invoca `id` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `Transaction.accountId`: Se invoca `accountId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `Transaction.amount`: Se invoca `amount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `Transaction.type`: Se invoca `type` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `Transaction.description`: Se invoca `description` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `UpdateStatusRequest.status`: Se invoca `status` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `Transaction.createdAt`: Se invoca `createdAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `Transaction.auditTrail`: Se invoca `auditTrail` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `Transaction.status`: Se invoca `status` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `Transaction.updatedAt`: Se invoca `updatedAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/infrastructure/security/JwtAuthenticationFilter.java` — `JwtTokenUtil.extractUsername`: Se invoca `extractUsername` sobre `JwtTokenUtil`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/infrastructure/security/JwtAuthenticationFilter.java` — `JwtTokenUtil.extractRoles`: Se invoca `extractRoles` sobre `JwtTokenUtil`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/infrastructure/adapter/OAuth2AuthenticationService.java` — `OAuth2Token.expiresAt`: Se invoca `expiresAt` sobre `OAuth2Token`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transaction/infrastructure/adapter/OAuth2AuthenticationService.java` — `OAuth2Token.roles`: Se invoca `roles` sobre `OAuth2Token`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/transaction/application/service/TransactionServiceTest.java` — `Transaction.id`: Se invoca `id` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/transaction/application/service/TransactionServiceTest.java` — `Transaction.status`: Se invoca `status` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/transaction/application/service/TransactionServiceTest.java` — `TransactionException.getMessage`: Se invoca `getMessage` sobre `TransactionException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/transaction/application/service/TransactionServiceTest.java` — `TransactionService.getTransactionById`: Se invoca `getTransactionById` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/transaction/application/service/TransactionServiceTest.java` — `Transaction.isPresent`: Se invoca `isPresent` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/transaction/application/service/TransactionServiceTest.java` — `Transaction.get`: Se invoca `get` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/transaction/application/service/TransactionServiceTest.java` — `Transaction.isEmpty`: Se invoca `isEmpty` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/transaction/application/service/TransactionServiceTest.java` — `TransactionService.getTransactionsByAccount`: Se invoca `getTransactionsByAccount` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/transaction/application/service/TransactionServiceTest.java` — `Transaction.size`: Se invoca `size` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/transaction/application/service/TransactionServiceTest.java` — `TransactionService.getTransactionsByAccountAndDateRange`: Se invoca `getTransactionsByAccountAndDateRange` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/transaction/application/service/TransactionServiceTest.java` — `TransactionService.hasAccessToTransaction`: Se invoca `hasAccessToTransaction` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/transaction/infrastructure/controller/TransactionControllerTest.java` — `TransactionService.getTransactionById`: Se invoca `getTransactionById` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/transaction/infrastructure/controller/TransactionControllerTest.java` — `TransactionService.getTransactionsByAccount`: Se invoca `getTransactionsByAccount` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/transaction/infrastructure/controller/TransactionControllerTest.java` — `TransactionService.getTransactionsByAccountAndDateRange`: Se invoca `getTransactionsByAccountAndDateRange` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced

### Brecha de conocimiento
Experto en el marco de trabajo (framework) mas relevante en su lenguaje de programación principal, lo que le permite escribir código más legible y reducir la redundancia. Ha implementado al menos dos mecanismos de autenticación e identifica el uso de un JWT

### Misión / candidato
Candidato con experiencia avanzada en Backend Java, trabajando en contextos de seguridad y arquitectura limpia.

### Reto
- Tema: Especialista en Framework con Proficiencia en Seguridad y Legibilidad de Código
- Seniority: advanced-l3
- Tipo: mixed
- Título: Optimización de Framework y Seguridad en Aplicación Backend
- Tiempo estimado: 3 semanas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Análisis de la Estructura Actual — objetivo: Identificar las deficiencias en la estructura del código y la seguridad de la aplicación — entregable (NO resolver): Informe detallado de las deficiencias encontradas y recomendaciones para mejorar la estructura y seguridad del código.
- Fase 2: Implementación de Mecanismos de Autenticación — objetivo: Implementar al menos dos mecanismos de autenticación y utilizar JWT para la gestión de sesiones — entregable (NO resolver): Código implementado con los nuevos mecanismos de autenticación y JWT, junto con documentación que explique las decisiones tomadas.
- Fase 3: Refactorización y Optimización del Código — objetivo: Refactorizar el código para mejorar la legibilidad y reducir la redundancia — entregable (NO resolver): Código refactorizado con mejoras en la legibilidad y reducción de redundancias, junto con documentación que explique los cambios realizados.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.bank.transaction</groupId>
    <artifactId>transaction-service</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>transaction-service</name>
    <description>Servicio de transacciones bancarias con autenticación JWT y OAuth2</description>

    <properties>
        <java.version>21</java.version>
        <springdoc-openapi.version>2.6.0</springdoc-openapi.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <!-- JWT -->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.12.5</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.12.5</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.12.5</version>
            <scope>runtime</scope>
        </dependency>

        <!-- SpringDoc OpenAPI -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>${springdoc-openapi.version}</version>
        </dependency>

        <!-- PostgreSQL -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/bank/transaction/TransactionApplication.java ===
package com.bank.transaction;


import com.bank.transaction.domain.model.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class TransactionApplication {
    private final String[] allowedOrigins = {"http://localhost:3000", "https://bank-ui.example.com"};

    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(allowedOrigins)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Transaction Service API")
                        .version("1.0")
                        .description("API para gestión de transacciones bancarias con autenticación JWT y OAuth2")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: transaction-service
  datasource:
    url: jdbc:postgresql://localhost:5432/transaction_db
    username: transaction_user
    password: transaction_password
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 20
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: validate
    properties:
      hibernate:
        format_sql: true
        jdbc:
          batch_size: 50
          order_inserts: true
          order_updates: true
        default_schema: transaction_schema
    open-in-view: false
  security:
    user:
      name: admin
      password: admin
      roles: ADMIN
    oauth2:
      client:
        registration:
          google:
            client-id: ${GOOGLE_CLIENT_ID}
            client-secret: ${GOOGLE_CLIENT_SECRET}
            scope: openid,profile,email
            redirect-uri: "{baseUrl}/login/oauth2/code/{registrationId}"
        provider:
          google:
            authorization-uri: https://accounts.google.com/o/oauth2/auth
            token-uri: https://oauth2.googleapis.com/token
            user-info-uri: https://www.googleapis.com/oauth2/v3/userinfo
            user-name-attribute: sub

server:
  port: 8080
  servlet:
    context-path: /api
  error:
    include-message: always
    include-binding-errors: always
    include-stacktrace: never
    include-exception: false

logging:
  level:
    root: INFO
    com.bank.transaction: DEBUG
    org.springframework.security: DEBUG
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      show-details: always
  metrics:
    tags:
      application: ${spring.application.name}

// === ARCHIVO: src/main/java/com/bank/transaction/domain/model/Transaction.java ===
package com.bank.transaction.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Transaction(
    @NotNull
    UUID id,

    @NotNull
    UUID accountId,

    @NotNull
    @Positive
    BigDecimal amount,

    @NotNull
    @Size(min = 3, max = 3)
    String currency,

    @NotNull
    TransactionType type,

    @NotNull
    LocalDateTime timestamp,

    @NotNull
    @Size(max = 255)
    String description,

    @NotNull
    TransactionStatus status
) {
    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, TRANSFER, PAYMENT, REFUND
    }

    public enum TransactionStatus {
        PENDING, COMPLETED, FAILED, CANCELLED
    }

    public Transaction {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (currency == null || currency.length() != 3) {
            throw new IllegalArgumentException("Currency must be a 3-letter code");
        }
    }

    public boolean isHighValue() {
        return amount.compareTo(new BigDecimal("10000.00")) > 0;
    }

    public boolean requiresAudit() {
        return isHighValue() || type == TransactionType.TRANSFER;
    }
}

// === ARCHIVO: src/main/java/com/bank/transaction/domain/port/TransactionRepository.java ===
package com.bank.transaction.domain.port;

import com.bank.transaction.domain.model.Transaction;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {
    Transaction save(Transaction transaction);

    Optional<Transaction> findById(UUID id);

    List<Transaction> findByAccountId(UUID accountId);

    List<Transaction> findByAccountIdAndDateRange(UUID accountId, LocalDateTime start, LocalDateTime end);

    List<Transaction> findHighValueTransactions();
}

// === ARCHIVO: src/main/java/com/bank/transaction/domain/port/AuthenticationService.java ===
package com.bank.transaction.domain.port;

import java.util.Set;

public interface AuthenticationService {
    /**
     * Verifica las credenciales del usuario y retorna un token de autenticación.
     * 
     * @param username Nombre de usuario
     * @param password Contraseña del usuario
     * @return Token de autenticación válido
     * @throws com.bank.transaction.infrastructure.exception.AuthenticationException Si las credenciales son inválidas
     */
    String authenticate(String username, String password);

    /**
     * Valida un token de autenticación y retorna los roles asociados.
     * 
     * @param token Token a validar
     * @return Conjunto de roles del usuario
     * @throws com.bank.transaction.infrastructure.exception.AuthenticationException Si el token es inválido o ha expirado
     */
    Set<String> validateToken(String token);

    /**
     * Verifica si el usuario tiene el rol requerido.
     * 
     * @param token Token de autenticación
     * @param requiredRole Rol requerido
     * @return true si el usuario tiene el rol, false en caso contrario
     */
    boolean hasRole(String token, String requiredRole);
}

// === ARCHIVO: src/main/java/com/bank/transaction/infrastructure/adapter/JpaTransactionRepository.java ===
package com.bank.transaction.infrastructure.adapter;

import com.bank.transaction.domain.model.Transaction;
import com.bank.transaction.domain.port.TransactionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaTransactionRepository implements TransactionRepository {

    private final SpringDataTransactionRepository springDataRepository;

    public JpaTransactionRepository(SpringDataTransactionRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return springDataRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> findById(UUID id) {
        return springDataRepository.findById(id);
    }

    @Override
    public List<Transaction> findByAccountId(UUID accountId) {
        return springDataRepository.findByAccountIdOrderByTimestampDesc(accountId);
    }

    @Override
    public List<Transaction> findByAccountIdAndDateRange(UUID accountId, LocalDateTime start, LocalDateTime end) {
        return springDataRepository.findByAccountIdAndTimestampBetweenOrderByTimestampDesc(
            accountId, start, end);
    }

    @Override
    public List<Transaction> findHighValueTransactions() {
        return springDataRepository.findByAmountGreaterThanOrderByTimestampDesc(
            new java.math.BigDecimal("10000"));
    }

    @Repository
    public interface SpringDataTransactionRepository extends JpaRepository<Transaction, UUID> {
        List<Transaction> findByAccountIdOrderByTimestampDesc(UUID accountId);

        List<Transaction> findByAccountIdAndTimestampBetweenOrderByTimestampDesc(
            UUID accountId, LocalDateTime start, LocalDateTime end);

        @Query("SELECT t FROM Transaction t WHERE t.amount > :threshold ORDER BY t.timestamp DESC")
        List<Transaction> findByAmountGreaterThanOrderByTimestampDesc(
            @Param("threshold") java.math.BigDecimal threshold);
    }
}

// === ARCHIVO: src/main/java/com/bank/transaction/application/service/TransactionService.java ===
package com.bank.transaction.application.service;

import com.bank.transaction.domain.model.Transaction;
import com.bank.transaction.domain.model.Transaction.TransactionStatus;
import com.bank.transaction.domain.model.Transaction.TransactionType;
import com.bank.transaction.domain.port.AuthenticationService;
import com.bank.transaction.domain.port.TransactionRepository;
import com.bank.transaction.infrastructure.exception.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private static final BigDecimal HIGH_VALUE_THRESHOLD = new BigDecimal("10000");
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_USER = "USER";

    private final TransactionRepository transactionRepository;
    private final AuthenticationService authenticationService;

    public TransactionService(TransactionRepository transactionRepository,
                              AuthenticationService authenticationService) {
        this.transactionRepository = transactionRepository;
        this.authenticationService = authenticationService;
    }

    @Transactional
    public Transaction createTransaction(UUID accountId, BigDecimal amount,
                                         TransactionType type, String description,
                                         String authToken) {
        logger.info("Iniciando creación de transacción para cuenta: {}, monto: {}", accountId, amount);

        validateAuthentication(authToken);
        validateTransactionInput(accountId, amount, type);

        Transaction transaction = new Transaction(
            UUID.randomUUID(),
            accountId,
            amount,
            type,
            TransactionStatus.PENDING,
            description,
            LocalDateTime.now(),
            authToken
        );

        Transaction savedTransaction = transactionRepository.save(transaction);
        logger.info("Transacción creada exitosamente con ID: {}", savedTransaction.id());

        if (savedTransaction.isHighValue()) {
            logger.warn("Transacción de alto valor detectada: {} para cuenta: {}",
                savedTransaction.amount(), accountId);
        }

        return savedTransaction;
    }

    @Transactional(readOnly = true)
    public Optional<Transaction> getTransaction(UUID transactionId, String authToken) {
        logger.debug("Consultando transacción con ID: {}", transactionId);

        validateAuthentication(authToken);

        Optional<Transaction> transaction = transactionRepository.findById(transactionId);
        if (transaction.isPresent() && transaction.get().isHighValue()) {
            verifyHighValueAccess(authToken);
        }

        return transaction;
    }

    @Transactional(readOnly = true)
    public List<Transaction> getAccountTransactions(UUID accountId, String authToken) {
        logger.debug("Consultando transacciones para cuenta: {}", accountId);

        validateAuthentication(authToken);

        return transactionRepository.findByAccountId(accountId);
    }

    @Transactional(readOnly = true)
    public List<Transaction> getAccountTransactionsByDateRange(UUID accountId,
                                                                LocalDateTime startDate,
                                                                LocalDateTime endDate,
                                                                String authToken) {
        logger.debug("Consultando transacciones para cuenta: {} entre {} y {}",
            accountId, startDate, endDate);

        validateAuthentication(authToken);

        return transactionRepository.findByAccountIdAndDateRange(accountId, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public List<Transaction> getHighValueTransactions(String authToken) {
        logger.debug("Consultando transacciones de alto valor");

        validateAuthentication(authToken);
        verifyHighValueAccess(authToken);

        return transactionRepository.findHighValueTransactions();
    }

    @Transactional
    public Transaction updateTransactionStatus(UUID transactionId, TransactionStatus newStatus,
                                                String authToken) {
        logger.info("Actualizando estado de transacción {} a {}", transactionId, newStatus);

        validateAuthentication(authToken);
        verifyHighValueAccess(authToken);

        Optional<Transaction> existingTransaction = transactionRepository.findById(transactionId);
        if (existingTransaction.isEmpty()) {
            throw new TransactionException("Transacción no encontrada: " + transactionId);
        }

        Transaction transaction = existingTransaction.get();
        Transaction updatedTransaction = new Transaction(
            transaction.id(),
            transaction.accountId(),
            transaction.amount(),
            transaction.type(),
            newStatus,
            transaction.description(),
            transaction.timestamp(),
            transaction.authToken()
        );

        return transactionRepository.save(updatedTransaction);
    }

    private void validateAuthentication(String authToken) {
        if (authToken == null || authToken.isBlank()) {
            throw new TransactionException("Token de autenticación requerido");
        }

        Set<String> roles = authenticationService.validateToken(authToken);
        if (roles.isEmpty()) {
            throw new TransactionException("Token de autenticación inválido");
        }
    }

    private void verifyHighValueAccess(String authToken) {
        if (!authenticationService.hasRole(authToken, ROLE_ADMIN)) {
            throw new TransactionException("Acceso denegado: se requiere rol ADMIN para operaciones de alto valor");
        }
    }

    private void validateTransactionInput(UUID accountId, BigDecimal amount, TransactionType type) {
        if (accountId == null) {
            throw new TransactionException("El ID de cuenta es requerido");
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new TransactionException("El monto debe ser mayor a cero");
        }

        if (type == null) {
            throw new TransactionException("El tipo de transacción es requerido");
        }

        if (amount.compareTo(HIGH_VALUE_THRESHOLD) > 0) {
            logger.info("Monto de alto valor detectado: {}", amount);
        }
    }
}

// === ARCHIVO: src/main/java/com/bank/transaction/infrastructure/adapter/JwtAuthenticationService.java ===
package com.bank.transaction.infrastructure.adapter;

import com.bank.transaction.domain.port.AuthenticationService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class JwtAuthenticationService implements AuthenticationService {

    @Override
    public String authenticate(String username, String password) {
        throw new UnsupportedOperationException("Implementar autenticación JWT");
    }

    @Override
    public Set<String> validateToken(String token) {
        throw new UnsupportedOperationException("Implementar validación de token JWT");
    }

    @Override
    public boolean hasRole(String token, String requiredRole) {
        throw new UnsupportedOperationException("Implementar verificación de rol JWT");
    }
}

// === ARCHIVO: src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java ===
package com.bank.transaction.infrastructure.controller;

import com.bank.transaction.domain.model.Transaction;
import com.bank.transaction.domain.model.Transaction.TransactionStatus;
import com.bank.transaction.domain.model.Transaction.TransactionType;
import com.bank.transaction.domain.port.AuthenticationService;
import com.bank.transaction.domain.port.TransactionRepository;
import com.bank.transaction.infrastructure.exception.TransactionException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
@Tag(name = "Transactions", description = "API para gestión de transacciones bancarias")
@SecurityRequirement(name = "bearerAuth")
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final AuthenticationService authenticationService;

    public TransactionController(TransactionRepository transactionRepository,
                                  AuthenticationService authenticationService) {
        this.transactionRepository = transactionRepository;
        this.authenticationService = authenticationService;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva transacción", description = "Registra una nueva transacción bancaria en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Transacción creada exitosamente",
                     content = @Content(schema = @Schema(implementation = TransactionResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos de transacción inválidos"),
        @ApiResponse(responseCode = "401", description = "No autorizado - Token JWT inválido o ausente"),
        @ApiResponse(responseCode = "403", description = "Prohibido - Rol insuficiente para esta operación")
    })
    public ResponseEntity<TransactionResponse> createTransaction(
            @Valid @RequestBody TransactionRequest request,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        
        String token = extractToken(authHeader);
        validateAuthentication(token, "TRANSACTION_CREATE");
        
        Transaction transaction = new Transaction(
            UUID.randomUUID(),
            request.accountId(),
            request.amount(),
            request.type(),
            request.description(),
            TransactionStatus.PENDING,
            LocalDateTime.now(),
            null,
            null
        );
        
        Transaction saved = transactionRepository.save(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(saved));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener transacción por ID", description = "Recupera los detalles de una transacción específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transacción encontrada",
                     content = @Content(schema = @Schema(implementation = TransactionResponse.class))),
        @ApiResponse(responseCode = "404", description = "Transacción no encontrada"),
        @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    public ResponseEntity<TransactionResponse> getTransactionById(
            @Parameter(description = "ID de la transacción", required = true) @PathVariable UUID id,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        
        String token = extractToken(authHeader);
        validateAuthentication(token, "TRANSACTION_READ");
        
        return transactionRepository.findById(id)
            .map(t -> ResponseEntity.ok(toResponse(t)))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/account/{accountId}")
    @Operation(summary = "Listar transacciones por cuenta", description = "Obtiene todas las transacciones asociadas a una cuenta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de transacciones de la cuenta"),
        @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    public ResponseEntity<List<TransactionResponse>> getTransactionsByAccount(
            @Parameter(description = "ID de la cuenta", required = true) @PathVariable UUID accountId,
            @Parameter(description = "Fecha de inicio (opcional)") @RequestParam(required = false) LocalDateTime startDate,
            @Parameter(description = "Fecha de fin (opcional)") @RequestParam(required = false) LocalDateTime endDate,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        
        String token = extractToken(authHeader);
        validateAuthentication(token, "TRANSACTION_READ");
        
        List<Transaction> transactions;
        if (startDate != null && endDate != null) {
            transactions = transactionRepository.findByAccountIdAndDateRange(accountId, startDate, endDate);
        } else {
            transactions = transactionRepository.findByAccountId(accountId);
        }
        
        List<TransactionResponse> responses = transactions.stream()
            .map(this::toResponse)
            .toList();
        
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/high-value")
    @Operation(summary = "Listar transacciones de alto valor", description = "Obtiene transacciones que superan el umbral configurado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de transacciones de alto valor"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido - Se requiere rol de auditor")
    })
    public ResponseEntity<List<TransactionResponse>> getHighValueTransactions(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        
        String token = extractToken(authHeader);
        validateAuthentication(token, "TRANSACTION_AUDIT");
        
        List<Transaction> transactions = transactionRepository.findHighValueTransactions();
        List<TransactionResponse> responses = transactions.stream()
            .map(this::toResponse)
            .toList();
        
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Actualizar estado de transacción", description = "Modifica el estado de una transacción existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estado actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Transacción no encontrada"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido")
    })
    public ResponseEntity<TransactionResponse> updateTransactionStatus(
            @Parameter(description = "ID de la transacción", required = true) @PathVariable UUID id,
            @Valid @RequestBody UpdateStatusRequest request,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        
        String token = extractToken(authHeader);
        validateAuthentication(token, "TRANSACTION_UPDATE");
        
        Transaction existing = transactionRepository.findById(id)
            .orElseThrow(() -> new TransactionException("Transacción no encontrada: " + id));
        
        Transaction updated = new Transaction(
            existing.id(),
            existing.accountId(),
            existing.amount(),
            existing.type(),
            existing.description(),
            request.status(),
            existing.createdAt(),
            LocalDateTime.now(),
            existing.auditTrail()
        );
        
        Transaction saved = transactionRepository.save(updated);
        return ResponseEntity.ok(toResponse(saved));
    }

    private String extractToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getCredentials() instanceof String token) {
            return token;
        }
        throw new TransactionException("Token de autenticación no proporcionado");
    }

    private void validateAuthentication(String token, String requiredRole) {
        if (!authenticationService.validateToken(token).isEmpty()) {
            if (!authenticationService.hasRole(token, requiredRole)) {
                throw new TransactionException("Rol insuficiente para esta operación: " + requiredRole);
            }
        } else {
            throw new TransactionException("Token inválido o expirado");
        }
    }

    private TransactionResponse toResponse(Transaction transaction) {
        return new TransactionResponse(
            transaction.id(),
            transaction.accountId(),
            transaction.amount(),
            transaction.type(),
            transaction.description(),
            transaction.status(),
            transaction.createdAt(),
            transaction.updatedAt()
        );
    }

    public record TransactionRequest(
        @NotNull(message = "El ID de cuenta es obligatorio")
        UUID accountId,
        @NotNull(message = "El monto es obligatorio")
        @Positive(message = "El monto debe ser positivo")
        BigDecimal amount,
        @NotNull(message = "El tipo de transacción es obligatorio")
        TransactionType type,
        @NotBlank(message = "La descripción es obligatoria")
        String description
    ) {}

    public record TransactionResponse(
        UUID id,
        UUID accountId,
        BigDecimal amount,
        TransactionType type,
        String description,
        TransactionStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {}

    public record UpdateStatusRequest(
        @NotNull(message = "El estado es obligatorio")
        TransactionStatus status
    ) {}
}

// === ARCHIVO: src/main/java/com/bank/transaction/infrastructure/security/SecurityConfig.java ===
package com.bank.transaction.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                .requestMatchers("/actuator/health").permitAll()
                .anyRequest().authenticated()
            );
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

// === ARCHIVO: src/main/java/com/bank/transaction/infrastructure/security/JwtTokenUtil.java ===
package com.bank.transaction.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret:mySecretKeyForJWTTokenGenerationThatIsLongEnough123456}")
    private String secret;

    @Value("${jwt.expiration:86400000}")
    private Long expiration;

    public String generateToken(String username, Set<String> roles) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        
        return Jwts.builder()
            .subject(username)
            .claim("roles", roles)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(key)
            .compact();
    }

    public boolean validateToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload();
        return claims.getSubject();
    }

    public Set<String> getRolesFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload();
        
        Object rolesObj = claims.get("roles");
        if (rolesObj instanceof java.util.List<?>) {
            return ((java.util.List<?>) rolesObj).stream()
                .map(Object::toString)
                .collect(Collectors.toSet());
        }
        return Set.of();
    }
}

// === ARCHIVO: src/main/java/com/bank/transaction/infrastructure/security/JwtAuthenticationFilter.java ===
package com.bank.transaction.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            
            if (jwtTokenUtil.validateToken(token)) {
                String username = jwtTokenUtil.extractUsername(token);
                Set<String> roles = jwtTokenUtil.extractRoles(token);
                
                List<SimpleGrantedAuthority> authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
                
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        
        filterChain.doFilter(request, response);
    }
}

// === ARCHIVO: src/main/java/com/bank/transaction/infrastructure/config/OpenApiConfig.java ===
package com.bank.transaction.infrastructure.config;


import com.bank.transaction.domain.model.Transaction;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Transaction Service API")
                        .version("1.0.0")
                        .description("""
                                API REST para la gestión de transacciones bancarias.
                                
                                Este servicio proporciona endpoints para crear, consultar y 
                                gestionar transacciones bancarias con soporte para múltiples 
                                mecanismos de autenticación incluyendo JWT y OAuth2.
                                """)
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("desarrollo@bank.com")
                                .url("https://www.bank.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("""
                                        Autenticación mediante token JWT.
                                        
                                        Para obtener un token, utilize el endpoint de autenticación
                                        y proporcione credenciales válidas.
                                        """))
                        .addSecuritySchemes("oauth2", new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .description("""
                                        Autenticación mediante OAuth2 con flujo de credenciales de cliente.
                                        
                                        Este esquema requiere configuración prévia del servidor de autorización.
                                        """)
                                .flows(new io.swagger.v3.oas.models.security.OAuthFlows()
                                        .clientCredentials(new io.swagger.v3.oas.models.security.OAuthFlow()
                                                .tokenUrl("/oauth/token")
                                                .scopes(new io.swagger.v3.oas.models.Scopes()
                                                        .addString("transaction:read", "Permiso para leer transacciones")
                                                        .addString("transaction:write", "Permiso para crear transacciones")
                                                        .addString("transaction:admin", "Permiso administrativo completo"))))));
    }
}

// === ARCHIVO: src/main/java/com/bank/transaction/infrastructure/adapter/OAuth2AuthenticationService.java ===
package com.bank.transaction.infrastructure.adapter;

import com.bank.transaction.domain.port.AuthenticationService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OAuth2AuthenticationService implements AuthenticationService {

    private final Map<String, OAuth2Token> tokenStore = new ConcurrentHashMap<>();
    private final String clientId = "transaction-service";
    private final String clientSecret = "secret";
    private final String tokenEndpoint = "/oauth/token";

    @Override
    public String authenticate(String username, String password) {
        throw new UnsupportedOperationException("OAuth2 authentication requires client credentials flow");
    }

    public String authenticateWithClientCredentials(String clientId, String clientSecret) {
        if (!this.clientId.equals(clientId) || !this.clientSecret.equals(clientSecret)) {
            throw new SecurityException("Invalid client credentials");
        }
        
        String tokenId = UUID.randomUUID().toString();
        OAuth2Token token = new OAuth2Token(
                tokenId,
                Set.of("transaction:read", "transaction:write"),
                System.currentTimeMillis() + 3600000
        );
        
        tokenStore.put(tokenId, token);
        return tokenId;
    }

    @Override
    public Set<String> validateToken(String token) {
        OAuth2Token oauth2Token = tokenStore.get(token);
        
        if (oauth2Token == null) {
            return Set.of();
        }
        
        if (System.currentTimeMillis() > oauth2Token.expiresAt()) {
            tokenStore.remove(token);
            return Set.of();
        }
        
        return oauth2Token.roles();
    }

    @Override
    public boolean hasRole(String token, String requiredRole) {
        Set<String> roles = validateToken(token);
        return roles.contains(requiredRole);
    }

    public Map<String, Object> createTokenResponse(String token) {
        OAuth2Token oauth2Token = tokenStore.get(token);
        if (oauth2Token == null) {
            throw new SecurityException("Invalid token");
        }
        
        return Map.of(
                OAuth2ParameterNames.ACCESS_TOKEN, token,
                OAuth2ParameterNames.TOKEN_TYPE, "Bearer",
                OAuth2ParameterNames.EXPIRES_IN, 3600,
                "scope", String.join(" ", oauth2Token.roles())
        );
    }

    private record OAuth2Token(String tokenId, Set<String> roles, long expiresAt) {}
}

// === ARCHIVO: src/main/java/com/bank/transaction/infrastructure/exception/TransactionException.java ===
package com.bank.transaction.infrastructure.exception;

public class TransactionException extends RuntimeException {
    private final String errorCode;
    private final String details;

    public TransactionException(String message) {
        super(message);
        this.errorCode = "TXN_ERROR";
        this.details = null;
    }

    public TransactionException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.details = null;
    }

    public TransactionException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "TXN_ERROR";
        this.details = null;
    }

    public TransactionException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.details = null;
    }

    public TransactionException(String message, String errorCode, String details) {
        super(message);
        this.errorCode = errorCode;
        this.details = details;
    }

    public TransactionException(String message, String errorCode, String details, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.details = details;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getDetails() {
        return details;
    }
}

// === ARCHIVO: src/main/java/com/bank/transaction/infrastructure/exception/AuthenticationException.java ===
package com.bank.transaction.infrastructure.exception;

public class AuthenticationException extends RuntimeException {
    private final String errorCode;

    public AuthenticationException(String message) {
        super(message);
        this.errorCode = "AUTH_ERROR";
    }

    public AuthenticationException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "AUTH_ERROR";
    }

    public String getErrorCode() {
        return errorCode;
    }
}

// === ARCHIVO: src/main/java/com/bank/transaction/infrastructure/exception/GlobalExceptionHandler.java ===
package com.bank.transaction.infrastructure.exception;


import com.bank.transaction.domain.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<ErrorResponse> handleTransactionException(
            TransactionException ex, WebRequest request) {
        logger.error("TransactionException: {} - ErrorCode: {}", ex.getMessage(), ex.getErrorCode(), ex);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Transaction Error")
                .message(ex.getMessage())
                .errorCode(ex.getErrorCode())
                .details(ex.getDetails())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(
            AuthenticationException ex, WebRequest request) {
        logger.error("AuthenticationException: {} - ErrorCode: {}", ex.getMessage(), ex.getErrorCode(), ex);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.UNAUTHORIZED.value())
                .error("Authentication Error")
                .message(ex.getMessage())
                .errorCode(ex.getErrorCode())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex, WebRequest request) {
        logger.error("Validation error: {}", ex.getMessage());
        List<Map<String, String>> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> {
                    Map<String, String> fieldError = new HashMap<>();
                    fieldError.put("field", error.getField());
                    fieldError.put("message", error.getDefaultMessage());
                    fieldError.put("rejectedValue", String.valueOf(error.getRejectedValue()));
                    return fieldError;
                })
                .collect(Collectors.toList());
        ValidationErrorResponse errorResponse = ValidationErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation Failed")
                .message("Los datos proporcionados no son válidos")
                .fieldErrors(fieldErrors)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorResponse> handleConstraintViolationException(
            ConstraintViolationException ex, WebRequest request) {
        logger.error("Constraint violation: {}", ex.getMessage());
        List<Map<String, String>> violations = ex.getConstraintViolations().stream()
                .map(violation -> {
                    Map<String, String> violationMap = new HashMap<>();
                    String field = violation.getPropertyPath().toString();
                    field = field.contains(".") ? field.substring(field.lastIndexOf('.') + 1) : field;
                    violationMap.put("field", field);
                    violationMap.put("message", violation.getMessage());
                    violationMap.put("rejectedValue", String.valueOf(violation.getInvalidValue()));
                    return violationMap;
                })
                .collect(Collectors.toList());
        ValidationErrorResponse errorResponse = ValidationErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Constraint Violation")
                .message("Se violaron las restricciones de validación")
                .fieldErrors(violations)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatchException(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        logger.error("Type mismatch: {} - Required type: {}", ex.getMessage(), ex.getRequiredType(), ex);
        String message = String.format("El parámetro '%s' tiene un valor inválido: '%s'. Se esperaba un tipo %s.",
                ex.getName(), ex.getValue(), ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconocido");
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Type Mismatch")
                .message(message)
                .errorCode("INVALID_PARAMETER_TYPE")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(
            NoHandlerFoundException ex, WebRequest request) {
        logger.error("No handler found: {}", ex.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Not Found")
                .message(String.format("El recurso '%s' no existe", ex.getHttpMethod() + " " + ex.getRequestURL()))
                .errorCode("RESOURCE_NOT_FOUND")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        logger.error("Illegal argument: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message(ex.getMessage())
                .errorCode("ILLEGAL_ARGUMENT")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalStateException(
            IllegalStateException ex, WebRequest request) {
        logger.error("Illegal state: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error("Conflict State")
                .message(ex.getMessage())
                .errorCode("ILLEGAL_STATE")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        logger.error("Unexpected error: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Server Error")
                .message("Ha ocurrido un error inesperado. Por favor, contacte al administrador.")
                .errorCode("INTERNAL_ERROR")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    public static class ErrorResponse {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private String errorCode;
        private String details;
        private String path;

        private ErrorResponse() {}

        public static ErrorResponseBuilder builder() {
            return new ErrorResponseBuilder();
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public int getStatus() {
            return status;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public String getDetails() {
            return details;
        }

        public String getPath() {
            return path;
        }

        public static class ErrorResponseBuilder {
            private LocalDateTime timestamp;
            private int status;
            private String error;
            private String message;
            private String errorCode;
            private String details;
            private String path;

            public ErrorResponseBuilder timestamp(LocalDateTime timestamp) {
                this.timestamp = timestamp;
                return this;
            }

            public ErrorResponseBuilder status(int status) {
                this.status = status;
                return this;
            }

            public ErrorResponseBuilder error(String error) {
                this.error = error;
                return this;
            }

            public ErrorResponseBuilder message(String message) {
                this.message = message;
                return this;
            }

            public ErrorResponseBuilder errorCode(String errorCode) {
                this.errorCode = errorCode;
                return this;
            }

            public ErrorResponseBuilder details(String details) {
                this.details = details;
                return this;
            }

            public ErrorResponseBuilder path(String path) {
                this.path = path;
                return this;
            }

            public ErrorResponse build() {
                ErrorResponse response = new ErrorResponse();
                response.timestamp = this.timestamp;
                response.status = this.status;
                response.error = this.error;
                response.message = this.message;
                response.errorCode = this.errorCode;
                response.details = this.details;
                response.path = this.path;
                return response;
            }
        }
    }

    public static class ValidationErrorResponse {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private List<Map<String, String>> fieldErrors;
        private String path;

        private ValidationErrorResponse() {}

        public static ValidationErrorResponseBuilder builder() {
            return new ValidationErrorResponseBuilder();
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public int getStatus() {
            return status;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }

        public List<Map<String, String>> getFieldErrors() {
            return fieldErrors;
        }

        public String getPath() {
            return path;
        }

        public static class ValidationErrorResponseBuilder {
            private LocalDateTime timestamp;
            private int status;
            private String error;
            private String message;
            private List<Map<String, String>> fieldErrors;
            private String path;

            public ValidationErrorResponseBuilder timestamp(LocalDateTime timestamp) {
                this.timestamp = timestamp;
                return this;
            }

            public ValidationErrorResponseBuilder status(int status) {
                this.status = status;
                return this;
            }

            public ValidationErrorResponseBuilder error(String error) {
                this.error = error;
                return this;
            }

            public ValidationErrorResponseBuilder message(String message) {
                this.message = message;
                return this;
            }

            public ValidationErrorResponseBuilder fieldErrors(List<Map<String, String>> fieldErrors) {
                this.fieldErrors = fieldErrors;
                return this;
            }

            public ValidationErrorResponseBuilder path(String path) {
                this.path = path;
                return this;
            }

            public ValidationErrorResponse build() {
                ValidationErrorResponse response = new ValidationErrorResponse();
                response.timestamp = this.timestamp;
                response.status = this.status;
                response.error = this.error;
                response.message = this.message;
                response.fieldErrors = this.fieldErrors;
                response.path = this.path;
                return response;
            }
        }
    }
}

// === ARCHIVO: src/test/java/com/bank/transaction/application/service/TransactionServiceTest.java ===
package com.bank.transaction.application.service;

import com.bank.transaction.domain.model.Transaction;
import com.bank.transaction.domain.model.Transaction.TransactionStatus;
import com.bank.transaction.domain.model.Transaction.TransactionType;
import com.bank.transaction.domain.port.AuthenticationService;
import com.bank.transaction.domain.port.TransactionRepository;
import com.bank.transaction.infrastructure.exception.TransactionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TransactionService - Pruebas Unitarias")
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private TransactionService transactionService;

    private Transaction transaccionValida;
    private Transaction transaccionAltoValor;
    private UUID cuentaId;
    private UUID transaccionId;

    @BeforeEach
    void setUp() {
        cuentaId = UUID.randomUUID();
        transaccionId = UUID.randomUUID();
        
        transaccionValida = new Transaction(
            transaccionId,
            cuentaId,
            TransactionType.TRANSFER,
            new BigDecimal("500.00"),
            "USD",
            "CUENTA_ORIGEN",
            "CUENTA_DESTINO",
            TransactionStatus.COMPLETED,
            LocalDateTime.now(),
            "USER_001"
        );
        
        transaccionAltoValor = new Transaction(
            UUID.randomUUID(),
            cuentaId,
            TransactionType.TRANSFER,
            new BigDecimal("15000.00"),
            "USD",
            "CUENTA_ORIGEN",
            "CUENTA_DESTINO",
            TransactionStatus.PENDING,
            LocalDateTime.now(),
            "USER_001"
        );
    }

    @Nested
    @DisplayName("Crear Transacción")
    class CrearTransaccion {

        @Test
        @DisplayName("Debe crear transacción exitosamente con token válido")
        void debeCrearTransaccionExitosamente() {
            String tokenValido = "Bearer token-valido";
            when(authenticationService.validateToken(tokenValido)).thenReturn(Set.of("ROLE_USER"));
            when(transactionRepository.save(any(Transaction.class))).thenReturn(transaccionValida);

            Transaction resultado = transactionService.createTransaction(transaccionValida, tokenValido);

            assertNotNull(resultado);
            assertEquals(transaccionId, resultado.id());
            assertEquals(TransactionStatus.COMPLETED, resultado.status());
            verify(transactionRepository).save(any(Transaction.class));
            verify(authenticationService).validateToken(tokenValido);
        }

        @Test
        @DisplayName("Debe rechazar creación con token inválido")
        void debeRechazarCreacionConTokenInvalido() {
            String tokenInvalido = "Bearer token-invalido";
            when(authenticationService.validateToken(tokenInvalido)).thenReturn(Set.of());

            TransactionException exception = assertThrows(
                TransactionException.class,
                () -> transactionService.createTransaction(transaccionValida, tokenInvalido)
            );

            assertEquals("Token de autenticación inválido", exception.getMessage());
            verify(transactionRepository, never()).save(any(Transaction.class));
        }

        @Test
        @DisplayName("Debe rechazar creación sin token")
        void debeRechazarCreacionSinToken() {
            TransactionException exception = assertThrows(
                TransactionException.class,
                () -> transactionService.createTransaction(transaccionValida, null)
            );

            assertEquals("Token de autenticación requerido", exception.getMessage());
            verify(transactionRepository, never()).save(any(Transaction.class));
        }

        @Test
        @DisplayName("Debe rechazar creación con monto negativo")
        void debeRechazarCreacionConMontoNegativo() {
            Transaction transaccionMontoNegativo = new Transaction(
                transaccionId,
                cuentaId,
                TransactionType.TRANSFER,
                new BigDecimal("-100.00"),
                "USD",
                "CUENTA_ORIGEN",
                "CUENTA_DESTINO",
                TransactionStatus.PENDING,
                LocalDateTime.now(),
                "USER_001"
            );
            String tokenValido = "Bearer token-valido";
            when(authenticationService.validateToken(tokenValido)).thenReturn(Set.of("ROLE_USER"));

            TransactionException exception = assertThrows(
                TransactionException.class,
                () -> transactionService.createTransaction(transaccionMontoNegativo, tokenValido)
            );

            assertEquals("El monto de la transacción debe ser positivo", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Obtener Transacción por ID")
    class ObtenerTransaccionPorId {

        @Test
        @DisplayName("Debe obtener transacción existente")
        void debeObtenerTransaccionExistente() {
            String tokenValido = "Bearer token-valido";
            when(authenticationService.validateToken(tokenValido)).thenReturn(Set.of("ROLE_USER"));
            when(transactionRepository.findById(transaccionId)).thenReturn(Optional.of(transaccionValida));

            Optional<Transaction> resultado = transactionService.getTransactionById(transaccionId, tokenValido);

            assertTrue(resultado.isPresent());
            assertEquals(transaccionId, resultado.get().id());
            verify(transactionRepository).findById(transaccionId);
        }

        @Test
        @DisplayName("Debe retornar vacío para transacción no existente")
        void debeRetornarVacioParaTransaccionNoExistente() {
            UUID idNoExistente = UUID.randomUUID();
            String tokenValido = "Bearer token-valido";
            when(authenticationService.validateToken(tokenValido)).thenReturn(Set.of("ROLE_USER"));
            when(transactionRepository.findById(idNoExistente)).thenReturn(Optional.empty());

            Optional<Transaction> resultado = transactionService.getTransactionById(idNoExistente, tokenValido);

            assertTrue(resultado.isEmpty());
        }
    }

    @Nested
    @DisplayName("Obtener Transacciones por Cuenta")
    class ObtenerTransaccionesPorCuenta {

        @Test
        @DisplayName("Debe obtener todas las transacciones de una cuenta")
        void debeObtenerTransaccionesDeCuenta() {
            String tokenValido = "Bearer token-valido";
            List<Transaction> transacciones = List.of(transaccionValida, transaccionAltoValor);
            when(authenticationService.validateToken(tokenValido)).thenReturn(Set.of("ROLE_USER"));
            when(transactionRepository.findByAccountId(cuentaId)).thenReturn(transacciones);

            List<Transaction> resultado = transactionService.getTransactionsByAccount(cuentaId, tokenValido);

            assertEquals(2, resultado.size());
            verify(transactionRepository).findByAccountId(cuentaId);
        }

        @Test
        @DisplayName("Debe obtener transacciones por rango de fechas")
        void debeObtenerTransaccionesPorRangoDeFechas() {
            String tokenValido = "Bearer token-valido";
            LocalDateTime inicio = LocalDateTime.now().minusDays(7);
            LocalDateTime fin = LocalDateTime.now();
            List<Transaction> transacciones = List.of(transaccionValida);
            when(authenticationService.validateToken(tokenValido)).thenReturn(Set.of("ROLE_USER"));
            when(transactionRepository.findByAccountIdAndDateRange(cuentaId, inicio, fin)).thenReturn(transacciones);

            List<Transaction> resultado = transactionService.getTransactionsByAccountAndDateRange(
                cuentaId, inicio, fin, tokenValido
            );

            assertEquals(1, resultado.size());
            verify(transactionRepository).findByAccountIdAndDateRange(cuentaId, inicio, fin);
        }
    }

    @Nested
    @DisplayName("Transacciones de Alto Valor")
    class TransaccionesAltoValor {

        @Test
        @DisplayName("Debe identificar transacciones de alto valor")
        void debeIdentificarTransaccionesDeAltoValor() {
            String tokenValido = "Bearer token-valido";
            when(authenticationService.validateToken(tokenValido)).thenReturn(Set.of("ROLE_ADMIN"));
            when(transactionRepository.findHighValueTransactions()).thenReturn(List.of(transaccionAltoValor));

            List<Transaction> resultado = transactionService.getHighValueTransactions(tokenValido);

            assertEquals(1, resultado.size());
            assertTrue(resultado.get(0).isHighValue());
            verify(transactionRepository).findHighValueTransactions();
        }

        @Test
        @DisplayName("Debe rechazar acceso a transacciones de alto valor sin rol admin")
        void debeRechazarAccesoSinRolAdmin() {
            String tokenUsuario = "Bearer token-usuario";
            when(authenticationService.validateToken(tokenUsuario)).thenReturn(Set.of("ROLE_USER"));
            when(authenticationService.hasRole(tokenUsuario, "ROLE_ADMIN")).thenReturn(false);

            TransactionException exception = assertThrows(
                TransactionException.class,
                () -> transactionService.getHighValueTransactions(tokenUsuario)
            );

            assertEquals("Acceso denegado. Se requiere rol ADMIN", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Validación de Roles y Permisos")
    class ValidacionRolesYPermisos {

        @Test
        @DisplayName("Debe verificar que usuario tiene acceso a sus propias transacciones")
        void debeVerificarAccesoATransaccionesPropias() {
            String tokenValido = "Bearer token-valido";
            when(authenticationService.validateToken(tokenValido)).thenReturn(Set.of("ROLE_USER"));
            when(transactionRepository.findById(transaccionId)).thenReturn(Optional.of(transaccionValida));

            boolean tieneAcceso = transactionService.hasAccessToTransaction(transaccionId, tokenValido, "USER_001");

            assertTrue(tieneAcceso);
        }

        @Test
        @DisplayName("Debe denegar acceso a transacciones de otro usuario")
        void debeDenegarAccesoATransaccionesDeOtroUsuario() {
            String tokenValido = "Bearer token-valido";
            when(authenticationService.validateToken(tokenValido)).thenReturn(Set.of("ROLE_USER"));
            when(transactionRepository.findById(transaccionId)).thenReturn(Optional.of(transaccionValida));

            boolean tieneAcceso = transactionService.hasAccessToTransaction(transaccionId, tokenValido, "USER_OTRO");

            assertFalse(tieneAcceso);
        }

        @Test
        @DisplayName("Admin debe poder acceder a cualquier transacción")
        void adminDebePoderAccederCualquerTransaccion() {
            String tokenAdmin = "Bearer token-admin";
            when(authenticationService.validateToken(tokenAdmin)).thenReturn(Set.of("ROLE_ADMIN"));
            when(authenticationService.hasRole(tokenAdmin, "ROLE_ADMIN")).thenReturn(true);
            when(transactionRepository.findById(transaccionId)).thenReturn(Optional.of(transaccionValida));

            boolean tieneAcceso = transactionService.hasAccessToTransaction(transaccionId, tokenAdmin, "USER_OTRO");

            assertTrue(tieneAcceso);
        }
    }

    @Nested
    @DisplayName("Auditoría de Transacciones")
    class AuditoriaTransacciones {

        @Test
        @DisplayName("Debe marcar transacción de alto valor para auditoría")
        void debeMarcarTransaccionAltoValorParaAuditoria() {
            assertTrue(transaccionAltoValor.requiresAudit());
        }

        @Test
        @DisplayName("Debe marcar transacción de transferencia internacional para auditoría")
        void debeMarcarTransaccionInternacionalParaAuditoria() {
            Transaction transaccionInternacional = new Transaction(
                UUID.randomUUID(),
                cuentaId,
                TransactionType.INTERNATIONAL_TRANSFER,
                new BigDecimal("1000.00"),
                "USD",
                "CUENTA_ORIGEN",
                "CUENTA_DESTINO_INTERNACIONAL",
                TransactionStatus.PENDING,
                LocalDateTime.now(),
                "USER_001"
            );

            assertTrue(transaccionInternacional.requiresAudit());
        }

        @Test
        @DisplayName("Transacción normal no requiere auditoría")
        void transaccionNormalNoRequiereAuditoria() {
            assertFalse(transaccionValida.requiresAudit());
        }
    }
}

// === ARCHIVO: src/test/java/com/bank/transaction/infrastructure/controller/TransactionControllerTest.java ===
package com.bank.transaction.infrastructure.controller;

import com.bank.transaction.application.service.TransactionService;
import com.bank.transaction.domain.model.Transaction;
import com.bank.transaction.domain.model.Transaction.TransactionStatus;
import com.bank.transaction.domain.model.Transaction.TransactionType;
import com.bank.transaction.infrastructure.exception.AuthenticationException;
import com.bank.transaction.infrastructure.exception.TransactionException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
@DisplayName("TransactionController - Pruebas de Integración")
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransactionService transactionService;

    private Transaction transaccionValida;
    private Transaction transaccionAltoValor;
    private UUID cuentaId;
    private UUID transaccionId;

    @BeforeEach
    void setUp() {
        cuentaId = UUID.randomUUID();
        transaccionId = UUID.randomUUID();
        
        transaccionValida = new Transaction(
            transaccionId,
            cuentaId,
            TransactionType.TRANSFER,
            new BigDecimal("500.00"),
            "USD",
            "CUENTA_ORIGEN",
            "CUENTA_DESTINO",
            TransactionStatus.COMPLETED,
            LocalDateTime.now(),
            "USER_001"
        );
        
        transaccionAltoValor = new Transaction(
            UUID.randomUUID(),
            cuentaId,
            TransactionType.TRANSFER,
            new BigDecimal("15000.00"),
            "USD",
            "CUENTA_ORIGEN",
            "CUENTA_DESTINO",
            TransactionStatus.PENDING,
            LocalDateTime.now(),
            "USER_001"
        );
    }

    @Nested
    @DisplayName("POST /api/v1/transactions - Crear Transacción")
    class CrearTransaccion {

        @Test
        @DisplayName("Debe crear transacción exitosamente con usuario autenticado")
        @WithMockUser(roles = "USER")
        void debeCrearTransaccionExitosamente() throws Exception {
            when(transactionService.createTransaction(any(Transaction.class), anyString()))
                .thenReturn(transaccionValida);

            mockMvc.perform(post("/api/v1/transactions")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(transaccionValida)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(transaccionId.toString()))
                .andExpect(jsonPath("$.status").value("COMPLETED"));
        }

        @Test
        @DisplayName("Debe rechazar creación sin autenticación")
        void debeRechazarCreacionSinAutenticacion() throws Exception {
            when(transactionService.createTransaction(any(Transaction.class), anyString()))
                .thenThrow(new AuthenticationException("Token requerido"));

            mockMvc.perform(post("/api/v1/transactions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(transaccionValida)))
                .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName("Debe rechazar creación con token inválido")
        @WithMockUser(roles = "USER")
        void debeRechazarCreacionConTokenInvalido() throws Exception {
            when(transactionService.createTransaction(any(Transaction.class), anyString()))
                .thenThrow(new TransactionException("Token de autenticación inválido"));

            mockMvc.perform(post("/api/v1/transactions")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(transaccionValida)))
                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debe rechazar creación con monto inválido")
        @WithMockUser(roles = "USER")
        void debeRechazarCreacionConMontoInvalido() throws Exception {
            Transaction transaccionInvalida = new Transaction(
                transaccionId,
                cuentaId,
                TransactionType.TRANSFER,
                new BigDecimal("-100.00"),
                "USD",
                "CUENTA_ORIGEN",
                "CUENTA_DESTINO",
                TransactionStatus.PENDING,
                LocalDateTime.now(),
                "USER_001"
            );

            when(transactionService.createTransaction(any(Transaction.class), anyString()))
                .thenThrow(new TransactionException("El monto de la transacción debe ser positivo"));

            mockMvc.perform(post("/api/v1/transactions")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(transaccionInvalida)))
                .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("GET /api/v1/transactions/{id} - Obtener Transacción por ID")
    class ObtenerTransaccionPorId {

        @Test
        @DisplayName("Debe obtener transacción existente")
        @WithMockUser(roles = "USER")
        void debeObtenerTransaccionExistente() throws Exception {
            when(transactionService.getTransactionById(eq(transaccionId), anyString()))
                .thenReturn(Optional.of(transaccionValida));

            mockMvc.perform(get("/api/v1/transactions/{id}", transaccionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(transaccionId.toString()))
                .andExpect(jsonPath("$.amount").value(500.00));
        }

        @Test
        @DisplayName("Debe retornar 404 para transacción no encontrada")
        @WithMockUser(roles = "USER")
        void debeRetornar404ParaTransaccionNoEncontrada() throws Exception {
            UUID idNoExistente = UUID.randomUUID();
            when(transactionService.getTransactionById(eq(idNoExistente), anyString()))
                .thenReturn(Optional.empty());

            mockMvc.perform(get("/api/v1/transactions/{id}", idNoExistente))
                .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("GET /api/v1/transactions/account/{accountId} - Obtener Transacciones por Cuenta")
    class ObtenerTransaccionesPorCuenta {

        @Test
        @DisplayName("Debe obtener todas las transacciones de una cuenta")
        @WithMockUser(roles = "USER")
        void debeObtenerTransaccionesDeCuenta() throws Exception {
            List<Transaction> transacciones = List.of(transaccionValida, transaccionAltoValor);
            when(transactionService.getTransactionsByAccount(eq(cuentaId), anyString()))
                .thenReturn(transacciones);

            mockMvc.perform(get("/api/v1/transactions/account/{accountId}", cuentaId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(transaccionId.toString()));
        }

        @Test
        @DisplayName("Debe obtener transacciones filtradas por rango de fechas")
        @WithMockUser(roles = "USER")
        void debeObtenerTransaccionesPorRangoDeFechas() throws Exception {
            LocalDateTime inicio = LocalDateTime.now().minusDays(7);
            LocalDateTime fin = LocalDateTime.now();
            List<Transaction> transacciones = List.of(transaccionValida);
            
            when(transactionService.getTransactionsByAccountAndDateRange(
                eq(cuentaId), eq(inicio), eq(fin), anyString()
            )).thenReturn(transacciones);

            mockMvc.perform(get("/api/v1/transactions/account/{accountId}", cuentaId)
                    .param("startDate", inicio.toString())
                    .param("endDate", fin.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
        }
    }

    @Nested
    @DisplayName("GET /api/v1/transactions/high-value - Transacciones de Alto Valor")
    class TransaccionesAltoValor {

        @Test
        @DisplayName("Debe permitir acceso a admin para transacciones de alto valor")
        @WithMockUser(roles = "ADMIN")
        void debePermitirAccesoAAdmin() throws Exception {
            List<Transaction> transacciones = List.of(transaccionAltoValor);
            when(transactionService.getHighValueTransactions(anyString()))
                .thenReturn(transacciones);

            mockMvc.perform(get("/api/v1/transactions/high-value"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].amount").value(15000.00));
        }

        @Test
        @DisplayName("Debe rechazar acceso de usuario regular a transacciones de alto valor")
        @WithMockUser(roles = "USER")
        void debeRechazarAccesoDeUsuarioRegular() throws Exception {
            when(transactionService.getHighValueTransactions(anyString()))
                .thenThrow(new TransactionException("Acceso denegado. Se requiere rol ADMIN"));

            mockMvc.perform(get("/api/v1/transactions/high-value"))
                .andExpect(status().isForbidden());
        }
    }

    @Nested
    @DisplayName("Manejo de Errores Global")
    class ManejoErrores {

        @Test
        @DisplayName("Debe manejar error de autenticación")
        @WithMockUser(roles = "USER")
        void debeManejarErrorDeAutenticacion() throws Exception {
            when(transactionService.createTransaction(any(Transaction.class), anyString()))
                .thenThrow(new AuthenticationException("Credenciales inválidas"));

            mockMvc.perform(post("/api/v1/transactions")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(transaccionValida)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").exists());
        }

        @Test
        @DisplayName("Debe manejar error de transacción")
        @WithMockUser(roles = "USER")
        void debeManejarErrorDeTransaccion() throws Exception {
            when(transactionService.createTransaction(any(Transaction.class), anyString()))
                .thenThrow(new TransactionException("Error al procesar transacción"));

            mockMvc.perform(post("/api/v1/transactions")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(transaccionValida)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
        }

        @Test
        @DisplayName("Debe manejar error interno del servidor")
        @WithMockUser(roles = "USER")
        void debeManejarErrorInternoDelServidor() throws Exception {
            when(transactionService.getTransactionById(any(UUID.class), anyString()))
                .thenThrow(new RuntimeException("Error interno"));

            mockMvc.perform(get("/api/v1/transactions/{id}", UUID.randomUUID()))
                .andExpect(status().isInternalServerError());
        }
    }

    @Nested
    @DisplayName("Validación de Request Body")
    class ValidacionRequestBody {

        @Test
        @DisplayName("Debe rechazar request con accountId nulo")
        @WithMockUser(roles = "USER")
        void debeRechazarRequestConAccountIdNulo() throws Exception {
            Transaction transaccionSinCuenta = new Transaction(
                transaccionId,
                null,
                TransactionType.TRANSFER,
                new BigDecimal("500.00"),
                "USD",
                "CUENTA_ORIGEN",
                "CUENTA_DESTINO",
                TransactionStatus.PENDING,
                LocalDateTime.now(),
                "USER_001"
            );

            mockMvc.perform(post("/api/v1/transactions")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(transaccionSinCuenta)))
                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debe rechazar request con tipo de transacción nulo")
        @WithMockUser(roles = "USER")
        void debeRechazarRequestConTipoNulo() throws Exception {
            Transaction transaccionSinTipo = new Transaction(
                transaccionId,
                cuentaId,
                null,
                new BigDecimal("500.00"),
                "USD",
                "CUENTA_ORIGEN",
                "CUENTA_DESTINO",
                TransactionStatus.PENDING,
                LocalDateTime.now(),
                "USER_001"
            );

            mockMvc.perform(post("/api/v1/transactions")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(transaccionSinTipo)))
                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debe rechazar request con currency vacía")
        @WithMockUser(roles = "USER")
        void debeRechazarRequestConCurrencyVacia() throws Exception {
            Transaction transaccionSinCurrency = new Transaction(
                transaccionId,
                cuentaId,
                TransactionType.TRANSFER,
                new BigDecimal("500.00"),
                "",
                "CUENTA_ORIGEN",
                "CUENTA_DESTINO",
                TransactionStatus.PENDING,
                LocalDateTime.now(),
                "USER_001"
            );

            mockMvc.perform(post("/api/v1/transactions")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(transaccionSinCurrency)))
                .andExpect(status().isBadRequest());
        }
    }
}
```
