package com.kaitait.registration.controller;

import com.kaitait.registration.documentation.UserControllerDocumentation;
import com.kaitait.registration.domain.User;
import com.kaitait.registration.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
@Api(value = "UserController")
public class UserController implements UserControllerDocumentation {
    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<User> register(final String firstName, final String lastName, final String email, final String password) {
        log.info("register called with firstName: {}, lastName: {}, email: {}, password: {}, ", firstName, lastName, email, password);
        final User newUser = new User(firstName, lastName, email, password);
        final User savedUser = userService.register(newUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> updateById(final String id, final String firstName, final String lastName, final String email, final String password) {
        log.info("updateById called with id: {}, firstName: {}, lastName: {}, email: {}, password: {}", id, firstName, lastName, email, password);
        final User newUser = new User(firstName, lastName, email, password);
        userService.updateBy(id, newUser);
        return new ResponseEntity<>(String.format("Updated user with id %s", id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<User>> getAll() {
        log.info("getAll called");
        final List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<User>> getByEmail(final String email) {
        log.info("getByEmail called with email: {}", email);
        final List<User> users = userService.findByEmail(email);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteByEmail(final String email) {
        log.info("deleteByEmail called with email: {}", email);
        final String deleted = userService.deleteByEmail(email);
        return new ResponseEntity<>(String.format("Deleted %s users!", deleted), HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<User>> getById(final String id) {
        log.info("getById called with id: {}", id);
        final List<User> users = userService.getById(id);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteById(final String id) {
        log.info("deleteById called with email: {}", id);
        userService.deleteById(id);
        return new ResponseEntity<>(String.format("Deleted user with id %s", id), HttpStatus.NO_CONTENT);
    }
}
