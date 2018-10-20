package com.plainprog.crystalbookreader;

import java.util.ArrayList;

public class PaginatedChapter extends LinedChapter {
    public ArrayList<Page> getPages() {
        return pages;
    }

    private ArrayList<Page> pages;
    public PaginatedChapter(LinedChapter Chapter, ArrayList<Page> pages ) {
        super(Chapter, Chapter.getLinedParagraphs());
        this.pages = pages;
    }
}
