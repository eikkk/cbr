package com.plainprog.crystalbookreader;

import java.util.ArrayList;

public class Paginator {

    private final float maxHeight;
    private final float maxWidth;

    private ArrayList<PaginatedChapter> paginatedChapters;

    private TextPaintCollection paints;
    private ArrayList<Page> currentChapterPages;
    private Page currentPage;
    private float currentHeight;

    public Paginator(TextPaintCollection paints, float maxHeight, float maxWidth){
        this.paints = paints;
        this.maxHeight = maxWidth;
        this.maxWidth = maxWidth;
        currentHeight = 0f;
        currentPage = new Page();
        paginatedChapters = new ArrayList<>();
    }
    public ArrayList<PaginatedChapter> Paginate(Book book){
        for (Chapter chapter : book.getChapters())
            paginatedChapters.add(PaginateChapter(chapter));
        return paginatedChapters;
    }
    private PaginatedChapter PaginateChapter(Chapter chapter){
        currentChapterPages = new ArrayList<>();
        for(BookItem item : chapter.getBookItems()){
            if (item instanceof BookTextItem){
                LineBreaker lineBreaker = new LineBreaker();
                LinedBookTextItem linedItem = lineBreaker.BreakParagraph((BookTextItem) item,paints,maxWidth);
                placeBookItemOnPages(linedItem);
            }
            else if (item instanceof BookMediaItem){

            }
        }
        return new PaginatedChapter(chapter, currentChapterPages);
    }
    private void placeBookItemOnPages(BookItem item){
        if (item instanceof LinedBookTextItem){
            LinedBookTextItem linedItem = (LinedBookTextItem)item;
            for (Line line: linedItem.getLines()){
                float lineSpacing = paints.getHelper().getLineSpacing(line);
                float lineHeight = paints.getHelper().measureLineHeight(line);
                if (currentHeight + lineHeight <= maxHeight){
                    currentPage.addLine(line);
                    currentHeight += lineHeight+ lineSpacing;
                }
                else{
                    currentChapterPages.add(currentPage);
                    currentPage = new Page();
                    currentPage.addLine(line);
                    currentHeight = lineHeight + lineSpacing;
                }
            }
        }
        else if (item instanceof BookMediaItem){

        }
    }

}
