package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.createUser(
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getCompanyId()
        );
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userData) {
        return userService.updateUser(
                id,
                userData.getFirstName(),
                userData.getLastName(),
                userData.getPhone()
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // Новый эндпоинт: получить всех пользователей компании
    @GetMapping("/company/{companyId}")
    public List<User> getUsersByCompanyId(@PathVariable Long companyId) {
        return userService.getUsersByCompanyId(companyId);
    }
}