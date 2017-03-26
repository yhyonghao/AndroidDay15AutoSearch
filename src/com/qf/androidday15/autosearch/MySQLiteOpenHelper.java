package com.qf.androidday15.autosearch;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MySQLiteOpenHelper extends SQLiteOpenHelper{
	public String create_table="create table if not exists log(" +
			"_id integer primary key autoincrement," +
			"content  text not null)";
	
   public MySQLiteOpenHelper(Context context,String name,CursorFactory factory,int version){
	   super(context,name,factory,version);
   }

@Override
public void onCreate(SQLiteDatabase db) {
	db.execSQL(create_table);
}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	db.execSQL("drop table if exists log");
	onCreate(db);
	
}
   
}
