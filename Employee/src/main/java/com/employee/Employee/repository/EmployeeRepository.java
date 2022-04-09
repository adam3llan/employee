package com.employee.Employee.repository;

import com.employee.Employee.models.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT u FROM Employee u WHERE u.email = ?1")
    Employee findByEmail(String email);
}
