package com.plainprog.crystalbookreader;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import javax.xml.parsers.*;
import org.xml.sax.*;

public class TextSAXParser{
    public TextSAXParser() {
    }
    public ArrayList<BookItem> parse(String content) throws ParserConfigurationException, SAXException, IOException {
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
