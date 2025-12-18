package com.diego.cleanArch.core.domain;

import com.diego.cleanArch.core.domain.exceptions.DomainException;

import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private String password;
    private String email;


    private String validateEmail(String email){
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new DomainException("Invalid email format");
        }
        return email;
    }

    private String validateName(String name){
        if(name == null || name.isBlank()){
            throw new DomainException("Name cannot be empty");
        }
        return name.trim();
    }

    private String validatePassword(String password){
        if(password == null || password.length() < 6){
            throw new DomainException("Password must have at least 6 characters");
        }
        return password;
    }

    public void changeEmail(String newEmail){
        this.email = validateEmail(newEmail);
    }

    public void changeName(String newName){
        this.name = validateName(newName);
    }

    public void changePassword(String newPassword){
        this.password = validatePassword(newPassword);
    }

    public User(UUID id, String name, String password, String email) {
        this.id = id;
        this.name = validateName(name) ;
        this.password = validatePassword(password);
        this.email = validateEmail(email);
    }

    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
}
