package org.endava.automation.testing.Pages;

import org.endava.automation.testing.Pages.BasePage.ZeroBankBasePage;
import org.endava.automation.testing.Utils.BasePage;
import java.util.List;
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
        chooseFromDDSOptionContainsText(ddlAccount, account);
    }

    public Double readDepositsAndReturnSum() {
        List<WebElement> depositElements = waitAndFindElementsFromRoot(By.cssSelector("tbody tr td:nth-child(3)"));
        return depositElements.stream()
            .mapToDouble(element -> {
                String deposit = element.getText();
                if(deposit.equals("")){
                    return 0.0;
                }
                return Double.parseDouble(deposit);
            })
            .sum();
    }

    public Double readWithdrawalsAndReturnSum() {
        List<WebElement> depositElements = waitAndFindElementsFromRoot(By.cssSelector("tbody tr td:nth-child(4)"));
        return depositElements.stream()
            .mapToDouble(element -> {
                String deposit = element.getText();
                if(deposit.equals("")){
                    return 0.0;
                }
                return Double.parseDouble(deposit);
            })
            .sum();
    }

    public double calculateWithdrawalDepositRatio() {
        return readWithdrawalsAndReturnSum() / readDepositsAndReturnSum();
    }
}
