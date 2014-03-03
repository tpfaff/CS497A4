package com.example.assignment4;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;

public class EditItemActivity extends Activity {
	EditText textField=null;
	RatingBar ratingBar=null;
	SQLiteDatabase db=null;
	Cursor dbCursor=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent=getIntent();
		int imageId=intent.getIntExtra("imageId",-1);
		if(imageId==-1){
			return;
		}
		setContentView(R.layout.edit_item_layout);
		db.openDatabase("Database", null,SQLiteDatabase.OPEN_READWRITE );
		String whereClause = "TIMETABLE_MODULECODE=123";
		dbCursor=db.query("Pics",null,null,null,null,null,null);//get the whole table
		//probably want to use the WHERE clause here, where the row has the image id in it!
		textField=(EditText)findViewById(R.id.title);
		ratingBar=(RatingBar)findViewById(R.id.image_rating_bar);
		if(dbCursor.moveToFirst()){
			textField.setText()
		}
	}

}
