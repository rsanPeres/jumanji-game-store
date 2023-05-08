package com.jumanji.games.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Notification{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    private String name;
    private String message;
    private LocalDateTime arrival = LocalDateTime.now();

    protected Notification(){}

    public Notification(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }
}
