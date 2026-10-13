package modelo;

import java.util.List;

/**
 * Ficha de pago que se genera al finalizar la inscripción.
 * Contiene los cursos inscritos (con su costo) y el costo total.
 * La crea el modelo en finalizarInscripcion().
 */
public class FichaPago {

    /** Cursos que quedaron inscritos. */
    private List<Curso> cursos;

    /** Suma de los costos de todos los cursos inscritos. */
    private double total;

    /**
     * Crea la ficha de pago.
     *
     * @param cursos cursos inscritos
     * @param total  costo total a pagar
     */
    public FichaPago(List<Curso> cursos, double total) {
        this.cursos = cursos;
        this.total = total;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public double getTotal() {
        return total;
    }
}
