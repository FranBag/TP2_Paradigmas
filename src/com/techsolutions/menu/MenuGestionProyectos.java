package menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import classes.Company;
import classes.Project;
import classes.Task;
import utilities.Clear;

public class MenuGestionProyectos{
    static Scanner input = new Scanner(System.in);

    public static void start(){
        Clear.clearScreen();
        System.out.println("=============================");
        System.out.println("///Gestión de proyectos///");
        System.out.println("=============================");
        System.out.println("1 - Crear nuevo proyecto");
        System.out.println("2 - Listar proyectos");
        System.out.println("3 - Mostrar resúmen de un proyecto");
        System.out.println("4 - Gestionar tareas de un proyecto");
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
            case 1:{
                System.out.println("Nombre del nuevo proyecto:");
                String projectName = input.nextLine();
                if(projectName == ""){
                    System.out.println("Nombre invalido");
                    start();
                    return;
                }
                Company.getInstance().createProject(projectName);
                input.nextLine();
                start();
                return;
            }
            case 2:{
                System.out.println("Listando todos los proyectos:\n");
                for (Project project : Company.getInstance().getProjects()) {
                    String customerName = (project.getCustomer() != null) ? project.getCustomer().getName() : "No asignado";
                    String managerName = (project.getManager() != null) ? project.getManager().getName() : "No asignado";
                    System.out.println("\nNombre: " + project.getName() + " | ID: " + project.getId() +
                    "\nCliente: " + customerName + " | Gerente: " + managerName);
                }
                input.nextLine();
                start();
                return;
            }
            case 3: {
                for (Project project : Company.getInstance().getProjects()) {
                    System.out.println("Nombre: " + project.getName() + " | ID: " + project.getId());
                }
                System.out.println("Ingrese ID de proyecto: ");
                try {
                    int id_Project = input.nextInt();
                    input.nextLine();
                    
                    if (id_Project >= 1 && id_Project <= Company.getInstance().getProjects().size()) {
                        Project project = Company.getInstance().getProjectByID(id_Project);
                        String customerName = (project.getCustomer() != null) ? project.getCustomer().getName() : "No asignado";
                        String managerName = (project.getManager() != null) ? project.getManager().getName() : "No asignado";
                        System.out.println("Resumen del proyecto " + project.getName());
                        System.out.println("ID: " + project.getId() + " | Cantidad de empleados: " + project.getEmployees().size());
                        System.out.println("Cliente: " + customerName + " | Gerente: " + managerName);
                        System.out.println("Cantidad total de tareas: " + project.getTasks().size());

                        // Contar tareas según su estado
                        int pendientes = 0;
                        int enProgreso = 0;
                        int completadas = 0;
                        for (Task task : project.getTasks()) {
                            switch (task.getStatus()) {
                                case "PENDIENTE":
                                    pendientes++;
                                    break;
                                case "EN PROGRESO":
                                    enProgreso++;
                                    break;
                                case "COMPLETADA":
                                    completadas++;
                                    break;
                                default:
                                    break;
                            }
                        }

                        // Mostrar resumen de tareas por estado
                        System.out.println("Tareas PENDIENTES: " + pendientes);
                        System.out.println("Tareas EN PROGRESO: " + enProgreso);
                        System.out.println("Tareas COMPLETADAS: " + completadas);
                    } else {
                        System.out.println("Por favor, ingrese un número dentro del rango 1 y " + Company.getInstance().getProjects().size());
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, ingrese un número.");
                    input.next();
                }
                input.nextLine();
                start();
                return;
            }



            case 4:{
                for (Project project : Company.getInstance().getProjects()) {
                    System.out.println("Nombre: " + project.getName() + " | ID: " + project.getId());
                }
                System.out.println("Ingrese ID de proyecto: ");
                try {
                    int id_Project = input.nextInt();
                input.nextLine();
                    
                    if (id_Project >= 1 && id_Project <= Company.getInstance().getProjects().size()) {
                        Project project = Company.getInstance().getProjectByID(id_Project);
                        SubMenuGestionTareas.start(project);
                    } else {
                        System.out.println("Por favor, ingrese un número dentro del rango 1 y " + Company.getInstance().getProjects().size());
                    } } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Por favor, ingrese un número.");
                        input.next();
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
