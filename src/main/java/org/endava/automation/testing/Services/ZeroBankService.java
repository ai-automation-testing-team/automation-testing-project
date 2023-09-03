package org.endava.automation.testing.Services;

import org.endava.automation.testing.Enums.TransferFundsAccountTypesEnum;
import org.endava.automation.testing.Pages.AccountActivityPage;
import org.endava.automation.testing.Pages.AccountSummaryPage;
import org.endava.automation.testing.Pages.HomePage;
import org.endava.automation.testing.Pages.LoginPage;
import org.endava.automation.testing.Pages.MyMoneyMapPage;
import org.endava.automation.testing.Pages.PayBillsPage;
import org.endava.automation.testing.Pages.TransferFundsPage;
import org.openqa.selenium.WebDriver;

public class ZeroBankService {

    private HomePage homePage;
    private AccountSummaryPage accountSummaryPage;
    private AccountActivityPage accountActivityPage;
    private TransferFundsPage transferFundsPage;
    private PayBillsPage payBillsPage;
    private MyMoneyMapPage myMoneyMapPage;

    public ZeroBankService(WebDriver driver){
        homePage = new HomePage(driver);
    }

    public void loginUsingCredentials(String userName, String password) {
        LoginPage loginPage = homePage.clickBtnSignIn();
        accountSummaryPage = loginPage.loginUsingCredentials(userName, password);
    }

    public double validateWithdrawalDepositRatioForAccount(String account) {
        accountActivityPage = accountSummaryPage.navigateToAccountActivity();
        accountActivityPage.chooseFromAccount(account);
        return accountActivityPage.calculateWithdrawalDepositRatio();
    }

    public String validateAccountFromInAccountActivity() {
        transferFundsPage = accountActivityPage.navigateToTransferFunds();
        return transferFundsPage.readValueFromAccountFromField();
    }

    public String validateSummaryLeftAmount() {
        myMoneyMapPage = accountSummaryPage.navigateToMyMoneyMap();
        return myMoneyMapPage.readSummaryLeftAmount();
    }

    public String validateDescriptionValueAfterPay(String payee, String account, String amount, String date,
                                                   String description) {
        payBillsPage = accountSummaryPage.navigateToPayBills();
        return payBillsPage.returnDescriptionValueAfterPay(payee, account, amount, date, description);
    }

    public String changeDescriptionAfterMoneyTransfer(TransferFundsAccountTypesEnum fromAccount,
                                                      TransferFundsAccountTypesEnum toAccount,
                                                      String amount, String description, String newDescription) {
        transferFundsPage = accountSummaryPage.navigateToTransferFunds();
        transferFundsPage.insertAllValuesAndClickContinue(fromAccount, toAccount, amount, description);
        transferFundsPage.insertDescription(newDescription);
        return transferFundsPage.readValueFromDescriptionField();
    }
}
