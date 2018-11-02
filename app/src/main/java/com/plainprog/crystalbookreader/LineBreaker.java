package com.plainprog.crystalbookreader;

import android.text.TextPaint;

import java.util.ArrayList;
import de.mfietz.jhyphenator.*;

public class LineBreaker
{
    private Line currentLine;
    private ArrayList<Line> lines;
    private float currentWidth;
    public LineBreaker()
    {
    }
    /*
    * maxWidth = windowWidth - margins
    * language needed for hyphenation
    */
    public LinedBookTextItem BreakBookTextItem(BookTextItem textItem, TextPaintCollection paints, Language language, float maxWidth)
    {
        if (textItem instanceof HeaderItem){
            breakHeader((HeaderItem)textItem,paints,language,maxWidth);
            return new LinedBookTextItem(textItem, lines);
        }
        else if (textItem instanceof ParagraphItem){
            breakParagraph((ParagraphItem)textItem,paints,language,maxWidth);
            return new LinedBookTextItem(textItem,lines);
        }
        lines = new ArrayList<>();
        currentLine = new Line();
        currentWidth = 0f;
            for (int k = 0; k < textItem.getContent().size(); k++)
            {
                // foreach word
                Text word = textItem.getContent().get(k);
                float partWidth = paints.getHelper().measureText(word);
                if (currentWidth + partWidth < maxWidth)
                {
                    currentLine.add(word);
                    currentWidth += partWidth;
                }
                else
                {
                    processLastInLineWord(word,maxWidth,language,paints);
                }
                if (k == textItem.getContent().size() - 1)
                {
                    lines.add(currentLine);
                }
            }
        LinedBookTextItem result = new LinedBookTextItem(textItem, lines);
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
    private void processLastInLineWord(Text textItem, float maxWidth, Language language, TextPaintCollection paints){
        // word not fit int the line
        // break it into subWords
        ArrayList<String> subWords = Hyphenate(textItem.getValue(),language);
        for (int q = 0; q < subWords.size(); q++){
            //foreach subWord(String)
            String subWordValue = subWords.get(q);
            SubWord subWord = new SubWord(subWordValue,textItem.getSettings(),textItem);
            if (q == subWords.size()-1)
                subWord.setSpaceAfter(true);
            //getting width
            float subWordWidthWithHyphen = measureSubWord(subWord,true,paints);
            float subWordWidthWithoutHyphen = measureSubWord(subWord,false,paints);
            if(currentWidth + subWordWidthWithHyphen < maxWidth){
                // if subWords with Hyphen fits, we add it without hyphen because next on may fit line also
                // so we will add hyphen in else block (only when we will know for sure when/if it is needed)
                currentLine.add(subWord);
                currentWidth += subWordWidthWithoutHyphen;
            }
            else{
                // if subWord doesn't fit in the line
                // we check if the last element in line is subWord, and if it is, we add hyphen after it.
                // and also that means that current subWord is not the first part of the word and will placed on new line,
                // so we add hyphen before it
                if (currentLine.getContent().size()>0){
                    Text lastInLine = currentLine.getContent().get(currentLine.getContent().size()-1);
                    if (lastInLine instanceof  SubWord){
                        ((SubWord)lastInLine).setHyphenAfter(true);
                        subWord.setHyphenBefore(true);
                    }
                }
                // line is formed, add it to lines
                lines.add(currentLine);
                // extraordinary scenario: subWord is so long that it doesn't fit maxWidth.
                // In that case we just divide it on two equal-length parts until one of it fits.
                if (subWordWidthWithHyphen > maxWidth){
                    ArrayList<SubWord> parts = divideOnTwoUntilFitsWidth(subWord,maxWidth,paints);
                    for(SubWord sub : parts){
                        currentLine = new Line();
                        currentLine.add(sub);
                        lines.add(currentLine);
                    }
                }
                // creating new line and adding current subWord (if hyphenBefore was needed we set it in previous if)
                currentLine = new Line();
                currentLine.add(subWord);
                currentWidth = paints.getHelper().measureText(subWord);
            }
        }
    }
    private ArrayList<SubWord> divideOnTwoUntilFitsWidth(SubWord subWord, float maxWidth, TextPaintCollection paints){
        ArrayList<SubWord> result = new ArrayList<>();
        float subWordWidthWithHyphen = measureSubWord(subWord,true,paints);
        if (subWordWidthWithHyphen < maxWidth){
            result.add(subWord);
            return result;
        }
        else{
            int mid = subWord.getValue().length() / 2; //get the middle of the String
            String[] parts = {subWord.getValue().substring(0, mid),subWord.getValue().substring(mid)};
            SubWord part1 = new SubWord(parts[1],subWord.getSettings(),subWord.getWord());
            SubWord part2 = new SubWord(parts[2],subWord.getSettings(),subWord.getWord());
            part1.setHyphenBefore(subWord.isHyphenBefore());
            part1.setHyphenAfter(true);
            part2.setHyphenBefore(true);
            part2.setHyphenAfter(true);
            result.addAll(divideOnTwoUntilFitsWidth(part1,maxWidth,paints));
            result.addAll(divideOnTwoUntilFitsWidth(part2,maxWidth,paints));
        }
        if (result.size()>0){
            result.get(result.size()-1).setHyphenAfter(false);
            result.get(result.size()-1).setSpaceAfter(true);
        }
        return result;
    }
    private void breakParagraph(ParagraphItem paragraph, TextPaintCollection paints, Language language, float maxWidth){
        lines = new ArrayList<>();
        currentLine = new Line();
        currentWidth = 0f;
        for (int k = 0; k < paragraph.getContent().size(); k++)
        {
            // foreach word
            Text part = paragraph.getContent().get(k);
            if (k==0){
                part.getSettings().setFirstInParagraph(true);
                currentWidth += TextDrawingConstants.paragraphStartSpace;
            }
            float partWidth = paints.getHelper().measureText(part);
            if (currentWidth + partWidth < maxWidth)
            {
                currentLine.add(part);
                currentWidth += partWidth;
            }
            else
            {
                processLastInLineWord(part,maxWidth,language,paints);
            }
            if (k == paragraph.getContent().size() - 1)
            {
                for(Text text:currentLine.getContent())
                    text.getSettings().setInLastLineOfParagraph(true);
                lines.add(currentLine);
            }
        }
    }
    private void breakHeader(HeaderItem header,TextPaintCollection paints, Language language, float maxWidth){
        lines = new ArrayList<>();
        currentLine = new Line();
        currentWidth = 0;
        for (int k = 0; k < header.getContent().size(); k++)
        {
            // foreach word
            Text part = header.getContent().get(k);
            float partWidth = paints.getHelper().measureText(part);
            if (currentWidth + partWidth < maxWidth)
            {
                currentLine.add(part);
                currentWidth += partWidth;
            }
            else
            {
                if (partWidth+TextDrawingConstants.headerMargin <maxWidth){
                    lines.add(currentLine);
                    currentLine = new Line();
                    currentLine.add(part);
                    currentWidth = partWidth;
                }
                else{
                    processLastInLineWord(part,maxWidth,language,paints);
                }
            }
            if (k == header.getContent().size() - 1)
            {
                lines.add(currentLine);
            }
        }
    }
}
