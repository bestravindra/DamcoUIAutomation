package com.damco.tests.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.damco.core.BaseConfiguration;
import com.damco.page.actions.web.MailAolPage;
import com.damco.page.validators.web.MailAolValidator;
import com.damco.utils.common.Config;
import static com.damco.utils.selenium.WebUtils.*;

public class MailAolTest extends BaseConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(MailAolTest.class);

	private MailAolPage mailAol;
	private MailAolValidator mailAolValidator;

	@BeforeClass
	public void setUp() {
		mailAol = new MailAolPage(driver);
		mailAolValidator = new MailAolValidator();
	}

	
	@Test(testName = "TC_01", description = "Validate Aol compose email and attachment functionality")
	public void test_aol_compose_email_and_attachment() {
		mailAol.login(Config.getProperty("userName"), Config.getProperty("password"));
		mailAol.composeEmail();
		mailAol.attachFileAndSendEmail(Config.getProperty("attachmentFilePath"));
		mailAol.openInboxEmail();
		String emailSubjectText = mailAol.getEmailSubject();
		String emailBodyText =  mailAol.getEmailBodyText();
		mailAol.downloadInboxAttachedFile(Config.getProperty("fileDownloadPath"));
		mailAolValidator.assertEquals(emailSubjectText, Config.getProperty("emailSubject"));
		mailAolValidator.assertContains(emailBodyText,Config.getProperty("Data1"));
		mailAolValidator.assertContains(emailBodyText,Config.getProperty("Data2"));
		mailAolValidator.assertContains(emailBodyText,Config.getProperty("Data3"));
		mailAolValidator.assertEquals(getSizeOfFile(Config.getProperty("fileDownloadPath"), 
										Config.getProperty("fileName")), 
										Long.valueOf(Config.getProperty("fileSize")));
	}

}
