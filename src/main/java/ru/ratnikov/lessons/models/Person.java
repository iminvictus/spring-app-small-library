package ru.ratnikov.lessons.models;

import javax.validation.constraints.*;

/**
 * @author Mikhail Ratnikov
 */
public class Person {
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 5, max = 100, message = "Full name must be between 5 and 100 characters")
    @Pattern(regexp = "[A-zА-я]+ [A-zА-я]+ [A-zА-я]+",
            message = "Full name should consist of first name, last name and patronym, like that: Francis Albert Scott")
    private String name;

    @Min(value = 1922, message = "Year of birth must be greater than 1922. Is the person really a hundred years old?")
    private int birth;

    //Default constructor for Spring
    public Person() { }

    public Person(String name, int birth) {
        this.name = name;
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirth() {
        return birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
