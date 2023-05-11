package com.damco.page.actions.web;

import static com.damco.utils.selenium.WebUtils.*;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.damco.reporter.ExtentReporter;
import com.damco.utils.common.Config;

public class MailAolPage {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlightsPage.class);
	
	private WebDriver driver;
	
	//*********************** Login Elements *************************************************************

	//To click on login icon user lands on home page
	@FindBy(how = How.CSS, using = "a.login>span")
	WebElement icon_login;
		
	//To enter user name for login  
	@FindBy(how = How.ID, using = "login-username")
	WebElement input_box_userName;
	
	//To click on next button after entering user name or password   
	@FindBy(how = How.ID, using = "login-signin")
	WebElement btn_next;
	
	//To enter password for login 
	@FindBy(how = How.ID, using = "login-passwd")
	WebElement input_box_password;
	
	
	//*********************** Compose Mail Elements *************************************************************
	
	//To click on compose button in order to compose a new mail
	@FindBy(how = How.XPATH, using = "//a[@aria-label='Compose']")
	WebElement btn_compose;
	
	//To enter receiver mail id
	@FindBy(how = How.ID, using = "message-to-field")
	WebElement input_receiver_mail_id;
	
	//To enter the subject of 
	@FindBy(how = How.CSS, using = "input[data-test-id=compose-subject]")
	WebElement input_mail_subject;
	
	//To enter the email body 
	@FindBy(how = How.CSS, using = "div[aria-label='Message body']")
	WebElement input_mail_body;

	//To click on bullet tab 
	@FindBy(how = How.CSS, using = "button[aria-label='Bulleted List']")
	WebElement label_bulleted;
	
	//To select bullet option 
	@FindBy(how = How.CSS, using = "button[aria-label='Bulleted List']>span")
	WebElement label_option_bulleted;
	
	//To select attach option
	@FindBy(how = How.XPATH, using = "//input[@type='file']")
	WebElement btn_attach_option;
	
	//To check whether file is attached or not
	@FindBy(how = How.CSS, using = "div[data-test-id='attachment-container']>div>img")
	WebElement link_attached_file;
	
	
	//To select attach option
	@FindBy(how = How.XPATH, using = "//button//span[text()='Send']")
	WebElement btn_send;
	
	//To check sent email confirmation message
	@FindBy(how = How.XPATH, using = "//a[data-test-id='navigate-button']")
	WebElement txt_email_sent_confirmation_msg;
	
	//To select attach option
	@FindBy(how = How.XPATH, using = "//span[text()='Inbox']")
	WebElement btn_inbox;
	
	//To open first email 
	@FindBy(how = How.CSS, using = "span[data-test-id='senders_list']")
	WebElement text_inbox_first_email;
	
	//To get opened email subject  
	@FindBy(how = How.CSS, using = "span[data-test-id='message-group-subject-text']")
	WebElement text_inbox_email_subject;
	
	//To get opened email body content 
	@FindBy(how = How.XPATH, using = "//div[@data-test-id='message-body-container']//ul[@dir='ltr']")
	WebElement text_inbox_email_body_content;
	
	//To click on attachment link to download attached file
	@FindBy(how = How.CSS, using = "a[data-test-id='attachment-download']")
	WebElement link_inbox_attachment_file;	
	
	public MailAolPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * This method can be used for login
	 * @param userName 
	 * @param password
	 */
	public void login(String userName, String password) {
		ExtentReporter.info("Login to Aol mail account");
		
		waitForElementVisibility(icon_login, 5);
		click(icon_login);
		
		waitForElementVisibility(input_box_userName, 5);
		enterText(input_box_userName, userName);
		click(btn_next);
		
		waitForElementVisibility(input_box_password, 5);
		enterText(input_box_password, password);
		click(btn_next);
	}
	
	/**
	 * This method can be used to compose an email
	 */
	public void composeEmail() {
		hardWait(10);
		waitForElementVisibility(btn_compose, 10);
		click(btn_compose);
		
		waitForElementVisibility(input_receiver_mail_id, 5);
		enterText(input_receiver_mail_id, Config.getProperty("receiverEmailId"));
		
		enterText(input_mail_subject, Config.getProperty("emailSubject"));
		composeEmailBody();
		
	}

	public void openInboxEmail() {
		waitForElementVisibility(btn_inbox, 5);
		click(btn_inbox);
		waitForElementVisibility(text_inbox_first_email, 5);
		click(text_inbox_first_email);
	}

	/**
	 * This method can be used to get email body text
	 */
	public String getEmailBodyText() {
		hardWait(5);
		String emailBodyText = getAttribute(text_inbox_email_body_content, "innerText");
		
		return emailBodyText;
	}

	/**
	 * This method can be used to get email subject
	 */
	public String getEmailSubject() {
		waitForElementVisibility(text_inbox_email_subject, 5);
		String emailSubjectText = getAttribute(text_inbox_email_subject, "innerText");
		
		return emailSubjectText;
	}

	public void attachFileAndSendEmail(String filePath) {
		hardWait(5);
		btn_attach_option.sendKeys(generateFilePath(filePath));
		waitForElementToBeClickable(link_attached_file, 20);
		click(btn_send);
		hardWait(10);
	}

	private void composeEmailBody() {
		click(label_bulleted);
		click(label_option_bulleted);
		
		enterTextWithouClear(input_mail_body, Config.getProperty("Data1"));
		enterKey(input_mail_body, Keys.ENTER);
		enterTextWithouClear(input_mail_body, Config.getProperty("Data2"));
		enterKey(input_mail_body, Keys.ENTER);
		enterTextWithouClear(input_mail_body, Config.getProperty("Data3"));
	}
	
	public void downloadInboxAttachedFile(String folderPath) {
		deleteFilesFromFolder(folderPath);
		waitForElementVisibility(link_inbox_attachment_file, 10);
		click(link_inbox_attachment_file);
		hardWait(20);
	}
	
	
	

}
