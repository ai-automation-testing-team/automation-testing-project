package org.endava.automation.testing.Pages;

import org.endava.automation.testing.Pages.BasePage.ZeroBankBasePage;
import org.endava.automation.testing.Utils.BasePage;
import org.endava.automation.testing.Utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TransferFundsPage extends ZeroBankBasePage {

    @FindBy(id = "tf_fromAccountId")
    private WebElement ddlFromAccount;

    @FindBy(id = "tf_toAccountId")
    private WebElement ddlToAccount;

    @FindBy(id = "tf_amount")
    private WebElement inputAmount;

    @FindBy(id = "tf_description")
    private WebElement inputDescription;

    @FindBy(id = "btn_submit")
    private WebElement btnSubmit;

    public TransferFundsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage newInstance(WebDriver driver) {
        return new TransferFundsPage(driver);
    }

    public void chooseFromAccount(String fromAccount) {
        Log.uiLogger("Choosing 'From Account': " + fromAccount);
        chooseFromDDSOptionContainsText(ddlFromAccount, fromAccount);
    }

    public void chooseToAccount(String toAccount) {
        Log.uiLogger("Choosing 'To Account': " + toAccount);
        chooseFromDDSOptionContainsText(ddlToAccount, toAccount);
    }

    public void insertAmountValue(String amount) {
        Log.uiLogger("Inserting amount value: " + amount);
        clearAndSendKeys(inputAmount, amount);
    }

    public void insertDescription(String description) {
        Log.uiLogger("Inserting description: " + description);
        clearAndSendKeys(inputDescription, description);
    }

    public void clickSubmit() {
        Log.uiLogger("Clicking the 'Submit' button.");
        btnSubmit.click();
    }

    public void clickContinue() {
        Log.uiLogger("Clicking the 'Continue' button.");
        btnSubmit.click();
    }

    public void insertAllValuesAndClickContinue(String fromAccount, String toAccount,
                                                String amount, String description) {
        chooseFromAccount(fromAccount);
        chooseToAccount(toAccount);
        insertAmountValue(amount);
        insertDescription(description);
        clickContinue();
    }

    public String readValueFromAccountFromField() {
        Log.uiLogger("Reading value from 'From Account' field.");
        return getValueFromInputField(ddlFromAccount);
    }

    public String readValueFromDescriptionField() {
        Log.uiLogger("Reading value from 'Description' field.");
        return getValueFromInputField(inputDescription);
    }

    public String readTransactionAmount() {
        Log.uiLogger("Reading Amount field after transaction");
        WebElement amount = waitAndFindElementFromRoot(By.cssSelector(".board-content .row:last-child div:last-child"));
        String transactionAmount = amount.getText();
        Log.uiLogger("Transaction Amount value is: " + transactionAmount);
        return transactionAmount;
    }

    public boolean validateTransactionIsSubmittedSuccessfully() {
        WebElement messageSuccessElement = waitAndFindElementFromRoot(By.className("alert-success"));
        String messageSuccess = messageSuccessElement.getText();
        Log.uiLogger("Reading transaction message: " + messageSuccess);
        return messageSuccess.contains("successfully");
    }
}
