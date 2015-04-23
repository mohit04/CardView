package com.example.tripadvisor;

import java.io.IOException;

import android.app.Activity;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

public class Discover_Places extends Activity{
	
	RecyclerView mRecyclerView;
	
	String[] myDataset = { "NEW DELHI","BANGALORE"};
	
	private RecyclerView.Adapter mAdapter;
	private RecyclerView.LayoutManager mLayoutManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.discover_places);
		
		mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

		// use this setting to improve performance if you know that changes
				// in content do not change the layout size of the RecyclerView
				mRecyclerView.setHasFixedSize(true);

				// use a linear layout manager
				mLayoutManager = new LinearLayoutManager(this);
				mRecyclerView.setLayoutManager(mLayoutManager);
				mRecyclerView.setItemAnimator(new DefaultItemAnimator());

				 DataBaseHelper myDbHelper = new DataBaseHelper(this);
				 
				 try {
					 
			        	myDbHelper.createDataBase();
			 
			 	} catch (IOException ioe) {
			        Log.i("createDataBase","unable to create database");
			 		throw new Error("Unable to create database");
			 
			 	}
				 
				 Log.i("beforeOpenDatabase",myDbHelper.getDatabaseName().toString());
			 
				 try {
					 
				 		myDbHelper.openDataBase();
				 
				 	}catch(SQLException sqle){
				        Log.i("opendataBase","unable to open database");
				 		throw sqle;
				 	}
					
				 
				 
				// specify an adapter (see also next example)
				mAdapter = new CardViewDataAdapter(myDataset,myDbHelper,this);
				mRecyclerView.setAdapter(mAdapter);
	  //  mAdapter = new CountryAdapter(CountryManager.getInstance().getCountries(), R.layout.row_country, this);
	  //  mRecyclerView.setAdapter(mAdapter);
		
		
	 
	       
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}
	
	

}
