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
	@DescAI(value = false, content = "[Test Name] - Verify Transfer Funds from Savings to Checking Account\n[Test Summary] - This test case verifies the successful transfer of funds from a savings account to a checking account.\n\n[Test Step] - User logs in using valid credentials - [Test Data] - Username: username, Password: password - [Test Result] - User successfully logs in.\n[Test Step] - User navigates to Account Activity page and selects the Savings account - [Test Data] - N/A - [Test Result] - Account Activity page is displayed and Savings account is selected.\n[Test Step] - User calculates the sum of deposits in the Savings account - [Test Data] - N/A - [Test Result] - Deposits sum is calculated correctly.\n[Test Step] - User calculates the sum of withdrawals from the Savings account - [Test Data] - N/A - [Test Result] - Withdrawals sum is calculated correctly.\n[Test Step] - User initiates a money transfer from the Savings account to the Checking account with the calculated amount and adds a description - [Test Data] - Transfer amount: (Deposits sum - Withdrawals sum), Description: Test Description - [Test Result] - Money transfer is successfully initiated.\n[Test Step] - User verifies that the transaction amount displayed after the transfer is correct - [Test Data] - N/A - [Test Result] - Transaction amount is displayed correctly.")
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
