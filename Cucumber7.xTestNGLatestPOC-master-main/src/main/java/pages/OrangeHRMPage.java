package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class OrangeHRMPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor to initialize the WebDriver and WebDriverWait
    public OrangeHRMPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout
    }

    // Navigate to the OrangeHRM website
    public void navigateToOrangeHrmWebsite(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    // Enter admin credentials
    public void enterAdminCredentials(String adminName, String adminPassword) {
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        usernameField.sendKeys(adminName);

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        passwordField.sendKeys(adminPassword);
    }

    // Click the Login button
    public void clickLoginButton() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        loginButton.click();
    }

    // Handle Chrome alert box
    public void handleChromeAlertBox() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (TimeoutException e) {
            System.out.println("No alert present to handle.");
        }
    }

    // Click the Admin button
    public void clickAdminButton() {
        WebElement adminButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href=\"/web/index.php/admin/viewAdminModule\"]")));
        adminButton.click();
    }

    // Select user role and enter username
    public void selectUserRoleAndEnterUsername(String username) throws InterruptedException {
//        WebElement userRoleDropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='User Role']/parent::div/parent::div/div[2]//div[text()='-- Select --']")));
//        userRoleDropdownElement.click();
//        userRoleDropdownElement.sendKeys(userRole);

        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Username']/parent::div/parent::div/div[2]//input")));
        usernameField.sendKeys(username);

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=' Search ']")));
        searchButton.click();
    }

    // Verify if the records should be displayed
    public boolean verifyRecordsDisplayed(String shouldBeDisplayed) {
        boolean isDisplayed;
        try {
            WebElement resultTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='oxd-icon bi-pencil-fill']")));
            System.out.println(resultTable.isDisplayed());
            if(Boolean.parseBoolean(shouldBeDisplayed)) {
            	Assert.assertTrue(resultTable.isDisplayed());
            } else {
            	Assert.assertFalse(resultTable.isDisplayed());
            }
            
            isDisplayed = resultTable.isDisplayed();
           
        } catch (TimeoutException e) {
            isDisplayed = false;
        }

        return isDisplayed == Boolean.parseBoolean(shouldBeDisplayed);
    }
}
