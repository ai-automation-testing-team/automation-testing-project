package org.endava.automation.testing.Pages;

import org.endava.automation.testing.Utils.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "user_login")
    private WebElement inputLogin;

    @FindBy(id = "user_password")
    private WebElement inputPassword;

    @FindBy(id = "user_remember_me")
    private WebElement checkBoxRememberMe;

    @FindBy(className = "btn-primary")
    private WebElement btnSignIn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage newInstance(WebDriver driver) {
        return new LoginPage(driver);
    }

    public void insertUserName(String userName) {
        clearAndSendKeys(inputLogin, userName);
    }

    public void insertPassword(String password) {
        clearAndSendKeys(inputPassword, password);
    }

    public void clickSignIn() {
        btnSignIn.click();
    }

    public AccountSummaryPage loginUsingCredentials(String userName, String password) {
        insertUserName(userName);
        insertPassword(password);
        clickSignIn();
        getDriver().get("http://zero.webappsecurity.com/bank/account-summary.html");
        return new AccountSummaryPage(getDriver());
    }

}
