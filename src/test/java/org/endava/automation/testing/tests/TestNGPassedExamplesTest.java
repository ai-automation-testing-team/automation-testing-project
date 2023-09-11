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
	@DescAI(value = false, content = "[Test Name] - Verify Transfer Funds from Savings to Checking Account\n[Test Summary] - This test case verifies the successful transfer of funds from a savings account to a checking account.\n\n[Test Step] - User logs in using credentials - [Test Data] - Username: username, Password: password - [Test Result] - The user successfully logs in.\n[Test Step] - User navigates to Account Activity to choose Account: Savings - [Test Data] - None - [Test Result] - The Account Activity page is successfully accessed and the Savings account is selected.\n[Test Step] - User reads Deposits and calculate Deposits sum - [Test Data] - None - [Test Result] - The sum of deposits is calculated correctly.\n[Test Step] - User reads Withdrawals and calculate Withdrawals sum - [Test Data] - None - [Test Result] - The sum of withdrawals is calculated correctly.\n[Test Step] - User makes Money Transfer from Account: Savings to Account: Checking with Amount: 1934.3 and Description: Test Description - [Test Data] - None - [Test Result] - The money transfer is successfully initiated with the specified amount and description.\n[Test Step] - User validates that Transaction is Submitted and returning the Transaction Amount - [Test Data] - None - [Test Result] - The transaction is successfully submitted and the transaction amount is returned.\n[Test Step] - User validates that Transaction Amount is: $ 1934.3 - [Test Data] - None - [Test Result] - The transaction amount is correctly displayed as $ 1934.3.")
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
