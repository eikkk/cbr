package com.plainprog.crystalbookreader;

import java.util.ArrayList;

public class Sentence {
    private ArrayList<Word> words;

    public Sentence(ArrayList<Word> words) {
        this.words = words;
    }

    public ArrayList<Word> getWords() {
        return words;
    }
}
