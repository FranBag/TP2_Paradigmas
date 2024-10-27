package classes;
import people.Employee;
import java.util.ArrayList;
import java.util.List;

public class Task implements Subject{
    private int id;
    private String name;
    private String status;
    private Project project;
    private List<Employee> employees;
    private TaskAssignmentStrategy assignmentStrategy;
    private List<Observer> observers;

    public Task(int id, String name) {
        this.id = id;
        this.name = name;
        this.status = "PENDIENTE";
        this.employees = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyEmployees() {
        for (Observer observer : observers) {
            observer.update(name, status);
        }
    }

    public void assignEmployee(Employee employee) {
        assignmentStrategy.assignTask(this, employee);
        attach(employee);
    }

    public void setAssignmentStrategy(TaskAssignmentStrategy strategy) {
        this.assignmentStrategy = strategy;
    }

    public void changeStatus(String newStatus) {
        this.status = newStatus;
        notifyEmployees();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getStatus() { return status; }
    public List<Employee> getEmployees() { return employees; }
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
}
