package com.codelearn.carpool;

import java.util.List;

import models.Carpool;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CarpoolAdapter extends ArrayAdapter<Carpool> {
	
	 private LayoutInflater inflater;
	 private List<Carpool> carpoolLocal;

	  public CarpoolAdapter(Activity activity, List<Carpool> carpools){
	         super(activity, R.layout.row_carpool, carpools);
	         inflater = activity.getWindow().getLayoutInflater();
	         carpoolLocal = carpools;
	     }
	     
	     public CarpoolAdapter(Activity activity,String[] str){
	         super(activity, R.layout.row_carpool);
	         inflater = activity.getWindow().getLayoutInflater();
	       
	     }
	     
	     @Override
	     public View getView(int position, View convertView, ViewGroup parent){
	         View row = inflater.inflate(R.layout.row_carpool, parent, false);
	         TextView title = (TextView) row.findViewById(R.id.carpoolTitle);
	         Carpool carpool = carpoolLocal.get(position);
	         title.setText(carpool.location);
	         TextView stime = (TextView) row.findViewById(R.id.stime_entry);
	         TextView etime = (TextView) row.findViewById(R.id.etime_entry);
	         stime.setText(carpool.stime);
	         etime.setText(carpool.etime);
	         return row;
	     }

}
