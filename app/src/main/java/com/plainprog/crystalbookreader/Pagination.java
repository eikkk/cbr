package com.plainprog.crystalbookreader;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class Pagination {
    private ArrayList<PaginatedChapter> paginatedChapters;
    private ArrayList<Page> pages;
    private Point displayDimensions;
    private Paddings paddings;
    private TextPaintCollection paints;

    public Point getDisplayDimensions() {
        return displayDimensions;
    }
    public Paddings getPaddings() {
        return paddings;
    }
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

    public Pagination(Book book, Point displayDimensions , TextPaintCollection paints, Paddings paddings)
    {
        this.paints = paints;
        this.displayDimensions = displayDimensions;
        this.paddings = paddings;
        float maxHeight = displayDimensions.y - paddings.getPaddingTop() - paddings.getPaddingBottom();
        float maxWidth = displayDimensions.x - paddings.getPaddingRight() - paddings.getPaddingLeft();
        Paginator paginator = new Paginator(paints,maxHeight,maxWidth);
        this.paginatedChapters = paginator.Paginate(book);
        this.pages = paginatedChaptersToListOfPages(paginatedChapters);
    }

    private ArrayList<Page> paginatedChaptersToListOfPages(ArrayList<PaginatedChapter> paginatedChapters){
        ArrayList<Page> pages = new ArrayList<>();
        for (PaginatedChapter chapter: paginatedChapters)
            for (Page page: chapter.getPages())
                pages.add(page);
        return pages;
    }
}
