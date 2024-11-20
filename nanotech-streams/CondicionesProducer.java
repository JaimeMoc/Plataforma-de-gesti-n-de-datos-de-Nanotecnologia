import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;

public class CondicionesProducer {

    public static void main(String[] args) {
        // Configuración del productor de Kafka
        Properties producerProps = new Properties();
        producerProps.put("bootstrap.servers", "localhost:9092");
        producerProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(producerProps);

        Random random = new Random();
        int[] temperaturas = {300, 400, 500, 600};  // Valores típicos de temperatura en experimentos
        double[] presiones = {1.0, 1.5, 2.0, 2.5};  // Presiones aplicadas en atmósferas
        int[] duraciones = {30, 60, 120, 180};      // Duraciones de los experimentos en minutos

        // Generar mensajes para el tópico condiciones-topic
        for (int i = 1; i <= 10; i++) {  // Generar 10 mensajes
            int idExperimento = random.nextInt(1000) + 1;  // ID aleatorio del experimento
            int idMaterial = random.nextInt(100) + 1;      // ID aleatorio del material
            int temperatura = temperaturas[random.nextInt(temperaturas.length)];
            double presion = presiones[random.nextInt(presiones.length)];
            int duracion = duraciones[random.nextInt(duraciones.length)];

            // Crear mensaje JSON
            String condicionesJson = String.format(
                "{\"id_experimento\":%d,\"id_material\":%d,\"temperatura\":%d,\"presion_aplicada\":%.1f,\"duracion_experimento\":%d}",
                idExperimento, idMaterial, temperatura, presion, duracion
            );

            // Enviar mensaje al tópico condiciones-topic
            producer.send(new ProducerRecord<>("condiciones-topic", Integer.toString(idExperimento), condicionesJson));
            System.out.println("Mensaje enviado: " + condicionesJson);
        }

        // Cerrar el productor
        producer.close();
    }
}
