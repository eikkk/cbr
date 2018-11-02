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
        return paint.getFontMetrics().bottom - paint.getFontMetrics().top + paint.getFontMetrics().leading;
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
        String textValue = new String(text.getValue());
        if (text instanceof SubWord && ((SubWord)text).isWithHyphen())
            textValue= textValue + "-";
        return paint.measureText(textValue);
    }
    public float getSpaceWidth(Text text){
        Paint paint = paints.getPaintForText(text);
        return paint.measureText(" ");
    }
    public float getLineSpacing(Line line)
    {
        //if (line == null || line.getContent().size() == 0)
            return 0f;
        //Paint paint = paints.getPaintForText(line.getContent().get(0));
        //return paint.getFontSpacing();
    }
}
