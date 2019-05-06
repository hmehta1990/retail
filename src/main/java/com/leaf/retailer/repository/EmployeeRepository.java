package com.leaf.retailer.repository;


import com.leaf.retailer.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    //Employee findByName(String name);
//    Boolean exists(Integer id);

    //  Employee find(Integer id);
    // void delete(Integer id);


}
