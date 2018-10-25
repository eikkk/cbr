package com.plainprog.crystalbookreader;

import android.content.Context;
import android.os.Environment;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubReader;

public class ePubReader implements BookReaderBridge {
    private nl.siegmann.epublib.domain.Book tempBook;
    private File epubFile;
    @Override
    public Book read(File file, Context context) {
        ePubBook book = new ePubBook();
        initializeEpubLibBook(file);

        String title = tempBook.getTitle();
        ArrayList<nl.siegmann.epublib.domain.Author> tempAuthors = new ArrayList<>(tempBook.getMetadata().getAuthors());
        ArrayList<Author> authors = new ArrayList<>();
        for (nl.siegmann.epublib.domain.Author item: tempAuthors)
            authors.add(new Author(item.getFirstname() + item.getLastname()));


        ArrayList<Chapter> chapters;
        if (false)
            chapters = readWithSAX();
        else
            chapters = readWithDOM(file,context);

        book.setTitle(title);
        book.setAuthors(authors);
        book.setChapters(chapters);
        return book;
    }
    private void initializeEpubLibBook(File file){
        InputStream epubInputStream = null;
        try {
            epubInputStream = new FileInputStream(file);
            //epubInputStream = ReadingActivity.manager.open("eng.epub");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load Book from inputStream
        tempBook = null;
        try {
            tempBook = (new EpubReader()).readEpub(epubInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private ArrayList<Chapter> readWithDOM(File epubFile, Context context){
        ArrayList<Chapter> chapters = new ArrayList<>();
        ePubDOMReader reader = new ePubDOMReader(epubFile, context);
        for (int i = 0; i < tempBook.getSpine().size(); i++){
            Resource resource = tempBook.getSpine().getResource(i);
            String str;
            try {
                str = new String(resource.getData());
                chapters.add(reader.parse(str));
            } catch (IOException e) {
                reader.processedFinished();
                e.printStackTrace();
            }
        }
        reader.processedFinished();
        return  chapters;
    }
    private ArrayList<Chapter> readWithSAX(){
        ArrayList<Chapter> chapters = new ArrayList<>();
        for (int i = 0; i < tempBook.getSpine().size(); i++){
            Resource resource = tempBook.getSpine().getResource(i);
            //String test = tempBook.getOpfResource().getHref();
            String str;
            try {
                str = new String(resource.getData());
                    TextSAXParser parser = new TextSAXParser();
                    chapters.add(new Chapter(parser.parse(str)));
            } catch (ParserConfigurationException|SAXException|IOException e) {

            }
        }
        return  chapters;
    }
    public ePubReader(){}
}
