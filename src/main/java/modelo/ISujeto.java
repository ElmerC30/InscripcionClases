package modelo;

/**
 * Patrón Observer - SUJETO (observable).
 *
 * La implementa el modelo (ModeloInscripcion). Guarda una lista de
 * observadores y les avisa cuando algo cambia. Gracias a esta interfaz el
 * modelo NO conoce a la vista concreta, solo conoce a "un IObservador".
 */
public interface ISujeto {

    /**
     * Registra un observador para que reciba las notificaciones.
     *
     * @param o observador a registrar (por ejemplo, la vista)
     */
    void agregarObservador(IObservador o);

    /**
     * Quita un observador para que ya no reciba notificaciones.
     *
     * @param o observador a eliminar
     */
    void eliminarObservador(IObservador o);

    /**
     * Avisa a todos los observadores registrados que el estado cambió
     * (llama a actualizar() de cada uno).
     */
    void notificarObservadores();
}
