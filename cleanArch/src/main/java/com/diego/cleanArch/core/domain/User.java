package com.diego.cleanArch.core.domain;

import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private String password;
    private String email;


    public String validateEmail(String email){
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail is not valid");
        }
        return email;
    }

    public void changeEmail(String newEmail){
        this.email = validateEmail(newEmail);
    }



    public User(UUID id, String name, String password, String email) {
        this.id = id;
        this.name = name ;
        this.password = password;
        this.email = validateEmail(email);
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
