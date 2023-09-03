package org.endava.automation.testing.Pages;

import org.endava.automation.testing.Enums.TransferFundsAccountTypesEnum;
import org.endava.automation.testing.Pages.BasePage.ZeroBankBasePage;
import org.endava.automation.testing.Utils.BasePage;
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
        chooseFromDDSOptionContainsText(ddlFromAccount, fromAccount.toString());
    }

    public void chooseToAccount(TransferFundsAccountTypesEnum toAccount) {
        chooseFromDDSOptionContainsText(ddlToAccount, toAccount.toString());
    }

    public void insertAmountValue(String amount) {
        clearAndSendKeys(inputAmount, amount);
    }

    public void insertDescription(String description) {
        // inputDescription.findElement(By.xpath("./..")).getAttribute("innerHTML")
        // input description parent html:
//                                    <input type="text" id="tf_description" class="span4" disabled="" value="Test Description">
//                                    <input type="hidden" name="description" value="Test Description">
        clearAndSendKeys(inputDescription, description);
    }

    public void clickSubmit() {
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
        return getValueFromInputField(ddlFromAccount);
    }

    public String readValueFromDescriptionField() {
        return getValueFromInputField(inputDescription);

    }

    private String getValueFromInputField(WebElement inputField) {
        return inputField.getAttribute("value");
    }
}
