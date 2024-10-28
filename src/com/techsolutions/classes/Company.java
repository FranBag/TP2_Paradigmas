package classes;

import java.util.ArrayList;
import java.util.List;

import people.Customer;
import people.Employee;
import people.Manager;

public class Company{
    private static Company instance;
    private String name;
    private List<Project> projects;
    private List<Customer> customers;
    private List<Manager> managers;
    private List<Employee> employees;


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

    public void createProject(String name){
        int id = getSizeProjects() + 1;
        projects.add(new Project(name, id));
    }
    public List<Project> getProjects() {
        return projects;
    }
    public Project getProjectByID(int id){
        for(Project project: projects){
            if(project.getId() == id){
                return project;
            }
        }
        return null;
    }
    public Project getProjectByName(String name){
        for(Project project: projects){
            if(project.getName().equals(name)){
                return project;
            }
        }
        return null;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
    public List<Customer> getCustomers() {
        return customers;
    }
    public Customer getCustomerByDNI(String dni){
        for(Customer customer: customers){
            if(customer.getDni().equals(dni)){
                return customer;
            }
        }
        return null;
    }


    public void addManager(Manager manager) {
        managers.add(manager);
    }
    public List<Manager> getManagers(){
        return managers;
    }
    public Manager getManagerByDNI(String dni){
        for(Manager manager: managers){
            if(manager.getDni().equals(dni)){
                return manager;
            }
        }
        return null;
    }


    public void addEmployee(Employee employee){
        employees.add(employee);
    }
    public List<Employee> getEmployees(){
        return employees;
    }
    public Employee getEmployeeByDNI(String dni){
        for(Employee employee: employees){
            if(employee.getDni().equals(dni)){
                return employee;
            }
        }
        return null;
    }

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