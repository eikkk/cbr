package com.plainprog.crystalbookreader;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class SAXParserEventHandler extends DefaultHandler {
    public ArrayList<Paragraph> getResult(){
        return  paragraphs;
    }
    public String getTitle(){return  title;}
    private ArrayList<Paragraph> paragraphs;
    private ArrayList<Text> currentParagraph;
    private TextXmlElementHistory nodeHistory;
    private StringBuilder currentString;
    private TextType currentTextType;
    private String title;
    private boolean processFinished;
    public SAXParserEventHandler(){
        paragraphs = new ArrayList<>();
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

        if (currentString.toString().isEmpty() || currentString.toString() == null || currentString.toString().trim().isEmpty()){
            currentString = new StringBuilder();
            return;
        }
        if (currentString.length()!= 0)
            currentParagraph.add(new Text(currentString.toString(), elementNameToTextType(localName)));

        switch (localName.toLowerCase()){
            case "p":
            case "h1":
            case "h2":
            case "h3":
            case "h4":
            case "h5":
            case "h6":
            case "li":
                if (currentString.length()!= 0)
                    currentParagraph.add(new Text(currentString.toString(), elementNameToTextType(localName)));
                currentString = new StringBuilder();
                Paragraph paragraph = new Paragraph(currentParagraph, true);
                currentParagraph = new ArrayList<>();
                paragraphs.add(paragraph);
                return;
            case "b":
            case "em":
            case "i":
            case "a":
            case "strong":
            case "sub":
            case "sup":if (currentString.length()!= 0)
                currentParagraph.add(new Text(currentString.toString(), elementNameToTextType(localName)));
                currentString = new StringBuilder();
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

    private TextType elementNameToTextType(String elementName){

        switch (elementName.toLowerCase()){
            default:
            case "p":  return TextType.NORMAL;
            case "h1": return TextType.H1;
            case "h2": return TextType.H2;
            case "h3": return TextType.H3;
            case "h4": return TextType.H4;
            case "h5": return TextType.H5;
            case "h6": return TextType.H6;
            case "b":return TextType.BOLD;
            case "em":return TextType.EMPHASIS;
            case "i":return TextType.ITALIC;
            case "a":return TextType.LINK;
            case "strong":return TextType.STRONG;
            case "sub":return TextType.SUBSCRIPT;
            case "sup":return TextType.SUPERSCRIPT;
        }
    }
    private Text setupTextAccordingToHistory(Text text, ArrayList<String> nodeHistory){

    }
}
