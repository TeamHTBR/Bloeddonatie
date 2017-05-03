package com.team_htbr.a1617proj1bloeddonatie_app;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

		// Add a marker in Brussel and move the camera
		LatLng brussel = new LatLng(50.871157, 4.331759);
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(brussel, 9));

		//enable search my location
		if ((ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
			mMap.setMyLocationEnabled(true);
		}

		//get database from firebase
		DatabaseReference fireBaseDataBase = FirebaseDatabase.getInstance().getReference();
		DatabaseReference markersDataBase = fireBaseDataBase.child("locations_test");

		//add new markers when added in database
		markersDataBase.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {

				for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
					Location newLocation = snapshot.getValue(Location.class);
					addNewMarker(newLocation);
				}
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
				return;
			}
		});

		//move camera to marker on click
		mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
			@Override
			public boolean onMarkerClick(Marker marker) {
				mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 10));
				return false;
			}
		});

		//creating custom info window
		mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
			@Override
			public View getInfoWindow(Marker marker) {
				return null;
			}

			@Override
			public View getInfoContents(Marker marker) {
				View v = getLayoutInflater().inflate(R.layout.info_window, null);

				TextView tvTitle= (TextView) v.findViewById(R.id.tv_title);
				TextView tvAddress= (TextView) v.findViewById(R.id.tv_address);

				tvTitle.setText(marker.getTitle());
				tvAddress.setText(marker.getSnippet());

				return v;
			}
		});

	}

	//add new marker to map
	public void addNewMarker(Location newMarker) {
		mMap.addMarker(new MarkerOptions()
			.position(newMarker.getCoordinates())
			.title(newMarker.getName())
			.snippet(newMarker.getAddress())
		);
	}
}
