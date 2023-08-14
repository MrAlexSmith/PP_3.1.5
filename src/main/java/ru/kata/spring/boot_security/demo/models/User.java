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

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username", unique = true)
    @NotEmpty(message = "Логин не может быть пустым!")
    @Size(min = 2, max = 255, message = "Логин должен содержать от 2 до 255 символов!")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "Пароль не может быть пустым!")
    @Size(min = 3, max = 255, message = "Пароль должен содержать минимум 3 символа")
    private String password;

    @Column(name = "name")
    @NotEmpty(message = "Имя не может быть пустым!")
    @Size(max = 255, message = "Имя не может содержать более 255 символов!")
    private String name;

    @Column(name = "surname")
    @NotEmpty(message = "Фамилия не может быть пустой!")
    @Size(max = 255, message = "Фамилия не может содержать более 255 символов!")
    private String surname;

    @Column(name = "age")
//    @NotEmpty(message = "Возраст не может быть пустым!")
//    @Size(min = 18, max = 61, message = "")
    @Min(value = 18, message = "Возраст не может быть меньше 18 лет!")
    @Max(value = 61, message = "Возраст не может быть больше 61 года!")
    private byte age;

    @ManyToMany(fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roleSet;

    @Column(name = "enabled")
    private boolean enabled;

    public User() {
    }

    public User(String username, String password, String name, String surname, byte age, Set<Role> roleSet, boolean enabled) {
        this.username = username;
        this.password = password;
        this.name     = name;
        this.surname  = surname;
        this.age      = age;
        this.roleSet  = roleSet;
        this.enabled  = enabled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", roleSet=" + roleSet +
                ", enabled=" + enabled +
                '}';
    }

    public String getRoleSetToString() {
        String stringRoleSet = "";

        for (Role role : roleSet) {
            if (!stringRoleSet.isEmpty()) {
                stringRoleSet += ", ";
            }
            String roleName = role.getName().replace("ROLE_", "");
            stringRoleSet += "\"" + roleName + "\"";
        }

        return stringRoleSet;
    }

}
