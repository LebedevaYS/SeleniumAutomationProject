package com.example;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class AlertPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public AlertPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[text()='Click for JS Alert']")
    private WebElement alertElement;

    public String clickAlertAndGetTextThenAccept() {
        alertElement.click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
        return text;
    }
}
