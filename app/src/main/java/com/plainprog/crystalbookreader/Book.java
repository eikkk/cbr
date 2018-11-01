package com.plainprog.crystalbookreader;

import java.util.ArrayList;

public abstract class Book {

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public void setChapters(ArrayList<Chapter> chapters) {
        this.chapters = chapters;
    }

    private String title;
    private ArrayList<Author> authors;
    private ArrayList<Chapter> chapters;
    private Language language;

    public String getTitle() {
        return title;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public ArrayList<Chapter> getChapters() {
        return chapters;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Book(){
    }
    public Book(Book book){
        this.title = book.title;
        this.authors = book.authors;
        this.chapters = book.chapters;
    }
}
