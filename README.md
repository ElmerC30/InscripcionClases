# InscripcionClases – CU-07 Inscribirse en clases

Examen de la Primera Unidad de Diseño de Software (ITSON).
Autor: Jesús Elmer Cantúa Domínguez (00000266938).

Aplicación Java Swing con arquitectura **MVC** y patrón **Observer**. Usa datos en memoria (sin base de datos).

## Paquetes
| Paquete | Clases | Rol |
|---|---|---|
| `modelo` | `ModeloInscripcion`, `Curso`, `FichaPago`, `ISujeto`, `IObservador` | Estado y reglas; es el *sujeto* del Observer |
| `vista` | `FrmInscripcion` | Pantalla Swing; es el *observador* |
| `controlador` | `ControlInscripcion` | Recibe las acciones de la vista y modifica el modelo |
| `main` | `Main` | Crea y conecta Modelo, Vista y Controlador |

## Ejecutar
- **NetBeans:** *Open Project* → seleccionar esta carpeta → *Run*.
- **Maven:**

  ```bash
  mvn compile exec:java
  ```
- **Sin Maven:**

  ```bash
  javac -d out $(find src/main/java -name "*.java") && java -cp out main.Main
  ```
