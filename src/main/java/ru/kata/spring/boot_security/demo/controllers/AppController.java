package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class AppController {

    private final UserService userService;

    @Autowired
    public AppController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String showAllUsers(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "all-users";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-info";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @RequestMapping("/updateInfo")
    public String updateUser(@RequestParam("usrId") int id, Model model, Principal principal) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        System.out.println(principal.getName()); // имя аутентифицированного пользователя
        return "user-info";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("usrId") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
