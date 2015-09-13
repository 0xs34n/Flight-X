package csc207.group0388.piii;

import java.util.ArrayList;
import java.util.Map;

import App.Flight;
import App.Client;
import App.Itinerary;
import Managers.ClientManager;
import Managers.FlightManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
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

/** A class representation of the search flight result activity. */
public class View_ClientsActivity extends Activity {


	
	ListView listView ;
	ArrayAdapter<String> adapter;
	protected ClientManager cm;
	protected ArrayList<Client> clients;
	private int itemPosition;
	private String email;
	private String check = "false";

	/**
	 * Calls when the activity is first created.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view__clients);
		Intent intent = getIntent();
		this.email = (String) intent.getSerializableExtra(MainActivity.USER);
		listView = (ListView) findViewById(R.id.listClients);
		
		cm = MainActivity.cm;

		clients = new ArrayList<Client>();
		for(Map.Entry<String, Client> entry: cm.getClients().entrySet()){
			clients.add(entry.getValue());
 		}
		String[] values = new String[clients.size()];
		for(int i = 0; i< clients.size(); i++){
			values[i] = clients.get(i).viewPersonalInfo();
		}
		
		
		adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, values);
		
		 // Assign adapter to ListView
        listView.setAdapter(adapter); 

        listView.setOnItemClickListener(new OnItemClickListener() {

              public void onItemClick(AdapterView<?> parent, View view,
                 int position, long id) {
                
               // ListView Clicked item index
               itemPosition     = position;
               
               // ListView Clicked item value
               String  itemValue    = (String) listView.getItemAtPosition(position);
              
                // Show Alert 
               
               new AlertDialog.Builder(View_ClientsActivity.this )
               .setTitle( "Edit Client" )
               .setMessage( "Do you want switch to this client:"+"\n" + itemValue)
               
               .setPositiveButton( "Cancel", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {
                       Log.d( "AlertDialog", "cancel" );
                   }
               } )
               .setNegativeButton( "Edit", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {
                       Log.d( "AlertDialog", "edit" );
                    Intent intent = new Intent(View_ClientsActivity.this, 
                    		Menu_Client.class);
               		intent.putExtra(MainActivity.USER, clients.get(itemPosition).email);
               		intent.putExtra(MainActivity.CHECK_ADMIN, check);
               		startActivity(intent);
                   }
               })

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
