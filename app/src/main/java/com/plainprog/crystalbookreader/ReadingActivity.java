package com.plainprog.crystalbookreader;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Point;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class ReadingActivity extends AppCompatActivity {
    private File file;
    private Book book;
    ViewPager viewPager;
    private boolean hasPermission = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_reading);
        viewPager = findViewById(R.id.viewPager);

        checkPermissions();
        if (hasPermission){
            file = getFileForTest();
            readTestFile();
            setupViewPager(viewPager);
        }
        else Toast.makeText(this, "No permission for reading", Toast.LENGTH_SHORT).show();
    }
    private void setupViewPager(ViewPager viewPager){

        int textSize = 40;
        Paddings paddings = new Paddings(10,10,10,10);
        Pagination pagination = new Pagination(book,getDisplayDimensions(),new TextPaintCollection(textSize),paddings);
        ViewPagerBookAdapter adapter = new ViewPagerBookAdapter(this, pagination);
        viewPager.setAdapter(adapter);
    }
    private Point getDisplayDimensions(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }
    private void checkPermissions(){

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);

        }
        else{
            hasPermission = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ReadingActivity.this.recreate();
                }else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
    }

    private File getFileForTest(){

        File sdcard = Environment.getExternalStorageDirectory();

//Get the text file
        File file = new File(sdcard,"eng.epub");
        String path = file.getPath();
        return  file;
    }
    private  void readTestFile(){
        BookReader reader = new BookReader();
        book = reader.read(file, this);
    }
    private void showBookInTextView(TextView textView, Book book){
        StringBuilder tempContent = new StringBuilder();
        for (Chapter chapter : book.getChapters()){
            tempContent.append("-------CHAPTER------");
            for (BookItem bookItem: chapter.getBookItems()){
                tempContent.append("---PARAGRAPH------");
                if (bookItem instanceof BookTextItem)
                    for (Text word : ((BookTextItem)bookItem).getContent())
                        tempContent.append(word.getValue());
            }
        }
        textView.setText(tempContent.toString());
    }
}
