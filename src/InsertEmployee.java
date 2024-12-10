import java.sql.*;
import java.util.Scanner;

public class InsertEmployee {
    public void insertEmployee(String url, String user, String password) {
        Scanner user_input = new Scanner(System.in);
        System.out.println("Enter employee's first name: ");
        String fname = user_input.next();
        System.out.println("Enter employee's last name: ");
        String lname = user_input.next();
        System.out.println("Enter employee's email: ");
        String email = user_input.next();
        System.out.println("Enter employee's date of hiring: ");
        String doh = user_input.next();
        System.out.println("Enter employee's Salary: ");
        double salary = user_input.nextDouble();
        System.out.println("Enter employee's SSN: ");
        String ssn = user_input.next();
        user_input.close();

        try (Connection myConn = DriverManager.getConnection(url, user, password)) {
            Statement myStmt = myConn.createStatement();
            String sqlcommand = "INSERT INTO employees (Fname, Lname, email, HireDate, Salary, SSN) VALUES ('" + fname + "', '" + lname + "', '" + email + "', '" + doh + "', " + salary + ", '" + ssn + "')";
            myStmt.executeUpdate(sqlcommand);
            System.out.println("Employee added successfully!");
            myConn.close();
        } catch (Exception e) {
            System.out.println("ERROR " + e.getLocalizedMessage());
        }

    }
}
