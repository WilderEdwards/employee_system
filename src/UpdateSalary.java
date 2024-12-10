import java.util.Scanner;
import java.sql.*;

public class UpdateSalary extends UpdateEmployee {
    public void updateEmployeeData(String url, String user, String password) {
        Scanner user_input = new Scanner(System.in);
        System.out.println("Enter the employee's ID you would like to update: ");
        int empid = user_input.nextInt();
        System.out.println("\n Enter information when prompted, or press Enter to skip. \n");
        System.out.println("Enter employee's salary: ");
        double salary = user_input.nextDouble();
        user_input.close();
        String sqlcommand = "UPDATE employees SET salary = " + salary + " WHERE empid = " + empid;
        try (Connection myConn = DriverManager.getConnection(url, user, password)) {
            Statement myStmt = myConn.createStatement();
            myStmt.executeUpdate(sqlcommand);
            System.out.println("Employee salary updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating employee salary.");
            e.printStackTrace();
        }
    }   
}
