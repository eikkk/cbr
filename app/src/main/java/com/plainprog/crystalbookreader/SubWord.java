package com.plainprog.crystalbookreader;

public class SubWord extends Text {
    public Text getWord() {
        return word;
    }

    public void setWord(Text word) {
        this.word = word;
    }

    private Text word;

    private boolean hyphenAfter;

    public boolean isHyphenAfter() {
        return hyphenAfter;
    }

    public void setHyphenAfter(boolean hyphenAfter) {
        this.hyphenAfter = hyphenAfter;
    }

    public boolean isHyphenBefore() {
        return hyphenBefore;
    }

    public void setHyphenBefore(boolean hyphenBefore) {
        this.hyphenBefore = hyphenBefore;
    }

    private boolean hyphenBefore;

    public SubWord(String value, TextSettings settings, Text word) {
        super(value, settings);
        this.word = word;
        hyphenAfter = false;
        hyphenBefore = false;
    }
    public boolean isWithHyphen(){
        if (hyphenBefore || hyphenAfter)
            return  true;
        return  false;
    }
}
