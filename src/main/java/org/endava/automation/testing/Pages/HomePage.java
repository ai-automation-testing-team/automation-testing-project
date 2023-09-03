package org.endava.automation.testing.Pages;

import org.endava.automation.testing.Utils.BasePage;
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
        btnSignIn.click();
        return new LoginPage(getDriver());
    }
}
