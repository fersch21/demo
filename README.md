Informatorio 2022 Java-Spring 

Trabajo práctico integrador


Crear una API rest para poder sacar turnos para cualquier evento o motivo que cualquier empresa  u organización cargue en el sistema. No es necesario estar autenticado para interactuar con la API.

Organización:
Deberá poder agregarse una empresa o organización que consta con los siguientes datos, nombre, cuit, dirección donde está registrada, teléfono de contacto, email de contacto, fecha de alta (en la que se dio de alta en el sistema) y una clave de alfanumérica de 20 a 40 caracteres, dicha clave se usará cuando la organización quiera crear un evento o motivo al cual las personas podrán sacar turno o si la organización desea eliminar el evento o motivo o si desea eliminar se la organización del sistema.

Evento:
El evento será generado por alguna organización y las personas podrán sacar un turno para asistir.
El evento tendrá nombre, ubicación, un campo que indique si está activo el evento (estará inactivo si la fecha del mismo ya pasó), la fecha en la que la creó la organización,  también se deberá indicar si es algo ocasional/ único es decir un evento que es en un momento particular como por ejemplo un recital, una convención o si será algo recurrente es decir que suceda todos los días por ejemplo ir al medico, turno para ir a un gimnasio, ir a un bar etc. 
●	En  caso de que sea un evento único u ocasional la persona no deberá ingresar la fecha ya que la fecha es única y ya la cargo la organización en el evento
●	En caso de que sea un evento recurrente del día a día deberá indicar la fecha y la hora.(No puede haber dos turnos con la misma fecha y hora) y debe haber
●	Una empresa no podrá tener más de 1 eventos con el mismo nombre activos

Persona es entidad : Una persona podrá sacar un turno para algún evento, no necesita estar registrado, deberá enviar el/los nombre/s de la persona, su apellido, su dni, el nombre del evento, el nombre de la organización, la fecha y hora en caso de que corresponda, se generará una clave para que el usuario modifique sus datos o se de baja, tendrá un campo que indique si esta activo o no.

Turno: El turno tendrá la fecha y hora en caso de que corresponda por ejemplo para ir a un turno médico o ir al gimnasio o un restaurante, en caso de que no corresponda por que es un evento como un recital o convención tendrá la fecha, tendrá un código alfabético generado aleatoriamente el cual no podrá ser repetido entre dos turnos activos, un campo que indique si el turno está activo o no (indica si la fecha del turno es anterior a la fecha actual).

●	Deberá contar con las entidades necesarias, incluir más si cree que necesita.
●	Cada entidad deberá tener su dto y su wrapper correspondiente.
●	Deberá validarse los datos recibidos y emitir un mensaje correspondiente en caso de que no lo sean.
●	Deberá utilizarse un controller advice para manejar los errores de manera centralizada.
●	Utilizar swagger para documentar la api
●	Utilizar interfaces de manera correcta para no depender de las implementaciones.
●	Un controlador por cada entidad.
●	El controlador de usuario va a poder traer todos los usuarios, un método para filtrar usuarios por apellido, y buscar usuario por dni.
●	El controlador de los turnos va a tener un método para traer todos los turnos de una organización dada,  (opcional traer todos los turnos inactivos), otro método para traer los turnos activos  según una organización y un evento, un método para crear un evento o modificar un evento activo y borrar un evento (con clave de organización).
●	El controlador de la empresa va a tener un método para registrar una empresa, modificar una empresa o eliminar una empresa (solicitará clave para las últimas 2 nombradas.), un método para traer los datos de una empresa activa según su cuit o nombre, y un método para traer todas las empresas registradas activas.
●	El controlador del usuario podrá dar de alta un usuario, modificar o eliminarlo y deberá validar la clave del mismo en las últimas 2. Un método que traiga todos los usuarios activos y un método que traiga un usuario por dni.

![image](https://user-images.githubusercontent.com/102974719/207161608-ba790fa4-85c9-4077-955f-65f356be10c2.png)
