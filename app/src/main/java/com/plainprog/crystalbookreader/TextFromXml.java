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
            setTextType(TextType.BOLD_ITALIC);
        else if (isItalic)
            setTextType(TextType.ITALIC);
        else if (isBold)
            setTextType(TextType.BOLD);
        else
            setTextType(TextType.NORMAL);
    }
    private void setupSizeAccordingToHistory(TextXmlElementHistory history){
        if (history.contains("sub") || history.contains("sup"))
            setTextSize(TextSize.SUB);
        else if (history.contains("h1"))
            setTextSize(TextSize.H1);
        else if (history.contains("h2"))
            setTextSize(TextSize.H2);
        else if (history.contains("h3"))
            setTextSize(TextSize.H3);
        else if (history.contains("h4"))
            setTextSize(TextSize.H4);
        else if (history.contains("h5"))
            setTextSize(TextSize.H5);
        else if (history.contains("h6"))
            setTextSize(TextSize.H6);
        else setTextSize(TextSize.NORMAL);

    }
    private void setupSpecificationsAccordingToHistory(TextXmlElementHistory history){
        boolean isLink = false;
        boolean isListElement = false;
        if (history.contains("li"))
            isListElement = true;
        if (history.contains("link"))
            isLink = true;
        if (isLink && isListElement)
            setTextSpecification(TextSpecification.LINK_LIST_ELEMENT);
        else if (isLink)
            setTextSpecification(TextSpecification.LINK);
        else if (isListElement)
            setTextSpecification(TextSpecification.LIST_ELEMENT);
        else setTextSpecification(TextSpecification.NONE);
    }
    private void setupPositionAccordingToHistory(TextXmlElementHistory history){
        if (history.hasAnyHeaderNode())
            setTextPosition(TextPosition.CENTER_HORIZONTALLY);
        else if (history.contains("sub"))
            setTextPosition(TextPosition.SUBSCRIPT);
        else  if (history.contains("sup"))
            setTextPosition(TextPosition.SUPERSCRIPT);
        else setTextPosition(TextPosition.NORMAL);
    }

}
