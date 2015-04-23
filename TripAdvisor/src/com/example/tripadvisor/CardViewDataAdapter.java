package com.example.tripadvisor;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CardViewDataAdapter extends RecyclerView.Adapter<CardViewDataAdapter.ViewHolder> {
	public String[] mDataset;
	DataBaseHelper dbh;
	static Context cont;

	

	// Provide a suitable constructor (depends on the kind of dataset)
	public CardViewDataAdapter(String[] myDataset, DataBaseHelper db,Context context ) {
		mDataset = myDataset;
		dbh=db;
		Log.i("dbh=db",dbh.getDatabaseName().toString()) ;
		cont=context;
	}

	// Create new views (invoked by the layout manager)
	@Override
	public CardViewDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
			int viewType) {
		// create a new view
		View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.cardview_row,parent,false);

		// create ViewHolder

		ViewHolder viewHolder = new ViewHolder(itemLayoutView);
		return viewHolder;
	}

	// Replace the contents of a view (invoked by the layout manager)
	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {

		// - get data from your itemsData at this position
		// - replace the contents of the view with that itemsData
		String st= mDataset[position].toString();

		viewHolder.tvtinfo_text.setText(st);
		
		
		
		     if(st.equals("NEW DELHI"))
	    	 viewHolder.bgimg.setImageResource(R.drawable.newdelhi);
		     if(st.equals("BANGALORE"))
		     viewHolder.bgimg.setImageResource(R.drawable.abangalore);
		
		//viewHolder.bgimg.setBackgroundResource(R.drawable.newdelhi);
		
		Cursor c = dbh.getInterest(st);
		int I_length=c.getCount();
		Log.i("cursor c",Integer.toString(c.getCount()));
		if( c != null && c.moveToFirst());
		{
		Log.i("cursor c",Integer.toString(c.getCount()));
		for(int i=0;i<I_length;i++)
		{
			if(i==0)
		viewHolder.I1.setText(c.getString(0));
			if(i==1)
				viewHolder.I2.setText(c.getString(0));
			if(i==2)
				viewHolder.I3.setText(c.getString(0));
			if(i==3)
				viewHolder.I4.setText(c.getString(0));
		c.moveToNext();
		}

		}
		c.close(); 
		
		Cursor c1 = dbh.getPartner(st);
		int P_length=c1.getCount();
		 
		
		if(c1!=null && c1.moveToFirst());
		{
			Log.i("cursor c1",Integer.toString(c1.getCount()));
			
			for(int i=I_length;i<P_length+I_length;i++)
			{
				if(i==0)
			    viewHolder.I1.setText(c1.getString(0));
				if(i==1)
					viewHolder.I2.setText(c1.getString(0));
				if(i==2)
					viewHolder.I3.setText(c1.getString(0));
				if(i==3)
					viewHolder.I4.setText(c1.getString(0));
			c1.moveToNext();
			}
			
		}
		
		c1.close();

	}

	// Return the size of your dataset (invoked by the layout manager)
	@Override
	public int getItemCount() {
		return mDataset.length;
	}

	// inner class to hold a reference to each item of RecyclerView
	public static class ViewHolder extends RecyclerView.ViewHolder {

		public TextView tvtinfo_text;
		public TextView I1;
		public TextView I2;
		public TextView I3;
		public TextView I4;
		public ImageView bgimg;

		public ViewHolder(View itemLayoutView) {
			super(itemLayoutView);
			tvtinfo_text = (TextView) itemLayoutView
					.findViewById(R.id.info_text);
			
			I1= (TextView) itemLayoutView.findViewById(R.id.t1);
			
			I2=(TextView) itemLayoutView.findViewById(R.id.t2);
			
			I3=(TextView) itemLayoutView.findViewById(R.id.t3);
			
			I4=(TextView) itemLayoutView.findViewById(R.id.t4);
			
			bgimg=(ImageView)itemLayoutView.findViewById(R.id.stateImage);
			
			final Context co = itemLayoutView.getContext();
			
			
			itemLayoutView.setOnClickListener(new OnClickListener() {

			    @Override
			    public void onClick(View v) {

			     //Toast.makeText(v.getContext(), "OnClick Version :" + versionName,
			       //Toast.LENGTH_SHORT).show();
			    	
			    	Intent i= new Intent(co,StateInfo.class);
                    co.startActivity(i);
                //    i.putExtra("state_id","NEW DELHI");
			    }
			   });
			

		}
	}

}
