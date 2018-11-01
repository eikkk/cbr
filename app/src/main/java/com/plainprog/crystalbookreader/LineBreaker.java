package com.plainprog.crystalbookreader;

import java.util.ArrayList;
import de.mfietz.jhyphenator.*;

public class LineBreaker
{

    public LineBreaker()
    {
    }

    public LinedBookTextItem BreakParagraph(BookTextItem paragraph, TextPaintCollection paints, Language language, float maxWidth)
    {
        ArrayList<Line> lines = new ArrayList<>();
        Line line = new Line();
        float currentWidth = 0f;
            for (int k = 0; k < paragraph.getContent().size(); k++)
            {
                // foreach word
                Text part = paragraph.getContent().get(k);
                float partWidth = paints.getHelper().measureText(part);
                if (currentWidth + partWidth < maxWidth)
                {
                    line.add(part);
                    currentWidth += partWidth;
                }
                else
                {
                    // word not fit int the line
                    // break it into subWords
                    ArrayList<String> subWords = Hyphenate(paragraph.getContent().get(k).getValue(),language);
                    for (int q = 0; q < subWords.size(); q++){
                        //foreach subWord(String)
                        String subWordValue = subWords.get(q);
                        SubWord subWord = new SubWord(subWordValue,part.getSettings(),part);
                        //getting width
                        float subWordWidthWithHyphen = measureSubWord(subWord,true,paints);
                        float subWordWidthWithoutHyphen = measureSubWord(subWord,false,paints);
                        if(currentWidth + subWordWidthWithHyphen < maxWidth){
                            // if subWords with Hyphen fits, we add it without hyphen because next on may fit line also
                            // so we will add hyphen in else block (only when we will know for sure when/if it is needed)
                            line.add(subWord);
                            currentWidth += subWordWidthWithoutHyphen;
                        }
                         else{
                            // if subWord doesn't fit in the line
                            // we check if the last element in line is subWord, and if it is, we add hyphen after it.
                            // and also that means that current subWord is not the first part of the word and will placed on new line,
                            // so we add hyphen before it
                            if (line.getContent().size()>0){
                                 Text lastInLine = line.getContent().get(line.getContent().size()-1);
                                 if (lastInLine instanceof  SubWord){
                                     ((SubWord)lastInLine).setHyphenAfter(true);
                                     subWord.setHyphenBefore(true);
                                 }
                            }
                            // line is formed, add it to lines
                            lines.add(line);
                            // creating new line and adding current subWord (if hyphenBefore was needed we set it in previous if)
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
    private ArrayList<String> Hyphenate(String word, Language language){
        HyphenationPattern pattern = HyphenationPattern.lookup(language.getjHypenatorLookUp());
        Hyphenator hyphenator = Hyphenator.getInstance(pattern);
        ArrayList <String> result = new ArrayList<>(hyphenator.hyphenate(word.trim()));
        result.set(result.size()-1,result.get(result.size()-1) + " ");  //because we trimmed it in previous line
        return result;
    }
    private float measureSubWord(SubWord subWord, boolean withHyphen, TextPaintCollection paints){
        // save value of HyphenAfter, to set if back later
        boolean initialHyphenAfter = subWord.isHyphenAfter();
        boolean initialHyphenBefore = subWord.isHyphenBefore();
        if (withHyphen){
            subWord.setHyphenAfter(withHyphen);
            subWord.setHyphenBefore(false);   // this whole if was unnecessary, this line also, did it just to code look logical
        }
        else{
            subWord.setHyphenAfter(withHyphen);
            subWord.setHyphenBefore(withHyphen);
        }
        float result = paints.getHelper().measureText(subWord);
        // restoring original value (we should not modify anything)
        subWord.setHyphenAfter(initialHyphenAfter);
        subWord.setHyphenBefore(initialHyphenBefore);
        return result;
    }
}
