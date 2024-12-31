// Jaime Alberto Suarez Moctezuma.
// Importación de las librerías necesarias.
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;
import java.util.Random;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Creación de la clase Material Producer. 
// Esta clase implementa un productor de Apache Kafka que genera datos simulados sobre materiales nanotecnológicos y los envía al tópico "material-topic".
public class MaterialProducer {

    public static void main(String[] args) {
        // Configuración del productor de Kafka. 
        Properties producerProps = new Properties();
        producerProps.put("bootstrap.servers", "localhost:9092");
        producerProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Creación del productor con las propiedades configuradas.
        KafkaProducer<String, String> producer = new KafkaProducer<>(producerProps);

        // Inicialización de utilidades para generar datos aleatorios.
        Random random = new Random();
        // Datos simulados para materiales.
        String[] nombresMateriales = {"Nanopartículas de óxido de samario", "Nanopartículas de plata", "Óxido de zinc"};
        String[] composicionesQuimicas = {"(Sm2O3)", "(Ag)", "(ZnO)"};
        String[] aplicaciones = {"Fotocatálisis", "Antimicrobiano", "Protectores solares"};

        // Formato de fecha para los datos generados.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            // Generar mensajes para el tópico material-topic.
            for (int i = 1; i <= 10; i++) { // Generar 10 mensajes.
                // Generación de datos aleatorios para un material.
                int idMaterial = random.nextInt(1000) + 1;  // ID aleatorio del material.
                String nombre = nombresMateriales[random.nextInt(nombresMateriales.length)];
                String composicion = composicionesQuimicas[random.nextInt(composicionesQuimicas.length)];
                String aplicacion = aplicaciones[random.nextInt(aplicaciones.length)];
                String fechaCreacion = LocalDate.now().minusDays(random.nextInt(365)).format(formatter); // Fecha aleatoria

                // Crear mensaje JSON.
                String materialJson = String.format(
                    "{\"id_material\":%d,\"nombre\":\"%s\",\"composicion_quimica\":\"%s\",\"aplicacion\":\"%s\",\"fecha_creacion\":\"%s\"}",
                    idMaterial, nombre, composicion, aplicacion, fechaCreacion
                );

                // Enviar mensaje al tópico material-topic.
                producer.send(new ProducerRecord<>("material-topic", Integer.toString(idMaterial), materialJson));
                // Imprimir el mensaje enviado en la consola.
                System.out.println("Mensaje enviado: " + materialJson);
            }

        } catch (Exception e) {
            // Manejo de excepciones durante la producción de mensajes.
            e.printStackTrace();
        } finally {
            // Cerrar el productor.
            producer.close();
        }
    }
}