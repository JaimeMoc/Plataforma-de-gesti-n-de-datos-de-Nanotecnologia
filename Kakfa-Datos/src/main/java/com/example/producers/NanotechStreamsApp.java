// Jaime Alberto Suarez Moctezuma.
// Importamos las librerías.
package com.example.producers;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

// Creación de la clase NanotechStreamAPP. aplicación NanotechStreamsApp.
// Esta clase configura y ejecuta una aplicación Kafka Streams que procesa datos de materiales nanotecnológicos y sus condiciones experimentales.
public class NanotechStreamsApp {
     // Logger para registrar eventos e información del programa.
    private static final Logger logger = Logger.getLogger(NanotechStreamsApp.class.getName());

    public static void main(String[] args) {
        // Configuración del logger para escribir en un archivo de log.
        try {
            FileHandler fh = new FileHandler("NanotechStreamsApp.log");
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Configuración de las propiedades para emplear Kafka Streams.
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("streams.properties")) {
            props.load(fis);
        } catch (IOException e) {
            logger.severe("Error cargando las propiedades: " + e.getMessage());
            return;
        }

        // Configuración de propiedades específicas de Kafka Streams.
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "nanotech-experiments");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        // Constructor de Streams para definir los tópicos y su procesamiento.
        StreamsBuilder builder = new StreamsBuilder();

        // Definición de los Streams para los tópicos de Materiales y Condiciones.
        KStream<String, String> materialStream = builder.stream("material-topic");
        KStream<String, String> condicionesStream = builder.stream("condiciones-topic");

        // Procesamiento de cada flujo: log de cada mensaje recibido.
        materialStream.foreach((key, value) -> logger.info("Material: " + value));
        condicionesStream.foreach((key, value) -> logger.info("Condiciones: " + value));

        // Creación de la instancia de KafkaStreams con la topología definida.
        KafkaStreams streams = new KafkaStreams(builder.build(), props);

        // Manejo de excepciones durante el inicio de los streams.
        try {
            streams.start();
        } catch (Exception e) {
            logger.severe("Error iniciando los streams: " + e.getMessage());
        }

        // Agregar un shutdown hook para cerrar los streams correctamente.
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                streams.close();
            } catch (Exception e) {
                logger.severe("Error cerrando los streams: " + e.getMessage());
            }
        }));
    }
}