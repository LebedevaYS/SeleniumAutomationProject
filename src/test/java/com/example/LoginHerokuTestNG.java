package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class LoginHerokuTestNG {
    private WebDriver driver;
    private Actions actions;
    private LoginHerokuPage loginPage;
    private ProfileHerokuPage profilePage;


    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://the-internet.herokuapp.com/login");
        loginPage = new LoginHerokuPage(driver);
        profilePage = new ProfileHerokuPage(driver);
    }

    @Test
    public void loginTest() {
        loginPage.inputLogin("tomsmith");
        loginPage.inputPassword("SuperSecretPassword!");
        loginPage.clickLoginBtn();

        String message = profilePage.getFlashMessage();
        Assert.assertTrue(message.contains("You logged into a secure area!"), "Login failed!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
