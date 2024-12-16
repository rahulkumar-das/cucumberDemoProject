package pages;


import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.messages.types.Duration;


public class amazonPage {

	  private WebDriver driver;
	  
	  private By searchBar = By.xpath("//input[@id='twotabsearchtextbox']");
	  private By searchButton = By.xpath("//input[@id='nav-search-submit-button']");
	  private By itemsImageList = By.xpath("//div[@class['s-image-padding']]//a[@class='a-link-normal s-no-outline']/div[@class='a-section aok-relative s-image-tall-aspect']");
	  private By addToCart = By.xpath("//input[@id='add-to-cart-button']");
	  private By cart = By.xpath("//a[@href='/cart?ref_=sw_gtc']");
	  private By cartPageValidation = By.xpath("//h2[contains(text(), \"Shopping Cart\")]");
	  
	  
	  // Constructor
	    public amazonPage(WebDriver driver) {
	        this.driver = driver;
	    }

		public void search(String item) {
			WebElement searchInput = driver.findElement(searchBar);
			searchInput.sendKeys(item);
			
			 WebElement searchBtn = driver.findElement(searchButton);
			 searchBtn.click();
			
		}

		public void selectItem() {
			// Locate all elements matching the XPath
			List<WebElement> items = driver.findElements(itemsImageList);

			// Check if the list is not empty to avoid IndexOutOfBoundsException
			if (!items.isEmpty()) {
			    // Click on the first WebElement in the list
			    items.get(0).click();
			} else {
			    System.out.println("No elements found matching the given XPath.");
			}

			
		}

		public void addToCart() {
			 WebElement addToCartButton = driver.findElement(addToCart);
			 addToCartButton.click();
			
		}

		public void navigateToCart() {

			 WebElement cartPage = driver.findElement(cart);
			 cartPage.click();
			 WebElement shoppingCartElement = driver.findElement(cartPageValidation);
			 
			// Check if the element is displayed
             if (shoppingCartElement.isDisplayed()) {
                 System.out.println("The 'Shopping Cart' element is displayed.");
             } else {
                 System.out.println("The 'Shopping Cart' element is not displayed.");
             }
			
		}
}
