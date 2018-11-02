package com.plainprog.crystalbookreader;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class SAXParserEventHandler extends DefaultHandler {
    public ArrayList<BookItem> getResult(){
        return bookItems;
    }
    public String getTitle(){return  title;}
    private ArrayList<BookItem> bookItems;
    private ArrayList<Text> currentParagraph;
    private TextXmlElementHistory nodeHistory;
    private StringBuilder currentString;
    private TextType currentTextType;
    private String title;
    private boolean processFinished;
    public SAXParserEventHandler(){
        bookItems = new ArrayList<>();
        currentParagraph = new ArrayList<>();
        nodeHistory = new TextXmlElementHistory();
        currentString = new StringBuilder();
        processFinished = false;
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (currentString.length() != 0){
            TextFromXml text = new TextFromXml(currentString.toString());
            text.setupAccordingToHistory(nodeHistory);
            currentParagraph.add(text);
        }
        nodeHistory.addToHistory(localName);
        currentString = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (currentString.toString().isEmpty() ||  currentString.toString().trim().isEmpty()){
            currentString = new StringBuilder();
            return;
        }
        switch (localName.toLowerCase()){
            case "p":
            case "h1":
            case "h2":
            case "h3":
            case "h4":
            case "h5":
            case "h6":
            case "li":
                if (currentString.length()!= 0){
                    TextFromXml text = new TextFromXml(currentString.toString());
                    text.setupAccordingToHistory(nodeHistory);
                    //currentParagraph.add(new Text(currentString.toString()));
                }
                currentString = new StringBuilder();
                BookTextItem paragraph = new BookTextItem(currentParagraph, true);
                currentParagraph = new ArrayList<>();
                bookItems.add(paragraph);
                nodeHistory.removeFromHistory(localName);
                return;
            /*case "b":
            case "em":
            case "i":
            case "a":
            case "strong":
            case "sub":
            case "sup":*/
            default:
                if (currentString.length()!= 0){
                    TextFromXml text = new TextFromXml(currentString.toString());
                    text.setupAccordingToHistory(nodeHistory);
                    currentParagraph.add(text);
            }
                currentString = new StringBuilder();
                nodeHistory.removeFromHistory(localName);
                return;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentString.append(ch,start,length);
        //Todo: handle all that CDATA stuff in characters()
    }

    @Override
    public void endDocument() throws SAXException {
        processFinished = true;
        super.endDocument();
    }
}
