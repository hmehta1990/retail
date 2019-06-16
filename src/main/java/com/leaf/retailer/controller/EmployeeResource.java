package com.leaf.retailer.controller;

import com.leaf.retailer.model.Employee;
import com.leaf.retailer.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
@Component
public class EmployeeResource implements ErrorController {

    private static final String PATH = "/error";
    private EmployeeService employeeService;

    @Autowired
    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "employee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllEmployee() {
        List<Employee> employees = employeeService.findAll();
        if (employees.isEmpty()) {
            return null;
        } else {
            return employees;
        }
    }

    @RequestMapping(value = PATH)
    public String error() {
        return "Error handling";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping(value = "employee", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) throws URISyntaxException {
        try {
            Employee employeeResult = employeeService.save(employee);
            return ResponseEntity.created(new URI("/api/employee" + employeeResult.getId())).body(employeeResult);
        } catch (Exception e) {
            return new ResponseEntity<Employee>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "employee", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) throws URISyntaxException {
        if (employee.getId() == null) {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        try {
            Employee result = employeeService.save(employee);
            return ResponseEntity.created(new URI("/api/employee/" + result.getId())).body(result);
        } catch (Exception e) {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) throws URISyntaxException {
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }

}
