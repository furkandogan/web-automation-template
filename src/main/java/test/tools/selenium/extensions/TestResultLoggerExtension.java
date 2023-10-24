package test.tools.selenium.extensions;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import test.tools.selenium.browser.BrowserOps;

import java.util.List;
import java.util.Optional;

public class TestResultLoggerExtension extends SeleniumJupiter implements BeforeTestExecutionCallback {

    final static Logger logger = LogManager.getLogger(TestResultLoggerExtension.class);

    WebDriverManager driverManager;
    List<WebDriver> drivers;

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        logger.info("Environment is started");
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        BrowserOps browserOps = new BrowserOps();
        driverManager = WebDriverManager.chromedriver().capabilities(browserOps.getChromeOptions()).browserInDocker();
        driverManager.config().setProperties("config/wdm-docker.properties");
        drivers = driverManager.create(extensionContext.getTestClass().get().getDeclaredMethods().length);
        super.beforeAll(extensionContext);
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        super.beforeEach(extensionContext);
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        super.afterEach(extensionContext);
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        super.afterAll(extensionContext);
    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        super.afterTestExecution(extensionContext);
        logger.info("Environment is closed");
    }

    @Override
    public void testDisabled(ExtensionContext extensionContext, Optional<String> reason) {
        super.testDisabled(extensionContext, reason);
    }

    @SneakyThrows
    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        super.testSuccessful(extensionContext);
    }

    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable cause) {
        super.testAborted(extensionContext, cause);
    }

    @SneakyThrows
    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable cause) {
        super.testFailed(extensionContext, cause);
    }
}
