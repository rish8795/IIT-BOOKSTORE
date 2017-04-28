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


public class UserHome extends Activity {


    ImageButton btn1,btn2,btn3;
    Button btn4;
    SessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_home);

        btn1 = (ImageButton) findViewById(R.id.imageButtonSearch);

        btn2 = (ImageButton) findViewById(R.id.imageButtonDue);

        btn3 = (ImageButton) findViewById(R.id.imageButtonProfile);


        btn1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(UserHome.this, BookSearchMain.class);
                        startActivity(myIntent);
                    }
                }
        );

    }


}
