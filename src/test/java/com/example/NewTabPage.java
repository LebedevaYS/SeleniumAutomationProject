package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.example.AdvancedActionsTest.actions;

public class NewTabPage {
    public WebDriver driver;
    public NewTabPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(linkText = "Click Here")
    private WebElement newTabLink;

    public void clickNewTab() {
        newTabLink.click();
    }
}
