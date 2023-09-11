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


    // DivisionByZero Scenario
    // @Test()
    // @AnalysisAI
    // public void verifyWithrawalDepositRatioForLoanAccount() {
    //     double ratio = zeroBankService.validateWithdrawalDepositRatioForAccount("Loan");
    //     Assert.assertEquals(ratio, 0.47, "Calculated ratio is not correct");
    // }


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


    // Passed Scenario
    @Test()
    @DescAI(value = false,
        content = "[Test Name] - verifyTransferFundsFromSavingsToCheckingAccount\r\n[Test Summary] - This test verifies that funds can be transferred successfully from the Savings account to the Checking account.\n\n[Test Step] - User navigates to Account Activity to choose Account: Savings - [Test Data] - None - [Test Result] - The user successfully navigates to the Account Activity page and selects the Savings account.\n[Test Step] - User reads Deposits and calculate Deposits sum - [Test Data] - None - [Test Result] - The user reads the deposits and calculates the sum of all deposits.\n[Test Step] - User reads Withdrawals and calculate Withdrawals sum - [Test Data] - None - [Test Result] - The user reads the withdrawals and calculates the sum of all withdrawals.\n[Test Step] - User makes Money Transfer from Account: Savings to Account: Checking with Amount: 1934.3 and Description: Test Description - [Test Data] - None - [Test Result] - The user successfully initiates a money transfer from the Savings account to the Checking account with the specified amount and description.\n[Test Step] - User validates that Transaction is Submitted and returning the Transaction Amount - [Test Data] - None - [Test Result] - The user verifies that the transaction is successfully submitted and retrieves the transaction amount.\n[Test Step] - User validates that Transaction Amount is: $ 1934.3 - [Test Data] - None - [Test Result] - The user verifies that the transaction amount is correct and matches the expected value.")
    public void verifyTransferFundsFromSavingsToCheckingAccount() {
        handleAllureDescription();

        zeroBankService.navigateToAccountActivityPageAndChooseAccount("Savings");
        double actualDeposits = zeroBankService.readDepositsFromAccount();
        double actualWithdrawal = zeroBankService.readWithdrawalsFromAccount();
        softAssert.assertEquals(actualDeposits, 1984.3, "Deposits sum is not correct");
        softAssert.assertEquals(actualWithdrawal, 50.0, "Withdrawals sum is not correct");
        zeroBankService.makeMoneyTransfer("Savings", "Checking", Double.toString(actualDeposits - actualWithdrawal),
            "Test Description");
        double transactionAmount = zeroBankService.validateMoneyIsTransferredAndReturnTransactionAmount();
        softAssert.assertEquals(transactionAmount, actualDeposits - actualWithdrawal,
            "Transaction amount is not correct");
        softAssert.assertAll();
    }


}
