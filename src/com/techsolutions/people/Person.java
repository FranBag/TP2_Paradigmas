package people;

import classes.Project;

public class Person{
    private int id;
    private String name;
    private String dni;
    private String phone;
    private String email;
    private Project project;


    public Person(String name, String dni, String phone, String email, int id) {
        this.name = name;
        this.dni = dni;
        this.phone = phone;
        this.email = email;
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

    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }
}
