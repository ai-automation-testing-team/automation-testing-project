package org.endava.automation.testing.Utils;

import io.qameta.allure.Allure;
import org.aeonbits.owner.ConfigCache;
import org.ai.automation.test_automation.annotations.DescAI;
import org.ai.automation.test_automation.service.TestModifier;
import org.ai.automation.test_automation.service.TestResultHandler;
import org.ai.automation.test_automation.service.testNG_impl.TestNGTestResultHandler;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.endava.automation.testing.Config.GitConfig;
import org.endava.automation.testing.Enums.DriverTypeEnum;
import org.endava.automation.testing.Enums.LogType;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

import static org.endava.automation.testing.Utils.FileHelper.getMavenBaseDirectory;
import static org.endava.automation.testing.Utils.FileHelper.getSourceFileFromTestClass;
import static org.endava.automation.testing.Utils.HtmlBuilder.buildHtmlFromAnalysisContent;
import static org.endava.automation.testing.Utils.HtmlBuilder.buildHtmlFromDescriptionContent;
import static org.endava.automation.testing.Utils.HtmlBuilder.extractTestDescriptionText;

public class BaseTest {

    private static boolean autoPR = false;
    private TestResultHandler testResultHandler;

    protected WebDriver driver;
    protected String baseUrl;
    protected SoftAssert softAssert;
    protected static final GitConfig GIT_CONFIG = ConfigCache.getOrCreate(GitConfig.class);


    @BeforeClass
    protected void beforeClass() throws IOException {
        testResultHandler = new TestNGTestResultHandler();
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


    @AfterMethod(alwaysRun = true)
    protected void afterMethod(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        Log.stepLogger("Test with name: " + testName + " has ended.");


        try {
            if (result.getStatus() == ITestResult.SUCCESS) {
                Class<?> aClass = result.getTestClass().getRealClass();
                String mavenBaseDirectory = getMavenBaseDirectory();
                File file = getSourceFileFromTestClass(aClass, mavenBaseDirectory);
                String description = testResultHandler.handleDescription(result, getLogContent(LogType.DESCRIPTION));

                if (Objects.nonNull(description)) {
                    String descriptionValue = extractTestDescriptionText(description);
                    String descriptionHtml = buildHtmlFromDescriptionContent(descriptionValue);
                    TestModifier.addAnnotation(file, result.getMethod().getMethodName(), descriptionValue);
                    autoPR = true;
                    Allure.description(descriptionHtml);
                    Allure.addAttachment("AI Description", "text/html", descriptionHtml, "html");
                }

            } else {
                String aiResults = testResultHandler.handleTestResult(result, getLogContent(LogType.ANALYSIS));
                System.out.printf("AI Response: " + aiResults);
                if (Objects.nonNull(aiResults)) {
                    String htmlAI = buildHtmlFromAnalysisContent(aiResults);
                    Allure.addAttachment("AI Suggestion", "text/html", htmlAI, "html");
                }
            }
        } catch (Exception e) {
            System.out.printf("After method has failed for test: " + testName);
            e.printStackTrace();
        }


    }


    protected void handleAllureDescription() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTrace[2];
        String cn = stackTraceElement.getClassName();
        String mn = stackTraceElement.getMethodName();

        DescAI annotation = null;
        try {
            annotation = Class.forName(cn).getMethod(mn).getAnnotation(DescAI.class);
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        boolean value = annotation.value();
        String content = annotation.content();

        if (!value && !content.isEmpty()) {
            Allure.descriptionHtml(HtmlBuilder.buildHtmlFromDescriptionContent(content));
        }
    }


    protected void afterClassExtended() {
    }


    private String getLogContent(LogType logType) {
        StringBuilder res = new StringBuilder();
        try {
            String log = new String(Files.readAllBytes(Paths.get("logFile.log")));

            if (LogType.DESCRIPTION.equals(logType)) {
                Arrays.stream(log.split("\n")).filter(s -> !s.contains("[Element Locating]"))
                    .toList()
                    .forEach(res::append);

            } else {
                Arrays.stream(log.split("\n"))
                    .skip(Math.max(0, log.split("\n").length - 10))
                    .toList()
                    .forEach(res::append);
            }
            return res.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @AfterClass(alwaysRun = true)
    protected void afterClass() {
        driver.quit();
    }


    @AfterSuite(alwaysRun = true)
    protected void afterSuite() throws IOException {
        if (autoPR) {
            Git git = Git.open(new File(""));
            String currentBranch = git.getRepository().getFullBranch();
            long l = System.currentTimeMillis();
            String newBranchName = "ai-description-" + l;
            String token = GIT_CONFIG.token();

            GitOperations gitOps = new GitOperations();

            try {
                gitOps.createBranchAndCommit(GIT_CONFIG.repoPath(), newBranchName);
                gitOps.pushToRemote(GIT_CONFIG.repoPath(), token);
                gitOps.createPullRequest(GIT_CONFIG.repoOwner(), GIT_CONFIG.repoName(), GIT_CONFIG.prTitle(),
                    GIT_CONFIG.prDescription(), newBranchName, currentBranch, token);
            } catch (IOException | GitAPIException e) {
                e.printStackTrace();
            }
        }
    }

}
