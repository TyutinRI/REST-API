package com.tyutin.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Класс-сущность для отображения пользователя в базе данных
 */

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    private String surname;
    private LocalDate birthday;
    private String login;
    private String password;

    public User(){}

    public User(Long id, String name, String surname, LocalDate birthday, String login, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
