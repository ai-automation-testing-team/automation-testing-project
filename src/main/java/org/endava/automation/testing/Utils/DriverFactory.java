package org.endava.automation.testing.Utils;

import org.endava.automation.testing.Enums.DriverTypeEnum;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    public static WebDriver createDriverForBrowserWithValue(DriverTypeEnum driverType) {
        String driverVersion = PropertiesReader
            .readFromProperties(ConfigurationConstants.MY_PROPERTIES_PATH, ConfigurationConstants.DRIVER_TYPE_VERSION);
        String baseURL = PropertiesReader
            .readFromProperties(ConfigurationConstants.MY_PROPERTIES_PATH, ConfigurationConstants.BASE_URL_PROPERTY);
        WebDriver driver;
        try {
            ChromeDriverManager.getInstance(DriverManagerType.CHROME).driverVersion(driverVersion).setup();
        } catch (Exception var3) {
            WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        }
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--allow-running-insecure-content");
        chromeOptions.addArguments("--ignore-ssl-errors=yes");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("window-size=1920x1080");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--allow-insecure-localhost");
        if (driverType.equals(DriverTypeEnum.GOOGLE_CHROME_DRIVER_HEADLESS)) {
            chromeOptions.addArguments("--headless");
        }
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get(baseURL);
        return driver;
    }
}

