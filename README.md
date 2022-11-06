# Instrucciones para construir la aplicación

## Sofware necesario antes de la instalacion
  - Android Studio : Se debe descargar Android Studio el cual sera el IDE de desarrollo que usaremos , para esto podemos seguir las siguientes [Instrucciones](https://developer.android.com/studio/install)
  - GIT: Debemos instalar GIT el cual usaremos para descargar el codigo fuente de la aplicación y realizar cambios de forma colaborativa ,para esto podemos seguir las siguientes [Instrucciones](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)

## Clonar repositorio

Una vez instalado el software necesario clonaremos el repositorio con el codigo del proyecto , para esto abriremos una consulta (en el caso de windows git bash) y no ubicaremos en el escritorio

![imagen](https://user-images.githubusercontent.com/98671337/200171336-a40c11af-49cc-4721-9cd4-f57cfbf5a122.png)

![imagen](https://user-images.githubusercontent.com/98671337/200171350-8470c4fd-209f-4774-a06a-1609cff1626f.png)

Una vez ubicado en el escritorio ejecutaremos el siguiente comando

```
git clone https://github.com/jaalruta/ingenieria-software-app-moviles.git
```

![imagen](https://user-images.githubusercontent.com/98671337/200171426-2a88c7b4-502c-4d26-9a53-017980aad318.png)

Comenzara la descarga del repositorio

![imagen](https://user-images.githubusercontent.com/98671337/200171446-e66d836c-6a8a-44cf-b8d4-d2954a25cfb2.png)

Con lo anterior ya tendremos el codigo fuente disponible localmente

## Importar proyecto en Android Studio

Al abrir Android Studio tendremos el siguiente menú

![imagen](https://user-images.githubusercontent.com/98671337/200171527-4f5878a9-06d9-4a75-a5ec-8257bb0216bf.png)

Aqui debemos usar el boton 
![imagen](https://user-images.githubusercontent.com/98671337/200171563-b19e6ea3-ef5c-4b21-947d-fa9a94ebdf38.png)

y luego buscamos la carpeta con la ubicacion del proyecto

![imagen](https://user-images.githubusercontent.com/98671337/200171585-d19e94b8-703a-489f-8b90-1cb058ef95e2.png)

Nos preguntara si confiamos en el proyecto

![imagen](https://user-images.githubusercontent.com/98671337/200171598-f57a87c1-6554-46ab-bef1-a499abb659b7.png)

Para este caso seleccionaremos "Trust Project"

La primera vez que abramos el proyecto es posible que nos genere el siguiente mensaje

![imagen](https://user-images.githubusercontent.com/98671337/200171709-3b5b33f2-f3bb-4640-8cbe-ed9cf492bcb6.png)

Esto es porque el directorio del SDK es diferente del equipo donde originalmente se desarrollo el proyecto , para solucionar esto tenemos dos opciones , 
Usar el Boton Ok del mismo mensaje y el IDE automaticamente ajustara la ruta , o ir al archivo local.properties y ajustar la URL del SDK , en ambos casos la URL en este archivo debe estar correcta

![imagen](https://user-images.githubusercontent.com/98671337/200171797-951e3d39-3269-4e4a-afd0-5179d0eb19c3.png)

Despues de esto debemos esperar a que Android Studio indexe el proyecto(esto suele tardar algunos minutos la primera vez)

![imagen](https://user-images.githubusercontent.com/98671337/200171854-afd49d0f-c0f6-4eab-9dc2-7ecdfcbb4441.png)

Una vez finalice no deberiamos tener ningun proceso corriendo

![imagen](https://user-images.githubusercontent.com/98671337/200171897-df19f96d-af8f-443f-bca1-b6cce85c8ddf.png)

## Ejecutar Proyecto

Para ejecutar el proyecto tenemos dos opciones , correrlo mediante un emulador o en un dispositivo fisico

### Ejecutar Proyecto en emulador

Debemos crear un emulador 
Para esto usaremos la siguiente pestaña

![imagen](https://user-images.githubusercontent.com/98671337/200172840-49ce8d39-1562-4479-8a82-8e8ccefbb9ec.png)

y luego usaremos la opción "Create Device"

![imagen](https://user-images.githubusercontent.com/98671337/200173382-d1ecac7f-d813-43e9-b6bd-88ae26edeb58.png)

En esta opción seleccionaremos el tipo de dispositivo que queremos crear

![imagen](https://user-images.githubusercontent.com/98671337/200173415-5e2beec7-ca7c-4eed-b6cb-d34b40880d11.png)

y luego daremos siguiente 

Luego debemos seleccionar la versiíon de Android que queremos usar (si no la tenemos descargada nos permitira hacerlo)
![imagen](https://user-images.githubusercontent.com/98671337/200173437-afb44fcf-61f3-41ab-afbc-cf85585b5e84.png)

Luego podremos colocar un nombre al dispositivo y usar el boton "finish" para terminar la creación
![imagen](https://user-images.githubusercontent.com/98671337/200173469-a2449ea8-7f51-4502-a25f-b597b2a5227e.png)

![imagen](https://user-images.githubusercontent.com/98671337/200173528-5e61e6cb-c3d7-4d4a-a21e-60ee3aab7e2b.png)

Para Ejecutar la aplicación en el dispositivo virtual debemos seleccionarlo en esta sección

![imagen](https://user-images.githubusercontent.com/98671337/200173587-89aa4a80-90f2-4100-bce8-9876681ff405.png)

![imagen](https://user-images.githubusercontent.com/98671337/200173600-8e77125e-7ce1-4dc3-93a9-8d3c786886a7.png)

Luego podremos usar el botón
![imagen](https://user-images.githubusercontent.com/98671337/200173626-c18c793d-04ce-4fd9-8412-f8c66901aec8.png)

Para ejecutar la app


### Ejecutar Proyecto en dispositivo fisico
primero debemos activar la depuración USB , para esto se explicara la manera mas usual (puede variar en algunos dispositivos)

 - Debemos ir a Ajustes -> Informacion del telefono
 
 ![imagen](https://user-images.githubusercontent.com/98671337/200172923-a907c89b-675e-4937-aa14-421165f0077f.png)
 
 - Luego daremos click varias vecen en "Número de compilación"
 
 ![imagen](https://user-images.githubusercontent.com/98671337/200172994-7f609ab3-a091-477f-b071-97351c04677b.png)

 
 ![imagen](https://user-images.githubusercontent.com/98671337/200172977-bd4e5250-f29f-4cec-8976-4f26efc01a02.png)

 -Luego iremos a Ajustes -> Sistema -> Avanzado -> Opciones de desarrollador
 ![imagen](https://user-images.githubusercontent.com/98671337/200173061-ec986221-f93c-4de3-921f-6688f2fc128e.png)

  Dentro de opciones para desarrollador debemos activar la opcion "Depuracion por USB"
  ![imagen](https://user-images.githubusercontent.com/98671337/200173090-441114d2-0b72-4ff4-93a2-0eefda9fdfe0.png)
  
  Con lo anterior tendremos activada la depuración por USB
  
  Una vez conectemos el dispositivo por cable y este en ejecucion android studio veremos un mensaje similar a este en el telofono
  ![imagen](https://user-images.githubusercontent.com/98671337/200173140-fdceec5d-2891-43c2-89db-550033d908c5.png)
  
  Debemos usar la opci+on de "Permitir"
  
  Luego en la pestaña
  
  ![imagen](https://user-images.githubusercontent.com/98671337/200172840-49ce8d39-1562-4479-8a82-8e8ccefbb9ec.png)
  
  Veremos que en la seccion de Physical estara el nombre de nuestro dispositivo
  
  ![imagen](https://user-images.githubusercontent.com/98671337/200173217-c262252f-e65f-40e5-b385-51ed7166ff08.png)
  
  Y en la opción de ejecución de la aplicacion tambien saldra el nombre de nuestro dispositivo
  ![imagen](https://user-images.githubusercontent.com/98671337/200173276-835658c4-0ed4-4ee0-a4b5-f8b9a5478af0.png)

  Con lo anterior al usar el boton 
  ![imagen](https://user-images.githubusercontent.com/98671337/200173331-73538bdc-222c-4b0c-b330-0775ab3de6ee.png)
  
  Podremos ejecutar la aplicación en nuestro dispostivo fisico

## Construir APK

Para construir el APK debemos usar la opción
Build -> Build Bundle(s) / APK(s) -> Build APK(s)

![imagen](https://user-images.githubusercontent.com/98671337/200173750-fa1c857f-9a94-4a93-a7a2-05cc32bc564e.png)


Andriod Studio comenzara la construcción del APK
![imagen](https://user-images.githubusercontent.com/98671337/200173845-56665e85-c178-4aa2-b1ba-501fbf40ac77.png)

Una vez termine nos generar un mensaje similar a este

![imagen](https://user-images.githubusercontent.com/98671337/200173887-740c7d9f-8e2e-49ee-b860-e2f26b7e8d4d.png)

haciendo click en "Locate" podremos ir al directorio donde genero el APK

![imagen](https://user-images.githubusercontent.com/98671337/200173914-5e803341-f68b-4f9d-bd99-a93ad7617806.png)


# Apk de app Vinilos

El APK construido se podra encontrar en el siguiente link

[apk](https://drive.google.com/file/d/1Vgk9-_G83B-umnEvwqIb2AGddF6LHxnq/view?usp=share_link)
