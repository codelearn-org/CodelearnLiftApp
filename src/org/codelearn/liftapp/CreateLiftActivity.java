package org.codelearn.liftapp;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.codelearn.liftapp.models.Lift;
import org.codelearn.liftapp.tasks.CreateLiftTask;
import org.codelearn.liftapp.tasks.EditLiftTask;

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

import com.google.gson.Gson;

public class CreateLiftActivity extends Activity {
	EditText _phoneNo;
	EditText _location;
	TimePicker _stime;
	TimePicker _etime;
	Button _submit;
	Editor edit;
	Boolean value = false;
	SharedPreferences prefs;
	Gson gson;
	CreateLiftTask ctask;
	EditLiftTask etask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_lift);
		Bundle extras = getIntent().getExtras();
		if(extras != null)
		value =  extras.getBoolean("edit");
		gson = new Gson();
		prefs =  getSharedPreferences("codelearn_liftapp", MODE_PRIVATE);
		 edit = prefs.edit();
		 ctask = new CreateLiftTask(this);
		 etask = new EditLiftTask(this);
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
			Lift cp = new Lift();
			cp.phone = _phoneNo.getText().toString();
			cp.location = _location.getText().toString();
			cp.stime = parseTime(_stime);
			cp.etime = parseTime(_etime);
			String json = gson.toJson(cp, Lift.class);

			if(value){
			etask.execute(json);	
			}
			else {
			ctask.execute(json);
			}
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
		_submit.setText("Update lift details");
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
