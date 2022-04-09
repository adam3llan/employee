package com.employee.Employee.repository;

import com.employee.Employee.models.employee.Employee;
import com.employee.Employee.models.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByEmployee(Employee employee);
}
