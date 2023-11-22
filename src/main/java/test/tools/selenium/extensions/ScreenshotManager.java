package test.tools.selenium.extensions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import test.tools.selenium.config.Config;
import test.tools.selenium.report.OutputHandler;

import java.io.File;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.io.FileUtils.copyFile;
import static org.openqa.selenium.OutputType.BASE64;
import static org.openqa.selenium.OutputType.FILE;
import static test.tools.selenium.report.OutputHandler.*;

public class ScreenshotManager {

    final static Logger logger = LogManager.getLogger(ScreenshotManager.class);

    ExtensionContext extensionContext;
    Config config;
    OutputHandler outputHandler;

    public ScreenshotManager(ExtensionContext extensionContext, Config config,
                             OutputHandler outputHandler) {
        this.extensionContext = extensionContext;
        this.config = config;
        this.outputHandler = outputHandler;
    }

    boolean isScreenshotRequired() {
        Optional<Throwable> executionException = extensionContext
                .getExecutionException();
        boolean isSscreenshot = config.isScreenshot();
        boolean isSscreenshotWhenFailure = config.isScreenshotWhenFailure();
        return isSscreenshot
                || (executionException.isPresent() && isSscreenshotWhenFailure);
    }

    void makeScreenshotIfRequired(List<WebDriver> driverList) {
        driverList.forEach(this::makeScreenshotIfRequired);
    }

    void makeScreenshotIfRequired(WebDriver driver) {
        if (isScreenshotRequired() && driver != null) {
            String screenshotFormat = config.getScreenshotFormat();
            switch (screenshotFormat) {
                case PNG_KEY:
                    loggerFileScreenshot(driver);
                    break;
                case BASE64_KEY:
                    loggerBase64Screenshot(driver);
                    break;
                case BASE64_AND_PNG_KEY:
                    loggerBase64Screenshot(driver);
                    loggerFileScreenshot(driver);
                    break;
                default:
                    logger.warn("Invalid screenshot format {}", screenshotFormat);
                    break;
            }
        }
    }

    void loggerBase64Screenshot(WebDriver driver) {
        try {
            String screenshotBase64 = ((TakesScreenshot) driver)
                    .getScreenshotAs(BASE64);
            logger.debug("Screenshot (in Base64) at the end of test "
                    + "(copy&paste this string as URL in browser to watch it):\r\n"
                    + "data:image/png;base64,{}", screenshotBase64);
        } catch (Exception e) {
            logger.trace("Exception getting screenshot in Base64", e);
        }
    }

    void loggerFileScreenshot(WebDriver driver) {
        try {
            File screenshotFile = ((TakesScreenshot) driver)
                    .getScreenshotAs(FILE);
            File destFile = outputHandler.getScreenshotFile(driver);
            logger.trace("Creating screenshot for {} in {}", driver, destFile);
            copyFile(screenshotFile, destFile);

        } catch (Exception e) {
            logger.trace("Exception getting screenshot as file", e);
        }
    }

}
