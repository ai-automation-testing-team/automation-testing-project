package org.endava.automation.testing.Pages;

import org.endava.automation.testing.Utils.BasePage;
import org.endava.automation.testing.Utils.Log;
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
        Log.uiLogger("Inserting username: " + userName);
        clearAndSendKeys(inputLogin, userName);
    }

    public void insertPassword(String password) {
        Log.uiLogger("Inserting password: " + password);
        clearAndSendKeys(inputPassword, password);
    }

    public void clickSignIn() {
        Log.uiLogger("Clicking the 'Sign In' button.");
        btnSignIn.click();
    }

    public AccountSummaryPage loginUsingCredentials(String userName, String password) {
        Log.uiLogger("Performing login with credentials.");
        insertUserName(userName);
        insertPassword(password);
        clickSignIn();
        Log.uiLogger("User is successfully logged in.");
        getDriver().get("http://zero.webappsecurity.com/bank/account-summary.html");
        return new AccountSummaryPage(getDriver());
    }

}
