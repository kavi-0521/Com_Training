import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A console application for managing employee records.
 * Compile with: javac EmployeeManagement.java
 * Run with:     java EmployeeManagement
 */
public class EmployeeManagement {
    private static final Map<String, Employee> employees = new LinkedHashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Employee Management ===");
            System.out.println("1. Add employee");
            System.out.println("2. Display employees");
            System.out.println("3. Search employee by ID");
            System.out.println("4. Update employee");
            System.out.println("5. Delete employee");
            System.out.println("6. Exit");

            int choice = readInt("Choose an option: ");
            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    searchEmployee();
                    break;
                case 4:
                    updateEmployee();
                    break;
                case 5:
                    deleteEmployee();
                    break;
                case 6:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1–6.");
            }
        }
        scanner.close();
    }

    private static void addEmployee() {
        System.out.print("Employee ID: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("Employee ID cannot be empty.");
            return;
        }
        if (employees.containsKey(id)) {
            System.out.println("An employee with that ID already exists.");
            return;
        }

        String name = readNonEmpty("Name: ");
        String department = readNonEmpty("Department: ");
        double salary = readNonNegativeDouble("Salary: ");
        employees.put(id, new Employee(id, name, department, salary));
        System.out.println("Employee added successfully.");
    }

    private static void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees have been added yet.");
            return;
        }

        System.out.println("\nEmployee records:");
        for (Employee employee : employees.values()) {
            printEmployee(employee);
        }
    }

    private static void searchEmployee() {
        System.out.print("Enter employee ID to search: ");
        String id = scanner.nextLine().trim();
        Employee employee = employees.get(id);
        if (employee == null) {
            System.out.println("No employee found with ID " + id + ".");
        } else {
            printEmployee(employee);
        }
    }

    private static void updateEmployee() {
        System.out.print("Enter employee ID to update: ");
        String id = scanner.nextLine().trim();
        Employee employee = employees.get(id);
        if (employee == null) {
            System.out.println("No employee found with ID " + id + ".");
            return;
        }

        System.out.println("Enter the updated details:");
        String name = readNonEmpty("Name: ");
        String department = readNonEmpty("Department: ");
        double salary = readNonNegativeDouble("Salary: ");
        employees.put(id, new Employee(id, name, department, salary));
        System.out.println("Employee updated successfully.");
    }

    private static void deleteEmployee() {
        System.out.print("Enter employee ID to delete: ");
        String id = scanner.nextLine().trim();
        Employee removed = employees.remove(id);
        if (removed == null) {
            System.out.println("No employee found with ID " + id + ".");
        } else {
            System.out.println("Employee " + id + " deleted successfully.");
        }
    }

    private static void printEmployee(Employee employee) {
        System.out.printf("ID: %s | Name: %s | Department: %s | Salary: %.2f%n",
                employee.id, employee.name, employee.department, employee.salary);
    }

    private static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This field cannot be empty. Please try again.");
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private static double readNonNegativeDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double salary = Double.parseDouble(input);
                if (Double.isFinite(salary) && salary >= 0) {
                    return salary;
                }
            } catch (NumberFormatException ignored) {
                // Show the common validation message below.
            }
            System.out.println("Enter a valid, non-negative salary.");
        }
    }

    private static class Employee {
        private final String id;
        private final String name;
        private final String department;
        private final double salary;

        private Employee(String id, String name, String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }
    }
}
