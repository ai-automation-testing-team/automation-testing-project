package org.endava.automation.testing.tests;

import org.endava.automation.testing.Enums.TransferFundsAccountTypesEnum;
import org.endava.automation.testing.Services.ZeroBankService;
import org.endava.automation.testing.Utils.BaseTest;
import org.endava.automation.testing.Utils.ConfigurationConstants;
import org.endava.automation.testing.Utils.Log;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGExamplesTest extends BaseTest {

    private ZeroBankService zeroBankService;

    @Override
    protected void beforeClassExtended() {
        zeroBankService = new ZeroBankService(driver);
        zeroBankService.loginUsingCredentials(
            ConfigurationConstants.USERNAME_COURSE, ConfigurationConstants.PASSWORD_COURSE);
    }

    @BeforeMethod
    protected void beforeMethod(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        Log.stepLogger("Test with name: " + testName + " has started.");
    }

    @Test(priority = 1)
    public void checkNullPointerException() {
        String accountFromValue = zeroBankService.validateAccountFromInAccountActivity();
        Assert.assertEquals(accountFromValue, "", "Account From value should be Empty");
    }

    @Test(priority = 2)
    public void checkDivisionByZero() {
        double ratio = zeroBankService.validateWithdrawalDepositRatioForAccount("Loan");
        Assert.assertEquals(ratio, 0.47, "Calculated ratio is not correct");
    }

    @Test(priority = 3)
    public void checkTimeoutException() {
        String summaryLeftAmount = zeroBankService.validateSummaryLeftAmount();
        Assert.assertEquals(summaryLeftAmount, "", "Account From value should be Empty");
    }

    @Test(priority = 4)
    public void checkStaleElementReferenceException() {
        String descriptionValue = zeroBankService.validateDescriptionValueAfterPay(
            "Bank of America", "Loan", "500", "2023-09-01", "Test Description");
        Assert.assertEquals(descriptionValue, "", "Account From value should be Empty");
    }

    @Test(priority = 5)
    public void checkInvalidElementStateException() {
        String descriptionValue = zeroBankService.changeDescriptionAfterMoneyTransfer(
            TransferFundsAccountTypesEnum.CHECKING_ACCOUNT, TransferFundsAccountTypesEnum.CREDIT_CARD_ACCOUNT,
            "500", "Test Description", "New Test Description");
        Assert.assertEquals(descriptionValue, "New Test Description", "Description value is not correct");
    }

    @AfterMethod
    protected void afterMethod(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        Log.stepLogger("Test with name: " + testName + " has ended.");
    }
}
