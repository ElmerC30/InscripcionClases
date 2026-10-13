package main;

import controlador.ControlInscripcion;
import modelo.ModeloInscripcion;
import vista.FrmInscripcion;

public class Main {

    public static void main(String[] args) {
        // 1. Se crea el MODELO (estado y datos en memoria)
        ModeloInscripcion modelo = new ModeloInscripcion();

        // 2. Se crea el CONTROLADOR, que conoce al modelo
        ControlInscripcion control = new ControlInscripcion(modelo);

        // 3. Se crea la VISTA, que conoce al modelo (para leer) y al controlador (para avisar)
        FrmInscripcion vista = new FrmInscripcion(modelo, control);

        // 4. Observer: la vista se registra como observadora del modelo
        modelo.agregarObservador(vista);

        // 5. Inicia el caso de uso: el modelo carga los cursos y notifica a la vista
        control.iniciarInscripcion();

        // 6. Se muestra la ventana
        vista.setVisible(true);
    }
}
