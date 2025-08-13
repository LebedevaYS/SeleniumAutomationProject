package com.example;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.Set;

public class AdvancedActionsTest {

    private static WebDriver driver;
    static WebDriverWait wait;
    static Actions actions;

    private static StartingPage startingPage;
    private static DropdownPage dropdownPage;
    private static AlertPage alertPage;
    private static DragdropPage dragdropPage;
    private static NewTabPage newTabPage;

    private static String startUrl;

    @BeforeClass
    public static void setupClass() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);

        startingPage = new StartingPage(driver);
        dropdownPage = new DropdownPage(driver);
        alertPage = new AlertPage(driver);
        dragdropPage = new DragdropPage(driver);
        newTabPage = new NewTabPage(driver);

        startUrl = ConfProperties.getProperty("startingPage");
    }

    @Before
    public void openStart() {
        driver.get(startUrl);
    }

    @After
    public void cleanupWindows() {
        String original = driver.getWindowHandle();
        for (String h : driver.getWindowHandles()) {
            if (!h.equals(original)) {
                driver.switchTo().window(h);
                driver.close();
            }
        }
        driver.switchTo().window(original);
    }

    @AfterClass
    public static void teardownClass() {
        if (driver != null) driver.quit();
    }

    // ---------- DROPDOWN ----------
    @Test
    public void testDropdown() {
        startingPage.clickDropdown();
        wait.until(ExpectedConditions.presenceOfElementLocated(org.openqa.selenium.By.id("dropdown")));

        dropdownPage.selectByVisibleText("Option 1");
        Assert.assertEquals("Option 1", dropdownPage.getSelectedOption());

        dropdownPage.selectByIndex(2);
        Assert.assertEquals("Option 2", dropdownPage.getSelectedOption());
    }

    // ---------- ALERT ----------
    @Test
    public void testAlert() {
        startingPage.clickAlert();

        String text = alertPage.clickAlertAndGetTextThenAccept();
        Assert.assertEquals("I am a JS Alert", text);
    }

    // ---------- DRAG & DROP ----------
    @Test
    public void testDragAndDrop() {
        startingPage.clickDragdrop();

        String aBefore = dragdropPage.getColumnAText(); // "A"
        String bBefore = dragdropPage.getColumnBText(); // "B"

        dragdropPage.doDragdrop();

        wait.until(d -> {
            String aAfter = dragdropPage.getColumnAText();
            String bAfter = dragdropPage.getColumnBText();
            return aAfter.equals(bBefore) && bAfter.equals(aBefore);
        });

        Assert.assertEquals("B", dragdropPage.getColumnAText());
        Assert.assertEquals("A", dragdropPage.getColumnBText());
    }

    // ---------- NEW TAB / WINDOWS ----------
    @Test
    public void testNewTab() {

        driver.get(ConfProperties.getProperty("newTabPage"));
        String original = driver.getWindowHandle();
        Set<String> before = driver.getWindowHandles();

        newTabPage.clickNewTab();

        wait.until(d -> d.getWindowHandles().size() > before.size());

        for (String h : driver.getWindowHandles()) {
            if (!h.equals(original)) {
                driver.switchTo().window(h);
                break;
            }
        }

        WebElement h3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h3")));
        Assert.assertEquals("New Window", h3.getText());
    }
}
