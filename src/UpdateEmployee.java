import java.util.Scanner;
import java.sql.*;
import java.util.ArrayList;


public class UpdateEmployee {
    public void updateEmployeeData(String url, String user, String password) {
    Scanner user_input = new Scanner(System.in);
    System.out.println("Enter the employee's ID you would like to update: ");
    int empid = user_input.nextInt();
    System.out.println("\n Enter information you would like to update when prompted, or press Enter to skip. \n");
    System.out.println("Employee's first name: ");
    String fname = user_input.next();
    System.out.println("Employee's last name: ");
    String lname = user_input.next();
    System.out.println("Employee's email: ");
    String email = user_input.next();
    System.out.println("Employee's SSN: ");
    String ssn = user_input.next();
    System.out.println("Employee's phone #: ");
    String phone = user_input.next();
    System.out.println("Employee's gender: ");
    String gender = user_input.next();
    System.out.println("Employee's pronouns: ");
    String pronouns = user_input.next();
    System.out.println("Employee's identified race: ");
    String identified_race = user_input.next();
    System.out.println("Employee's location (state): ");
    String state = user_input.next();
    System.out.println("Employee's location (city): ");
    String city = user_input.next();

    

    ArrayList<String> entered_values_employee = new ArrayList<String>() {
        {
            add(fname);
            add(lname);
            add(email);
            add(ssn);

        }
    };

    ArrayList<String> column_names_employee = new ArrayList<String>() {
        {
            add("Fname");
            add("Lname");
            add("email");
            add("SSN");
        }
    };

    ArrayList<String> entered_values_address = new ArrayList<String>() {
        {
            add(state);
            add(city);
        }
    };

    ArrayList<String> column_names_address= new ArrayList<String>() {
        {
            add("state");
            add("city");
        }
    };

    ArrayList<String> entered_values_demographics = new ArrayList<String>() {
        {
            add(gender);
            add(phone);
            add(identified_race);
            add(pronouns);
        }
    };

    ArrayList<String> column_names_demographics = new ArrayList<String>() {
        {
            add("gender");
            add("phone");
            add("identified_race");
            add("pronouns");
        }
    };


    for (int i = 0; i < entered_values_employee.size(); i++) {
        if (entered_values_employee.get(i).equals("")) {
            entered_values_employee.remove(i);
            column_names_employee.remove(i);
        }
    }

    for (int i = 0; i < entered_values_address.size(); i++) {
        if (entered_values_address.get(i).equals("")) {
            entered_values_address.remove(i);
            column_names_address.remove(i);
        }
    }

    for (int i = 0; i < entered_values_demographics.size(); i++) {
        if (entered_values_demographics.get(i).equals("")) {
            entered_values_demographics.remove(i);
            column_names_demographics.remove(i);
        }
    }


    try (Connection myConn = DriverManager.getConnection(url, user, password)) {
        Statement myStmt = myConn.createStatement();
        if (entered_values_demographics.size() > 0) {
            for (int i = 0; i < entered_values_demographics.size(); i++) {
                String sqlcommand = "UPDATE address SET " + column_names_demographics.get(i) + " = '" + entered_values_demographics.get(i) + "' WHERE empid = " + empid;
                myStmt.executeUpdate(sqlcommand);
                String sqlcommand2 = "SELECT * FROM demographics WHERE empid = " + empid;
                ResultSet myRS = myStmt.executeQuery(sqlcommand2);
                while (myRS.next()) {
                    System.out.println("Employee's demographics updated successfully.");
                    System.out.println("Employee's demographics: " + myRS.getString("pronouns") + "," + myRS.getString("identified_race") + ",");
                }
            }
        }

        if (entered_values_employee.size() > 0) {
        for (int i = 0; i < entered_values_employee.size(); i++) {
            String sqlcommand = "UPDATE employees SET " + column_names_employee.get(i) + " = '" + entered_values_employee.get(i) + "' WHERE empid = " + empid;
            myStmt.executeUpdate(sqlcommand);
            String sqlcommand2 = "SELECT * FROM employees WHERE empid = " + empid;
            ResultSet myRS = myStmt.executeQuery(sqlcommand2);
            while (myRS.next()) {
                System.out.println("Employee's information updated successfully.");
                System.out.println("Employee's name: " + myRS.getString("Fname") + " " + myRS.getString("Lname"));
            }
        }

        if (entered_values_address.size() > 0) {
            for (int i = 0; i < entered_values_address.size(); i++) {
                String sqlcommand = "UPDATE address SET " + column_names_address.get(i) + " = '" + entered_values_address.get(i) + "' WHERE empid = " + empid;
                myStmt.executeUpdate(sqlcommand);
                String sqlcommand2 = "SELECT * FROM address WHERE empid = " + empid;
                ResultSet myRS = myStmt.executeQuery(sqlcommand2);
                while (myRS.next()) {
                    System.out.println("Employee's address+ updated successfully.");
                    System.out.println("Employee's address: " + myRS.getString("state") + ", " + myRS.getString("city"));
                }
            }
        }
    user_input.close();
    myConn.close();
    }
}
    catch (SQLException e) {
        System.out.println("Error updating employee information.");
        e.printStackTrace();
        }
    }
}