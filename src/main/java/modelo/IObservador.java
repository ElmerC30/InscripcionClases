package modelo;

/**
 * Patrón Observer - OBSERVADOR.
 *
 * Toda clase que quiera enterarse de los cambios del modelo debe
 * implementar esta interfaz. En este proyecto la implementa la vista
 * (FrmInscripcion): cuando el modelo cambia, llama a actualizar() y la
 * vista vuelve a leer los datos del modelo para redibujarse.
 */
public interface IObservador {

    /**
     * Lo invoca el sujeto (el modelo) cada vez que su estado cambia.
     */
    void actualizar();
}
