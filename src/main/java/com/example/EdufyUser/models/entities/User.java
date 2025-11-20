package com.example.EdufyUser.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

// ED-138-SJ
@Entity
@Table(name = "edufy_user")
public class User {

// Attributes ----------------------------------------------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //ED-239-AWS Changed from GenerationType.AUTO to -> IDENTITY
    private Long id;

    @Column(name = "user_sub",unique = true, nullable = false)
    private String uuid;

    @Column(name = "user_username", nullable = false)
    private String username;

    @Column(name = "user_email", nullable = false)
    private String email;

    @Column(name = "user_creator")
    private boolean creator;

    @Column(name = "user_active")
    private boolean active;

// Constructors --------------------------------------------------------------------------------------------------------

    public User() {}

    public User(
            String uuid,
            String username,
            String email,
            boolean creator,
            boolean active)
    {
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.creator = creator;
        this.active = active;
    }

    public User(
            Long id,
            String uuid,
            String username,
            String email,
            boolean creator,
            boolean active)

    {
        this.id = id;
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.creator = creator;
        this.active = active;
    }

    public User(User user)
    {
        this.id = user.id;
        this.uuid = user.uuid;
        this.username = user.username;
        this.email = user.email;
        this.creator = user.creator;
        this.active = user.active;
    }


// Getters & Setters ---------------------------------------------------------------------------------------------------

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getUuid() {return uuid;}
    public void setUuid(String uuid) {this.uuid = uuid;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public boolean isCreator() {return creator;}
    public void setCreator(boolean creator) {this.creator = creator;}

    public boolean isActive() {
        return active;}

    public void setActive(boolean active) {
        this.active = active;}


    // toString ------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", creator=" + creator +
                ", active=" + active +
                '}';
    }
}