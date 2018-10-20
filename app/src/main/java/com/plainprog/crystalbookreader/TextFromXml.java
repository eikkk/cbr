package com.plainprog.crystalbookreader;

public class TextFromXml extends Text {
    public TextFromXml(String value) {
        super(value);
    }
    public void setupAccordingToHistory(TextXmlElementHistory history){
        setupPositionAccordingToHistory(history);
        setupSizeAccordingToHistory(history);
        setupSpecificationsAccordingToHistory(history);
        setupTypeAccordingToHistory(history);
    }
    private void setupTypeAccordingToHistory(TextXmlElementHistory history){
        boolean isItalic = false;
        boolean isBold = false;
        if (history.contains("b") || history.contains("strong"))
            isBold = true;
        if (history.contains("i"))
            isItalic = true;
        if (isItalic && isBold)
            getSettings().setTextType(TextType.BOLD_ITALIC);
        else if (isItalic)
            getSettings().setTextType(TextType.ITALIC);
        else if (isBold)
            getSettings().setTextType(TextType.BOLD);
        else
            getSettings().setTextType(TextType.NORMAL);
    }
    private void setupSizeAccordingToHistory(TextXmlElementHistory history){
        if (history.contains("sub") || history.contains("sup"))
            getSettings().setTextSize(TextSize.SUB);
        else if (history.contains("h1"))
            getSettings().setTextSize(TextSize.H1);
        else if (history.contains("h2"))
            getSettings().setTextSize(TextSize.H2);
        else if (history.contains("h3"))
            getSettings().setTextSize(TextSize.H3);
        else if (history.contains("h4"))
            getSettings().setTextSize(TextSize.H4);
        else if (history.contains("h5"))
            getSettings().setTextSize(TextSize.H5);
        else if (history.contains("h6"))
            getSettings().setTextSize(TextSize.H6);
        else getSettings().setTextSize(TextSize.NORMAL);

    }
    private void setupSpecificationsAccordingToHistory(TextXmlElementHistory history){
        boolean isLink = false;
        boolean isListElement = false;
        if (history.contains("li"))
            isListElement = true;
        if (history.contains("link"))
            isLink = true;
        if (isLink && isListElement)
            getSettings().setTextSpecification(TextSpecification.LINK_LIST_ELEMENT);
        else if (isLink)
            getSettings().setTextSpecification(TextSpecification.LINK);
        else if (isListElement)
            getSettings().setTextSpecification(TextSpecification.LIST_ELEMENT);
        else getSettings().setTextSpecification(TextSpecification.NONE);
    }
    private void setupPositionAccordingToHistory(TextXmlElementHistory history){
        if (history.hasAnyHeaderNode())
            getSettings().setTextPosition(TextPosition.CENTER_HORIZONTALLY);
        else if (history.contains("sub"))
            getSettings().setTextPosition(TextPosition.SUBSCRIPT);
        else  if (history.contains("sup"))
            getSettings().setTextPosition(TextPosition.SUPERSCRIPT);
        else getSettings().setTextPosition(TextPosition.NORMAL);
    }

}
