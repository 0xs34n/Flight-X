package csc207.group0388.piii;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import App.Client;
import Managers.FlightManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

/** A class representation of the search itinerary activity. */
public class Search_ItineraryActivity extends Activity {
	
	public static final String SEARCH_FLIGHT_RESULT = "resultKey";
	public static final String FILENAME = "Workbook.csv";
	
	
	public static final String USERDATADIR = "data";
	
	private DatePicker dpResult;

	 
	private String year;
	private String month;
	private String day;
	private String email;
	private String check;
	
	/**
	 * Calls when the activity is first created.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search__itinerary);
		Intent intent = getIntent();
		this.email = (String) intent.getSerializableExtra(MainActivity.USER);
		this.check = (String) intent.getSerializableExtra(MainActivity.CHECK_ADMIN);
		
	}
	
	/**
	 * Searches the itineraries from the origin and destination.
	 * @param view
	 * @throws FileNotFoundException an exception discarded if the file is not found.
	 */
	public void SearchItineraries(View view) throws FileNotFoundException{
		Intent intent = new Intent(this, Search_Itinerary_ResultActivity.class);
		
		// Converts date into a string.
		dpResult = (DatePicker) findViewById(R.id.datePicker1);
		year = Integer.toString(dpResult.getYear());
		month = Integer.toString(dpResult.getMonth()+1);
		day = Integer.toString(dpResult.getDayOfMonth());
		
		if(Integer.parseInt(day) < 10 && Integer.parseInt(month) < 10) {
			month = "0" + month;
			day = "0" + day;
		}
		
		else if(Integer.parseInt(day) < 10) {
			day = "0" + day;
		}
		
		else if(Integer.parseInt(month) < 10) {
			month = "0" + month;
		}
		
	    String date = year + "-" + month + "-" + day;
        System.out.println(date);
        
        // Gets the origin from the 1st EditText field.
        EditText Origin = (EditText) findViewById(R.id.Origin_itinerary);
        String origin = Origin.getText().toString();
        
        // Gets the destination from the 2nd EditText field.
        EditText Destination = (EditText) findViewById(R.id.Destination_itinerary);
        String destination = Destination.getText().toString();
        intent.putExtra(SEARCH_FLIGHT_RESULT, 
        		MainActivity.fm.getItineraries(date, origin, destination));
        intent.putExtra(MainActivity.USER, email);
		intent.putExtra(MainActivity.CHECK_ADMIN, check);
		
		System.out.println(MainActivity.fm.getItineraries(date, origin, destination));
		startActivity(intent);
	}
}
