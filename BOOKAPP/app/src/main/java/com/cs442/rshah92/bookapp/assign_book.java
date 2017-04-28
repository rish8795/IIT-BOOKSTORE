package com.cs442.rshah92.bookapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cs442.rshah92.bookapp.Database.BookDetails;
import com.cs442.rshah92.bookapp.Database.SessionManager;
import com.cs442.rshah92.bookapp.Database.Waitlist;

public class assign_book extends AppCompatActivity {

    Waitlist waitlist;
    Bundle extra;
    Button add;
    SessionManager session;

    EditText Ttitle, Tauthor, Tisbn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_assign_book);

        add = (Button) findViewById(R.id.assignBook);

        waitlist = new Waitlist(this);
        openDB();
        extra = getIntent().getExtras();

       final String name = extra.getString("name");
       String  user = extra.getString("user");
       String  time = extra.getString("time");
       String status = extra.getString("status");

    }

    private void openDB(){
        waitlist.open();
    }


}
