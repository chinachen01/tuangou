package com.scxh.meituan.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MeituanDataBaseHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "meituan.db";
	private static final int DB_VERSION = 1;
	
	private static MeituanDataBaseHelper helper = null;
	private MeituanDataBaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}
	public static MeituanDataBaseHelper getInstanceDataBaseHelper(Context context) {
		if(helper == null) {
			helper = new MeituanDataBaseHelper(context);
		}
		return helper;
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE user(_id INTEGER PRIMARY KEY AUTOINCREMENT,_name TEXT NOT NULL UNIQUE,_password TEXT NOT NULL)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
