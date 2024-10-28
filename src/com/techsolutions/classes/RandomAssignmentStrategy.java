package classes;

import java.util.Random;

import people.Employee;
import utilities.MessagePrinter;

// Estrategia de asignamiento aleatorio.
public class RandomAssignmentStrategy implements TaskAssignmentStrategy{
    @Override
    public void assignTask(Task task, Employee employee){
        Random random = new Random();
        int max = task.getProject().getEmployees().size(); // Toma el máximo de empleados dentro del proyecto.
        if(max <= 0){
                MessagePrinter.error("No hay empleados en el proyecto");
                return;
        }
        int index = random.nextInt(max); // Elige un indice aleatorio de un empleado.
        Employee indexEmployee= task.getProject().getEmployees().get(index);
        indexEmployee.addMyTask(task);
        task.addEmployee(indexEmployee);
        task.attach(indexEmployee);
        MessagePrinter.log("La tarea '" + task.getName() + "' ha sido asignada de forma aleatoria a " + indexEmployee.getName());
    }
}
