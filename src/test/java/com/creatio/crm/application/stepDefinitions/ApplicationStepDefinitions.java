package com.creatio.crm.application.stepDefinitions;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.creatio.crm.application.steps.CookiesPageSteps;
import com.creatio.crm.application.steps.HomePageSteps;
import com.creatio.crm.application.steps.LoginPageSteps;
import com.creatio.crm.application.steps.SignUpPageSteps;
import com.creatio.crm.framework.base.BasePage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ApplicationStepDefinitions {

	public LoginPageSteps loginPage;
	public HomePageSteps homePage;
	public CookiesPageSteps cookiesPage;
	public SignUpPageSteps signUpPage;

	@Given("Initialized all the page classes")
	public void initializePages() {
		WebDriver driver = BasePage.getDriver();
		loginPage = new LoginPageSteps(driver);
		homePage = new HomePageSteps(driver);
		cookiesPage = new CookiesPageSteps(driver);
		signUpPage = new SignUpPageSteps(driver);
	}

	@Given("Launch the Creatio application")
	public void launch_the_creatio_application() {
		loginPage.launchApplication();
	}

	@Then("Verify whether the cookies pop-up is displayed")
	public void verify_whether_the_cookies_pop_up_is_displayed() {
		cookiesPage.verifyCookiesPopUpIsDisplayed();
	}

	@Then("Verify cookies pop-up content")
	public void verify_cookies_pop_up_content(DataTable dataTable) {
		List<String> content = dataTable.asList();
		String expectedText = content.get(1);
		cookiesPage.verifyCookiesPopUpContent(expectedText);
	}

	@Then("verify the cookies pop-up logos")
	public void verify_the_cookies_pop_up_logos() {
		cookiesPage.verifyCookiesPopUpLogos();
	}

	@Then("verify the cookies pop-up selection buttons")
	public void verify_the_cookies_pop_up_selection_buttons() {
		cookiesPage.verifyCookieSelectionButtons();
	}

	@Then("verify the cookies pop-up switch buttons")
	public void verify_the_cookies_pop_up_switch_buttons() {
		cookiesPage.verifyCookieSwitchButtons();
	}

	@When("user clicks on the Show details link.")
	public void user_clicks_on_the_show_details_link() {
		cookiesPage.clickOnShowDetailsLink();
	}

	@Then("cookies pop-up should be displayed in expanded view")
	public void cookies_pop_up_should_be_displayed_in_expanded_view() {
		cookiesPage.verifyExpandedViewOfCookiesPopUp();
	}

	@When("^User click on the selection (.*)$")
	public void clickOnTheSelectButton(String buttonName) {
		cookiesPage.selectCookieSelectionButton(buttonName);
	}

	@Then("Cookies popUp should be closed")
	public void cookies_pop_up_should_be_closed() {
		cookiesPage.verifyCookiesPopUpIsDisappeared();
	}

	@When("^User enter (.*) and (.*)$")
	public void enterCredentials(String username, String password) {
		loginPage.enterBusinessEmailAndPassword(username, password);
	}

	@When("Click on the login button")
	public void click_on_the_login_button() {
		loginPage.clickOnLoginButton();
	}

	@Then("Login should be successful")
	public void login_should_be_successful() {
		homePage.verifyHomePageDisplayed();
	}

	@When("User clicks on the user profile icon")
	public void user_clicks_on_the_user_profile_icon() {
		homePage.verifyUsersLinkDisplayed();
	}

	@When("Click on the logout button")
	public void click_on_the_logout_button() {
		homePage.logoutFromApplication();
	}

	@Then("Logout should be successful")
	public void logout_should_be_successful() {
		loginPage.verifyLoginPageDisplayed();
	}

}
