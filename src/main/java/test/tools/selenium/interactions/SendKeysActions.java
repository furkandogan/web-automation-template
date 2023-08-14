package test.tools.selenium.interactions;

import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SendKeysActions {

    final static Logger logger = LogManager.getLogger(SendKeysActions.class);

    public WebDriver driver;
    public WebDriverWait wait;
    public ExtentTest extentTest;
    public ActionsAPI actionsAPI;
    public SimpleActions simpleActions;

    public SendKeysActions(WebDriver driver, WebDriverWait wait, ExtentTest extentTest) {
        this.driver = driver;
        this.wait = wait;
        this.extentTest = extentTest;
        actionsAPI = new ActionsAPI(driver, wait, extentTest);
        simpleActions = new SimpleActions(driver, wait,extentTest);
    }

    /**
     * Send texts or keys in input element
     *
     * @param element
     * @param value
     */
    public void sendKeys(WebElement element, String value) {
        try {
            actionsAPI.scrollToVisibleElement(element);
            element.clear();
            element.sendKeys(value);
            wait.until(ExpectedConditions.attributeToBe(element, "value", value));
            extentTest.pass(String.format("Sent value: {} to element: {} ", value, element));
            logger.info("Sent value: {} to element: {} ", value, element);
        } catch (Exception e) {
            simpleActions.highlightElement(element);
            extentTest.fail(e);
            logger.error(e);
        }
    }

    /**
     * Send texts or keys in input element
     *
     * @param xpath
     * @param value
     */
    public void sendKeys(By xpath, String value) {
        WebElement element = null;
        try {
            element = actionsAPI.scrollToVisibleElement(xpath);
            element.clear();
            element.sendKeys(value);
            wait.until(ExpectedConditions.attributeToBe(xpath, "value", value));
            extentTest.pass(String.format("Sent value: {} to element: {} ", value, element));
            logger.info("Sent value: {} to element: {} ", value, element);
        } catch (Exception e) {
            simpleActions.highlightElement(element);
            extentTest.fail(e);
            logger.error(e);
        }
    }

    /**
     * Send texts or keys in input element by js
     *
     * @param element
     * @param value
     */
    public void sendKeysByJs(WebElement element, String value) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
            wait.until(ExpectedConditions.attributeToBe(element, "value", value));
            extentTest.pass(String.format("Sent value: {} to element: {} ", value, element));
            logger.info("Sent value: {} to element: {} ", value, element);
        } catch (Exception e) {
            simpleActions.highlightElement(element);
            extentTest.fail(e);
            logger.error(e);
        }
    }

    /**
     * Send texts or keys in input element by js
     *
     * @param xpath
     * @param value
     */
    public void sendKeysByJs(By xpath, String value) {
        WebElement element = null;
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(xpath));
            element = driver.findElement(xpath);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
            wait.until(ExpectedConditions.attributeToBe(xpath, "value", value));
            extentTest.pass(String.format("Sent value: {} to element: {} ", value, element));
            logger.info("Sent value: {} to element: {} ", value, element);
        } catch (Exception e) {
            simpleActions.highlightElement(element);
            extentTest.fail(e);
            logger.error(e);
        }
    }

}
