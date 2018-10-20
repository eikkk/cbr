package com.plainprog.crystalbookreader;

public class Word extends Text{

    public Word(String value) {
        super(value);
    }
    public Word(String value, TextSettings settings){super(value,settings);}
    public Word(Text text){super(text);}
}
