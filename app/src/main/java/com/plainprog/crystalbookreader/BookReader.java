package com.plainprog.crystalbookreader;

public class BookReader extends AbstractBookReader {
    @Override
    public Book read(String filepath) {
        String extension = getExtension(filepath);
        switch (extension){
            case "epub":
                bookReader = new ePubReader();
                return bookReader.read(filepath);
            case "fb2":
                bookReader = new fb2Reader();
                return bookReader.read(filepath);
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
