package utilities;
import java.util.Scanner;

public class MessagePrinter{
    static Scanner input = new Scanner(System.in);

    public static void error(){
        System.out.println("\n» (!) Valor inválido «\nPresione enter para continuar...");
        input.nextLine();
        Clear.clearScreen();
    }

    public static void error(String message){
        System.out.println("\n» (!) " + message + " «\nPresione enter para continuar...");
        input.nextLine();
        Clear.clearScreen();
    }

    public static void log(){
        System.out.println("\nPresione enter para continuar...");
        input.nextLine();
        Clear.clearScreen();
    }

    public static void log(String message){
        System.out.println("\n" + message + "\nPresione enter para continuar...");
        input.nextLine();
        Clear.clearScreen();
    }
}
