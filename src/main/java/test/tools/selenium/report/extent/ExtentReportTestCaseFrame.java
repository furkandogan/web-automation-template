package test.tools.selenium.report.extent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentKlovReporter;
import com.aventstack.extentreports.reporter.ExtentLoggerReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import cucumber.api.java.gl.E;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import test.tools.selenium.TestCaseFrame;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;

public class ExtentReportTestCaseFrame extends TestCaseFrame {

    private ExtentReports extentReports = null;
    private boolean isKlovReporter = false;

    public ExtentReports getExtentReports() {
        return extentReports;
    }

    public void setExtentReports(ExtentReports extentReports) {
        this.extentReports = extentReports;
    }

    public boolean isKlovReporter() {
        return isKlovReporter;
    }

    public void setKlovReporter(boolean klovReporter) {
        isKlovReporter = klovReporter;
    }

    public ExtentReportTestCaseFrame() {
        try {
            setKlovReporter(Boolean.valueOf(getConfigProperty("klov.reporter")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     * @throws Exception
     */
    public ExtentReports createExtentsReportInstance() throws Exception {

        ExtentReports extentReports = new ExtentReports();
        extentReports.setReportUsesManualConfiguration(true);

        if (isKlovReporter()) {
            ExtentKlovReporter  klovReporter = new ExtentKlovReporter (getConfigProperty("report.title"),getConfigProperty("build"));
            klovReporter.initMongoDbConnection(getConfigProperty("klov.db"), Integer.parseInt(getConfigProperty("klov.db.port")));
            klovReporter.initKlovServerConnection(getConfigProperty("klov.url"));

            extentReports.attachReporter(klovReporter);
        } else {
            ReportManager instance = ReportManager.getInstance();
            String reportBaseFolder = instance.getReportBaseFolder();
            String reportLogFolder = instance.getReportLogFolder();


            ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(reportBaseFolder + "/index.html");
            //extentHtmlReporter.config().setChartVisibilityOnOpen(true);
            extentHtmlReporter.config().setDocumentTitle(getConfigProperty("report.title"));
            extentHtmlReporter.config().setEncoding("UTF-8");
            extentHtmlReporter.config().setProtocol(Protocol.HTTPS);
            extentHtmlReporter.config().setReportName(getConfigProperty("build"));
            //extentHtmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
            extentHtmlReporter.config().setTheme(Theme.STANDARD);
            extentHtmlReporter.config().setTimeStampFormat("mm/dd/yyyy hh:mm:ss a");
            extentHtmlReporter.config().setCSS("css-string");
            extentHtmlReporter.config().setJS("js-string");
            //extentHtmlReporter.setAppendExisting(true);

            ExtentLoggerReporter extentLoggerReporter =  new ExtentLoggerReporter(reportLogFolder + "/log.html");
            extentLoggerReporter.config().setDocumentTitle(getConfigProperty("report.title"));
            extentLoggerReporter.config().setEncoding("UTF-8");
            extentLoggerReporter.config().setProtocol(Protocol.HTTPS);
            extentLoggerReporter.config().setReportName(getConfigProperty("build"));
            //extentHtmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
            extentLoggerReporter.config().setTheme(Theme.STANDARD);
            extentLoggerReporter.config().setTimeStampFormat("mm/dd/yyyy hh:mm:ss a");
            extentLoggerReporter.config().setCSS("css-string");
            extentLoggerReporter.config().setJS("js-string");

            extentReports.attachReporter(extentHtmlReporter,extentLoggerReporter);
        }

        return extentReports;
    }

    /***
     *
     * @param captureName
     * @return
     * @throws Exception
     */
    public String createScreenCapture(String captureName) throws Exception {
        String screenShot = (( TakesScreenshot ) getWebDriver()).getScreenshotAs(OutputType.BASE64);

        byte[] btDataFile = Base64.getDecoder().decode(screenShot);

        String imageName = String.format("%s%s%s.png", ReportManager.getInstance().getReportImageFolder(), File.separator, (captureName + System.currentTimeMillis()));

        File of = new File(imageName);
        FileOutputStream osf = new FileOutputStream(of);
        osf.write(btDataFile);
        osf.flush();
        String imagePath = "images/" + of.getName();
        return imagePath;
    }

    /***
     *
     * @param extentTest
     * @param captureName
     */
    public void logScreenCapture(ExtentTest extentTest, String captureName) {

        try {
            //add screen capture
            String capturedFilePath = createScreenCapture(captureName);
            extentTest.addScreenCaptureFromPath(capturedFilePath, captureName);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /***
     *
     * @param extentReport
     * @param extentTest
     */
    public void cleanUp(ExtentReports extentReport, ExtentTest extentTest) {
        if (extentReport != null) {
            if (extentTest != null) {
                getExtentReports().flush();
            }
            getExtentReports().flush();
        }
    }
}
