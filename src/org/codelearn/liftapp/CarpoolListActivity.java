package org.codelearn.liftapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class CarpoolListActivity extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carpool_list);

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.carpool_list, menu);
		return true;
	}

	
}
