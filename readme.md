# navent
navent - examen tecnico

        2)Asumiendo que muchos usuarios van a estar mandando solicitudes de manera concurrente
        una manera de hacerlo es implementando la memoria cache asignada en la capa de la lógica del negocio, para así evitar
        acceder a la capa de datos lo menos posible. La cache es performante en "lectura", indefectiblemente(obviamente esta cache
        es actualizada en cada lectura(o modificacion) ya que lo que importa es evitar ir a buscar muchas veces).
        Tmb se podria implementar una cache en el frontend, pero podria ser un problematico
        porque por ejemplo la persona puede estar utilizando el browser y tener varias pantallas abiertas entonces
        podria volverse lento, ademas de exponer los datos que pueden ser relevantes.
        A nivel del motor de base de datos seria apropiado agregar indices
        para aquellos campos que son los "habituales", en este caso por el "id" de pedido.
        En cuanto a los campos blobs por registros se puede considerar la asociacion e implementacion de
        un FileSystem para guardar los archivos en lugar de guardarlo en la BD.
        En cuanto a las escrituras lo mas conveniente seria utilizar alguna cola(Kafka,activeMQ?) para las solicitudes,
        en caso de ser muy recurrentes.
