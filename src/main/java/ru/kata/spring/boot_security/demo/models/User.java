package ru.kata.spring.boot_security.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
//import org.hibernate.annotations.LazyCollection;          // Закомментирован, т. к. является устаревшим.
//import org.hibernate.annotations.LazyCollectionOption;    // Закомментирован, т. к. является устаревшим.

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "name")
    @NotEmpty(message = "Имя не может быть пустым!")
    @Size(max = 255, message = "Имя не может содержать более 255 символов!")
    private String name;

    @Column(name = "surname")
    @NotEmpty(message = "Фамилия не может быть пустой!")
    @Size(max = 255, message = "Фамилия не может содержать более 255 символов!")
    private String surname;

    @Column(name = "age")
    @Min(value = 18, message = "Возраст не может быть меньше 18 лет!")
    @Max(value = 61, message = "Возраст не может быть больше 61 года!")
    private byte age;

    @Email(message = "Некорректный адрес электронной почты!")
    @Column(unique = true)
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "Пароль не может быть пустым!")
    @Size(min = 3, max = 255, message = "Пароль должен содержать минимум 3 символа")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
//    @LazyCollection(LazyCollectionOption.EXTRA) // Закомментирован, т. к. является устаревшим.
    @Fetch(FetchMode.JOIN)
    @NotEmpty(message = "Не указана роль!")
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {
    }

    public User(String name, String surname, byte age, String username, String password, Set<Role> roles) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
