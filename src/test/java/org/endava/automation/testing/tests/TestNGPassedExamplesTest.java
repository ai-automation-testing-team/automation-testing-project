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
	@DescAI(value = false, content = "[Test Name] - verifyTransferFundsFromSavingsToCheckingAccount\n[Test Summary] - This test verifies the transfer of funds from the Savings account to the Checking account.\n\n[Test Step] - User logs in using credentials - Username: {username}, Password: {password} - [Test Data] - username: {username}, password: {password} - [Test Result] - The user is successfully logged in.\n[Test Step] - User navigates to Account Activity to choose Account: Savings - [Test Data] - None - [Test Result] - The user successfully navigates to the Account Activity page and chooses the Savings account.\n[Test Step] - User reads Deposits and calculate Deposits sum - [Test Data] - None - [Test Result] - The sum of deposits in the Savings account is calculated to be 1984.3.\n[Test Step] - User reads Withdrawals and calculate Withdrawals sum - [Test Data] - None - [Test Result] - The sum of withdrawals from the Savings account is calculated to be 50.0.\n[Test Step] - User makes Money Transfer from Account: Savings to Account: Checking with Amount: 1934.3 and Description: Test Description - [Test Data] - From Account: Savings, To Account: Checking, Amount: 1934.3, Description: Test Description - [Test Result] - The money transfer is successfully made from the Savings account to the Checking account with the specified amount and description.\n[Test Step] - User validates that Transaction is Submitted and returning the Transaction Amount - [Test Data] - None - [Test Result] - The transaction is successfully submitted and the transaction amount is returned as $ 1934.3.\n[Test Step] -")
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
