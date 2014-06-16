package com.codelearn.carpool;

import models.Carpool;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CarpoolDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carpool_detail);
		Carpool  value = (Carpool) getIntent().getSerializableExtra("MyClass");
		TextView cp_location = (TextView) findViewById(R.id.cp_location);
		TextView cp_stime = (TextView) findViewById(R.id.cp_stime);
		TextView cp_etime = (TextView) findViewById(R.id.cp_etime);
		TextView cp_phone = (TextView) findViewById(R.id.cp_phn);
	}


}
