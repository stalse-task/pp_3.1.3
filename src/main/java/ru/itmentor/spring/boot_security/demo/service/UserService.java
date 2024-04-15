package ru.itmentor.spring.boot_security.demo.service;


import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUsers();

    void deleteUser(Long id);

    User userForUpdate(User user, Long id);

    User getUser(Long id);

    User getUserByUsername(String username);

}
