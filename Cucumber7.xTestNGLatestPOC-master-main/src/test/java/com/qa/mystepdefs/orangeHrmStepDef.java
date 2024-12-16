package com.qa.mystepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.OrangeHRMPage;
import com.aventstack.extentreports.ExtentTest;
public class orangeHrmStepDef {
	
	WebDriver driver = Hooks.driver;
	OrangeHRMPage orangeHRMPage = new OrangeHRMPage(driver);
	ExtentTest test = Hooks.test;
	
	@Given("I navigate to the orange hrm website")
    public void iNavigateToTheOrangeHrmWebsite() {
		 orangeHRMPage.navigateToOrangeHrmWebsite("http://opensource-demo.orangehrmlive.com/");
	}
	
	@And("I enter the admin name {string} and admin password {string}")
    public void iEnterTheAdminNameAndAdminPassword(String adminName, String adminPassword) {
		 orangeHRMPage.enterAdminCredentials(adminName, adminPassword);
	}
	
	@Then("I click on Login button")
    public void iClickOnLoginButton() {
		orangeHRMPage.clickLoginButton();
	}
	
	@And("I handle the chrome alert box")
    public void iHandleTheChromeAlertBox() {
		 orangeHRMPage.handleChromeAlertBox();
	}
	
	@Then("I click on admin button")
    public void iClickOnAdminButton() {
		orangeHRMPage.clickAdminButton();
	}
	
	@And("I enter the username {string}")
    public void iSelectTheUserRoleAndEnterTheUsername(String username) throws InterruptedException {
		orangeHRMPage.selectUserRoleAndEnterUsername(username);
	}
	
	@Then("I will verify if the records should be displayed {string}")
    public void iWillVerifyIfTheRecordsShouldBeDisplayed(String shouldBeDisplayed) {
		 boolean result = orangeHRMPage.verifyRecordsDisplayed(shouldBeDisplayed);
	        if (result) {
	            test.pass("Records displayed as expected.");
	        } else {
	            test.fail("Records display verification failed.");
	        }
	}
	

}
