package com.plainprog.crystalbookreader;

import java.util.ArrayList;

public class Page {

    ArrayList<Line> lines;

    public Page(ArrayList<Line> lines) {
        this.lines = lines;
    }

    public Page() {
        lines = new ArrayList<>();
    }

    public void addLine(Line line){
        lines.add(line);
    }

    public ArrayList<Line> getLines() {
        return lines;
    }
}
