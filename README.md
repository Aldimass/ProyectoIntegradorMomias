# 🏛️ Proyecto Integrador: Momias (Etapa 1)
**Institución:** Universidad del Valle de México (UVM)  
**Materia:** Programación Intermedia / Sistemas Concurrentes  

##  Descripción del Proyecto
Este proyecto simula un sistema de supervivencia en un entorno de pirámides utilizando **Programación Concurrente** en Java. El sistema gestiona múltiples entidades (Arqueólogos y Momias) que operan de forma independiente mediante hilos, interactuando con recursos limitados.

##  Características Técnicas
* **Multithreading:** Uso de la clase `Thread` para el movimiento autónomo de las entidades.
* **Sincronización:** Implementación de bloques `synchronized` para gestionar el consumo de "Aceite" y evitar condiciones de carrera.
* **Interfaz Gráfica (GUI):** Ventana desarrollada en `Swing` que permite al usuario interactuar con el sistema en tiempo real (Recarga de combustible).
* **Arquitectura:** Basada en herencia y polimorfismo con una clase base `Entidad`.

##  Estructura del Código
* `Entidad.java`: Clase abstracta que define las propiedades base (nombre, x, y).
* `Arqueologo.java`: Hilo que consume aceite para desplazarse.
* `Momia.java`: Hilo de movimiento constante y autónomo.
* `Aceite.java`: Recurso compartido con métodos sincronizados.
* `Main.java`: Punto de entrada y gestión de la interfaz gráfica.

##  Reflexión Técnica (HITL)
El desarrollo siguió una metodología **Human-in-the-loop (HITL)**, integrando asistencia de Inteligencia Artificial (Gemini) para la optimización de algoritmos, bajo la supervisión y validación lógica del desarrollador para cumplir con los estándares académicos de la UVM.

