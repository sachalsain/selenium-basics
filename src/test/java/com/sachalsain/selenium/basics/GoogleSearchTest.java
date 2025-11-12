package com.sachalsain.selenium.basics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class GoogleSearchTest {
    
    WebDriver driver;
    
    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    
    @Test
    public void verifyGoogleTitle() {
        driver.get("https://www.google.com");
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Google"), "Error: Title does not contain Google.");
    }
    
    @AfterTest
    public void tearDown() {
        if(driver !=null) {
            driver.quit();
        }
    }
}
