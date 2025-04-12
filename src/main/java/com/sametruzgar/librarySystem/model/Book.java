package com.sametruzgar.librarySystem.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Book {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private boolean isAvaible=true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isAvaible() {
        return isAvaible;
    }

    public void setAvaible(boolean avaible) {
        isAvaible = avaible;
    }

    public void setAvailable(boolean b) {
    }
}
