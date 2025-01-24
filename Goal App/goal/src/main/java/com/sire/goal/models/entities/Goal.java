package com.sire.goal.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Goal {
    @Id
    @GeneratedValue
    private Integer id;

    private String text;


    @JsonIgnoreProperties("Goal")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    // Default constructor
    public Goal() {}

    // Constructor without user
    public Goal(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    // Constructor with user
    public Goal(Integer id, String text, User user) {
        this.id = id;
        this.text = text;
        this.user = user;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date(); // Automatically set the current date and time
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
