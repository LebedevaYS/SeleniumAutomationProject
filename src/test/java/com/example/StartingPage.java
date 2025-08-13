package com.example;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StartingPage {
    public WebDriver driver;
    public StartingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(linkText = "Dropdown")
    private WebElement dropdownLink;

    @FindBy(linkText = "JavaScript Alerts")
    private WebElement alertLink;

    @FindBy(linkText = "Drag and Drop")
    private WebElement dragdropLink;

    public void clickDropdown() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownLink);
        dropdownLink.click();
    }

    public void clickAlert() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", alertLink);
        alertLink.click();
    }

    public void clickDragdrop() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dragdropLink);
        dragdropLink.click();
    }
}
