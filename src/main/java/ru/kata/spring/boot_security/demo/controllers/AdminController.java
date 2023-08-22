package ru.kata.spring.boot_security.demo.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.securities.UserDetailsImpl;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

@Controller
@RequestMapping
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String showAdminPage(@AuthenticationPrincipal UserDetails user, Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", ((UserDetailsImpl) user).getUser());
        model.addAttribute("roleSet", roleService.getAllRoles());
        return "admin/adminPage";
    }

    @GetMapping("/add")
    public String newUserPage(@AuthenticationPrincipal UserDetails user, Model model) {
        model.addAttribute("user", ((UserDetailsImpl) user).getUser());
        model.addAttribute("roleSet", roleService.getAllRoles());
        return "admin/newUser";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user) {
        getUserRoles(user);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PutMapping("/{id}/update")
    public String updateUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roleSet", roleService.getAllRoles());
        getUserRoles(user);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    private void getUserRoles(User user) {
        user.setRoleSet(user.getRoleSet().stream()
                .map(role -> roleService.getRole(role.getName()))
                .collect(Collectors.toSet()));
    }
}
