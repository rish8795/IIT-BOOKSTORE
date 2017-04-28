package com.cs442.rshah92.bookapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.cs442.rshah92.bookapp.Database.BookDetails;
import com.cs442.rshah92.bookapp.Database.Waitlist;
import com.cs442.rshah92.bookapp.Database.Utils;


import java.io.InputStream;

/**
 * Created by Yani on 12/4/2016.
 */

public class BookPage extends Activity {

    BookDetails bookDetails;
    Bundle extra;
    Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.bookpage);

        add = (Button) findViewById(R.id.buttonAdd);

        TextView Tname = (TextView) findViewById(R.id.bookInfoName);
        TextView Tauthor = (TextView) findViewById(R.id.author);
        TextView Tisbn = (TextView) findViewById(R.id.isbn);
        TextView Tid = (TextView) findViewById(R.id.id);






        bookDetails = new BookDetails(this);
        openDB();

        extra = getIntent().getExtras();

        String idb = extra.getString("id");
        final String name = extra.getString("name");
        String author = extra.getString("author");
        String isbn = extra.getString("ISBN");
        String quantity = extra.getString("quantity");
        String shelf = extra.getString("shelf");
        String time = "2016-12-10 09:02:15";


        Cursor cursor = bookDetails.getIDData(idb);

        startManagingCursor(cursor);


        String[] fromFieldNames = new String[]
                {BookDetails.BOOKNAME};

        Tname.setText(name);
        Tauthor.setText(author);
        Tisbn.setText(isbn);
        Tid.setText(idb);


        int[] toViewIDs = new int[]
                {R.id.bookInfoName};


        SimpleCursorAdapter myCursorAdapter =
                new SimpleCursorAdapter(
                        this,
                        R.layout.bookpage,
                        cursor,
                        fromFieldNames,
                        toViewIDs
                );


        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addbook();
                        Toast.makeText(getApplicationContext(), "added " + name, Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }

    public void addbook()
    {
        final ProgressDialog progressdialog = new ProgressDialog(this,R.style.AppTheme);
        progressdialog.setIndeterminate(true);
        progressdialog.setMessage("Creating Account");
        progressdialog.show();

        String idb = extra.getString("id");
        int id = Integer.parseInt(idb);
        String time = "2016-12-04 02:42:15";

        Waitlist wait = new Waitlist(getApplicationContext());
        wait.open();
        wait.insertData(1, id, time, "Waiting");
        progressdialog.dismiss();
    }


    private void openDB(){
        bookDetails.open();
    }

    private void closeDB(){
        bookDetails.close();
    }
}
