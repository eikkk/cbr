package com.plainprog.crystalbookreader;

import android.content.Context;
import android.os.Environment;

import org.w3c.dom.Document;
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
    private Context context;
    private File unzippedDirectory;
    private URL baseURL;
    private Document document;
    public ePubDOMReader(File file, String source, URL url, Context context){
        // 1. unpack archive
        this.context = context;
        unzip(file);
        // 2. create document
        document = createDocumentFromString(source);
        // 3. create map
        baseURL = getEpubBaseURL(unzippedDirectory);
        StyleMap map = CSSFactory.assignDOM(document, null,  baseURL, "screen", true);
        // 4. create book object
        // 5. serialize and save created object to file
        // 6. delete unpacked




        //get the element style
        try {
            //String pathToEpub = "jar:" + new File(Environment.getExternalStorageDirectory(), "eng.epub").toURI().toURL().getPath()+ "!/OPS";
            //URL url1 = new URL(pathToEpub);
            URL url1 = new File(Environment.getExternalStorageDirectory(), "epubContent/OPS/css/main.css").toURI().toURL();
            //URL url1 = new URL("file:///android_asset/main.css");
            //File css = ReadingActivity.manager.open("main.css");
            //MediaSpec spec = new MediaSpec("screen");
            StyleSheet styleSheet = CSSFactory.parse(url,null);
            //Analyzer analyzer = new Analyzer(styleSheet);
            //StyleMap map = analyzer.evaluateDOM(document,"screen", true);
            //StyleMap map = CSSFactory.assignDOM(document, null,  url1, "screen", true);
            int i = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i = 0;
        //NodeData style = map.get();
        //get the type of the assigned value
        //CSSProperty.Margin mm = style.getProperty("margin-top");
        //System.out.println("margin-top=" + mm);
        //if a length is specified, obtain the exact value
        //if (mm == Margin.length)
        //{
        //   TermLength mtop = style.getValue(TermLength.class, "margin-top");
        //   System.out.println("value=" + mtop);
        //}
    }
    private Document createDocumentFromString(String source){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
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

        unzippedDirectory = context.getCacheDir();
        try {
            ZipFile zipFile = new ZipFile(file.getPath());
            zipFile.extractAll(unzippedDirectory.getPath());
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }
    private URL getEpubBaseURL(File searchDerictory) {

        FilenameFilter opfFilter = new FilenameFilter() {
            File f;
            public boolean accept(File dir, String name) {
                if(name.endsWith(".opf")) {
                    return true;
                }
                f = new File(dir.getAbsolutePath()+"/"+name);

                return f.isDirectory();
            }
        };
        String files[] = searchDerictory.list(opfFilter);
        if (files.length == 0)
            return null;
        else{
            File opfFile = new File(files[0]);
            File baseFolder = new File(opfFile.getParent());
            if (baseFolder.exists() && baseFolder.isDirectory()) {
                try {
                    return baseFolder.toURI().toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            else return  null;
        }

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
