package com.damco.flights.model;

public class FlightPriceMapper {

	private int flightPrice;
	private String flightName;
	
	public FlightPriceMapper(int flightPrice, String flightName) {
		super();
		this.flightPrice = flightPrice;
		this.flightName = flightName;
	}
	
	public int getFlightPrice() {
		return flightPrice;
	}
	public void setFlightPrice(int flightPrice) {
		this.flightPrice = flightPrice;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	
	
}
