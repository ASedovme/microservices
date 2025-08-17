package org.example.service;

import org.example.model.CompanyDto;
import org.example.model.UserDto;
import org.example.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired private RestTemplate restTemplate;

    @Value("${user.service.url:http://user-service}")
    private String userServiceUrl;

    public List<CompanyDto> getAllWithEmployees() {
        return companyRepository.findAll().stream()
                .map(company -> {
                    List<UserDto> employees = company.getEmployeeIds().stream()
                            .map(id -> getUserById(id))
                            .filter(java.util.Objects::nonNull)
                            .collect(Collectors.toList());
                    return new CompanyDto(company.getId(), company.getName(), company.getBudget(), employees);
                })
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long userId) {
        try {
            var url = userServiceUrl + "/api/users/" + userId;
            var response = restTemplate.getForEntity(url, UserDto.class);
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }
}