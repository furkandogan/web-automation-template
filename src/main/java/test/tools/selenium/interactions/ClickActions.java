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

public class ClickActions {

    final static Logger logger = LogManager.getLogger(ClickActions.class);

    public WebDriver driver;
    public WebDriverWait wait;
    public ExtentTest extentTest;
    public ActionsAPI actionsAPI;
    public SimpleActions simpleActions;

    public ClickActions(WebDriver driver, WebDriverWait wait, ExtentTest extentTest) {
        this.driver = driver;
        this.wait = wait;
        this.extentTest = extentTest;
        actionsAPI = new ActionsAPI(driver, wait, extentTest);
        simpleActions = new SimpleActions(driver, wait, extentTest);
    }

    /**
     * Click to element that is visible
     *
     * @param element
     */
    public void click(WebElement element) {
        try {
            actionsAPI.scrollToVisibleElement(element);
            element.click();
            extentTest.pass(String.format("Clicked to element: {}", element));
            logger.info("Clicked to element: {}", element);
        } catch (Exception e) {
            simpleActions.highlightElement(element);
            extentTest.fail(e);
            logger.error(e);
        }
    }

    /**
     * Click to element that is visible
     *
     * @param xpath
     */
    public void click(By xpath) {
        WebElement element = null;
        try {
            element = actionsAPI.scrollToVisibleElement(xpath);
            element.click();
            extentTest.pass(String.format("Clicked to element: {}", element));
            logger.info("Clicked to element: {}", element);
        } catch (Exception e) {
            simpleActions.highlightElement(element);
            extentTest.fail(e);
            logger.error(e);
        }
    }

    /**
     * Click to element that is invisible
     *
     * @param element
     */
    public void clickByJs(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
            extentTest.pass(String.format("Clicked to element: {}", element));
            logger.info("Clicked to element: {}", element);
        } catch (Exception e) {
            simpleActions.highlightElement(element);
            extentTest.fail(e);
            logger.error(e);
        }
    }

    /**
     * Click to element that is invisible
     *
     * @param xpath
     */
    public void clickByJs(By xpath) {
        WebElement element = null;
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(xpath));
            element = driver.findElement(xpath);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
            extentTest.pass(String.format("Clicked to element: {}", element));
            logger.info("Clicked to element: {}", element);
        } catch (Exception e) {
            simpleActions.highlightElement(element);
            extentTest.fail(e);
            logger.error(e);
        }
    }

}
