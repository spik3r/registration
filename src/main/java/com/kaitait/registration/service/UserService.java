package com.kaitait.registration.service;

import com.kaitait.registration.domain.User;
import com.kaitait.registration.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
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
        return userRepository.getById(id);
    }
}
