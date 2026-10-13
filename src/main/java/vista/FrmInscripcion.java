package vista;

import controlador.ControlInscripcion;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import modelo.Curso;
import modelo.FichaPago;
import modelo.IObservador;
import modelo.ModeloInscripcion;

/**
 * VISTA del patrón MVC y OBSERVADOR del patrón Observer.
 */
public class FrmInscripcion extends JFrame implements IObservador {

    /** Modelo del que la vista LEE los datos. */
    private ModeloInscripcion modelo;

    /** Controlador al que la vista le PASA las acciones del alumno. */
    private ControlInscripcion control;

    // Componentes de la pantalla
    private JList<Curso> lstCursosDisponibles;
    private JList<Curso> lstCursosInscritos;
    private JLabel lblTotal;
    private JButton btnInscribir;
    private JButton btnFinalizar;
    private JTextArea txtFichaPago;

    /**
     * Crea la ventana.
     *
     * @param modelo  modelo del que se leen los datos
     * @param control controlador que recibe las acciones del alumno
     */
    public FrmInscripcion(ModeloInscripcion modelo, ControlInscripcion control) {
        this.modelo = modelo;
        this.control = control;
        inicializarComponentes();
    }

    /**
     * Construye la interfaz gráfica: listas, botones, total y ficha de pago.
     */
    private void inicializarComponentes() {
        setTitle("Inscribirse en clases");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ----- Título -----
        JLabel lblTitulo = new JLabel("Inscripción a clases");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 12, 0, 12));
        add(lblTitulo, BorderLayout.NORTH);

        // ----- Zona izquierda: cursos disponibles -----
        lstCursosDisponibles = new JList<>();
        lstCursosDisponibles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrDisponibles = new JScrollPane(lstCursosDisponibles);
        scrDisponibles.setBorder(BorderFactory.createTitledBorder("Cursos disponibles (costo)"));
        scrDisponibles.setPreferredSize(new Dimension(360, 220));

        // ----- Centro: botón para inscribir el curso seleccionado -----
        btnInscribir = new JButton("Inscribir »");
        btnInscribir.addActionListener(e -> btnInscribirActionPerformed());

        // ----- Zona derecha: cursos inscritos y monto a pagar debajo -----
        lstCursosInscritos = new JList<>();
        JScrollPane scrInscritos = new JScrollPane(lstCursosInscritos);
        scrInscritos.setBorder(BorderFactory.createTitledBorder("Cursos inscritos"));
        scrInscritos.setPreferredSize(new Dimension(360, 220));

        lblTotal = new JLabel();
        lblTotal.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblTotal.setBorder(BorderFactory.createEmptyBorder(4, 6, 0, 0));

        JPanel pnlDerecha = new JPanel(new BorderLayout());
        pnlDerecha.add(scrInscritos, BorderLayout.CENTER);
        pnlDerecha.add(lblTotal, BorderLayout.SOUTH);

        JPanel pnlListas = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 8, 0, 8);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        pnlListas.add(scrDisponibles, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        pnlListas.add(btnInscribir, gbc);
        gbc.gridx = 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pnlListas.add(pnlDerecha, gbc);
        add(pnlListas, BorderLayout.CENTER);

        // ----- Zona inferior: botón finalizar y ficha de pago -----
        btnFinalizar = new JButton("Finalizar inscripción");
        btnFinalizar.addActionListener(e -> btnFinalizarActionPerformed());
        JPanel pnlBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlBoton.add(btnFinalizar);

        txtFichaPago = new JTextArea(10, 60);
        txtFichaPago.setEditable(false);
        txtFichaPago.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        JScrollPane scrFicha = new JScrollPane(txtFichaPago);
        scrFicha.setBorder(BorderFactory.createTitledBorder("Ficha de pago"));

        JPanel pnlInferior = new JPanel(new BorderLayout());
        pnlInferior.setBorder(BorderFactory.createEmptyBorder(0, 8, 8, 8));
        pnlInferior.add(pnlBoton, BorderLayout.NORTH);
        pnlInferior.add(scrFicha, BorderLayout.CENTER);
        add(pnlInferior, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }


    private void btnInscribirActionPerformed() {
        Curso curso = lstCursosDisponibles.getSelectedValue();
        if (curso == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un curso de la lista de cursos disponibles.",
                    "Inscribirse en clases", JOptionPane.WARNING_MESSAGE);
            return;
        }
        control.inscribirCurso(curso);
    }

    /**
     * Clic en "Finalizar inscripción": le avisa al controlador.
     */
    private void btnFinalizarActionPerformed() {
        control.finalizarInscripcion();
    }


    @Override
    public void actualizar() {
        List<Curso> disponibles = modelo.getCursosDisponibles();
        List<Curso> inscritos = modelo.getCursosInscritos();
        double total = modelo.calcularTotal();
        FichaPago ficha = modelo.getFichaPago();

        lstCursosDisponibles.setListData(disponibles.toArray(new Curso[0]));
        lstCursosInscritos.setListData(inscritos.toArray(new Curso[0]));
        lblTotal.setText(String.format("Total a pagar: $%,.2f", total));

        if (ficha != null) {
            mostrarFichaPago(ficha);
        } else {
            txtFichaPago.setText("La ficha de pago se generará al finalizar la inscripción.");
        }

        // Una vez generada la ficha ya no se puede inscribir ni finalizar otra vez
        btnInscribir.setEnabled(ficha == null && !disponibles.isEmpty());
        btnFinalizar.setEnabled(ficha == null && !inscritos.isEmpty());
    }

    /**
     * Escribe en pantalla la ficha de pago: cada curso con su costo y el total.
     *
     * @param ficha ficha de pago generada por el modelo
     */
    private void mostrarFichaPago(FichaPago ficha) {
        StringBuilder texto = new StringBuilder();
        String linea = "-".repeat(62) + "\n";
        texto.append("                    FICHA DE PAGO\n");
        texto.append(linea);
        texto.append(String.format("%-9s %-36s %15s%n", "Clave", "Curso", "Costo"));
        texto.append(linea);
        for (Curso curso : ficha.getCursos()) {
            texto.append(String.format("%-9s %-36s %15s%n",
                    curso.getClave(), curso.getNombre(), String.format("$%,.2f", curso.getCosto())));
        }
        texto.append(linea);
        texto.append(String.format("%-46s %15s%n", "COSTO TOTAL:", String.format("$%,.2f", ficha.getTotal())));
        txtFichaPago.setText(texto.toString());
        txtFichaPago.setCaretPosition(0);
    }
}
