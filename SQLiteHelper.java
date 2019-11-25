package com.opac.network;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static String dbname = "network.db";


    public SQLiteHelper(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table infoNetwork ( id INTEGER PRIMARY KEY AUTOINCREMENT, info TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS infoNetwork ");
        onCreate(db);
    }

    public boolean addData(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("info", name);

        long result = db.insert("infoNetwork", null, contentValues);

        return result != -1;
    }

    public ArrayList<String> getdata(){
        ArrayList<String> listData = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM infoNetwork ", null);

        res.moveToFirst();

        while (!res.isAfterLast()){
            String t1 = res.getString(0);
            String t2 = res.getString(1);

            listData.add(t1+" - "+t2);

            res.moveToNext();
        }

        return listData;
    }

}
