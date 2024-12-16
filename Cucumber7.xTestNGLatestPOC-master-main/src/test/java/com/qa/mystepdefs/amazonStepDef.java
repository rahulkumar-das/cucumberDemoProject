package com.qa.mystepdefs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.amazonPage;
import io.cucumber.java.en.Then;

public class amazonStepDef {

	WebDriver driver = Hooks.driver;
	private amazonPage amazonPage;
	ExtentTest test = Hooks.test;

	@Given("I navigate to amazon us website")
	public void i_navigate_to_amazon_us_website() {
		test.info("Navigating to the homepage");
		driver.get("https://www.amazon.com/");
		amazonPage = new amazonPage(driver);
		test.pass("Successfully navigated to the Amazon US page");
	}

	@And("search for {string}")
	public void searchForItem(String item) {
		amazonPage.search(item);
		test.pass("Searched for "+item);
	}

	@Then("I click on first item")
	public void i_click_on_first_item() {
		amazonPage.selectItem();
		test.pass("Clicked on the first result");
	}

	@Then("add the item to cart")
	public void add_the_item_to_cart() {
		amazonPage.addToCart();
		test.pass("Added item to the cart");
	}

	@Then("I navigate to my cart")
	public void i_navigate_to_my_cart() {
		amazonPage.navigateToCart();
		test.pass("Checking the cart");
	}

}
