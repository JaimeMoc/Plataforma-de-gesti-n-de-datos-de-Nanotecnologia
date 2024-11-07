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

# Arquitectura de datos a emplear. 

Para este proyecto se planea emplear la arquitectura delta o delta lake, ya que, en este proyecto no se requiere de una respuesta inmediata a eventos en tiempo real. El enfoque principal recae en el procesamiento por lotes para el análisis y el entrenamiento de modelos de machine learning. 

Se quiere aprovechar de esta arquitectura las transacciones ACID que nos proporcionan consistencia y confiabilidad de los datos. 
El poder mejorar el rendimiento de la consultas mediante la implementación de la optimización de los datos y el uso de indices. 
El poder manejar volumenes de datos considerables y poder aprovechar los datos históricos. 

![PIPELINE](https://github.com/JaimeMoc/Plataforma_de_gestion_de_datos_de_Nanotecnologia/blob/b72ddeebed696fcc0f8773a37f52b3a7a6fdd5fd/Historical%20Data.png)

Para este proyecto se planea combinar datos históricos (csv) con datos por streaming. Esta integración permite a los científicos tener una visión completa y actualizada de los experimentos facilitando el análisis.

## Desglose del Pipeline: 

**1.- Ingesta de datos:**
Para la ingesta de datos se empleó Databricks que permitió manejar tanto datos históricos como datos en tiempo real de manera eficiente. 
La implementación de datos en streaming se realiza mediante Apache Kafka, que captura datos generados en tiempo real por los experimentos.

**2.- Transformación de datos:**
Se usó Databricks para limpiar, transformar y preparar los datos tanto históricos como en tiempo real.

**3.- Carga y almacenamiento:**
Se usa Delta Lake para almacenar datos procesados de manera eficiente y segura.

**4.- Optimización de consultas:**
Se empleó Databricks para optimizar las consultas y mejorar el rendimiento.

**5.- Visualización de datos:**
Se usó Power BI para observar los datos obtenidos y asegurar la existencia de posibles relaciones o patrones.

**6.- Análisis y Aprendizaje Automático:**
Azure ML se utiliza para realizar análisis avanzados y construir modelos de aprendizaje automático.



