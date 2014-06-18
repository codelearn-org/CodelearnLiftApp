package org.codelearn.liftapp;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.codelearn.liftapp.models.Carpool;
import org.codelearn.liftapp.tasks.CreateCarpoolTask;
import org.codelearn.liftapp.tasks.EditCarpoolTask;

import android.app.Activity;
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
	Carpool value;
	CreateCarpoolTask ctask;
	EditCarpoolTask etask;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_carpool);
		gson = new Gson();
		value = (Carpool) getIntent().getSerializableExtra("MyClass");
		_phoneNo = (EditText) findViewById(R.id.fld_phn);
		_location = (EditText) findViewById(R.id.fld_location);
		_stime = (TimePicker) findViewById(R.id.fld_stime);
		_etime = (TimePicker) findViewById(R.id.fld_etime);
		_submit = (Button) findViewById(R.id.carpool_submit);
		if(value != null){
			populateFields(value);
		}
		ctask = new CreateCarpoolTask(this);
		etask = new EditCarpoolTask(this);
		
		_submit.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
			Carpool cp = new Carpool();
			cp.phone = _phoneNo.getText().toString();
			cp.location = _location.getText().toString();
			cp.stime = parseTime(_stime);
			cp.etime = parseTime(_etime);
			String json = gson.toJson(cp, Carpool.class);
			
			if(value!=null){
			etask.execute(json);	
			}
			else {
			ctask.execute(json);
			}
			
			}});
	}

	private void populateFields(Carpool value) {
		_phoneNo.setText(value.phone);
		_location.setText(value.location);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Calendar c = Calendar.getInstance();
		try {
			Date date = sdf.parse(value.stime);
		    c.setTime(date);
		    _stime.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
		    _stime.setCurrentMinute(c.get(Calendar.MINUTE));
		    date = sdf.parse(value.etime);
		    c.setTime(date);
		    _etime.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
		    _etime.setCurrentMinute(c.get(Calendar.MINUTE));
			
		} catch (ParseException e) {
		
			
		}
		_submit.setText("Update Carpool");
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

		 formatter = new SimpleDateFormat("HH:mm");
		 s = formatter.format(calendar.getTime());
		 return s;
	 }

}
