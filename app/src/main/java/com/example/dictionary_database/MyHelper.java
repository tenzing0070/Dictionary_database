package com.example.dictionary_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {

    private static final String databaseName = "DictionaryDB.db";
    private static final int dbVersion = 1;
    private static final String Word = "Word";
    private static final String Meaning = "Meaning";
    private static final String tblWord = "tblWord";
    private static final String WordId = "WordId";

    public MyHelper (Context context){
        super(context, databaseName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE "+ tblWord + "(" + WordId + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Word + "TEXT," + Meaning + "TEXT" + ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static long InsertData(String word, String meaning, SQLiteDatabase db){

        long id;
        ContentValues contentValues = new ContentValues();
        contentValues.put(Word, word);
        contentValues.put(Meaning, meaning);
        id = db.insert(tblWord, null, contentValues);
        return id;
    }

    public DictionaryModel getWordId(int id){

        DictionaryModel meaning = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from tblWord where WordId = " + id;
        Cursor cursor =  db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{

                Integer wordId = cursor.getInt(cursor.getColumnIndexOrThrow("WordId"));
                String word = cursor.getString(cursor.getColumnIndexOrThrow("Word"));
                String mean = cursor.getString(cursor.getColumnIndexOrThrow("Meaning"));
                meaning = new DictionaryModel(word, wordId, mean);
            }

            while (cursor.moveToNext());
        }

        cursor.close();
        return meaning;
    }
}
