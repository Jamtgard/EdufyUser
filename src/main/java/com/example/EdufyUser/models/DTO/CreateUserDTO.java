package com.example.EdufyUser.models.DTO;

import java.util.Set;

//ED-239-AWS
public class CreateUserDTO {

    private String username;
    private String email;
    private String password;
    private Boolean active;
    //ED-318-SA: removed that user can be a creator

    private Set<String> services;

    public CreateUserDTO() {
    }

    public CreateUserDTO(String username, String email, String password, boolean active, Set<String> services) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = active;
        this.services = services;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
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
    //ED-318-SA: removed that user can be a creator
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public Set<String> getServices() {
        return services;
    }
    public void setServices(Set<String> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "CreateUserDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", services=" + services +
                '}';
    }
}
