package classes;

// Interfáz que se les asignará a los observadores del sujeto.
public interface Observer {
    void update(String taskName, String newStatus);
}