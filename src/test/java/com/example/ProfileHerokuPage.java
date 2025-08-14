package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfileHerokuPage {
    private WebDriver driver;

    public ProfileHerokuPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(id = "flash")
    private WebElement flashMessage;

    public String getFlashMessage() {
        return flashMessage.getText().trim();
    }
}
