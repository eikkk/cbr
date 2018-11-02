package com.plainprog.crystalbookreader;

import android.graphics.PointF;
import android.graphics.RectF;

public class Text {

    private String value;
    private TextSettings settings;
    private BookTextItem bookItem;

    public RectF getDrawRectangle() {
        return drawRectangle;
    }

    public void setDrawRectangle(RectF drawRectangle) {
        this.drawRectangle = drawRectangle;
    }

    private RectF drawRectangle;

    public Text(String value, BookTextItem bookItem){
        this.value = value;
        this.settings = new TextSettings();
        this.settings.setTextType(TextType.NORMAL);
        this.settings.setTextSize(TextSize.NORMAL);
        this.settings.setTextSpecification(TextSpecification.NONE);
        this.settings.setTextPosition(TextPosition.NORMAL);
        this.bookItem = bookItem;
    }

    public TextSettings getSettings() {
        return settings;
    }

    public BookTextItem getBookItem() {
        return bookItem;
    }

    public  Text(Text text){
        value = new String(text.getValue());
        settings = new TextSettings(text.getSettings());
        this.bookItem = text.getBookItem();
    }
    public Text(String value, TextSettings settings, BookTextItem bookItem){
        this.value = value;
        this.settings = new TextSettings(settings);
        this.bookItem = bookItem;
    }

    public String getValue() {
        return value;
    }

    public boolean isHeader()
    {
        if (settings.getTextSize() == TextSize.H1 || settings.getTextSize() == TextSize.H2 || settings.getTextSize() == TextSize.H3 ||settings.getTextSize() == TextSize.H4 || settings.getTextSize() == TextSize.H5 || settings.getTextSize() == TextSize.H6)
            return true;
        else
            return false;
    }

}
