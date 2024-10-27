# Trabajo Práctico N° 2.1

La empresa **"Tech Solutions"** gestiona **Proyectos** para diversos **Clientes**. Cada Proyecto tiene un **Gerente de Proyecto**, varios **Empleados** asignados, y **tareas** a realizar. El sistema debe permitir:

1. **Agregar Proyectos y Clientes** al sistema.
2. **Asignar empleados** a los proyectos y definir sus roles (Desarrollador, Tester, etc.).
3. **Registrar tareas** dentro de cada proyecto y asociarlas a empleados, permitiendo gestionar su estado (pendiente, en curso, finalizada).
4. **Mostrar un resumen** del estado de un proyecto: cantidad de tareas finalizadas, pendientes y en curso.
5. **Utilizar los patrones de diseño** para:
   - **Singleton**: Para la clase que gestiona la lista de proyectos y empleados.
   - **Factory Method**: Para crear diferentes tipos de Empleados.
   - **Adapter**: Para integrar el sistema con una base de datos antigua que almacena información en un formato diferente.
   - **Observer**: Para notificar a los empleados cuando una tarea cambia de estado.
   - **Strategy**: Para implementar diferentes estrategias de asignación de tareas a empleados.

## Requerimientos:

1. **Desarrollar el código en Java**, utilizando los patrones mencionados.
2. **Realizar un Diagrama de Clases**, mostrando las relaciones entre las clases y los patrones utilizados.
3. **Exponer y defender el trabajo**, explicando la elección de los patrones de diseño (**14 de octubre del 2024**).
