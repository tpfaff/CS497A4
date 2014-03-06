package com.example.assignment4;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

public class EditItemActivity extends Activity {
	EditText textField=null;
	ImageView iv=null;
	RatingBar ratingBar=null;
	SQLiteDatabase db=null;
	Cursor dbCursor=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent=getIntent();
		setContentView(R.layout.edit_item_layout);
		Integer imageId=intent.getIntExtra("imageId",-1);
		if(imageId==-1){
			return;
		}
		
		textField=(EditText)findViewById(R.id.title);
		ratingBar=(RatingBar)findViewById(R.id.image_rating_bar);
		iv=(ImageView)findViewById(R.id.image_to_edit);
	
		
		Drawable img=getApplication().getResources().getDrawable(imageId);
		
		db=this.openOrCreateDatabase("Database",this.MODE_PRIVATE,null);
		String selection="DrawableReference = ?";
		String[] whereClause={imageId.toString()};
		dbCursor=db.query("Pics",null,selection,whereClause,null,null,null);//get the row where the correct DrawableReference integer is found
		
	
		if(dbCursor.moveToFirst()){
			textField.setText(dbCursor.getString(0)); 
			iv.setImageDrawable(img);
			ratingBar.setRating(dbCursor.getFloat(2));
	     }
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
