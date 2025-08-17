package org.example.model;

import java.math.BigDecimal;
import java.util.List;

public record CompanyDto(
        Long id,
        String name,
        BigDecimal budget,
        List<UserDto> employees
) {
}