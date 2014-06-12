package com.codelearn.carpool;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class CreateCarpoolActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_carpool);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_carpool, menu);
		return true;
	}

}
