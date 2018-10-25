package com.plainprog.crystalbookreader;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;

public class TextPaintCollection
{
    private TextPaintHelper helper;
    private float textSize;

    public float getTextSize()
    {
        return textSize;
    }
    public TextPaintHelper getHelper() {
        return helper;
    }



    public TextPaintCollection(float textSize) {
        this.textSize = textSize;
        helper = new TextPaintHelper(this);
    }

    public Paint getPaintForText(Text text)
    {
        //todo: check what a correct sizes for all headers
        //todo: and maybe fonts should be handled here with setTypeFace
        Paint paint = new Paint();
        paint.setTextSize(textSize);
        paint.setAntiAlias(true);
        switch (text.getSettings().getTextType()) {
            default:
            case NORMAL:
                paint.setTypeface(Typeface.DEFAULT);
                break;
            case ITALIC:
                paint.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                break;
            case BOLD:
                paint.setTypeface(Typeface.DEFAULT_BOLD);
                break;
            case BOLD_ITALIC:
                paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
                break;
        }
        switch (text.getSettings().getTextSize()) {
            case H1: {
                paint.setTextSize(textSize * 2);
                break;
            }
            case H2: {
                paint.setTextSize(textSize * 1.7f);
                break;
            }
            case H3: {
                paint.setTextSize(textSize * 1.5f);
                break;
            }
            case H4: {
                paint.setTextSize(textSize * 1.4f);
                break;
            }
            case H5: {
                paint.setTextSize(textSize * 1.3f);
                break;
            }
            case H6: {
                paint.setTextSize(textSize * 1.2f);
                break;
            }
            case SUB:{
                paint.setTextSize(textSize* 0.3f);
                break;
            }
        }
        return paint;

    }
}
