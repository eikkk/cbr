package com.plainprog.crystalbookreader;

import java.util.ArrayList;
import java.util.List;

public class Pagination {
    ArrayList<PaginatedChapter> paginatedChapters;
    ArrayList<Page> pages;
    float screenHeight;
    float screenWidth;
    float paddingLeft;

    public float getScreenHeight() {
        return screenHeight;
    }

    public float getScreenWidth() {
        return screenWidth;
    }

    public float getPaddingLeft() {
        return paddingLeft;
    }

    public float getPaddingRight() {
        return paddingRight;
    }

    public float getPaddingTop() {
        return paddingTop;
    }

    public float getPaddingBottom() {
        return paddingBottom;
    }

    float paddingRight;
    float paddingTop;
    float paddingBottom;
    TextPaintCollection paints;
    int count = 0;
    public TextPaintCollection getPaints()
    {
        return paints;
    }
    public ArrayList<Page> getPages()
    {
        return pages;
    }
    public ArrayList<PaginatedChapter> getPaginatedChapters()
    {
        return paginatedChapters;
    }
    public int getCount()
    {
            return pages.size();
    }
    public Pagination(ArrayList<Chapter> chapters, float screenHeight, float screenWidth, TextPaintCollection paints, float paddingLeft, float paddingRight, float paddingTop, float paddingBottom)
    {
        this.paints = paints;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.paddingBottom = paddingBottom;
        this.paddingLeft = paddingLeft;
        this.paddingRight = paddingRight;
        this.paddingTop = paddingTop;

        paginatedChapters = Paginate(chapters, screenHeight - paddingBottom - paddingTop, screenWidth -paddingLeft - paddingRight, paints);


        pages = new ArrayList<>();
        for (PaginatedChapter chapter: paginatedChapters)
            for (Page page: chapter.getPages())
                pages.add(page);
    }
    private ArrayList<PaginatedChapter> Paginate(ArrayList<Chapter> chapters, float maxHeight, float maxWidth, TextPaintCollection paints)
    {
        LineBreaker lineBreaker = new LineBreaker();
        ArrayList<LinedChapter> linedChapters = lineBreaker.BreakChapters(chapters, paints, maxWidth);
        ArrayList<PaginatedChapter> paginatedChapters = new ArrayList<>();
        for(LinedChapter linedChapter : linedChapters)
        {
            ArrayList<Page> pages = new ArrayList<>();
            Page page = new Page();
            float currentHeight = 0f;
            for (int i = 0;i < linedChapter.getLinedParagraphs().size(); i++)
            {
                for (int q = 0; q< linedChapter.getLinedParagraphs().get(i).getLines().size(); q++)
                {
                    Line line = linedChapter.getLinedParagraphs().get(i).getLines().get(q);
                    float lineHeight = paints.getHelper().measureLineHeight(line);
                    float lineSpacing = paints.getHelper().getLineSpacing(line);
                    if (currentHeight + lineHeight + lineSpacing < maxHeight)
                    {
                        if (page.getLines().size() == 0)
                            currentHeight += lineHeight;
                        else
                            currentHeight += lineHeight+ lineSpacing;
                        page.addLine(line);
                    }
                    else
                    {
                        pages.add(page);
                        page = new Page();
                        page.addLine(line);
                        currentHeight = lineHeight;
                    }
                    /*
                    //set last and first in p markers
                    if (q == linedChapter.LinedParagraphs[i].Lines.Count-1)
                    {
                        linedChapter.LinedParagraphs[i].Lines[q].IsLastInParagraph = true;
                    }
                    if (q == 0)   // if it's first line in chapter
                    {
                        linedChapter.LinedParagraphs[i].Lines[q].IsFirstInParagraph = true;
                        if (page.Lines.Count != 1)    // not first line in page
                            currentHeight += linedChapter.LinedParagraphs[i].Lines[q].LineSpacing;
                    }*/
                }
                // last paragraph was handled and we break the page
                if (i == linedChapter.getLinedParagraphs().size()-1)
                {
                    pages.add(page);
                    page = new Page();
                    currentHeight = 0f;
                }
            }
            PaginatedChapter paginatedChapter = new PaginatedChapter(linedChapter, pages);
            paginatedChapters.add(paginatedChapter);
        }
        return paginatedChapters;
    }
}
