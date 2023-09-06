package org.endava.automation.testing.Services;

import org.endava.automation.testing.Enums.TransferFundsAccountTypesEnum;
import org.endava.automation.testing.Pages.AccountActivityPage;
import org.endava.automation.testing.Pages.AccountSummaryPage;
import org.endava.automation.testing.Pages.HomePage;
import org.endava.automation.testing.Pages.LoginPage;
import org.endava.automation.testing.Pages.MyMoneyMapPage;
import org.endava.automation.testing.Pages.PayBillsPage;
import org.endava.automation.testing.Pages.TransferFundsPage;
import org.endava.automation.testing.Utils.Log;
import org.openqa.selenium.WebDriver;

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
        Log.userFlowLogger("User logs in using credentials. Username: " + userName + ", Password: " + password);
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
        transferFundsPage = accountActivityPage.navigateToTransferFunds();
        return transferFundsPage.readValueFromAccountFromField();
    }

    public String validateSummaryLeftAmount() {
        Log.userFlowLogger("User reads the account balance (difference between total inflow and total outflow).");
        myMoneyMapPage = accountSummaryPage.navigateToMyMoneyMap();
        return myMoneyMapPage.readSummaryLeftAmount();
    }

    public String validateDescriptionValueAfterPay(String payee, String account, String amount, String date,
                                                   String description) {
        Log.userFlowLogger(
            "User inserts payee: " + payee + ", account: " + account + ", amount: " + amount + ", date " +
                "and description: " + description + " and gets description value after paying.");
        payBillsPage = accountSummaryPage.navigateToPayBills();
        return payBillsPage.returnDescriptionValueAfterPay(payee, account, amount, date, description);
    }

    public String changeDescriptionAfterMoneyTransfer(TransferFundsAccountTypesEnum fromAccount,
                                                      TransferFundsAccountTypesEnum toAccount,
                                                      String amount, String description, String newDescription) {
        Log.userFlowLogger(
            "User transfers " + amount + " dollars from " + fromAccount.toString() + " account to " +
                toAccount.toString() + " account and inserts description " + description);
        transferFundsPage = accountSummaryPage.navigateToTransferFunds();
        transferFundsPage.insertAllValuesAndClickContinue(fromAccount, toAccount, amount, description);
        Log.userFlowLogger(
            "User edits the description to " + newDescription);
        transferFundsPage.insertDescription(newDescription);
        return transferFundsPage.readValueFromDescriptionField();
    }
}
