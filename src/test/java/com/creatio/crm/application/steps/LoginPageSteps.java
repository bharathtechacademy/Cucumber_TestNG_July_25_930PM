package com.creatio.crm.application.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.creatio.crm.application.elements.LoginPageElements;

public class LoginPageSteps extends LoginPageElements{
	
	public LoginPageSteps(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Method to verify login page is getting displayed.
	public void verifyLoginPageDisplayed() {
		waitForElement(loginPageHeader);
	}
	
	//Method to enter business email and password within the login page.
	public void enterBusinessEmailAndPassword(String businessEmail, String password) {
		waitForElement(businessEmailTextbox);
		enterText(businessEmailTextbox, businessEmail);
		waitForElement(passwordTextbox);
		enterText(passwordTextbox, password);
	}
	
	//Method to click on login button within the login page.
	public void clickOnLoginButton() {
		waitForElement(loginButton);
		click(loginButton);
	}
	
	//Method to verify sign up header within the login page.
	public void verifySignUpHeader() {
		waitForElement(signUpHeader);
	}
	
	//Method to click on sign up link within the login page.
	public void clickOnSignUpLink() {
		waitForElement(signUpLink);
		click(signUpLink);
	}
	
	//Method to click on forgot password link within the login page.
	public void clickOnForgotPasswordLink() {
		waitForElement(forgotPasswordLink);
		click(forgotPasswordLink);
	}
	
	//Method to verify social media icons.
	public void verifySocialMediaIcons() {
		waitForElement(linkedInIcon);
		waitForElement(googleIcon);
		waitForElement(facebookIcon);
	}	
	
}
