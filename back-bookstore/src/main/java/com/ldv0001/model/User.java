package com.ldv0001.model;

import javax.persistence.*;

@Entity
@Table(name="usr")
public class User {
    public User(){}

    public User(Role role){
        this.role=role;
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;
    String password;

    @Enumerated(EnumType.STRING)
    Role role;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
