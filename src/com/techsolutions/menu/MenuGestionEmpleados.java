package menu;

//import java.util.InputMismatchException;
import java.util.Scanner;

import classes.Company;
import classes.Project;
import people.Employee;
import people.EmployeeFactory;
import people.Manager;
import utilities.Clear;
import utilities.MyException;

public class MenuGestionEmpleados{
        static Scanner input = new Scanner(System.in);

        public static void start(){
        String name;
        String dni;
        String phone;
        String email;
        String projectName;
        // Clear.clearScreen();
        System.out.println("=============================");
        System.out.println("///Gestión de empleados///");
        System.out.println("=============================");
        System.out.println("1 - Crear nuevo empleado");
        System.out.println("2 - Listar empleados");
        System.out.println("3 - Asignar empleado a proyecto");
        System.out.println("4 - Crear nuevo gerente");
        System.out.println("5 - Listar gerentes");
        System.out.println("6 - Asignar gerente a un proyecto");
        System.out.println("0 - Atrás");

        int selection = 0;
        try{
            selection = Integer.parseInt(input.nextLine());
        }catch(NumberFormatException e){
            System.out.println("Por favor, ingrese un número valido.");
        }
        switch(selection) {
            case 0:
                return;
            case 1:
                System.out.println("Nombre del empleado:");
                name = input.nextLine();
                System.out.println("DNI:");
                dni = input.nextLine();
                System.out.println("Número de teléfono:");
                phone = input.nextLine();
                System.out.println("Email:");
                email = input.nextLine();
                System.out.println("Rol(Designer/Developer/Tester):");
                String rol = input.nextLine();
                if (name.isEmpty() || dni.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                    System.out.println("Todos los campos son obligatorios.");
                } 
                else{
                    try{
                        Employee empleado = EmployeeFactory.createEmployee(name, dni, phone, email, rol);
                        Company.getInstance().addEmployee(empleado);
                        System.out.println("Empleado " + name + " con el rol " + rol + " ha sido creado");
                    }catch(MyException e){
                        System.out.println("Rol ingresado inválido.");
                    }
                }
                input.nextLine();
                start();
                return;
            case 2:
                System.out.println("Listando todos los empleados:\n");
                for(Employee employee : Company.getInstance().getEmployees()) {
                    projectName = (employee.getProject() != null) ? employee.getProject().getName() : "No asignado";
                    System.out.println("Nombre: " + employee.getName()  + " | Proyecto: " + projectName);
                }
                input.nextLine();
                start();
                return;
            case 3:{
                for (Employee employee : Company.getInstance().getEmployees()) {
                    System.out.println("Nombre: " + employee.getName()  + " | DNI: " + employee.getDni());
                }
                System.out.println("Ingrese DNI del empleado: ");
                String dni_Employeer = input.nextLine();
                Employee employee = Company.getInstance().getEmployeeByDNI(dni_Employeer);
                if(employee != null){
                    System.out.println("Agregar al empleado " + employee.getName() + " al proyecto");

                    for (Project project : Company.getInstance().getProjects()) {
                        System.out.println("Nombre: " + project.getName() + " | ID: " + project.getId());
                    }
                    System.out.println("Ingrese ID de proyecto: ");
                    try{
                        int id_Project = Integer.parseInt(input.nextLine());
                        Project project = Company.getInstance().getProjectByID(id_Project);
                    if(project != null){
                        project.addEmployee(employee);
                        employee.setProject(project);
                        System.out.println("Se ha asignado a " + employee.getName() + " al proyecto " + project.getName());
                    }else{
                        System.out.println("No se ha encontrado un projecto con ese ID.");
                    }
                    }catch(NumberFormatException e){
                        System.out.println("Por favor, ingrese un número valido.");
                    
                    }
                }else{
                    System.out.println("No existe un empleado con ese DNI.");
                }
                input.nextLine();
                start();
                return;
            }
            case 4:
                System.out.println("Nombre del gerente:");
                name = input.nextLine();
                System.out.println("DNI:");
                dni = input.nextLine();
                System.out.println("Número de teléfono:");
                phone = input.nextLine();
                System.out.println("Email:");
                email = input.nextLine();
                if (name.isEmpty() || dni.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                    System.out.println("Todos los campos son obligatorios.");
                } 
                else{
                    Company.getInstance().addManager(new Manager(name, dni, phone, email, Company.getInstance().getSizeManagers()+1));
                    System.out.println("Gerente " + name + " creado.");
                }
                input.nextLine();
                start();
                return;
            case 5:
                System.out.println("Listando todos los gerentes:\n");
                for(Manager manager : Company.getInstance().getManagers()){
                    projectName = (manager.getProject() != null) ? manager.getProject().getName() : "No asignado";
                    System.out.println("Nombre: " + manager.getName()  + " | Proyecto: " + projectName);
                }
                input.nextLine();
                start();
                return;
            case 6:{
                for (Manager manager : Company.getInstance().getManagers()) {
                    System.out.println("Nombre: " + manager.getName()  + " | DNI: " + manager.getDni());
                }
                System.out.println("Ingrese DNI del gerente: ");
                String dni_Manager = input.nextLine();
                try{
                    Manager manager = Company.getInstance().getManagerByDNI(dni_Manager);
                    System.out.println("Asignar al gerente " + manager.getName() + " al proyecto");

                    for (Project project : Company.getInstance().getProjects()) {
                        System.out.println("Nombre: " + project.getName() + " | ID: " + project.getId());
                    }
                    System.out.println("Ingrese ID de proyecto: ");
                    try{
                        int id_Project = Integer.parseInt(input.nextLine());
                        Project project = Company.getInstance().getProjectByID(id_Project);
                        project.setManager(manager);
                        manager.setProject(project);
                        System.out.println("Se ha asignado a " + manager.getName() + " al proyecto " + project.getName());
                    }catch(NumberFormatException e){
                        System.out.println("Por favor, ingrese un número valido.");
                    }
                }catch(NullPointerException e){
                    System.out.println("Ingrese un valor correcto.");
                }
                input.nextLine();
                start();
                return;
            }
            default:
                System.out.println("Valor incorrecto");
                input.nextLine();
                start();
                return;
        }
    }
}
