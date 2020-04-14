package com.laacompany.uts_mcd.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TransactionBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "transactionBase.db";

    public TransactionBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Schema.TransactionTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                Schema.TransactionTable.Cols.UUID + ", " +
                Schema.TransactionTable.Cols.TITLE + ", " +
                Schema.TransactionTable.Cols.DESCRIPTION + ", " +
                Schema.TransactionTable.Cols.Quantity +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
