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

    public User register(final User user) {
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

    // Soft delete
    public void deleteById(final String id) {
        User user = userRepository.getById(id);
        userRepository.save(softDelete(user));
    }

    //Hard delete
    public String deleteByEmail(final String email) {
        return userRepository.deleteByEmail(email);
    }

    public List<User> findByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    public void updateBy(final String id, final User newUser) {
        final User oldUser = userRepository.getById(id);
        userRepository.save(getUpdatedUser(oldUser, newUser));
    }

    private User softDelete(final User user) {
        final User deletedUser = new User("-", "-", "-", "-");
        return getUpdatedUser(user, deletedUser);
    }
    private User getUpdatedUser(final User oldUser, final User newUser) {
        return new User(oldUser, newUser);
    }
}
