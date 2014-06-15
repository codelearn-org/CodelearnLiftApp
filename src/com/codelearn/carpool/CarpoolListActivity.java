package com.codelearn.carpool;

import java.util.List;

import models.Carpool;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;

public class CarpoolListActivity extends ListActivity {
	private CarpoolAdapter carpoolItemArrayAdapter;
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
	
	public void renderList(List<Carpool> cp){
		carpoolItemArrayAdapter = new CarpoolAdapter(this, cp);
		setListAdapter(carpoolItemArrayAdapter);
	}

}
