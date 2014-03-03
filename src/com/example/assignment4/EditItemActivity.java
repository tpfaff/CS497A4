package com.example.assignment4;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;

public class EditItemActivity extends Activity {
	EditText textField=null;
	RatingBar ratingBar=null;
	SQLiteDatabase db=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_item_layout);
		textField=(EditText)findViewById(R.id.title);
		ratingBar=(RatingBar)findViewById(R.id.image_rating_bar);
	}

}
