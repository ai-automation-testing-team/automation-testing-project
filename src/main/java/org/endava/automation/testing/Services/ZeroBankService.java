package org.endava.automation.testing.Services;

import org.endava.automation.testing.Pages.AccountActivityPage;
import org.endava.automation.testing.Pages.AccountSummaryPage;
import org.endava.automation.testing.Pages.HomePage;
import org.endava.automation.testing.Pages.LoginPage;
import org.endava.automation.testing.Pages.MyMoneyMapPage;
import org.endava.automation.testing.Pages.PayBillsPage;
import org.endava.automation.testing.Pages.TransferFundsPage;
import org.endava.automation.testing.Utils.Log;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ZeroBankService {

    private HomePage homePage;
    private AccountSummaryPage accountSummaryPage;
    private AccountActivityPage accountActivityPage;
    private TransferFundsPage transferFundsPage;
    private PayBillsPage payBillsPage;
    private MyMoneyMapPage myMoneyMapPage;

    public ZeroBankService(WebDriver driver) {
        homePage = new HomePage(driver);
    }

    public void loginUsingCredentials(String userName, String password) {
        Log.userFlowLogger("User logs in using credentials - Username: " + userName + ", Password: " + password);
        LoginPage loginPage = homePage.clickBtnSignIn();
        accountSummaryPage = loginPage.loginUsingCredentials(userName, password);
    }

    public double validateWithdrawalDepositRatioForAccount(String account) {
        Log.userFlowLogger("User calculates withdrawal/deposit ratio for account: " + account);
        accountActivityPage = accountSummaryPage.navigateToAccountActivity();
        accountActivityPage.chooseFromAccount(account);
        return accountActivityPage.calculateWithdrawalDepositRatio();
    }

    public String validateAccountFromInAccountActivity() {
        Log.userFlowLogger("User reads value from 'From Account' field.");
        transferFundsPage = myMoneyMapPage.navigateToTransferFunds();
        return transferFundsPage.readValueFromAccountFromField();
    }

    public String validateSummaryAmountLeft() {
        Log.userFlowLogger("User reads the account balance (difference between total inflow and total outflow).");
        myMoneyMapPage = accountSummaryPage.navigateToMyMoneyMap();
        return myMoneyMapPage.readSummaryAmountLeft();
    }

    public String validateDescriptionValueAfterPay(String payee, String account, String amount, String date,
                                                   String description) {
        Log.userFlowLogger("User inserts payee: " + payee + ", account: " + account + ", amount: " + amount
            + ", date " + "and description: " + description + " and gets description value after paying.");
        payBillsPage = accountSummaryPage.navigateToPayBills();
        return payBillsPage.returnDescriptionValueAfterPay(payee, account, amount, date, description);
    }

    public String changeDescriptionAfterMoneyTransfer(String fromAccount, String toAccount, String amount,
                                                      String description, String newDescription) {
        Log.userFlowLogger("User transfers " + amount + " dollars from " + fromAccount + " account to " +
                toAccount + " account and inserts description " + description);
        transferFundsPage = accountSummaryPage.navigateToTransferFunds();
        transferFundsPage.insertAllValuesAndClickContinue(fromAccount, toAccount, amount, description);
        Log.userFlowLogger("User edits the description to " + newDescription);
        transferFundsPage.insertDescription(newDescription);
        return transferFundsPage.readValueFromDescriptionField();
    }

    public boolean addNewPayee(String payeeName, String payeeAddress, String payeeAccount, String payeeDetails) {
        Log.userFlowLogger("User adds new Payee with Mame: " + payeeName + " Address: " + payeeAddress + " Account: " +
            payeeAccount + " and Details: " + payeeDetails);
        payBillsPage = accountSummaryPage.navigateToPayBills();
        payBillsPage.addNewPayee(payeeName, payeeAddress, payeeAccount, payeeDetails);
        return payBillsPage.validatePayeeIsAdded();
    }

    public void navigateToAccountActivityPageAndChooseAccount(String account) {
        Log.userFlowLogger("User navigates to Account Activity to choose Account: " + account);
        accountActivityPage = accountSummaryPage.navigateToAccountActivity();
        accountActivityPage.chooseFromAccount(account);
    }

    public double readDepositsFromAccount() {
        Log.userFlowLogger("User reads Deposits and calculate Deposits sum");
        return accountActivityPage.readDepositsAndReturnSum();
    }

    public double readWithdrawalsFromAccount() {
        Log.userFlowLogger("User reads Withdrawals and calculate Withdrawals sum");
        return accountActivityPage.readWithdrawalsAndReturnSum();
    }

    public void makeMoneyTransfer(String fromAccount, String toAccount, String amount, String description) {
        Log.userFlowLogger("User makes Money Transfer from Account: " + fromAccount + " to Account: " + toAccount +
            " with Amount: " + amount + " and Description: " + description);
        transferFundsPage = accountSummaryPage.navigateToTransferFunds();
        transferFundsPage.insertAllValuesAndClickContinue(fromAccount, toAccount, amount, description);
        transferFundsPage.clickSubmit();
    }

    public double validateMoneyIsTransferredAndReturnTransactionAmount() {
        Log.userFlowLogger("User validates that Transaction is Submitted and returning the Transaction Amount");
        boolean messageSuccess = transferFundsPage.validateTransactionIsSubmittedSuccessfully();
        Assert.assertTrue(messageSuccess, "Transaction was not successfully submitted");
        String transactionAmount = transferFundsPage.readTransactionAmount();
        Log.userFlowLogger("User validates that Transaction Amount is: " + transactionAmount);
        return Double.parseDouble(transactionAmount.replace("$ ", ""));
    }
}
