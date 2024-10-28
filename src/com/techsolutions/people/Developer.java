package people;

public class Developer extends Employee{

    public Developer(String name, String dni, String phone, String email, int id){
        super(name, dni, phone, email, id);
    }
    
    @Override
    public void trabajar(){
        System.out.println("Estoy desarrollando un programa.");
    }

    @Override
    public String getRole(){
        return "Developer";
    }

}
