package com.example;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage {
    public WebDriver driver;
    public DropdownPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(id = "dropdown")
    private WebElement dropdownElement;

    public void selectByVisibleText(String text) {
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(text);
    }

    public void selectByValue(String text) {
        Select select = new Select(dropdownElement);
        select.selectByValue(text);
    }

    public void selectByIndex(int index) {
        Select select = new Select(dropdownElement);
        select.selectByIndex(index);
    }

    public String getSelectedOption() {
        Select select = new Select(dropdownElement);
        return select.getFirstSelectedOption().getText();
    }

}
