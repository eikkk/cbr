package com.plainprog.crystalbookreader;

import java.util.ArrayList;

public class LinedChapter extends Chapter {
    public LinedChapter(Chapter chapter, ArrayList<LinedBookTextItem> linedParagraphs) {
        super(chapter.getBookItems());
        this.linedParagraphs = linedParagraphs;
    }

    public ArrayList<LinedBookTextItem> getLinedParagraphs() {
        return linedParagraphs;
    }

    private ArrayList<LinedBookTextItem> linedParagraphs;
}
