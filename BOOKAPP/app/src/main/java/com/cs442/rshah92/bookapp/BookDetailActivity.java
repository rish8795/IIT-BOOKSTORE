package com.cs442.rshah92.bookapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.cs442.rshah92.bookapp.Database.BookDetails;
import com.cs442.rshah92.bookapp.Database.SessionManager;
import com.cs442.rshah92.bookapp.Database.Utils;
import com.cs442.rshah92.bookapp.Database.Waitlist;

import org.w3c.dom.Text;

import java.util.HashMap;

import static com.cs442.rshah92.bookapp.Database.SessionManager.*;
import static com.cs442.rshah92.bookapp.Database.SessionManager.KEY_Role;

public class BookDetailActivity extends Activity {

    BookDetails bookDetails;
    Bundle extra;
    Button add;
    SessionManager session;


    TextView Ttitle, Tauthor, Tisbn;
ImageView img;
    byte[] img1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_book_detail);

img=(ImageView)findViewById(R.id.ivBookCover);

        add = (Button) findViewById(R.id.request);

//        session.getUserDetails();

  //      HashMap<String, String> user = new HashMap<String, String>();
    //    user.get(KEY_Role);



        Ttitle = (TextView) findViewById(R.id.tvTitle);
        Tauthor = (TextView) findViewById(R.id.tvAuthor);
        Tisbn = (TextView) findViewById(R.id.tvISBN);


        bookDetails = new BookDetails(this);
        openDB();

        extra = getIntent().getExtras();

        int idb = extra.getInt("id",0);
        /*final String name = extra.getString("name");
        String author = extra.getString("author");
        String isbn = extra.getString("ISBN");
        String quantity = extra.getString("quantity");
        String shelf = extra.getString("shelf");
        String time = "2016-12-04 02:42:15";
*/
        final Cursor cursor = bookDetails.getIDData(idb+"");
cursor.moveToFirst();

        startManagingCursor(cursor);



        String[] fromFieldNames = new String[]
                {BookDetails.BOOKNAME};

        Ttitle.setText(cursor.getString(cursor.getColumnIndex(BookDetails.BOOKNAME)));
        Tauthor.setText(cursor.getString(cursor.getColumnIndex(BookDetails.AUTHOR)));
        Tisbn.setText(cursor.getString(cursor.getColumnIndex(BookDetails.ISBN)));
        img1=cursor.getBlob(cursor.getColumnIndex(BookDetails.IMG));
        img.setImageBitmap(Utils.getImage(img1));
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
                        Toast.makeText(getApplicationContext(), "added " + (cursor.getString(cursor.getColumnIndex(BookDetails.BOOKNAME))), Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }

    public void addbook()
    {
        session = new SessionManager((getApplicationContext()));

        session.checkLogin();

        HashMap<String, String > user = session.getUserDetails();

        //String role = user.get();
        int uid = Integer.parseInt(user.get(KEY_Role));

        final ProgressDialog progressdialog = new ProgressDialog(this,R.style.AppTheme);
        progressdialog.setIndeterminate(true);
        progressdialog.setMessage("Creating Account");
        progressdialog.show();

        String idb = extra.getString("id");
        int id = Integer.parseInt(idb);
        String time = "2016-12-04 02:42:15";

        Waitlist wait = new Waitlist(getApplicationContext());
        wait.open();
        wait.insertData(uid, id, time, "Waiting");
        progressdialog.dismiss();
    }


    private void openDB(){
        bookDetails.open();
    }

    private void closeDB(){
        bookDetails.close();
    }
}
