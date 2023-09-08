package org.endava.automation.testing.Utils;

import java.io.File;
import java.io.IOException;
import org.aeonbits.owner.ConfigCache;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.endava.automation.testing.Config.AIConfig;
import org.endava.automation.testing.Enums.DriverTypeEnum;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class BaseTest {

    protected WebDriver driver;
    protected String baseUrl;
    protected SoftAssert softAssert;
    protected static final AIConfig aiConfig = ConfigCache.getOrCreate(AIConfig.class);

    @BeforeClass
    protected void beforeClass() throws IOException {
        baseUrl = PropertiesReader
            .readFromProperties(ConfigurationConstants.MY_PROPERTIES_PATH, ConfigurationConstants.BASE_URL_PROPERTY);
        Log.info("Base URL: " + baseUrl);
        String driverType = PropertiesReader
            .readFromProperties(ConfigurationConstants.MY_PROPERTIES_PATH, ConfigurationConstants.DRIVER_TYPE_PROPERTY);
        Log.info("Web Driver Type: " + driverType);
        Log.info("Creating WebDriver instance...");
        driver = DriverFactory.createDriverForBrowserWithValue(DriverTypeEnum.parse(driverType));
        Log.info("WebDriver instance is created.");
        beforeClassExtended();
    }

    protected void beforeClassExtended() throws IOException {
    }

    @BeforeMethod
    protected void beforeMethod(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        Log.stepLogger("Test with name: " + testName + " has started.");
        softAssert = new SoftAssert();
    }

    @AfterMethod
    protected void afterMethod(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        Log.stepLogger("Test with name: " + testName + " has ended.");
        softAssert.assertAll();
    }

    protected void afterClassExtended() {
    }

    @AfterClass(alwaysRun = true)
    protected void afterClass() {
        driver.quit();
    }

    @AfterSuite(alwaysRun = true)
    protected void afterSuite() throws IOException {
        Git git = Git.open(new File(""));
        String currentBranch = git.getRepository().getFullBranch();
        long l = System.currentTimeMillis();
        String newBranchName = "ai-description-" + l;
        String token = System.getProperty("token");

        GitOperations gitOps = new GitOperations();

        try {
            gitOps.createBranchAndCommit(aiConfig.repoPath(), newBranchName);
            gitOps.pushToRemote(aiConfig.repoPath(), token);
            gitOps.createPullRequest(aiConfig.repoOwner(), aiConfig.repoName(), aiConfig.prTitle(),
                aiConfig.prDescription(), newBranchName, currentBranch, token);
        } catch (IOException | GitAPIException e) {
            e.printStackTrace();
        }
    }
}
