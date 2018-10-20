package com.plainprog.crystalbookreader;

public class TextSettings {

    public TextSettings(TextType type, TextSize size, TextSpecification specification, TextPosition position){
        this.textType = type;
        this.textPosition = position;
        this.textSize = size;
        this.textSpecification = specification;
    }
    public  TextSettings(TextSettings settings){
        this.textSpecification = settings.getTextSpecification();
        this.textSize = settings.getTextSize();
        this.textPosition = settings.getTextPosition();
        this.textSpecification = settings.getTextSpecification();
    }

    public TextSettings() {
    }

    private TextType textType;
    private TextSize textSize;
    private TextSpecification textSpecification;
    private TextPosition textPosition;


    public TextType getTextType() {
        return textType;
    }
    public void setTextType(TextType textType) {
        this.textType = textType;
    }

    public TextSize getTextSize() {
        return textSize;
    }
    public void setTextSize(TextSize textSize) {
        this.textSize = textSize;
    }

    public TextSpecification getTextSpecification() {
        return textSpecification;
    }
    public void setTextSpecification(TextSpecification textSpecification) {
        this.textSpecification = textSpecification;
    }

    public TextPosition getTextPosition() {
        return textPosition;
    }
    public void setTextPosition(TextPosition textPosition) {
        this.textPosition = textPosition;
    }
}
