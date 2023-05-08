package com.damco.page.validators.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** This class contains all validations related to Personal Info Page.
 * 
 * @author Ravindra
 *
 */
public class FlightsValidator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlightsValidator.class);

	
	
	public void displaySecondLowestFlightPrice(Map<Integer, String> flightsInfo) {
		flightsInfo.forEach((k,v) -> {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("Flight Price: " + k + " Flight Name: " + v);
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			});
	}


}
