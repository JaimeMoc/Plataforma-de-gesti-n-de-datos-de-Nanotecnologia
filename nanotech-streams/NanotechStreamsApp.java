// Proyecto Nanotecnologia. 

// Importamos las líbrerias. 
package com.example;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import java.util.Properties;


public class NanotechStreamsApp {
    public static void main(String[] args) {
        // Configuración de las propiedades para emplear Kafka Streams.
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "nanotech-experiments");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        // Constructor de Streams para definir los tópicos y su procesamiento. 
        StreamsBuilder builder = new StreamsBuilder();

        // Definición de los Streams para los topicos de Materiales y Condiciones. 
        KStream<String, String> materialStream = builder.stream("material-topic");
        KStream<String, String> condicionesStream = builder.stream("condiciones-topic");

        materialStream.foreach((key, value) -> System.out.println("Material: " + value));
        condicionesStream.foreach((key, value) -> System.out.println("Condiciones: " + value));

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

    }
}
