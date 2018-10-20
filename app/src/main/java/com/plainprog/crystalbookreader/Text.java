package com.plainprog.crystalbookreader;

public class Text {

    private String value;
    private TextSettings settings;

    public Text(String value){
        this.value = value;
        this.settings = new TextSettings();
        this.settings.setTextType(TextType.NORMAL);
        this.settings.setTextSize(TextSize.NORMAL);
        this.settings.setTextSpecification(TextSpecification.NONE);
        this.settings.setTextPosition(TextPosition.NORMAL);
    }

    public TextSettings getSettings() {
        return settings;
    }

    public  Text(Text text){
        value = new String(text.getValue());
        settings = new TextSettings(text.getSettings());
    }
    public Text(String value, TextSettings settings){
        this.value = value;
        this.settings = new TextSettings(settings);
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
