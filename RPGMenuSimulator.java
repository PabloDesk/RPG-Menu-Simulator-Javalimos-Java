import java.util.Scanner;

public class RPGMenuSimulator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // --- VARIABLES DEL PERSONAJE ---
        String nombre = "Sin Nombre";
        String tipo = "Ninguno";
        int vida = 0;
        int vidaMaxima = 0;
        int fuerza = 0;
        boolean personajeCreado = false;

        // --- VARIABLES DEL SISTEMA ---
        int opcionPrincipal = 0;
        String[] inventario = {"Poción de Vida", "Escudo de Hierro", "Pergamino Mágico", "Llave Antigua"};

        // 1. MENU PRINCIPAL - Usando do-while
        do {
            System.out.println("\n=== RPG MENU SIMULATOR ===");
            System.out.println("1. Crear personaje");
            System.out.println("2. Entrenar (Subir Stats)");
            System.out.println("3. Batalla (Simulación)");
            System.out.println("4. Ver Inventario");
            System.out.println("5. Ver Estado del personaje");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción: ");

            // Validación de entrada simple
            if (sc.hasNextInt()) {
                opcionPrincipal = sc.nextInt();
                sc.nextLine(); // Limpiar el buffer
            } else {
                System.out.println("Introduce un número válido");
                sc.nextLine(); // Limpiar error
                continue;
            }

            switch (opcionPrincipal) {

                case 1: // --- CREAR PERSONAJE (Variables + If/Else) ---
                    System.out.println("Introduce el nombre de tu héroe: ");
                    nombre = sc.nextLine();

                    System.out.println("Elige clase: 1. Guerrero | 2. Mago | 3. Arquero");
                    int claseElegida = sc.nextInt();

                    if (claseElegida == 1) {
                        tipo = "Guerrero";
                        vida = 100;
                        fuerza = 15;
                    } else if (claseElegida == 2) {
                        tipo = "Mago";
                        vida = 70;
                        fuerza = 25;
                    } else if (claseElegida == 3) {
                        tipo = "Arquero";
                        vida = 85;
                        fuerza = 18;
                    } else {
                        System.out.println("Clase no válida. Se asignará 'Aldeano NPC' por defecto.");
                        tipo = "Aldeano";
                        vida = 50;
                        fuerza = 5;
                    }
                    vidaMaxima = vida;
                    personajeCreado = true;
                    System.out.println("¡Personaje " + nombre + " el " + tipo + " creado con éxito!");
                    break;

                case 2: // --- ENTRENAMIENTO (Bucle while) ---
                    if (!personajeCreado) {
                        System.out.println("Primero debes crear un personaje (Opción 1).");
                    } else {
                        int opcionEntreno = -1;
                        while (opcionEntreno != 0) {
                            System.out.println("\n--- ZONA DE ENTRENAMIENTO ---");
                            System.out.println("1. Levantar pesas (+5 Fuerza)");
                            System.out.println("2. Meditar (+10 Vida Máxima)");
                            System.out.println("0. Volver al menú principal");
                            System.out.print("Elección: ");
                            opcionEntreno = sc.nextInt();

                            if (opcionEntreno == 1) {
                                fuerza += 5;
                                System.out.println("¡Tu fuerza ahora es " + fuerza + "!");
                            } else if (opcionEntreno == 2) {
                                vidaMaxima += 10;
                                vida = vidaMaxima; // Curar al entrenar
                                System.out.println("¡Tu vida máxima ahora es " + vidaMaxima + "!");
                            } else if (opcionEntreno != 0) {
                                System.out.println("Opción inválida");
                            }
                        }
                    }
                    break;

                case 3: // --- BATALLA (Bucle for) ---
                    if (!personajeCreado) {
                        System.out.println("No puedes pelear sin personaje.");
                    } else {
                        System.out.println("\n¡Un Ogro salvaje aparece!");
                        int vidaEnemigo = 100;

                        for (int turno = 1; turno <= 5; turno++) {
                            System.out.println(">> TURNO " + turno + "<<");

                            // Daño al enemigo
                            vidaEnemigo -= fuerza;
                            System.out.println("Atacas al Ogro causando " + fuerza + " de daño.");

                            // Daño al jugador (simulado 10 fijo)
                            vida -= 10;
                            System.out.println("El Ogro te golpe. Vida restante: " + vida);

                            if (vida <= 0) {
                                System.out.println("¡HAS SIDO DERROTADO! ¡JAVALISTE!");
                                vida = 1; // Resucitar con 1 de vida para seguir jugando
                                break;
                            }
                            if (vidaEnemigo <= 0) {
                                System.out.println("¡Has vencido al Ogro antes de tiempo!");
                                break;
                            }
                            if (turno == 5) {
                                System.out.println("El combate termina en empate por agotamiento");
                            }
                        }
                    }
                    break;

                case 4: // --- INVENTARIO (Bucle for-each) ---
                    System.out.println("\n--- TU MOCHILA ---");
                    int contador = 1;
                    for (String item : inventario) {
                        System.out.println(contador + ". " + item);
                        contador++;
                    }
                    break;

                case 5: // --- ESTADO (If / Else y Operadores) ---
                    if (!personajeCreado) {
                        System.out.println("No hay datos que mostrar");
                    } else {
                        System.out.println("\n--- ESTADO ACTUAL ---");
                        System.out.println("Nombre: " + nombre + " | Clase: " + tipo);
                        System.out.println("Fuerza: " + fuerza + " | Vida " + vida + "/" + vidaMaxima);

                        // Lógica de salud
                        if (vida > (vidaMaxima * 0.8)) {
                            System.out.println("Estado: Estás en excelente estado.");
                        } else if (vida > (vidaMaxima * 0.4)) {
                            System.out.println("Estado: Estado moderado.");
                        } else {
                            System.out.println("Estado: Cuidado, estás herido de gravedad.");
                        }
                    }
                    break;

                case 6: // --- SALIR ---
                    System.out.println("Guardando progreso... ¡Chao, " + nombre + "!");
                    break;

                default:
                    System.out.println("Opción inválida, intenta de nuevo.");
            }
        } while (opcionPrincipal != 6);

        sc.close();
    }
}
