package com.plainprog.crystalbookreader;

public class TextSettings {

    public TextSettings(TextType type, TextSize size, TextSpecification specification, TextPosition position){
        this.textType = type;
        this.textPosition = position;
        this.textSize = size;
        this.textSpecification = specification;
        this.isFirstInParagraph = false;
        this.isInLastLineOfParagraph = false;
    }
    public  TextSettings(TextSettings settings){
        this.textType = settings.getTextType();
        this.textSize = settings.getTextSize();
        this.textPosition = settings.getTextPosition();
        this.textSpecification = settings.getTextSpecification();
        this.isFirstInParagraph = settings.isFirstInParagraph();
        this.isInLastLineOfParagraph = settings.isInLastLineOfParagraph();
    }

    public TextSettings() {
        textType = TextType.NORMAL;
        textSize = TextSize.NORMAL;
        textSpecification = TextSpecification.NONE;
        textPosition = TextPosition.NORMAL;
        isFirstInParagraph = false;
        isInLastLineOfParagraph = false;
    }

    private TextType textType;
    private TextSize textSize;
    private TextSpecification textSpecification;
    private TextPosition textPosition;

    public boolean isFirstInParagraph() {
        return isFirstInParagraph;
    }

    public void setFirstInParagraph(boolean firstInParagraph) {
        isFirstInParagraph = firstInParagraph;
    }

    private boolean isFirstInParagraph;
    private boolean isInLastLineOfParagraph;

    public boolean isInLastLineOfParagraph() {
        return isInLastLineOfParagraph;
    }

    public void setInLastLineOfParagraph(boolean inLastLineOfParagraph) {
        isInLastLineOfParagraph = inLastLineOfParagraph;
    }

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
