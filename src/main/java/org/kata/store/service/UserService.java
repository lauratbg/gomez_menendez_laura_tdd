package org.kata.store.service;

import org.kata.store.domain.User;
import org.kata.store.repository.UserRepository;

/*
 * TODO: Alta de usuarios.
 *
 * Requisitos (escribe primero el test de cada uno):
 *   - Se puede dar de alta un usuario con id, nombre y apellidos.
 *   - No se puede dar de alta un usuario con un id que ya existe: se lanza una excepción.
 *
 * Pista: recibe el UserRepository por constructor. Así, en el test, puedes pasarle un mock.
 */
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        if (userRepository == null) {
            throw new IllegalArgumentException("UserRepository must not be null");
        }
        this.userRepository = userRepository;
    }

    public User registerUser(String id, String name, String surname) {
        if (userRepository.existsById(id)) {
            throw new IllegalArgumentException("User already exists with id: " + id);
        }

        User user = new User(id, name, surname);
        userRepository.save(user);
        
        return user;
    }
}
