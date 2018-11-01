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
            //String[] words = text.getValue().split("(?= )");
            String[] words = text.getValue().trim().split("\\s+");
            for(int i = 0; i < words.length; i++)
                if (!words[i].isEmpty() && !words[i].trim().isEmpty()){
                    String word = words[i];
                    if (i != words.length-1)
                        word = word+" ";
                        result.add(new Word(word, text.getSettings()));
                }
        }
        this.content = result;
    }
    public BookTextItem(){content = new ArrayList<>();}

    public ArrayList<Word> getContent() {
        return content;
    }

    public void addContent(Text text){
        ArrayList<Word> result = new ArrayList<>();
            //String[] words = text.getValue().split("(?= )");
        String[] words = text.getValue().split("\\s+");
            for(int i = 0; i < words.length; i++)
                if (!words[i].isEmpty() && !words[i].trim().isEmpty()){
                    String word = words[i] + " ";
                    result.add(new Word(word, text.getSettings()));
                }
        this.content = result;
    }
}
