package org.example.service;

import org.example.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public User createUser(String firstName, String lastName, String phone, Long companyId) {
        User user = new User(firstName, lastName, phone, companyId);
        user.setId(counter.incrementAndGet());
        users.add(user);
        return user;
    }

    public User getUserById(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User updateUser(Long id, String firstName, String lastName, String phone) {
        User user = getUserById(id);
        if (user != null) {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhone(phone);
        }
        return user;
    }

    public boolean deleteUser(Long id) {
        return users.removeIf(u -> u.getId().equals(id));
    }

    public List<User> getUsersByCompanyId(Long companyId) {
        List<User> result = new ArrayList<>();
        for (User user : users) {
            if (user.getCompanyId() != null && user.getCompanyId().equals(companyId)) {
                result.add(user);
            }
        }
        return result;
    }
}