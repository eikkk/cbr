package com.plainprog.crystalbookreader;

public class Word extends Text{
    public Word(String value, BookTextItem bookItem) {
        super(value, bookItem);
    }

    public Word(Text text) {
        super(text);
    }

    public Word(String value, TextSettings settings, BookTextItem bookItem) {
        super(value, settings, bookItem);
    }
}
