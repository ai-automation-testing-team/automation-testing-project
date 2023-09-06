package org.endava.automation.testing.Pages;

import org.endava.automation.testing.Pages.BasePage.ZeroBankBasePage;
import org.endava.automation.testing.Utils.BasePage;
import java.util.List;
import org.endava.automation.testing.Utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountActivityPage extends ZeroBankBasePage {

    @FindBy(id = "aa_accountId")
    private WebElement ddlAccount;

    public AccountActivityPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage newInstance(WebDriver driver) {
        return new AccountActivityPage(driver);
    }

    public void chooseFromAccount(String account) {
        Log.uiLogger("Choosing account: " + account);
        chooseFromDDSOptionContainsText(ddlAccount, account);
    }

    public Double readDepositsAndReturnSum() {
        Log.elementLocatingLogger("Reading deposits from the table.");
        List<WebElement> depositElements = waitAndFindElementsFromRoot(By.cssSelector("tbody tr td:nth-child(3)"));
        double sum = depositElements.stream()
            .mapToDouble(element -> {
                String deposit = element.getText();
                if(deposit.equals("")){
                    return 0.0;
                }
                return Double.parseDouble(deposit);
            })
            .sum();
        Log.uiLogger("Sum of deposits: " + sum);
        return sum;
    }

    public Double readWithdrawalsAndReturnSum() {
        Log.elementLocatingLogger("Reading withdrawals from the table.");
        List<WebElement> withdrawalElements = waitAndFindElementsFromRoot(By.cssSelector("tbody tr td:nth-child(4)"));
        double sum = withdrawalElements.stream()
            .mapToDouble(element -> {
                String withdrawal = element.getText();
                if(withdrawal.equals("")){
                    return 0.0;
                }
                return Double.parseDouble(withdrawal);
            })
            .sum();
        Log.uiLogger("Sum of withdrawals: " + sum);
        return sum;
    }

    public double calculateWithdrawalDepositRatio() {
        Log.uiLogger("Calculating withdrawal/deposit ratio.");
        double ratio = readWithdrawalsAndReturnSum() / readDepositsAndReturnSum();
        Log.uiLogger("Withdrawal/Deposit Ratio: " + ratio);
        return ratio;
    }
}
