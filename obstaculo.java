package uvm;
import java.awt.Color;

public class Obstaculo {
    private int x, y, ancho, alto;
    private Color colorPared;

    // Se crea pasándole las coordenadas y tamaño exactos
    public Obstaculo(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.colorPared = new Color(0, 0, 150); // Azul oscuro laberinto
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getAncho() { return ancho; }
    public int getAlto() { return alto; }
    public Color getColorPared() { return colorPared; }
}
