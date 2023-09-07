package org.endava.automation.testing.Pages;

import org.endava.automation.testing.Utils.BasePage;
import org.endava.automation.testing.Utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(id = "signin_button")
    private WebElement btnSignIn;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage newInstance(WebDriver driver) {
        return new HomePage(driver);
    }

    public LoginPage clickBtnSignIn() {
        Log.uiLogger("Clicking the 'Sign In' button.");
        btnSignIn.click();
        return new LoginPage(getDriver());
    }
}
