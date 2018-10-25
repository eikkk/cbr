package com.plainprog.crystalbookreader;

import java.util.ArrayList;

public class HeaderItem extends BookTextItem {
    public HeaderItem(ArrayList<Word> content) {
        super(content);
    }

    public HeaderItem(BookTextItem bookTextItem) {
        super(bookTextItem);
    }

    public HeaderItem(ArrayList<Text> content, boolean uselessParameter) {
        super(content, uselessParameter);
    }
    public HeaderItem(){super();}
}
