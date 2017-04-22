package com.team_htbr.a1617proj1bloeddonatie_app;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

	private GoogleMap mMap;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);
		// Obtain the SupportMapFragment and get notified when the map is ready to be used.
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
			.findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);


	}


	/**
	 * Manipulates the map once available.
	 * This callback is triggered when the map is ready to be used.
	 * This is where we can add markers or lines, add listeners or move the camera. In this case,
	 * we just add a marker near Sydney, Australia.
	 * If Google Play services is not installed on the device, the user will be prompted to install
	 * it inside the SupportMapFragment. This method will only be triggered once the user has
	 * installed Google Play services and returned to the app.
	 */
	@Override
	public void onMapReady(GoogleMap googleMap) {
		mMap = googleMap;

		// Add a marker in Sydney and move the camera
		LatLng sydney = new LatLng(51, 3);
		mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 8));

		if ((ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
			mMap.setMyLocationEnabled(true);
		}

		DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
		final DatabaseReference locations = mDatabase.child("locations_test");

		locations.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				List<Object> locationsList = new ArrayList<Object>();

				for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
					locationsList.add(locationSnapshot.getValue());
					Location nieuweLocatie = dataSnapshot.getValue(Location.class);
					Marker(nieuweLocatie);
				}

			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});

		Location test = new Location("Dit is de titel hiep hiep hoera", "willystraat", 40, "Marjet", 51.04155579823785, 3.7056541442871094 );
		Marker(test);
	}

	public void Marker(Location testing) {
		LatLng latlng = new LatLng(testing.getLAT(), testing.getLNG());
		mMap.addMarker(new MarkerOptions()
			.position(latlng)
			.title(testing.getName())
			.snippet(testing.getAddress())
		);

	}
}
