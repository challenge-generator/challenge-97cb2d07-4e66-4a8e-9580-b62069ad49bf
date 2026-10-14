# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Optimización de Framework y Seguridad en Aplicación Backend**.

| | |
|---|---|
| Tema | Especialista en Framework con Proficiencia en Seguridad y Legibilidad de Código |
| Nivel | advanced-l3 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java 21 / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean con capas de dominio, aplicación e infraestructura |
| Tiempo estimado | 3 semanas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web n/a
- org.springframework.boot:spring-boot-starter-data-jpa n/a
- org.springframework.boot:spring-boot-starter-security n/a
- org.springframework.boot:spring-boot-starter-validation n/a
- io.jsonwebtoken:jjwt-api 0.12.5
- io.jsonwebtoken:jjwt-impl 0.12.5
- io.jsonwebtoken:jjwt-jackson 0.12.5
- org.springdoc:springdoc-openapi-starter-webmvc-ui 2.6.0
- org.postgresql:postgresql n/a
- org.projectlombok:lombok n/a
- org.springframework.boot:spring-boot-starter-test n/a
- org.springframework.security:spring-security-test n/a

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Análisis de la Estructura Actual**: Informe detallado de las deficiencias encontradas y recomendaciones para mejorar la estructura y seguridad del código.
- **Fase 2 — Implementación de Mecanismos de Autenticación**: Código implementado con los nuevos mecanismos de autenticación y JWT, junto con documentación que explique las decisiones tomadas.
- **Fase 3 — Refactorización y Optimización del Código**: Código refactorizado con mejoras en la legibilidad y reducción de redundancias, junto con documentación que explique los cambios realizados.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/main/java/com/bank/transaction/domain/port/AuthenticationService.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- [ ] `src/main/java/com/bank/transaction/infrastructure/adapter/JwtAuthenticationService.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- [ ] `src/main/java/com/bank/transaction/infrastructure/security/SecurityConfig.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- [ ] `src/main/java/com/bank/transaction/infrastructure/security/JwtTokenUtil.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- [ ] `src/main/java/com/bank/transaction/infrastructure/security/JwtAuthenticationFilter.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- [ ] `src/main/java/com/bank/transaction/infrastructure/adapter/OAuth2AuthenticationService.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- [ ] `src/main/java/com/bank/transaction/infrastructure/exception/AuthenticationException.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (49)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/bank/transaction/TransactionApplication.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.models.OpenAPI pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/transaction/application/service/TransactionService.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/transaction/infrastructure/config/OpenApiConfig.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/transaction/infrastructure/exception/GlobalExceptionHandler.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/transaction/infrastructure/adapter/JpaTransactionRepository.java` — `SpringDataTransactionRepository.findByAccountIdOrderByTimestampDesc`
      Se invoca `findByAccountIdOrderByTimestampDesc` sobre `SpringDataTransactionRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/infrastructure/adapter/JpaTransactionRepository.java` — `SpringDataTransactionRepository.findByAccountIdAndTimestampBetweenOrderByTimestampDesc`
      Se invoca `findByAccountIdAndTimestampBetweenOrderByTimestampDesc` sobre `SpringDataTransactionRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/infrastructure/adapter/JpaTransactionRepository.java` — `SpringDataTransactionRepository.findByAmountGreaterThanOrderByTimestampDesc`
      Se invoca `findByAmountGreaterThanOrderByTimestampDesc` sobre `SpringDataTransactionRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/application/service/TransactionService.java` — `Transaction.id`
      Se invoca `id` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/application/service/TransactionService.java` — `Transaction.amount`
      Se invoca `amount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/application/service/TransactionService.java` — `Transaction.isPresent`
      Se invoca `isPresent` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/application/service/TransactionService.java` — `Transaction.get`
      Se invoca `get` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/application/service/TransactionService.java` — `Transaction.accountId`
      Se invoca `accountId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/application/service/TransactionService.java` — `Transaction.type`
      Se invoca `type` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/application/service/TransactionService.java` — `Transaction.description`
      Se invoca `description` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/application/service/TransactionService.java` — `Transaction.timestamp`
      Se invoca `timestamp` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/application/service/TransactionService.java` — `Transaction.authToken`
      Se invoca `authToken` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `UpdateStatusRequest.accountId`
      Se invoca `accountId` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `UpdateStatusRequest.amount`
      Se invoca `amount` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `UpdateStatusRequest.type`
      Se invoca `type` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `UpdateStatusRequest.description`
      Se invoca `description` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `Transaction.id`
      Se invoca `id` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `Transaction.accountId`
      Se invoca `accountId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `Transaction.amount`
      Se invoca `amount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `Transaction.type`
      Se invoca `type` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `Transaction.description`
      Se invoca `description` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `UpdateStatusRequest.status`
      Se invoca `status` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `Transaction.createdAt`
      Se invoca `createdAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `Transaction.auditTrail`
      Se invoca `auditTrail` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `Transaction.status`
      Se invoca `status` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java` — `Transaction.updatedAt`
      Se invoca `updatedAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/infrastructure/security/JwtAuthenticationFilter.java` — `JwtTokenUtil.extractUsername`
      Se invoca `extractUsername` sobre `JwtTokenUtil`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/infrastructure/security/JwtAuthenticationFilter.java` — `JwtTokenUtil.extractRoles`
      Se invoca `extractRoles` sobre `JwtTokenUtil`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/infrastructure/adapter/OAuth2AuthenticationService.java` — `OAuth2Token.expiresAt`
      Se invoca `expiresAt` sobre `OAuth2Token`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transaction/infrastructure/adapter/OAuth2AuthenticationService.java` — `OAuth2Token.roles`
      Se invoca `roles` sobre `OAuth2Token`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/transaction/application/service/TransactionServiceTest.java` — `Transaction.id`
      Se invoca `id` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/transaction/application/service/TransactionServiceTest.java` — `Transaction.status`
      Se invoca `status` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/transaction/application/service/TransactionServiceTest.java` — `TransactionException.getMessage`
      Se invoca `getMessage` sobre `TransactionException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/transaction/application/service/TransactionServiceTest.java` — `TransactionService.getTransactionById`
      Se invoca `getTransactionById` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/transaction/application/service/TransactionServiceTest.java` — `Transaction.isPresent`
      Se invoca `isPresent` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/transaction/application/service/TransactionServiceTest.java` — `Transaction.get`
      Se invoca `get` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/transaction/application/service/TransactionServiceTest.java` — `Transaction.isEmpty`
      Se invoca `isEmpty` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/transaction/application/service/TransactionServiceTest.java` — `TransactionService.getTransactionsByAccount`
      Se invoca `getTransactionsByAccount` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/transaction/application/service/TransactionServiceTest.java` — `Transaction.size`
      Se invoca `size` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/transaction/application/service/TransactionServiceTest.java` — `TransactionService.getTransactionsByAccountAndDateRange`
      Se invoca `getTransactionsByAccountAndDateRange` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/transaction/application/service/TransactionServiceTest.java` — `TransactionService.hasAccessToTransaction`
      Se invoca `hasAccessToTransaction` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/transaction/infrastructure/controller/TransactionControllerTest.java` — `TransactionService.getTransactionById`
      Se invoca `getTransactionById` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/transaction/infrastructure/controller/TransactionControllerTest.java` — `TransactionService.getTransactionsByAccount`
      Se invoca `getTransactionsByAccount` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/transaction/infrastructure/controller/TransactionControllerTest.java` — `TransactionService.getTransactionsByAccountAndDateRange`
      Se invoca `getTransactionsByAccountAndDateRange` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (20)

- `pom.xml`
- `src/main/java/com/bank/transaction/TransactionApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/bank/transaction/domain/model/Transaction.java`
- `src/main/java/com/bank/transaction/domain/port/TransactionRepository.java`
- `src/main/java/com/bank/transaction/domain/port/AuthenticationService.java`
- `src/main/java/com/bank/transaction/infrastructure/adapter/JpaTransactionRepository.java`
- `src/main/java/com/bank/transaction/application/service/TransactionService.java`
- `src/main/java/com/bank/transaction/infrastructure/adapter/JwtAuthenticationService.java`
- `src/main/java/com/bank/transaction/infrastructure/controller/TransactionController.java`
- `src/main/java/com/bank/transaction/infrastructure/security/SecurityConfig.java`
- `src/main/java/com/bank/transaction/infrastructure/security/JwtTokenUtil.java`
- `src/main/java/com/bank/transaction/infrastructure/security/JwtAuthenticationFilter.java`
- `src/main/java/com/bank/transaction/infrastructure/config/OpenApiConfig.java`
- `src/main/java/com/bank/transaction/infrastructure/adapter/OAuth2AuthenticationService.java`
- `src/main/java/com/bank/transaction/infrastructure/exception/TransactionException.java`
- `src/main/java/com/bank/transaction/infrastructure/exception/AuthenticationException.java`
- `src/main/java/com/bank/transaction/infrastructure/exception/GlobalExceptionHandler.java`
- `src/test/java/com/bank/transaction/application/service/TransactionServiceTest.java`
- `src/test/java/com/bank/transaction/infrastructure/controller/TransactionControllerTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/bank/transaction`
- `src/main/java/com/bank/transaction/domain`
- `src/main/java/com/bank/transaction/domain/model`
- `src/main/java/com/bank/transaction/domain/port`
- `src/main/java/com/bank/transaction/application`
- `src/main/java/com/bank/transaction/application/service`
- `src/main/java/com/bank/transaction/infrastructure`
- `src/main/java/com/bank/transaction/infrastructure/adapter`
- `src/main/java/com/bank/transaction/infrastructure/config`
- `src/main/java/com/bank/transaction/infrastructure/controller`
- `src/main/java/com/bank/transaction/infrastructure/security`
- `src/main/resources`
- `src/test/java/com/bank/transaction`

## Verificacion

```bash
mvn clean compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean con capas de dominio, aplicación e infraestructura**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced
- Brecha que el reto ataca: Experto en el marco de trabajo (framework) mas relevante en su lenguaje de programación principal, lo que le permite escribir código más legible y reducir la redundancia. Ha implementado al menos dos mecanismos de autenticación e identifica el uso de un JWT
- Mision: Candidato con experiencia avanzada en Backend Java, trabajando en contextos de seguridad y arquitectura limpia.

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
