package ru.ratnikov.lessons.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Mikhail Ratnikov
 */
public class Book {
    private int id;

    @NotEmpty(message = "Title of the book can not be empty")
    @Size(min = 2, max = 200, message = "Title should be between 2 and 200 characters")
    private String title;

    @NotEmpty(message = "A book can not be without author")
    @Size(min = 2, max = 100, message = "Author name should be between 2 and 100 characters")
    private String author;

    @Min(value = 1500, message = "Year of publication should be greater than 1800")
    private int year;

    public Book() { }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
