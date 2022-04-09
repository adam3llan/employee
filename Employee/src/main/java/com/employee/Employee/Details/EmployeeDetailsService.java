package com.employee.Employee.Details;
import com.employee.Employee.models.employee.Employee;
import com.employee.Employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class EmployeeDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository repo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = repo.findByEmail(email);
        if (employee == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new EmployeeDetails(employee);
    }

}
