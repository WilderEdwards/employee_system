import java.sql.*;
import java.util.Scanner;

public class GetEmployeesInfo {
        public void getFullInfo(String url,String user, String password) {

        System.out.println("EMPLOYEE INFORMATION\n");
        //TODO: Potentially make separate methods for each query (SSN, empID, name, etc)

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

    }
    public void getPayByDivision(String url, String user, String password) {
        Scanner user_input = new Scanner(System.in);
        System.out.println("Enter the division you would like to search: ");
        String division = user_input.next();
        System.out.println("Enter the month you would like to search: ");
        int month = user_input.nextInt();
        user_input.close();
        String sqlcommand = "SELECT SUM(pay) " +
                            "FROM employee_division NATURAL JOIN division d NATURAL JOIN payroll p " +
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
        Scanner user_input = new Scanner(System.in);
        System.out.println("Enter the job title you would like to search: ");
        String job_title = user_input.next();
        System.out.println("Enter the month you would like to search: ");
        int month = user_input.nextInt();
        user_input.close();
        String sqlcommand = "SELECT SUM(earnings) " +
                            "FROM employee_job_titles ejt NATURAL JOIN job_titles jt NATURAL JOIN payroll" +
                            "WHERE jt.job_title = '" + job_title + "' AND MONTH(p.pay_date) = " + month;

        try (Connection myConn = DriverManager.getConnection(url, user, password)) {
            Statement myStmt = myConn.createStatement();
            ResultSet myRS = myStmt.executeQuery(sqlcommand);
            while (myRS.next()) {
                System.out.println("Total pay for " + job_title + " in month " + month + " is: " + myRS.getDouble("SUM(pay)"));
            }
            myConn.close();
        } catch (Exception e) {
            System.out.println("ERROR " + e.getLocalizedMessage());
        }
    }
}


