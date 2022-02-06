import com.exadel.sandbox.entities.Employee;
import com.exadel.sandbox.service.EmployeeService;

import java.util.Arrays;
import java.util.List;

public class dbTest {
    private static EmployeeService employeeService;

    public static void main(String[] args) {
        List<Employee> employees = employeeService;


        System.out.println(Arrays.toString(employees.toArray()));

    }
}
