package com.example.assignment4;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
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
		Integer imageId=intent.getIntExtra("imageId",-1);
		if(imageId==-1){
			return;
		}
		String id=imageId.toString(); //not used right now
		setContentView(R.layout.edit_item_layout);
		db=this.openOrCreateDatabase("Database",this.MODE_PRIVATE,null);
		String selection="DrawableReference = ?";
		String[] whereClause={id};
		dbCursor=db.query("Pics",null,selection,whereClause,null,null,null);//get the row where the correct DrawableReference integer is found
		textField=(EditText)findViewById(R.id.title);
		ratingBar=(RatingBar)findViewById(R.id.image_rating_bar);
		//if(dbCursor.moveToFirst()){
			textField.setText(dbCursor.getString(0)); //this never happens
			ratingBar.setRating(dbCursor.getFloat(2));
	//	}
	}
	public void saveData(View view){
	//find the row again
	//insert into that row the new name and rating
		//dbCursor=db.query("Pics", null, selection, selectionArgs, groupBy, having, orderBy);
		if(dbCursor.moveToFirst()){
		//	db.execSQL(sql);//insert
		}
	}
}
