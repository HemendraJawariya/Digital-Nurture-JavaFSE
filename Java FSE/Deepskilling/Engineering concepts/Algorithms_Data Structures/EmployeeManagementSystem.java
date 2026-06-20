public class EmployeeManagementSystem {

    public static class Employee {
        private final int id;
        private final String name;
        private final String department;

        public Employee(int id, String name, String department) {
            this.id = id;
            this.name = name;
            this.department = department;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return id + " | " + name + " | " + department;
        }
    }

    private final Employee[] employees;
    private int size;

    public EmployeeManagementSystem(int capacity) {
        employees = new Employee[capacity];
    }

    public boolean addEmployee(Employee employee) {
        if (size == employees.length) {
            return false;
        }

        employees[size++] = employee;
        return true;
    }

    public Employee findEmployeeById(int id) {
        for (int index = 0; index < size; index++) {
            if (employees[index].getId() == id) {
                return employees[index];
            }
        }
        return null;
    }

    public boolean removeEmployee(int id) {
        for (int index = 0; index < size; index++) {
            if (employees[index].getId() == id) {
                for (int shiftIndex = index; shiftIndex < size - 1; shiftIndex++) {
                    employees[shiftIndex] = employees[shiftIndex + 1];
                }
                employees[--size] = null;
                return true;
            }
        }
        return false;
    }

    public void displayEmployees() {
        for (int index = 0; index < size; index++) {
            System.out.println(employees[index]);
        }
    }

    public void printAnalysis() {
        System.out.println("Analysis:");
        System.out.println("Array access is O(1), but search and delete are O(n) in the worst case.");
        System.out.println("The structure uses O(capacity) space with a fixed-size array.");
    }

    public static void main(String[] args) {
        EmployeeManagementSystem system = new EmployeeManagementSystem(5);
        system.addEmployee(new Employee(1, "Maya", "IT"));
        system.addEmployee(new Employee(2, "Arjun", "HR"));
        system.addEmployee(new Employee(3, "Sara", "Finance"));

        System.out.println(system.findEmployeeById(2));
        system.removeEmployee(1);
        system.displayEmployees();
        system.printAnalysis();
    }
}