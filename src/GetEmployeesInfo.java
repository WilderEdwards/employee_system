import java.sql.*;
import java.util.Scanner;

public class GetEmployeesInfo {
        public static void main(String[] args) {

    
        

        System.out.println("")
        String url = "jdbc:mysql://localhost:3306/employeeData";
        String user = "root";
        String password = "Seeker123*";
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
            
        String sqlcommand = "SELECT * 
                            FROM employee e
                            WHERE e.Fname = '" + fname + "' AND e.Lname = '" + lname + "' AND e.empid = " + empid + " AND e.ssn = '" + ssn + "'";

        try (Connection myConn = DriverManager.getConnection(url, user, password)) {
            Statement myStmt = myConn.createStatement();

            output.append("\nEMPLOYEE INFORMATION\n");
            ResultSet myRS = myStmt.executeQuery(sqlcommand);

            while (myRS.next()) {
                output.append("Name= " + myRS.getString("e.Fname") + " " + myRS.getString("e.Lname") + "\t");
                output.append("Title=" + myRS.getString("jt.job_title") + "     " + myRS.getString("e.email") + "\n");
                System.out.print(output.toString());
                output.setLength(0);

                // Create an instance of Payroll and call getPayByMonth
                Payroll payroll = new Payroll();
                output.append(payroll.getPayByMonth(myRS.getInt("e.empid"), myConn));
                System.out.print(output.toString());
                output.setLength(0);
            }
            myConn.close();
        } catch (Exception e) {
            System.out.println("ERROR " + e.getLocalizedMessage());
        }

        System.out.println("\nPress Enter to clear console screen...\n");
        Scanner myScanner = new Scanner(System.in);
        myScanner.nextLine();
        myScanner.close();
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
}
