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
	@DescAI(value = false, content = "[Test Name] - verifyTransferFundsFromSavingsToCheckingAccount\n[Test Summary] - This test case verifies the functionality of transferring funds from the Savings account to the Checking account and validates the transaction amount.\n\n[Test Step] - User navigates to the Account Activity page and chooses the Savings account - [Test Data] - N/A - [Test Result] - The Savings account is successfully selected.\n[Test Step] - User reads the deposits from the account and calculates the sum - [Test Data] - N/A - [Test Result] - The sum of the deposits is 1984.3.\n[Test Step] - User reads the withdrawals from the account and calculates the sum - [Test Data] - N/A - [Test Result] - The sum of the withdrawals is 50.0.\n[Test Step] - User makes a money transfer from the Savings account to the Checking account with the calculated amount and a test description - [Test Data] - Transfer amount: 1934.3, Description: Test Description - [Test Result] - The transfer is successfully completed.\n[Test Step] - User validates that the transaction amount is correct and matches the calculated amount - [Test Data] - N/A - [Test Result] - The transaction amount is correctly displayed as 1934.3.")
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
