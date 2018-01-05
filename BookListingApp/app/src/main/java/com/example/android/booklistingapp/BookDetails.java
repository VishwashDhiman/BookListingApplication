package com.example.android.booklistingapp;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohit Mehndiratta on 16-04-2017.
 */
public class BookDetails extends AppCompatActivity implements LoaderCallbacks<List<Book>> {
    private TextView mEmptyStateTextView;
    private String REQUESED_URL = "";
    private String titles;
    private static final int Book_LOADER_ID = 1;
    private String authors;
    private int i=0;
    private BookAdapter adapter;
    private int minNumberOfResources = 0;
    private static final int maxNumberOfResources = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listing);
        Intent intent = getIntent();
        titles= intent.getStringExtra("title");
        authors= intent.getStringExtra("author");
        if (!titles.equals("")) {
            titles = "+intitle:" + titles;
        }
        else {
            titles ="";
        }
        if(!authors.equals("")){
            authors = "+inauthor:" + authors;
        }
        else{
            authors="";
        }
        REQUESED_URL ="https://www.googleapis.com/books/v1/volumes?q="+titles+authors+"&startIndex="+ String.valueOf(i)+"&maxResults="+String.valueOf(maxNumberOfResources);
        ListView bookListView = (ListView) findViewById(R.id.list);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        bookListView.setEmptyView(mEmptyStateTextView);
        adapter = new BookAdapter(this , new ArrayList<Book>());
        bookListView.setAdapter(adapter);
        ConnectivityManager connMgr = (ConnectivityManager)
                       getSystemService(Context.CONNECTIVITY_SERVICE);

               // Get details on the currently active default data network
               NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

               // If there is a network connection, fetch data
                                if (networkInfo != null && networkInfo.isConnected()) {
                        // Get a reference to the LoaderManager, in order to interact with loaders.
                        LoaderManager loaderManager = getLoaderManager();

                        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
                        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
                        // because this activity implements the LoaderCallbacks interface).
                        loaderManager.initLoader(Book_LOADER_ID, null, this);
                    } else {
                        // Otherwise, display error
                        // First, hide loading indicator so error message will be visible
                        View loadingIndicator = findViewById(R.id.loading_indicator);
                        loadingIndicator.setVisibility(View.GONE);

                        // Update empty state with no connection error message
                        mEmptyStateTextView.setText(R.string.no_internet_connection);
                    }


    }



    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(this, REQUESED_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        mEmptyStateTextView.setText(R.string.no_books);

        adapter.clear();

        if ( data!= null && !data.isEmpty()) {
            adapter.addAll(data);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        adapter.clear();
    }

}
