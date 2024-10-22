# Proyecto: Planeación de gestión de datos de nanotecnologia. 

## Contexto
En el campo de la nanotecnología, los científicos realizan experimentos sobre materiales que operan a nano escala, estos generan datos relacionados con las propiedades físicas y químicas de los materiales estudiados.

Estos datos son importantes para comprender y predecir cómo los materiales se comportan al aplicar ciertas fuerzas o condiciones, como cambios de temperatura o presión. Para poder usarlos y poder realizar predicciones precisas, es necesario el construir una estructura de datos que permita el acceso de esa información de forma optimizada.

[![images.jpg](https://i.postimg.cc/1zY0ddD1/images.jpg)](https://postimg.cc/McVjMds9)

## Objetivo
El objetivo principal de este proyecto es desarrollar una plataforma de gestión de datos que facilite a los científicos acceder y analizar estos datos, impulsando así nuevas predicciones sobre el comportamiento de los materiales bajo distintas condiciones.  

## Datos

Para obtener los datos que se usaran en este proyecto se empleo la herramienta de Mockaroo. Generando 3 tablas (Materiales, propiedades, condiciones). 

La primera tabla llamada materiales incluirá campos como; un “id” (“id_material”) (integer) que será la llave principal, “nombre” (text), “composicion_quimica” (text), “aplicacion” (text), y “fecha_creacion” (date). 

Tendremos una segunda tabla llamada “condiciones”, la cual estará compuesta de un “id” (“id_experimento”) que será la llave principal (integer), “id_material” que será la llave foránea con la tabla “material” (integer), “temperatura” (integer), “presión_aplicada” (numeric), “duración_experimento” (integer), “fecha_experimento” (date).   

La tercera tabla llamada “propiedades”, estará compuesta por id (“id_propiedad”) que será la llave principal (integer), “id_material” que será la llave foránea (integer), “resistencia” (integer), “elasticidad” (integer), “conductividad_termica” (numeric), “densidad” (numeric), “diámetro_nanoparticulas” (integer), “espesor_capa” (integer), “capacidad_absorcion_luz” (integer).  

![EBD](Data/EBD.png)

## Flujo de trabajo. 

![Flujodedatos](https://github.com/JaimeMoc/Plataforma_de_gestion_de_datos_de_Nanotecnologia/blob/e72407033b7995a9ffbbbf5c1bb187355d5025bd/Diagrama%20.png)

### Ingesta de Datos
- **Azure Data Factory**: Utiliza Azure Data Factory para orquestar y automatizar la ingesta de datos desde diversas fuentes (bases de datos, APIs, archivos, etc.).
- **Azure Blob Storage**: Almacena los datos crudos en Azure Blob Storage para un acceso y procesamiento eficiente.

### Procesamiento de Datos
- **Databricks**: Configura un clúster de Databricks en Azure para el procesamiento de datos. Databricks proporciona un entorno optimizado para Apache Spark.
- **Apache Spark**: Utiliza Spark para el procesamiento en paralelo de grandes volúmenes de datos. Puedes emplear Spark SQL para consultas estructuradas y Spark MLlib para tareas de machine learning.

### Almacenamiento de Datos Procesados
- **Azure Data Lake Storage**: Almacena los datos procesados en Azure Data Lake Storage, que ofrece escalabilidad y seguridad.
- **Delta Lake**: Implementa Delta Lake sobre Azure Data Lake Storage para garantizar la integridad de los datos y permitir transacciones ACID.

### Análisis y Modelado
- **Databricks Notebooks**: Utiliza notebooks en Databricks para que los científicos de datos puedan explorar y analizar los datos de manera interactiva.
- **Azure Machine Learning**: Integra Azure Machine Learning para desarrollar, entrenar y desplegar modelos predictivos.

### Visualización y Reportes
- **Power BI**: Conecta Power BI a los datos procesados para crear dashboards interactivos y reportes que faciliten la toma de decisiones.
- **Azure Synapse Analytics**: Utiliza Azure Synapse para análisis avanzados y consultas en tiempo real.

### Monitoreo y Mantenimiento
- **Azure Monitor**: Implementa Azure Monitor para supervisar el rendimiento del pipeline y detectar posibles problemas.
- **Databricks Jobs**: Programa y automatiza tareas recurrentes en Databricks para mantener el flujo de datos actualizado.
