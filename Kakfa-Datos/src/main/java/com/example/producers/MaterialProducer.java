// Jaime Alberto Suarez Moctezuma.
// Importamos las librerías.
package com.example.producers;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

// Creación de la clase MaterialProducer. 
// Esta clase genera datos simulados sobre materiales nanotecnológicos y los guarda en un archivo CSV.
public class MaterialProducer {

    public static void main(String[] args) {
        // Inicialización de utilidades para generar datos aleatorios.
        Random random = new Random();
        // Datos simulados para materiales.
        String[] prefijos = {"Nano", "Micro", "Ultra", "Super"};
        String[] usuarios = {"UsuarioA", "UsuarioB"};
        String[] materiales = {"partículas", "tubos", "láminas", "esferas"};
        String[] elementos = {"de óxido de samario", "de plata", "de zinc", "de carbono", "de oro", "de cobre", "de titanio", "de hierro", "de sílice"};
        String[] composicionesQuimicas = {"(Sm2O3)", "(Ag)", "(ZnO)", "(C)", "(Au)", "(Cu)", "(Ti)", "(Fe)", "(SiO2)"};
        String[] aplicaciones = {"Fotocatálisis", "Antimicrobiano", "Protectores solares", "Conductores", "Sensores"};
        int[] temperaturas = {20, 15, 32, 9, 42}; // Temperatura aleatoria.
        double[] presiones = {1.0, 1.5, 2.0, 2.5, 3.0}; // Presión aleatoria.
        int[] duraciones = {10, 20, 30, 40, 50}; // Duración aleatoria.

        // Formato de fecha para los datos generados.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Nombre del archivo CSV.
        String csvFile = "datos_streaming.csv";

        try (FileWriter writer = new FileWriter(csvFile)) {
            // Escribir la cabecera del archivo CSV.
            writer.append("id_material,nombre,usuario,composicion_quimica,aplicacion,fecha_creacion,temperatura,presion,duracion\n");

            // Generar datos para 100 materiales.
            for (int i = 1; i <= 100; i++) {
                // Generación de datos aleatorios para un material.
                int idMaterial = random.nextInt(1000) + 1;  // ID aleatorio del material.
                String nombre = prefijos[random.nextInt(prefijos.length)] + " " +
                                materiales[random.nextInt(materiales.length)] + " " +
                                elementos[random.nextInt(elementos.length)];
                String usuario = usuarios[random.nextInt(usuarios.length)];
                String composicion = composicionesQuimicas[random.nextInt(composicionesQuimicas.length)];
                String aplicacion = aplicaciones[random.nextInt(aplicaciones.length)];
                String fechaCreacion = LocalDate.now().minusDays(random.nextInt(365)).format(formatter); // Fecha aleatoria
                int temperatura = temperaturas[random.nextInt(temperaturas.length)];
                double presion = presiones[random.nextInt(presiones.length)];
                int duracion = duraciones[random.nextInt(duraciones.length)];

                // Escribir los datos en el archivo CSV.
                writer.append(idMaterial + "," + nombre + "," + usuario + "," + composicion + "," + aplicacion + "," + fechaCreacion + "," + temperatura + "," + presion + "," + duracion + "\n");
            }

            System.out.println("Los datos se han guardado en '" + csvFile + "'.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}