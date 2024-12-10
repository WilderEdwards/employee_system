import java.sql.*;
import java.util.Scanner;


public class GetPayInfo extends GetEmployeesInfo {
    public void getPayByDivision(String url, String user, String password) {
        Scanner user_input = new Scanner(System.in);
        System.out.println("Enter the division you would like to search: ");
        String division = user_input.next();
        System.out.println("Enter the month you would like to search: ");
        int month = user_input.nextInt();
        user_input.close();

        String sqlcommand = "SELECT SUM(pay_amount) " +
                            "FROM payroll p NATURAL JOIN employee e " +
                            "WHERE e.division = '" + division + "' AND MONTH(pay_date) = " + month;
        
        try (Connection myConn = DriverManager.getConnection(url, user, password)) {
            Statement myStmt = myConn.createStatement();
            ResultSet myRS = myStmt.executeQuery(sqlcommand);
            while (myRS.next()) {
                System.out.println("Total pay for " + division + " in month " + month + " is: " + myRS.getDouble("SUM(pay_amount)"));
            }
            myConn.close();
        } catch (Exception e) {
            System.out.println("ERROR " + e.getLocalizedMessage());
    }
    }
}