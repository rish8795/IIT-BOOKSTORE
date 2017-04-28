package com.cs442.rshah92.bookapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by rishabh on 10-11-2016.
 */

public class BookDetails{


    public static final String _ID = "_id";
    public static final String BOOKNAME = "B_name";
    public static final String AUTHOR = "Author";
    public static final String ISBN = "Isbn";
    public static final String QUANT = "Quantity";
    public static final String IMG = "Image";
    public static final String DESCRIPTION = "Description";
    public static final String SHELF = "Shelf";

    public static final String TAG ="BookDetails";

    private static SQLiteDatabase mDb;
    private BookDetails.DatabaseHelper mDbHelper;
    //private SQLiteDatabase mDb;


    public static final String DATABASE_NAME = "Student.db";
    public static final String BOOK_DET = "book_detail";
    private static final int DATABASE_VERSION = 3;

    private final Context mCtx;


    public static final String BOOK_CREATE =
            "CREATE TABLE if not exists " + BOOK_DET + " (" +
                    _ID + " integer PRIMARY KEY autoincrement," +
                    BOOKNAME + " TEXT," +
                    AUTHOR + " TEXT," +
                    ISBN + " TEXT," +
                    QUANT + " TEXT," +
                    IMG + " BLOB," +
                    DESCRIPTION + " TEXT," +
                    SHELF + " TEXT)";


    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {



            Log.w(TAG, Logindata.ACCOUNT_CREATE);
            db.execSQL(Logindata.ACCOUNT_CREATE);

            Log.v(TAG, BookDetails.BOOK_CREATE);
            db.execSQL(BookDetails.BOOK_CREATE);

            Log.w(TAG, Waitlist.WAITLIST_CREATE);
            db.execSQL(Waitlist.WAITLIST_CREATE);

        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + BOOK_DET);
            onCreate(db);

        }
    }

    public BookDetails(Context ctx) {
        this.mCtx = ctx;
    }

    public BookDetails open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }
    public long insertData(String B_name,String Author,String Isbn,String Quantity,byte[] data,String Description,String Shelf) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOKNAME,B_name);
        contentValues.put(AUTHOR,Author);
        contentValues.put(ISBN,Isbn);
        contentValues.put(QUANT,Quantity);
        contentValues.put(IMG,data);
        contentValues.put(DESCRIPTION,Description);
        contentValues.put(SHELF,Shelf);
        Log.d("Success","Added");
        return mDb.insert(BOOK_DET,null ,contentValues);

    }


    public Cursor bgetAllData() {

        Cursor res = mDb.rawQuery("select * from "+BOOK_DET,null);
        return res;
    }

    public Cursor getSpecificData(String input) {

        Cursor res = mDb.rawQuery("SELECT * FROM " + BOOK_DET + " WHERE " + BOOKNAME + " LIKE " + "'%" + input +"%'",null);
        return res;
    }

    public Cursor getIDData(String input) {

        Cursor res = mDb.rawQuery("SELECT * FROM " + BOOK_DET + " WHERE " + _ID + " LIKE " + "'%" + input +"%'",null);
        return res;
    }


    public boolean bupdateData(String id,String username,String password) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(_ID,id);
        contentValues.put(BOOKNAME,username);
        contentValues.put(AUTHOR,password);
        mDb.update(BOOK_DET, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer bdeleteData (String id) {

        return mDb.delete(BOOK_DET, "ID = ?",new String[] {id});
    }

    public Cursor getAuthordata(String input) {

        Cursor res = mDb.rawQuery("SELECT * FROM " + BOOK_DET + " WHERE " + AUTHOR + " LIKE " + "'%" + input +"%'",null);
        return res;
    }

    public Cursor getisbndata(String input) {

        Cursor res = mDb.rawQuery("SELECT * FROM " + BOOK_DET + " WHERE " + ISBN + " LIKE " + "'%" + input +"%'",null);
        return res;
    }

}