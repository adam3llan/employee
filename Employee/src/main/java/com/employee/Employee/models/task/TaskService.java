package com.employee.Employee.models.task;

import com.employee.Employee.models.employee.Employee;
import com.employee.Employee.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    public void addTask(Task task, Employee employee) {
        task.setEmployee(employee);
        taskRepository.save(task);
    }
    public List<Task> findUserTask(Employee employee){

        return taskRepository.findByEmployee(employee);
    }
}
