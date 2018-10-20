package com.plainprog.crystalbookreader;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReadingActivity extends AppCompatActivity {
public static AssetManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_reading);

        manager = getAssets();
        BookReader reader = new BookReader();
        Book book = reader.read("sdfsf.epub");
        TextView textView = findViewById(R.id.textView);
        StringBuilder tempContent = new StringBuilder();
        for (Chapter chapter : book.getChapters()){
            tempContent.append("-------CHAPTER------");
            for (Paragraph paragraph: chapter.getParagraphs()){
                tempContent.append("---PARAGRAPH------");
                for (Text word : paragraph.getContent())
                    tempContent.append(word.getValue());
            }
        }
        textView.setText(tempContent.toString());


    }
}
