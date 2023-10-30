package org.example.dto;

public class UserDto {

    private String firstName;

    private String lastName;

    private int age;

    private String email;

    private String phoneNumber;

    public UserDto(String firstName, String lastName, int age, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAge(int age) {
        this.age = age;
    }
}