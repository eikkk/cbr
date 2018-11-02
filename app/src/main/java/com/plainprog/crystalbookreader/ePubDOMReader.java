package com.plainprog.crystalbookreader;

import android.content.Context;
import android.os.Environment;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.core.ZipFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.MediaSpec;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.domassign.Analyzer;
import cz.vutbr.web.domassign.StyleMap;

public class ePubDOMReader {
    private File epubFile;
    private Context context;

    private File unzippedDirectory;
    private URL baseURL;
    private Document document;

    private BookTextItem currentBookItem;
    ArrayList<BookItem> chapterContent;
    public ePubDOMReader(File epubFile, Context context){
        this.epubFile = epubFile;
        this.context = context;
        currentBookItem = new BookTextItem();
        chapterContent = new ArrayList<>();
        unzipEPUB();
    }
    public void processedFinished(){
        deleteRecursive(unzippedDirectory);
    }
    private void unzipEPUB(){
        unzippedDirectory = context.getCacheDir();
        try {
            ZipFile zipFile = new ZipFile(epubFile.getPath());
            zipFile.extractAll(unzippedDirectory.getPath());
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }

    private void deleteRecursive(File fileOrDirectory) {

        if (fileOrDirectory.isDirectory()) {
            for (File child : fileOrDirectory.listFiles()) {
                deleteRecursive(child);
            }
        }

        fileOrDirectory.delete();
    }
    public Chapter parse(String source){
        // 2. create document
        document = createDocumentFromString(source);
        // 3. create map
        baseURL = getEpubBaseURL(unzippedDirectory);
        StyleMap map = CSSFactory.assignDOM(document, null,  baseURL, "screen", true);
        // 4. create chapter object
        createChapter(document,map);
        return new Chapter(chapterContent);
    }

    private void createChapter(Document doc, StyleMap map){
        //Chapter result = new Chapter();
        chapterContent = new ArrayList<>();
        NodeList nodes = doc.getChildNodes();
        for (int i = 0; i< nodes.getLength(); i++)
            processDOMElement(nodes.item(i));
    }
    private void processDOMElement(Node node){
        NodeList nodes;
        if (node.getNodeType() == Node.TEXT_NODE){
            if (!node.getTextContent().isEmpty() && !node.getTextContent().trim().isEmpty()){
            currentBookItem.addContent(new Text(node.getTextContent(),currentBookItem));
            chapterContent.add(currentBookItem);
            currentBookItem = new BookTextItem();
            }
            return;
        }
        /*if (node.getLocalName() == null){
            processDOMElement(node);
            return;
        }*/
        String nodeName = node.getNodeName().toLowerCase();
        switch (nodeName){
            case "p":
            case "h1":
            case "h2":
            case "h3":
            case "h4":
            case "h5":
            case "h6":
            case "a":
            case "abbr":
            case "acronym":
            case "address":
            case "article":
            case "aside":
            case "audio":
            case "b":
            case "bdo":
            case "big":
            case "blockquote":
            case "br":
            case "caption":
            case "center":
            case "cite":
            case "code":
            case "data":
            case "del":
            case "dfn":
            case "em":
            case "font":
            case "i":
            case "ins":
            case "kbd":
            case "label":
            case "link":
            case "mark":
            case "pre":
            case "q":
            case "s":
            case "small":
            case "span":
            case "strike":
            case "strong":
            case "sub":
            case "sup":
            case "tt":
            case "u":
            case "var":
                processDOMElementAsBookItem(node);
                return;
            default:
                NodeList children = node.getChildNodes();
                for (int i =0; i < children.getLength(); i ++)
                    processDOMElement(children.item(i));
                return;
        // dir, ol, ul, dr - lists
        }
    }
    private void processDOMElementAsBookItem(Node node){

        NodeList nodes;
        String nodeName = node.getNodeName().toLowerCase();
        switch (nodeName){
            case "p":
                currentBookItem = new ParagraphItem();
                nodes = node.getChildNodes();
                for (int i = 0; i< nodes.getLength(); i++)
                    processDOMElementAsPartOfBookItem(nodes.item(i));
                chapterContent.add(currentBookItem);
                currentBookItem = new BookTextItem();
                return;
            case "h1":
            case "h2":
            case "h3":
            case "h4":
            case "h5":
            case "h6":
                currentBookItem = new HeaderItem();
                nodes = node.getChildNodes();
                for (int i = 0; i< nodes.getLength(); i++)
                    processDOMElementAsPartOfBookItem(nodes.item(i));
                chapterContent.add(currentBookItem);
                currentBookItem = new BookTextItem();
                return;
            case "a":
            case "abbr":
            case "acronym":
            case "address":
            case "article":
            case "aside":
            case "audio":
            case "b":
            case "bdo":
            case "big":
            case "blockquote":
            case "br":
            case "caption":
            case "center":
            case "cite":
            case "code":
            case "data":
            case "del":
            case "dfn":
            case "em":
            case "font":
            case "i":
            case "ins":
            case "kbd":
            case "label":
            case "link":
            case "mark":
            case "pre":
            case "q":
            case "s":
            case "small":
            case "span":
            case "strike":
            case "strong":
            case "sub":
            case "sup":
            case "tt":
            case "u":
            case "var":
                currentBookItem = new BookTextItem();
                nodes = node.getChildNodes();
                for (int i = 0; i< nodes.getLength(); i++)
                    processDOMElementAsPartOfBookItem(nodes.item(i));
                chapterContent.add(currentBookItem);
                currentBookItem = new BookTextItem();
                return;
            // dir, ol, ul, dr - lists
        }
    }
    private void processDOMElementAsPartOfBookItem(Node node){

        if (node.getNodeType() == Node.TEXT_NODE){
            if (!node.getTextContent().isEmpty() && !node.getTextContent().trim().isEmpty())
                currentBookItem.addContent(new Text(node.getTextContent(),currentBookItem));
            return;
        }

        NodeList nodes;
        String nodeName = node.getNodeName().toLowerCase();
        switch (nodeName){
            case "p":
            case "h1":
            case "h2":
            case "h3":
            case "h4":
            case "h5":
            case "h6":
            case "a":
            case "abbr":
            case "acronym":
            case "address":
            case "article":
            case "aside":
            case "audio":
            case "b":
            case "bdo":
            case "big":
            case "blockquote":
            case "br":
            case "caption":
            case "center":
            case "cite":
            case "code":
            case "data":
            case "del":
            case "dfn":
            case "em":
            case "font":
            case "i":
            case "ins":
            case "kbd":
            case "label":
            case "link":
            case "mark":
            case "pre":
            case "q":
            case "s":
            case "small":
            case "span":
            case "strike":
            case "strong":
            case "sub":
            case "sup":
            case "tt":
            case "u":
            case "var":
            default:
                nodes = node.getChildNodes();
                for (int i = 0; i< nodes.getLength(); i++)
                    processDOMElementAsPartOfBookItem(nodes.item(i));
                // dir, ol, ul, dr - lists
        }
    }
    private Document createDocumentFromString(String source){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        factory.setNamespaceAware(true);
        Document document;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new InputSource(new StringReader(source)));
            return document;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private void unzip(File file ){

    }
    private File getOpfFile(File searchDirectory){

        FilenameFilter opfFilter = new FilenameFilter() {
            public boolean accept(File dir, String filename)
            { return filename.endsWith(".opf"); }
        };
        File[] opfFiles = searchDirectory.listFiles(opfFilter);
        if (opfFiles.length > 0)
            return  opfFiles[0];

        File[] files = searchDirectory.listFiles();
        for (int i =0; i < files.length; i++){
            if (files[i].isDirectory()){
                File opfFile = getOpfFile(files[i]);
                if (opfFile != null)
                    return opfFile;
            }
        }
        return null;
    }
    private URL getEpubBaseURL(File searchDirectory) {

        /*FilenameFilter opfFilter = new FilenameFilter() {
            File f;
            public boolean accept(File dir, String name) {
                if(name.endsWith(".opf")) {
                    return true;
                }
                f = new File(dir.getAbsolutePath()+"/"+name);

                return f.isDirectory();
            }
        };

        File[] files = searchDirectory.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String filename)
            { return filename.endsWith(".opf"); }
        } );*/

        File opfFile = getOpfFile(searchDirectory);
        if (opfFile != null) {
            try {
                return opfFile.getParentFile().toURI().toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }
        }
        else return null;
    }
    /*private void extractFolder(String zipFile,String extractFolder)
    {
        try
        {
            int BUFFER = 2048;
            File file = new File(zipFile);

            ZipFile zip = new ZipFile(file);
            String newPath = extractFolder;

            new File(newPath).mkdir();
            Enumeration zipFileEntries = zip.entries();

            // Process each entry
            while (zipFileEntries.hasMoreElements())
            {
                // grab a zip file entry
                ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
                String currentEntry = entry.getName();

                File destFile = new File(newPath, currentEntry);
                //destFile = new File(newPath, destFile.getName());
                File destinationParent = destFile.getParentFile();

                // create the parent directory structure if needed
                destinationParent.mkdirs();

                if (!entry.isDirectory())
                {
                    BufferedInputStream is = new BufferedInputStream(zip
                            .getInputStream(entry));
                    int currentByte;
                    // establish buffer for writing file
                    byte data[] = new byte[BUFFER];

                    // write the current file to disk
                    FileOutputStream fos = new FileOutputStream(destFile);
                    BufferedOutputStream dest = new BufferedOutputStream(fos,
                            BUFFER);

                    // read and write until last byte is encountered
                    while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
                        dest.write(data, 0, currentByte);
                    }
                    dest.flush();
                    dest.close();
                    is.close();
                }


            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }*/
}
