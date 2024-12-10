import java.sql.*;
import java.util.Scanner;

public class GetEmployeesInfo {
        public void getFullInfo(String url,String user, String password) {


        System.out.println("");
        StringBuilder output = new StringBuilder("");

            Scanner user_input = new Scanner(System.in);
    
            System.out.println("Enter employee's first name: ");
            String fname = user_input.next();
    
            System.out.println("Enter employee's last name: ");
            String lname = user_input.next();
    
            System.out.println("Enter employee's ID: ");
            int empid = user_input.nextInt();
    
            System.out.println("Enter the employee's SSN: ");
            String ssn = user_input.next();
            user_input.close();
            
        String sqlcommand = "SELECT * " +
                            "FROM employees e" +
                            "WHERE e.Fname = '" + fname + "' AND e.Lname = '" + lname + "' AND e.empid = " + empid + " AND e.SSN = '" + ssn + "'";

        try (Connection myConn = DriverManager.getConnection(url, user, password)) {
            Statement myStmt = myConn.createStatement();

            output.append("\nEMPLOYEE INFORMATION\n");
            ResultSet myRS = myStmt.executeQuery(sqlcommand);

            while (myRS.next()) {
                output.append("Name= " + myRS.getString("e.Fname") + " " + myRS.getString("e.Lname") + "\t");
                output.append("Title=" + myRS.getString("jt.job_title") + "     " + myRS.getString("e.email") + "\n");
                System.out.print(output.toString());
                output.setLength(0);

            }
            myConn.close();
        } catch (Exception e) {
            System.out.println("ERROR " + e.getLocalizedMessage());
        }

        System.out.println("\nPress Enter to clear console screen...\n");
        Scanner myScanner = new Scanner(System.in);
        //myScanner.nextLine();
        myScanner.close();
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
    public void getPayByDivision(String url, String user, String password) {
        Scanner user_input = new Scanner(System.in);
        System.out.println("Enter the division you would like to search: ");
        String division = user_input.next();
        System.out.println("Enter the month you would like to search: ");
        int month = user_input.nextInt();
        user_input.close();
        String sqlcommand = "SELECT SUM(pay) " +
                            "FROM payroll p NATURAL JOIN employees e " +
                            "WHERE e.division = '" + division + "' AND MONTH(p.pay_date) = " + month;

        try (Connection myConn = DriverManager.getConnection(url, user, password)) {
            Statement myStmt = myConn.createStatement();
            ResultSet myRS = myStmt.executeQuery(sqlcommand);
            while (myRS.next()) {
                System.out.println("Total pay for " + division + " in month " + month + " is: " + myRS.getDouble("SUM(pay)"));
            }
            myConn.close();
        } catch (Exception e) {
            System.out.println("ERROR " + e.getLocalizedMessage());
        }
    }


    public void getPayByJobTitle(String url, String user, String password) {

    }
}


