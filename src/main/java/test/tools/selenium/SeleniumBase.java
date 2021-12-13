package test.tools.selenium;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.tools.selenium.config.DBManager;
import test.tools.selenium.config.PropertyNames;
import test.tools.selenium.report.extent.ExtentReportTestCaseFrame;
import test.tools.selenium.util.DbUtility;

import java.util.LinkedList;
import java.util.List;

public class SeleniumBase extends ExtentReportTestCaseFrame {

    private WebDriver driver = null;
    private WebDriverWait wait = null;
    private ExtentTest extTest = null;
    private static List<GalenTestInfo> galenTestInfos = null;
    private static DbUtility oracleDb = null;

    //@BeforeSuite
    public static void setUpSuite() throws Exception {
        oracleDb = DBManager.getOracleDb();
        oracleDb.initConnectionPool();
        setExtentReports(createExtentsReportInstance());
        if (Boolean.parseBoolean(getConfigProperty(PropertyNames.GALEN_TEST_LAYOUT))) {
            galenTestInfos = new LinkedList<GalenTestInfo>();
        }
    }

    //@BeforeTest
    public void setUpTest(String scenario) throws Exception {
        driver = createWebDriver(scenario);
        wait = getWait();
        extTest = getExtentReports().createTest(scenario);
    }

    //@AfterTest
    public void tearDownTest(String scenario) throws Exception {
        if (extTest != null) {
            boolean scenarioIsFailed = extTest.getStatus().equals(Status.PASS);
            if (scenarioIsFailed == false) {
                extTest.log(Status.FAIL, scenario + " senaryosu hata almıştır");
                logScreenCapture(extTest, scenario);
                Cookie cookie = new Cookie("zaleniumTestPassed", "false");
                driver.manage().addCookie(cookie);
            } else {
                extTest.log(Status.PASS, scenario + " senaryosu başarılı olmuştur");
                logScreenCapture(extTest, scenario);
                Cookie cookie = new Cookie("zaleniumTestPassed", "true");
                driver.manage().addCookie(cookie);
            }
        }
        cleanUpWebDriver(driver);
    }


    //@AfterSuite
    public static void tearDownSuite() throws Exception {
        if (Boolean.parseBoolean(getConfigProperty(PropertyNames.GALEN_TEST_LAYOUT))) {
            new HtmlReportBuilder().build(galenTestInfos,
                    "target/galen-html-reports");
        }
        cleanUp(getExtentReports());
        oracleDb.closeConnectionPool();
    }
}
