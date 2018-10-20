package com.plainprog.crystalbookreader;

import java.util.ArrayList;

public class LinedChapter extends Chapter {
    public LinedChapter(Chapter chapter, ArrayList<LinedParagraph> linedParagraphs) {
        super(chapter.getParagraphs());
        this.linedParagraphs = linedParagraphs;
    }

    public ArrayList<LinedParagraph> getLinedParagraphs() {
        return linedParagraphs;
    }

    private ArrayList<LinedParagraph> linedParagraphs;
}
