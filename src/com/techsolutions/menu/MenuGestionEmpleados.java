package menu;

import java.util.Scanner;

import classes.Company;
import classes.Project;
import people.Employee;
import people.EmployeeFactory;
import people.Manager;
import utilities.Clear;
import utilities.MessagePrinter;

public class MenuGestionEmpleados{
        static Scanner input = new Scanner(System.in);

        public static void start(){
        String name;
        String dni;
        String phone;
        String email;
        String projectName;

        Clear.clearScreen();
        
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
            MessagePrinter.error();
            start();
            return;
        }
        switch(selection) {
            case 0:
                return;
            case 1:
                Clear.clearScreen();
                System.out.println("Nombre del empleado:");
                name = input.nextLine();
                System.out.println("DNI:");
                dni = input.nextLine();
                System.out.println("Número de teléfono:");
                phone = input.nextLine();
                System.out.println("Email:");
                email = input.nextLine();
                System.out.println("Rol:\n"+
                "1 - Designer\n" + 
                "2 - Developer\n" +
                "3 - Tester");
                String rol = input.nextLine();
                if (name.isEmpty() || dni.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                    MessagePrinter.error("Todos los campos son obligatorios");
                } 
                else{
                    try{
                        switch (Integer.parseInt(rol)){
                            case 1:
                                rol = "Designer";
                                break;
                            case 2:
                                rol = "Developer";
                                break;
                            case 3:
                                rol = "Tester";
                                break;
                            default:
                                MessagePrinter.error("Rol ingresado inválido");
                                start();
                                return;
                            }
                    }catch(NumberFormatException e){
                        MessagePrinter.error("Rol ingresado inválido");
                        start();
                        return;
                    }
                    Employee empleado = EmployeeFactory.createEmployee(name, dni, phone, email, rol);
                    Company.getInstance().addEmployee(empleado);
                    MessagePrinter.log("Empleado " + name + " con el rol " + rol + " ha sido creado");
                }
                start();
                return;
            case 2:
                Clear.clearScreen();
                System.out.println("Listando todos los empleados:\n");
                for(Employee employee : Company.getInstance().getEmployees()) {
                    projectName = (employee.getProject() != null) ? employee.getProject().getName() : "No asignado";
                    System.out.println("Nombre: " + employee.getName()  + " | Rol: " + employee.getRole() + " | Proyecto: " + projectName);
                }
                MessagePrinter.log();
                start();
                return;
            case 3:{
                Clear.clearScreen();
                for (Employee employee : Company.getInstance().getEmployees()) {
                    System.out.println("Nombre: " + employee.getName()  + " | DNI: " + employee.getDni());
                }
                System.out.println("Ingrese DNI del empleado: ");
                String dni_Employeer = input.nextLine();
                Employee employee = Company.getInstance().getEmployeeByDNI(dni_Employeer);
                if(employee != null){
                    Clear.clearScreen();
                    System.out.println("Agregar al empleado " + employee.getName() + " al proyecto");
                    
                    for (Project project : Company.getInstance().getProjects()) {
                        System.out.println("Nombre: " + project.getName() + " | ID: " + project.getId());
                    }
                    System.out.println("Ingrese ID de proyecto: ");
                    int id_Project = input.nextInt();
                    input.nextLine();
                    Project project = Company.getInstance().getProjectByID(id_Project);
                    if(project != null){
                        project.addEmployee(employee);
                        employee.setProject(project);
                        MessagePrinter.log("Se ha asignado a " + employee.getName() + " al proyecto " + project.getName());
                    }else{
                        MessagePrinter.error("No se ha encontrado un projecto con ese ID");
                    }
                }else{
                    MessagePrinter.error("No existe un empleado con ese DNI");
                }
                start();
                return;
            }
            case 4:
                Clear.clearScreen();
                System.out.println("Nombre del gerente:");
                name = input.nextLine();
                System.out.println("DNI:");
                dni = input.nextLine();
                System.out.println("Número de teléfono:");
                phone = input.nextLine();
                System.out.println("Email:");
                email = input.nextLine();
                if (name.isEmpty() || dni.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                    MessagePrinter.error("Todos los campos son obligatorios");
                } 
                else{
                    Company.getInstance().addManager(new Manager(name, dni, phone, email, Company.getInstance().getSizeManagers()+1));
                    MessagePrinter.log("Gerente " + name + " creado.");
                }
                start();
                return;
            case 5:
                Clear.clearScreen();
                System.out.println("Listando todos los gerentes:\n");
                for(Manager manager : Company.getInstance().getManagers()){
                    projectName = (manager.getProject() != null) ? manager.getProject().getName() : "No asignado";
                    System.out.println("Nombre: " + manager.getName()  + " | Proyecto: " + projectName);
                }
                MessagePrinter.log();
                start();
                return;
            case 6:{
                Clear.clearScreen();
                for (Manager manager : Company.getInstance().getManagers()) {
                    System.out.println("Nombre: " + manager.getName()  + " | DNI: " + manager.getDni());
                }
                System.out.println("Ingrese DNI del gerente: ");
                String dni_Manager = input.nextLine();
                Manager manager = Company.getInstance().getManagerByDNI(dni_Manager);
                if(manager != null){
                    Clear.clearScreen();
                    System.out.println("Asignar al gerente " + manager.getName() + " al proyecto");
                    for (Project project : Company.getInstance().getProjects()) {
                        System.out.println("Nombre: " + project.getName() + " | ID: " + project.getId());
                    }
                    System.out.println("Ingrese ID de proyecto: ");
                    int id_Project = input.nextInt();
                    input.nextLine();
                    Project project = Company.getInstance().getProjectByID(id_Project);
                    if(project != null){
                        project.setManager(manager);
                        manager.setProject(project);
                        MessagePrinter.log("Se ha asignado a " + manager.getName() + " al proyecto " + project.getName());
                    }else{
                        MessagePrinter.error();
                    }
                }else{
                    MessagePrinter.error();
                }
                start();
                return;
            }
            default:
                MessagePrinter.error();
                start();
                return;
        }
    }
}
