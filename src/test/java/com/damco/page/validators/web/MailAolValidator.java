package com.damco.page.validators.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.damco.utils.common.CustomAssertion;

/** This class contains all validations related to Personal Info Page.
 * 
 * @author Ravindra
 *
 */
public class MailAolValidator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MailAolValidator.class);

	
	
	public void assertEquals(String actual, String expected) {
		CustomAssertion.assertEquals(actual, expected);
	}

	public void assertContains(String actual, String expected) {
		CustomAssertion.assertContains(actual, expected);
	}
	
	public void assertEquals(long actual, long expected) {
		CustomAssertion.assertEquals(actual, expected);
	}

}
