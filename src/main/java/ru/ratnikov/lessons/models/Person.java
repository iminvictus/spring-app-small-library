package ru.ratnikov.lessons.models;

import javax.validation.constraints.*;

/**
 * @author Mikhail Ratnikov
 */
public class Person {
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name length should be between 2 to 30 characters")
    private String name;

    @Min(value = 1, message = "Age should be greater than zero")
    private int age;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    //Country, City, Zip code (6 digit)
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}",
            message = "Address should be in this format: Country, City, Zip Code (6 digits)")
    private String address;

    public Person() { }

    public Person(int id, String name, int age, String email, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
