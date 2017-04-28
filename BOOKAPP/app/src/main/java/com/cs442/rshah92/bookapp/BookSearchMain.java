package com.cs442.rshah92.bookapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class BookSearchMain extends AppCompatActivity {


    Button Bnamesearch;
    Button Anamesearch;
    Button isbnsearch;
    Button viewatz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_book_search_main);

        Bnamesearch = (Button) findViewById(R.id.Bnamesearch);

        Anamesearch = (Button) findViewById(R.id.Anamesearch);

        isbnsearch = (Button) findViewById(R.id.isbsearch);

        viewatz = (Button) findViewById(R.id.atzsearch);

        booksearch();

        authorsearch();

        isbsearch();

        atozsearch();

    }


    public void booksearch(){
        Bnamesearch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(BookSearchMain.this, BookSearchActivity.class);
                        startActivity(myIntent);
                    }
                }
        );
    }


    public void authorsearch() {
        Anamesearch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(BookSearchMain.this, AuthorSearch.class);
                        startActivity(myIntent);
                    }
                }
        );
    }


    public void isbsearch() {
        isbnsearch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(BookSearchMain.this, ISBNSearchActivity.class);
                        startActivity(myIntent);
                    }
                }
        );
    }

    public void atozsearch() {
        viewatz.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(BookSearchMain.this, ViewBooks.class);
                        startActivity(myIntent);
                    }
                }
        );
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();

    }


}
