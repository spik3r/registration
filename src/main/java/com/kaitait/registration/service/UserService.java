package com.kaitait.registration.service;

import com.kaitait.registration.domain.User;
import com.kaitait.registration.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private Producer producer;

    @Inject
    public UserService(final UserRepository userRepository, final Producer producer) {
        this.userRepository = userRepository;
        this.producer = producer;
    }

    public User register(User user) {
        final User savedUser = userRepository.save(user);
        producer.sendMessage(savedUser);
        return savedUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getById(final String id) {
        return Collections.singletonList(userRepository.getById(id));
    }

    public void deleteById(final String id) {
        userRepository.deleteById(id);
    }

    public String deleteByEmail(final String email) {
        return userRepository.deleteByEmail(email);
    }

    public List<User> findByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    public void updateBy(String id, User newUser) {
        final User oldUser = userRepository.getById(id);
        final User updatedUser = new User(oldUser, newUser);
        userRepository.save(updatedUser);
    }
}
