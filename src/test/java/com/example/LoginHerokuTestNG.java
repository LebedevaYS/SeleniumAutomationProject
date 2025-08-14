package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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

    @DataProvider(name = "logins")
    public Object[][] loginData() {
        return new Object[][] {
                //{ConfProperties.getProperty("rightlogin"), ConfProperties.getProperty("rightpassword"), "You logged into a secure area!"},
                {ConfProperties.getProperty("wronglogin"), ConfProperties.getProperty("wrongpassword"), "Your username is invalid!"}
        };
    }

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        loginPage = new LoginHerokuPage(driver);
        profilePage = new ProfileHerokuPage(driver);
        driver.get("https://the-internet.herokuapp.com/login");
    }


    @Test(dataProvider = "logins")
    public void loginTest(String username, String password, String expectedMessage) {
        loginPage.inputLogin(username);
        loginPage.inputPassword(password);
        loginPage.clickLoginBtn();

        String actualMessage = profilePage.getFlashMessage();
        System.out.print("message:" + actualMessage);
        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "Login result does not match expected message!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
