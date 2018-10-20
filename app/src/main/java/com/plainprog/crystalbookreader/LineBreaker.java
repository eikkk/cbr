package com.plainprog.crystalbookreader;

import android.graphics.Paint;

import java.util.ArrayList;

public class LineBreaker
{

    public LineBreaker()
    {
    }
    public ArrayList<LinedChapter> BreakChapters(ArrayList<Chapter> chapters, TextPaintCollection paints, float maxWidth)
    {
        ArrayList<LinedChapter> result = new ArrayList<>();
        for (int i = 0; i < chapters.size(); i++)
        {
            LinedChapter linedChapter = BreakChapter(chapters.get(i), paints, maxWidth);
            result.add(linedChapter);
        }
        return result;
    }
    private LinedChapter BreakChapter(Chapter chapter, TextPaintCollection paints, float maxWidth)
    {
        //char[] separators = new char[] {' '};
        ArrayList<LinedParagraph> linedParagraphs = new ArrayList<>();
        for(Paragraph paragraph : chapter.getParagraphs()) {
            LinedParagraph linedParagraph = BreakParagraph(paragraph, paints, maxWidth);
            linedParagraphs.add(linedParagraph);
        }
        LinedChapter linedChapter = new LinedChapter(chapter, linedParagraphs);
        return linedChapter;
    }
    private LinedParagraph BreakParagraph(Paragraph paragraph, TextPaintCollection paints, float maxWidth)
    {
        ArrayList<Line> lines = new ArrayList<>();
        //float lineheight = MeasureHeight(new Text("Some Text", TextType.NORMAL), paints);
        //float linespacing = GetLineSpacing(new Text("Some Text", TextType.NORMAL), paints);
        Line line = new Line();
        float currentWidth = 0f;

            for (int k = 0; k < paragraph.getContent().size(); k++)
            {
                //darchSentence ds = paragraph.darchSentences[k];
                //ArrayList<Word> parts = GetSentenceParts(ds.Sentence1, separators);

                int i = 0;
                //lineheight = MeasureHeight(new Text("Some Text", paragraph.getContent().get(0).getTextType()), paints);
                //linespacing = GetLineSpacing(new Text("Some Text", paragraph.getContent().get(0).getTextType()), paints);
                //line.LineHeight = lineheight;
                //ine.LineSpacing = linespacing;

                //Subline subline = new Subline(new List<Text>());
                //subline.darchSentence = ds;


                float partWidth = paints.getHelper().measureText(paragraph.getContent().get(k));
                //parts[k].Width = partWidth;
                if (currentWidth + partWidth < maxWidth)
                {
                    //subline.Content.Add(parts[k]);
                    line.add(paragraph.getContent().get(k));
                    currentWidth += partWidth;
                    if (k == paragraph.getContent().size() - 1)
                    {
                        //line.Sublines.Add(subline);
                        lines.add(line);
                        //if (z == paragraph.darchSentences.Count - 1) // if it's also last sentence
                        //   lines.Add(line);
                    }
                }
                else
                {
                    lines.add(line);
                    //line = FillLineWithSpaces(line, maxWidth, paints);
                    //line.Sublines.Add(subline);
                    //lines.Add(line);
                    line = new Line();
                    //subline = new Subline(new List<Text>());
                    //subline.darchSentence = ds;
                    currentWidth = 0;
                    line.add(paragraph.getContent().get(k));
                    //subline.Content.Add(parts[k]);    // add unhandled word to new line
                    currentWidth += partWidth;
                    if (k == paragraph.getContent().size() - 1)
                    {
                        lines.add(line);
                    }
                }
            }
        LinedParagraph result = new LinedParagraph(paragraph, lines);
        //result.LineSpacing = linespacing;
        return result;
    }
}
