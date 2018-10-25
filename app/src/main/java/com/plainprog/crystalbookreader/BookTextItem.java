package com.plainprog.crystalbookreader;

import java.util.ArrayList;

public class BookTextItem extends BookItem {

    private ArrayList<Word> content;

    public BookTextItem(ArrayList<Word> content) {
        this.content = content;
    }
    public BookTextItem(BookTextItem bookTextItem){
        this.content = bookTextItem.getContent();
    }
    public BookTextItem(ArrayList<Text> content, boolean uselessParameter){
        ArrayList<Word> result = new ArrayList<>();
        for(Text text: content){
            String[] words = text.getValue().split("(?=;)");
            for(int i = 0; i < words.length; i++)
                if (!words[i].isEmpty())
                    result.add(new Word(words[i], text.getSettings()));
        }
        this.content = result;
    }
    public BookTextItem(){content = new ArrayList<>();}

    public ArrayList<Word> getContent() {
        return content;
    }

    public void addContent(Text text){
        ArrayList<Word> result = new ArrayList<>();
            String[] words = text.getValue().split("(?=;)");
            for(int i = 0; i < words.length; i++)
                if (!words[i].isEmpty())
                    result.add(new Word(words[i], text.getSettings()));
        this.content = result;
    }
}
