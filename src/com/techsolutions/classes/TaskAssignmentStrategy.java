package classes;
import people.Employee;

public interface TaskAssignmentStrategy {
    void assignTask(Task task, Employee employee);
}
