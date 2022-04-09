package com.employee.Employee.models.task;

import com.employee.Employee.models.employee.Employee;

import javax.persistence.*;


@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "task_desc", nullable = false)

    private String desc;
    @ManyToOne
    private Employee employee;

    public Task() {
    }

    public Task(Long id, String desc, Employee employee) {
        this.id = id;
        this.desc = desc;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                ", employees=" + employee +
                '}';
    }
}
