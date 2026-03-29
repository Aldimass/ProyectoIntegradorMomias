public class Arqueologo extends Entidad {
    private Aceite lampara; // Relación: el arqueólogo USA el aceite

    public Arqueologo(String nombre, int x, int y, Aceite lampara) {
        super(nombre, x, y);
        this.lampara = lampara;
    }

    // El método "run" es el cuerpo del hilo
    @Override
    public void run() {
        while (true) {
            try {
                // 1. Gasta aceite antes de moverse
                if (lampara.consumir(10)) {
                    x++; // 2. Avanza un paso
                    System.out.println("🚶 " + nombre + " en: [" + x + "," + y + "]");
                    // 3. Espera 2 segundos (hilo lento)
                    Thread.sleep(2000); 
                } else {
                    // 4. Si no hay aceite, el hilo termina (muere)
                    System.out.println("💀 " + nombre + " se quedó sin luz... GAME OVER.");
                    break; 
                }
            } catch (InterruptedException e) { break; }
        }
    }
}
