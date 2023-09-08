package org.endava.automation.testing.Pages;

import org.endava.automation.testing.Pages.BasePage.ZeroBankBasePage;
import org.endava.automation.testing.Utils.BasePage;
import org.endava.automation.testing.Utils.Log;
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

    public String readSummaryAmountLeft() {
        Log.dataExtractionLogger("Reading summary amount left.");
        WebElement leftAmount = waitAndFindElementFromRoot(
            By.cssSelector("#summaryReport tr:last-child td:last-child"));
        String amount = leftAmount.getText();
        Log.dataExtractionLogger("Summary amount left read: " + amount);
        return amount;
    }
}
