package com.qa.mystepdefs;

import io.cucumber.java.Before;
import io.cucumber.java.After;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.Scenario;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setup() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize(); // Optional: Maximize the browser window
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null; // Ensure driver is reset for the next test run
        }
    }
     
    private static ExtentReports extentReports;
    private static ExtentSparkReporter sparkReporter;
    public static ExtentTest test;

    @Before(order = 0) // Ensure this runs first
    public void setupReport() {
        if (extentReports == null) { // Initialize only once
            // Define report file path
            String reportPath = "test-output/ExtentReport.html";
            File reportFile = new File(reportPath);

            // Initialize Spark Reporter
            sparkReporter = new ExtentSparkReporter(reportPath);

            // If file exists, rename it with a timestamp
            if (reportFile.exists()) {
                String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
                File archivedReport = new File("test-output/ExtentReport_" + timestamp + ".html");
                reportFile.renameTo(archivedReport);
            }

            // Configure the report
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle("Automation Report");
            sparkReporter.config().setReportName("Cucumber Test Report");

            // Attach reporter to ExtentReports
            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);

            // Add system info to the report
            extentReports.setSystemInfo("OS", System.getProperty("os.name"));
            extentReports.setSystemInfo("Browser", "Chrome");
        }
    }


    @Before
    public void beforeScenario(Scenario scenario) {
        // Create a test node for each scenario
        test = extentReports.createTest(scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        // Log the scenario status
        if (scenario.isFailed()) {
            test.fail("Scenario failed: " + scenario.getName());
        } else {
            test.pass("Scenario passed: " + scenario.getName());
        }
    }

    @After
    public void tearDownReport() {
        if (extentReports != null) {
            extentReports.flush(); // Generate the report
        }
    }
}

