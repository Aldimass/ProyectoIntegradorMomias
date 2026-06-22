package uvm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.Clip;

public class Juego extends JPanel implements ActionListener, KeyListener {
    // Control de todos los recursos gráficos (Sprites y Texturas)
    private BufferedImage imgArqueologo, imgMomia, imgAceite, imgPared, imgFondo;
    
    private Arqueologo arq; 
    private Momia momi;
    private ArrayList<Obstaculo> laberinto;
    private ArrayList<Point> espaciosLibres; 
    private Mecanica meca;
    private Timer timer;
    private Clip musicaAmbiente; 
    private Random random;

    private final int ANCHO = 256;
    private final int ALTO = 240;
    
    private int aceiteX, aceiteY;
    private final int ACEITE_TAM = 16; // Escalado a 16 para que tu bidón pixel art luzca perfecto
    private boolean aceiteActivo;

    private boolean movArriba, movAbajo, movIzq, movDer;

    private boolean juegoTerminado;
    private boolean primerMovimiento;
    private int frames; 
    private int segundosSobrevividos;

    public Juego() {
        setPreferredSize(new Dimension(ANCHO, ALTO));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        
        random = new Random();
        meca = new Mecanica();
        
        cargarRecursos();
        reiniciarPartida();
        
        timer = new Timer(16, this);
        timer.start();
    }

    private void construirMapa() {
        laberinto = new ArrayList<>();
        espaciosLibres = new ArrayList<>();
        
        String[] mapaVisual = {
            "XXXXXXXXXXXXXXXX",
            "X......X.......X",
            "X.XXXX.X.XXXXX.X",
            "X.X..........X.X",
            "X.X.XXXXXXXX.X.X",
            "X.X...X........X",
            "X.XXX.X.XXXXXX.X",
            "X...X.X......X.X",
            "X.XXX.XXXXXX.X.X",
            "X.X..........X.X",
            "X.X.XXXXXXXX.X.X",
            "X.X......X...X.X",
            "X.XXXXXX.X.XXX.X",
            "X..............X",
            "XXXXXXXXXXXXXXXX" 
        };

        for (int fila = 0; fila < mapaVisual.length; fila++) {
            for (int col = 0; col < mapaVisual[fila].length(); col++) {
                char celda = mapaVisual[fila].charAt(col);
                int posX = col * 16;
                int posY = fila * 16;
                
                if (celda == 'X') {
                    laberinto.add(new Obstaculo(posX, posY, 16, 16));
                } else if (celda == '.') {
                    // Guardamos la posición exacta del bloque libre para centrar el nuevo sprite de aceite
                    espaciosLibres.add(new Point(posX, posY)); 
                }
            }
        }
    }

    private void reiniciarPartida() {
        construirMapa();
        arq = new Arqueologo(16 * 1, 16 * 13); 
        momi = new Momia(16 * 14, 16 * 1);    
        
        juegoTerminado = false;
        primerMovimiento = false;
        segundosSobrevividos = 0;
        frames = 0;
        movArriba = false; movAbajo = false; movIzq = false; movDer = false;
        
        aparecerAceite();

        if (musicaAmbiente != null) {
            musicaAmbiente.stop();              
            musicaAmbiente.setFramePosition(0); 
            musicaAmbiente.start();             
        }
    }

    private void aparecerAceite() {
        Point p = espaciosLibres.get(random.nextInt(espaciosLibres.size()));
        aceiteX = p.x;
        aceiteY = p.y;
        aceiteActivo = true;
    }

    private void cargarRecursos() {
        try {
            // Rutas directas a la raíz de target/classes (src/main/resources/) para evitar "input == null"
            imgArqueologo = ImageIO.read(getClass().getResource("/Arqueologo.png"));
            imgMomia = ImageIO.read(getClass().getResource("/Momia.png"));
            imgAceite = ImageIO.read(getClass().getResource("/Aceite.png"));
            imgPared = ImageIO.read(getClass().getResource("/BloquePared.png"));
            imgFondo = ImageIO.read(getClass().getResource("/Fondo.png"));
            
            // Audio de ambientación de fondo (.wav convertido de .ogg)
            musicaAmbiente = Sonido.iniciarMusicaFondo(getClass().getResource("/Ambiente.wav"));
        } catch (Exception e) {
            System.out.println("Error cargando recursos: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        dibujarEscenarioTopDown(g2d);

        if (juegoTerminado) {
            g2d.setColor(new Color(0, 0, 0, 220));
            g2d.fillRect(0, 0, ANCHO, ALTO);
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Monospaced", Font.BOLD, 16));
            g2d.drawString("¡ATRAPADO!", 80, 100);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Monospaced", Font.BOLD, 11));
            g2d.drawString("Sobreviviste: " + segundosSobrevividos + " seg", 60, 130);
            return;
        }

        renderizarLinternaCentral(g2d);
        dibujarHUD(g2d);
    }

    private void dibujarEscenarioTopDown(Graphics2D g) {
        // 1. Dibujar imagen de fondo de la tumba
        if (imgFondo != null) {
            g.drawImage(imgFondo, 0, 0, ANCHO, ALTO, null);
        } else {
            g.setColor(new Color(30, 25, 20));
            g.fillRect(0, 0, ANCHO, ALTO);
        }

        // 2. Dibujar laberinto de paredes texturizadas
        for (Obstaculo obs : laberinto) {
            if (imgPared != null) {
                g.drawImage(imgPared, obs.getX(), obs.getY(), obs.getAncho(), obs.getAlto(), null);
            } else {
                g.setColor(obs.getColorPared());
                g.fillRect(obs.getX(), obs.getY(), obs.getAncho(), obs.getAlto());
                g.setColor(Color.BLACK);
                g.drawRect(obs.getX(), obs.getY(), obs.getAncho(), obs.getAlto());
            }
        }

        // 3. Dibujar ítem de Aceite (Bidón personalizado)
        if (aceiteActivo) {
            if (imgAceite != null) {
                g.drawImage(imgAceite, aceiteX, aceiteY, ACEITE_TAM, ACEITE_TAM, null);
            } else { 
                g.setColor(new Color(255, 200, 0)); 
                g.fillOval(aceiteX + 3, aceiteY + 3, 10, 10); 
            }
        }

        // 4. Dibujar Sprite del Arqueólogo
        if (imgArqueologo != null) {
            g.drawImage(imgArqueologo, arq.getJugadorX(), arq.getJugadorY(), arq.getTamano(), arq.getTamano(), null);
        } else { 
            g.setColor(arq.getCuerpo()); 
            g.fillOval(arq.getJugadorX(), arq.getJugadorY(), arq.getTamano(), arq.getTamano()); 
        }

        // 5. Dibujar Sprite de la Momia
        if (imgMomia != null) {
            g.drawImage(imgMomia, momi.getMomiaX(), momi.getMomiaY(), momi.getTamano(), momi.getTamano(), null);
        } else { 
            g.setColor(momi.getColorMomia()); 
            g.fillRect(momi.getMomiaX(), momi.getMomiaY(), momi.getTamano(), momi.getTamano()); 
        }
    }

    private void renderizarLinternaCentral(Graphics2D g2d) {
        float radioLuz = 120f * (arq.getAceite() / 100f);
        if (radioLuz < 20f) radioLuz = 20f; 

        float cx = arq.getJugadorX() + (arq.getTamano() / 2.0f);
        float cy = arq.getJugadorY() + (arq.getTamano() / 2.0f);

        RadialGradientPaint mascara = new RadialGradientPaint(
            cx, cy, radioLuz,
            new float[]{0.0f, 0.4f, 1.0f},
            new Color[]{new Color(0, 0, 0, 0), new Color(0, 0, 0, 180), new Color(0, 0, 0, 255)}
        );
        g2d.setPaint(mascara);
        g2d.fillRect(0, 0, ANCHO, ALTO);
    }

    private void dibujarHUD(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(10, 10, 60, 8);
        g.setColor(new Color(255, 170, 0));
        g.fillRect(10, 10, (int) (60 * (arq.getAceite() / 100f)), 8);
        g.setColor(Color.WHITE);
        g.drawRect(10, 10, 60, 8);

        g.setFont(new Font("Monospaced", Font.BOLD, 9));
        g.drawString("TIEMPO:" + segundosSobrevividos, 170, 18);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!juegoTerminado && primerMovimiento) {
            int vel = 2;
            int dx = 0, dy = 0;
            if (movArriba) dy -= vel;
            if (movAbajo) dy += vel;
            if (movIzq) dx -= vel;
            if (movDer) dx += vel;

            if (dx != 0) {
                Rectangle futX = new Rectangle(arq.getJugadorX() + dx, arq.getJugadorY(), arq.getTamano(), arq.getTamano());
                boolean choque = false;
                for (Obstaculo obs : laberinto) if (meca.chocaConPared(futX, obs)) choque = true;
                if (!choque) arq.setJugadorX(arq.getJugadorX() + dx);
            }
            
            if (dy != 0) {
                Rectangle futY = new Rectangle(arq.getJugadorX(), arq.getJugadorY() + dy, arq.getTamano(), arq.getTamano());
                boolean choque = false;
                for (Obstaculo obs : laberinto) if (meca.chocaConPared(futY, obs)) choque = true;
                if (!choque) arq.setJugadorY(arq.getJugadorY() + dy);
            }

            momi.actualizarPerspectivaSuperior(arq.getAceite(), arq.getJugadorX(), arq.getJugadorY());
            arq.consumirAceite(0.06f);

            frames++;
            if (frames >= 60) {
                segundosSobrevividos++;
                frames = 0;
            }

            if (meca.detectarColisionMomia(arq, momi)) {
                juegoTerminado = true;
                if (musicaAmbiente != null) musicaAmbiente.stop(); 
                // Reproduce el audio del zombie cuando te atrapa (.wav convertido de .m4a)
                Sonido.reproducirSFX(getClass().getResource("/Atrapado.wav")); 
            }

            if (aceiteActivo && meca.detectarRecargaAceite(arq, aceiteX, aceiteY, ACEITE_TAM)) {
                arq.recargarAceite();
                aceiteActivo = false;
                Sonido.reproducirSFX(getClass().getResource("/Recarga.wav")); 
                aparecerAceite(); 
            }
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!primerMovimiento && (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || 
                                  e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)) {
            primerMovimiento = true;
            Sonido.reproducirSFX(getClass().getResource("/PrimerPaso.wav")); 
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) movArriba = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) movAbajo = true;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) movIzq = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) movDer = true;
        
        if (e.getKeyCode() == KeyEvent.VK_ENTER && juegoTerminado) reiniciarPartida();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) movArriba = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) movAbajo = false;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) movIzq = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) movDer = false;
    }

    @Override 
    public void keyTyped(KeyEvent e) {}
}
