package org.endava.automation.testing.Utils;

import org.endava.automation.testing.Enums.DriverTypeEnum;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class BaseTest{

    protected WebDriver driver;
    protected String baseUrl;
    protected SoftAssert softAssert;

    @BeforeClass
    protected void beforeClass() throws IOException {
        baseUrl = PropertiesReader.readFromProperties(ConfigurationConstants.MY_PROPERTIES_PATH, ConfigurationConstants.BASE_URL_PROPERTY);
        String driverType = PropertiesReader.readFromProperties(ConfigurationConstants.MY_PROPERTIES_PATH, ConfigurationConstants.DRIVER_TYPE_PROPERTY);
        driver = DriverFactory.createDriverForBrowserWithValue(DriverTypeEnum.parse(driverType));
        beforeClassExtended();
    }

    protected void beforeClassExtended() throws IOException {
    }

    @BeforeMethod
    protected void beforeMethod() {
        softAssert = new SoftAssert();
    }

    @AfterMethod
    protected void afterMethod() {
        softAssert.assertAll();
    }

    protected void afterClassExtended() {
    }

    @AfterClass(alwaysRun = true)
    protected void afterClass() {
        driver.quit();
    }
}
