package csc207.group0388.piii;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import Managers.FlightManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

/** A class representation of the search flight result activity. */
public class Search_FlightActivity extends Activity {
	
	public static final String SEARCH_FLIGHT_RESULT = "resultKey";
	public static final String FILENAME = "Workbook.csv";
	public static final String USERDATADIR = "data";
	

	private DatePicker dpResult;

	private String email;
	private String check;
	private String year;
	private String month;
	private String day;
	

	/**
	 * Calls when the activity is first created.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search__flight);
		Intent intent = getIntent();
		this.email = (String) intent.getSerializableExtra(MainActivity.USER);
		this.check = (String) intent.getSerializableExtra(MainActivity.CHECK_ADMIN);
	}
	
	/**
	 * Searches the flight from the origin and destination.
	 * @param view
	 * @throws FileNotFoundException an exception discarded if the file is not found.
	 */
	public void searchFlights(View view) throws FileNotFoundException{
		Intent intent = new Intent(this, Search_Flight_ResultActivity.class);
		
		// Converts date into a string.
		dpResult = (DatePicker) findViewById(R.id.datePicker);
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
        EditText Origin = (EditText) findViewById(R.id.origin);
        String origin = Origin.getText().toString();
        
        // Gets the destination from the 2st EditText field.
        EditText Destination = (EditText) findViewById(R.id.destination);
        String destination = Destination.getText().toString();
        intent.putExtra(SEARCH_FLIGHT_RESULT,MainActivity.
        		fm.getFlightItinerary(date, origin, destination));
        intent.putExtra(MainActivity.USER, email);
		intent.putExtra(MainActivity.CHECK_ADMIN, check);
        System.out.println(MainActivity.fm.getFlightItinerary(date, origin, destination));
		startActivity(intent);
		
	}
	
}
