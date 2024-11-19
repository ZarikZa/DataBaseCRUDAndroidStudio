package com.example.databasess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DaaBaseHelper extends SQLiteOpenHelper {

    private static final String dbName = "book_debil";
    private static final int SCHEMA = 1;
    static final String Table_Name = "books";
    public static final String COLUMN_ID = "id_book";
    public static final String COLUMN_NAME = "book_name";
    public static final String COLUMN_AUTOR = "book_autor";

    public DaaBaseHelper(@Nullable Context context) {
        super(context, dbName, null,SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + Table_Name
                + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_AUTOR + " TEXT);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(sqLiteDatabase);
    }

    public long addBook(String bookNameAdd, String bookAutorAdd){
        SQLiteDatabase db = this.getWritableDatabase(); //открываем бд для записи
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, bookNameAdd);
        values.put(COLUMN_AUTOR, bookAutorAdd);

        long result = db.insert(Table_Name, null, values);
        db.close();
        return result;
    }

    public int deleteBookID(long bookID){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(Table_Name, COLUMN_ID+"=?", new String[]{String.valueOf(bookID)});
        db.close();
        return result;
    }

    public Cursor getAllBooks(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.query(Table_Name,null,null,null,null,null,null);
    }

    public Cursor getBookById(int bookId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = {String.valueOf(bookId)};
        return db.query(Table_Name, null, COLUMN_ID + "=?", selectionArgs, null, null, null);
    }

    public int updateBook(int bookId, String newBookName, String newBookAuthor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, newBookName);
        contentValues.put(COLUMN_AUTOR, newBookAuthor);
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(bookId)};
        int result = db.update(Table_Name, contentValues, selection, selectionArgs);
        db.close();
        return result;
    }
}
