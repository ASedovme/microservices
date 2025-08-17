package org.example.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal budget;

    @ElementCollection
    @CollectionTable(name = "company_employees", joinColumns = @JoinColumn(name = "company_id"))
    @Column(name = "user_id")
    private List<Long> employeeIds;

    public Company() {}

    public Company(String name, BigDecimal budget, List<Long> employeeIds) {
        this.name = name;
        this.budget = budget;
        this.employeeIds = employeeIds;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public BigDecimal getBudget() { return budget; }
    public void setBudget(BigDecimal budget) { this.budget = budget; }
    public List<Long> getEmployeeIds() { return employeeIds; }
    public void setEmployeeIds(List<Long> employeeIds) { this.employeeIds = employeeIds; }
}