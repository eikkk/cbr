package com.plainprog.crystalbookreader;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class TextSAXParser{
    public TextSAXParser() {
    }
    public ArrayList<Paragraph> parse(String content) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        SAXParser saxParser = spf.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();
        SAXParserEventHandler handler = new SAXParserEventHandler();
        xmlReader.setContentHandler(handler);
        xmlReader.parse(new InputSource(new StringReader(content)));
        return handler.getResult();
    }
}
