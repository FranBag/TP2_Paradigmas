package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import people.Employee;
import people.EmployeeFactory;

import classes.Company;

public class TxtFileAdapter {

    String filePath = "src/com/techsolutions/utilities/Base_de_Datos.txt";

    public TxtFileAdapter(){

    }

    public void readCompanyData() {
        Company company = Company.getInstance();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Empresa:")) {
                    String companyName = line.substring(line.indexOf(":") + 1).trim();
                    company.setName(companyName);

                } else if (line.startsWith("Proyecto:")) {
                    String projectName = line.substring(line.indexOf(":") + 1).trim();
                    company.createProject(projectName);

                } else if (line.startsWith("Empleado:")) {
                    String[] employeeData = line.substring(line.indexOf(":") + 1).trim().split(", ");
                    String name = employeeData[0];
                    String dni = employeeData[1];
                    String phone = employeeData[2];
                    String email = employeeData[3];
                    String role = employeeData[4];
                    String project = employeeData[5];

                    System.out.println(name);

                    Employee employee = EmployeeFactory.createEmployee(name, dni, phone, email, role);
                    company.addEmployee(employee);
                    System.out.println(project);
                    company.getProjectByName(project).addEmployee(employee);
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}