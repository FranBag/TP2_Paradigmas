package menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import classes.Company;
import classes.DirectAssignmentStrategy;
import classes.Project;
import classes.RandomAssignmentStrategy;
import classes.Task;
import people.Employee;
import utilities.Clear;
import utilities.MessagePrinter;

public class SubMenuGestionTareas {
    static Scanner input = new Scanner(System.in);

    public static void start(Project project){
        Clear.clearScreen();
        System.out.println("=============================");
        System.out.println("Gestión de tareas - " + project.getName());
        System.out.println("=============================");
        System.out.println("1 - Crear nueva tarea");
        System.out.println("2 - Listar tareas");
        System.out.println("3 - Cambiar estado de tarea");
        System.out.println("4 - Asignar tarea a un empleado");
        System.out.println("5 - Ver empleados de una tarea");
        System.out.println("0 - Atrás");

        int selection = 0;
        try{
            selection = Integer.parseInt(input.nextLine());
        }catch(NumberFormatException e){
            MessagePrinter.error();
            start(project);
            return;
        }
        switch(selection) {
            case 0:
                return;
            case 1:{
                Clear.clearScreen();
                System.out.println("Nombre de nueva tarea:");
                String taskName = input.nextLine();
                if(taskName != ""){
                    project.addTask(taskName);
                    MessagePrinter.log("Se ha creado la tarea " + taskName + " en " + project.getName());
                }else{
                    MessagePrinter.error("Nombre ingresado no valido");
                }
                start(project);
                return;
            }
            case 2:{
                Clear.clearScreen();
                System.out.println("Listando todas las tareas de " + project.getName() + ":\n");
                for (Task task : project.getTasks()){
                    System.out.println("\nNombre: " + task.getName() + " | ID: " + task.getId() +
                    "\nCantidad de empleados: " + task.getEmployees().size() + " Estado: " + task.getStatus());
                }
                MessagePrinter.log();
                start(project);
                return;
            }
            case 3:{
                Clear.clearScreen();
                for (Task task : project.getTasks()){
                    System.out.println("\nNombre: " + task.getName() + " | ID: " + task.getId() +
                    "\nEstado: " + task.getStatus());
                }
                System.out.println("Ingrese ID de tarea: ");
                try{
                    int id_Task = input.nextInt();
                    input.nextLine();
                    Task task = project.getTaskByID(id_Task);

                    if(task != null){
                        System.out.println("Nuevo estado para tarea " + task.getName() +
                        "\n1.PENDIENTE" +
                        "\n2.EN CURSO" +
                        "\n3.FINALIZADA");
                        int newStatus = input.nextInt();
                        input.nextLine();
                        String status;
                        switch (newStatus){
                            case 1:
                                status = "PENDIENTE";
                                task.changeStatus(status);
                                break;
                            case 2:
                                status = "EN CURSO";
                                task.changeStatus(status);
                                break;
                            case 3:
                                status = "FINALIZADA";
                                task.changeStatus(status);
                                break;
                            default:
                                status = null;
                                MessagePrinter.error();
                                break;
                        }
                        if(status != null){
                            MessagePrinter.log("Estado de " + task.getName() + " cambiado a " + status);
                        }
                    } else{
                        MessagePrinter.error();
                    }
                }catch (InputMismatchException e) {
                    MessagePrinter.error();
                }
                start(project);
                return;
            }
            case 4:{
                Clear.clearScreen();
                for (Task task : project.getTasks()){
                    System.out.println("\nNombre: " + task.getName() + " | ID: " + task.getId());
                }
                System.out.println("Ingrese ID de tarea: ");
                try{
                    int id_Task = input.nextInt();
                    input.nextLine();
                    Task task = project.getTaskByID(id_Task);
                    if(task != null){
                        System.out.println("Seleccione método de asignación: ");
                        System.out.println("1. Asignación directa");
                        System.out.println("2. Asignación aleatoria");
                        int assignmentMethod = input.nextInt();
                        input.nextLine();
                            switch(assignmentMethod){
                                case 1:
                                    task.setAssignmentStrategy(new DirectAssignmentStrategy());
                                    break;
                                case 2:
                                    task.setAssignmentStrategy(new RandomAssignmentStrategy());
                                    task.assignEmployee(null);
                                    start(project);
                                    return;
                                default:
                                    MessagePrinter.error("Método de asignación no válido. Se usará asignación aleatoria por defecto");
                                    task.setAssignmentStrategy(new RandomAssignmentStrategy());
                                    break;
                            }
                        Clear.clearScreen();
                        System.out.println("Asignar tarea " + task.getName() + " al empleado:");
                        for (Employee employee : project.getEmployees()) {
                            System.out.println("Nombre: " + employee.getName() + " | DNI: " + employee.getDni());
                        }
                        System.out.println("Ingrese DNI de empleado: ");
                        String dni_Employee = input.nextLine();
                        try{
                            Employee employee = project.getEmployeeByDNI(dni_Employee);
                            task.assignEmployee(employee);
                        }catch(NullPointerException e){
                            MessagePrinter.error("No se encontró un empleado con ese DNI");
                            break;
                        }
                    }else{
                        MessagePrinter.error();
                    }
                }catch (InputMismatchException e) {
                    MessagePrinter.error();
                }
                start(project);
                return;
                }
            case 5:{
                Clear.clearScreen();
                for (Task task : project.getTasks()){
                    System.out.println("\nNombre: " + task.getName() + " | ID: " + task.getId());
                }
                System.out.println("Ingrese ID de tarea: ");
                int id_Task = input.nextInt();
                input.nextLine();
                Task task = project.getTaskByID(id_Task);
                if(task != null){
                    Clear.clearScreen();
                    System.out.println("Empleados de la tarea " + task.getName());
                    for (Employee employee : task.getEmployees()){
                        System.out.println("\nNombre: " + employee.getName() + " | Email: " + employee.getEmail());
                    }
                    MessagePrinter.log();
                }else{
                    MessagePrinter.error("No se ha encontrado una tarea con ese ID");
                }
                start(project);
                return;
            }
            default:
                System.out.println("Valor incorrecto");
                input.nextLine();
                start(project);
                return;
        }
    }
}
