package com.example.loginregistration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="USER_RECORD.db";
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
    public void onCreate(SQLiteDatabase db) {
        String new_user = "CREATE TABLE " + TABLE_NAME +
                " (" + COL_1 + " TEXT, " +
                COL_2 + " TEXT, " +
                COL_3 + " TEXT, " +
                COL_4 + " TEXT, " +
                COL_5 + " TEXT, " +
                COL_6+ " TEXT );" ;
        db.execSQL(new_user);
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(MyDB);
    }
    public Boolean register_user(String id,String name, String email,String password,String phoneno,String gender){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
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
    public Boolean delete(String email){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME+" where email=?",new String[]{email});
        if(cursor.getCount()>0){
            long result=db.delete(TABLE_NAME,"email=?",new String[]{email});
            if(result==-1){
                return false;
            }
            else
            {
                return true;
            }
        }
        else{
            return false;
        }

    }
    public Boolean update(String email,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_5,password);
        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME+" where email=?",new String[]{email});
        if(cursor.getCount()>0){
            long result=db.update(TABLE_NAME,contentValues,"email=?",new String[]{email});
            if(result==-1){
                return false;
            }
            else
            {
                return true;
            }
        }
        else{
            return false;
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
    public Cursor getinfo()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME+"",null);
          return cursor;
    }
}

