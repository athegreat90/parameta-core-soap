package com.parameta.employee.soap.core.repo;

import com.parameta.employee.soap.core.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepo extends JpaRepository<Employee, Integer> {
    Employee findEmployeesByDocument(String document);
}
