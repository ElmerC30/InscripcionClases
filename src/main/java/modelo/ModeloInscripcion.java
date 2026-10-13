package modelo;

import java.util.ArrayList;
import java.util.List;

public class ModeloInscripcion implements ISujeto {

    /** Cursos que se muestran en la lista de la izquierda. */
    private List<Curso> cursosDisponibles;

    /** Cursos que el alumno ya inscribió (lista de la derecha). */
    private List<Curso> cursosInscritos;

    /** Ficha de pago; se crea al finalizar la inscripción. */
    private FichaPago fichaPago;

    /** Observadores registrados (en este proyecto: la vista). */
    private List<IObservador> observadores;

    /**
     * Crea el modelo con las listas vacías.
     * Los cursos se cargan después con cargarCursos().
     */
    public ModeloInscripcion() {
        cursosDisponibles = new ArrayList<>();
        cursosInscritos = new ArrayList<>();
        fichaPago = null;
        observadores = new ArrayList<>();
    }

    public void cargarCursos() {
        cursosDisponibles.clear();
        cursosInscritos.clear();
        fichaPago = null;

        cursosDisponibles.add(new Curso("ISW-101", "Programación Orientada a Objetos", 2450.00));
        cursosDisponibles.add(new Curso("ISW-202", "Estructura de Datos", 2300.00));
        cursosDisponibles.add(new Curso("ISW-301", "Diseño de Software", 2500.00));
        cursosDisponibles.add(new Curso("ISW-302", "Bases de Datos", 2400.00));
        cursosDisponibles.add(new Curso("ISW-303", "Ingeniería de Requerimientos", 2150.00));
        cursosDisponibles.add(new Curso("ISW-401", "Redes de Computadoras", 2200.00));

        notificarObservadores();
    }

    /**
     * Inscribe un curso: lo quita de los disponibles y lo agrega a los
     * inscritos. Después notifica a la vista.
     *
     * @param curso curso seleccionado por el alumno
     */
    public void inscribirCurso(Curso curso) {
        // Si ya se finalizó o el curso no está disponible, no se hace nada
        if (fichaPago != null || !cursosDisponibles.contains(curso)) {
            return;
        }
        cursosDisponibles.remove(curso);
        cursosInscritos.add(curso);

        notificarObservadores();
    }

    /**
     * Finaliza la inscripción generando la ficha de pago con los cursos
     * inscritos y el costo total. Después notifica a la vista.
     */
    public void finalizarInscripcion() {
        // Solo se puede finalizar una vez y con al menos un curso inscrito
        if (fichaPago != null || cursosInscritos.isEmpty()) {
            return;
        }
        double total = calcularTotal();
        fichaPago = new FichaPago(new ArrayList<>(cursosInscritos), total);

        notificarObservadores();
    }


    public double calcularTotal() {
        double total = 0;
        for (Curso curso : cursosInscritos) {
            total += curso.getCosto();
        }
        return total;
    }

    public List<Curso> getCursosDisponibles() {
        return cursosDisponibles;
    }

    public List<Curso> getCursosInscritos() {
        return cursosInscritos;
    }

    public FichaPago getFichaPago() {
        return fichaPago;
    }

    @Override
    public void agregarObservador(IObservador o) {
        observadores.add(o);
    }

    @Override
    public void eliminarObservador(IObservador o) {
        observadores.remove(o);
    }

    @Override
    public void notificarObservadores() {
        for (IObservador o : observadores) {
            o.actualizar();
        }
    }
}
