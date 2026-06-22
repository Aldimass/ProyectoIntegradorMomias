package uvm;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sonido {
    public static void reproducirSFX(URL archivoSonido) {
        if (archivoSonido == null) return;
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivoSonido);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();      
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}
    }

    public static Clip iniciarMusicaFondo(URL archivoSonido) {
        if (archivoSonido == null) return null;
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivoSonido);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); 
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            return null;
        }
    }
}
