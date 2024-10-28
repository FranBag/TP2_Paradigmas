import classes.Company;
import classes.Project;
import classes.Task;
import utilities.Clear;
import utilities.TxtFileAdapter;
import utilities.MessagePrinter;
import menu.MenuGestionClientes;
import menu.MenuGestionEmpleados;
import menu.MenuGestionProyectos;
import people.Customer;
import people.Employee;
import people.EmployeeFactory;
import people.Manager;

import java.util.Scanner;

import javax.naming.ldap.ManageReferralControl;

public class app {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Company company = Company.getInstance();

        TxtFileAdapter adapter = new TxtFileAdapter();

        // Creación de empresa y projecto inicial
        company.setName("Tech Solutions");
        
        company.createProject("Página web para peluquería");
        Project proyecto1 = company.getProjectByID(1);

        // Creación de tareas iniciales
        proyecto1.addTask("Programar backend");
        proyecto1.addTask("Programar frontend");
        proyecto1.addTask("Probar endpoints");

        Task tarea1 = proyecto1.getTaskByID(1);
        Task tarea2 = proyecto1.getTaskByID(2);
        Task tarea3 = proyecto1.getTaskByID(3);

        // Creación de empleados iniciales
        Employee empleado1 = EmployeeFactory.createEmployee("Francisco Bagneres", "44556677", "3764", "fbagneres@gmail.com", "Developer");
        Employee empleado2 = EmployeeFactory.createEmployee("Kiara Tabarez", "88991100", "3765", "ktabarez@gmail.com", "Designer");
        Employee empleado3 = EmployeeFactory.createEmployee("Mateo Tomas", "99881155", "3766", "mtomas@gmail.com", "Tester");
        Manager gerente = new Manager("Santiago Caceres", "11223344", "1234", "scaceres@gmail.com", 1);
        Customer cliente = new Customer("Matias Tang", "77889911", "3214", "mtang@gmail.com", 1);

        // Añadiendo empleados, gerente y cliente a proyectos
        proyecto1.addEmployee(empleado1);
        empleado1.setProject(proyecto1);
        proyecto1.addEmployee(empleado2);
        empleado2.setProject(proyecto1);
        proyecto1.addEmployee(empleado3);
        empleado3.setProject(proyecto1);

        proyecto1.setManager(gerente);
        gerente.setProject(proyecto1);

        proyecto1.setCustomer(cliente);
        cliente.setProject(proyecto1);

        // Añadiendo tareas a empleados
        tarea1.addEmployee(empleado1); 
        empleado1.addMyTask(tarea1);
        tarea2.addEmployee(empleado2);
        empleado2.addMyTask(tarea2);
        tarea3.addEmployee(empleado3);
        empleado3.addMyTask(tarea3);



        while (true) {
            Clear.clearScreen();
            System.out.println("Bienvenido al sistema de la empresa " + company.getName());
            System.out.println("\n=============================");
            System.out.println("Seleccione una opcion");
            System.out.println("1 - Gestionar proyectos");
            System.out.println("2 - Gestionar clientes");
            System.out.println("3 - Gestionar empleados");
            System.out.println("4 - Ingresar datos de DB antigua");
            System.out.println("0 - Salir");
            System.out.println("=============================");


            int selection;
            try {
                selection = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                MessagePrinter.error();
                continue;
            }

            switch(selection){
                case 0:
                    System.out.println("Saliendo del sistema...");
                    input.close();
                    return;
                case 1:
                    MenuGestionProyectos.start();
                    break;
                case 2:
                    MenuGestionClientes.start();
                    break;
                case 3:
                    MenuGestionEmpleados.start();
                    break;
                case 4:
                    adapter.readCompanyData();
                    break;
                default:
                    MessagePrinter.error();
                    break;
            }
            Clear.clearScreen();
        }
    }
}
