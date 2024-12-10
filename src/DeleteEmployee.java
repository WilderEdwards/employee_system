import java.util.Scanner;
import java.sql.*;

public class DeleteEmployee {
    public void deleteEmployee(String url, String user, String password) {
        Scanner user_input = new Scanner(System.in);
        System.out.println("Enter the employee's ID you would like to delete: ");
        int empid = user_input.nextInt();
        user_input.close();
        String sqlcommand = "DELETE FROM employee WHERE empid = " + empid;
        try (Connection myConn = DriverManager.getConnection(url, user, password)) {
            Statement myStmt = myConn.createStatement();
            myStmt.executeUpdate(sqlcommand);
            System.out.println("Employee deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting employee.");
            e.printStackTrace();
        }
    }
}
