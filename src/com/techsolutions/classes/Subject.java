package classes;

// Interfáz que implementará la clase que será observada.
public interface Subject{

    public void attach(Observer obs);

    public void detach(Observer obs);
    
    public void notifyEmployees();
}
