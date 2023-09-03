package org.endava.automation.testing.Pages;

import org.endava.automation.testing.Pages.BasePage.ZeroBankBasePage;
import org.endava.automation.testing.Utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyMoneyMapPage extends ZeroBankBasePage {

    public MyMoneyMapPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage newInstance(WebDriver driver) {
        return new MyMoneyMapPage(driver);
    }

    public String readSummaryLeftAmount() {
        WebElement leftAmount = waitAndFindElementFromRoot(
            By.cssSelector("#summaryReport tr:last-child td:last-child"));
        return leftAmount.getText();
    }

}
