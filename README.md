========================================================================
                      TUMBA LABERINTO - RETRO 8-BIT
========================================================================

1. PLATAFORMA DE DESARROLLO Y VERSIÓN
-------------------------------------
* Lenguaje: Java 17 / 21+ (Compatible con Java Virtual Machines modernas)
* Sistema de Gestión: Apache Maven
* Entorno de Desarrollo (IDE): Visual Studio Code (VS Code)
* Sistema Operativo Base: macOS (arquitectura Apple Silicon / M2)
* Librerías Utilizadas: Java Swing y Java AWT (Gráficos nativos de renderizado 2D)

2. AUTORES Y CONTACTO
---------------------
* Autor(es): [Tu Nombre Completo / Nombre de tu equipo]
* Institución: Universidad del Valle de México (UVM)
* Carrera: Ingeniería en Ciencia de Datos
* Correo Electrónico de Contacto: [tu.correo@estudiante.uvm.mx]

3. MECÁNICA DEL JUEGO
---------------------
* Perspectiva: Vista aérea (Top-Down) en un laberinto retro de 16x15 bloques.
* Objetivo: El jugador controla a un arqueólogo atrapado en una tumba oscura y debe sobrevivir el mayor tiempo posible esquivando a una momia que lo acecha constantemente.
* Mecánica de Linterna (Luz Limitada): El mapa está completamente a oscuras. La visibilidad del jugador depende del nivel de aceite de su linterna, el cual disminuye progresivamente con el tiempo. El radio de iluminación se encoge si el aceite escasea.
* Mecánica de Recarga (Ítem Aceite): Para mantener la linterna encendida, el jugador debe buscar y recolectar bidones de aceite que aparecen de manera aleatoria en los espacios vacíos del laberinto.
* IA de la Momia: La momia rastrea dinámicamente la posición del jugador en tiempo real en base a su nivel de visibilidad para acorralarlo.
* Controles:
  - Flechas de Dirección (Arriba, Abajo, Izquierda, Derecha): Mover al Arqueólogo.
  - Tecla Enter: Reiniciar la partida una vez que has sido atrapado.

4. LICENCIAS DE IMÁGENES (CRÉDITOS GRÁFICOS)
--------------------------------------------
Todos los elementos visuales emplean una estética Pixel Art diseñada y/o adaptada a una resolución nativa de 256x240 píxeles:
* Arqueólogo (Sprite de jugador): Recurso Pixel Art personalizado ('Arqueologo.png').
* Momia (Sprite de enemigo): Recurso Pixel Art personalizado ('Momia.png').
* Bloque de Pared (Textura de obstáculos): Bloque Pixel Art texturizado de 16x16 píxeles ('BloquePared.png').
* Suelo / Fondo de la Cueva: Textura modular retro para el suelo de la cripta ('Fondo.png').
* Bidón de Aceite (Ítem consumible): Sprite Pixel Art de 16x16 píxeles de un contenedor de combustible ('Aceite.png').
* Tipo de Licencia Gráfica: Atribución de uso libre para fines educativos y académicos (CC BY / Uso No Comercial).

5. LICENCIAS DE SONIDO (CRÉDITOS DE AUDIO)
------------------------------------------
El apartado de audio digital utiliza codificación PCM Lineal a un formato estricto WAVE (.wav) de 16 bits a 44,100 Hz:
* Música de Ambientación ('Ambiente.wav'): 
  - Archivo de origen: "soulja-unit-broken-tune-07.ogg" por SouljaUnit.
  - Licencia: Creative Commons (CC BY / CC0) - Uso Libre.
* Efecto de Sonido de Muerte ('Atrapado.wav'):
  - Archivo de origen: "zombie-choking.m4a" por 317music.
  - Descripción: Sonido de ataque/asfixia de criatura al colisionar con el jugador.
  - Licencia: Creative Commons (CC BY) - Uso Libre con fines académicos.
* Otros efectos de audio integrados en el código: 'PrimerPaso.wav' y 'Recarga.wav'.

========================================================================
                © 2026 - Proyecto Académico - UVM
========================================================================
