import java.util.Scanner;

public class Juego {

    private Jugador[] jugadores;
    private Tablero tablero;
    private int turnoActual;

    public Juego(Jugador jugador1, Jugador jugador2, int tamanoTablero) {
        jugadores = new Jugador[]{jugador1, jugador2};
        tablero = new Tablero(tamanoTablero);
        turnoActual = 0;
    }

    public void iniciarPartida() {
        System.out.println("¡Bienvenido al Triquitraque!");
        mostrarTablero(); // Se llama al método aquí
    }

    public void finalizarPartida() {
        String ganador = tablero.verificarGanador();
        if (ganador.equals("Empate")) {
            System.out.println("¡Empate!");
        } else {
            System.out.println("¡Felicidades " + ganador + ", has ganado!");
        }

        System.out.println("\n¿Deseas iniciar una nueva partida? (Si/No)");
        Scanner scanner = new Scanner(System.in);
        String respuesta = scanner.nextLine().toUpperCase();
        if (respuesta.equals("SI")) {
            iniciarPartida();
        } else {
            System.out.println("¡Gracias por jugar!");
        }
    }

    public void cambiarTurno() {
        turnoActual = (turnoActual + 1) % jugadores.length;
    }

    public void manejarEntradaUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Turno de " + jugadores[turnoActual].obtenerNombre() + " (" + jugadores[turnoActual].getFicha() + "):");
        System.out.print("Ingrese la fila (1-" + tablero.getTamano() + "): ");
        int fila = scanner.nextInt() - 1;
        System.out.print("Ingrese la columna (1-" + tablero.getTamano() + "): ");
        int columna = scanner.nextInt() - 1;

        while (!validarCasilla(fila, columna)) {
            System.out.println("Casilla inválida. Intenta nuevamente:");
            System.out.print("Ingrese la fila (1-" + tablero.getTamano() + "): ");
            fila = scanner.nextInt() - 1;
            System.out.print("Ingrese la columna (1-" + tablero.getTamano() + "): ");
            columna = scanner.nextInt() - 1;
        }

        tablero.colocarFicha(fila, columna, jugadores[turnoActual].getFicha());
        mostrarTablero(); // Se llama al método aquí

        if (tablero.verificarGanador().equals("Empate") || tablero.verificarEmpate()) {
            finalizarPartida();
        } else {
            cambiarTurno();
        }
    }

    private boolean validarCasilla(int fila, int columna) {
        return fila >= 0 && fila < tablero.getTamano() && columna >= 0 && columna < tablero.getTamano() && tablero.getMatriz()[fila][columna].equals("-");
    }

    public void mostrarTablero() {
        for (int i = 0; i < tablero.getMatriz().length; i++) {
            for (int j = 0; j < tablero.getMatriz()[0].length; j++) {
                System.out.print("|" + tablero.getMatriz()[i][j] + "|");
            }
            System.out.println();
        }
    }

    // Getters and Setters (opcionales)
    public Jugador[] getJugadores() {
        return jugadores;
    }

    public void setJugadores(Jugador[] jugadores) {
        this.jugadores = jugadores;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public int getTurnoActual() {
        return turnoActual;
    }

    public void setTurnoActual(int turnoActual) {
        this.turnoActual = turnoActual;
    }
}

