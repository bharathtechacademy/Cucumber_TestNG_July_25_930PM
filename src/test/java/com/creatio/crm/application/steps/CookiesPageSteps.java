package com.creatio.crm.application.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.creatio.crm.application.elements.CookiesPageElements;

public class CookiesPageSteps extends CookiesPageElements{
	
	public CookiesPageSteps(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Method to verify cookies pop-up is getting displayed.
	public void verifyCookiesPopUpIsDisplayed() {
		waitForElement(cookiesHeader);
	}
	
	//Method to verify cookies pop-up content is valid.
	public void verifyCookiesPopUpContent(String expectedContent) {
		String actualContent = getElementText(cookiesContent);
		Assert.assertEquals(actualContent, expectedContent, "Cookies pop-up content is not as expected.");		
	}
	
	//Method to verify cookies pop-up logos.
	public void verifyCookiesPopUpLogos() {
		waitForElement(creatioLogo);
		waitForElement(cookieBotLogo);
	}
	
	//Method to verify cookie selection buttons within the popup.
	public void verifyCookieSelectionButtons() {
		waitForElement(allowAllButton);
		waitForElement(allowSelectionButton);
		waitForElement(denyButton);
	}
	
	//Method to verify cookie switch buttons within the popup.
	public void verifyCookieSwitchButtons() {
		waitForElement(necessarySwitchButton);
		waitForElement(preferencesSwitchButton);
		waitForElement(statisticsSwitchButton);
		waitForElement(marketingSwitchButton);
	}
	
	//Method to click on Show Details link 
	public void clickOnShowDetailsLink() {
		waitForElement(showDetailsLink);
		click(showDetailsLink);
	}
	
	//Method to verify expanded view of cookies pop-up.
	public void verifyExpandedViewOfCookiesPopUp() {
		waitForElement(cokkiesPopUpExpandedView);
	}
	
	//Method to Select selection buttons.
	public void selectCookieSelectionButton(String buttonName) {
		switch(buttonName.toLowerCase()) {
			case "allow all":
				click(allowAllButton);
				break;
			case "allow selection":
				click(allowSelectionButton);
				break;
			case "deny":
				click(denyButton);
				break;
			default:
				Assert.fail("Invalid button name provided: " + buttonName);
		}
	}
	
	//Method to verify cookies pop-up is disappeared from the page.
	public void verifyCookiesPopUpIsDisappeared() {
		waitForElementToDisappear(cookiesHeader);
	}
	

}
