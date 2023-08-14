package ru.kata.spring.boot_security.demo.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.securities.UserDetailsImpl;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AppController {

    private final UserServiceImpl userService;

    private final RoleServiceImpl roleService;

    private final UserValidator userValidator;

    @Autowired
    public AppController(UserService userService, RoleServiceImpl roleService, UserValidator userValidator) {
        this.userService = (UserServiceImpl) userService;
        this.roleService = roleService;
        this.userValidator = userValidator;
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
        List<Role> allRoles = roleService.getAllRoles();
        model.addAttribute("allRoles", allRoles);
        return "user-info";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult,
                           @RequestParam("listRoles") List<String> listRoles,
                           Model model) {

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            List<Role> selectedRoles = new ArrayList<>();
            for (String roleName : listRoles) {
                Role role = roleService.getRoleByName(roleName);
                if (role != null) {
                    selectedRoles.add(role);
                }
            }
            user.setRoleSet(new HashSet<>(selectedRoles));
            model.addAttribute("user", user);
            List<Role> allRoles = roleService.getAllRoles();
            model.addAttribute("allRoles", allRoles);
            return "/user-info";
        }

        Set<Role> userRoles = new HashSet<>();

        for (String roleName : listRoles) {
            Role role = roleService.getRoleByName(roleName);
            if (role != null) {
                userRoles.add(role);
            }
        }

        user.setRoleSet(userRoles);

        userService.saveUser(user);

        return "redirect:/admin";
    }

    @RequestMapping("/updateInfo")
    public String updateUser(@RequestParam("usrId") int id, Model model, Principal principal) {
        User userLogin = ((UserDetailsImpl) userService.loadUserByUsername(principal.getName())).getUser();
        String roles = userLogin.getRoleSet().toString();

        if (roles.contains("ROLE_USER") && !roles.contains("ROLE_ADMIN") && userLogin.getId() != id) {
            return "access-denied";
        } else if (roles.contains("ROLE_USER") && !roles.contains("ROLE_ADMIN")) {
            model.addAttribute("hidden", true);
        }
        else {
            model.addAttribute("hidden", false);
        }

        User user = userService.getUser(id);
        model.addAttribute("user", user);
        List<Role> allRoles = roleService.getAllRoles();
        model.addAttribute("allRoles", allRoles);

        return "user-info";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("usrId") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @RequestMapping("/cancel")
    public String cancel(Model model, Principal principal) {
        User userLogin = ((UserDetailsImpl) userService.loadUserByUsername(principal.getName())).getUser();
        String roles = userLogin.getRoleSet().toString();

        if (roles.contains("ROLE_ADMIN")) {
            return "redirect:/admin";
        }

        model.addAttribute("user", userLogin);
        List<Role> allRoles = roleService.getAllRoles();
        model.addAttribute("allRoles", allRoles);
        model.addAttribute("hidden", true);

        return "user-info";
    }

    @RequestMapping("/logoutapp")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Очистка сессии
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Сброс аутентификации
        SecurityContextHolder.clearContext();


        // Перенаправление на страницу логаута
        return "redirect:/logout-success";
    }

    @RequestMapping("/logout-success")
    public String logoutSuccess() {
        return "logoutapp"; // Имя шаблона для страницы успешного выхода
    }
}
