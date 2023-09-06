package org.endava.automation.testing.Utils;

import java.time.Duration;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Getter
public abstract class BasePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private AjaxElementLocatorFactory factory;
    private Actions actions;
    private JavascriptExecutor javascriptExecutor;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.factory = new AjaxElementLocatorFactory(driver, ConfigurationConstants.MAX_RETRY_FOR_LOCATING_ELEMENT_AJAX_FACTORY);
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigurationConstants.MAX_RETRY_FOR_LOCATING_ELEMENT));
        actions = new Actions(driver);
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    public abstract BasePage newInstance(WebDriver driver);

    public <T extends BasePage> BasePage navigateTo(String url, T type) {
        driver.get(url);
        return type.newInstance(driver);
    }

    protected void moveToElement(WebElement element) {
        Log.elementLocatingLogger("Moving to the element: " + element.toString());
        actions.moveToElement(element);
        actions.perform();
        Log.elementLocatingLogger("Moved to the element successfully.");
    }

    protected void clearAndSendKeys(WebElement element, String text) {
        Log.uiLogger("Clearing and sending keys to element: " + element.toString());
        wait.until(ExpectedConditions.visibilityOf(element));
        moveToElement(element);
        element.clear();
        element.sendKeys(text);
        Log.uiLogger("Cleared and sent keys successfully.");
    }

    protected void waitForElementToBeClickableAndClick(WebElement elem) {
        Log.uiLogger("Waiting for the element to be clickable: " + elem.toString());
        moveToElement(elem);
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        elem.click();
        Log.uiLogger("Element is clickable and has been clicked.");
    }

    protected WebElement waitAndFindElement(WebElement root, By byLocator) {
        Log.elementLocatingLogger("Waiting for the element to be present within the root element.");
        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(root, byLocator));
        WebElement foundElement = root.findElement(byLocator);
        Log.elementLocatingLogger("Element found: " + foundElement.toString());
        return foundElement;
    }

    protected WebElement waitAndFindElementFromRoot(By byLocator) {
        Log.elementLocatingLogger("Waiting for the element to be present.");
        wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
        WebElement foundElement = driver.findElement(byLocator);
        Log.elementLocatingLogger("Element found: " + foundElement.toString());
        return foundElement;
    }

    protected List<WebElement> waitAndFindElements(WebElement root, By byLocator) {
        Log.elementLocatingLogger("Waiting for elements to be present within the root element.");
        wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(root, byLocator));
        List<WebElement> foundElements = root.findElements(byLocator);
        Log.elementLocatingLogger("Found " + foundElements.size() + " elements within the root element.");
        return foundElements;
    }

    protected List<WebElement> waitAndFindElementsFromRoot(By byLocator) {
        Log.elementLocatingLogger("Waiting for elements to be present.");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byLocator));
        List<WebElement> foundElements = driver.findElements(byLocator);
        Log.elementLocatingLogger("Found " + foundElements.size() + " elements.");
        return foundElements;
    }

    protected void chooseFromDDSOptionContainsText(WebElement ddl, String itemText) {
        Log.uiLogger("Choosing an option containing text: " + itemText);
        ddl.click();
        List<WebElement> ddlFromAccountsOptions = waitAndFindElements(ddl, By.tagName("option"));
        for (WebElement ddlFromAccountsOption : ddlFromAccountsOptions) {
            String optionText = ddlFromAccountsOption.getText();
            if (optionText.contains(itemText)) {
                ddlFromAccountsOption.click();
                Log.elementLocatingLogger("Option with text '" + itemText + "' selected.");
                break;
            }
        }
        waitForElementToBeClickableAndClick(ddl);
    }
}
