package com.plainprog.crystalbookreader;

import java.util.ArrayList;

public class LinedBookTextItem extends BookTextItem {

    public LinedBookTextItem(ArrayList<Word> content, ArrayList<Line> lines) {
        super(content);
        this.lines = lines;
    }

    public LinedBookTextItem(BookTextItem bookTextItem, ArrayList<Line> lines) {
        super(bookTextItem);
        this.lines = lines;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    private ArrayList<Line> lines;

}
