public abstract class Entidad extends Thread {
    protected String nombre;
    protected int x, y; // Coordenadas de posición

    public Entidad(String nombre, int x, int y) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
    }
}
