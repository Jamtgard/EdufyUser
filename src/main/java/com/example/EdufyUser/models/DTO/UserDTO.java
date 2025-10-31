package com.example.EdufyUser.models.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

//ED-86-SA
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Long id;
    private String uuid;
    private String username;
    private String email;
    private boolean creator;
    private boolean active; //ED-88-AA

    public UserDTO() {
    }

    public UserDTO(Long id, String uuid, String username, String email, boolean creator, boolean active) {
        this.id = id;
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.creator = creator;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public boolean isCreator() {
        return creator;
    }

    public void setCreator(boolean creator) {
        this.creator = creator;
    }

    //ED-88-AA
    public boolean isActive() {
        return active;
    }
    //ED-88-AA
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", creator=" + creator +
                ", active=" + active +
                '}';
    }
}
