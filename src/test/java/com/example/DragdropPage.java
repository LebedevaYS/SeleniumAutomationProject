package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.example.AdvancedActionsTest.actions;
import static com.example.AdvancedActionsTest.wait;

public class DragdropPage {
    public WebDriver driver;
    Actions actions;
    public DragdropPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.actions = new Actions(driver);
    }
    @FindBy(id = "column-a")
    private WebElement columnAElement;

    @FindBy(id = "column-b")
    private WebElement columnBElement;



    public void doDragdrop() {
        actions.dragAndDrop(columnAElement, columnBElement).perform();
    }

    public String getColumnAText() {
        return columnAElement.getText();
    }

    public String getColumnBText() {
        return  columnBElement.getText();
    }
}
