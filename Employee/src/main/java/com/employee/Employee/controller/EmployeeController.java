package com.employee.Employee.controller;

import javax.validation.Valid;
import com.employee.Employee.models.employee.Employee;
import com.employee.Employee.models.task.Task;
import com.employee.Employee.models.task.TaskService;
import com.employee.Employee.repository.EmployeeRepository;
import com.employee.Employee.repository.RoleRepository;
import com.employee.Employee.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TaskService taskService;

    @GetMapping("")
    public String viewHomePage(){
        return "index";
    }
    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "signup_form";
    }
    @PostMapping("/process_register")
    public String newEmployee(@ModelAttribute("employee") Employee employee, Model model) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(employee.getPassword());
        employee.setPassword(encodedPassword);
        employee.setRoles(new HashSet<>(roleRepository.findByName("USER")));
        employeeRepository.save(employee);
        return "register_success";
    }
    @GetMapping("/users")
    public String findAllEmployees(Model model) {
        List<Employee> employeeList = employeeRepository.findAll();
        model.addAttribute("employeeList", employeeList);
        return "employees";
    }

    @GetMapping("/addTask")
    public String taskForm(String email, Model model, HttpSession session) {
        session.setAttribute("email", email);
        model.addAttribute("task", new Task());
        return "sendTaskForm";
    }
    @PostMapping("/addTask")
    public String addTask(@Valid Task task, HttpSession session) {
        String email = (String) session.getAttribute("email");
        Employee employee = employeeRepository.findByEmail(email);
        taskService.addTask(task, employee);
        return "redirect:/employees";
    }

    @GetMapping("/dashboard/tasks")
    public String dashPage(Model model, Principal principal){
        String email = principal.getName();
        Employee employee = employeeRepository.findByEmail(email);
        model.addAttribute("tasks", taskService.findUserTask(employee));
        return "profile";
    }

    @GetMapping("/dashboard")
    public String Dashboard(Model model, Principal principal) {
        String email = principal.getName();
        Employee employee = employeeRepository.findByEmail(email);
        model.addAttribute("employee", employee);
        return "dashboard";
    }

}
