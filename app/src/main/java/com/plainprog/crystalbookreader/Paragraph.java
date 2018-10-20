package com.plainprog.crystalbookreader;

import java.util.ArrayList;

public class Paragraph {

    ArrayList<Word> content;

    public Paragraph(ArrayList<Word> content) {
        this.content = content;
    }
    public Paragraph(Paragraph paragraph){
        this.content = paragraph.getContent();
    }
    public Paragraph(ArrayList<Text> content, boolean uselessParameter){
        ArrayList<Word> result = new ArrayList<>();
        for(Text text: content){
            String[] words = text.getValue().split("(?=;)");
            for(int i = 0; i < words.length; i++)
                if (!words[i].isEmpty())
                    result.add(new Word(words[i], text.getTextType()));
        }
        this.content = result;
    }

    public ArrayList<Word> getContent() {
        return content;
    }
}
