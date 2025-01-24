package com.sire.goal.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;

    @NotEmpty
    private String name;

    @NotEmpty
    @Column(unique = true)
    private String username;

    @NotEmpty
    @Column(unique = true)
    private String email;

    @NotEmpty
    private String password;

    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "user")
    private List<Goal> myGoals;

    public User(Integer id, String username, String email, String password){
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public User(Integer id, String username, String email, String password, List<Goal> myGoals ){
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.myGoals = myGoals;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotEmpty String getName() {
        return name;
    }

    public void setName(@NotEmpty String name) {
        this.name = name;
    }

    public User() {

    }

    @Override
    public @NotEmpty String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty String username) {
        this.username = username;
    }

    public @NotEmpty String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty String email) {
        this.email = email;
    }

    @Override
    public @NotEmpty String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty String password) {
        this.password = password;
    }

    public List<Goal> getMyGoals() {
        return myGoals;
    }

    public void setMyGoals(List<Goal> myGoals) {
        this.myGoals = myGoals;
    }









}
