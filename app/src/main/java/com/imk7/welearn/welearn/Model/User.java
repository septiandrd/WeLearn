package com.imk7.welearn.welearn.Model;

/**
 * Created by septiandrd on 10/11/17.
 */

public class User {
    String name, username, noHP, email,password,token;

    public User() {
    }

    public User(String name, String noHP, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.noHP = noHP;
        this.email = email;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNoHP() {
        return noHP;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }
}
