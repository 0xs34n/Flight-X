package csc207.group0388.piii;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;

import App.Administrator;
import App.Flight;
import App.User;
import App.Client;
import Managers.AdminManager;
import Managers.ClientManager;
import Managers.FlightManager;
import Managers.PasswordManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


/** A class representation of the MainActivity. */
public class MainActivity extends Activity {
	
	//Declares all the constants to be used in this activity.
    public static final String ClientFileString = "Clients.csv";
    public static final String AdminFileString = "Admins.csv";
    
    public static final String ClientFileByte = "ClientsByte.csv";
    public static final String AdminFileByte = "AdminsByte.csv";
    public static final String FLIGHT_FILE_BYTE = "flightsByte.csv";
    
    
    public static final String PasswordFile = "Password.csv";
    
    public static final String USERDATADIR = "data";
    public static final String STUDENT_MANAGER_KEY = "clientManagerKey";
    public static final String USER = "email";
    public static final String CHECK_ADMIN = "check";

    public static final String PASSWORD_FILE_BYTE = "PasswordsByte.csv";
    
    public static final String Password = "Passwords.csv";
   
    public static final String FLIGHT_FILE_NAME = "Workbook.csv";
   
    public static Administrator admin;
	public static Client client;
	public static ClientManager cm;
	public static AdminManager am;
	public static FlightManager fm;
	public static PasswordManager pm;
	public static File userdata;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		userdata = this.getApplicationContext().getDir(USERDATADIR, MODE_PRIVATE);
        
		// Initializes managers.
        fm = new FlightManager();
        
        fm.add(new Flight("AC100", "2015-09-01 08:00", 
    			"2015-09-01 23:00", "Air Canada",
				"Shanghai", "Toronto", 900, 100));
        fm.add(new Flight("AC101", "2015-09-02 01:00", 
    			"2015-09-02 23:00", "British Airways",
				"Toronto", "London", 900, 100));     
        fm.add(new Flight("AC102", "2015-09-03 01:00", 
    			"2015-09-03 11:00", "Air Transat",
				"London", "New York", 900, 100));
        fm.add(new Flight("AC103", "2015-09-03 13:00", 
    			"2015-09-03 23:00", "Cathay Pacific",
				"New York", "Koh Samui", 900, 100));
        fm.add(new Flight("AC104", "2015-09-04 01:00", 
    			"2015-09-04 22:00", "Delta Airways",
				"Koh Samui", "Tokyo", 900, 100));
        
        fm.add(new Flight("AC100", "2015-09-01 08:00", 
    			"2015-09-01 23:15", "Air Canada",
				"Shanghai", "Toronto", 800, 100));
        fm.add(new Flight("AC101", "2015-09-02 01:00", 
    			"2015-09-02 23:15", "British Airways",
				"Toronto", "London", 800, 100));     
        fm.add(new Flight("AC102", "2015-09-03 01:00", 
    			"2015-09-03 11:15", "Air Transat",
				"London", "New York", 800, 100));
        fm.add(new Flight("AC103", "2015-09-03 13:00", 
    			"2015-09-03 23:15", "Cathay Pacific",
				"New York", "Koh Samui", 800, 100));
        fm.add(new Flight("AC104", "2015-09-04 01:00", 
    			"2015-09-04 22:15", "Delta Airways",
				"Koh Samui", "Tokyo", 800, 100));
        
        fm.add(new Flight("AC105", "2015-09-01 08:00", 
    			"2015-09-01 23:30", "Air Canada",
				"Shanghai", "Toronto", 700, 100));
        fm.add(new Flight("AC106", "2015-09-02 01:00", 
    			"2015-09-02 23:30", "British Airways",
				"Toronto", "London", 700, 100));     
        fm.add(new Flight("AC107", "2015-09-03 01:00", 
    			"2015-09-03 11:30", "Air Transat",
				"London", "New York", 700, 100));
        fm.add(new Flight("AC108", "2015-09-03 13:00", 
    			"2015-09-03 23:30", "Cathay Pacific",
				"New York", "Koh Samui", 700, 100));
        fm.add(new Flight("AC109", "2015-09-04 01:00", 
    			"2015-09-04 22:30", "Delta Airways",
				"Koh Samui", "Tokyo", 700, 100));
        
        fm.add(new Flight("AC110", "2015-09-01 08:00", 
    			"2015-09-01 23:45", "Air Canada",
				"Shanghai", "Toronto", 600, 100));
        fm.add(new Flight("AC111", "2015-09-02 01:00", 
    			"2015-09-02 23:45", "British Airways",
				"Toronto", "London", 600, 100));     
        fm.add(new Flight("AC112", "2015-09-03 01:00", 
    			"2015-09-03 11:45", "Air Transat",
				"London", "New York", 600, 100));
        fm.add(new Flight("AC113", "2015-09-03 13:00", 
    			"2015-09-03 23:45", "Cathay Pacific",
				"New York", "Koh Samui", 600, 100));
        fm.add(new Flight("AC114", "2015-09-04 01:00", 
    			"2015-09-04 22:45", "Delta Airways",
				"Koh Samui", "Tokyo", 600, 100));
        
        fm.add(new Flight("AC115", "2015-09-01 08:00", 
    			"2015-09-01 23:50", "Air Canada",
				"Shanghai", "Toronto", 500, 100));
        fm.add(new Flight("AC116", "2015-09-02 01:00", 
    			"2015-09-02 23:50", "British Airways",
				"Toronto", "London", 500, 100));     
        fm.add(new Flight("AC117", "2015-09-03 01:00", 
    			"2015-09-03 11:50", "Air Transat",
				"London", "New York", 500, 100));
        fm.add(new Flight("AC118", "2015-09-03 13:00", 
    			"2015-09-03 23:50", "Cathay Pacific",
				"New York", "Koh Samui", 500, 100));
        fm.add(new Flight("AC119", "2015-09-04 01:00", 
    			"2015-09-04 22:50", "Delta Airways",
				"Koh Samui", "Tokyo", 500, 100));

  
        
        
        cm = new ClientManager();
		am = new AdminManager();
		am.add(new Administrator("admin","admin","admin@flightx.com","flightXHQ", "100", "2015-01-01"));
		System.out.println(am);
		pm = new PasswordManager();
		pm.addPassword("admin@flightx.com", "admin");
		System.out.println(pm);
		// Reads all the files from the managers.
		try {
			this.readFileAdmin();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.readFileClient();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.readFileFlight();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.readFilePassword();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Switches to the register user activity if the button is clicked.
	 * @param view
	 */
	public void registerUser(View view){
		
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
	}
	
	/**
	 * Validates whether the user is a valid client or a valid administrator.
	 * If tests pass as a client, then the user is taken to the Client Menu.
	 * If tests pass as an administrator, then the user is taken to the Administrator Menu.
	 * Otherwise, presents an error.
	 * @param view
	 * @throws ClassNotFoundException an exception discarded if the class is not found.
	 * @throws IOException an exception discarded if an error has occurred.
	 */
	public void login(View view) throws ClassNotFoundException, IOException{
		
		if(checkClient() != null){
			Intent intent = new Intent(this, Menu_Client.class);
			intent.putExtra(USER, checkClient().email);
			intent.putExtra(CHECK_ADMIN, "false");
			startActivity(intent);
		}
       
		
		else if (checkAdmin() != null){

			Intent intent = new Intent(this, Menu_Admin.class);
			intent.putExtra(USER, checkAdmin().email);
			intent.putExtra(CHECK_ADMIN, "true");
			startActivity(intent);
		}
		else
		{
			 new AlertDialog.Builder(MainActivity.this)
             .setTitle( "Error" )
             .setMessage( "Your username or password is incorrect")
             .setNegativeButton( "Ok", new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int which) {
                     Log.d( "AlertDialog", "Negative" );
                 }
             } )
             .show(); 
		}
	}

	/**
	 * Checks whether or not the client is valid
	 * @return null
	 * @throws FileNotFoundException an exception discarded if the file is not found.
	 */
	public Client checkClient() throws FileNotFoundException{

		
		EditText usernameText = (EditText) findViewById(R.id.editUsername);
		String username = usernameText.getText().toString();
		
		EditText passwordText =	(EditText) findViewById(R.id.editPassword);
		String password = passwordText.getText().toString();
		if(pm.checkClient(cm, username, password)){
			return cm.getClient(username);
		}
		return null;

	}
	
	/**
	 * Checks whether or not the admin is valid
	 * @return null
	 * @throws FileNotFoundException an exception discarded if the file is not found.
	 */
	public Administrator checkAdmin() throws FileNotFoundException{

		
		EditText usernameText = (EditText) findViewById(R.id.editUsername);
		String username = usernameText.getText().toString();
		
		EditText passwordText =	(EditText) findViewById(R.id.editPassword);
		String password = passwordText.getText().toString();
		if(pm.checkAdmin(am, username, password)){
			return am.getAdmin(username);
		}
		
		return null;
	}
	
	/**
	 * Reads the client file.
	 * @throws ClassNotFoundException an exception discarded if the class is not found
	 * @throws IOException an exception discarded if an error has occured.
	 */
	public void readFileClient() throws ClassNotFoundException, IOException{
		File userdata = this.getApplicationContext().getDir(USERDATADIR,
				MODE_PRIVATE);
		File clientFileByte = new File(userdata, ClientFileByte);
		File clientFile = new File(userdata, ClientFileString);
        

    	if (clientFileByte.exists()) {
    		cm.readFromFile(clientFileByte);
    	} else {
    		//clientFileByte.createNewFile();
    		cm.readFromCSVFile(clientFile);
    	}
	}
	
	public void readFileAdmin() throws ClassNotFoundException, IOException{
		File userdata = this.getApplicationContext().getDir(USERDATADIR,
				MODE_PRIVATE);
		File adminFileByte = new File(userdata, AdminFileByte);
		File adminFile = new File(userdata, AdminFileString);

    	if (adminFileByte.exists()) {
    		am.readFromFile(adminFileByte);
    	} else {
    		//adminFileByte.createNewFile();
    		am.readFromCSVFile(adminFile);
    	}
	}
	
	/**
	 * Reads the flight file.
	 * @throws ClassNotFoundException an exception discarded if the class is not found
	 * @throws IOException an exception discarded if an error has occured.
	 */
	public void readFileFlight() throws ClassNotFoundException, IOException{
		File userdata = this.getApplicationContext().getDir(USERDATADIR, 
				MODE_PRIVATE);
		File flightFileByte  = new File(userdata, FLIGHT_FILE_BYTE );
		File flightsFile = new File(userdata, FLIGHT_FILE_NAME);

    	if (flightFileByte .exists()) {
    		fm.readFromFile(flightFileByte);
    	} else {
    		//flightFileByte .createNewFile();
    		fm.androidReadFile(flightsFile);
    	}
	}
	
	/**
	 * Reads the password file.
	 * @throws ClassNotFoundException an exception discarded if the class is not found
	 * @throws IOException an exception discarded if an error has occured.
	 */
	public void readFilePassword() throws ClassNotFoundException, IOException{
		File userdata = this.getApplicationContext().getDir(USERDATADIR, 
				MODE_PRIVATE);
		File passwowrdFileByte  = new File(userdata, PASSWORD_FILE_BYTE );
		File passwordFile = new File(userdata, Password);

    	if (passwowrdFileByte.exists()) {
    		pm.readFromFile(passwowrdFileByte);
    	} else {
    
    		pm.readFromCSVFile(passwordFile);
    	}
	}
	
	/**
	 * Saves the client file.
	 * @throws ClassNotFoundException an exception discarded if the class is not found
	 * @throws IOException an exception discarded if an error has occured.
	 */
	public static void saveFileClient() throws ClassNotFoundException, IOException{

		File clientFileByte = new File(userdata, ClientFileByte);

    	if (clientFileByte.exists()) {
    		cm.saveToFile(clientFileByte);
    	} else {
    		clientFileByte.createNewFile();
    		cm.saveToFile(clientFileByte);
    	}
	}
	
	/**
	 * Saves the admin file.
	 * @throws ClassNotFoundException an exception discarded if the class is not found
	 * @throws IOException an exception discarded if an error has occured.
	 */
	public static void saveFileAdmin() throws ClassNotFoundException, IOException{

		File adminFileByte = new File(userdata, AdminFileByte);


    	if (adminFileByte.exists()) {
    		am.saveToFile(adminFileByte);
    	} else {
    		adminFileByte.createNewFile();
    		am.saveToFile(adminFileByte);
    	}
	}
	
	/**
	 * Saves the flight file.
	 * @throws ClassNotFoundException an exception discarded if the class is not found
	 * @throws IOException an exception discarded if an error has occured.
	 */
	public static void saveFileFlight() throws ClassNotFoundException, IOException{

		File flightFileByte  = new File(userdata, FLIGHT_FILE_BYTE );

    	if (flightFileByte .exists()) {
    		fm.saveToFile(flightFileByte);
    	} else {
    		flightFileByte .createNewFile();
    		fm.saveToFile(flightFileByte);
    	}
	}
	
	/**
	 * Saves the password file.
	 * @throws ClassNotFoundException an exception discarded if the class is not found
	 * @throws IOException an exception discarded if an error has occured.
	 */
	public static void saveFilePassword() throws ClassNotFoundException, IOException{

		File passwowrdFileByte  = new File(userdata, PASSWORD_FILE_BYTE );

    	if (passwowrdFileByte.exists()) {
    		pm.saveToFile(passwowrdFileByte);
    	} else {
    		passwowrdFileByte.createNewFile();
    		pm.saveToFile(passwowrdFileByte);
    	}
	}
	
}
