package org.kata.store.service;

import org.kata.store.domain.User;
import org.kata.store.repository.UserRepository;

import java.util.Objects;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(
                userRepository,
                "UserRepository must not be null"
        );
    }

    public User registerUser(String id, String name, String surname) {
        validateUserDoesNotExist(id);

        User user = new User(id, name, surname);
        userRepository.save(user);
        return user;
    }

    private void validateUserDoesNotExist(String id) {
        if (userRepository.existsById(id)) {
            throw new IllegalArgumentException("User already exists with id: " + id);
        }
    }
}