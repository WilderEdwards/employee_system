import java.util.Scanner;
import java.sql.*;


public class UpdateEmployee {
    public void updateEmployeeData(String url, String user, String password) {
    Scanner user_input = new Scanner(System.in);
    System.out.println("Enter the employee's ID you would like to update: ");
    int empid = user_input.nextInt();
    System.out.println("\n Enter information when prompted, or press Enter to skip. \n");
    System.out.println("Enter employee's salary: ");
    double salary = user_input.nextDouble();


    

    }
}