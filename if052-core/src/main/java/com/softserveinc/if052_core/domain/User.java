package com.softserveinc.if052_core.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class User {

    //- Properties -//
    private int userId;

    @NotNull
    @NotEmpty
    @Length( min = 2 , max = 32)
    private String name;

    @NotNull
    @NotEmpty
    @Length( min = 2 , max = 32)
    private String surname;

    @NotNull
    @NotEmpty
    @Length( min = 2 , max = 32)
    private String middleName;

    @NotNull
    @NotEmpty
    @Length( min = 8 , max = 32)
    private String login;

    @NotNull
    @NotEmpty
    @Length( min = 8 , max = 32)
    private String password;

    @NotNull
    @NotEmpty
    @Length( min = 8 , max = 32)
    @Email
    private String email;

    private String role;

    private List<Address> addresses;

    public User(){

    }
    public User(String name, String surname, String middleName, String login,
                String password, String email) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public User(String name, String surname, String middleName, String login,
                String password, String email, String role) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    //- Getters -//
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public List <Address> getAddresses() {
        return addresses;
    }

    //- Setters -//
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMiddleName(String middlName) {
        this.middleName = middlName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddresses(List <Address> addresses) {
        this.addresses = addresses;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //- toString -//

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email ='" + email + '}';
    }
}
