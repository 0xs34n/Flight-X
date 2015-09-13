package csc207.group0388.piii;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import App.Administrator;
import App.Flight;
import Managers.FlightManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

/** A class representation of the edit given flight activity. */
public class Edit_Given_FlightActivity extends Activity {
	

	private String email;
	private Administrator admin;
	
	/**
	 * Calls when the activity is first created.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit__given__flight);
		Intent intent = getIntent();
		this.email = (String) intent.getSerializableExtra(MainActivity.USER);
		this.admin = (Administrator)MainActivity.am.getAdmin(email);

	}
	
	/**
	 * Searches the current available flights.
	 * @param view
	 * @throws FileNotFoundException an exception discarded if the file is not found.
	 */
	public void searchFlights(View view) throws FileNotFoundException{
		Intent intent = new Intent(this, Edit_FlightActivity.class);
        EditText flightNum = (EditText) findViewById(R.id.editFlightNum);
        String number = flightNum.getText().toString();
        intent.putExtra(View_FlightListActivity.EDIT_FLIGHT, 
        		MainActivity.fm.getFlight(number));
        intent.putExtra(View_FlightListActivity.EDIT_FLIGHT, 
        		MainActivity.fm.getFlight(number));
        intent.putExtra(MainActivity.USER,admin);
        
		startActivity(intent);
		
	}
	public void home(View view){
		Intent intent = new Intent(this, Menu_Admin.class);
		intent.putExtra(MainActivity.USER, this.email);
		intent.putExtra(MainActivity.CHECK_ADMIN, "true");
		startActivity(intent);
	}
}
