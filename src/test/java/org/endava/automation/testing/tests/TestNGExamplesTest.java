package org.endava.automation.testing.tests;

import org.aeonbits.owner.ConfigCache;
import org.aeonbits.owner.ConfigFactory;
import org.endava.automation.testing.Config.GitConfig;
import org.endava.automation.testing.Services.ZeroBankService;
import org.endava.automation.testing.Utils.BaseTest;
import org.endava.automation.testing.Utils.ConfigurationConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGExamplesTest extends BaseTest {

    private ZeroBankService zeroBankService;
    protected static final GitConfig gitConfig = ConfigCache.getOrCreate(GitConfig.class);


    @Override
    protected void beforeClassExtended() {
//        zeroBankService = new ZeroBankService(driver);
//        zeroBankService.loginUsingCredentials(
//            ConfigurationConstants.USERNAME_COURSE, ConfigurationConstants.PASSWORD_COURSE);
    }

    // NullPointerException Scenario
//    @Test(priority = 1)
//    public void verifyAccountFromValueIsEmpty() {
//        String accountFromValue = zeroBankService.validateAccountFromInAccountActivity();
//        Assert.assertEquals(accountFromValue, "", "Account From value should be Empty");
//    }
//
//    // DivisionByZero Scenario
//    @Test(priority = 2, enabled = false)
//    public void verifyWithrawalDepositRatioForLoanAccount() {
//        double ratio = zeroBankService.validateWithdrawalDepositRatioForAccount("Loan");
//        Assert.assertEquals(ratio, 0.47, "Calculated ratio is not correct");
//    }
//
//    // StaleElementReferenceException Scenario
//    @Test(priority = 3, enabled = false)
//    public void verifyDescriptionValueAfterPay() {
//        String descriptionValue = zeroBankService.validateDescriptionValueAfterPay(
//            "Bank of America", "Loan", "500", "2023-09-01", "Test Description");
//        Assert.assertEquals(descriptionValue, "", "Account From value should be Empty");
//    }
//
//    // InvalidElementStateException Scenario
//    @Test(priority = 4, enabled = false)
//    public void verifyChangingDescriptionValueAfterMoneyTransfer() {
//        String descriptionValue = zeroBankService.changeDescriptionAfterMoneyTransfer(
//            "Checking", "Credit Card", "500", "Test Description", "New Test Description");
//        Assert.assertEquals(descriptionValue, "New Test Description", "Description value is not correct");
//    }
//
//    // TimeoutException Scenario
//    @Test(priority = 5, enabled = false)
//    public void verifyPayeeIsSuccessfullyAdded() {
//        boolean payeeSuccessfullyAdded = zeroBankService.addNewPayee(
//            "Sprint", "Vardarska 4", "Checking", "Payee Details");
//        Assert.assertTrue(payeeSuccessfullyAdded, "Payee is not successfully added");
//    }
//
//    // Passed Scenario
//    @Test(priority = 6, enabled = false)
//    public void verifyTransferFundsFromSavingsToCheckingAccount() {
//        zeroBankService.navigateToAccountActivityPageAndChooseAccount("Savings");
//        double actualDeposits = zeroBankService.readDepositsFromAccount();
//        double actualWithdrawal = zeroBankService.readWithdrawalsFromAccount();
//        softAssert.assertEquals(actualDeposits, 1984.3, "Deposits sum is not correct");
//        softAssert.assertEquals(actualWithdrawal, 50.0, "Withdrawals sum is not correct");
//        zeroBankService.makeMoneyTransfer("Savings", "Checking",
//            Double.toString(actualDeposits - actualWithdrawal), "Test Description");
//        double transactionAmount = zeroBankService.validateMoneyIsTransferredAndReturnTransactionAmount();
//        softAssert.assertEquals(transactionAmount, actualDeposits - actualWithdrawal,
//            "Transaction amount is not correct");
//    }

    @Test()
    public void dummyTest() {
        ConfigFactory.setProperty("token", System.getenv("token"));
        System.out.println("Token is " + ConfigFactory.getProperty("token"));
        Assert.assertEquals(gitConfig.token(), "STANKO2");
    }
}
