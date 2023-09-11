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
	@DescAI(value = false, content = "[Test Name] - Verify Transfer Funds from Savings to Checking Account\n[Test Summary] - This test is designed to verify that the funds can be successfully transferred from a savings account to a checking account.\n\n[Test Step] - User logs in using credentials - [Test Data] - Username: username, Password: password - [Test Result] - User successfully logs in.\n[Test Step] - User navigates to Account Activity to choose Account: Savings - [Test Data] - None - [Test Result] - User successfully navigates to the Account Activity page and chooses the Savings account.\n[Test Step] - User reads Deposits and calculate Deposits sum - [Test Data] - None - [Test Result] - User successfully reads the deposits and calculates the sum.\n[Test Step] - User reads Withdrawals and calculate Withdrawals sum - [Test Data] - None - [Test Result] - User successfully reads the withdrawals and calculates the sum.\n[Test Step] - User makes Money Transfer from Account: Savings to Account: Checking with Amount: 1934.3 and Description: Test Description - [Test Data] - None - [Test Result] - User successfully initiates the money transfer from the savings account to the checking account with the specified amount and description.\n[Test Step] - User validates that Transaction is Submitted and returning the Transaction Amount - [Test Data] - None - [Test Result] - User successfully validates that the transaction is submitted and retrieves the transaction amount.\n[Test Step] - Test with name: verifyTransferFundsFromSavingsToCheckingAccount has ended - [Test Data] - None - [Test Result] - The test execution is completed.")
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
