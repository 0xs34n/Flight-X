package csc207.group0388.piii;

import java.io.File;
import java.io.IOException;

import App.Client;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/** A class representation of the search flight result activity. */
public class View_Personal_InfoActivity extends Activity {

	private String check;
	private String email;
	
	/**
	 * Calls when the activity is first created.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view__personal__info);
        Intent intent = getIntent();
        check = (String) intent.getSerializableExtra(MainActivity.CHECK_ADMIN);
        email = (String) intent.getSerializableExtra(MainActivity.USER);
        if(check.equals("true")){
        	TextView info = (TextView) findViewById(R.id.personalInfo);
            info.setText(MainActivity.am.getAdmin(email).viewPersonalInfo());
        }
        else{
        	TextView info = (TextView) findViewById(R.id.personalInfo);
            info.setText(MainActivity.cm.getClient(email).viewPersonalInfo());
        }
      
	}
	
	/**
	 * Returns back to the edict client menu for the admin.
	 * @param view
	 */
	public void editClient(View view) {
		Intent intent = new Intent(this, Edit_ClientActivity.class);
		intent.putExtra(MainActivity.USER, email);
		intent.putExtra(MainActivity.CHECK_ADMIN, check);
		startActivity(intent);
	}
	public void home(View view){
		if(check.equals("true")){
			Intent intent = new Intent(this, Menu_Admin.class);
			intent.putExtra(MainActivity.USER, this.email);
			intent.putExtra(MainActivity.CHECK_ADMIN, check);
			startActivity(intent);
		}
		else{
		Intent intent = new Intent(this, Menu_Client.class);
		intent.putExtra(MainActivity.USER, this.email);
		intent.putExtra(MainActivity.CHECK_ADMIN, check);
		startActivity(intent);}
	}
}