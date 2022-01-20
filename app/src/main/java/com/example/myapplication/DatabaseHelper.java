package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR PRIMARY KEY ,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(1234567890,'Nilesh',100000.00,'nilesh@gmail.com','XXXXXXXXXXXX1234','ABC09876543')");
        db.execSQL("insert into user_table values(2345678901,'Manoj',90000.67,'manoj@gmail.com','XXXXXXXXXXXX2341','BCA98765432')");
        db.execSQL("insert into user_table values(3456789012,'Aniket',80000.56,'aniket@gmail.com','XXXXXXXXXXXX3412','CAB87654321')");
        db.execSQL("insert into user_table values(4567890123,'Hritik',70000.01,'hritik@gmail.com','XXXXXXXXXXXX4123','ABC76543210')");
        db.execSQL("insert into user_table values(5678901234,'Rohan',60000.48,'rohan@gmail.com','XXXXXXXXXXXX2345','BCA65432109')");
        db.execSQL("insert into user_table values(6789012345,'Aman',50000.16,'aman@gmail.com','XXXXXXXXXXXX3452','CAB54321098')");
        db.execSQL("insert into user_table values(7890123456,'Shivang',40000.00,'shivang@gmail.com','XXXXXXXXXXXX4523','ABC43210987')");
        db.execSQL("insert into user_table values(8901234567,'Shubham',30000.22,'shubham@gmail.com','XXXXXXXXXXXX5234','BCA32109876')");
        db.execSQL("insert into user_table values(9012345678,'Vivek',20000.46,'vivek@gmail.com','XXXXXXXXXXXX3456','CAB21098765')");
        db.execSQL("insert into user_table values(1234567809,'Kirti',10000.90,'kirti@gmail.com','XXXXXXXXXXXX4563','ABC10987654')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
