package org.example.model;

public record UserDto(
        Long id,
        String firstName,
        String lastName,
        String phone,
        Object company // null, чтобы избежать цикла
) {}