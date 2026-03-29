public class Aceite {
    private int cantidadActual = 100; // Empezamos con el tanque lleno

    // synchronized para que solo un hilo a la vez toque el aceite
    public synchronized boolean consumir(int cantidad) {
        if (cantidadActual >= cantidad) {
            cantidadActual -= cantidad; // Restamos aceite
            System.out.println("[SISTEMA] Aceite restante: " + cantidadActual + "%");
            return true; // Hay aceite, se puede mover
        }
        return false; // No hay aceite, debe detenerse
    }

    // Método para rellenar el tanque (interacción del jugador)
    public synchronized void recargar() {
        this.cantidadActual = 100;
        System.out.println(">>> ¡LÁMPARA RECARGADA AL 100%! <<<");
    }
}
