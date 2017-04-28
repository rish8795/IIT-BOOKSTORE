
package com.cs442.rshah92.bookapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.cs442.rshah92.bookapp.Database.BookDetails;
import com.cs442.rshah92.bookapp.Database.Waitlist;

import org.w3c.dom.Text;

public class ISBNSearchActivity extends Activity{

    BookDetails bookDetails;

    Button search;

    EditText searchBox;

    byte[] image;

    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_isbnsearch);


        bookDetails = new BookDetails(this);
        openDB();
        search = (Button) findViewById(R.id.searchButton);
        searchBox = (EditText) findViewById(R.id.BooksearchBox);
        populateListView("");

        search.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String searchParam = searchBox.getText().toString();
                        populateListView(searchParam);
                    }
                }
        );


    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        closeDB();
    }

    private void openDB(){
        bookDetails.open();
    }

    private void closeDB(){
        bookDetails.close();
    }

   /* public void onClickSearch(){
        String searchParam = searchBox.getText().toString();
        populateListView(searchParam);
    }*/

    private void populateListView(String searchParameter){
        Cursor cursor = bookDetails.getisbndata(searchParameter);

        startManagingCursor(cursor);

        String[] fromFieldNames = new String[]
                {BookDetails.BOOKNAME, BookDetails.AUTHOR, BookDetails.QUANT, BookDetails.SHELF, BookDetails._ID, BookDetails.ISBN};

        int[] toViewIDs = new int[]
                {R.id.bookID, R.id.logID, R.id.available, R.id.shelf, R.id._ID, R.id.isbn};

        //image = cursor.getBlob(cursor.getColumnIndex(BookDetails.IMG));

        //img.setImageBitmap(Utils.getImage(image));

        final SimpleCursorAdapter myCursorAdapter =
                new SimpleCursorAdapter(
                        this,
                        R.layout.view_book_layout,
                        cursor,
                        fromFieldNames,
                        toViewIDs
                );

        myCursorAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {

            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                int i;
                //Quantity value check
                if (view.getId() == R.id.available) {
                    TextView tv = (TextView) view;
                    i = Integer.parseInt(cursor.getString(columnIndex));
                    if (i <= 0){
                        tv.setText(""+2);
                        tv.setTextSize(15);
                        tv.setTextColor(Color.RED);
                    }
                    else{
                        tv.setText(""+i);
                        tv.setTextSize(15);
                        tv.setTextColor(Color.GREEN);

                    }

                    return true;
                }
                return false;
            }
        });


        final ListView myList = (ListView) findViewById(R.id.ListViewBooks);
        myList.setAdapter(myCursorAdapter);

        myList.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view, int position, long id)
                    {
                                                    /*progressdialog.setIndeterminate(true);
                                                    progressdialog.setMessage("Creating Account");
                                                    progressdialog.show();
                                                    Waitlist book = new Waitlist(getApplicationContext());
                                                    book.open();
                                                    book.insertData(1, 1, "tomorrow", "Waiting");
                                                    progressdialog.dismiss();*/
                        String idb = ((TextView) view.findViewById(R.id._ID)).getText().toString();

                        String name = ((TextView) view.findViewById(R.id.bookID)).getText().toString();
                        String author = ((TextView) view.findViewById(R.id.logID)).getText().toString();
                        String isbn = ((TextView) view.findViewById(R.id.isbn)).getText().toString();
                        String quantity = ((TextView) view.findViewById(R.id.available)).getText().toString();
                        String shelf = ((TextView) view.findViewById(R.id.shelf)).getText().toString();

                        Intent in = new Intent(getApplicationContext(), BookDetailActivity.class);

                        in.putExtra("id", idb);
                        in.putExtra("name", name);
                        in.putExtra("author", author);
                        in.putExtra("ISBN", isbn);
                        in.putExtra("quantity", quantity);
                        in.putExtra("shelf", shelf);

                        startActivity(in);

                    }
                }
        );
    }
}

