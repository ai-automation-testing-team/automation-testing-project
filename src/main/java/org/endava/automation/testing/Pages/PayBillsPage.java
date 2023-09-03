package org.endava.automation.testing.Pages;

import org.endava.automation.testing.Pages.BasePage.ZeroBankBasePage;
import org.endava.automation.testing.Utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PayBillsPage extends ZeroBankBasePage {

    public PayBillsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage newInstance(WebDriver driver) {
        return new PayBillsPage(driver);
    }

    public void choosePayee(String payee) {
        WebElement ddlPayee = waitAndFindElementFromRoot(By.id("sp_payee"));
        chooseFromDDSOptionContainsText(ddlPayee, payee);
    }

    public void chooseAccount(String account) {
        WebElement ddlAccount = waitAndFindElementFromRoot(By.id("sp_account"));
        chooseFromDDSOptionContainsText(ddlAccount, account);
    }

    public void insertAmount(String amount) {
        WebElement inputAmount = waitAndFindElementFromRoot(By.id("sp_amount"));
        clearAndSendKeys(inputAmount, amount);
    }

    public void insertDate(String date) {
        WebElement inputDate = waitAndFindElementFromRoot(By.id("sp_date"));
        clearAndSendKeys(inputDate, date + Keys.TAB);
    }

    public void insertDescription(String description) {
        WebElement inputDescription = waitAndFindElementFromRoot(By.id("sp_description"));
        clearAndSendKeys(inputDescription, description);
    }

    public void clickPay() {
        WebElement btnPay = waitAndFindElementFromRoot(By.id("pay_saved_payees"));
        waitForElementToBeClickableAndClick(btnPay);
    }

    public String returnDescriptionValueAfterPay(String payee, String account, String amount, String date,
                                                 String description) {
        insertAllValues(payee, account, amount, date, description);
        WebElement descriptionValue = waitAndFindElementFromRoot(By.id("sp_date"));
        clickPay();
        return descriptionValue.getAttribute("value");
    }

    public void insertAllValues(String payee, String account, String amount, String date, String description) {
        choosePayee(payee);
        chooseAccount(account);
        insertAmount(amount);
        insertDate(date);
        insertDescription(description);
    }
}
