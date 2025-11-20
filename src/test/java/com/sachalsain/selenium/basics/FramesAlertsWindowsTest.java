package com.sachalsain.selenium.basics;

import org.openqa.selenium.Alert;
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
public class FramesAlertsWindowsTest {

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
    public void handleSimpleAlert() {
        // Navigate to a site with alerts
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        // Click the button that opens a simple alert
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

        // Switch driver focus to alert
        Alert alert = driver.switchTo().alert();

        // Accept the alert (like clicking OK)
        alert.accept();

        // Validate result message
        String result = driver.findElement(By.id("result")).getText();
        Assert.assertTrue(result.contains("You successfully clicked an alert"));
    }

    @Test
    public void handleConfirmAcceptedAlert() {
        // Open alert page
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        // Trigger confirm alert
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        // Switch to alert
        Alert alert = driver.switchTo().alert();

        // Click Ok (accept alert)
        alert.accept();

        // Validate the message displayed after dismiss
        String result = driver.findElement(By.id("result")).getText();
        Assert.assertTrue(result.contains("You clicked: Ok"));
    }

    @Test
    public void handleConfirmRejectedAlert() {
        // Open alert page
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        // Trigger confirm alert
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        // Switch to alert
        Alert alert = driver.switchTo().alert();

        // Click Cancel (dismiss alert)
        alert.dismiss();

        // Validate the message displayed after dismiss
        String result = driver.findElement(By.id("result")).getText();
        Assert.assertTrue(result.contains("You clicked: Cancel"));
    }

    @Test
    public void handlePromptAcceptAlert() {
        // Open prompt alert page
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        // Click the prompt alert button
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        // Switch to alert
        Alert alert = driver.switchTo().alert();

        // Enter text inside the prompt popup
        alert.sendKeys("Tahreem Automation");

        // Accept the prompt
        alert.accept();

        // Validate the returned text
        String result = driver.findElement(By.id("result")).getText();
        Assert.assertTrue(result.contains("Tahreem Automation"));
    }

    @Test
    public void handlePromptRejectAlert() {
        // Open prompt alert page
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        // Click the prompt alert button
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        // Switch to alert
        Alert alert = driver.switchTo().alert();

        // Enter text inside the prompt popup
        alert.sendKeys("Tahreem Automation");

        // Cancel the prompt
        alert.dismiss();

        // Validate the returned text
        String result = driver.findElement(By.id("result")).getText();
        Assert.assertTrue(result.contains("null"));
    }

    @Test
    public void handleiFrame() {
        // Open a page with an iframe
        driver.get("https://the-internet.herokuapp.com/iframe");

        // Switch into the iframe by ID
        driver.switchTo().frame("mce_0_ifr");

        // Locate the text box inside iframe
        WebElement textBox = driver.findElement(By.id("tinymce"));

        // Clear existing text
//        textBox.clear();
        // Type new text
//        textBox.sendKeys("Learning Selenium iFrames!");
        // Switch back to main page
        driver.switchTo().defaultContent();

        // Validate page heading outside iframe
        String heading = driver.findElement(By.tagName("h3")).getText();
        Assert.assertTrue(heading.contains("Editor"));
    }

    @Test
    public void handleNestedFrames() {
        // Open nested frames example
        driver.get("https://the-internet.herokuapp.com/nested_frames");

        // Switch to parent frame: frame-top
        driver.switchTo().frame("frame-top");

        // Switch to child frame: frame-left
        driver.switchTo().frame("frame-left");

        // Validate content inside nested frame
        String text = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue(text.contains("LEFT"));

        // Return to default page
        driver.switchTo().defaultContent();
    }

    @Test
    public void handleMultipleWindows() {
        // Navigate to page with multiple window example
        driver.get("https://the-internet.herokuapp.com/windows");

        // Click on link to open new window
        driver.findElement(By.linkText("Click Here")).click();

        // Store the current window handle
        String parentWindow = driver.getWindowHandle();

        // Switch to the newly opened window
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
            }
        }

        // Validate heading in new window
        String heading = driver.findElement(By.tagName("h3")).getText();
        Assert.assertEquals(heading, "New Window");

        // Close the new window
        driver.close();

        // Switch back to parent window
        driver.switchTo().window(parentWindow);

        // Validate main page title
        Assert.assertTrue(driver.getTitle().contains("The Internet"));
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
