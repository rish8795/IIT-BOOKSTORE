package com.cs442.rshah92.bookapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cs442.rshah92.bookapp.Database.SessionManager;

import java.util.HashMap;

public class AdminHome extends Activity {

    ImageButton btn1,btn2,btn3,btn4;

    Button btn5;

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_admin_home);

        btn1 = (ImageButton) findViewById(R.id.imageButton1);

        btn2 = (ImageButton) findViewById(R.id.imageButtonDue);

        btn4 = (ImageButton) findViewById(R.id.imageButtonSearch);


        btn1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(AdminHome.this, Adminissuebook.class);
                        startActivity(myIntent);
                    }
                }
        );

        btn4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(AdminHome.this, BookSearchMain.class);
                        startActivity(myIntent);
                    }
                }
        );

        btn2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(AdminHome.this, ViewWaitlist.class);
                        startActivity(myIntent);
                    }
                }
        );


    }
}
