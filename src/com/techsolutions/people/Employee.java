package people;

import java.util.ArrayList;
import java.util.List;

import classes.Observer;
import classes.Task;

public abstract class Employee extends Person implements Observer{
    private List<Task> tasks;


    public Employee(String name, String dni, String phone, String email, int id){
        super(name, dni, phone, email, id);
        this.tasks = new ArrayList<>();
    }

    public abstract void trabajar();

    public abstract String getRole();

    public List<Task> getTasks() { return tasks; }

    public void addMyTask(Task task) {
        tasks.add(task);
    }

    @Override
    public void update(String taskName, String newStatus) {
        System.out.println("Avisando a " + getName() + ": La tarea " + taskName + " cambió su estado a: " + newStatus);
    }
}
