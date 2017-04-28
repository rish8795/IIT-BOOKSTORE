package com.cs442.rshah92.bookapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cs442.rshah92.bookapp.Database.BookDetails;
import com.cs442.rshah92.bookapp.Database.Utils;

import java.io.InputStream;

import static com.cs442.rshah92.bookapp.R.id.bokadd;

public class Adminissuebook extends Activity implements View.OnClickListener{


    byte[] inputData;
    Bitmap bitmap;
    Uri selectedImage;
    private static final int Result_load_image = 1;
    ImageView imageupload,imagedownload;
    Button btnupload,btndownload,btnadd;
    EditText et1,et2;
    EditText bokname,author,isbn,shelf;
    EditText quantity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_adminissuebook);


        imageupload = (ImageView) findViewById(R.id.imageupload);
        imagedownload = (ImageView) findViewById(R.id.imagedownload);

        btnupload = (Button) findViewById(R.id.btnupload);
        btnadd = (Button) findViewById(bokadd);

        et1 = (EditText) findViewById(R.id.etuploadname);


        bokname = (EditText) findViewById(R.id.bookname);
        author = (EditText) findViewById(R.id.authorname);
        isbn = (EditText) findViewById(R.id.isbn);
        shelf = (EditText) findViewById(R.id.shelf);
        quantity = (EditText) findViewById(R.id.quantity);

        imageupload.setOnClickListener(this);
        et1.setOnClickListener(this);
        btnupload.setOnClickListener(this);
        imagedownload.setOnClickListener(this);

//        btndownload.setOnClickListener(this);
        btnadd.setOnClickListener(this);
    }

        @Override
        public void onClick (View view)
        {
            switch (view.getId()) {
                case R.id.imageupload:

                    break;

                case R.id.btnupload:
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, Result_load_image);
                    break;
                case R.id.etuploadname:
                    break;
                case R.id.imagedownload:
                    break;
                case R.id.bokadd:
                    Toast.makeText(this,"add button clicked",Toast.LENGTH_SHORT).show();
                    addbook();
                    break;
            }
        }


        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            try {
                if (requestCode == Result_load_image && resultCode == RESULT_OK && data != null) {
                     selectedImage= data.getData();
                    imageupload.setImageURI(selectedImage);
                } else {
                    Toast.makeText(this, "You haven't picked Image",
                            Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                        .show();
            }
        }

    public void addbook()
    {
        if ( !Validate())
        {
            onBookaddfailed();
            return;
        }
        btnadd.setEnabled(false);
        final ProgressDialog progressdialog = new ProgressDialog(this,R.style.AppTheme);
        progressdialog.setIndeterminate(true);
        progressdialog.setMessage("Creating Account");
        progressdialog.show();

        String _bookname = bokname.getText().toString();
        String _author = author.getText().toString();
        String _isbn = isbn.getText().toString();
        String _shelf = shelf.getText().toString();
        String _quantity = quantity.getText().toString();
        String _description = et1.getText().toString();
        InputStream istream = null;

        try {
            istream= getContentResolver().openInputStream(selectedImage);
            inputData = Utils.getBytes(istream);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

        BookDetails book = new BookDetails(getApplicationContext());
        book.open();
        book.insertData(_bookname,_author,_isbn,_quantity,inputData,_description,_shelf);
        onBookaddsuccess();
        progressdialog.dismiss();
    }

    public void onBookaddsuccess()
    {
        btnadd.setEnabled(false);
        Toast.makeText(this,"Book added sucessfuly",Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK,null);
        finish();
    }

    public boolean Validate()
    {

    boolean valid= true;
        String Bookname = bokname.getText().toString();
        String Author = author.getText().toString();
        String Isbn = isbn.getText().toString();
        String Shelf = shelf.getText().toString();
        String Quantity = quantity.getText().toString();
        String Description = et1.getText().toString();

        if (Bookname.isEmpty())
        {
            bokname.setError("Book name required");
        }else {
            bokname.setError(null);
        }

        if (Author.isEmpty())
        {
            bokname.setError("Book name required");
        }else {
            bokname.setError(null);
        }


        if (Isbn.isEmpty())
        {
            bokname.setError("Book name required");
        }else {
            bokname.setError(null);
        }


        if (Shelf.isEmpty())
        {
            bokname.setError("Book name required");
        }else {
            bokname.setError(null);
        }


        if (Quantity.isEmpty())
        {
            bokname.setError("Book name required");
        }else {
            bokname.setError(null);
        }


        if (Bookname.isEmpty())
        {
            bokname.setError("Book name required");
        }else {
            bokname.setError(null);
        }

        if (Description.isEmpty())
        {
            bokname.setError("Book name required");
        }else {
            bokname.setError(null);
        }

        return valid;
    }

    public void onBookaddfailed()
    {
        btnadd.setEnabled(true);
    }
}
