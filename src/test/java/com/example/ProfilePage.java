package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {
    public WebDriver driver;
    public ProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }
    @FindBy(xpath = "//*[contains(@class, 'TitleWithDeviceList')]")
    private WebElement Text;

    public String getTextToCompare() {
        String compText = Text.getText();
        return compText;
    }
}
