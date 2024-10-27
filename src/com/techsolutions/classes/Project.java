package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import people.Customer;
import people.Employee;
import people.Manager;

public class Project{
    private int id;
    private String name;
    private Manager manager;
    private List<Employee> employees = new ArrayList<>();
    private Customer customer;
    private List<Task> tasks = new ArrayList<>();

    public Project(String name, int id){
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Manager getManager() {
        return manager;
    }
    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
    public Employee getEmployeeByDNI(String dni){
        for(Employee employee: employees){
            if(employee.getDni().equals(dni)){
                return employee;
            }
        }
        return null;
    }

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Task> getTasks() {
        return tasks;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    public void addTask(String taskName) {
        int taskId = tasks.size() + 1;
        Task newTask = new Task(taskId, taskName);
        tasks.add(newTask);
        newTask.setProject(this);
        System.out.println("Tarea '" + taskName + "' creada en el proyecto '" + name + "'.");
    }
    public Task getTaskByID(int id){
        for(Task task: tasks){
            if(task.getId() == id){
                return task;
            }
        }
        return null;
    }

    public void showProjectSummary() {
        System.out.println("Resumen del proyecto: " + name);
        for (Task task : tasks) {
            System.out.println("Tarea ID: " + task.getId() + ", Nombre: " + task.getName() + ", Estado: " + task.getStatus());
        }
    }

    public Map<String, Integer> getTaskCountByStatus() {
        Map<String, Integer> statusCount = new HashMap<>();
        for (Task task : tasks) {
            String status = task.getStatus();
            statusCount.put(status, statusCount.getOrDefault(status, 0) + 1);
        }
        return statusCount;
    }

}
    
    
