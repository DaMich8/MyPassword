package com.example.mypassword.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper
{
    //DB versija
    private  static final int DATABASE_VERSION = 1;

    //Db Pavadinimas
    private static final String DBName="MyPassword.db";

    //lenteles pavadinimas
    private static final String TABLE_USER = "user";

    //user table column
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_USERNAME= "user_username";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";


    //
    private final String CREATE_USER_TABLE = "CREATE TABLE "+ TABLE_USER + "("
            +COLUMN_USER_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_USER_USERNAME+ " TEXT, "
            +COLUMN_USER_EMAIL+ " TEXT, "
            +COLUMN_USER_PASSWORD+ " TEXT"+")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS "+TABLE_USER;


    public Database(Context context)
    {
        super(context, DBName, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase DataBse) {
        DataBse.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);

    }

    public void addUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_USER_USERNAME, user.getUsername());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        db.insert(TABLE_USER, null, values);
        db.close();
    }
}
