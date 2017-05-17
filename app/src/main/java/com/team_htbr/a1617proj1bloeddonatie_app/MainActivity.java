package com.team_htbr.a1617proj1bloeddonatie_app;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = "MainActivity";
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("Rode Kruis");

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

		mDrawerLayout.addDrawerListener(mToggle);
		mToggle.syncState();

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);


		Button btnBloodtype = (Button) findViewById(R.id.Bloodtype);
		btnBloodtype.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity.this, SubscribeBloodtypeActivity.class));
			}
		});
		// Subscribing to a blood type
		//FirebaseMessaging.getInstance().subscribeToTopic("blood-AB");
		// Uncomment this line to effectively unsubscribe from topic
		// FirebaseMessaging.getInstance().unsubscribeFromTopic("blood-AB");

		Button btnDonorTest = (Button) findViewById(R.id.donorTest);
		btnDonorTest.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity.this, DonorTestActivity.class));
			}
		});

		Button btnMaps = (Button) findViewById(R.id.GoogleMap);
		btnMaps.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity.this, MapsActivity.class));
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(mToggle.onOptionsItemSelected(item)){
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
