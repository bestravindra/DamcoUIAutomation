package com.damco.tests.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.damco.core.BaseConfiguration;
import com.damco.page.actions.web.FlightsPage;
import com.damco.page.validators.web.FlightsValidator;
import com.damco.utils.common.Config;

public class FlightsTest extends BaseConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(FlightsTest.class);

	private FlightsPage flights;
	private FlightsValidator flightValidator;

	@BeforeClass
	public void setUp() {
		flights = new FlightsPage(driver);
		flightValidator = new FlightsValidator();
	}

	
	@Test(testName = "TC_01", description = "Validate second lowest flight price")
	public void test_second_lowest_flight_price() {
		flights.closePromotionalBannerIfPresent();
		flights.selectFlightsTab();
		flights.enterDepartureCity(Config.getProperty("fromCity"));
		flights.enterArrivalCity(Config.getProperty("toCity"));
		flights.enterDepartureDate(Integer.valueOf(Config.getProperty("plusDaysFromCurrentDate")));
		flights.searchFlight();
		flights.closePayLaterPopupIfPresent();
		flights.sortFlightByDeparture();
		Map<Integer, String> flightInfo = flights.displaySecondLowestPriceOfFlight();
		flightValidator.displaySecondLowestFlightPrice(flightInfo);
	}

}
