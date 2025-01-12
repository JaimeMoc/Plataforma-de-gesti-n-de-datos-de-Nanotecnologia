# NanoTech: Proyecto de Ingeniería de Datos. 

## Contexto.

En el campo de la nanotecnología, los científicos realizan experimentos sobre materiales que operan a nano escala, estos generan datos relacionados con las propiedades físicas y químicas de los materiales estudiados.

Estos datos son importantes para comprender y predecir cómo los materiales se comportan al aplicar ciertas fuerzas o condiciones, como cambios de temperatura o presión. Para poder usarlos y poder realizar predicciones precisas, es necesario el construir una estructura de datos que permita el acceso de esa información de forma optimizada.

[![images.jpg](https://i.postimg.cc/1zY0ddD1/images.jpg)](https://postimg.cc/McVjMds9)

## Objetivo.

El objetivo principal de este proyecto es desarrollar una plataforma de gestión de datos que facilite a los científicos acceder y analizar estos datos, impulsando así nuevas predicciones sobre el comportamiento de los materiales bajo distintas condiciones.  

## Datos.

Para obtener los datos que se usaran en este proyecto se empleó la herramienta de Mockaroo. Generando 3 tablas (Materiales, propiedades, condiciones). 

La primera tabla llamada materiales incluirá campos como; un “id” (“id_material”) (integer) que será la llave principal, “nombre” (text), “composicion_quimica” (text), “aplicacion” (text), y “fecha_creacion” (date). 

Tendremos una segunda tabla llamada “condiciones”, la cual estará compuesta de un “id” (“id_experimento”) que será la llave principal (integer), “id_material” que será la llave foránea con la tabla “material” (integer), “temperatura” (integer), “presión_aplicada” (numeric), “duración_experimento” (integer), “fecha_experimento” (date).   

La tercera tabla llamada “propiedades”, estará compuesta por "id" (“id_propiedad”) que será la llave principal (integer), “id_material” que será la llave foránea (integer), “resistencia” (integer), “elasticidad” (integer), “conductividad_termica” (numeric), “densidad” (numeric), “diámetro_nanoparticulas” (integer), “espesor_capa” (integer), “capacidad_absorcion_luz” (integer).  

![EBD](Data/EBD.png)

# Arquitectura de datos a emplear. 

Para este proyecto de ingeniería de datos se planea emplear una arquitectura Lambda, esta nos permite el manejar mejor los datos históricos y los datos generados en tiempo real. 

# Implementación. 

Para poder implementar los datos históricos se definieron tres capas principales:

## 1.- Capa de ingesta (Batch y Streaming): 

**Batch:** Para la ingesta de datos de los datos históricos emplearemos de la herramienta Databricks que nos permitirá gestionar eficazmente todos estos datos. Databricks emplea a su vez Spark que nos permite aplicar ETL a los datos, código en Python, SQL, Scala, etc. 

Para ello, se empleó de su versión “Community edition”, en él generamos tres carpetas principales (Bronce, Silver, y Gold) en donde se aplicaron las transformaciones.

**Streaming:** Para el proceso de ingesta de datos en tiempo real, se empleó la herramienta de Confluent Kafka, primero se instaló Docker, con el objetivo de poder procesar Kafka mediante una imagen de Docker. También se realizaron configuraciones específicas. 

Para poder generar los mensajes se generaron tres archivos .java, además de generar archivos como “pom.xml”, “producer.properties”, y tener descargado “Maven” para poder manejar los jars. 

## 2.- Capa de procesamiento:

**Batch:** Emplearemos la limpieza, transformación y almacenamiento en Delta Lake, mediante la implementación de una capa “Silver”. Esta organización (Bronce, Silver, Gold) es común dentro de la arquitectura Delta, aunque también nos servirá para obtener una mejor organización de nuestro código. 

**Streaming:** Dentro de esta parte buscamos el generar 100 mensajes que contenga datos de materiales (Nombre, id, compuesto químico, aplicación, ect), y datos de los experimentos (Elasticidad, Resistencia, etc). 
Al terminar de generar los 100 mensajes, los guardamos en un archivo csv, este a su vez lo almacenaremos de igual forma en Azure para posteriormente emplearlo.

## 3.- Visualización:
Para visualizar los resultados emplearemos de la herramienta “Power BI” que nos ayudará a generar un dashboard que nos permita obtener una visión clara y concisa de los datos. 

![Lambda](https://github.com/JaimeMoc/Plataforma_de_gestion_de_datos_de_Nanotecnologia/blob/d5a2ecd3fbd9490344280e0c77e9f9a261d60928/Lambda%20Arquitectura.png)

## Visualización de los datos finales. 

## Contribuciones.

Este proyecto está abierto a la colaboración y agradezco las contribuciones que quiera realizar :) . Si deseas participar, sigue las siguientes directrices:

### ¿Cómo contribuir?

1. **Forkea el repositorio**: Crea una copia del repositorio en tu cuenta de GitHub.
2. **Clona el repositorio**: Descarga el repositorio a tu máquina local.
   ```bash
   git clone https://github.com/JaimeMoc/Plataforma_de_gestion_de_datos_de_Nanotecnologia.git

**Contacto**: 
Si tienes preguntas o necesitas orientación para comenzar, no dudes en abrir un Issue en el repositorio o enviar un correo a:

Correo: suarezjaime2712@gmail.com
Jaime Alberto Suarez Moctezuma.
