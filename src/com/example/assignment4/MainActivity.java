package com.example.assignment4;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RatingBar;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Cursor dbCursor=null;
		int drawable1Resource=0;
		int drawable2Resource=0;
		int drawable3Resource=0;
		
		float rating1=0;
		float rating2=0;
		float rating3=0;
		
		ImageView img1=(ImageView)findViewById(R.id.img_view_1);
		ImageView img2=(ImageView)findViewById(R.id.img_view_2);
		ImageView img3=(ImageView)findViewById(R.id.img_view_3);
	
		
		RatingBar ratingbar1 =(RatingBar)findViewById(R.id.rating_bar_1);
		RatingBar ratingbar2 =(RatingBar)findViewById(R.id.rating_bar_2);
		RatingBar ratingbar3 =(RatingBar)findViewById(R.id.rating_bar_3);
		SQLiteDatabase db=this.openOrCreateDatabase("Database",this.MODE_PRIVATE,null);
		
		db.execSQL("CREATE TABLE IF NOT EXISTS Pics(DrawableName TEXT,DrawableReference INTEGER,PictureRating REAL)");
	    dbCursor=db.query("Pics",null,null,null,null,null,null);//get the whole table
	 if(!dbCursor.moveToFirst()){//if theres not an element in the db yet, insert stuff
		db.execSQL("INSERT INTO Pics VALUES('"+"R.drawable.space1"+"\'"+","+"\'"+R.drawable.space1+"\'"+","+"\'"+ratingbar1.getRating()+"')");
		db.execSQL("INSERT INTO Pics VALUES('"+"R.drawable.space2"+"\'"+","+"\'"+R.drawable.space2+"\'"+","+"\'"+ratingbar2.getRating()+"')");
		db.execSQL("INSERT INTO Pics VALUES('"+"R.drawable.space3"+"\'"+","+"\'"+R.drawable.space3+"\'"+","+"\'"+ratingbar3.getRating()+"')");

		}
		
		                  //table //columns (null gives all columns) //WHERE clause
		dbCursor=db.query("Pics",null,null,null,null,null,null);//get the whole table
		
	//method, get image drawables from db
		if(dbCursor.moveToFirst()){
			drawable1Resource=dbCursor.getInt(1); //get the first image's drawable folder reference
			dbCursor.moveToNext();
			drawable2Resource=dbCursor.getInt(1);//get the second image's drawable folder reference
			dbCursor.moveToNext();
			drawable3Resource=dbCursor.getInt(1);//get the third image's drawable folder reference
		}
	//method set image drawables	
		Drawable drawable1=getResources().getDrawable(drawable1Resource);
		Drawable drawable2=getResources().getDrawable(drawable2Resource);
		Drawable drawable3=getResources().getDrawable(drawable3Resource);
		img1.setImageDrawable(drawable1);
		img2.setImageDrawable(drawable2);
		img3.setImageDrawable(drawable3);
		
	//method get ratings from db
	if(dbCursor.moveToFirst()){
		rating1=dbCursor.getFloat(2);
		dbCursor.moveToNext();
		rating2=dbCursor.getFloat(2);
		dbCursor.moveToNext();
		rating3=dbCursor.getFloat(2);
	}
	//method set ratings
		ratingbar1.setRating(rating1);
		ratingbar2.setRating(rating2);
		ratingbar3.setRating(rating3);
		
		 db.close();
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
