package org.endava.automation.testing.tests;

import org.ai.automation.test_automation.annotations.AnalysisAI;
import org.ai.automation.test_automation.annotations.DescAI;
import org.endava.automation.testing.Services.ZeroBankService;
import org.endava.automation.testing.Utils.BaseTest;
import org.endava.automation.testing.Utils.ConfigurationConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGPassedExamplesTest extends BaseTest {

	private ZeroBankService zeroBankService;

	@Override
	protected void beforeClassExtended() {
		zeroBankService = new ZeroBankService(driver);
		zeroBankService.loginUsingCredentials(ConfigurationConstants.USERNAME_COURSE,
				ConfigurationConstants.PASSWORD_COURSE);
	}
	// Passed Scenario
	@Test()
	@DescAI(value = false, content = "[Test Name] - Verify Transfer Funds From Savings To Checking Account\n\n[Test Summary] - This test case aims to verify that funds can be successfully transferred from a savings account to a checking account.\n\n[Test Step] - User navigates to Account Activity to choose Account: Savings - [Test Data] - N/A - [Test Result] - The user successfully navigates to the Account Activity page and selects the Savings account.\n\n[Test Step] - User reads Deposits and calculate Deposits sum - [Test Data] - N/A - [Test Result] - The user successfully reads the deposits from the account and calculates the sum.\n\n[Test Step] - User reads Withdrawals and calculate Withdrawals sum - [Test Data] - N/A - [Test Result] - The user successfully reads the withdrawals from the account and calculates the sum.\n\n[Test Step] - User makes Money Transfer from Account: Savings to Account: Checking with Amount: 1934.3 and Description: Test Description - [Test Data] - Amount: 1934.3, Description: Test Description - [Test Result] - The user successfully initiates a money transfer from the Savings account to the Checking account with the specified amount and description.\n\n[Test Step] - User validates that Transaction is Submitted and returning the Transaction Amount - [Test Data] - N/A - [Test Result] - The user successfully validates that the transaction is submitted and retrieves the transaction amount.")
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
