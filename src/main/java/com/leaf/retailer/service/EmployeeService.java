package com.leaf.retailer.service;

import com.leaf.retailer.model.Employee;
import com.leaf.retailer.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@Component
@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee) {
        if (employee.getId() != null) {
            throw new EntityExistsException("There is already existing enitity with such ID in data base");

        }
        return employeeRepository.save(employee);
    }

    public Employee update(Employee employee) {
        if (employee.getId() != null) {
            throw new EntityExistsException("There is already existing enitity with such ID in data base");

        }
        return employeeRepository.save(employee);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findOne(Integer id) {
        return employeeRepository.findById(id);
    }

    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }
}
