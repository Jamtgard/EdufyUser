package com.example.EdufyUser.models.DTO;

//ED-239-AWS
public class CreateUserDTO {

    private String username;
    private String email;
    private String password;
    private boolean creator;
    private Boolean active;

    public CreateUserDTO() {
    }

    public CreateUserDTO(String username, String email, String password, boolean creator, boolean active) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.creator = creator;
        this.active = active;
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
    public boolean isCreator() {
        return creator;
    }
    public void setCreator(boolean creator) {
        this.creator = creator;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "CreateUserDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", creator=" + creator +
                ", active=" + active +
                '}';
    }

}
