package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.securities.UserDetailsImpl;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

@Component
public class UserValidator implements Validator {

    private final UserServiceImpl userService;

    @Autowired
    public UserValidator(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        try {
            UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
            if (((User)target).getId() == ((User)((UserDetailsImpl)userDetails).getUser()).getId()) {
                return; // это один и тот же пользователь
            }
        }
        catch (UsernameNotFoundException ignored) {
            return; // пользователь не найден, значит имя уникальное
        }

        errors.rejectValue("username", "", "Пользователь с таким именем существует!");
    }
}
