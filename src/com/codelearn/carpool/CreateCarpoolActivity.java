package com.codelearn.carpool;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import tasks.CreateCarpoolTask;

import models.Carpool;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.gson.Gson;

public class CreateCarpoolActivity extends Activity {
	EditText _phoneNo;
	EditText _location;
	TimePicker _stime;
	TimePicker _etime;
	Button _submit;
	Gson gson;
	Context activity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_carpool);
		activity =this.getApplicationContext();
		gson = new Gson();
		_phoneNo = (EditText) findViewById(R.id.fld_phn);
		_location = (EditText) findViewById(R.id.fld_location);
		_stime = (TimePicker) findViewById(R.id.fld_stime);
		_etime = (TimePicker) findViewById(R.id.fld_etime);
		_submit = (Button) findViewById(R.id.carpool_submit);
		
		_submit.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
			Carpool cp = new Carpool();
			cp.phone = _phoneNo.getText().toString();
			cp.location = _location.getText().toString();
			cp.stime = parseTime(_stime);
			cp.etime = parseTime(_etime);
			String json = gson.toJson(cp, Carpool.class);
			(new CreateCarpoolTask(activity)).execute(json);
				
			}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_carpool, menu);
		return true;
	}
	
	 public String parseTime(TimePicker tp){
		 String s;
		 Format formatter;
		 Calendar calendar = Calendar.getInstance();
		 calendar.set(Calendar.HOUR_OF_DAY, tp.getCurrentHour());
		 calendar.set(Calendar.MINUTE, tp.getCurrentMinute());

		 formatter = new SimpleDateFormat("HH:mm:00");
		 s = formatter.format(calendar.getTime());
		 return s;
	 }

}
