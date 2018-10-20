package com.plainprog.crystalbookreader;

import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubReader;

public class ePubReader implements BookReaderBridge {
    @Override
    public Book read(String filepath) {
        ePubBook book = new ePubBook();
        // find InputStream for book
        InputStream epubInputStream = null;
        try {
            epubInputStream = ReadingActivity.manager
                    .open("eng.epub");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load Book from inputStream
        nl.siegmann.epublib.domain.Book tempBook = null;
        try {
            tempBook = (new EpubReader()).readEpub(epubInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String title = tempBook.getTitle();
        ArrayList<nl.siegmann.epublib.domain.Author> tempAuthors = new ArrayList<>(tempBook.getMetadata().getAuthors());
        ArrayList<Author> authors = new ArrayList<>();
        for (nl.siegmann.epublib.domain.Author item: tempAuthors)
            authors.add(new Author(item.getFirstname() + item.getLastname()));


        ArrayList<Chapter> chapters = new ArrayList<>();
        for (int i = 0; i < tempBook.getSpine().size(); i++){
            Resource resource = tempBook.getSpine().getResource(i);
            String str;
            try {
              str = new String(resource.getData());
              TextSAXParser parser = new TextSAXParser();
              chapters.add(new Chapter(parser.parse(str)));
            } catch (ParserConfigurationException|SAXException|IOException e) {
                
            }
        }
        book.setTitle(title);
        book.setAuthors(authors);
        book.setChapters(chapters);
        return book;
    }
    public ePubReader(){}
}
