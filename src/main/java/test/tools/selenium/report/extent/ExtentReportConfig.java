package test.tools.selenium.report.extent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentKlovReporter;
import test.tools.selenium.config.Config;

public class ExtentReportConfig {
    Config config;

    public ExtentReportConfig(Config config) {
        this.config = config;
    }

    public ExtentReports createExtentReport() {
        ExtentReports extentReports = new ExtentReports();
        extentReports.setReportUsesManualConfiguration(true);
        if (config.getKlovEnabled()) {
            ExtentKlovReporter klovReporter = new ExtentKlovReporter(config.getTitle(), config.getBuild());
            klovReporter.initMongoDbConnection(config.getKlovDb(), config.getKlovPort());
            klovReporter.initKlovServerConnection(config.getKlovUrl());
            extentReports.attachReporter(klovReporter);
        }
        return extentReports;
    }

    /***
     *
     * @param extentReport
     */
    public void cleanUp(ExtentReports extentReport) {
        if (extentReport != null) {
            extentReport.flush();
        }
    }
}
