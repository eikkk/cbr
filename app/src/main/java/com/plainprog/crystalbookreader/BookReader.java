package com.plainprog.crystalbookreader;

import android.content.Context;

import java.io.File;

public class BookReader extends AbstractBookReader {
    @Override
    public Book read(File file, Context context) {
        String extension = getExtension(file.getName());
        switch (extension){
            case "epub":
                bookReader = new ePubReader();
                return bookReader.read(file, context);
            case "fb2":
                bookReader = new fb2Reader();
                return bookReader.read(file, context);
        }
        return null;
    }
    private BookReaderBridge bookReader;
    public BookReader(){}
    private String getExtension(String filepath){
        String extension = "";

        int i = filepath.lastIndexOf('.');
        if (i > 0) {
            extension = filepath.substring(i+1);
        }
        return  extension;
    }
}
