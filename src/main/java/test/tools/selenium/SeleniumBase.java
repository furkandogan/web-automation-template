package test.tools.selenium;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.tools.selenium.config.ConfigurationManager;
import test.tools.selenium.config.DBManager;
import test.tools.selenium.config.PropertyNames;
import test.tools.selenium.report.extent.ExtentReportTestCaseFrame;
import test.tools.selenium.util.DbUtility;

import java.time.Duration;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SeleniumBase extends ExtentReportTestCaseFrame implements TestWatcher, BeforeAllCallback, BeforeEachCallback, AfterEachCallback, AfterAllCallback {

    final static Logger logger = LogManager.getLogger(SeleniumBase.class);

    public static DbUtility oracleDb = null;
    public WebDriver driver = null;
    public WebDriverWait wait = null;
    public ExtentTest extTest = null;
    public InitElements initElements = null;
    public List<GalenTestInfo> galenTestInfos = null;

    private List<TestResultStatus> testResultsStatus = new ArrayList<>();

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        oracleDb = DBManager.getOracleDb();
        oracleDb.initConnectionPool();
        setExtentReports(createExtentsReportInstance());
        if (Boolean.parseBoolean(getConfigProperty(PropertyNames.GALEN_TEST_LAYOUT))) {
            galenTestInfos = new LinkedList<GalenTestInfo>();
        }
        logger.info("Environment is started for {}", extensionContext.getDisplayName());
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        String testCaseName = extensionContext.getDisplayName();
        extTest = getExtentReports().createTest(testCaseName).assignCategory(extensionContext.getTags().iterator().next());
        driver = createWebDriver(testCaseName);
        wait = getWait();
        initElements = new InitElements(driver);
        openStartPage();
        logger.info(testCaseName + " is started");
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        if (Boolean.parseBoolean(getConfigProperty(PropertyNames.GALEN_TEST_LAYOUT))) {
            new HtmlReportBuilder().build(galenTestInfos,
                    "target/galen-html-reports");
        }
        oracleDb.closeConnectionPool();
        cleanUp(getExtentReports());
        String testCaseName = extensionContext.getDisplayName();
        logger.info("Environment is closed for {}", testCaseName);
        Map<TestResultStatus, Long> summary = testResultsStatus.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        logger.info("Test Result Summary for {} {}", testCaseName, summary.toString());
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {

    }

    private enum TestResultStatus {
        SUCCESSFUL, ABORTED, FAILED, DISABLED;
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        logger.info("{} is disabled with reason :- {}", context.getDisplayName(), reason.orElse("No reason"));

        testResultsStatus.add(TestResultStatus.DISABLED);
    }

    @SneakyThrows
    @Override
    public void testSuccessful(ExtensionContext context) {
        String testCaseName = context.getDisplayName();
        logger.info("{} is successful", testCaseName);
        extTest.pass(testCaseName + " scenario is successful", MediaEntityBuilder.createScreenCaptureFromPath(createScreenCapture(testCaseName)).build());
        cleanUpWebDriver(driver);
        testResultsStatus.add(TestResultStatus.SUCCESSFUL);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        logger.info("{} is aborted", context.getDisplayName());

        testResultsStatus.add(TestResultStatus.ABORTED);
    }

    @SneakyThrows
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String testCaseName = context.getDisplayName();
        logger.info("{} is failed", testCaseName);
        extTest.fail(testCaseName + " scenario is not successful", MediaEntityBuilder.createScreenCaptureFromPath(createScreenCapture(testCaseName)).build());
        cleanUpWebDriver(driver);
        testResultsStatus.add(TestResultStatus.FAILED);
    }
}
