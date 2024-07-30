public class EmployeeManagement {
    private Employee[] employees;
    private int size;

    public EmployeeManagement(int capacity) {
        employees = new Employee[capacity];
        size = 0;
    }

    public void addEmployee(Employee e) {
        if (size < employees.length) {
            employees[size++] = e;
        }
    }

    public Employee searchEmployee(int employeeId) {
        for (Employee e : employees) {
            if (e != null && e.employeeId == employeeId) return e;
        }
        return null;
    }

    public void traverse() {
        for (Employee e : employees) {
            if (e != null) System.out.println(e.name);
        }
    }

    public void deleteEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].employeeId == employeeId) {
                employees[i] = employees[size - 1];
                employees[size - 1] = null;
                size--;
                return;
            }
        }
    }
}