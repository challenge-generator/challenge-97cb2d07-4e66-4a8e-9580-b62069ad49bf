# Optimización de Framework y Seguridad en Aplicación Backend

El candidato, un desarrollador backend avanzado, debe optimizar la estructura de una aplicación Java existente para mejorar la legibilidad del código y reducir la redundancia. La aplicación, utilizada en un entorno bancario para procesar transacciones, requiere la implementación de mecanismos de autenticación robustos. Los actores clave incluyen el'motor de transacciones', el'servicio de autenticación' y el'sistema de auditoría'. La aplicación debe manejar 10 000 transacciones por segundo con un SLA del 99.9% y garantizar la trazabilidad de las transacciones durante 5 años. El candidato debe identificar y corregir las deficiencias en la seguridad y la estructura del código, implementando al menos dos mecanismos de autenticación y utilizando JWT para la gestión de sesiones.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Especialista en Framework con Proficiencia en Seguridad y Legibilidad de Código |
| **Nivel** | advanced-l3 |
| **Tipo** | mixed |
| **Tiempo estimado** | 3 semanas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Análisis de la Estructura Actual

**Objetivo:** Identificar las deficiencias en la estructura del código y la seguridad de la aplicación

**Tiempo estimado:** 1 semana

**Instrucciones:**

- Revisar la estructura actual del código para identificar redundancias y áreas de mejora en la legibilidad.
- Evaluar los mecanismos de autenticación existentes y determinar si cumplen con los estándares de seguridad requeridos.

**Entregable:** Informe detallado de las deficiencias encontradas y recomendaciones para mejorar la estructura y seguridad del código.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la separación de preocupaciones y el uso de patrones de diseño para mejorar la legibilidad.
- Investiga los estándares de seguridad actuales y cómo se aplican en aplicaciones similares.

</details>

### Fase 2: Implementación de Mecanismos de Autenticación

**Objetivo:** Implementar al menos dos mecanismos de autenticación y utilizar JWT para la gestión de sesiones

**Tiempo estimado:** 1 semana

**Instrucciones:**

- Diseñar y aplicar al menos dos mecanismos de autenticación robustos.
- Incorporar el uso de JWT para la gestión segura de sesiones de usuario.

**Entregable:** Código implementado con los nuevos mecanismos de autenticación y JWT, junto con documentación que explique las decisiones tomadas.

<details>
<summary>Pistas de conocimiento</summary>

- Explora diferentes tipos de autenticación (por ejemplo, autenticación básica, OAuth) y elige las más adecuadas para el contexto.
- Investiga sobre JWT y cómo implementarlo de manera segura en una aplicación Java.

</details>

### Fase 3: Refactorización y Optimización del Código

**Objetivo:** Refactorizar el código para mejorar la legibilidad y reducir la redundancia

**Tiempo estimado:** 1 semana

**Instrucciones:**

- Refactorizar el código siguiendo los patrones de diseño identificados en la fase 1.
- Eliminar redundancias y mejorar la legibilidad del código.

**Entregable:** Código refactorizado con mejoras en la legibilidad y reducción de redundancias, junto con documentación que explique los cambios realizados.

<details>
<summary>Pistas de conocimiento</summary>

- Aplica principios de programación limpia y patrones de diseño comunes en Java.
- Utiliza herramientas de análisis de código para identificar y corregir problemas de legibilidad y redundancia.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son los mecanismos de autenticación y por qué son importantes en una aplicación backend?
- **paraQueSirve**: ¿Para qué sirve utilizar JWT en la gestión de sesiones de usuario?
- **comoSeUsa**: ¿Cómo se aplican los patrones de diseño para mejorar la legibilidad del código?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar mecanismos de autenticación y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones implica la elección de un mecanismo de autenticación sobre otro en un contexto de seguridad?

## Criterios de Evaluacion

- Identificación correcta de las deficiencias en la estructura y seguridad del código.
- Implementación efectiva de al menos dos mecanismos de autenticación.
- Uso adecuado de JWT para la gestión de sesiones.
- Refactorización del código que mejora la legibilidad y reduce la redundancia.
- Documentación clara y completa que explica los cambios y decisiones tomadas.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
