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
    public int getSpaceCount(){
        int count = 0;
        for(int i =0; i < content.size();i++){
            Text text = content.get(i);
            if (!(text instanceof SubWord)){
                if (i != content.size()-1)
                    count++; // if its last word in line we don't set space there
            }
            else if (((SubWord)text).isSpaceAfter())
                count++;
        }
        return count;
    }
}
