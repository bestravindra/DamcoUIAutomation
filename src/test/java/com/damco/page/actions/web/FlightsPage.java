package com.damco.page.actions.web;

import static com.damco.utils.selenium.WebUtils.click;
import static com.damco.utils.selenium.WebUtils.doesElemExists;
import static com.damco.utils.selenium.WebUtils.enterText;
import static com.damco.utils.selenium.WebUtils.getDynamicWebElement;
import static com.damco.utils.selenium.WebUtils.getfutureDateInStringFormat;
import static com.damco.utils.selenium.WebUtils.waitForElementVisibility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.damco.flights.model.FlightPriceMapper;
import com.damco.reporter.ExtentReporter;
import com.damco.utils.common.Config;
import com.damco.utils.selenium.ElementSelectorType;

public class FlightsPage {

	private static final Logger LOGGER = LoggerFactory.getLogger(FlightsPage.class);

	private WebDriver driver;

	//*********************** Select Flights Elements *************************************************************
	
	//After navigating to MTT there are multiple tabs at the top this element is for flights  
	@FindBy(how = How.XPATH, using = "//li[@class='menu_Flights']/div/a[@href='https://www.makemytrip.com/flights/']")
	WebElement tab_flights;
	
	//To select one way radio button
	@FindBy(how = How.XPATH, using = "//li[@data-cy='oneWayTrip']/span")
	WebElement radio_btn_OneWay;
	
	
	//*********************************** From City Elements ******************************************************
	//To click on from city input box 
	@FindBy(how = How.XPATH, using = "//label[@for='fromCity']") 
	WebElement input_box_from_city;
	
	//To fill value for from city
	@FindBy(how = How.XPATH, using = "//input[@placeholder='From']") 
	WebElement placeholder_from_city;
	
	//To select value from available options
	@FindBy(how = How.XPATH, using = "//p[text()='New Delhi, India']") 
	WebElement text_from_city;
	
	
	//*********************************** To City Elements ******************************************************
	//To click on to city input box 
	@FindBy(how = How.XPATH, using = "//label[@for='toCity']") 
	WebElement input_box_to_city;
	
	//To fill value for to city
	@FindBy(how = How.XPATH, using = "//input[@placeholder='To']") 
	WebElement placeholder_to_city;
		
	//To select value from available options
	@FindBy(how = How.XPATH, using = "//p[text()='Mumbai, India']") 
	WebElement text_to_city;
	
	
	//*********************************** Departure Date Elements ******************************************************
	//To click on departure date input field
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Departure')]") 
	WebElement input_date_departure;
	
	
	//To click on next button in order to move to next month to select future date
	@FindBy(how = How.XPATH, using = "//span[@aria-label='Next Month']") 
	WebElement btn_next_month;
	
	//To select departure date from calendar wizard
	
	
	
	//*********************************** Search Flight Elements ******************************************************
	//To click on search button after entering flight details
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Search')]") 
	WebElement btn_search;
	
	
	//*********************************** Flight Sorting Elements ******************************************************
	//To click on Departure button to sort flights by departure.
	@FindBy(how = How.XPATH, using = "//span[text()='Departure']") 
	WebElement fiter_btn_departure;
	

	//*********************************** Displayed Flights Elements ******************************************************
	//To get price of all the flights
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'priceSection')]//p") 
	List<WebElement> text_flight_price;
	
	//To get name of all the flights
	@FindBy(how = How.XPATH, using = "//div[@class='listingCard']//div[2]//p[contains(@class,'airlineName')]") 
	List<WebElement> text_flight_name;
	
	//*********************************** Advertisement Banner Elements ******************************************************
	//To close the advertisement banner
	@FindBy(how = How.XPATH, using = "//i[contains(@class,'we_close')]") 
	WebElement btn_advertisement_close_icon;

	//To close pay later pop-up
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'overlayCrossIcon')]") 
	WebElement btn_pay_later_popup_close_icon;
	
	public FlightsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * This method can be used to close promotional banner if it is appearing on the home page after navigation to MMT site
	 */
	public void closePromotionalBannerIfPresent() {
		if (doesElemExists(driver, ElementSelectorType.xpath, "//i[contains(@class,'we_close')]")) {
			click(btn_advertisement_close_icon);
		}
	}
	
	public void closePayLaterPopupIfPresent() {
		try {
			waitForElementVisibility(btn_pay_later_popup_close_icon, 5);
		} catch (Exception e) {
			
		}
		
		if (doesElemExists(driver, ElementSelectorType.xpath, "//span[contains(@class,'overlayCrossIcon')]")) {
			click(btn_pay_later_popup_close_icon);
		}
	}
	
	/**
	 * This method can be used to select flights tab from the home page top left section
	 */
	public void selectFlightsTab() {
		ExtentReporter.info("Selecting flight tab on home page from top left section");
		waitForElementVisibility(tab_flights, 5);
		click(tab_flights);
	}
    
	/**
	 * This method can be used to enter flight departure city 
	 * 
	 * @param departureCityName
	 */
	public void enterDepartureCity(String departureCityName) {
		ExtentReporter.info("Entering flight departure city");
		waitForElementVisibility(input_box_from_city, 5);
		click(input_box_from_city);
		waitForElementVisibility(placeholder_from_city, 5);
		enterText(placeholder_from_city, departureCityName);
		waitForElementVisibility(placeholder_from_city, 5);
		click(text_from_city);
	}
	
	/**
	 * This method can be used to enter flight arrival city 
	 * 
	 * @param arrivalCityName
	 */
	public void enterArrivalCity(String arrivalCityName) {
		ExtentReporter.info("Entering flight arrival city");
		waitForElementVisibility(input_box_to_city, 5);
		click(input_box_to_city);
		waitForElementVisibility(placeholder_to_city, 5);
		enterText(placeholder_to_city, arrivalCityName);
		waitForElementVisibility(placeholder_to_city, 5);
		click(text_to_city);
	}
	
	/**
	 * This method can be used to enter departure date 
	 * 
	 * @param plusNumberOfdaysFromCurrentDate
	 */
	public void enterDepartureDate(int plusNumberOfdaysFromCurrentDate) {
		ExtentReporter.info("Entering flight departure date");
		click(input_date_departure);
		while(!(doesElemExists(driver, ElementSelectorType.xpath, "//div[@aria-label='" + getDateFormat(plusNumberOfdaysFromCurrentDate) + "']"))) {
			click(btn_next_month);
		}
		click(getDynamicWebElement(driver, ElementSelectorType.xpath, "//div[@aria-label='" + getDateFormat(plusNumberOfdaysFromCurrentDate)  + "']"));
	}
	
	/**
	 * This method can be used to click on search button after entering flight details
	 * 
	 */
	public void searchFlight() {
		ExtentReporter.info("Clicking on serach flight button after entring the flight details");
		waitForElementVisibility(btn_search, 5);
		click(btn_search);
	}
	
	/**
	 * This method can be used to sort flights by departure
	 */
	public void sortFlightByDeparture() {
		ExtentReporter.info("Sorting flights by departure");
		waitForElementVisibility(fiter_btn_departure, 10);
		click(fiter_btn_departure);
	}

	/**
	 * This method can be used to display second lowest flight price and name
	 */
	public Map<Integer,String> displaySecondLowestPriceOfFlight() {
		waitForElementVisibility(text_flight_price.get(0), 10);
		
		List<Integer> flightPricList = text_flight_price
										.stream()
										.map(elm -> elm.getText().trim().replaceAll("[^0-9]", ""))
										.map(price -> Integer.valueOf(price))
										.collect(Collectors.toList());
		
		List<String> flightsName = text_flight_name
									.stream()
									.map(elm -> elm.getAttribute("innerText").trim())
									.collect(Collectors.toList());
				
		List<FlightPriceMapper> flightPriceMapperList = new ArrayList<FlightPriceMapper>();
				
		IntStream.range(0, flightPricList.size())
												.forEach(index ->  flightPriceMapperList
												.add(new FlightPriceMapper(flightPricList.get(index),flightsName.get(index))));
   
		List<Integer> flightPriceList = flightPricList
											.stream()
											.distinct()
											.sorted()
											.collect(Collectors.toList());
		
		int secondLowestFlightPrice = flightPriceList.get(1);
		
		Optional<String> secondLowestFlightName = flightPriceMapperList
														.stream()
														.filter(mapper -> mapper.getFlightPrice() == secondLowestFlightPrice)
														.map(mapper -> mapper.getFlightName())
														.findFirst();
		
		Map<Integer, String> flightInfo = new HashMap<Integer, String>();
		flightInfo.put(secondLowestFlightPrice, secondLowestFlightName.get());
		
		return flightInfo;
	}
	
	/**
	 * This method can be used to get string formatted date as configured in properties file
	 */
	public String getDateFormat(int plusNumberOfdaysFromCurrentDate) {
		//To select departure date from calendar wizard
		String departureDateFormat = getfutureDateInStringFormat(plusNumberOfdaysFromCurrentDate, Config.getProperty("dateFormat"));
		return departureDateFormat;
	}
	
}
