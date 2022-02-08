import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.employee.service.EmployeeService;

import java.util.Arrays;
import java.util.List;

public class dbTest {
    private static EmployeeService employeeService;

    public static void main(String[] args) {
        List<Employee> employees = employeeService;


        System.out.println(Arrays.toString(employees.toArray()));

    }
}
