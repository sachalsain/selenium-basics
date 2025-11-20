package com.sachalsain.selenium.basics;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class WaitsMasteryTest {

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

    @Test
    public void implicitWaitDemo() {
        // Apply implicit wait (applies to all findElement calls)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to the site
        driver.get("https://automationexercise.com");

        // Attempt to locate the logo element (implicit wait will retry automatically)
        WebElement logo = driver.findElement(By.className("logo"));

        // Validate that the logo is displayed
        Assert.assertTrue(logo.isDisplayed(), "Logo should be visible on homepage");
    }

    @Test
    public void explicitWaitVisibility() {
        // Navigate to login page
        driver.get("https://automationexercise.com/login");

        // Create explicit wait with 10-second timeout
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until the email input field becomes visible
        WebElement emailField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[data-qa='login-emails']")
                )
        );

        // Confirm the field is displayed
        Assert.assertTrue(emailField.isDisplayed(), "Email field must be visible");
    }

    @Test
    public void explicitWaitClickable() {
        // Open the login page
        driver.get("https://automationexercise.com/login");

        // Create an explicit wait instance
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for login button to be clickable
        WebElement loginButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button[data-qa='login-button']")
                )
        );

        // Click the button once it is ready
        loginButton.click();
    }

    @Test
    public void fluentWaitDemo() {
        // Navigate to homepage
        driver.get("https://automationexercise.com");

        // Create a FluentWait with custom polling and ignored exceptions
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15)) // Max wait time
                .pollingEvery(Duration.ofSeconds(2)) // Check every 2 seconds
                .ignoring(NoSuchElementException.class);       // Ignore missing elements

        // Wait until the logo appears on the page
        WebElement logo = fluentWait.until(driver
                -> driver.findElement(By.className("logo"))
        );

        // Verify logo visibility
        Assert.assertTrue(logo.isDisplayed(), "Logo should be displayed after wait");
    }

    @Test
    public void waitForAttributeChange() {
        // Open homepage
        driver.get("https://automationexercise.com");

        // Create explicit wait instance
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the 'class' attribute of the body tag to contain "loaded"
        wait.until(
                ExpectedConditions.attributeContains(
                        By.tagName("body"),
                        "class",
                        "loaded"
                )
        );

        // Validate the attribute change
        Assert.assertTrue(
                driver.findElement(By.tagName("body"))
                        .getAttribute("class")
                        .contains("loaded"),
                "Body 'class' attribute should contain 'loaded'"
        );
    }

    @Test
    public void waitForTitle() {
        // Open homepage
        driver.get("https://automationexercise.com");

        // Create explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until page title is exactly as expected
        wait.until(ExpectedConditions.titleIs("Automation Exercise"));

        // Assert the page title
        Assert.assertEquals(driver.getTitle(), "Automation Exercise", "Page title mismatch");
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
