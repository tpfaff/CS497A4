package com.example.assignment4;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseHelper {
	
	private static SQLiteDatabase db=null;
	private static Context parentContext;
	private ArrayList<String> nodeList=new ArrayList<String>();
	private ArrayList<String> nameNodes=null;
	private ArrayList<String> imageIds=null;
	private AssetManager assMan;
	
	public DatabaseHelper(Context parentContext) {
		this.parentContext=parentContext.getApplicationContext();
		assMan=parentContext.getAssets();
	}

	public boolean initializeNewDatabase(String filePath){
		
		try {
			assMan.open(filePath);
			db=parentContext.openOrCreateDatabase("Database",parentContext.MODE_PRIVATE,null);
		
			
			db.close();
			return true;
		} catch (IOException e) {
			
			e.printStackTrace();
			return false;
		}
	}
	
	

	public SQLiteDatabase getDatabase(){
		return db;
	}
	
	
	}

	

