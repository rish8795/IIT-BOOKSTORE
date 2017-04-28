package com.cs442.rshah92.bookapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.cs442.rshah92.bookapp.Database.Logindata;

public class MainActivity extends Activity {


    Logindata myDb;
    Button btnRegister;
    Button btnviewAll;
    Button login;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        login = (Button) findViewById(R.id.Login);
        search = (Button) findViewById(R.id.btnSearch);

        Register();
        Search();
        Login();

    }


    public void Register() {
        btnRegister.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(MainActivity.this, Signup.class);
                        startActivity(myIntent);
                    }
                }
        );
    }




    public void Login(){

        login.setOnClickListener(

                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent myIntent = new Intent(MainActivity.this, Login_admin.class);
                                startActivity(myIntent);
                            }
                        }
        );
    }

    public void Search(){

        search.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(MainActivity.this, Search.class);
                        startActivity(myIntent);
                    }
                }
        );
    }


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
