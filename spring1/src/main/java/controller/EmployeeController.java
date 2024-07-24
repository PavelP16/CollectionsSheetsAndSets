package controller;


import com.example.spring1.exception.EmployeeAlreadyAddedException;
import com.example.spring1.exception.EmployeeNotFoundException;
import com.example.spring1.exception.EmployeeStorageIsFullException;
import com.example.spring1.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.EmployeeService;

import java.util.Set;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestParam String firstName,
                                                @RequestParam String lastName) {
        try {
            employeeService.addEmployee(firstName, lastName);
            Employee employee = new Employee(firstName, lastName);
            return ResponseEntity.ok(employee);
        } catch (EmployeeStorageIsFullException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        } catch (EmployeeAlreadyAddedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Employee> removeEmployee(@RequestParam String firstName,
                                                   @RequestParam String lastName) {
        try {
            employeeService.removeEmployee(firstName, lastName);
            Employee employee = new Employee(firstName, lastName);
            return ResponseEntity.ok(employee);
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/find")
    public ResponseEntity<Employee> findEmployee(@RequestParam String firstName,
                                                 @RequestParam String lastName) {
        try {
            Employee employee = employeeService.findEmployee(firstName, lastName);
            return ResponseEntity.ok(employee);
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Set<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
}

