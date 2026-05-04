public abstract class Entidad {
    protected int x, y;
    protected String nombre;
    protected int salud;

    public Entidad(String nombre, int x, int y, int salud) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.salud = salud;
    }

    // Métodos comunes
    public void moverse(int nuevaX, int nuevaY) {
        this.x = nuevaX;
        this.y = nuevaY;
    }

    // Getters y Setters
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public String getNombre() { return nombre; }
    public int getSalud() { return salud; }
    public void setSalud(int salud) { this.salud = salud; }

    // Método que obliga a los demás a implementar su lógica
    public abstract void actuar();
}

