package controlador;

import modelo.Curso;
import modelo.ModeloInscripcion;

/**
 * CONTROLADOR del patrón MVC.
 */
public class ControlInscripcion {

    /** Modelo que el controlador modifica. */
    private ModeloInscripcion modelo;

    /**
     * @param modelo modelo con el que trabaja el controlador
     */
    public ControlInscripcion(ModeloInscripcion modelo) {
        this.modelo = modelo;
    }

    /**
     * Arranque del caso de uso: pide al modelo que cargue los cursos.
     */
    public void iniciarInscripcion() {
        modelo.cargarCursos();
    }

    /**
     * El alumno seleccionó un curso y presionó "Inscribir".
     *
     * @param curso curso seleccionado en la vista
     */
    public void inscribirCurso(Curso curso) {
        if (curso != null) {
            modelo.inscribirCurso(curso);
        }
    }

    /**
     * El alumno presionó "Finalizar inscripción".
     */
    public void finalizarInscripcion() {
        modelo.finalizarInscripcion();
    }
}
