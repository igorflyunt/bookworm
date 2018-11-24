package com.softserve.librarymanager.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Author extends Person {
    private Date birthDate;
    private List<Book> books = new ArrayList<>();
    private List<Genre> genres = new ArrayList<>();

    public Author() { }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Author{" +
                "birthDate=" + birthDate +
                ", books=" + books +
                '}' + super.toString();
    }
}
