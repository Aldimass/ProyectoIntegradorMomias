public class Momia extends Entidad {
    public Momia(String nombre, int x, int y) {
        super(nombre, x, y);
    }

    @Override
    public void run() {
        while (true) {
            try {
                // La momia se mueve cada 1.5 segundos (hilo rápido)
                y++; 
                System.out.println("🧟 " + nombre + " acecha en: [" + x + "," + y + "]");
                Thread.sleep(1500); 
            } catch (InterruptedException e) { break; }
        }
    }
}
