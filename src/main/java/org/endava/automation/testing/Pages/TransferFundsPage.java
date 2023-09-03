package org.endava.automation.testing.Pages;

import org.endava.automation.testing.Enums.TransferFundsAccountTypesEnum;
import org.endava.automation.testing.Pages.BasePage.ZeroBankBasePage;
import org.endava.automation.testing.Utils.BasePage;
import org.endava.automation.testing.Utils.Log;
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

    public void chooseFromAccount(TransferFundsAccountTypesEnum fromAccount) {
        Log.uiLogger("Choosing 'From Account': " + fromAccount.toString());
        chooseFromDDSOptionContainsText(ddlFromAccount, fromAccount.toString());
    }

    public void chooseToAccount(TransferFundsAccountTypesEnum toAccount) {
        Log.uiLogger("Choosing 'To Account': " + toAccount.toString());
        chooseFromDDSOptionContainsText(ddlToAccount, toAccount.toString());
    }

    public void insertAmountValue(String amount) {
        Log.uiLogger("Inserting amount value: " + amount);
        clearAndSendKeys(inputAmount, amount);
    }

    public void insertDescription(String description) {
        Log.uiLogger("Inserting description: " + description);
        // inputDescription.findElement(By.xpath("./..")).getAttribute("innerHTML")
        // input description parent html:
//                                    <input type="text" id="tf_description" class="span4" disabled="" value="Test Description">
//                                    <input type="hidden" name="description" value="Test Description">
        clearAndSendKeys(inputDescription, description);
    }

    public void clickSubmit() {
        Log.uiLogger("Clicking the 'Submit' button.");
        btnSubmit.click();
    }

    public void insertAllValuesAndClickContinue(TransferFundsAccountTypesEnum fromAccount,
                                                TransferFundsAccountTypesEnum toAccount, String amount,
                                                String description) {
        chooseFromAccount(fromAccount);
        chooseToAccount(toAccount);
        insertAmountValue(amount);
        insertDescription(description);
        clickSubmit();
    }

    public String readValueFromAccountFromField() {
        Log.uiLogger("Reading value from 'From Account' field.");
        return getValueFromInputField(ddlFromAccount);
    }

    public String readValueFromDescriptionField() {
        Log.uiLogger("Reading value from 'Description' field.");
        return getValueFromInputField(inputDescription);

    }

    private String getValueFromInputField(WebElement inputField) {
        return inputField.getAttribute("value");
    }
}
