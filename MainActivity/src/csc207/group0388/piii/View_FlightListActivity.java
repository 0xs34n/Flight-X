package csc207.group0388.piii;

import java.util.ArrayList;
import java.util.Map;

import App.Administrator;
import App.Client;
import App.Flight;
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
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/** A class representation of the search flight result activity. */
public class View_FlightListActivity extends Activity {

	public static final String EDIT_FLIGHT = "edit_flight";
	
	Administrator admin;
	private String email;
	ListView listView ;

	ArrayAdapter<String> adapter;
	protected FlightManager fm;
	protected ArrayList<Flight> flights;
	private int itemPosition;
	
	/**
	 * Calls when the activity is first created.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view__flight_list);
		Intent intent = getIntent();
		this.email = (String) intent.getSerializableExtra(MainActivity.USER);
		this.admin = MainActivity.am.getAdmin(email);
		listView = (ListView) findViewById(R.id.listFlights);
		
		fm = MainActivity.fm;

		flights = new ArrayList<Flight>();
		for(Map.Entry<String, Flight> entry: fm.getFlights().entrySet()){
			flights.add(entry.getValue());
 		}
		String[] values = new String[flights.size()];
		for(int i = 0; i< flights.size(); i++){
			values[i] = flights.get(i).toString();
		}
		
		
		adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, values);
		
		 // Assign adapter to ListView
        listView.setAdapter(adapter); 
        //final Dialog dialog = new Dialog(this);
        // ListView Item Click Listener
        listView.setOnItemClickListener(new OnItemClickListener() {

              public void onItemClick(AdapterView<?> parent, View view,
                 int position, long id) {
                
               // ListView Clicked item index
               itemPosition     = position;
               
               // ListView Clicked item value
               String  itemValue    = (String) listView.getItemAtPosition(position);
              
                // Show Alert 
               
               new AlertDialog.Builder(View_FlightListActivity.this )
               .setTitle( "Edit Flight" )
               .setMessage( "Do you want edit this flight:"+"\n" + itemValue + "?")
               .setPositiveButton( "Yes", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {
                       Log.d( "AlertDialog", "Positive" );
                    Intent intent = new Intent(View_FlightListActivity.this, 
                    		Edit_FlightActivity.class);
               		intent.putExtra(EDIT_FLIGHT, flights.get(itemPosition));
               		intent.putExtra(MainActivity.CHECK_ADMIN, "true");
               		intent.putExtra(MainActivity.USER, admin);
               		startActivity(intent);
                   }
               })
               .setNegativeButton( "No", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {
                       Log.d( "AlertDialog", "Negative" );
                   }
               } )
               .show();
               
              }

         }); 
	}
	public void home(View view){
		Intent intent = new Intent(this, Menu_Admin.class);
		intent.putExtra(MainActivity.USER, this.email);
		intent.putExtra(MainActivity.CHECK_ADMIN, "true");
		startActivity(intent);
	}

}
