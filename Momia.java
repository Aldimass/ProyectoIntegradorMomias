public class Momia extends Entidad {
    private int velocidad;

    public Momia(String nombre, int x, int y) {
        super(nombre, x, y);
        this.velocidad = 1; // puedes ajustar la velocidad
    }

    public void atacar(Entidad objetivo) {
        System.out.println("🧟 " + nombre + " ataca a " + objetivo.getNombre());
    }

    public void perseguir() {
        y += velocidad;
        System.out.println("🧟 " + nombre + " se mueve a: [" + x + "," + y + "]");
    }

    @Override
    public void run() {
        while (true) {
            try {
                perseguir(); // usa tu nueva lógica
                Thread.sleep(1500); 
            } catch (InterruptedException e) { 
                break; 
            }
        }
    }
}
