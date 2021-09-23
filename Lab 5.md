Nombres:Diego Alejandro león 

`	     `Juan Sebastian Ospina

MVC PRIMEFACES INTRODUCTION
# PARTE I. - JUGANDO A SER UN CLIENTE HTTP
1. Abra una terminal Linux o consola de comandos Windows.
1. Realice una conexión síncrona TCP/IP a través de Telnet al siguiente servidor:
   1. Host: www.escuelaing.edu.co
   1. Puerto: 80
1. Teniendo en cuenta los parámetros del comando telnet:
   telnet HOST PORT
   1. Telnet [www.escuelaing.edu.co](http://www.escuelaing.edu.co) 80
1. Antes de que el servidor cierre la conexión por falta de comunicación:
   1. Revise la página 36 del [RFC del protocolo HTTP](https://tools.ietf.org/html/rfc2616), sobre cómo realizar una petición GET. Con esto, solicite al servidor el recurso ‘sssss/abc.html’, usando la versión 1.0 de HTTP.
      1. GET/abc.html HTTP/1.0
      1. Host: escuelaing.edu.co
   1. Asegúrese de presionar ENTER dos veces después de ingresar el comando.

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.001.png)

1. Revise el resultado obtenido. ¿Qué codigo de error sale?, revise el significado del mismo en [la lista de códigos de estado HTTP](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes).
- 301 Moved Permanently: Esta y todas las solicitudes futuras deben dirigirse al URI dado.
  1. ¿Qué otros códigos de error existen?, ¿En qué caso se manejaron?
     1. 1xx informational response – la solicitud fue recibida, proceso continuo
     1. 2xx successful – la solicitud fue recibida, comprendida y aceptada con éxito
     1. 3xx redirection – Se deben tomar más medidas para completar la solicitud.
     1. 4xx client error –la solicitud contiene una sintaxis incorrecta o no se puede cumplir
     1. ` `5xx server error –el servidor no cumplió con una solicitud aparentemente válida

` `Realice una nueva conexión con telnet, esta vez a:

1. Host: www.httpbin.org
1. Puerto: 80
1. Versión HTTP: 1.1

Ahora, solicite (GET) el recurso */html*. ¿Qué se obtiene como resultado?

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.002.png)

¡Muy bien!, ¡Acaba de usar el protocolo HTTP sin un navegador Web!. Cada vez que se usa un navegador, éste se conecta a un servidor HTTP, envía peticiones (del protocolo HTTP), espera el resultado de las mismas, y -si se trata de contenido HTML- lo interpreta y dibuja.

5. Seleccione el contenido HTML de la respuesta y copiarlo al portapapeles CTRL-SHIFT-C. Ejecute el comando wc (*word count*) para contar palabras con la opción -c para contar el número de caracteres:
   wc -c
   ` `Pegue el contenido del portapapeles con CTRL-SHIFT-V y presione CTRL-D (fin de archivo de Linux). Si no termina el comando wc presione CTRL-D de nuevo. No presione mas de dos veces CTRL-D indica que se termino la entrada y puede cerrarle la terminal. Debe salir el resultado de la cantidad de caracteres que tiene el contenido HTML que respondió el servidor.
   Claro está, las peticiones GET son insuficientes en muchos casos. Investigue: ¿Cuál es la diferencia entre los verbos GET y POST? ¿Qué otros tipos de peticiones existen?
   1. ![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.003.png)
   1. En el tipo de petición GET los parámetros URL se guardan junto al URL mientras que con POST no. Por lo que POST ofrece mayor discreción debido a que tampoco se guardan los parámetros URL en el caché ni en el registro del servidor caso contrario en GET que se guardan sin cifrar.
   1. - OPTIONS

\- HEAD solo revisa los headers

\- PUT	deposita en la ubicación de destino

\- DELETE elimina en la ubicación de destino

\- CONNECT 

\- TRACE 

5. En la practica no se utiliza telnet para hacer peticiones a sitios web sino el comando curl con ayuda de la linea de comandos:
   curl [www.httpbin.org](http://www.httpbin.org)

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.004.png)
Utilice ahora el parámetro -v y con el parámetro -i:
curl -v [www.httpbin.org](http://www.httpbin.org)

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.005.png)

curl -i [www.httpbin.org](http://www.httpbin.org)

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.006.png)
¿Cuáles son las diferencias con los diferentes parámetros?

curl -v Make the operation more talkative

curl -i Include protocol response headers in the output



como se ve en la imágenes el i incluye todo el protocolo de respuesta la v hacia una mayor  descripción de todo lo que pasa


# PARTE II. - HACIENDO UNA APLICACIÓN WEB DINÁMICA A BAJO NIVEL.
En este ejercicio, va a implementar una aplicación Web muy básica, haciendo uso de los elementos de más bajo nivel de Java-EE (Enterprise Edition), con el fin de revisar los conceptos del protocolo HTTP. En este caso, se trata de un módulo de consulta de clientes Web que hace uso de una librería de acceso a datos disponible en un repositorio Maven local.

I. Para esto, cree un proyecto maven nuevo usando el arquetipo de aplicación Web estándar maven-archetype-webapp y realice lo siguiente:

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.007.png)

1. mvn archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-webapp -DarchetypeVersion=1.4

Revise la clase Sample Servlet incluida a continuación, e identifique qué hace:

Revise qué valor tiene el parámetro ‘urlPatterns’ de la anotación [@WebServlet](http://docs.oracle.com/javaee/6/tutorial/doc/bnafu.html), pues este indica qué URLs atiende las peticiones del servlet

La clase Sample Servlet extiende de una clase que se va a encargar de el protocolo http y esta clase servlet esta sobreescribiendo el método de doGet que se encarga de lo que es el GET del protocolo HTTP. la variable urlPatterns es la que va a almacenar la ruta URL para usar el protocolo http

1. En el pom.xml, modifique la propiedad "packaging" con el valor "war". Agregue la siguiente dependencia:
1. Revise en el pom.xml para qué puerto TCP/IP está configurado el servidor embebido de Tomcat (ver sección de plugins).
- Puerto 8080
1. Compile y ejecute la aplicación en el servidor embebido Tomcat, a través de Maven con:
   mvn package

mvn tomcat7:run

1. Abra un navegador, y en la barra de direcciones ponga la URL con la cual se le enviarán peticiones al ‘SampleServlet’. Tenga en cuenta que la URL tendrá como host ‘localhost’, como puerto, el configurado en el pom.xml y el path debe ser el del Servlet. Debería obtener un mensaje de saludo.
   1. ![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.008.png)
   1. ![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.009.png)
1. Observe que el Servlet ‘SampleServlet’ acepta peticiones GET, y opcionalmente, lee el parámetro ‘name’. Ingrese la misma URL, pero ahora agregando un parámetro GET (si no sabe como hacerlo, revise la documentación en [http://www.w3ols.com/tags/ref_httpmethods.asp](http://www.w3schools.com/tags/ref_httpmethods.asp)).
   1. ![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.010.png)
   1. <http://localhost:8080/helloServlet?name=Juan>
1. Busque el artefacto gson en el repositorio de maven y agregue la dependencia.

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.011.png)

1. En el navegador revise la dirección <https://jsonplaceholder.typicode.com/todos/1>. Intente cambiando diferentes números al final del path de la url.

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.012.png)

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.013.png)

1. Basado en la respuesta que le da el servicio del punto anterior, cree la clase edu.eci.cvds.servlet.model.Todo con un constructor vacío y los métodos getter y setter para las propiedades de los "To Dos" que se encuentran en la url indicada.
1. Utilice la siguiente clase para consumir el servicio que se encuentra en la dirección url del punto anterior.
1. Cree una clase que herede de la clase HttpServlet (similar a SampleServlet), y para la misma sobrescriba el método heredado doGet. Incluya la anotación @Override para verificar –en tiempo de compilación-- que efectivamente se esté sobreescribiendo un método de las superclases.
1. Para indicar en qué URL el servlet interceptará las peticiones GET, agregue al método la anotación @WebServlet, y en dicha anotación, defina la propiedad urlPatterns, indicando la URL (que usted defina) a la cual se asociará el servlet.
1. Teniendo en cuenta las siguientes métodos disponibles en los objetos ServletRequest y ServletResponse recibidos por el método doGet:
   1. response.setStatus(N); <- Indica con qué código de error N se generará la respuesta. Usar la clase HttpServletResponse para indicar el código de respuesta.
   1. request.getParameter(param); <- Consulta el parámetro recibido, asociado al nombre ‘param’.
   1. response.getWriter() <- Retorna un objeto PrintWriter a través del cual se le puede enviar la respuesta a quien hizo la petición.
   1. response.setContentType(T) <- Asigna el tipo de contenido (MIME type) que se entregará en la respuesta.

Implemente dicho método de manera que:

1. Asuma que la petición HTTP recibe como parámetro el número de id de una lista de cosas por hacer (todo), y que dicha identificación es un número entero.
1. Con el identificador recibido, consulte el item por hacer de la lista de cosas por hacer, usando la clase "Service" creada en el punto 10.
1. Si el item existe:
   1. Responder con el código HTTP que equivale a ‘OK’ ([ver referencia anterior](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes)), y como contenido de dicha respuesta, el código html correspondiente a una página con una tabla que tenga los detalles del item, usando la clase "Service" creada en el punto 10 par crear la tabla.
1. Si el item no existe:
   1. Responder con el código correspondiente a ‘no encontrado’, y con el código de una página html que indique que no existe un item con el identificador dado.
   1. Si no se paso parámetro opcional, o si el parámetro no contiene un número entero, devolver el código equivalente a requerimiento inválido.
   1. Si se genera la excepcion MalformedURLException devolver el código de error interno en el servidor
   1. Para cualquier otra excepcion, devolver el código equivalente a requerimiento inválido.

1. Una vez hecho esto, verifique el funcionamiento de la aplicación, recompile y ejecute la aplicación.
1. Intente hacer diferentes consultas desde un navegador Web para probar las diferentes funcionalidades.
- Funcionando:

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.014.png)

- No encontrado:

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.015.png)

- Sin parámetros:

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.016.png)

# PARTE III.
16. En su servlet, sobreescriba el método doPost, y haga la misma implementación del doGet.
1. Cree el archivo index.html en el directorio src/main/webapp/index.html de la siguiente manera:
16. En la página anterior, cree un formulario que tenga un campo para ingresar un número (si no ha manejado html antes, revise http://www.w3schools.com/html/ ) y un botón. El formulario debe usar como método ‘POST’, y como acción, la ruta relativa del último servlet creado (es decir la URL pero excluyendo ‘http://localhost:8080/’).
16. Revise [este ejemplo de validación de formularios con javascript](http://www.w3schools.com/js/js_validation.asp) y agruéguelo a su formulario, de manera que -al momento de hacer ‘submit’- desde el browser se valide que el valor ingresado es un valor numérico.
16. Recompile y ejecute la aplicación. Abra en su navegador en la página del formulario, y rectifique que la página hecha anteriormente sea mostrada. Ingrese los datos y verifique los resultados. Cambie el formulario para que ahora en lugar de POST, use el método GET . Qué diferencia observa?

- POST:

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.017.png)

- GET:

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.018.png)

La principal diferencia que se puede observar es que con el método POST no se permite visualizar los parámetros en el URL mientras que con el método GET si se puede.

16. ¿Qué se está viendo? Revise cómo están implementados los métodos de la clase Service.java para entender el funcionamiento interno.
- Lo que hace es crear una tabla en formato html de manera automática con los parámetros tomados de los getter de la clase Todo. 
# PARTE IV. - FRAMEWORKS WEB MVC – JAVA SERVER FACES / PRIME FACES
En este ejercicio, usted va a desarrollar una aplicación Web basada en el marco JSF, y en una de sus implementaciones más usadas: [PrimeFaces](http://primefaces.org/). 

Escriba una aplicación web que utilice PrimeFaces para calcular la media, la moda, la desviación estándar y varianza de un conjunto de N números reales. Este conjunto de N números reales deben ser ingresados por el usuario de manera que puedan ser utilizados para los cálculos.

Diagrama de casos de uso de la aplicación:

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.019.png)


1. Al proyecto Maven, debe agregarle las dependencias más recientes de javax.javaee-api, com.sun.faces.jsf-api, com.sun.faces.jsf-impl, javax.servlet.jstl y Primefaces (en el archivo pom.xml).
1. Para que configure automáticamente el descriptor de despliegue de la aplicación (archivo web.xml), de manera que el *framework* JSF se active al inicio de la aplicación, en el archivo web.xml agregue la siguiente configuración:
1. Revise cada una de las configuraciones agregadas anteriormente para saber qué hacen y por qué se necesitan. Elimine las que no se necesiten.
   1. JavaEE-API: Como su nombre lo dice trae la api de java.
   1. Jsf-api: Esta trae el api de Java Server Faces con clases abstractas e interfaces
   1. Jsf-impl: Contienen la implementación con código funcional y no clases abstractas e interfaces 
   1. jstl esta contiene etiquetas para el html que permiten encapsular determinadas acciones.
1. Ahora, va a crear un Backing-Bean de sesión, el cual, para cada usuario, mantendrá de lado del servidor las siguientes propiedades:
   1. El conjunto de datos ingresados por el usuario.
   1. Los resultados de las operaciones.
   1. La cantidad de números ingresados por el usuario.
1. Para hacer esto, cree una clase que tenga:
   1. el constructor por defecto (sin parámetros)
   1. los métodos get/set necesarios dependiendo si las propiedades son de escritura o lectura
   1. coloque las anotaciones:
      1. @ManagedBean, incluyendo el nombre: @ManagedBean(name = "calculadoraBean").
      1. @ApplicationScoped.
1. A la implementación de esta clase, agregue los siguientes métodos:
   1. calculateMean: Debe recibir como parámetro el listado de valores y retornar el promedio de los números en ella.
   1. calculateStandardDeviation: Debe recibir como parámetro el listado de valores y retornar el la desviación estandar de los números en ella.
   1. calculateVariance: Debe recibir como parámetro el listado de valores y retornar la varianza de los números en ella.
   1. calculateMode: Debe recibir como parámetro el listado de valores y retornar la moda de los números en ella.
   1. restart: Debe volver a iniciar la aplicación (Borrar el campo de texto para que el usuario agregue los datos).
1. Cree una página XHTML, de nombre calculadora.xhtml (debe quedar en la ruta src/main/webapp). Revise en la [página 13 del manual de PrimeFaces](http://www.primefaces.org/docs/guide/primefaces_user_guide_5_2.pdf), qué espacios de nombres XML requiere una página de PrimeFaces y cuál es la estructura básica de la misma.
1. Con base en lo anterior, agregue un formulario con identificador calculadora\_form con el siguiente contenido básico:
1. Al formulario, agregue:
   1. Un elemento de tipo <p:outputLabel> para el resultado de la moda, sin embargo, este elemento se debe ocultar. Para ocultarlo, se puede agregar el estilo display: none; al elemento. Una forma de hacerlo es por medio de la propiedad style.
      1. En una aplicacion real, no se debería tener este elemento, solo se crea con el fin de simplificar una prueba futura.
   1. Un elemento <p:inputText>para que el usuario ingrese los números. (Tenga en cuenta que una opción para separar los números es con “;” aunque no necesariamente debe hacerlo así) 
      Por ejemplo:
      2; 3.5; 4.8; 5.1
   1. Un elemento de tipo <p:outputLabel> para mostrar cada una de las operaciones resultantes. Y asocie dichos elementos al BackingBean de sesión a través de su propiedad value, y usando como referencia el nombre asignado: value="#{calculadoraBean.nombrePropiedad}"
1. Al formulario, agregue dos botones de tipo <p:commandButton>, cuatro para enviar la lista de números ingresados y ver el calculo de cada valor, y otro para reiniciar el juego.
   1. El botón de *Calculo de valores* debe tener asociado a su propiedad update el nombre del formulario en el que se agregaron los campos antes descritos, de manera que al hacer clic, se ejecute un ciclo de JSF y se *refresque* la vista.
   1. Debe tener también una propiedad actionListener con la cual se le indicará que, al hacer clic, se ejecutará el método CalculateXXX, creado en el backing-bean de sesión:
      <p:commandButton update="calculadora\_form" actionListener="#{calculadoraBean.calculateXXX}">...
   1. El botón de reiniciar juego tendrá las mismas propiedades de update y actionListener del otro con el valor correspondiente:
      <p:commandButton update="…" actionListener="…">
1. Para verificar el funcionamiento de la aplicación, agregue el plugin tomcat-runner dentro de los plugins de la fase de construcción (build). Tenga en cuenta que en la configuración del plugin se indica bajo que ruta quedará la aplicación:
   1. mvn package
   1. mvn tomcat7:run
1. Si no hay errores, la aplicación debería quedar accesible en la URL: [http://localhost:8080/faces/calculadora.xhtml](http://localhost:8080/faces/guess.xhtml)
1. Si todo funcionó correctamente, realice las siguientes pruebas:
   1. Abra la aplicación en un explorador. Realice algunas pruebas de aceptación con la aplicación.

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.020.png)![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.021.png)![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.022.png)

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.023.png)![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.024.png)

1. Abra la aplicación en dos computadores diferentes. Si no dispone de uno, hágalo en dos navegadores diferentes (por ejemplo Chrome y Firefox; incluso se puede en un único navegador usando una ventana normal y una ventana de incógnito / privada). Haga cinco intentos en uno, y luego un intento en el otro. ¿Qué valor tiene cada uno?

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.025.png)

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.026.png)

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.027.png)

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.028.png)

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.029.png)


1. Aborte el proceso de Tomcat-runner haciendo Ctrl+C en la consola, y modifique el código del backing-bean de manera que use la anotación @SessionScoped en lugar de @ApplicationScoped. Reinicie la aplicación y repita el ejercicio anterior.

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.030.png)

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.031.png)

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.032.png)

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.033.png)

![](IMG/Aspose.Words.95eda224-9547-4ae5-b40a-28696fb148ac.034.png)



1. Dado la anterior, ¿Cuál es la diferencia entre los backing-beans de sesión y los de aplicación?
- Backing bean es cualquier bean referenciado por un formulario mientas que un managed bean es un bean de respaldo que ha sido registrado con JSF y es creado automáticamente por el mismo cuando se necesita.
  1. Por medio de las herramientas de desarrollador del explorador (Usando la tecla "F12" en la mayoría de exploradores):
     1. Ubique el código HTML generado por el servidor.
     1. Busque el elemento oculto, que contiene el número generado aleatoriamente.
     1. En la sección de estilos, deshabilite el estilo que oculta el elemento para que sea visible.
     1. Observe el cambio en la página, cada vez que se realiza un cambio en el estilo.
     1. Revise qué otros estilos se pueden agregar a los diferentes elementos y qué efecto tienen en la visualización de la página.
     1. Actualice la página. Los cambios de estilos realizados desaparecen, pues se realizaron únicamente en la visualización, la respuesta del servidor sigue siendo la misma, ya que el contenido de los archivos allí almacenados no se ha modificado.
     1. Revise qué otros cambios se pueden realizar y qué otra información se puede obtener de las herramientas de desarrollador.
1. Para facilitar los intentos del usuario, se agregará una lista de los últimos valores ingresados:
   1. Agregue en el Backing-Bean, una propiedad que contenga una lista de valores ingresados por el usuario.
   1. Cuando se reinicie la aplicación, limpie el contenido de la lista.
   1. Busque cómo agregar una tabla a la página, cuyo contenido sea la lista de listas de números.




