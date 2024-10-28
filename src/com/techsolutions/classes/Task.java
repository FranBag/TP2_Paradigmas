package classes;
import people.Employee;
import java.util.ArrayList;
import java.util.List;

// Clase tarea que implementará la interfáz sujeto para ser observada.
public class Task implements Subject{

    // Atributos de la clase tareas.
    private int id;
    private String name;
    private String status;
    private Project project;
    private List<Employee> employees;
    private TaskAssignmentStrategy assignmentStrategy;
    private List<Observer> observers;

    // Constructor de la clase.
    public Task(int id, String name) {
        this.id = id;
        this.name = name;
        this.status = "PENDIENTE";
        this.employees = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    // Sobreescritura de la clase attach, añade un observador a la lista de observers.
    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    // Sobreescritura de la clase detach, elimina un observador de la lista de observers.
    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    // Sobreescritura de la clase notifyEmployees, notifica a los observadores que la clase cambió.
    @Override
    public void notifyEmployees() {
        for (Observer observer : observers) {
            observer.update(name, status);
        }
    }

    // Asigna al empleado a la tarea.
    public void assignEmployee(Employee employee) {
        assignmentStrategy.assignTask(this, employee);
        System.out.println(assignmentStrategy.getClass());
        if(assignmentStrategy.getClass().toString().equals("class classes.RandomAssignmentStrategy")){ // Si la asignación es aleatoria se asigna
                                                                                                                // dentro de la propia clase de estrategia
            return;
        }else{
            attach(employee);
        }
    }

    // Cambia el método de asignación.
    public void setAssignmentStrategy(TaskAssignmentStrategy strategy) {
        this.assignmentStrategy = strategy;
    }

    // Cambia el estado de la tarea y avisa a los observadores.
    public void changeStatus(String newStatus) {
        this.status = newStatus;
        notifyEmployees();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public int getId(){ return id; }
    public String getName(){ return name; }
    public String getStatus(){ return status; }
    public List<Employee> getEmployees(){ return employees; }
    public Project getProject(){ return project; }
    public void setProject(Project project){ this.project = project; }
}
