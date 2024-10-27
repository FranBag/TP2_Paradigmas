package menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import classes.Company;
import classes.Project;
import people.Customer;
import utilities.Clear;

public class MenuGestionClientes{
    static Scanner input = new Scanner(System.in);

    public static void start(){
        Clear.clearScreen();
        System.out.println("=============================");
        System.out.println("///Gestión de clientes///");
        System.out.println("=============================");
        System.out.println("1 - Agregar nuevo cliente");
        System.out.println("2 - Asignar cliente a proyecto");
        System.out.println("3 - Listar clientes");
        System.out.println("4 - Mostrar datos de un cliente");
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
                System.out.println("Nombre del cliente:");
                String name = input.nextLine();
                System.out.println("DNI:");
                String dni = input.nextLine();
                System.out.println("Número de teléfono:");
                String phone = input.nextLine();
                System.out.println("Email:");
                String email = input.nextLine();
                if (name.isEmpty() || dni.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                    System.out.println("Todos los campos son obligatorios.");}
                    else{
                        Customer newCustomer = new Customer(name, dni, phone, email, Company.getInstance().getSizeCustomers()+1);
                    Company.getInstance().addCustomer(newCustomer);
                    System.out.println("Cliente " + name + " creado.");
                    }
                input.nextLine();
                start();
                return;
                
            }
            case 2:{
                for (Customer customer : Company.getInstance().getCustomers()) {
                    System.out.println("Nombre: " + customer.getName()  + " | DNI: " + customer.getDni());
                }
                System.out.println("Ingrese DNI del cliente: ");
                String dni_Customer = input.nextLine();
                try{
                    Customer customer = Company.getInstance().getCustomerByDNI(dni_Customer);
                    System.out.println("Asignar al cliente " + customer.getName() + " al proyecto");

                    for (Project project : Company.getInstance().getProjects()) {
                        System.out.println("Nombre: " + project.getName() + " | ID: " + project.getId());
                    }
                    System.out.println("Ingrese ID de proyecto: ");
                    try{
                        int id_Project = Integer.parseInt(input.nextLine());
                        Project project = Company.getInstance().getProjectByID(id_Project);
                        project.setCustomer(customer);
                        customer.setProject(project);
                        System.out.println("Se ha asignado a " + customer.getName() + " al proyecto " + project.getName());
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
            case 3:{
                System.out.println("Listando todos los clientes:\n");
                for (Customer customer : Company.getInstance().getCustomers()) {
                    String projectName = (customer.getProject() != null) ? customer.getProject().getName() : "No asignado";
                    System.out.println("Nombre: " + customer.getName()  + " | Proyecto: " + projectName);
                }
                input.nextLine();
                start();
                return;
            }
            case 4:{
                for (Customer customer : Company.getInstance().getCustomers()) {
                    System.out.println("Nombre: " + customer.getName()  + " | DNI: " + customer.getDni());
                }
                System.out.println("Ingrese DNI de cliente: ");
                String dni_Customer = input.nextLine();
                try{
                    Customer customer = Company.getInstance().getCustomerByDNI(dni_Customer);
                    String projectName = (customer.getProject() != null) ? customer.getProject().getName() : "No asignado";
                    System.out.println("Datos del cliente " + customer.getName());
                    System.out.println("DNI:" + customer.getDni() + " | Proyecto: " + projectName);
                    System.out.println("Telefono: " + customer.getPhone() + " | Email: " + customer.getEmail());
                }catch(NullPointerException e){
                    System.out.println("No existe un cliente con ese DNI.");
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
