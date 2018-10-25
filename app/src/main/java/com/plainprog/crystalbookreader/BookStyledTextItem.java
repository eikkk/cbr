package com.plainprog.crystalbookreader;

import java.time.format.TextStyle;
import java.util.ArrayList;

public class BookStyledTextItem extends BookTextItem {
    public BookStyledTextItem(ArrayList<Word> content) {
        super(content);
    }

    public BookStyledTextItem(BookTextItem bookTextItem) {
        super(bookTextItem);
    }

    public BookStyledTextItem(ArrayList<Text> content, boolean uselessParameter) {
        super(content, uselessParameter);
    }

    private Align align;
    private TextStyle textStyle;

}
