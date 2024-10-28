package people;

public class Designer extends Employee{

    public Designer(String name, String dni, String phone, String email, int id){
        super(name, dni, phone, email, id);
    }

    @Override
    public void trabajar(){
        System.out.println("Estoy diseñando algo.");
    }

    @Override
    public String getRole(){
        return "Designer";
    }
}