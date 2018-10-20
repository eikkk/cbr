package com.plainprog.crystalbookreader;

public class Text {
    private String value;

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

    private TextType textType;
    private TextSize textSize;
    private TextSpecification textSpecification;
    private TextPosition textPosition;

    public Text(String value){
        this.value = value;
        this.textType = TextType.NORMAL;
        this.textSize = TextSize.NORMAL;
        this.textSpecification = TextSpecification.NONE;
        this.textPosition = TextPosition.NORMAL;
    }



    public String getValue() {
        return value;
    }

    public boolean isHeader()
    {
        if (textSize == TextSize.H1 || textSize == TextSize.H2 || textSize == TextSize.H3 ||textSize == TextSize.H4 || textSize == TextSize.H5 || textSize == TextSize.H6)
            return true;
        else
            return false;
    }

}
