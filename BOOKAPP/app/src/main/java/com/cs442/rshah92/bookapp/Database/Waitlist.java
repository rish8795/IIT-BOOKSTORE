package com.cs442.rshah92.bookapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class Waitlist{


    public static final String ID = "_id";
    public static final String Log_ID = "L_id";
    public static final String Book_ID = "B_id";
    public static final String TIME = "dateAndTime";
    public static final String STAT = "Status";

    public static final String TAG ="Waitlist";

    private static SQLiteDatabase mDb;
    private Waitlist.DatabaseHelper mDbHelper;
    //private SQLiteDatabase mDb;


    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "waitlist";
    private static final int DATABASE_VERSION = 3;

    private final Context mCtx;


    public static final String WAITLIST_CREATE =
            "CREATE TABLE if not exists " + TABLE_NAME + " (" +
                    ID + " integer PRIMARY KEY autoincrement, " +
                    Log_ID + " TEXT, " +
                    Book_ID + " TEXT, " +
                    TIME + " TEXT, " +
                    STAT + " TEXT)";


    private static class DatabaseHelper extends SQLiteOpenHelper{

        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);}

        @Override
        public void onCreate(SQLiteDatabase db) {

            Log.w(TAG, Waitlist.WAITLIST_CREATE);
            db.execSQL(Waitlist.WAITLIST_CREATE);

            Log.w(TAG, Logindata.ACCOUNT_CREATE);
            db.execSQL(Logindata.ACCOUNT_CREATE);

            Log.v(TAG, Waitlist.WAITLIST_CREATE);
            db.execSQL(Waitlist.WAITLIST_CREATE);



        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);

        }


    }

    public Waitlist(Context ctx) {
        this.mCtx = ctx;
    }

    public Waitlist open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }
    public long insertData(Integer L_id,Integer B_id,String W_dateandtime,String Status) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(Log_ID,L_id);
        contentValues.put(Book_ID,B_id);
        contentValues.put(TIME,W_dateandtime);
        contentValues.put(STAT,Status);

        Log.d("Success","Added");
        /*Log.d(B_id.toString(), L_id.toString());
        Log.d(W_dateandtime, Status);*/

        return mDb.insert(TABLE_NAME,null ,contentValues);
    }


    public Cursor bgetAllData() {

        Cursor res = mDb.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public long Updatedata(Integer L_id,Integer B_id,String W_dateandtime,String Status)
    {

        ContentValues contentValues = new ContentValues();
        contentValues.put(Log_ID,L_id);
        contentValues.put(Book_ID,B_id);
        contentValues.put(TIME,W_dateandtime);
        contentValues.put(STAT,Status);

        Log.d("Success","Updated");
        /*Log.d(B_id.toString(), L_id.toString());
        Log.d(W_dateandtime, Status);*/

        return mDb.update(TABLE_NAME,contentValues,"ID=?",new String[]{String.valueOf(L_id)});

    }

}

