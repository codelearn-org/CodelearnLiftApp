package org.codelearn.liftapp;

import org.codelearn.liftapp.models.Carpool;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CarpoolDetailActivity extends Activity {
	Carpool value;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carpool_detail);
		value = (Carpool) getIntent().getSerializableExtra("MyClass");
		TextView cp_location = (TextView) findViewById(R.id.cp_location);
		TextView cp_stime = (TextView) findViewById(R.id.cp_stime);
		TextView cp_etime = (TextView) findViewById(R.id.cp_etime);
		TextView cp_phone = (TextView) findViewById(R.id.cp_phn);
		cp_location.setText(value.location);
		cp_stime.setText(value.stime);
		cp_etime.setText(value.etime);
		cp_phone.setText(value.phone);
		Button call = (Button) findViewById(R.id.call);
		Button message = (Button) findViewById(R.id.message);
		call.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				 Intent callIntent = new Intent(Intent.ACTION_CALL);          
		            callIntent.setData(Uri.parse("tel:"+value.phone));          
		            startActivity(callIntent);  
			}});

		message.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intentsms = new Intent( Intent.ACTION_VIEW, Uri.parse( "sms:" + value.phone ) );
	            startActivity( intentsms );  
			}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.carpool_detail_activty, menu);
		return true;
	}

}
