package org.endava.automation.testing.Pages;

import org.endava.automation.testing.Pages.BasePage.ZeroBankBasePage;
import org.endava.automation.testing.Utils.BasePage;
import org.openqa.selenium.WebDriver;

public class AccountSummaryPage extends ZeroBankBasePage {

    public AccountSummaryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage newInstance(WebDriver driver) {
        return new AccountSummaryPage(driver);
    }
}
