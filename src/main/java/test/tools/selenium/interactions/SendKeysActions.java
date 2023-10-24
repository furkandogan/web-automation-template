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

public class SendKeysActions extends ActionsAPI {

    final static Logger logger = LogManager.getLogger(SendKeysActions.class);

    public SendKeysActions(WebDriver driver, WebDriverWait wait, ExtentTest extentTest) {
        super(driver, wait, extentTest);
    }

    /**
     * Send texts or keys in input element
     *
     * @param element
     * @param value
     */
    public void sendKeys(WebElement element, String value) {
        try {
            scrollToVisibleElement(element);
            element.clear();
            element.sendKeys(value);
            wait.until(ExpectedConditions.attributeToBe(element, "value", value));
            if (extentTest != null)
                extentTest.pass(String.format("Sent value: %s to element: %s ", value, element));
            logger.info("Sent value: {} to element: {} ", value, element);
        } catch (Exception e) {
            highlightElement(element);
            if (extentTest != null){
                extentTest.fail(e);
            }
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
            element = scrollToVisibleElement(xpath);
            element.clear();
            element.sendKeys(value);
            wait.until(ExpectedConditions.attributeToBe(xpath, "value", value));
            if (extentTest != null)
                extentTest.pass(String.format("Sent value: %s to element: %s ", value, element));
            logger.info("Sent value: {} to element: {} ", value, element);
        } catch (Exception e) {
            highlightElement(element);
            if (extentTest != null){
                extentTest.fail(e);
            }
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
            if (extentTest != null)
                extentTest.pass(String.format("Sent value: %s to element: %s ", value, element));
            logger.info("Sent value: {} to element: {} ", value, element);
        } catch (Exception e) {
            highlightElement(element);
            if (extentTest != null){
                extentTest.fail(e);
            }
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
            if (extentTest != null)
                extentTest.pass(String.format("Sent value: %s to element: %s ", value, element));
            logger.info("Sent value: {} to element: {} ", value, element);
        } catch (Exception e) {
            highlightElement(element);
            if (extentTest != null){
                extentTest.fail(e);
            }
            logger.error(e);
        }
    }

}
