package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;

    private String firstName;

    private String lastName;

    private int age;

    private String email;

    private String phoneNumber;

    private final List<Booking> bookings = new ArrayList<>();

    public User(int id, String firstName, String lastName, int age, String email, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void addBooking(Booking booking){
        if (booking.getUser() != null && booking.getUser().equals(this)) {
            throw new IllegalArgumentException("Car is already booked by this user");
        } else if (booking.getUser() != null){
            throw new IllegalArgumentException("Car is already booked by other user");
        }
        bookings.add(booking);
        booking.setUser(this);
    }

    public void removeBooking(Booking booking){
        if (!bookings.contains(booking)){
            throw new IllegalArgumentException("No such booking!");
        }
        bookings.remove(booking);
        booking.setUser(null);
    }

    public List<Booking> getBookings() {
        return bookings;
    }
}
