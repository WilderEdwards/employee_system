import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner user_input = new Scanner(System.in);

        System.out.println("Enter the username for database connection ('root' by default): ");
        String user = user_input.next();

        System.out.println("Enter the password for database connection: ");
        String password = user_input.next();

        String url = "jdbc:mysql://localhost:3306/employeedata?useSSL=false";

        System.out.println("Welcome to the Employee Management System");

        int user_choice;

        do {
            System.out.println("\nPlease select an option from the menu below:");
            System.out.println("1. Add a new employee");
            System.out.println("2. Search employee information");
            System.out.println("3. Update employee information (Salary / Data)");
            System.out.println("4. Delete an employee");
            System.out.println("5. Quit");

            System.out.println("\nEnter your choice: ");
            while (!user_input.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                user_input.next(); // Consume the invalid input
            }
            user_choice = user_input.nextInt();

            switch (user_choice) {
                case 1:
                    InsertEmployee newEmployee = new InsertEmployee();
                    newEmployee.insertEmployee(url, user, password);
                    break;

                case 2:
                    GetEmployeesInfo employeeInfo = new GetEmployeesInfo();
                    System.out.println("Would you like to:");
                    System.out.println("1. View full-time employee information with pay statement history");
                    System.out.println("2. Search total employee pay for a specific month by Job Title");
                    System.out.println("3. Search total employee pay for a specific month by Division");

                    int search_choice;
                    while (!user_input.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a number between 1 and 3.");
                        user_input.next(); // Consume the invalid input
                    }
                    search_choice = user_input.nextInt();

                    switch (search_choice) {
                        case 1:
                            employeeInfo.getFullInfo(url, user, password);
                            break;
                        case 2:
                            employeeInfo.getPayByJobTitle(url, user, password);
                            break;
                        case 3:
                            employeeInfo.getPayByDivision(url, user, password);
                            break;
                        default:
                            System.out.println("Invalid selection. Please try again.");
                            break;
                    }
                    break;

                case 3:
                    UpdateEmployee updateEmployee = new UpdateEmployee();
                    updateEmployee.updateEmployeeData(url, user, password);
                    break;

                case 4:
                    DeleteEmployee deleteEmployee = new DeleteEmployee();
                    deleteEmployee.deleteEmployee(url, user, password);
                    break;

                case 5:
                    System.out.println("\n\nThank you for using the Employee Management System.");
                    break;

                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        } while (user_choice != 5);

        user_input.close();
    }
}