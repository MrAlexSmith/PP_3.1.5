package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();
    void saveUser(User user);
    User getUser(long id);
    void deleteUser(long id);
}
