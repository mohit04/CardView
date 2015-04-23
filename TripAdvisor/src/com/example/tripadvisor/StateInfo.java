package com.example.tripadvisor;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StateInfo extends Activity {

	String str;
	String url[]={"http://www.delhitourism.gov.in/delhitourism/itinerary/one_day_itinerary.jsp"};
	  private static String file_url ;
	    public int pos=0;
	    private ProgressDialog pDialog;
	    Button bt;
	       public static final int progress_bar_type = 0; 
	       public String[] sdfiles={"/sdcard/JIITBrochure2014.pdf","/sdcard/AdmissionAnnouncementAdJIIT2014.pdf","/sdcard/ACADEMICCALENDAR201415.pdf"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    //str=savedInstanceState.getString("state_id");
	    setContentView(R.layout.state_info);
	   TextView tv = (TextView) findViewById(R.id.textView1);
	   tv.setText("NEW DELHI");
	   bt = (Button) findViewById(R.id.itinerary);
	   bt.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			AlertDialog.Builder builder = new AlertDialog.Builder(StateInfo.this);
			builder.setItems(R.array.itinerary, mDialogListener);
			AlertDialog dialog = builder.create();
			dialog.show();
		}
	});
	}

	
	
	
	protected DialogInterface.OnClickListener mDialogListener = new DialogInterface.OnClickListener() 
	{
		
	public void onClick(DialogInterface arg0, int arg1) {
	
		if(arg1==0)
		{
			 file_url = url[arg1];
		}
		
		if(arg1==1)
		{
			file_url = url[arg1];
		}
		
		if(arg1==2)
		{
			file_url = url[arg1];
		}
		new DownloadFileFromURL().execute(file_url);
	}
	};
	
	
	@Override
	protected Dialog onCreateDialog(int id) {
	    switch (id) {
	    case progress_bar_type:
	        pDialog = new ProgressDialog(this);
	        pDialog.setMessage("Downloading file. Please wait...");
	        pDialog.setIndeterminate(false);
	        pDialog.setMax(100);
	        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	        pDialog.setCancelable(true);
	        pDialog.show();
	        return pDialog;
	    default:
	        return null;
	    }
	}
	
	
	class DownloadFileFromURL extends AsyncTask<String, String, String> {
		 
	    /**
	     * Before starting background thread
	     * Show Progress Bar Dialog
	     * */
	    @Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	        showDialog(progress_bar_type);
	    }
	 
	    /**
	     * Downloading file in background thread
	     * */
	    @Override
	    protected String doInBackground(String... f_url) {
	        int count;
	        try {
	            URL url = new URL(f_url[0]);
	            URLConnection conection = url.openConnection();
	            conection.connect();
	            // getting file length
	            int lenghtOfFile = conection.getContentLength();
	 
	            // input stream to read file - with 8k buffer
	            InputStream input = new BufferedInputStream(url.openStream(), 8192);
	            String file=sdfiles[pos];
	            // Output stream to write file
	            OutputStream output = new FileOutputStream(file);
	 
	            byte data[] = new byte[1024];
	 
	            long total = 0;
	 
	            while ((count = input.read(data)) != -1) {
	                total += count;
	                // publishing the progress....
	                // After this onProgressUpdate will be called
	                publishProgress(""+(int)((total*100)/lenghtOfFile));
	 
	                // writing data to file
	                output.write(data, 0, count);
	            }
	 
	            // flushing output
	            output.flush();
	 
	            // closing streams
	            output.close();
	            input.close();
	 
	        } catch (Exception e) {
	            Log.e("Error: ", e.getMessage());
	        }
	 
	        return null;
	    }
	 
	    /**
	     * Updating progress bar
	     * */
	    protected void onProgressUpdate(String... progress) {
	        // setting progress percentage
	        pDialog.setProgress(Integer.parseInt(progress[0]));
	   }
	 
	    /**
	     * After completing background task
	     * Dismiss the progress dialog
	     * **/
	    @Override
	    protected void onPostExecute(String file_url) {
	        // dismiss the dialog after the file was downloaded
	        dismissDialog(progress_bar_type);
	        
	        String file1=sdfiles[pos];
	 Intent intent=new Intent();
	    File file3=new File(file1);
	   intent.setDataAndType(Uri.fromFile(file3),"application/pdf");
	   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
       startActivity(intent);
	    }
	 
	}
	
}
