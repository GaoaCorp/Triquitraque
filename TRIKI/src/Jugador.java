public class Jugador {

    private String nombre;
    private String color;
    private String ficha;

    public Jugador(String nombre, String color) {
        this.nombre = nombre;
        this.color = color;
        this.ficha = color.equals("Rojo") ? "X" : "O";
    }

    public String obtenerNombre() {
        return nombre;
    }

    public String getColor() {
        return color;
    }

    public String getFicha() {
        return ficha;
    }
}
