public class RecursoCompartido {
    private static final int CAPACIDAD_MAX = 100;
    private int cantidadActualAceite;

    public RecursoCompartido() {
        this.cantidadActualAceite = 50; // Inicia a media capacidad
    }

    public synchronized boolean consumirAceite() {
        if (cantidadActualAceite > 0) {
            cantidadActualAceite--;
            return true;
        }
        return false;
    }

    public synchronized void recargarAceite() {
        this.cantidadActualAceite = CAPACIDAD_MAX;
        System.out.println("Depósito recargado al 100%");
    }
    
    public int getCantidad() { return cantidadActualAceite; }
}
