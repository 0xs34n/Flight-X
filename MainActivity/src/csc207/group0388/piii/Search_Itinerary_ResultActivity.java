package csc207.group0388.piii;

import java.io.IOException;
import java.util.ArrayList;

import App.Administrator;
import App.Client;
import App.Flight;
import App.Itinerary;
import Managers.FlightManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

/** A class representation of the search flight result activity. */
public class Search_Itinerary_ResultActivity extends Activity {
	
	private ArrayList<Itinerary> itineraries;
	private String email;
	private Client client;
	private Administrator admin;
	private String check;
	public static final String SORT_FLIGHT_RESULT = "sort";
	public static final String Search_Itinerary = "true";
	ListView listView ;
	ArrayAdapter<String> adapter;
	private FlightManager fm = new FlightManager();
	private int itemPosition;
	
	/**
	 * Calls when the activity is first created.
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search__itinerary__result);
		Intent intent = getIntent();
		this.email = (String) intent.getSerializableExtra(MainActivity.USER);
		this.check = (String) intent.getSerializableExtra(MainActivity.CHECK_ADMIN);
		if(check.equals("true")){
			admin = MainActivity.am.getAdmin(email);
		}
		else{
			client = MainActivity.cm.getClient(email);
		}
		listView = (ListView) findViewById(R.id.list_itinerary);
		itineraries= (ArrayList<Itinerary>) 
				intent.getSerializableExtra(
						Search_FlightActivity.SEARCH_FLIGHT_RESULT);
		
		String[] values = new String[itineraries.size()];
		for(int i = 0; i<itineraries.size(); i++){
			values[i] = itineraries.get(i).toString();
		}
		
		
		adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, values);
		
		 // Assign adapter to ListView
        listView.setAdapter(adapter); 
        final Dialog dialog = new Dialog(this);
        // ListView Item Click Listener
        listView.setOnItemClickListener(new OnItemClickListener() {

              public void onItemClick(AdapterView<?> parent, View view,
                 int position, long id) {
                
               // ListView Clicked item index
               itemPosition = position;
               
               // ListView Clicked item value
               String  itemValue = (String) listView.getItemAtPosition(position);
               if(check.equals("true")){
                   if(Search_Itinerary_ResultActivity.this.admin.checkBookItinerary(itemValue)){
                	   new AlertDialog.Builder(Search_Itinerary_ResultActivity.this)
                       .setTitle( "Book Itinerary" )
                       .setMessage( "You already booked this itinerary: "+"\n" + itemValue)
                       .setNegativeButton( "Back", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int which) {
                               Log.d( "AlertDialog", "Negative" );
                           }
                       } )
                       .show();  
                   }
                   else{
                	   	new AlertDialog.Builder(Search_Itinerary_ResultActivity.this )
                       .setTitle( "Book Itinerary" )
                       .setMessage( "Book itinerary:"+"\n" + itemValue)
                       .setPositiveButton( "Ok", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int which) {
                               Log.d( "AlertDialog", "Positive" );
                               // if client able to book
                               if(itineraries.get(itemPosition).bookable()){
                            	   MainActivity.am.getAdmin(Search_Itinerary_ResultActivity
                                		   .this.email).bookItinerary(itineraries.get(itemPosition));
                            	   //save the client info
                            	   try {
           								MainActivity.saveFileAdmin();
           							} catch (ClassNotFoundException e) {
           							// TODO Auto-generated catch block
           							e.printStackTrace();
           							} catch (IOException e) {
           							// TODO Auto-generated catch block
           							e.printStackTrace();
           							} 
                            	   // modify the fm
                            	   for(Flight flight:itineraries.get(itemPosition).itinerary){
                            		   MainActivity.fm.getFlight(flight.flightNum).bookFlight();
                            		  int i= MainActivity.fm.getFlight(flight.flightNum).currentBooked;
                            		  System.out.print(i);
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
                            	Intent intent = new Intent(
                            			Search_Itinerary_ResultActivity.this, 
                            			View_Booked_ItinerariesActivity.class);
                           		intent.putExtra(MainActivity.USER, email);
                           		intent.putExtra(MainActivity.CHECK_ADMIN, check);
                           		startActivity(intent);
                            	   // reduce the list
                            	   /*itineraries.get(itemPosition).bookItinerary();
                            	   itineraries.remove(itemPosition);
                                   listView = (ListView) findViewById(R.id.list_itinerary);
                              		fm.sortByTime(itineraries);
                              		String[] values = new String[itineraries.size()];
                              		for(int i = 0; i<itineraries.size(); i++){
                              			values[i] = itineraries.get(i).toString();
                              		}
                              		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                               				Search_Itinerary_ResultActivity.this,
                               				android.R.layout.simple_list_item_1, 
                               				android.R.id.text1, values);
                               		
                               		 // Assign adapter to ListView
                                     listView.setAdapter(adapter);	*/
                               }
                               else{
                            	 new AlertDialog.Builder(Search_Itinerary_ResultActivity.this)
                       	        .setTitle( "Error" )
                       	        .setMessage( "This itinerary is full")
                       	        .setNegativeButton( "Back", new DialogInterface.OnClickListener() {
                       	            public void onClick(DialogInterface dialog, int which) {
                       	                Log.d( "AlertDialog", "negative" );
                       	            }
                       	        })
                       	        .show();
                               }
                           }
                       })
                       .setNegativeButton( "Back", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int which) {
                               Log.d( "AlertDialog", "Negative" );
                           }
                       } )
                       .show();
                   }
    
               }
               else{
                   if(Search_Itinerary_ResultActivity.this.client.checkBookItinerary(itemValue)){
                	   new AlertDialog.Builder(Search_Itinerary_ResultActivity.this)
                       .setTitle( "Book Itinerary" )
                       .setMessage( "You already booked this itinerary: "+"\n" + itemValue)
                       .setNegativeButton( "back", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int which) {
                               Log.d( "AlertDialog", "Negative" );
                           }
                       } )
                       .show();  
                   }
                   // Client
                   else{
                	   
                	   
                	   	new AlertDialog.Builder(Search_Itinerary_ResultActivity.this )
                       .setTitle( "Book Itinerary" )
                       .setMessage( "Book this itinerary:"+"\n" + itemValue)
                       .setPositiveButton( "Ok", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int which) {
                               Log.d( "AlertDialog", "Positive" );
                               // if client able to book
                               if(itineraries.get(itemPosition).bookable()){
                            	   System.out.println(itemPosition);
                            	   MainActivity.cm.getClient(Search_Itinerary_ResultActivity
                                		   .this.email).bookItinerary(itineraries.get(itemPosition));
                            	   //save the client info
                            	   try {
           								MainActivity.saveFileClient();
           							} catch (ClassNotFoundException e) {

           							e.printStackTrace();
           							} catch (IOException e) {

           							e.printStackTrace();
           							} 
                            	   // modify the fm
                            	   for(Flight flight:itineraries.get(itemPosition).itinerary){
                            		   MainActivity.fm.getFlight(flight.flightNum).bookFlight();
                            		  int i= MainActivity.fm.getFlight(flight.flightNum).currentBooked;
                            		  System.out.print(i);
                            	   }
                            	   try {
                            		   MainActivity.saveFileFlight();
                            	   } catch (ClassNotFoundException e) {

                            		   e.printStackTrace();
                            	   } catch (IOException e) {

                            		   e.printStackTrace();
                            	   }
                            	Intent intent = new Intent(
                            			Search_Itinerary_ResultActivity.this, 
                            			View_Booked_ItinerariesActivity.class);
                           		intent.putExtra(MainActivity.USER, email);
                           		intent.putExtra(MainActivity.CHECK_ADMIN, check);
                           		startActivity(intent);
                               }
                               else{
                            	 new AlertDialog.Builder(Search_Itinerary_ResultActivity.this)
                       	        .setTitle( "ERROR" )
                       	        .setMessage( "This itinerary is full")
                       	        .setNegativeButton( "BACK", new DialogInterface.OnClickListener() {
                       	            public void onClick(DialogInterface dialog, int which) {
                       	                Log.d( "AlertDialog", "negative" );
                       	            }
                       	        })
                       	        .show();
                               }
                           }
                       })
                       .setNegativeButton( "Back", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int which) {
                               Log.d( "AlertDialog", "Negative" );
                           }
                       } )
                       .show();
                   }
            	   
               }
              }

         }); 
        

}
	
	/**
	 * Sorts the itineraries in ascending order of time.
	 * @param v
	 */
	public void sortByTime(View v){

		
		listView = (ListView) findViewById(R.id.list_itinerary);
		fm.sortByTime(itineraries);
		String[] values = new String[itineraries.size()];
		for(int i = 0; i<itineraries.size(); i++){
			values[i] = itineraries.get(i).toString();
		}
		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, values);
		
		 // Assign adapter to ListView
        listView.setAdapter(adapter); 
		
	}
	
	/**
	 * Sorts the itineraries in ascending order of cost.
	 * @param v
	 */
	public void sortByCost(View v){
		
		listView = (ListView) findViewById(R.id.list_itinerary);
		fm.sortByCost(itineraries);
		String[] values = new String[itineraries.size()];
		for(int i = 0; i<itineraries.size(); i++){
			values[i] = itineraries.get(i).toString();
		}
		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, values);
		
		 // Assign adapter to ListView
        listView.setAdapter(adapter); 
		
	}
	
	/**
	 * Books the specific itinerary.
	 * @param v
	 */
	public void book(View v){
		
		
		Intent intent = new Intent(this, View_Booked_ItinerariesActivity.class);
		intent.putExtra(MainActivity.USER, email);
   		intent.putExtra(MainActivity.CHECK_ADMIN, check);
		startActivity(intent);

		
	}
}
