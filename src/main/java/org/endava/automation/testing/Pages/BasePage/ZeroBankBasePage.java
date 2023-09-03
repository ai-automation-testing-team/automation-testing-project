package org.endava.automation.testing.Pages.BasePage;

import org.endava.automation.testing.Enums.HeaderMenuItemsEnum;
import org.endava.automation.testing.Pages.AccountActivityPage;
import org.endava.automation.testing.Pages.MyMoneyMapPage;
import org.endava.automation.testing.Pages.PayBillsPage;
import org.endava.automation.testing.Pages.TransferFundsPage;
import org.endava.automation.testing.Utils.BasePage;
import org.endava.automation.testing.Utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class ZeroBankBasePage extends BasePage {

    public ZeroBankBasePage(WebDriver driver) {
        super(driver);
    }

    public TransferFundsPage navigateToTransferFunds() {
        WebElement linkButton = waitAndFindElementFromRoot(By.id(HeaderMenuItemsEnum.TRANSFER_FUNDS.toString()));
        linkButton.click();
        Log.navigationLogger("Navigated to 'Transfer Funds' page.");
        return new TransferFundsPage(getDriver());
    }

    public PayBillsPage navigateToPayBills() {
        WebElement linkButton = waitAndFindElementFromRoot(By.id(HeaderMenuItemsEnum.PAY_BILLS.toString()));
        linkButton.click();
        Log.navigationLogger("Navigated to 'Pay Bills' page.");
        return new PayBillsPage(getDriver());
    }

    public AccountActivityPage navigateToAccountActivity() {
        WebElement linkButton =
            waitAndFindElementFromRoot(By.id(HeaderMenuItemsEnum.ACCOUNT_ACTIVITY.toString()));
        linkButton.click();
        Log.navigationLogger("Navigated to 'Account Activity' page.");
        return new AccountActivityPage(getDriver());
    }

    public MyMoneyMapPage navigateToMyMoneyMap() {
        WebElement linkButton =
            waitAndFindElementFromRoot(By.id(HeaderMenuItemsEnum.MY_MONEY_MAP.toString()));
        linkButton.click();
        Log.navigationLogger("Navigated to 'My Money Map' page.");
        return new MyMoneyMapPage(getDriver());
    }
}
