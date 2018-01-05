package com.example.android.booklistingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mohit Mehndiratta on 16-04-2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(Context context, List<Book> book){
        super(context,0,book);
    }
    @Override
    public View getView(int position, View convert, ViewGroup parent){
        View listItemView = convert;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.activity_list,parent,false);
        }
        Book book = getItem(position);
        TextView title = (TextView) listItemView.findViewById(R.id.title1);
        TextView author = (TextView) listItemView.findViewById(R.id.author);
        String title1= book.getTitle();
        String author1 = book.getAuthor();
        title.setText(title1);
        author.setText(author1);
        return listItemView;
    }
}
