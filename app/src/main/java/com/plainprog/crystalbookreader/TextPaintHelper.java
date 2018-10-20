package com.plainprog.crystalbookreader;

import android.graphics.Paint;

public class TextPaintHelper {
    TextPaintCollection paints;
    public TextPaintHelper(TextPaintCollection paints) {
        this.paints = paints;
    }

    public float measureLineHeight(Line line){
        if (line == null || line.getContent().size() == 0)
            return 0;
        Paint paint = paints.getPaintForText(line.getContent().get(0));
        return (paint.getFontMetrics().top + paint.getFontMetrics().bottom) * (-1);
    }
    public float measureLine(Line line)
    {
        float result = 0f;
        for(Text content : line.getContent())
            result += measureText(content);
        return result;
    }
    public float measureText(Text text)
    {
        Paint paint = paints.getPaintForText(text);
        return paint.measureText(text.getValue());
    }
    public float getLineSpacing(Line line)
    {
        if (line == null || line.getContent().size() == 0)
            return 0f;
        Paint paint = paints.getPaintForText(line.getContent().get(0));
        return paint.getFontSpacing();
    }
}
