package modelo;


public class Curso {

    /** Clave única del curso, por ejemplo "ISW-301". */
    private String clave;

    /** Nombre del curso. */
    private String nombre;

    /** Costo de inscripción del curso. */
    private double costo;

    /**
     * Crea un curso con sus datos.
     *
     * @param clave  clave del curso
     * @param nombre nombre del curso
     * @param costo  costo del curso
     */
    public Curso(String clave, String nombre, double costo) {
        this.clave = clave;
        this.nombre = nombre;
        this.costo = costo;
    }

    public String getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public double getCosto() {
        return costo;
    }

    @Override
    public String toString() {
        return String.format("%s  %s  -  $%,.2f", clave, nombre, costo);
    }
}
