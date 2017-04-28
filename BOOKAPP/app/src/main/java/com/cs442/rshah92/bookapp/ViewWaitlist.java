package com.cs442.rshah92.bookapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.cs442.rshah92.bookapp.Database.Waitlist;

public class ViewWaitlist extends Activity {

    Waitlist waitlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_view_books);

        waitlist = new Waitlist(this);
        openDB();
        populateListView();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        closeDB();
    }

    private void openDB(){
        waitlist.open();
    }

    private void closeDB(){
        waitlist.close();
    }

    private void populateListView(){
        Cursor cursor = waitlist.bgetAllData();

        startManagingCursor(cursor);

        String[] fromFieldNames = new String[]
                {Waitlist.Log_ID, Waitlist.Book_ID, Waitlist.TIME, Waitlist.STAT,Waitlist.ID};

        int[] toViewIDs = new int[]
                {R.id.logID, R.id.bookID, R.id.time, R.id.stat, R.id._ID};


        SimpleCursorAdapter myCursorAdapter =
                new SimpleCursorAdapter(
                        this,
                        R.layout.view_waitlist_layout,
                        cursor,
                        fromFieldNames,
                        toViewIDs
                );

        ListView myList = (ListView) findViewById(R.id.ListViewBooks);
        myList.setAdapter(myCursorAdapter);

        myList.setOnItemClickListener(

                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view, int position, long id)
                    {
                      //  String idb = ((TextView) view.findViewById(R.id._ID)).getText().toString();

                        int bid = Integer.parseInt(((TextView) view.findViewById(R.id.bookID)).getText().toString());
                        int userid = Integer.parseInt(((TextView) view.findViewById(R.id.logID)).getText().toString());
                        String time = ((TextView) view.findViewById(R.id.time)).getText().toString();
                        String status = ((TextView)view.findViewById(R.id.stat)).getText().toString();
                       // String isbn = ((TextView) view.findViewById(R.id.isbn)).getText().toString();

                        Intent in = new Intent(getApplicationContext(), assign_book.class);

                        Log.d("Log Id:",userid+"");
                        Log.d("Bid:", bid+"");
                        in.putExtra("uid",userid);
                        in.putExtra("bid",bid);
                        //in.putExtra("id", idb);
                       /* in.putExtra("name", name);
                        in.putExtra("user", user);
                        in.putExtra("time", time);
                        in.putExtra("status",status);*/
                        //in.putExtra("ISBN",isbn);
                        startActivity(in);

                    }
                }

        );
    }
}
