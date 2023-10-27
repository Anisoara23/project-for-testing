package org.example.model;

import java.time.LocalDateTime;

public class Booking {

    private int id;

    private LocalDateTime bookedAt;

    private User user;

    private Car car;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
