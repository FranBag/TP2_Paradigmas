package classes;

import people.Employee;

// Estrategia de asignamiento directo.
public class DirectAssignmentStrategy implements TaskAssignmentStrategy {
    @Override
    public void assignTask(Task task, Employee employee) {
        employee.addMyTask(task);
        task.addEmployee(employee);
        System.out.println("La tarea '" + task.getName() + "' ha sido asignada directamente a " + employee.getName());
    }
}