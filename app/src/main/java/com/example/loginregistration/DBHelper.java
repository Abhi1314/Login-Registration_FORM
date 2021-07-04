package com.example.loginregistration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="USER_RECORD";
    private static final String TABLE_NAME="USER_DATA";
    private static final String COL_1="ID";
    private static final String COL_2="NAME";
    private static final String COL_3="EMAIL";
    private static final String COL_4="PHONE_NUMBER";
    private static final String COL_5="PASSWORD";
    private static final String COL_6="GENDER";

    public DBHelper(@Nullable Context context) {
        super(context,DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
     MyDB.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,EMAIL TEXT,PHONE_NUMBER TEXT,PASSWORD TEXT,GENDER TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(MyDB);
    }
    public Boolean register_user(String name, String email, String password,String phoneno,String gender){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
       contentValues.put(COL_2,name);
       contentValues.put(COL_3,email);
       contentValues.put(COL_5,password);
       contentValues.put(COL_4,phoneno);
       contentValues.put(COL_6,gender);

        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else{
            return true;
        }
    }
    public Boolean checkuser(String email,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String [] columns={COL_1};
        String Selection= COL_3 + "=?"+" and "+COL_5+"=?";
        String [] Selectionargs={email,password};
        Cursor cursor= db.query(TABLE_NAME,columns,Selection,Selectionargs, null,null,null);
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

}

