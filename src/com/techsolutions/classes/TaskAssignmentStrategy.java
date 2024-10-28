package classes;
import people.Employee;

// Interfáz de estrategias de asignamiento.
public interface TaskAssignmentStrategy {
    void assignTask(Task task, Employee employee);
}
