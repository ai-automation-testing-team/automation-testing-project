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
	@DescAI(value = false, content = "[Test Name] - verifyTransferFundsFromSavingsToCheckingAccount\n[Test Summary] - This test verifies the successful transfer of funds from the Savings account to the Checking account.\n\n[Test Step] - User navigates to the Account Activity page and selects the Savings account - [Test Data] - N/A - [Test Result] - The Savings account is selected successfully.\n[Test Step] - User calculates the sum of deposits and verifies that it matches the expected value - [Test Data] - N/A - [Test Result] - The sum of deposits is correct.\n[Test Step] - User calculates the sum of withdrawals and verifies that it matches the expected value - [Test Data] - N/A - [Test Result] - The sum of withdrawals is correct.\n[Test Step] - User makes a money transfer from the Savings account to the Checking account with the specified amount and description - [Test Data] - Amount: $1934.3, Description: Test Description - [Test Result] - The transfer is successfully submitted.\n[Test Step] - User validates that the transaction amount matches the expected amount - [Test Data] - N/A - [Test Result] - The transaction amount is correct.")
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
