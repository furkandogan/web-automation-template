package test.tools.selenium.report.extent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentKlovReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import test.tools.selenium.TestCaseFrame;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;

public class ExtentReportTestCaseFrame extends TestCaseFrame {
    private static ExtentReports extentReports = null;

    public static ExtentReports getExtentReports() {
        return extentReports;
    }

    public static void setExtentReports(ExtentReports extentReports) {
        ExtentReportTestCaseFrame.extentReports = extentReports;
    }

    public ExtentReportTestCaseFrame() {
    }

    /**
     * @return
     * @throws Exception
     */
    public static ExtentReports createExtentsReportInstance() throws Exception {

        ExtentReports extentReports = new ExtentReports();
        extentReports.setReportUsesManualConfiguration(true);

        if (Boolean.valueOf(getConfigProperty("klov.reporter"))) {
            ExtentKlovReporter klovReporter = new ExtentKlovReporter(getConfigProperty("report.title"), getConfigProperty("build"));
            klovReporter.initMongoDbConnection(getConfigProperty("klov.db"), Integer.parseInt(getConfigProperty("klov.db.port")));
            klovReporter.initKlovServerConnection(getConfigProperty("klov.url"));

            extentReports.attachReporter(klovReporter);
        }
        if (Boolean.valueOf(getConfigProperty("spark.reporter"))) {
            ReportManager instance = ReportManager.getInstance();
            String reportBaseFolder = instance.getReportBaseFolder();

            ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportBaseFolder + "/spark.html")
                    .viewConfigurer()
                    .viewOrder()
                    .as(new ViewName[]{
                            ViewName.DASHBOARD,
                            ViewName.TEST,
                            ViewName.AUTHOR,
                            ViewName.DEVICE,
                            ViewName.EXCEPTION,
                            ViewName.LOG
                    })
                    .apply();

            extentReports.attachReporter(extentSparkReporter);
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
        String screenShot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BASE64);

        byte[] btDataFile = Base64.getDecoder().decode(screenShot);

        String imageName = String.format("%s%s%s.png", ReportManager.getInstance().getReportImageFolder(), File.separator, (captureName + System.currentTimeMillis()));

        File of = new File(imageName);
        FileOutputStream osf = new FileOutputStream(of);
        osf.write(btDataFile);
        osf.flush();
        String imagePath = of.getPath();
        return imagePath;
    }


    public String createScreenCast(String castName) throws Exception {
        String screenShot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BASE64);

        byte[] btDataFile = Base64.getDecoder().decode(screenShot);

        String cast = String.format("%s%s%s.mp4", ReportManager.getInstance().getReportCastFolder(), File.separator, (castName + System.currentTimeMillis()));

        File of = new File(cast);
        FileOutputStream osf = new FileOutputStream(of);
        osf.write(btDataFile);
        osf.flush();
        String imagePath = of.getPath();
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
     * @param extentTest
     * @param castName
     */
    public void logScreenCast(ExtentTest extentTest, String castName) {

        try {
            //add screen capture
            String castedFilePath = createScreenCapture(castName);
            extentTest.addScreenCaptureFromPath(castedFilePath, castName);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /***
     *
     * @param extentReport
     */
    public static void cleanUp(ExtentReports extentReport) {
        if (extentReport != null) {
            getExtentReports().flush();
        }
    }
}
