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
	@DescAI(value = false, content = "[Test Name] - verifyTransferFundsFromSavingsToCheckingAccount\n[Test Summary] - This test verifies the successful transfer of funds from the Savings account to the Checking account.\n\n[Test Step] - User navigates to the Account Activity page and selects the Savings account - [Test Data] - None - [Test Result] - The Savings account is selected successfully.\n[Test Step] - User calculates the sum of deposits and withdrawals in the Savings account - [Test Data] - None - [Test Result] - The sums of deposits and withdrawals are calculated correctly.\n[Test Step] - User initiates a money transfer from the Savings account to the Checking account with the calculated amount and a test description - [Test Data] - Amount: [calculated amount], Description: Test Description - [Test Result] - The transfer is initiated successfully.\n[Test Step] - User validates that the transaction amount is correct - [Test Data] - None - [Test Result] - The transaction amount is correct.")
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
