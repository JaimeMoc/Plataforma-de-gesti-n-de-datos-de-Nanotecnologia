import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;

public class MaterialProducer {

    public static void main(String[] args) {
        // Configuración del productor de Kafka
        Properties producerProps = new Properties();
        producerProps.put("bootstrap.servers", "localhost:9092");
        producerProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(producerProps);

        Random random = new Random();
        // Datos simulados para materiales
        String[] nombresMateriales = {"Nanopartículas de óxido de samario", "Nanopartículas de plata", "Óxido de zinc"};
        String[] composicionesQuimicas = {"(Sm2O3)", "(Ag)", "(ZnO)"};
        String[] aplicaciones = {"Fotocatálisis", "Antimicrobiano", "Protectores solares"};

        // Generar mensajes para el tópico material-topic
        for (int i = 1; i <= 10; i++) { // Generar 10 mensajes
            int idMaterial = random.nextInt(1000) + 1;  // ID aleatorio del material
            String nombre = nombresMateriales[random.nextInt(nombresMateriales.length)];
            String composicion = composicionesQuimicas[random.nextInt(composicionesQuimicas.length)];
            String aplicacion = aplicaciones[random.nextInt(aplicaciones.length)];
            String fechaCreacion = "2024-11-15"; // Fecha fija, se puede usar aleatoria si se prefiere

            // Crear mensaje JSON
            String materialJson = String.format(
                "{\"id_material\":%d,\"nombre\":\"%s\",\"composicion_quimica\":\"%s\",\"aplicacion\":\"%s\",\"fecha_creacion\":\"%s\"}",
                idMaterial, nombre, composicion, aplicacion, fechaCreacion
            );

            // Enviar mensaje al tópico material-topic
            producer.send(new ProducerRecord<>("material-topic", Integer.toString(idMaterial), materialJson));
            System.out.println("Mensaje enviado: " + materialJson);
        }

        // Cerrar el productor
        producer.close();
    }
}
