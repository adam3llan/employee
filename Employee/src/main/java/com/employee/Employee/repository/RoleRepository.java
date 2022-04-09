package com.employee.Employee.repository;

import com.employee.Employee.models.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Set<Role> findByName(String USER);
}
