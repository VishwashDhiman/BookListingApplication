package com.example.android.booklistingapp;

/**
 * Created by Mohit Mehndiratta on 16-04-2017.
 */

public class Book {
    private String titleBook;
    private String authorBook;
    public Book(String title, String author){
        titleBook=title;
        authorBook=author;
    }
    public String getTitle(){
        return  titleBook;
    }
    public String getAuthor(){
        return authorBook;
    }
}
