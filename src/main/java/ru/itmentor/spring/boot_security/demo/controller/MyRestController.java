package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    private final UserService userService;

    @Autowired
    public MyRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable(name = "id") Long id) {
        User user = userService.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/users/{id}")
    public ResponseEntity<String> userForUpdate(@PathVariable Long id, @RequestBody User user) {
        User existingUser = userService.getUser(id);
        if (existingUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.userForUpdate(user, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
