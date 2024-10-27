package classes;

import java.util.Random;

import people.Employee;

public class RandomAssignmentStrategy implements TaskAssignmentStrategy{
    @Override
    public void assignTask(Task task, Employee employee){
        Random random = new Random();
        int max = task.getProject().getEmployees().size();
        int index = random.nextInt(max);
        Employee indexEmployee= task.getProject().getEmployees().get(index);
        indexEmployee.addMyTask(task);
        task.addEmployee(indexEmployee);
        System.out.println("La tarea '" + task.getName() + "' ha sido asignada de forma aleatoria a " + indexEmployee.getName());
    }
}
