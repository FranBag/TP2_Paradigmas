package people;

import utilities.MyException;
import classes.Company;

public class EmployeeFactory {
    public static Employee createEmployee(String name, String dni, String phone, String email, String rol) throws MyException{
        switch (rol) {
            case "Designer":
                return createDesigner(name, dni, phone, email);
            case "Developer":
                return createDeveloper(name, dni, phone, email);
            case "Tester":
                return createTester(name, dni, phone, email);
            default:
                throw new MyException("Rol ingresado inexistente");
        }
    }
    
    private static Employee createDesigner(String name, String dni, String phone, String email){
        return new Designer(name, dni, phone, email, Company.getInstance().getSizeEmployees()+1);
    }

    private static Employee createDeveloper(String name, String dni, String phone, String email){
        return new Developer(name, dni, phone, email, Company.getInstance().getSizeEmployees()+1);
    }

    private static Employee createTester(String name, String dni, String phone, String email){
        return new Tester(name, dni, phone, email, Company.getInstance().getSizeEmployees()+1);
    }
}
