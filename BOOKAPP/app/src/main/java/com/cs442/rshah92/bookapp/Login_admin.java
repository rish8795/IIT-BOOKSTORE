package com.cs442.rshah92.bookapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.cs442.rshah92.bookapp.Database.BookDetails;
import com.cs442.rshah92.bookapp.Database.SessionManager;
import com.cs442.rshah92.bookapp.Database.Logindata;

public class Login_admin extends Activity {



    SessionManager session;

    // Email, password edittext
    EditText txtUsername, txtPassword;

    // login button
    Button btnLogin,basinup1;

    // Alert Dialog Manager
    AlertDialog alertDialog;


    Logindata logindata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login_admin);

        logindata = new Logindata(this);

        logindata.open();
        session = new SessionManager(getApplicationContext());

        alertDialog = new AlertDialog.Builder(this).create();

        txtUsername = (EditText) findViewById(R.id.tusername);
        txtPassword = (EditText) findViewById(R.id.apassword);


        basinup1 = (Button) findViewById(R.id.basinup1);
        basinup1.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent myIntent = new Intent(Login_admin.this, Signup.class);
                            startActivity(myIntent);
                        }
                    }
            );

        // Login button
        btnLogin = (Button) findViewById(R.id.alogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get username, password from EditText
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                Cursor cursor=  logindata.checkCredentials(username,password);
                if (!username.equals("")  || !password.equals("")) {

                    if (cursor != null && cursor.getCount()==1) {

                        cursor.moveToFirst();




                        String cusername = cursor.getString(cursor
                                .getColumnIndex(Logindata.USERNAME));
                        String cpassword = cursor.getString(cursor
                                .getColumnIndex(Logindata.PASSWORD));
                        String role = cursor.getString(cursor
                                .getColumnIndex(Logindata.ROLES));
                        int storeid = Integer.parseInt(cursor.getString(cursor
                                .getColumnIndex(Logindata._ID)));
                        Log.d("storeid",storeid+"");



                        if (username.equals(cusername) && password.equals(cpassword) && storeid==1) {
                            session.createLoginSession(cusername, storeid,role,cpassword);
                            Intent i = new Intent(getApplicationContext(), AdminHome.class);
                            startActivity(i);
                            finish();
                        }

                        else if (username.equals(cusername) && password.equals(cpassword) && storeid>1) {
                            session.createLoginSession(cusername, storeid,role,cpassword);
                            Intent i = new Intent(getApplicationContext(), UserHome.class);
                            startActivity(i);
                            finish();
                        }

                        else {

                        }
                    } else {
                        alertDialog.setTitle("Login failed..");
                        alertDialog.setMessage("Username/Password is incorrect");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                }
                else
                {

                    alertDialog.setTitle("Required");
                    alertDialog.setMessage("Username and Password is required");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }

            }
        });
    }
}