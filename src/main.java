import java.util.Scanner;

public class main {
    public static void main(String[] args) {

    // TODO: Add your database connection information for debugging, add respective classes for different methods
    // (View full-time employee information with pay statement history, search total employee pay for a specific month by Job Title, search total employee pay for a specific month by Division)

    Scanner user_input = new Scanner(System.in);
    System.out.println("Enter the user for database connection ('root' default): ");
    String user = user_input.next();
    System.out.println("Enter the password for database connection: ");
    String password = user_input.next();
    String url = "jdbc:mysql://localhost:3306/employee_system?useSSL=false";
    //CHANGE THE URL TO YOUR DATABASE CONNECTION




    System.out.println("Welcome to the Employee Management System");
    System.out.println("Please select an option from the menu below:");
    System.out.println("1. Add a new employee");
    System.out.println("2. Search employee information");
    System.out.println("3. Update employee information (Salary / Data)");
    System.out.println("4. Delete an employee");
    System.out.println("5. Quit");



// TODO: Add more options for the user to select from from the assignment. Current complete options: 
// as of (commit 12-16-24 3:00 PM)
// 1. Add a new employee
// 2. Search employee information (Full-time employee information with pay statement history, total employee pay for a specific month by Job Title, total employee pay for a specific month by Division)


    int user_choice = user_input.nextInt();
    while (user_choice != 5) {
        if (user_choice == 1) {
            InsertEmployee newEmployee = new InsertEmployee();
            newEmployee.insertEmployee(url, user, password);
        } else if (user_choice == 2) {
            GetEmployeesInfo employeeInfo = new GetEmployeesInfo();
            System.out.println("Would you like to:");
            System.out.println("1. View full-time employee information with pay statement history");
            System.out.println("2. Search total employee pay for a specific month by Job Title");
            System.out.println("3. Search total employee pay for a specific month by Division");
            user_choice = user_input.nextInt();
            if (user_choice == 1) {
                employeeInfo.getFullInfo(url,user, password);
            } else if (user_choice == 2) {
                employeeInfo.getPayByJobTitle(url, user, password);
            } else if (user_choice == 3) {
                employeeInfo.getPayByDivision(url, user, password);
            } else {
                System.out.println("Invalid selection. Please try again.");
            }

        } else if (user_choice == 3) {
            UpdateEmployee updateEmployee = new UpdateEmployee();
            updateEmployee.updateEmployeeData(url, user, password);
        } else if (user_choice == 4) {
            DeleteEmployee deleteEmployee = new DeleteEmployee();
            deleteEmployee.deleteEmployee(url, user, password);
        }
        else {
            System.out.println("Invalid selection. Please try again.");
            }
        }
    user_input.close();
    System.out.println("\n\nThank you for using the Employee Management System.");
    }
}
