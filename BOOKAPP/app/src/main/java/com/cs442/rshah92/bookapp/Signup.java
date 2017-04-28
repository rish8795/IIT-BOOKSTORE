package com.cs442.rshah92.bookapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cs442.rshah92.bookapp.Database.Logindata;

public class Signup extends Activity {

    Logindata myDb;
    EditText f_name,l_name,Password,email,cwid;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_signup);

        myDb = new Logindata(this);

        f_name = (EditText) findViewById(R.id.f_name);
        l_name = (EditText) findViewById(R.id.l_name);
        Password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        cwid = (EditText) findViewById(R.id.cwid);

        btnAddData = (Button) findViewById(R.id.button_register);


        AddData();
    }

    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Logindata accountTable=new Logindata(getApplicationContext());
                        accountTable.open();
                        accountTable.addNewAccount(email.getText().toString(),Password.getText().toString(),f_name.getText().toString(),l_name.getText().toString(),cwid.getText().toString());
                        onSignupSuccess();

                    }
                }
        );
    }

    public void onSignupSuccess() {

        Toast.makeText(this, "Registered Successfully!!Please Login with your email and password", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, null);
        Intent i=new Intent(getApplicationContext(),Login_admin.class);
        startActivity(i);
        finish();
    }
}
