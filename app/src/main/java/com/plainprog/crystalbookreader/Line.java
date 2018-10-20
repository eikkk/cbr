package com.plainprog.crystalbookreader;

import android.graphics.Paint;

import java.util.ArrayList;

public class Line {

    private ArrayList<Text> content;

    public Line(ArrayList<Text> content) {
        this.content = content;
    }

    public Line() {
        content = new ArrayList<>();
    }
    public void add(Text content){
        this.content.add(content);
    }
    public ArrayList<Text> getContent() {
        return content;
    }
}
