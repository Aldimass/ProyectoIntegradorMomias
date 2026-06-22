package uvm;
import java.awt.Rectangle;

public class Mecanica {
    
    public Mecanica() {}

    // Evalúa la predicción de movimiento contra una pared estática
    public boolean chocaConPared(Rectangle hitboxFuturo, Obstaculo pared) {
        Rectangle hitboxPared = new Rectangle(pared.getX(), pared.getY(), pared.getAncho(), pared.getAlto());
        return hitboxFuturo.intersects(hitboxPared);
    }

    public boolean detectarColisionMomia(Arqueologo a, Momia m) {
        Rectangle hitboxJugador = new Rectangle(a.getJugadorX(), a.getJugadorY(), a.getTamano(), a.getTamano());
        Rectangle hitboxMomia = new Rectangle(m.getMomiaX(), m.getMomiaY(), m.getTamano(), m.getTamano());
        return hitboxJugador.intersects(hitboxMomia);
    }

    public boolean detectarRecargaAceite(Arqueologo a, int ax, int ay, int atam) {
        Rectangle hitboxJugador = new Rectangle(a.getJugadorX(), a.getJugadorY(), a.getTamano(), a.getTamano());
        Rectangle hitboxAceite = new Rectangle(ax, ay, atam, atam);
        return hitboxJugador.intersects(hitboxAceite);
    }
}
