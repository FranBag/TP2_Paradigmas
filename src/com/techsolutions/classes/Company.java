package classes;

import java.util.ArrayList;
import java.util.List;

import people.Customer;
import people.Employee;
import people.Manager;

public class Company{
    
    // Atributos de la clase Company
    private static Company instance;
    private String name;
    private List<Project> projects;
    private List<Customer> customers;
    private List<Manager> managers;
    private List<Employee> employees;

    // Constructor de la clase
    private Company(){
        this.projects = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.managers = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public static Company getInstance(){
        if(instance == null){
            instance = new Company();
        }
        return instance;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Crea un proyecto, le asigna un ID y lo añade a la lista de proyectos.
    public void createProject(String name){
        int id = getSizeProjects() + 1;
        projects.add(new Project(name, id));
    }
    public List<Project> getProjects() {
        return projects;
    }
    public Project getProjectByID(int id){ // Obtiene un proyecto según el ID.
        for(Project project: projects){
            if(project.getId() == id){
                return project; 
            }
        }
        return null; // Si no lo encuentra devuelve null.
    }
    public Project getProjectByName(String name){ // Obtiene un proyecto según el nombre.
        for(Project project: projects){
            if(project.getName().equals(name)){
                return project;
            }
        }
        return null; // Si no lo encuentra devuelve null.
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
    public List<Customer> getCustomers() {
        return customers;
    }
    public Customer getCustomerByDNI(String dni){ // Obtiene un cliente según su DNI.
        for(Customer customer: customers){
            if(customer.getDni().equals(dni)){
                return customer;
            }
        }
        return null; // Si no lo encuentra devuelve null.
    }


    public void addManager(Manager manager) {
        managers.add(manager);
    }
    public List<Manager> getManagers(){
        return managers;
    }
    public Manager getManagerByDNI(String dni){ // Obtiene un gerente según su DNI.
        for(Manager manager: managers){
            if(manager.getDni().equals(dni)){
                return manager;
            }
        }
        return null;  // Si no lo encuentra devuelve null.
    }


    public void addEmployee(Employee employee){ 
        employees.add(employee);
    }
    public List<Employee> getEmployees(){
        return employees;
    }
    public Employee getEmployeeByDNI(String dni){ // Obtiene un empleado según su DNI.
        for(Employee employee: employees){
            if(employee.getDni().equals(dni)){
                return employee;
            }
        }
        return null; // Si no lo encuentra devuelve null.
    }

    // Métodos encargados de obtener el tamaño de las listas de la companía.
    public int getSizeProjects(){
        return projects.size();
    }
    public int getSizeCustomers(){
        return customers.size();
    }
    public int getSizeManagers(){
        return managers.size();
    }
    public int getSizeEmployees(){
        return employees.size();
    }
}