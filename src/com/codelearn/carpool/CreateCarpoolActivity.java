package com.codelearn.carpool;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class CreateCarpoolActivity extends Activity {
	EditText _phoneNo;
	EditText _location;
	TimePicker _stime;
	TimePicker _etime;
	Button _submit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_carpool);
		_phoneNo = (EditText) findViewById(R.id.fld_phn);
		_location = (EditText) findViewById(R.id.fld_location);
		_stime = (TimePicker) findViewById(R.id.fld_stime);
		_etime = (TimePicker) findViewById(R.id.fld_etime);
		_submit = (Button) findViewById(R.id.carpool_submit);
		_submit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				
			}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_carpool, menu);
		return true;
	}

}
