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
	@DescAI(value = false, content = "[Test Name] - Verify Transfer Funds From Savings to Checking Account\n[Test Summary] - This test case verifies that the funds can be successfully transferred from the savings account to the checking account.\n\n[Test Step] - User navigates to Account Activity to choose Account: Savings - [Test Data] - User selects the account type as Savings - [Test Result] - The savings account is selected.\n[Test Step] - User reads Deposits and calculate Deposits sum - [Test Data] - N/A - [Test Result] - The sum of deposits in the savings account is calculated.\n[Test Step] - User reads Withdrawals and calculate Withdrawals sum - [Test Data] - N/A - [Test Result] - The sum of withdrawals from the savings account is calculated.\n[Test Step] - User makes Money Transfer from Account: Savings to Account: Checking with Amount: {actualDeposits - actualWithdrawal} and Description: Test Description - [Test Data] - Amount: {actualDeposits - actualWithdrawal}, Description: Test Description - [Test Result] - The funds are successfully transferred from the savings account to the checking account.\n[Test Step] - User validates that Transaction is Submitted and returning the Transaction Amount - [Test Data] - N/A - [Test Result] - The transaction is submitted successfully and the transaction amount is validated.")
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
