package com.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;
import java.util.Random;

public class NanotechStreamsApp {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "nanotech-experiments");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> materialStream = builder.stream("material-topic");
        KStream<String, String> condicionesStream = builder.stream("condiciones-topic");

        materialStream.foreach((key, value) -> System.out.println("Material: " + value));
        condicionesStream.foreach((key, value) -> System.out.println("Condiciones: " + value));

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

        generateAndSendData();
    }

    private static void generateAndSendData() {
        Random random = new Random();
        String [] nombreMaterial = {"Nanoparticulas de óxido de samario", "Nanoparticulas de plata", "Oxido de zinc"};
        String [] compuestoQuimico = {"(TiO2)", "(Ag)", "(ZnO)"};

        String nombresmateriales = nombreMaterial[random.nextInt(nombreMaterial.length)];
        String compuestosQuimicos = compuestoQuimico[random.nextInt(compuestoQuimico.length)];
        int idMaterial = random.nextInt(1000) + 1;

        String materialJson = String.format(
        "{\"id_material\":%d,\"nombre\":\"%s\",\"compuesto_quimico\":\"%s\"}",
        idMaterial, nombreMaterial, compuestoQuimico
        );
    
        Properties producerProps = new Properties();
        producerProps.put 
    }
}
