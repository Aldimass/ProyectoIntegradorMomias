import javax.swing.*;
import java.awt.event.*;

public class Main extends JFrame {
    // El recurso que compartirán las entidades
    private static RecursoCompartido recursoShared = new RecursoCompartido(); 

    public Main() {
        // --- CONFIGURACIÓN DE LA INTERFAZ GRÁFICA (Tu código original) ---
        setTitle("SISTEMA DE CONTROL - ETAPA 2");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new java.awt.FlowLayout());

        JButton botonRecarga = new JButton("🔋 RECARGAR ACEITE");
        add(new JLabel("Control de la Lámpara:"));
        add(botonRecarga);

        // Acción del botón: Recarga el aceite usando el método de la clase RecursoCompartido
        botonRecarga.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                recursoShared.recargarAceite();
            }
        });

        setVisible(true); 
    }

    public static void main(String[] args) {
        // 1. EJECUCIÓN DE PRUEBAS ESTÁTICAS (Lo que la profa calificará)
        System.out.println("=== INICIANDO PRUEBAS DE EVALUACIÓN ===");
        testEntidad();     // Prueba Integrante 1
        testArqueologo();  // Prueba Integrante 2
        testMomia();       // Prueba Integrante 3
        testRecurso();     // Prueba Integrante 4
        System.out.println("=== FIN DE PRUEBAS ===\n");

        // 2. LANZAMIENTO DE LA GUI
        new Main(); 

        // 3. INSTANCIACIÓN DE ENTIDADES
        // Nota: Se crean los objetos para validar que la jerarquía funciona.
        Arqueologo ark = new Arqueologo("Arqueologo_1", recursoShared);
        Momia m1 = new Momia("Momia_Ramses", 5, 5);

        // Los hilos se activarán en el Parcial 3 según instrucciones.
        // ark.start(); 
        // m1.start();
    }

    // ==========================================================
    // MÉTODOS ESTÁTICOS DE PRUEBA (REQUERIMIENTO DE EVALUACIÓN)
    // ==========================================================

    public static void testEntidad() {
        System.out.println("[Test Integrante 1]: Verificando herencia en Entidad...");
        // Se prueba la lógica base de posición
        Momia m = new Momia("Test", 10, 10);
        m.setX(20);
        System.out.println("Resultado: Posición actualizada a " + m.getX());
    }

    public static void testArqueologo() {
        System.out.println("[Test Integrante 2]: Verificando Arqueólogo y consumo...");
        RecursoCompartido tempRC = new RecursoCompartido();
        Arqueologo a = new Arqueologo("Indy_Test", tempRC);
        a.huir(); // Debe intentar consumir aceite
    }

    public static void testMomia() {
        System.out.println("[Test Integrante 3]: Verificando ataque de Momia...");
        Momia m = new Momia("Momia_Test", 0, 0);
        m.actuar(); // Ejecuta su comportamiento base
    }

    public static void testRecurso() {
        System.out.println("[Test Integrante 4]: Verificando Recurso Compartido...");
        recursoShared.recargarAceite();
        System.out.println("Depósito actual: " + recursoShared.getCantidad());
    }
}

