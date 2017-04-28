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

public class Logindata {


    public static final String _ID = "_id";
    public static final String USERNAME = "EMAIL";
    public static final String PASSWORD = "PASSWORD";
    public static final String FIRST_NAME = "F_name";
    public static final String LAST_NAME = "L_name";
    public static final String CWIID = "CWID";
    public static final String ROLES = "role";

    public static final String TAG = "Logindata";
    private static SQLiteDatabase mDb;
    private DatabaseHelper mDbHelper;
    //private SQLiteDatabase mDb;


    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "login_table";
    private static final int DATABASE_VERSION = 3;

    public int i = 0;


    private final Context mCtx;

    public static final String ACCOUNT_CREATE =
            "CREATE TABLE if not exists " + TABLE_NAME + " (" +
                    _ID + " integer PRIMARY KEY autoincrement," +
                    USERNAME + " TEXT," +
                    PASSWORD + " TEXT," +
                    FIRST_NAME + " TEXT," +
                    LAST_NAME + " TEXT," +
                    CWIID + " TEXT," +
                    ROLES + " TEXT)";


    static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            Log.w(TAG, Logindata.ACCOUNT_CREATE);
            db.execSQL(Logindata.ACCOUNT_CREATE);

            Log.w(TAG,BookDetails.BOOK_CREATE);
            db.execSQL(BookDetails.BOOK_CREATE);

            Log.w(TAG, Waitlist.WAITLIST_CREATE);
            db.execSQL(Waitlist.WAITLIST_CREATE);




        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);

        }
    }
        public Logindata(Context ctx) {
            this.mCtx = ctx;
        }

        public Logindata open() throws SQLException {
            mDbHelper = new DatabaseHelper(mCtx);
            mDb = mDbHelper.getWritableDatabase();
            return this;
        }

        public void close() {
            if (mDbHelper != null) {
                mDbHelper.close();
            }
        }


        public static Cursor checkCredentials(String username, String password) {
            Cursor cursor = mDb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + USERNAME + "=? AND " + PASSWORD + "=?", new String[]{username, password});
            return cursor;
        }


        public long addNewAccount(String username, String password, String f_name, String l_name, String cwid) {
            if (i==0) {
                i++;
                ContentValues contentValues = new ContentValues();
                contentValues.put(USERNAME, username);
                contentValues.put(PASSWORD, password);
                contentValues.put(FIRST_NAME, f_name);
                contentValues.put(LAST_NAME, l_name);
                contentValues.put(CWIID, cwid);
                contentValues.put(ROLES, "admin");
                Log.d("admin","");
                return mDb.insert(TABLE_NAME, null, contentValues);
            }

            else{
                ContentValues contentValues = new ContentValues();
                contentValues.put(USERNAME, username);
                contentValues.put(PASSWORD, password);
                contentValues.put(FIRST_NAME, f_name);
                contentValues.put(LAST_NAME, l_name);
                contentValues.put(CWIID, cwid);
                contentValues.put(ROLES, "user");
                Log.d("admin","");
                return mDb.insert(TABLE_NAME, null, contentValues);
            }

        }


    }


