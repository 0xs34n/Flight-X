package csc207.group0388.piii;

import java.io.IOException;
import java.util.regex.Pattern;

import App.Administrator;
import App.Flight;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/** A class representation of the edit flight activity. */
public class Edit_FlightActivity extends Activity {
	
	private Administrator admin;
	private Flight flight;
	private String fnumber;
	
	/**
	 * Calls when the activity is created.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit__flight);
		Intent intent = getIntent();
		admin = (Administrator) intent.getSerializableExtra(MainActivity.USER);
        flight = (Flight) intent.getSerializableExtra(
        		View_FlightListActivity.EDIT_FLIGHT);
        fnumber = flight.flightNum;
        System.out.println(flight.currentBooked);
	}
	
	
	/**
	 * Processes the new flight information to be edited.
	 * @param view
	 */
	@SuppressLint("NewApi")
	public void submitInfo(View view) {
		
		// Gets the number from the 1st EditText field.
		EditText editNumber = (EditText) findViewById(R.id.editFlightNum);
		String number = editNumber.getText().toString();
		
        // Gets the departure date and time from the 2nd EditText field.
		EditText editDepartureDateTime = (EditText) 
				findViewById(R.id.editDepartDateTime);
		String departureDateTime = editDepartureDateTime.getText().toString();
		
        // Gets the arrival date and time from the 3rd EditText field.
		EditText editArrivalDateTime = (EditText) 
				findViewById(R.id.editArrivalDateTime);
		String arrivalDateTime = editArrivalDateTime.getText().toString();
		
        // Gets the airline from the 4th EditText field.
		EditText editAirline = (EditText) findViewById(R.id.editAirline);
		String airline = editAirline.getText().toString();
		
        // Gets the origin from the 5th EditText field.
		EditText editOrigin = (EditText) findViewById(R.id.editOrigin);
		String origin = editOrigin.getText().toString();
		
        // Gets the destination from the 6th EditText field.
		EditText editDestination = (EditText) 
				findViewById(R.id.editDestination);
		String destination = editDestination.getText().toString();
		
        // Gets the price from the 7th EditText field.
		EditText editPrice = (EditText) findViewById(R.id.editPrice);
		String price = editPrice.getText().toString();
		
        // Gets the seats from the 8th EditText field.
		EditText editSeats = (EditText) findViewById(R.id.numSeats);
		String numSeats = editSeats.getText().toString();
		
		System.out.println(flight.currentBooked);

		if(!numSeats.isEmpty() && (isValidSeats(numSeats))
				&&flight.currentBooked > Integer.parseInt(numSeats) 
			){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle( "Invalid Input" ).setMessage(
            		"Number of seat should "
            		+ "bigger than current book:"+flight.currentBooked )
                   .setNegativeButton("Ok", new DialogInterface.OnClickListener
                		   () {
                       public void onClick(DialogInterface dialog, int id) { 
                       }
                   });
            AlertDialog alert = builder.create();
            alert.show();	
		}
		// Checks if the specific fields are not empty.
		// If they are not empty, hence they need to be edited.
		// Then the edit flight method is called on the specific field.
		else if((isValidDateTime(arrivalDateTime)||arrivalDateTime.isEmpty())
				&&(isValidDateTime(departureDateTime)||departureDateTime
						.isEmpty())
				&&(isValidPrice(price)||price.isEmpty())
				&&(isValidSeats(numSeats)||numSeats.isEmpty())){
			if (!number.isEmpty()) {
				admin.editFlight(flight, "number", number);
			}
			if (!departureDateTime.isEmpty()) {
				admin.editFlight(flight, "departureDateTime",
						departureDateTime);
			}
			if (!arrivalDateTime.isEmpty()) {
				admin.editFlight(flight, "arrivalDateTime", arrivalDateTime);
			}
			if (!airline.isEmpty()) {
				admin.editFlight(flight, "airline", airline);
			}if (!origin.isEmpty()) {
				admin.editFlight(flight, "origin", origin);
			}if (!destination.isEmpty()) {
				admin.editFlight(flight, "destination ", destination);
			}
			if (!price.isEmpty()) {
				admin.editFlight(flight, "price", price);
			}
			if (!numSeats.isEmpty()) {
				
				admin.editFlight(flight, "numSeats", numSeats);
				
			}
			
			new AlertDialog.Builder(Edit_FlightActivity.this)
	        .setTitle( "EDIT FLIGHT" )
	        .setMessage( "You successful change flight infomation to: "
	        			+"\n"+flight.toString())
	        .setPositiveButton( "Ok", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	                Log.d( "AlertDialog", "Positive" );
	                MainActivity.fm.getFlights().remove(fnumber);
	                MainActivity.fm.getFlights().put(flight.flightNum, flight);
	                try {
						MainActivity.saveFileFlight();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
	                Intent intent = new Intent(Edit_FlightActivity.this, 
	                		View_FlightListActivity.class);
	                intent.putExtra(MainActivity.USER,
	                		Edit_FlightActivity.this.admin.email);
	                startActivity(intent);
	            }
	        })
	        .show();
			
		}
		else{
			 AlertDialog.Builder builder = new AlertDialog.Builder(this);
	            builder.setMessage("Invalid input!")
	                   .setNegativeButton("Ok", 
	                		   new DialogInterface.OnClickListener() {
	                       public void onClick(DialogInterface dialog, int id) { 
	                       }
	                   });
	            AlertDialog alert = builder.create();
	            alert.show();	
		}
		
	
	}
	
	/**
	 * Checks if the date and time being entered is a valid date and time
	 * @param dateTime string of the date and time being verified
	 * @return boolean true or false if it's valid or not.
	 */
	public static boolean isValidDateTime(String dateTime){
		return Pattern.matches("((19|20)\\d\\d)-([0][1-9]|1[012])"
				+ "-([0][1-9]|[12][0-9]|3[01]) "
				+ "([2][0-3]|[0-1][0-9]|[0][1-9]):[0-5][0-9]$", dateTime);
	}

	/**
	 * Checks if the price being entered is a valid price.
	 * @param price string of the price being verified
	 * @return boolean true or false if it's valid or not.
	 */
	public boolean isValidPrice(String price){
		return (price.matches("\\d+") || price.matches("[0-9]*\\.?[0-9]+"));
	}
	
	/**
	 * Checks if the price matches the seats.
	 * @param price string of the price being verified
	 * @return boolean true or false if it's valid or not.
	 */
	public boolean isValidSeats(String price){
		return price.matches("\\d+");
	}
}
