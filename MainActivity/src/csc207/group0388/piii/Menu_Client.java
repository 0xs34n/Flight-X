package csc207.group0388.piii;

import App.Client;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

/** A class representation of the client menu. */
public class Menu_Client extends Activity {
	
	public Client client;
	private String email;
	private String check;
	
	/**
	 * Calls when the activity is first created.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu__client);
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
	 * Switches to the view personal information activity if the button is clicked.
	 * @param view
	 */
	public void viewPersonalInfo(View view){
		Intent intent = new Intent(this, View_Personal_InfoActivity.class);
		intent.putExtra(MainActivity.USER, email);
		intent.putExtra(MainActivity.CHECK_ADMIN, check);
		startActivity(intent);
	}
	
	/**
	 * Switches to the view booked itineraries if the button is clicked.
	 * @param view
	 */
	public void viewBookedItineraries(View view){
		Intent intent = new Intent(this, View_Booked_ItinerariesActivity.class);
		intent.putExtra(MainActivity.USER, this.email);
		intent.putExtra(MainActivity.CHECK_ADMIN, this.check);
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
}
