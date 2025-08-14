package com.example;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTestNG {
    public static LoginPage loginPage;
    public static ProfilePage profilePage;
    public static WebDriver driver;

    @BeforeClass
    public void setupClass() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
    }

    @BeforeMethod
    public void openLoginPage() {
        driver.get(ConfProperties.getProperty("loginpageY"));
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String login, String expectedText) {
        loginPage.inputLogin(login);
        loginPage.clickLoginBtn();
        String actualText = profilePage.getTextToCompare();
        Assert.assertEquals(actualText, expectedText, "Текст после логина не совпадает");
    }

    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider() {
        return new Object[][]{
                {ConfProperties.getProperty("loginY"), ConfProperties.getProperty("compareTextY")}
                // Можно добавить другие кейсы, например: {"invalidLogin", "Ошибка логина"}
        };
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
