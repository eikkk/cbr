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

    private ArrayList<Paragraph> paragraphs;

    public Chapter(ArrayList<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
        title = "";
    }

    public ArrayList<Paragraph> getParagraphs() {
        return paragraphs;
    }
}
