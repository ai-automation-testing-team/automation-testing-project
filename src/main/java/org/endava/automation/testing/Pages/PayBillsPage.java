package org.endava.automation.testing.Pages;

import org.endava.automation.testing.Pages.BasePage.ZeroBankBasePage;
import org.endava.automation.testing.Utils.BasePage;
import org.endava.automation.testing.Utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PayBillsPage extends ZeroBankBasePage {

    @FindBy(css = "#ui-tabs-2 fieldset")
    private WebElement addNewPayeeForm;

    public PayBillsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage newInstance(WebDriver driver) {
        return new PayBillsPage(driver);
    }

    public void choosePayee(String payee) {
        Log.uiLogger("Choosing payee: " + payee);
        WebElement ddlPayee = waitAndFindElementFromRoot(By.id("sp_payee"));
        chooseFromDDSOptionContainsText(ddlPayee, payee);
    }

    public void chooseAccount(String account) {
        Log.uiLogger("Choosing account: " + account);
        WebElement ddlAccount = waitAndFindElementFromRoot(By.id("sp_account"));
        chooseFromDDSOptionContainsText(ddlAccount, account);
    }

    public void insertAmount(String amount) {
        Log.uiLogger("Inserting amount: " + amount);
        WebElement inputAmount = waitAndFindElementFromRoot(By.id("sp_amount"));
        clearAndSendKeys(inputAmount, amount);
    }

    public void insertDate(String date) {
        Log.uiLogger("Inserting date: " + date);
        WebElement inputDate = waitAndFindElementFromRoot(By.id("sp_date"));
        clearAndSendKeys(inputDate, date + Keys.TAB);
    }

    public void insertDescription(String description) {
        Log.uiLogger("Inserting description: " + description);
        WebElement inputDescription = waitAndFindElementFromRoot(By.id("sp_description"));
        clearAndSendKeys(inputDescription, description);
    }

    public void clickPayButton() {
        Log.uiLogger("Clicking the 'Pay' button.");
        WebElement btnPay = waitAndFindElementFromRoot(By.id("pay_saved_payees"));
        waitForElementToBeClickableAndClick(btnPay);
    }

    public String returnDescriptionValueAfterPay(String payee, String account, String amount, String date,
                                                 String description) {
        paySavedPayee(payee, account, amount, date, description);
        WebElement descriptionValue = waitAndFindElementFromRoot(By.id("sp_description"));
        clickPayButton();
        Log.userFlowLogger("User paid and the page is refreshed.");
        return getElementValue(descriptionValue);
    }

    public void paySavedPayee(String payee, String account, String amount, String date, String description) {
        choosePayee(payee);
        chooseAccount(account);
        insertAmount(amount);
        insertDate(date);
        insertDescription(description);
    }

    public void insertPayeeName(String payeeName) {
        Log.uiLogger("Inserting Payee Name: " + payeeName);
        WebElement inputPayeeName = waitAndFindElementFromRoot(By.id("np_new_payee_name"));
        clearAndSendKeys(inputPayeeName, payeeName);
    }

    public void insertPayeeAddress(String payeeAddress) {
        Log.uiLogger("Inserting Payee Address: " + payeeAddress);
        WebElement inputPayeeAddress = waitAndFindElement(addNewPayeeForm, By.id("np_new_payee_address"));
        clearAndSendKeys(inputPayeeAddress, payeeAddress);
    }

    public void insertPayeeAccount(String payeeAccount) {
        Log.uiLogger("Inserting Payee Account: " + payeeAccount);
        WebElement inputPayeeAccount = waitAndFindElement(addNewPayeeForm, By.id("np_new_payee_account"));
        clearAndSendKeys(inputPayeeAccount, payeeAccount);
    }

    public void insertPayeeDetails(String payeeDetails) {
        Log.uiLogger("Inserting Payee Details: " + payeeDetails);
        WebElement inputPayeeDetails = waitAndFindElement(addNewPayeeForm, By.id("np_new_payee_details"));
        clearAndSendKeys(inputPayeeDetails, payeeDetails);
    }

    public void clickAddButton() {
        Log.uiLogger("Clicking the 'Add' button.");
        WebElement addPayeeButton = waitAndFindElementFromRoot(By.id("add_new_payee"));
        waitForElementToBeClickableAndClick(addPayeeButton);
    }

    public void addNewPayee(String payeeName, String payeeAddress, String payeeAccount, String payeeDetails) {
        navigateToAddNewPayeeTab();
        insertPayeeName(payeeName);
        insertPayeeAddress(payeeAddress);
        insertPayeeAccount(payeeAccount);
        insertPayeeDetails(payeeDetails);
        clickAddButton();
    }

    public void navigateToAddNewPayeeTab() {
        Log.uiLogger("Clicking the 'Add New Payee' link button.");
        WebElement linkText = waitAndFindElementFromRoot(By.linkText("Add New Payee"));
        waitForElementToBeClickableAndClick(linkText);
    }

    public boolean validatePayeeIsAdded() {
        WebElement messageSuccessElement = waitAndFindElementFromRoot(By.id("alert_content"));
        String messageSuccess = messageSuccessElement.getText();
        Log.uiLogger("Reading transaction message: " + messageSuccess);
        return messageSuccess.contains("successfully");
    }
}
