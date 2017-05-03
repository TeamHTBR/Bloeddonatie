package com.team_htbr.a1617proj1bloeddonatie_app;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by bjorn on 22-4-2017.
 */

public class Location {

	private String id;
	private String name;
	private String street;
	private String streetNumber;
	private String city;
	private Boolean isMobile;
	private double lat;
	private double lng;

	public Location(String id, String name, String street, String streetNumber, String city, Boolean isMobile, double lat, double lng) {
		this.id = id;
		this.name = name;
		this.street = street;
		this.streetNumber = streetNumber;
		this.city = city;
		this.isMobile = isMobile;
		this.lat = lat;
		this.lng = lng;
	}

	public Location() {

	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setMoblie(Boolean moblie) {
		isMobile = moblie;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getId() {

		return id;
	}

	public String getName() {
		return name;
	}

	public String getStreet() {
		return street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public String getCity() {
		return city;
	}

	public Boolean getMoblie() {
		return isMobile;
	}

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}

	public LatLng getCoordinates() {
		return new LatLng(lat, lng);
	}

	public String getAddress() {
		return street + " " + streetNumber + ", " + city;
	}
}
