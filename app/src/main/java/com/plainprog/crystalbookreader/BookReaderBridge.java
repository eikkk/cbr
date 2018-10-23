package com.plainprog.crystalbookreader;

import android.content.Context;

import java.io.File;

public interface BookReaderBridge {
    public Book read(File file, Context context);
}
