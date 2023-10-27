package org.example.model;

import java.time.LocalDateTime;

public class Booking {

    private int id;

    private LocalDateTime bookedAt;

    private User user;

    private Car car;

    public Booking(int id, LocalDateTime bookedAt, User user, Car car) {
        this.id = id;
        this.bookedAt = bookedAt;
        this.user = user;
        this.car = car;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return this.id;
    }
}
