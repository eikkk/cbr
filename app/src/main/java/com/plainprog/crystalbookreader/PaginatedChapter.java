package com.plainprog.crystalbookreader;

import java.util.ArrayList;

public class PaginatedChapter extends Chapter {
    public ArrayList<Page> getPages() {
        return pages;
    }

    private ArrayList<Page> pages;
    public PaginatedChapter(Chapter chapter, ArrayList<Page> pages ) {
        super(chapter);
        this.pages = pages;
    }
}
