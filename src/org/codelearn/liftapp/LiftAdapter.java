package org.codelearn.liftapp;

import java.util.List;

import org.codelearn.liftapp.models.Lift;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class LiftAdapter extends ArrayAdapter<Lift> {
	
	 private LayoutInflater inflater;
	 private List<Lift> carpoolLocal;
		
	  public LiftAdapter(Activity activity, List<Lift> carpools){
	         super(activity, R.layout.row_lift, carpools);
	         inflater = activity.getWindow().getLayoutInflater();
	         carpoolLocal = carpools;
	     }
	     
	     public LiftAdapter(Activity activity){
	         super(activity, R.layout.row_lift);
	         inflater = activity.getWindow().getLayoutInflater();
	       
	     }
	     
	     @Override
	     public View getView(int position, View convertView, ViewGroup parent){
	        
	    	 View row = inflater.inflate(R.layout.row_lift, parent, false);
	         TextView title = (TextView) row.findViewById(R.id.carpoolTitle);
	         Lift carpool = carpoolLocal.get(position);
	         title.setText(carpool.location);
	         TextView stime = (TextView) row.findViewById(R.id.stime_entry);
	         TextView etime = (TextView) row.findViewById(R.id.etime_entry);
	         stime.setText(carpool.stime);
	         etime.setText(carpool.etime);
	         return row;
	     }

}