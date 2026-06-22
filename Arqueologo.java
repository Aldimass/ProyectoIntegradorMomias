package uvm;
import java.awt.Color;

public class Arqueologo {
    private int arqueologoX;
    private int arqueologoY;
    private int tamano;
    private Color cuerpo;
    private float aceite; 

    public Arqueologo(int xInicio, int yInicio) {
        this.arqueologoX = xInicio;
        this.arqueologoY = yInicio;
        this.tamano = 12; // Personaje pequeño para caber en el laberinto
        this.cuerpo = new Color(139, 69, 19); 
        this.aceite = 100.0f; 
    }

    public int getJugadorX() { return arqueologoX; }
    public void setJugadorX(int x) { this.arqueologoX = x; }
    
    public int getJugadorY() { return arqueologoY; }
    public void setJugadorY(int y) { this.arqueologoY = y; }
    
    public int getTamano() { return tamano; }
    public Color getCuerpo() { return cuerpo; }
    public float getAceite() { return aceite; }
    public void setAceite(float aceite) { this.aceite = aceite; }

    public void consumirAceite(float cantidad) {
        this.aceite -= cantidad;
        if (this.aceite < 0) this.aceite = 0;
    }

    public void recargarAceite() {
        this.aceite += 40.0f; 
        if (this.aceite > 100.0f) this.aceite = 100.0f;
    }
}
