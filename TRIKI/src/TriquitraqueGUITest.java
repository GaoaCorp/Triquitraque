import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TriquitraqueGUITest {

    private TriquitraqueGUI triquitraqueGUI;

    @BeforeEach
    public void setUp() {
        triquitraqueGUI = new TriquitraqueGUI();
    }

    @Test
    public void testNombreJugador1() {
        assertEquals("Jugador1", triquitraqueGUI.getNombreJugador1());
    }

    @Test
    public void testNombreJugador2() {
        assertEquals("Jugador2", triquitraqueGUI.getNombreJugador2());
    }

    @Test
    public void testTamanoTablero() {
        assertEquals(3, triquitraqueGUI.getTamanoTablero());
    }

    @Test
    public void testFichas() {
        assertArrayEquals(new String[]{"X", "O"}, TriquitraqueGUI.getFICHAS());
    }

    @Test
    public void testTurnoActual() {
        assertEquals(0, triquitraqueGUI.getTurnoActual());
    }

    @Test
    public void testTableroInicial() {
        String[][] tablero = triquitraqueGUI.getTablero();
        assertEquals("-", tablero[0][0]);
        assertEquals("-", tablero[1][1]);
        assertEquals("-", tablero[2][2]);
    }

    @Test
    public void testColorFichaPorDefecto() {
        assertEquals(java.awt.Color.BLUE, triquitraqueGUI.obtenerColorPorNombre("Indefinido"));
    }

    // Puedes agregar más pruebas según sea necesario para cubrir más casos de uso
}
