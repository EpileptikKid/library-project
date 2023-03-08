package web.andrii.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class Book {
    private int id;
    private String name;
    private String author;
    private String reader;
    @Min(value = 0, message = "")    //Додати повідомлення про помилку
    @Max(value = 2023, message = "") //Додати повідомлення про помилку
    private int year;

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book(int id, String name, String author, String reader, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.reader = reader;
        this.year = year;
    }

    public Book(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getReader() {
        return reader;
    }

    public void setReader(String reader) {
        this.reader = reader;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
