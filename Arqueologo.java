public class Arqueologo extends Entidad {
    private boolean estaVivo;
    private int nivelAceiteLampara;
    private RecursoCompartido recurso; // Asociación

    public Arqueologo(String nombre, RecursoCompartido recurso) {
        super(nombre, 0, 0, 100);
        this.recurso = recurso;
        this.estaVivo = true;
        this.nivelAceiteLampara = 20; // Inicia con algo de aceite
    }

    public void huir() {
        if (recurso.consumirAceite()) {
            this.x += 5;
            System.out.println(nombre + " huyó. Aceite restante en depósito.");
        } else {
            System.out.println("¡Sin aceite! " + nombre + " no puede correr.");
        }
    }

    @Override
    public void actuar() {
        System.out.println(nombre + " está explorando la pirámide...");
    }

    public int getNivelAceite() { return nivelAceiteLampara; }
}

