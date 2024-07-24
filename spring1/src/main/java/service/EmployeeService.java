package service;


import com.example.spring1.model.Employee;
import com.example.spring1.exception.EmployeeNotFoundException;
import com.example.spring1.exception.EmployeeStorageIsFullException;
import com.example.spring1.exception.EmployeeAlreadyAddedException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EmployeeService {
    private static final int MAX_EMPLOYEES = 100;
    private final Set<Employee> employees = new HashSet<>();

    public void addEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Employee storage is full");
        }
        if (!employees.add(newEmployee)) {
            throw new EmployeeAlreadyAddedException("Employee already exists");
        }
    }

    public void removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.remove(employee)) {
            throw new EmployeeNotFoundException("Employee not found");
        }
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Employee not found");
        }
        return employee;
    }

    public Set<Employee> getAllEmployees() {
        return new HashSet<>(employees);
    }
}
