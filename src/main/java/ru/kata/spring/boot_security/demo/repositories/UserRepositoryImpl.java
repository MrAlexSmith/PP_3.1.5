package ru.kata.spring.boot_security.demo.repositories;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.kata.spring.boot_security.demo.models.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    @Autowired
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User ", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(long id) {
        Query query = entityManager.createQuery("delete from User where id =:userId");
        query.setParameter("userId", id);
        query.executeUpdate();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username");
        query.setParameter("username", username);
        try {
            return Optional.ofNullable((User) query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
