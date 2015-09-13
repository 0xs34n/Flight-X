package csc207.group0388.piii;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/** A class representation of the admin menu. */
public class Menu_Admin extends Activity {
	

	private String email;
	private String check;
	/**
	 * Calls when the activity is first created.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu__admin);
		Intent intent = getIntent();
		email = (String) intent.getSerializableExtra(MainActivity.USER);
		check = (String) intent.getSerializableExtra(MainActivity.CHECK_ADMIN);

		
	}
	
	/**
	 * Switches to the search flight activity if the button is clicked.
	 * @param view
	 */
	public void searchFlights(View view){
		Intent intent = new Intent(this, Search_FlightActivity.class);
		intent.putExtra(MainActivity.USER, email);
		intent.putExtra(MainActivity.CHECK_ADMIN, check);
		startActivity(intent);
	}
	
	/**
	 * Switches to the search itineraries activity if the button is clicked.
	 * @param view
	 */
	public void searchItineraries(View view){
		Intent intent = new Intent(this, Search_ItineraryActivity.class);
		intent.putExtra(MainActivity.USER, email);
		intent.putExtra(MainActivity.CHECK_ADMIN, check);
		startActivity(intent);
		
	}
	
	/**
	 * Switches to the view client list activity if the button is clicked.
	 * @param view
	 */
	public void viewClientList(View view){
		Intent intent = new Intent(this, View_ClientsActivity.class);
		intent.putExtra(MainActivity.USER, email);
		startActivity(intent);
	}
	
	/**
	 * Switches to the upload flight activity if the button is clicked.
	 * @param view
	 */
	public void uploadFlightList(View view){
		Intent intent = new Intent(this, Upload_Flight_ListActivity.class);
		intent.putExtra(MainActivity.USER, email);
		intent.putExtra(MainActivity.CHECK_ADMIN, check);
		startActivity(intent);	
	}
	
	public void viewPersonalInfo(View view){
		Intent intent = new Intent(this, View_Personal_InfoActivity.class);
		intent.putExtra(MainActivity.USER, email);
		intent.putExtra(MainActivity.CHECK_ADMIN, check);
		startActivity(intent);
	}
	
	/**
	 * Switches back to the main activity if the button is clicked.
	 * @param view
	 */
	public void logOut(View view){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	
	/**
	 * Switches to the booked itineraries activity if the button is clicked.
	 * @param view
	 */
	public void viewBookedItineraries(View view){
		Intent intent = new Intent(this, View_Booked_ItinerariesActivity.class);
		intent.putExtra(MainActivity.USER, this.email);
		intent.putExtra(MainActivity.CHECK_ADMIN, check);
		startActivity(intent);
	}
	
	/**
	 * Edits the chosen flight.
	 * @param view
	 */
	public void editFlight(View view){
		
		
		 new AlertDialog.Builder(Menu_Admin.this )
         .setTitle( "Edit Flight" )
         .setMessage( "Do you want to edit by searching a specific "
         		+ "flight or viewing all flights?")
         .setPositiveButton( "Search Flight", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int which) {
                 Log.d( "AlertDialog", "serach" );
                Intent intent = new Intent(Menu_Admin.this, 
                		Edit_Given_FlightActivity.class);
         		intent.putExtra(MainActivity.USER, email);
         		startActivity(intent);
             }
         })

        .setNeutralButton( "View All", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int which) {
            	 Log.d( "AlertDialog", "view" );
            	Intent intent = new Intent(Menu_Admin.this, 
            			 View_FlightListActivity.class);
         		intent.putExtra(MainActivity.USER, email);
         		startActivity(intent);
               
             }
         } )
         .setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int which) {
                 Log.d( "AlertDialog", "cancel" );
             }
         } )
         .show();
	}
	
}
