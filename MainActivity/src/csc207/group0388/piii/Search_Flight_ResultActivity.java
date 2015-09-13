package csc207.group0388.piii;

import java.util.ArrayList;

import App.Administrator;
import App.Flight;
import App.Itinerary;
import Managers.FlightManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/** A class representation of the search flight result activity. */
public class Search_Flight_ResultActivity extends Activity {
	

	private String email;
	private String check;
	private int itemPosition;
	private Administrator admin;
	private ArrayList<Itinerary> itineraries;
	private FlightManager fm = new FlightManager();
	ListView listView ;
	
	ArrayAdapter<String> adapter;
	
	/**
	 * Calls when the activity is first created.
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search__flight__result);
		Intent intent = getIntent();
		this.email = (String) intent.getSerializableExtra(MainActivity.USER);
		this.check = (String) intent.getSerializableExtra(MainActivity.CHECK_ADMIN);
		
		listView = (ListView) findViewById(R.id.list_itinerary_flight);
		itineraries= (ArrayList<Itinerary>)intent.getSerializableExtra
				(Search_FlightActivity.SEARCH_FLIGHT_RESULT);
		
		String[] values = new String[itineraries.size()];
		for(int i = 0; i<itineraries.size(); i++){
			values[i] = itineraries.get(i).itinerary.get(0).toString();

		}
		
		
		adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, values);
		
		 // Assign adapter to ListView
        listView.setAdapter(adapter); 
        if(check.equals("true")){
            listView.setOnItemClickListener(new OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view,
                   int position, long id) {
                  
                 // ListView Clicked item index
                 itemPosition     = position;
                 
                 // ListView Clicked item value
                 String  itemValue    = (String) listView.getItemAtPosition(position);
                
                  // Show Alert 
                 
                 new AlertDialog.Builder(Search_Flight_ResultActivity.this )
                 .setTitle( "Edit Flight" )
                 .setMessage( "Do you want tp edit flight:"+"\n" + itemValue + "?")
                 
                 .setPositiveButton( "Cancel", new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int which) {
                         Log.d( "AlertDialog", "cancel" );
                     }
                 } )
                 .setNegativeButton( "Edit", new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int which) {
                         Log.d( "AlertDialog", "edit" );
                        admin = MainActivity.am.getAdmin(email);
                        Flight flight = itineraries.get(itemPosition).itinerary.get(0);
                        Intent intent = new Intent(Search_Flight_ResultActivity.this, 
                        		Edit_FlightActivity.class);
                        intent.putExtra(View_FlightListActivity.EDIT_FLIGHT, flight);
                  		intent.putExtra(MainActivity.USER, admin);
                 		intent.putExtra(MainActivity.CHECK_ADMIN, check);
                 		startActivity(intent);
                     }
                 })

                 .show();
                 
                }

           }); 
        }
	}
	
	/**
	 * Sorts the flights by ascending order of time. 
	 * @param v
	 */
	public void sortByTime(View v){
		
		
		listView = (ListView) findViewById(R.id.list_itinerary_flight);
		fm.sortByTime(itineraries);
		String[] values = new String[itineraries.size()];
		for(int i = 0; i<itineraries.size(); i++){
			values[i] = itineraries.get(i).itinerary.get(0).toString();
		}
		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, values);
		
		 // Assign adapter to ListView
        listView.setAdapter(adapter); 
		
	}
	
	/**
	 * Sorts the flights by ascending order of cost of flight.
	 * @param v
	 */
	public void sortByCost(View v){
		
		listView = (ListView) findViewById(R.id.list_itinerary_flight);
		fm.sortByCost(itineraries);
		String[] values = new String[itineraries.size()];
		for(int i = 0; i<itineraries.size(); i++){
			values[i] = itineraries.get(i).itinerary.get(0).toString();
		}
		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, values);
		
		 // Assign adapter to ListView
        listView.setAdapter(adapter); 
		
	}
	
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
			startActivity(intent);
		}
		
	}
	
	
}
