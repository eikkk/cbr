package com.plainprog.crystalbookreader;

import java.util.ArrayList;

public class Chapter {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    private ArrayList<BookItem> bookItems;

    public Chapter(ArrayList<BookItem> bookItems) {
        this.bookItems = bookItems;
        title = "";
    }
    public Chapter(Chapter chapter){
        this.title = chapter.getTitle();
        this.bookItems = chapter.getBookItems();
    }

    public ArrayList<BookItem> getBookItems() {
        return bookItems;
    }
}
