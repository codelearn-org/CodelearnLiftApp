package org.codelearn.liftapp;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class CreateLiftActivity extends Activity {
	EditText _phoneNo;
	EditText _location;
	TimePicker _stime;
	TimePicker _etime;
	Button _submit;
	Editor edit;
	SharedPreferences prefs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_lift);
		Boolean value =  getIntent().getBooleanExtra("edit",false);
		 prefs =  getSharedPreferences("codelearn_liftapp", MODE_PRIVATE);
		 edit = prefs.edit();
		_phoneNo = (EditText) findViewById(R.id.fld_phn);
		_location = (EditText) findViewById(R.id.fld_location);
		_stime = (TimePicker) findViewById(R.id.fld_stime);
		_etime = (TimePicker) findViewById(R.id.fld_etime);
		_submit = (Button) findViewById(R.id.lift_submit);
		if(value){
			populateFields();
		}
		
		_submit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
			
			edit.putString("pref_key_phone", _phoneNo.getText().toString());
			edit.putString("pref_key_location", _location.getText().toString());
			edit.putString("pref_key_stime",  parseTime(_stime));
			edit.putString("pref_key_etime",  parseTime(_etime));
			edit.commit();
			}});
		
		
	}
	private void populateFields() {
		_phoneNo.setText(prefs.getString("pref_key_phone", ""));
		_location.setText(prefs.getString("pref_key_location", ""));
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Calendar c = Calendar.getInstance();
		try {
			Date date = sdf.parse(prefs.getString("pref_key_stime", "00:00"));
		    c.setTime(date);
		    _stime.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
		    _stime.setCurrentMinute(c.get(Calendar.MINUTE));
		    date = sdf.parse(prefs.getString("pref_key_etime", "00:00"));
		    c.setTime(date);
		    _etime.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
		    _etime.setCurrentMinute(c.get(Calendar.MINUTE));

		} catch (ParseException e) {


		}
		_submit.setText("Update Carpool");
	}

	 public String parseTime(TimePicker tp){
		 String s;
		 Format formatter;
		 Calendar calendar = Calendar.getInstance();
		 calendar.set(Calendar.HOUR_OF_DAY, tp.getCurrentHour());
		 calendar.set(Calendar.MINUTE, tp.getCurrentMinute());

		 formatter = new SimpleDateFormat("HH:mm");
		 s = formatter.format(calendar.getTime());
		
		 return s;
	 }
	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_carpool, menu);
		return true;
	}
}
