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
	@DescAI(value = false, content = "[Test Name] - Verify Transfer Funds From Savings to Checking Account\n[Test Summary] - This test case verifies the successful transfer of funds from a savings account to a checking account.\n\n[Test Step] - User logs in using credentials - [Test Data] - Username: username, Password: password - [Test Result] - User is successfully logged in.\n[Test Step] - User navigates to Account Activity to choose Account: Savings - [Test Data] - None - [Test Result] - User is on the Account Activity page.\n[Test Step] - User reads Deposits and calculates Deposits sum - [Test Data] - None - [Test Result] - Sum of deposits is 1984.3.\n[Test Step] - User reads Withdrawals and calculates Withdrawals sum - [Test Data] - None - [Test Result] - Sum of withdrawals is 50.0.\n[Test Step] - User makes Money Transfer from Account: Savings to Account: Checking with Amount: 1934.3 and Description: Test Description - [Test Data] - None - [Test Result] - Transfer is successfully submitted.\n[Test Step] - User validates that Transaction is Submitted and returns the Transaction Amount - [Test Data] - None - [Test Result] - Transaction is submitted and the amount is $1934.3.")
	public void verifyTransferFundsFromSavingsToCheckingAccount() {
		handleAllureDescription();

		// zeroBankService.navigateToAccountActivityPageAndChooseAccount("Savings");
		// double actualDeposits = zeroBankService.readDepositsFromAccount();
		// double actualWithdrawal = zeroBankService.readWithdrawalsFromAccount();
		// softAssert.assertEquals(actualDeposits, 1984.3, "Deposits sum is not correct");
		// softAssert.assertEquals(actualWithdrawal, 50.0, "Withdrawals sum is not correct");
		// zeroBankService.makeMoneyTransfer("Savings", "Checking", Double.toString(actualDeposits - actualWithdrawal),
		// 		"Test Description");
		// double transactionAmount = zeroBankService.validateMoneyIsTransferredAndReturnTransactionAmount();
		// softAssert.assertEquals(transactionAmount, actualDeposits - actualWithdrawal,
		// 		"Transaction amount is not correct");
		// softAssert.assertAll();
	}

}
