package com.sb04.employee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;


@Entity
public class Credentials {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String login_id;

    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getLogin_id() {
        return login_id;
    }

    public String getPassword() {
        return password;
    }
}
