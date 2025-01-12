# Kafka_Datos

## Descripción

Esta carpeta contiene el código necesario para generar y procesar mensajes de Kafka, simulando un flujo de datos en streaming. El código se desarrolló tomando como referencia diversos repositorios de GitHub, videos de YouTube y páginas web, creando un modelo básico pero funcional.

## Contenido

### Código en Kafka

El código se divide en tres archivos principales:

1. **NanotechStreamsApp.java**: Código principal que detalla datos importantes.
2. **MaterialProducer.java**: Genera datos de los materiales, incluyendo id, nombre, composición química, aplicación y fecha de creación.
3. **CondicionesProducer.java**: Incluye los datos de los experimentos.

### Creación de Tópicos

Para generar y pasar los mensajes, se requieren algunas configuraciones especiales. Es necesario tener una estructura de carpetas y archivos adecuada, configurar un archivo `pom.xml`, instalar un controlador como `maven`, y un archivo `producer.properties`. Los tópicos creados son:

1. `./kafka-topics --create --topic material-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1`
2. `./kafka-topics --create --topic condiciones-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1`
3. `./kafka-topics --create --topic NanotechStreamsApp-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1`

### Almacenamiento de Mensajes

Los mensajes generados se guardan en formato CSV para su posterior procesamiento en plataformas como Databricks. Los archivos generados son:

- `datos_experimento_streaming.csv`
- `datos_streaming.csv`
