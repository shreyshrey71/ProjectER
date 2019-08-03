package com.example.android.pianotile20;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Record.db";
    public static final String TABLE_NAME = "Record_table";
    public static final String sno = "Sno";
    public static final String div = "Div";
    public static final String rate = "Rate";
    public static final String time = "Time";
    public static final String counter = "Counter";
    public static final String mode = "Mode";
    public static final String recording = "Recording";

    Context context;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table " + TABLE_NAME + "(Sno Integer Primary Key AUTOINCREMENT, div String, rate String, time String, counter String, mode String, recording String)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + TABLE_NAME);
        onCreate(db);
    }
    public long insertData(String mdiv, String mrate, String mtime, String mcounter, String m,String rec) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(div, mdiv);
        contentValues.put(rate, mrate);
        contentValues.put(time, mtime);
        contentValues.put(counter, mcounter);
        contentValues.put(mode, m);
        contentValues.put(recording,rec);
        long l = db.insert(TABLE_NAME, null, contentValues);
        return l;
    }

    public Cursor getAllDataSno() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" order by sno desc",null);
        return res;
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public Cursor getAllDataModeC(String m) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where Mode = ? order by Counter desc",new String[] {m},null);
        return res;
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public Cursor getAllDataModeD(String m) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where Mode = ? order by time",new String[] {m},null);
        return res;
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public Cursor getAllDataModeR(String m) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where Mode = ? order by rate desc",new String[] {m},null);
        return res;
    }

}
