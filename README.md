# Triquitraque

Introducción:

Triquitraque es un juego de estrategia y habilidad donde dos jugadores compiten por colocar sus fichas en un tablero de forma estratégica con el objetivo de formar una línea horizontal, vertical o diagonal antes que su oponente.

 

Requerimientos Funcionales:

 

·      Inicio del Juego:

o   Los jugadores deben poder iniciar una partida nueva.

o   Los jugadores deben seleccionar un color que sera el ultilizado para su ficha de juego.

o   Se debe permitir la selección de nombres de los jugadores.

·      Tablero:

o   El tablero debe tener un tamaño definido 3x3, 4x4 o 5x5.

o   Se debe mostrar el estado actual del tablero después de cada movimiento.

o   Debe ser capaz de determinar cuándo se ha formado una línea ganadora.

·      Jugabilidad:

o   Los jugadores deben poder colocar sus fichas en el tablero.

o   Las fichas deben estar con el color que el jugador defina.

o   Se debe validar si una casilla está ocupada antes de colocar una ficha.

o   Debe alternarse el turno de los jugadores después de cada movimiento.

o   El juego debe detectar si se ha alcanzado un empate cuando todas las casillas están ocupadas y no hay un ganador, en este caso el juego continua pero debe reiniciarse el tablero.

·      Finalización del Juego:

o   El juego debe terminar cuando un jugador forme una línea ganadora.

o   Se debe permitir iniciar una nueva partida o salir del juego después de que haya terminado una partida.

 

Orientación a Objetos:

·      Clase Jugador:

o   Atributos: nombre, color, ficha.

o   Métodos: obtenerNombre().

·      Clase Tablero:

o   Atributos: matriz para representar el estado del tablero.

o   Métodos: colocarFicha(), verificarGanador(), verificarEmpate(), mostrarTablero().

·      Clase Juego:

o   Atributos: jugadores, tablero.

o   Métodos: iniciarPartida(), finalizarPartida(), cambiarTurno(), manejarEntradaUsuario().

·      Clase Principal (Main):

o   Contiene el método main() para iniciar el juego.
