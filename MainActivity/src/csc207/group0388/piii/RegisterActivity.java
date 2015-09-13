package csc207.group0388.piii;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import App.Administrator;
import App.Client;
import Managers.ClientManager;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

@SuppressLint("NewApi")
/** A class representation of the register activity. */
public class RegisterActivity extends Activity {
	private String last_name;
	private String first_name;
	private String email_address;
	private String address;
	private String credit_card;
	private String cc_expiry_date;
	private String password;
	
	/**
	 * Calls when the activity is first created.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
	}
	
	/**
	 * Registers a new user to the system.
	 * Which would enable him/her to log in.
	 * @param view
	 * @throws ClassNotFoundException an exception discarded if the class is not found.
	 * @throws IOException an exception discarded if an error has occured.
	 */
	public void registerUser(View view) throws ClassNotFoundException, IOException {
		
		// Gets the last name from the 1st EditText field.
		EditText LastName = (EditText) findViewById(R.id.lastName);
		last_name = LastName.getText().toString();
		
        // Gets the first name from the 2nd EditText field.
		EditText FirstName = (EditText) findViewById(R.id.firstName);
		first_name = FirstName.getText().toString();
		
        // Gets the email address from the 3rd EditText field.
		EditText EmailAddress = (EditText) findViewById(R.id.emailAddress);
		email_address = EmailAddress.getText().toString();
		
        // Gets the address from the 4th EditText field.
		EditText Address = (EditText) findViewById(R.id.address);
		address = Address.getText().toString();
		
        // Gets the credit card from the 5th EditText field.
		EditText CreditCard = (EditText) findViewById(R.id.creditCard);
		credit_card = CreditCard.getText().toString();
		
        // Gets the number from the 6th EditText field.
		EditText CcExpiryDate = (EditText) findViewById(R.id.ccExpDate);
		cc_expiry_date = CcExpiryDate.getText().toString();
		
        // Gets the number from the 7th EditText field.
		EditText Password = (EditText) findViewById(R.id.password);
		password = Password.getText().toString();
		if(!check(last_name,first_name,email_address,
				address,credit_card,cc_expiry_date,password)){
			new AlertDialog.Builder(RegisterActivity.this)
	        .setTitle( "Error" )
	        .setMessage( "Invaild input!")
	        .setNegativeButton( "Ok", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	                Log.d( "AlertDialog", "negative" );
	                
	            }
	        })
	        .show();
		}
		
		else if(MainActivity.cm.CheckClient(email_address)
				||MainActivity.am.checkAdmin(email_address)){
			new AlertDialog.Builder(RegisterActivity.this)
	        .setTitle( "Error" )
	        .setMessage( "This email already exists")
	        .setNegativeButton( "Ok", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	                Log.d( "AlertDialog", "negative" );
	                
	            }
	        })
	        .show();
		}
		else{
			
			if(email_address == "admin@flightx.com") {
				Administrator admin = new Administrator(last_name, first_name, email_address, address, credit_card, cc_expiry_date);
				MainActivity.am.add(admin);
				MainActivity.pm.addPassword(email_address, password);
				MainActivity.saveFileAdmin();
				MainActivity.saveFilePassword();
			}
	
			Client client = new Client(last_name, first_name, email_address,  
					address, credit_card, cc_expiry_date);
			MainActivity.cm.add(client);
			MainActivity.pm.addPassword(email_address, password);
			MainActivity.saveFileClient();
			MainActivity.saveFilePassword();

			new AlertDialog.Builder(RegisterActivity.this)
	        .setTitle( "Congratulations!" )
	        .setMessage( "You have successfully registered!")
	        .setPositiveButton( "Ok", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	                Log.d( "AlertDialog", "postive" );
	                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
	        		startActivity(intent);
	                
	            }
	        })
	        .show();
		}
	}
	
	/**
	 * Checks if the email address being entered is a valid date and time eg. xyz@gmail.com
	 * @param email string of the email address being verified
	 * @return boolean true or false if it's valid or not.
	 */
	public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]"
        		+ "+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\."
        		+ "[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)"
        		+ "+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    	}
	
	/**
	 * Checks if the date being entered is a valid date.
	 * @param inDate string of the date and time being verified
	 * @return boolean true or false if it's valid or not.
	 */
	public boolean isValidDate(String inDate) {
        Pattern datePattern = Pattern.compile("((19|20)\\d{2})-([1-9]|0[1-9]"
        		+ "|1[0-2])-(0[1-9]|[1-9]|[12][0-9]|3[01])");
        Matcher dateMatcher = datePattern.matcher(inDate);
        if(!dateMatcher.matches()){
            System.out.println("Invalid date format!!! -> " + inDate);
            return false;
        }
        System.out.println("Valid date format.");
        return true;
		}
	
	
	/**
	 * Checks if all the sections have been filled by the user.
	 * @param lastName a string of the last name of the user.
	 * @param firstName a string of the first name of the user.
	 * @param email a string of the email of the user.
	 * @param address a string of the address of the user.
	 * @param creditCard a string of the credit card of the user.
	 * @param CCD a string of the credit card expire date of the user.
	 * @param password a string of the password of the user.
	 * @return boolean true or false whether or not everything is filled.
	 */
	@SuppressLint("NewApi")
	public boolean check(String lastName,String firstName,String email,
			String address,String creditCard,String CCD,String password){
		if(!lastName.isEmpty()&&!firstName.isEmpty()&&!email.isEmpty()
				&&!address.isEmpty()&&
				!creditCard.isEmpty()&&!CCD.isEmpty()
				&&!password.isEmpty()&&isValidDate(CCD)&&isValidEmailAddress(email)){
			
			return true;
		}
		return false;
	}
	
	/**
	 * Returns to the main activity if the register button was accidentally clicked.
	 * @param view
	 */
	public void cancel(View view){
		Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
		startActivity(intent);
	}
}


