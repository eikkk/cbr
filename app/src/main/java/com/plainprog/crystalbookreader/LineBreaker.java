package com.plainprog.crystalbookreader;

import java.util.ArrayList;

public class LineBreaker
{

    public LineBreaker()
    {
    }

    public LinedBookTextItem BreakParagraph(BookTextItem paragraph, TextPaintCollection paints, float maxWidth)
    {
        ArrayList<Line> lines = new ArrayList<>();
        Line line = new Line();
        float currentWidth = 0f;
            for (int k = 0; k < paragraph.getContent().size(); k++)
            {
                int i = 0;
                float partWidth = paints.getHelper().measureText(paragraph.getContent().get(k));
                if (currentWidth + partWidth < maxWidth)
                {
                    line.add(paragraph.getContent().get(k));
                    currentWidth += partWidth;
                    if (k == paragraph.getContent().size() - 1)
                    {
                        lines.add(line);
                    }
                }
                else
                {
                    lines.add(line);
                    line = new Line();
                    currentWidth = 0;
                    line.add(paragraph.getContent().get(k));
                    currentWidth += partWidth;
                    if (k == paragraph.getContent().size() - 1)
                    {
                        lines.add(line);
                    }
                }
            }
        LinedBookTextItem result = new LinedBookTextItem(paragraph, lines);
        return result;
    }
}
