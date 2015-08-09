package com.saivikas.c1timetable;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Second extends Activity {
	
	byte idval,temp;
	int randomNum;
	Cursor c;
	TextView ct,ttl;
	DBHelper myDbHelper;
	Button back;
	ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		
		back = (Button) findViewById(R.id.menu);
		idval = getIntent().getExtras().getByte("the_id");
		ttl = (TextView) findViewById(R.id.thetitle);
		ct = (TextView) findViewById(R.id.comment);		
		lv = (ListView) findViewById(R.id.listView1);
		
		//setting title text
		switch(idval) {
		case 1: ttl.setText("Monday");  break;
		case 2: ttl.setText("Tuesday");  break;
		case 3: ttl.setText("Wednesday");  break;
		case 4: ttl.setText("Thursday");  break;
		case 5: ttl.setText("Friday");  break;
		case 6: ttl.setText("Saturday");  break;
		}
		
		
		//add the working of back button
		back.setOnClickListener(new OnClickListener()  {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Second.this,MainActivity.class));
			}
		});
			
		//start database works
		connectToDatabase();
		
		//setting comment
		setComment();
		
		//creating an array list of items
		List<String> myList = new ArrayList<String>();
		
		//populating the list
		c = myDbHelper.rawquery("select * from subjects where ID="+Byte.toString(idval), null);
		c.moveToFirst();
		
		myList.add("1. "+c.getString(2));
		myList.add("2. "+c.getString(3));
		myList.add("3. "+c.getString(4));
		myList.add("4. "+c.getString(5));
		myList.add("5. "+c.getString(6));
		myList.add("6. "+c.getString(7));
		myList.add("7. "+c.getString(8));
		myList.add("8. "+c.getString(9));
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,myList);
		lv.setAdapter(adapter);
	}
	
	private void connectToDatabase() {
    	myDbHelper = new DBHelper(Second.this);
        try {
 
        	myDbHelper.createDataBase();
 
        } catch (IOException ioe) {
 
        	throw new Error("Unable to create database");
 
        }	
 
        try {
 
        	myDbHelper.openDataBase();
 
        }catch(SQLException sqle){
 
        	throw sqle;
 
        }
    }

	public void setComment()
	{
		SecureRandom rand = new SecureRandom(); 
		randomNum =  1 + rand.nextInt(2);
		try {
			c = myDbHelper.rawquery("select comment"+randomNum+" from subjects where ID="+Byte.toString(idval),null);
		} catch(Exception e) {
			Toast.makeText(getApplicationContext(), e.toString(), 5).show();
		}
		c.moveToFirst();
		ct.setText(c.getString(0));
	}	
}
