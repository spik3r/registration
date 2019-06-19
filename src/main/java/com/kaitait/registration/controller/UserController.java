package com.kaitait.registration.controller;

import com.kaitait.registration.domain.User;
import com.kaitait.registration.repository.UserRepository;
import com.kaitait.registration.service.Producer;
import com.kaitait.registration.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@Slf4j
public class UserController {
//TODO: add ControllerAdvice
    private UserRepository userRepository;
    private final Producer producer;
    private final UserService userService;

    @Autowired
    public UserController(final UserRepository userRepository, final Producer producer, final UserService userService) {
        this.userRepository = userRepository;
        this.producer = producer;
        this.userService = userService;
    }

    //Register
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password) {
        log.info("____ register called with firstName: {}, lastName: {}, email: {}, password: {}, ", firstName, lastName, email, password);
        final User newUser = new User(firstName, lastName, email, password);
        final User savedUser = userService.register(newUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //Edit

    //Get All
    @GetMapping("/")
    public ResponseEntity<List<User>> getAll() {
        log.info("getAll called");
        final List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    //Get By ID
    @GetMapping("/{id}")
    public ResponseEntity<List<User>> getById(@PathVariable final String id) {
        log.info("getById called with id: {}", id);
        final List<User> user = userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    //Delete
    //Delete Multiple

    @GetMapping("/health")
    public ResponseEntity getIndex() {
        return new ResponseEntity("I'm alive :)", HttpStatus.OK);
    }

    @GetMapping("/kafka")
    public String sendMsg() {
        User u = userRepository.save(new User("AAA","BBB", "test@kafkamail.com", "$3cre7@123"));
        producer.sendMessage(u);
        return "kafka saved: " + u.getId();
    }

    @GetMapping("/db")
    public String saveToDB() {
        userRepository.save(new User("Kai","Tait", "kaiftait@gmail.com", "top_secret@123"));
        return "db saved";
    }
    @PostMapping("/{user}")
    public String saveUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "user saved: " + user;
    }
    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("registration", new User());
        return "greeting";
    }

    @GetMapping("/email{email}")
    public User getUserByEmail(@PathVariable("email") final String email) {
        return userRepository.findByEmail(email);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
