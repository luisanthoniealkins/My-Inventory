package com.laacompany.uts_mcd.Handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.laacompany.uts_mcd.Database.Schema.TransactionTable;
import com.laacompany.uts_mcd.Database.TransactionBaseHelper;
import com.laacompany.uts_mcd.ObjectClass.Transaction;

import java.util.ArrayList;
import java.util.UUID;

public class Handle {


    private static TransactionBaseHelper mTransHelper;
    public static ArrayList<Transaction> sTransactions;

    public static void init(Context context){
        mTransHelper = new TransactionBaseHelper(context);
        sTransactions = getTransactionList();
    }

    public static void insert(Transaction transaction){

        SQLiteDatabase db = mTransHelper.getWritableDatabase();
        db.insert(TransactionTable.NAME, null, getContentValues(transaction));
        db.close();

    }


    public static void update(Transaction transaction){
        SQLiteDatabase db = mTransHelper.getWritableDatabase();
        String uuidString = transaction.getId().toString();
        db.update(TransactionTable.NAME, getContentValues(transaction), TransactionTable.Cols.UUID + " = ?", new String[] {uuidString});
    }

    private static ArrayList<Transaction> getTransactionList(){
        ArrayList<Transaction> transactions = new ArrayList<>();
        SQLiteDatabase db = mTransHelper.getReadableDatabase();
        Cursor cursor = db.query(TransactionTable.NAME, null, null,null,null,null,null);

        while(cursor.moveToNext()){
            UUID uuid = UUID.fromString(cursor.getString(cursor.getColumnIndex(TransactionTable.Cols.UUID)));
            String name = cursor.getString(cursor.getColumnIndex(TransactionTable.Cols.TITLE));
            String desc = cursor.getString(cursor.getColumnIndex(TransactionTable.Cols.DESCRIPTION));
            int quan = cursor.getInt(cursor.getColumnIndex(TransactionTable.Cols.Quantity));
            transactions.add(new Transaction(uuid,name,desc,quan));
        }

        cursor.close();
        db.close();
        return transactions;
    }

    private static ContentValues getContentValues(Transaction transaction){
        ContentValues values = new ContentValues();
        values.put(TransactionTable.Cols.UUID, transaction.getId().toString());
        values.put(TransactionTable.Cols.TITLE, transaction.getName());
        values.put(TransactionTable.Cols.DESCRIPTION, transaction.getDescription());
        values.put(TransactionTable.Cols.Quantity, String.valueOf(transaction.getQuantity()));
        return values;
    }


}
