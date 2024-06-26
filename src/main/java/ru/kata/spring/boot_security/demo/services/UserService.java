package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    List<User> getAllUsers();
    User getUserById(Long id);
}
