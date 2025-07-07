## **Arquitectura de Software y Clean Architecture**

### **1. Introducción**
- **Saludo e Introducción al Tema**  
  - "¡Hola! En este video vamos a hablar sobre **arquitectura de software**, un tema clave para diseñar aplicaciones escalables y mantenibles. Además, exploraremos un enfoque popular llamado **Clean Architecture**, y tambien cómo implementarlo con java."

---

### **2. ¿Qué es la Arquitectura de Software?**  
- **Definición general**  
  - "La **arquitectura de software** es la estructura fundamental de un sistema. Se refiere a cómo se organiza y se comunica el código dentro de la aplicación, asegurando que sea escalable, fácil de mantener y que responda bien a los cambios."
- **Importancia**
  - "Una buena arquitectura facilita la evolución del sistema, reduce los errores y mejora la productividad del equipo de desarrollo."
- **Tipos de arquitectura de software**  
  - Monolítica, microservicios, arquitectura en capas, arquitectura orientada a eventos, etc.

---

### **3. Desafíos Comunes en Arquitectura de Software**  
- **Problemas típicos sin una buena arquitectura**  
  - Acoplamiento excesivo entre componentes, dificultades al agregar nuevas funcionalidades, bajo rendimiento, y dificultades para realizar pruebas, por ejemplo queremos probar en una calculadora que todo salga bien cuando se haga x operacion o similar, seria una mala idea hacerlo en la interfaz de usuario porque necesitaria cargar en memoria un monton de cosas innecesarias  para hacerlo, una forma adecuada seria añadir por ejemplo una api que use un contrato con entidades del sistema a traves de interfaces bien definidas para luego solo verificarlo sin afectar otros modulos, de manera que como por ejemplo a cierto cliente no le parezca adecuada la manera en la que se ejecuta una funcionalidad aun si se cambia sigue utilizandose correctamente la api o con ligeros cambios.  

- **¿Por qué es crucial planificar la arquitectura desde el principio?**  
  - "La falta de una arquitectura bien pensada lleva a un código difícil de mantener, pruebas complicadas y una alta deuda técnica, e incluso a nosotros mismos, por ejemplo a quitar ciertas inseguridades tipo 'creo que si vale esto'"
---

### **4. Introducción a Clean Architecture**  
- **¿Qué es Clean Architecture?**  
  - "Clean Architecture es un patrón propuesto por **Robert C. Martin**, también conocido como **Uncle Bob**, que se centra en separar el código en capas bien definidas, promoviendo la independencia de frameworks, bases de datos y cualquier otro detalle externo."
- **Principales principios de Clean Architecture**  
  - **Independencia de Frameworks**: El negocio no debe depender de un framework específico.  
  - **Independencia de la UI**: La lógica de negocio debe ser independiente de la interfaz de usuario.  
  - **Independencia de la base de datos**: La base de datos es solo un detalle de implementación.  
  - **Pruebas**: La lógica central debe ser fácilmente testeable, si existen demasiados problemas se debe revisar esto adecuadamente por ejemplo las dependencias externas como la base de datos no se  gestionan correctamente y los desarrolladores la inyectan directamente. De esta forma un sistema probado significa una gran depurabilidad, calidad, bajos costos al detectar errores pronto y confianza entre stakeholders.
- **Las capas de Clean Architecture**  
    
    Las capas de esta arquitectura estan definidas graficamente como si se trata de un caparazon, cada capa puede interactuar con las que se encuentran en su capa y anteriores pero no con externas las cuales las desconocen, para implementar esto se debe de añadir compuertas que los comuniquen es decir interfaces que los conecten indirectamente.
  
  1. **Capa de Entidades**: Contiene las entidades y sus reglas de negocio fundamentales, no se mejoralo xd.
  2. **Capa de Casos de Uso**: Define la lógica de aplicación, por ejemplo tras x accion quiero que se guarde la informacion, se notifique al usuario y/o mas acciones. 
  3. **Capa de Adaptadores de Interfaces**: Interactúa con los usuarios o sistemas externos, por ejemplo la implementacion de controladores, presentadores o la vista en una arquitectura MVC y gateways que son intermediarios entre partes de la aplicacion y un acceso seguro, centralizado y eficiente en el sistema.
  4. **Capa de infraestructura**: Con infraestructura nos referimos a frameworks, base de datos, interfaz de usuarios e interfaces externas que se añadiran sin embargo no se relaciona directamente con la politica de la aplicacion, Implementaciones de detalles como bases de datos, servicios externos, frameworks.

---

<!-- si me da la gana -->
### **5. Ejemplo de Código Simple con Clean Architecture**  

### **6. Beneficios de Clean Architecture**  
- **Beneficios claves**  
  - **Independencia**: La lógica de negocio es independiente de frameworks, UI, bases de datos, lo que facilita cambios sin afectar todo el sistema.  
  - **Escalabilidad**: Al separar las responsabilidades, el sistema es más fácil de ampliar y modificar.  
  - **Testabilidad**: La lógica central puede ser probada sin necesidad de preocuparse por la base de datos o la UI.  
- **Aplicabilidad**  
  - **Clean Architecture** es ideal para aplicaciones complejas y de largo plazo, donde los requisitos cambian con frecuencia y se busca una separacion clara entre la logica de negocio y otros detalles del sistema.
---

Claro, aquí tienes un resumen conciso de Spring, Hibernate, JPA y PostgreSQL que podrías utilizar en tu video:

---

### **Spring Framework**:
Spring es un marco de trabajo para aplicaciones Java que facilita el desarrollo de aplicaciones empresariales, proporcionando herramientas para crear aplicaciones robustas, escalables y mantenibles. Ofrece soporte para la creación de aplicaciones web, gestión de dependencias, integración con bases de datos, entre otros. **Spring Boot** simplifica aún más el proceso de configuración y despliegue.

### **Hibernate**:
Hibernate es un framework de mapeo objeto-relacional (ORM) para Java. Su función principal es gestionar la interacción entre las clases Java y las tablas de bases de datos, permitiendo almacenar y recuperar objetos de forma sencilla. Hibernate gestiona la persistencia de los datos y se encarga de la conversión entre las entidades Java y las estructuras de la base de datos.

### **JPA (Java Persistence API)**:
JPA es una especificación de Java para el manejo de la persistencia de datos. Proporciona una interfaz estándar para trabajar con bases de datos relacionales mediante la programación orientada a objetos. JPA es el estándar utilizado por frameworks como Hibernate para simplificar la interacción con bases de datos. Con JPA, se pueden realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) de manera eficiente.

### **PostgreSQL**:
PostgreSQL es un sistema de gestión de bases de datos relacional de código abierto y muy robusto. Es conocido por su fiabilidad, soporte a transacciones y su amplia gama de características avanzadas como consultas complejas, integridad referencial y manejo de grandes volúmenes de datos. PostgreSQL se integra muy bien con Java mediante frameworks como Hibernate y JPA, ofreciendo una plataforma poderosa para desarrollar aplicaciones de bases de datos.

---
 ¡Hasta la próxima!"
