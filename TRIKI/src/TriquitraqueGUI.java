import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TriquitraqueGUI extends JFrame implements ActionListener {

    // Declaración de variables

    private String nombreJugador1;
    private String nombreJugador2;
    private int tamanoTablero;
    private static final String[] FICHAS = {"X", "O"}; // Símbolos de los jugadores
    private int turnoActual = 0; // Índice del jugador actual (0 o 1)
    private String[][] tablero; // Representación interna del tablero de juego
    private JButton[][] botones; // Botones que representan las celdas del tablero de juego
    private Color[] coloresFichas; // Colores de las fichas de los jugadores
    private JPanel panelReiniciar; // Panel para contener el botón de reinicio
    private JButton btnReiniciar;  // Botón para reiniciar el juego
    private JButton btnVolver; // Botón para volver al menú de configuración
    private int[] contadorGanadas; // Contador de partidas ganadas por cada jugador
    private JLabel etiquetaContador; // Etiqueta para mostrar el contador de partidas ganadas

    public TriquitraqueGUI() {
        super("Triquitraque");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear el panel de configuración inicial
        mostrarPanelConfiguracion();

        setSize(300, 350); // Tamaño inicial
        setVisible(true);
    }

    public static Object[] getFICHAS() {
        return FICHAS;
    }

    private void mostrarPanelConfiguracion() {
        JTextField nombreJugador1Field = new JTextField();
        JTextField nombreJugador2Field = new JTextField();
        String[] tamanosTablero = {"3x3", "4x4", "5x5"};
        JComboBox<String> tamanoTableroCombo = new JComboBox<>(tamanosTablero);

        // Agregar opciones de colores para las fichas
        String[] opcionesColores = {"Rojo", "Azul", "Verde", "Negro"};
        JComboBox<String>[] colorFichasCombo = new JComboBox[FICHAS.length];

        JPanel panelConfiguracion = new JPanel(new GridLayout(0, 1));
        panelConfiguracion.add(new JLabel("Nombre del Jugador 1:"));
        panelConfiguracion.add(nombreJugador1Field);
        panelConfiguracion.add(new JLabel("Nombre del Jugador 2:"));
        panelConfiguracion.add(nombreJugador2Field);
        panelConfiguracion.add(new JLabel("Tamaño del Tablero:"));
        panelConfiguracion.add(tamanoTableroCombo);

        // Agregar opciones de colores para las fichas
        for (int i = 0; i < FICHAS.length; i++) {
            colorFichasCombo[i] = new JComboBox<>(opcionesColores);
            panelConfiguracion.add(new JLabel("Color de " + FICHAS[i] + ":"));
            panelConfiguracion.add(colorFichasCombo[i]);
        }

        int resultado = JOptionPane.showConfirmDialog(null, panelConfiguracion,
                "Configuración del Juego", JOptionPane.OK_CANCEL_OPTION);
        if (resultado == JOptionPane.OK_OPTION) {
            nombreJugador1 = nombreJugador1Field.getText();
            nombreJugador2 = nombreJugador2Field.getText();
            String seleccionTamano = (String) tamanoTableroCombo.getSelectedItem();
            tamanoTablero = Integer.parseInt(seleccionTamano.substring(0, 1));

            // Obtener colores seleccionados para las fichas
            coloresFichas = new Color[FICHAS.length];
            for (int i = 0; i < FICHAS.length; i++) {
                String seleccionColor = (String) colorFichasCombo[i].getSelectedItem();
                coloresFichas[i] = obtenerColorPorNombre(seleccionColor);
            }

            // Crear el panel del tablero de juego
            crearTablero();
        } else {
            System.exit(0); // Si el usuario cancela, se cierra la aplicación
        }
    }

    private void crearTablero() {
        JPanel panelTablero = new JPanel(new GridLayout(tamanoTablero, tamanoTablero));
        tablero = new String[tamanoTablero][tamanoTablero];
        botones = new JButton[tamanoTablero][tamanoTablero];
        coloresFichas = new Color[FICHAS.length];
        contadorGanadas = new int[FICHAS.length];

        for (int i = 0; i < FICHAS.length; i++) {
            contadorGanadas[i] = 0; // Inicializar contador de partidas ganadas
        }

        for (int i = 0; i < tamanoTablero; i++) {
            for (int j = 0; j < tamanoTablero; j++) {
                tablero[i][j] = "-";
                botones[i][j] = new JButton("-");
                botones[i][j].setFont(new Font("Arial", Font.BOLD, 40));
                botones[i][j].addActionListener(this);
                panelTablero.add(botones[i][j]);
            }
        }

        // Crear el panel del botón de reinicio
        panelReiniciar = new JPanel();
        btnReiniciar = new JButton("Reiniciar Juego");
        btnReiniciar.addActionListener(this);
        btnReiniciar.setEnabled(false); // Desactivar el botón de reinicio inicialmente

        // Crear el botón para volver al menú de configuración
        btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(this);
        panelReiniciar.add(btnReiniciar);
        panelReiniciar.add(btnVolver);

        // Crear etiqueta para el contador de partidas ganadas
        etiquetaContador = new JLabel(nombreJugador1 + ": " + contadorGanadas[0] + " - " + nombreJugador2 + ": " + contadorGanadas[1]);
        etiquetaContador.setFont(new Font("Arial", Font.BOLD, 24));
        etiquetaContador.setForeground(Color.BLUE);

        // Crear el panel que contiene el tablero y el contador
        JPanel panelTableroContador = new JPanel(new BorderLayout());
        panelTableroContador.add(panelTablero, BorderLayout.CENTER);
        panelTableroContador.add(etiquetaContador, BorderLayout.NORTH);

        // Agregar paneles al marco principal
        getContentPane().removeAll(); // Limpiar el contenido anterior
        add(panelTableroContador, BorderLayout.CENTER);
        add(panelReiniciar, BorderLayout.SOUTH);

        pack(); // Ajustar tamaño automáticamente
    }

    private void reiniciarJuego() {
        // Limpiar el tablero y habilitar los botones nuevamente
        for (int i = 0; i < tamanoTablero; i++) {
            for (int j = 0; j < tamanoTablero; j++) {
                tablero[i][j] = "-";
                botones[i][j].setText("-");
                botones[i][j].setEnabled(true);
            }
        }
        btnReiniciar.setEnabled(false); // Desactivar el botón de reinicio
        turnoActual = 0; // Restablecer el turno al primer jugador
    }

    private void mostrarError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void colocarFicha(int fila, int columna) throws InvalidMoveException {
        if (tablero[fila][columna].equals("-")) {
            tablero[fila][columna] = FICHAS[turnoActual];
            botones[fila][columna].setForeground(coloresFichas[turnoActual]); // Establecer color de la ficha
            botones[fila][columna].setText(FICHAS[turnoActual]);
            turnoActual = (turnoActual + 1) % FICHAS.length; // Cambiar al siguiente jugador
        } else {
            throw new InvalidMoveException("Casilla ocupada. Intente de nuevo.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton clickedButton = (JButton) e.getSource();

            // Manejar clics en los botones del tablero de juego
            for (int i = 0; i < tamanoTablero; i++) {
                for (int j = 0; j < tamanoTablero; j++) {
                    if (clickedButton == botones[i][j] && botones[i][j].getText().equals("-")) {
                        try {
                            colocarFicha(i, j);
                            mostrarTablero(); // Actualizar la representación visual del tablero
                            verificarGanador(); // Verificar si hay un ganador o un empate
                        } catch (InvalidMoveException ex) {
                            mostrarError(ex.getMessage()); // Mostrar mensaje de error
                        }
                    }
                }
            }

            // Manejar clic en el botón de reinicio
            if (e.getSource() == btnReiniciar) {
                reiniciarJuego();
            }

            // Manejar clic en el botón de volver al menú de configuración
            if (e.getSource() == btnVolver) {
                mostrarPanelConfiguracion();
            }
        }
    }

    private void mostrarTablero() {
        // Imprimir el tablero en la consola (para fines de depuración)
        for (int i = 0; i < tamanoTablero; i++) {
            for (int j = 0; j < tamanoTablero; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void verificarGanador() {
        String ganador = verificarGanadorEnTablero();
        if (ganador != null) {
            contadorGanadas[ganador.equals(FICHAS[0]) ? 0 : 1]++; // Incrementar contador de ganadas del jugador correspondiente
            actualizarContador(); // Actualizar visualización del contador de partidas ganadas
            mostrarMensajeGanador(ganador); // Mostrar un mensaje anunciando al ganador
            deshabilitarBotones(); // Desactivar botones para evitar más jugadas
        } else if (verificarEmpate()) {
            mostrarMensajeEmpate(); // Mostrar un mensaje indicando un empate
            deshabilitarBotones(); // Desactivar botones para evitar más jugadas
        }
    }

    private void actualizarContador() {
        etiquetaContador.setText(nombreJugador1 + ": " + contadorGanadas[0] + " - " + nombreJugador2 + ": " + contadorGanadas[1]);
    }

    private void deshabilitarBotones() {
        for (int i = 0; i < tamanoTablero; i++) {
            for (int j = 0; j < tamanoTablero; j++) {
                botones[i][j].setEnabled(false); // Desactivar todos los botones del tablero
            }
        }
        btnReiniciar.setEnabled(true); // Activar el botón de reinicio
    }

    private void mostrarMensajeGanador(String ganador) {
        String mensaje;
        if (ganador.equals("X")) {
            mensaje = "¡Ganador: " + nombreJugador1 + "!";
        } else {
            mensaje = "¡Ganador: " + nombreJugador2 + "!";
        }
        JOptionPane.showMessageDialog(this, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarMensajeEmpate() {
        JOptionPane.showMessageDialog(this, "¡Empate!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }

    // Métodos auxiliares para verificar ganador y empate
    private String verificarGanadorEnTablero() {
        // Verificar filas, columnas y diagonales para encontrar un ganador
        for (int i = 0; i < tamanoTablero; i++) {
            if (tablero[i][0].equals(tablero[i][1]) && tablero[i][1].equals(tablero[i][2]) && !tablero[i][0].equals("-")) {
                return tablero[i][0];
            }
            if (tablero[0][i].equals(tablero[1][i]) && tablero[1][i].equals(tablero[2][i]) && !tablero[0][i].equals("-")) {
                return tablero[0][i];
            }
        }
        if (tamanoTablero == 3) {
            if (tablero[0][0].equals(tablero[1][1]) && tablero[1][1].equals(tablero[2][2]) && !tablero[0][0].equals("-")) {
                return tablero[0][0];
            }
            if (tablero[0][2].equals(tablero[1][1]) && tablero[1][1].equals(tablero[2][0]) && !tablero[0][2].equals("-")) {
                return tablero[0][2];
            }
        } else {
            // Verificar diagonales para tableros de tamaño 4x4 o 5x5
            boolean diagonalPrincipalGanada = true;
            boolean diagonalSecundariaGanada = true;
            for (int i = 0; i < tamanoTablero - 1; i++) {
                if (!tablero[i][i].equals(tablero[i + 1][i + 1]) || tablero[i][i].equals("-")) {
                    diagonalPrincipalGanada = false;
                }
                if (!tablero[i][tamanoTablero - 1 - i].equals(tablero[i + 1][tamanoTablero - 2 - i]) || tablero[i][tamanoTablero - 1 - i].equals("-")) {
                    diagonalSecundariaGanada = false;
                }
            }
            if (diagonalPrincipalGanada) {
                return tablero[0][0];
            }
            if (diagonalSecundariaGanada) {
                return tablero[0][tamanoTablero - 1];
            }
        }
        return null; // No hay ganador todavía
    }

    private boolean verificarEmpate() {
        // Verificar si todas las celdas están llenas y no hay ganador
        for (int i = 0; i < tamanoTablero; i++) {
            for (int j = 0; j < tamanoTablero; j++) {
                if (tablero[i][j].equals("-")) {
                    return false; // Se encontró una celda vacía, no es un empate
                }
            }
        }
        return true; // Todas las celdas están llenas, es un empate
    }

    public int getTamanoTablero() {
        return tamanoTablero;
    }

    public int getTurnoActual() {
        return turnoActual;
    }

    public String[][] getTablero() {
        return tablero;
    }

    public String getNombreJugador2() {
        return nombreJugador2;
    }

    public String getNombreJugador1() {
        return nombreJugador1;
    }

    // Clase de excepción personalizada para manejar movimientos inválidos
    private static class InvalidMoveException extends Exception {
        public InvalidMoveException(String message) {
            super(message);
        }
    }

    public Color obtenerColorPorNombre(String nombreColor) {
        switch (nombreColor.toLowerCase()) {
            case "Rojo":
                return Color.RED;
            case "Azul":
                return Color.BLUE;
            case "Verde":
                return Color.GREEN;
            case "Negro":
                return Color.BLACK;
            default:
                return Color.BLACK; // Color por defecto en caso de error
        }
    }
    }

