package com.plainprog.crystalbookreader;

import java.util.ArrayList;

public class ParagraphItem extends BookTextItem {
    public ParagraphItem(ArrayList<Word> content) {
        super(content);
    }

    public ParagraphItem(BookTextItem bookTextItem) {
        super(bookTextItem);
    }

    public ParagraphItem(ArrayList<Text> content, boolean uselessParameter) {
        super(content, uselessParameter);
    }
    public ParagraphItem(){super();}
}
