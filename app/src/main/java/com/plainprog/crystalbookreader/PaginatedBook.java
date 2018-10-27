package com.plainprog.crystalbookreader;

public class PaginatedBook extends Book {
    private Pagination pagination;
    public PaginatedBook(Book book){
        super(book);
    }
    public PaginatedBook(Book book, Pagination pagination){
        super(book);
        this.pagination = pagination;
    }

}
