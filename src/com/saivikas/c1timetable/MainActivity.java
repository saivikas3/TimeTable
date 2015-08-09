package com.saivikas.c1timetable;



import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	Cursor c=null;
	DBHelper myDbHelper;
	private TextView intro;
	private RadioGroup rg;
	byte idval;
	static boolean flag = false;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		intro = (TextView) findViewById(R.id.introtext);
		rg = (RadioGroup) findViewById(R.id.radioGroup1);
		
		//display toast
				Context context = getApplicationContext();
				CharSequence text = "App Developed By Sai Vikas";
				int duration = 5;

				Toast toast = Toast.makeText(context, text, duration);
				if(!flag) {
					toast.show();
					flag = true;
				}
		
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup rg1, int checkedId) {
				switch(checkedId) {
				case R.id.mon: idval = 1;
							  
							   break;	
				case R.id.tue: idval = 2;
				              
							   break;
				case R.id.wed: idval = 3;
							  
					           break;
				case R.id.thu: idval = 4;
							  
					           break;
				case R.id.fri: idval = 5;
				               break;
				case R.id.sat: idval = 6;
							   break;
				}
		
				Intent nextClass = new Intent(MainActivity.this, Second.class);
				nextClass.putExtra("the_id", idval);
				
				startActivity(nextClass);
			}
		});
		
		
		
		intro.setText("Hey there, Welcome back! Which day's time table do you want to know?");
		
		
		
		
	}
	

	@Override
	public void onBackPressed() {
		//close the app
		
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
	    alertDialogBuilder.setMessage("Do you want to exit?");
	    alertDialogBuilder.setPositiveButton("No", 
	      new DialogInterface.OnClickListener() {
			
	         @Override
	         public void onClick(DialogInterface arg0, int arg1) {
	            startActivity(new Intent(MainActivity.this, MainActivity.class));
				finish();
	         }
	      });
	      
	      alertDialogBuilder.setNegativeButton("Yes", 
	      new DialogInterface.OnClickListener() {
				
	         @Override
	         public void onClick(DialogInterface dialog, int which) {
	        	 Intent homeIntent = new Intent(Intent.ACTION_MAIN);
	     	     homeIntent.addCategory( Intent.CATEGORY_HOME );
	     	     homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
	     	     startActivity(homeIntent);
	     	     finish();
	     	    
			 }
	      });
		    
	      AlertDialog alertDialog = alertDialogBuilder.create();
	      alertDialog.show();
		
		
		
		
		
	}
}