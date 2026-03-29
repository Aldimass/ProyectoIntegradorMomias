import javax.swing.*;
import java.awt.event.*;

public class Main extends JFrame {
    private static Aceite recursoShared = new Aceite(); // El recurso que compartirán los hilos

    public Main() {
        // Configuración de la Ventana (Interfaz Gráfica)
        setTitle("SISTEMA DE CONTROL - ETAPA 1");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new java.awt.FlowLayout());

        JButton botonRecarga = new JButton("🔋 RECARGAR ACEITE");
        add(new JLabel("Control de la Lámpara:"));
        add(botonRecarga);

        // Cuando haces CLIC en el botón, recargas el aceite compartido
        botonRecarga.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                recursoShared.recargar();
            }
        });

        setVisible(true); // Hace visible la ventana
    }

    public static void main(String[] args) {
        new Main(); // Abre la ventana para que el usuario "juegue"
        
        // Creación de los hilos de Arqueólogo y Momia
        // IMPORTANTE: Les pasamos el MISMO objeto de Aceite (recursoShared)
        Arqueologo ark = new Arqueologo("Arqueologo_1", 0, 0, recursoShared);
        Momia m1 = new Momia("Momia_Ramses", 5, 5);

        // ".start()" les da vida y hace que empiecen a correr sus métodos "run"
        ark.start();
        m1.start();
    }
}
