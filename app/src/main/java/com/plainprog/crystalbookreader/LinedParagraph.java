package com.plainprog.crystalbookreader;

import java.util.ArrayList;

public class LinedParagraph extends Paragraph {

    public LinedParagraph(ArrayList<Word> content, ArrayList<Line> lines) {
        super(content);
        this.lines = lines;
    }

    public LinedParagraph(Paragraph paragraph, ArrayList<Line> lines) {
        super(paragraph);
        this.lines = lines;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    private ArrayList<Line> lines;

}
