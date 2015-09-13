package csc207.group0388.piii;

import java.io.IOException;
import java.util.ArrayList;

import App.Administrator;
import App.Client;
import App.Flight;
import App.Itinerary;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/** A class representation of the upload flight list activity. */
public class View_Booked_ItinerariesActivity extends Activity {

	private String email;
	private Client client;
	private Administrator admin;
	private String check;
	private int itemPosition;
	ListView listView ;
	private ArrayList<Itinerary> booked;
	
	/**
	 * Calls when the activity is first created.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view__booked__itineraries);
		listView = (ListView) findViewById(R.id.booked_itineraries);
		Intent intent = getIntent();
		
		this.email = (String) intent.getSerializableExtra(MainActivity.USER);
		this.check = (String) intent.getSerializableExtra(MainActivity.CHECK_ADMIN);
		if(check.equals("true")){
			admin = MainActivity.am.getAdmin(email);
			booked = admin.checkBookedItinerary();
			
		}
		else{
			client = MainActivity.cm.getClient(this.email);
			booked = client.checkBookedItinerary();
		}
		

		String[] values = new String[booked.size()];
		for(int i = 0; i<booked.size(); i++){
			values[i] = booked.get(i).toString();
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, values);
		
		 // Assign adapter to ListView
		 listView.setAdapter(adapter); 
		 listView.setOnItemClickListener(new OnItemClickListener() {

	         public void onItemClick(AdapterView<?> parent, View view,
	            int position, long id) {
	           
	          // ListView Clicked item index
	          itemPosition  = position;
	          
	          // ListView Clicked item value
	          //String  itemValue = (String) listView.getItemAtPosition(position);
	         
	           // Show Alert 
	          new AlertDialog.Builder(View_Booked_ItinerariesActivity.this )
	          .setTitle( "Remove Booked Flight" )
	          .setMessage( "Do you want to remove Itinerary from your Itinerary list?")
	          
	          .setPositiveButton( "No", new DialogInterface.OnClickListener() {
	              public void onClick(DialogInterface dialog, int which) {
	                  Log.d( "AlertDialog", "cancel" );
	              }
	          } )
	          .setNegativeButton( "Yes", new DialogInterface.OnClickListener() {
	              public void onClick(DialogInterface dialog, int which) {
	                  Log.d( "AlertDialog", "edit" );
	                 if(check.equals("true")){
	                	for(Flight flight : MainActivity.am.getAdmin(email)
		                			.checkBookedItinerary().get(itemPosition)
		                			.itinerary){
		 	                	MainActivity.fm.getFlight(flight.flightNum).cancelBook();
		 	                }
	                	 MainActivity.am.getAdmin(email).cancelItinerary(itemPosition);
	                	 try {
							MainActivity.saveFileAdmin();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                	
	                	try {
							MainActivity.saveFileFlight();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                	admin = MainActivity.am.getAdmin(email);
	                	booked = admin.checkBookedItinerary();
	                 }
	                 else{
	                	
	                	for(Flight flight : MainActivity.cm.getClient(email)
	                			.checkBookedItinerary().get(itemPosition)
	                			.itinerary){
		 	                	MainActivity.fm.getFlight(flight.flightNum).cancelBook();
		 	                }
	                	MainActivity.cm.getClient(email).cancelItinerary(itemPosition);
	                	
	                	
	 	                try {
	 						MainActivity.saveFileClient();
	 					} catch (ClassNotFoundException e) {
	 						// TODO Auto-generated catch block
	 						e.printStackTrace();
	 					} catch (IOException e) {
	 						// TODO Auto-generated catch block
	 						e.printStackTrace();
	 					}
	 	              
	 	               try {
						MainActivity.saveFileFlight();
	 	               } catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
	 	               } catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
	 	               }
	 	                client = MainActivity.cm.getClient(email);
		                booked = client.checkBookedItinerary(); 
	 	                
	                 }
	               
	                
	              	

	          		String[] values = new String[booked.size()];
	          		for(int i = 0; i<booked.size(); i++){
	          			values[i] = booked.get(i).toString();
	          		}
	          		
	          		listView = (ListView) findViewById(R.id.booked_itineraries);
	          		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
	          				View_Booked_ItinerariesActivity.this,
	          	              android.R.layout.simple_list_item_1, 
	          	              android.R.id.text1, values);
	          		 // Assign adapter to ListView
	          		listView.setAdapter(adapter); 
	              }
	          })

	          .show();
	          
	         }

	    });
	}
	
	
	/**
	 * Returns back to the menu of the client.
	 * @param view
	 */
	public void home(View view){
		if(check.equals("true")){
			Intent intent = new Intent(this, Menu_Admin.class);
			intent.putExtra(MainActivity.USER, this.email);
			intent.putExtra(MainActivity.CHECK_ADMIN, check);
			startActivity(intent);
		}
		else{
		Intent intent = new Intent(this, Menu_Client.class);
		intent.putExtra(MainActivity.USER, this.email);
		intent.putExtra(MainActivity.CHECK_ADMIN, check);
		startActivity(intent);}
	}
	
	/**
	 * Returns back to the search itinerary menu for the client.
	 * @param view
	 */
	public void keepBooking(View view){

		Intent intent = new Intent(this, Search_ItineraryActivity.class);
		intent.putExtra(MainActivity.USER, this.email);
		intent.putExtra(MainActivity.CHECK_ADMIN, check);
		startActivity(intent);
		
	}
	
}
