package com.plainprog.crystalbookreader;

import java.util.ArrayList;
import de.mfietz.jhyphenator.*;

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
                Text part = paragraph.getContent().get(k);
                int i = 0;
                float partWidth = paints.getHelper().measureText(part);
                if (currentWidth + partWidth < maxWidth)
                {
                    line.add(part);
                    currentWidth += partWidth;
                }
                else
                {
                    HyphenationPattern pattern = HyphenationPattern.lookup("en_us");
                    Hyphenator hyphenator = Hyphenator.getInstance(pattern);
                    ArrayList<String> subWords = new ArrayList<>(hyphenator.hyphenate(paragraph.getContent().get(k).getValue()));
                    //StringBuilder beforeHyphenPart = new StringBuilder();
                    //StringBuilder afterHyphenPart = new StringBuilder();
                    // List<Text> hyphenatedWord = ...
                    for (int q = 0; q < subWords.size(); q++){
                        String subWordValue = subWords.get(q);
                        SubWord subWord = new SubWord(subWordValue,part.getSettings(),part);
                        subWord.setHyphenAfter(true);
                        float subWordWidthWithHyphen = paints.getHelper().measureText(subWord);
                        subWord.setHyphenBefore(false);
                        subWord.setHyphenAfter(false);
                        float subWordWidthWithoutHyphen = paints.getHelper().measureText(subWord);
                        if(currentWidth + subWordWidthWithHyphen < maxWidth){
                            line.add(subWord);
                            //beforeHyphenPart.append(subWordValue);
                            currentWidth += subWordWidthWithoutHyphen;
                        }
                         else{
                            //SubWord beforeHyphen = new SubWord(beforeHyphenPart.toString(), part.getSettings(),part);
                            //beforeHyphen.setHyphenAfter(true);
                            //line.add(beforeHyphen);
                            if (line.getContent().size()>0){
                                 Text lastInLine = line.getContent().get(line.getContent().size()-1);
                                 if (lastInLine instanceof  SubWord){
                                     ((SubWord)lastInLine).setHyphenAfter(true);
                                     subWord.setHyphenBefore(true);
                                 }
                            }
                            lines.add(line);
                            line = new Line();
                            line.add(subWord);
                            currentWidth = paints.getHelper().measureText(subWord);
                         }
                    }
                }

                if (k == paragraph.getContent().size() - 1)
                {
                    lines.add(line);
                }
            }
        LinedBookTextItem result = new LinedBookTextItem(paragraph, lines);
        return result;
    }
}
