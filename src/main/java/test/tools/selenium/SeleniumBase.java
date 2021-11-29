package test.tools.selenium;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.tools.selenium.config.PropertyNames;
import test.tools.selenium.interactions.*;
import test.tools.selenium.report.extent.ExtentReportTestCaseFrame;

import java.util.LinkedList;
import java.util.List;

public class SeleniumBase extends ExtentReportTestCaseFrame {

    private WebDriver driver = null;
    private WebDriverWait wait = null;
    private ExtentTest extTest = null;
    private List<GalenTestInfo> galenTestInfos = null;

    @BeforeClass
    public void setUp(String scenario) throws Exception {
        driver = createWebDriver(scenario);
        wait = getWait();
        setExtentReports(createExtentsReportInstance());
        extTest = getExtentReports().createTest(scenario);
        PageFactory.initElements(driver, WaitingActions.class);
        if (Boolean.parseBoolean(getConfigProperty(PropertyNames.GALEN_TEST_LAYOUT))) {
            galenTestInfos = new LinkedList<GalenTestInfo>();
        }
    }

    @AfterClass
    public void teardown(String scenario) throws Exception {
        if (Boolean.parseBoolean(getConfigProperty(PropertyNames.GALEN_TEST_LAYOUT))) {
            new HtmlReportBuilder().build(galenTestInfos,
                    "target/galen-html-reports");
        }
        if (extTest != null) {
            boolean scenarioIsFailed = extTest.getStatus().equals(Status.PASS);
            if (scenarioIsFailed == false) {
                extTest.log(Status.FAIL, scenario + " senaryosu hata almıştır");
                logScreenCapture(extTest, scenario);
                cleanUp(getExtentReports(), extTest);
                Cookie cookie = new Cookie("zaleniumTestPassed", "false");
                driver.manage().addCookie(cookie);
            } else {
                extTest.log(Status.PASS, scenario + " senaryosu başarılı olmuştur");
                logScreenCapture(extTest, scenario);
                cleanUp(getExtentReports(), extTest);
                Cookie cookie = new Cookie("zaleniumTestPassed", "true");
                driver.manage().addCookie(cookie);
            }
        }
        cleanUpWebDriver(driver);
    }
}
