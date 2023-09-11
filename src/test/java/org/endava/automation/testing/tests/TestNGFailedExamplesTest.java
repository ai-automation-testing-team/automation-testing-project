package org.endava.automation.testing.tests;

import org.ai.automation.test_automation.annotations.AnalysisAI;
import org.ai.automation.test_automation.annotations.DescAI;
import org.endava.automation.testing.Services.ZeroBankService;
import org.endava.automation.testing.Utils.BaseTest;
import org.endava.automation.testing.Utils.ConfigurationConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGFailedExamplesTest extends BaseTest {

    private ZeroBankService zeroBankService;


    @Override
    protected void beforeClassExtended() {
        zeroBankService = new ZeroBankService(driver);
        zeroBankService.loginUsingCredentials(ConfigurationConstants.USERNAME_COURSE,
            ConfigurationConstants.PASSWORD_COURSE);
    }


    // NullPointerException Scenario
    @Test()
    @AnalysisAI
    public void verifyAccountFromValueIsEmpty() {
        String accountFromValue = zeroBankService.validateAccountFromInAccountActivity();
        Assert.assertEquals(accountFromValue, "", "Account From value should be Empty");
    }


    // StaleElementReferenceException Scenario
    @Test()
    @AnalysisAI
    public void verifyDescriptionValueAfterPay() {
        String descriptionValue = zeroBankService.validateDescriptionValueAfterPay("Bank of America", "Loan", "500",
            "2023-09-01", "Test Description");
        Assert.assertEquals(descriptionValue, "", "Account From value should be Empty");
    }


    // InvalidElementStateException Scenario
    @Test()
    @AnalysisAI
    //check again
    public void verifyChangingDescriptionValueAfterMoneyTransfer() {
        String descriptionValue = zeroBankService.changeDescriptionAfterMoneyTransfer("Checking", "Credit Card", "500",
            "Test Description", "New Test Description");
        Assert.assertEquals(descriptionValue, "New Test Description", "Description value is not correct");
    }


    // TimeoutException Scenario
    @Test()
    @AnalysisAI
    public void verifyPayeeIsSuccessfullyAdded() {
        boolean payeeSuccessfullyAdded = zeroBankService.addNewPayee("Sprint", "Vardarska 4", "Checking",
            "Payee Details");
        Assert.assertTrue(payeeSuccessfullyAdded, "Payee is not successfully added");
    }



}
