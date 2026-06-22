package uvm;
import java.awt.Color;

public class Momia {
    private float momiaX; // Usamos float para un rastreo fluido y milimétrico
    private float momiaY;
    private int tamano;
    private float velocidad;
    private Color colorMomia;

    public Momia(int xInicio, int yInicio) {
        this.momiaX = xInicio; 
        this.momiaY = yInicio;
        this.tamano = 14; // Un poco más grande que el arqueólogo
        this.velocidad = 0.2f;
        this.colorMomia = new Color(210, 205, 180);
    }

    public int getMomiaX() { return (int) momiaX; }
    public void setPosicion(int x, int y) { this.momiaX = x; this.momiaY = y; }
    
    public int getMomiaY() { return (int) momiaY; }
    public int getTamano() { return tamano; }
    public Color getColorMomia() { return colorMomia; }

    public void actualizarPerspectivaSuperior(float aceite, int destinoX, int destinoY) {
        // La momia se vuelve más agresiva sin luz
        if (aceite <= 0) velocidad = 3.0f; 
        else if (aceite <= 25f) velocidad = 1.8f; 
        else if (aceite <= 50f) velocidad = 1.0f; 
        else if (aceite <= 75f) velocidad = 0.6f; 
        else velocidad = 0.3f; // Lento cuando hay luz

        // Matemáticas para perseguir al jugador (Comportamiento de espectro)
        float difX = destinoX - momiaX;
        float difY = destinoY - momiaY;
        float distancia = (float) Math.sqrt((difX * difX) + (difY * difY));

        if (distancia > 0) {
            momiaX += (difX / distancia) * velocidad;
            momiaY += (difY / distancia) * velocidad;
        }

        // Si recargas aceite brillante, se aleja por instinto
        if (aceite > 85f && distancia < 50) {
            momiaX -= (difX / distancia) * 1.5f;
            momiaY -= (difY / distancia) * 1.5f;
        }
    }
}
