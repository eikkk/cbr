package com.plainprog.crystalbookreader;

import android.content.Context;

import java.io.File;

public abstract class AbstractBookReader {
    public abstract  Book read(File file, Context context);
}
