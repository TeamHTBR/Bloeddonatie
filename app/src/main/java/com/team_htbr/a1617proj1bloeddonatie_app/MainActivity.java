package com.team_htbr.a1617proj1bloeddonatie_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = "MainActivity";
	List<String> addressen = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("Rode Kruis");

		Button btnShowToken = (Button) findViewById(R.id.button_show_token);
		btnShowToken.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Get the token
				String token = FirebaseInstanceId.getInstance().getToken();
				Log.d(TAG, "Token: " + token);
				Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
			}
		});

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

		DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
		DatabaseReference locations = mDatabase.child("locations_test");

		locations.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

				}
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});

	}
}
