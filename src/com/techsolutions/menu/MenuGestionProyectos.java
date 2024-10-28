package menu;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import classes.Company;
import classes.Project;
import utilities.Clear;
import utilities.MessagePrinter;

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
            MessagePrinter.error();
            start();
            return;
        }
        switch(selection) {
            case 0:
                return;
            case 1:{
                Clear.clearScreen();
                System.out.println("Nombre del nuevo proyecto:");
                String projectName = input.nextLine();
                if(projectName == ""){
                    MessagePrinter.error("Nombre no válido");
                }else{
                    Company.getInstance().createProject(projectName);
                    MessagePrinter.log("Se ha creado el proyecto " + projectName);
                }
                start();
                return;
            }
            case 2:{
                Clear.clearScreen();
                System.out.println("Listando todos los proyectos:\n");
                for (Project project : Company.getInstance().getProjects()) {
                    String customerName = (project.getCustomer() != null) ? project.getCustomer().getName() : "No asignado";
                    String managerName = (project.getManager() != null) ? project.getManager().getName() : "No asignado";
                    System.out.println("\nNombre: " + project.getName() + " | ID: " + project.getId() +
                    "\nCliente: " + customerName + " | Gerente: " + managerName);
                }
                MessagePrinter.log();
                start();
                return;
            }
            case 3: {
                Clear.clearScreen();
                for (Project project : Company.getInstance().getProjects()) {
                    System.out.println("Nombre: " + project.getName() + " | ID: " + project.getId());
                }
                System.out.println("Ingrese ID de proyecto: ");
                try {
                    int id_Project = input.nextInt();
                    input.nextLine();
                    Project project = Company.getInstance().getProjectByID(id_Project);
                    if (project != null){
                        Clear.clearScreen();
                        String customerName = (project.getCustomer() != null) ? project.getCustomer().getName() : "No asignado";
                        String managerName = (project.getManager() != null) ? project.getManager().getName() : "No asignado";
                        System.out.println("Resumen del proyecto " + project.getName());
                        System.out.println("ID: " + project.getId() + " | Cantidad de empleados: " + project.getEmployees().size());
                        System.out.println("Cliente: " + customerName + " | Gerente: " + managerName);
                        System.out.println("Cantidad total de tareas: " + project.getTasks().size());
                        Map<String, Integer> taskStatusCounts = project.getTaskCountByStatus();
                        System.out.println("Cantidad de tareas por estado:");
                        for (Map.Entry<String, Integer> entry : taskStatusCounts.entrySet()) {
                            System.out.println("- " + entry.getKey() + ": " + entry.getValue());
                        }
                        MessagePrinter.log();
                    }else{
                        MessagePrinter.error();
                    }
                }catch(InputMismatchException e){
                    MessagePrinter.error();
                }
                start();
                return;
            }

            case 4:{
                Clear.clearScreen();
                for(Project project : Company.getInstance().getProjects()){
                    System.out.println("Nombre: " + project.getName() + " | ID: " + project.getId());
                }
                System.out.println("Ingrese ID de proyecto: ");
                try{
                    int id_Project = input.nextInt();
                    input.nextLine();
                    Project project = Company.getInstance().getProjectByID(id_Project);
                    if (project != null){
                        SubMenuGestionTareas.start(project);
                    }else{
                        MessagePrinter.error();
                    }
                }catch(InputMismatchException e){
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
