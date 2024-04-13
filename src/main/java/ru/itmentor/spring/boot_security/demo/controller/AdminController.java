package ru.itmentor.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/index")
    public String home() {
        return "index";
    }


    @GetMapping(value = "")
    public String getAllUser(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("allUser", userList);
        return "users";
    }

    @GetMapping(value = "/creatUser")
    public String creatUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-info";
    }

    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }


    @RequestMapping(value = "/editUser")
    public String editUser(@RequestParam("id") Long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping(value = "/userForUpdate")
    public String userForUpdate(@ModelAttribute("user") User user, @RequestParam("id") Long id) {
        userService.userForUpdate(user, id);
        return "redirect:/admin";
    }


    @RequestMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}