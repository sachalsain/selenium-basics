package com.sachalsain.selenium.basics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class LocatorMasteryTest {

    // Declare WebDriver instance to control the browser
    WebDriver driver;

    // Setup method that runs before each test class
    @BeforeClass
    public void setup() {
        // Initialize ChromeDriver to use Google Chrome browser
        driver = new ChromeDriver();
        // Maximize the browser window for better visibility
        driver.manage().window().maximize();
    }

    // Test method demonstrating different types of locators
    @Test
    public void locatorTypesDemo() {
        // Navigate to the Automation Exercise website
        driver.get("https://automationexercise.com");
        // Find element by ID attribute
        driver.findElement(By.id("header"));
        // Find element by CSS class name
        driver.findElement(By.className("logo"));
        // Find link element by exact text match
        driver.findElement(By.linkText("Signup / Login"));
        // Find link element by partial text match
        driver.findElement(By.partialLinkText("Signup"));
        // Find element using CSS selector with placeholder attribute
        driver.findElement(By.cssSelector("input[placeholder='Your email address']"));
        // Find element using CSS selector with type attribute
        driver.findElement(By.cssSelector("input[type='email']"));
        // Find element using XPath with text content matching
        driver.findElement(By.xpath("//a[text()=' Signup / Login']"));
    }

    // Test method demonstrating web element interactions
    @Test
    public void interactionDemo() {
        // Navigate to the login page
        driver.get("https://automationexercise.com/login");

        // Find email input field using CSS selector with data-qa attribute
        WebElement email = driver.findElement(By.cssSelector("input[data-qa='login-email']"));
        // Enter text into the email field
        email.sendKeys("test@example.com");
        // Clear the previously entered text
        email.clear();
        // Enter new text into the email field
        email.sendKeys("final@example.com");

        // Find login button using CSS selector
        WebElement button = driver.findElement(By.cssSelector("button[data-qa='login-button']"));
        // Click the login button to submit the form
        button.click();
    }

    // Test method demonstrating browser navigation
    @Test
    public void navigationDemo() {
        // Navigate to Google website
        driver.get("https://google.com");
        // Navigate to Bing website (replacing current page)
        driver.get("https://bing.com");

        // Navigate back to previous page (Google)
        driver.navigate().back();
        // Navigate forward to next page (Bing)
        driver.navigate().forward();
        // Refresh the current page
        driver.navigate().refresh();

        // Assert that the page title has some content (length > 0)
        Assert.assertTrue(driver.getTitle().length() > 0);
    }

    // Test method demonstrating advanced locator strategies
    @Test
    public void advancedLocators() {
        // Navigate to Automation Exercise website
        driver.get("https://automationexercise.com");

        // Find element using XPath with parent-child relationship
        // Locates anchor tags inside list items within unordered list with specific class
        driver.findElement(By.xpath("//ul[@class='nav navbar-nav']/li/a"));
        
        // Find element using CSS selector with class chaining
        // Locates the same element using CSS selector syntax
        driver.findElement(By.cssSelector("ul.nav.navbar-nav li a"));
    }

    // Test method demonstrating assertions for verification
    @Test
    public void assertionDemo() {
        // Navigate to Automation Exercise website
        driver.get("https://automationexercise.com");

        // Get the title of the current webpage
        String homepageTitle = driver.getTitle();
        // Assert that the page title matches expected value with custom error message
        Assert.assertEquals(homepageTitle, "Automation Exercise", "Homepage title mismatch!");

        // Find the logo element by class name
        WebElement logo = driver.findElement(By.className("logo"));
        // Assert that the logo is visible on the page with custom error message
        Assert.assertTrue(logo.isDisplayed(), "Logo is not visible!");
    }

    // Cleanup method that runs after all tests complete
    @AfterTest
    public void tearDown() {
        // Check if driver instance exists before trying to close it
        if (driver != null) {
            // Close the browser and end the WebDriver session
            driver.quit();
        }
    }

}
