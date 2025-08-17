package org.example.controller;

import org.example.model.Company;
import org.example.model.CompanyDto;
import org.example.model.UserDto;
import org.example.repository.CompanyRepository;
import org.example.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired private CompanyRepository companyRepository;

    @GetMapping
    public List<CompanyDto> getAll() {
        return companyService.getAllWithEmployees();
    }

    @GetMapping("/{id}")
    public CompanyDto getById(@PathVariable Long id) {
        var company = companyRepository.findById(id).orElse(null);
        if (company == null) return null;
        List<UserDto> employees = company.getEmployeeIds().stream()
                .map(id2 -> companyService.getUserById(id2))
                .filter(java.util.Objects::nonNull)
                .collect(java.util.stream.Collectors.toList());
        return new CompanyDto(company.getId(), company.getName(), company.getBudget(), employees);
    }

    @PostMapping
    public Company create(@RequestBody Company company) {
        return companyRepository.save(company);
    }

    @PutMapping("/{id}")
    public Company update(@PathVariable Long id, @RequestBody Company company) {
        company.setId(id);
        return companyRepository.save(company);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        companyRepository.deleteById(id);
    }
}
