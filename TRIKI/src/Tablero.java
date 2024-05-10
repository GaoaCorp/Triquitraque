public class Tablero {

    private String[][] matriz;
    private int tamano;

    public Tablero(int tamano) {
        this.tamano = tamano;
        matriz = new String[tamano][tamano];
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                matriz[i][j] = "-";
            }
        }
    }

    public String[][] getMatriz() {
        return matriz;
    }

    public int getTamano() {
        return tamano;
    }

    public void colocarFicha(int fila, int columna, String ficha) {
        if (isValidPosition(fila, columna)) {
            matriz[fila][columna] = ficha;
        } else {
            System.out.println("Posición inválida. Intenta nuevamente.");
        }
    }

    public String verificarGanador() {
        // Check rows
        for (int i = 0; i < tamano; i++) {
            if (matriz[i][0].equals(matriz[i][1]) && matriz[i][1].equals(matriz[i][2]) && !matriz[i][0].equals("-")) {
                return matriz[i][0];
            }
        }

        // Check columns
        for (int i = 0; i < tamano; i++) {
            if (matriz[0][i].equals(matriz[1][i]) && matriz[1][i].equals(matriz[2][i]) && !matriz[0][i].equals("-")) {
                return matriz[0][i];
            }
        }

        // Check diagonals
        if (matriz[0][0].equals(matriz[1][1]) && matriz[1][1].equals(matriz[2][2]) && !matriz[0][0].equals("-")) {
            return matriz[0][0];
        }

        if (matriz[0][2].equals(matriz[1][1]) && matriz[1][1].equals(matriz[2][0]) && !matriz[0][2].equals("-")) {
            return matriz[0][2];
        }

        // Check for tie
        if (verificarEmpate()) {
            return "Empate";
        }

        return "-";
    }

    boolean verificarEmpate() {
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                if (matriz[i][j].equals("-")) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidPosition(int fila, int columna) {
        return fila >= 0 && fila < tamano && columna >= 0 && columna < tamano && matriz[fila][columna].equals("-");
    }
}
