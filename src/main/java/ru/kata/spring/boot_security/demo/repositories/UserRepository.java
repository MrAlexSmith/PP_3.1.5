package ru.kata.spring.boot_security.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import ru.kata.spring.boot_security.demo.models.User;

@Repository
public interface UserRepository {
    List<User> getAllUsers();

    void saveUser(User user);

    User getUser(int id);

    void deleteUser(int id);

    Optional<User> findByUsername(String username);
}