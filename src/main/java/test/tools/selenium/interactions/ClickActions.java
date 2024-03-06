package test.tools.selenium.interactions;

import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClickActions extends ActionsAPI {

    final static Logger logger = LogManager.getLogger(ClickActions.class);

    public ClickActions(WebDriver driver, WebDriverWait wait, ExtentTest extentTest) {
        super(driver, wait, extentTest);
    }

    /**
     * Click to element that is visible
     *
     * @param element
     */
    public void click(WebElement element) {
        try {
            await(2);
            scrollToVisibleElement(element);
            await(2);
            element.click();
            if (extentTest != null)
                extentTest.pass(String.format("Clicked to element: {%s}", element));
            logger.info("Clicked to element: {}", element);
        } catch (Exception e) {
            highlightElement(element);
            if (extentTest != null){
                extentTest.fail(e);
            }
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
            await(2);
            element = scrollToVisibleElement(xpath);
            await(2);
            element.click();
            if (extentTest != null)
                extentTest.pass(String.format("Clicked to element: {%s}", element));
            logger.info("Clicked to element: {}", element);
        } catch (Exception e) {
            highlightElement(element);
            if (extentTest != null){
                extentTest.fail(e);
            }
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
            await(1);
            js.executeScript("arguments[0].scrollIntoView(false);", element);
            await(1);
            js.executeScript("arguments[0].click()", element);
            if (extentTest != null)
                extentTest.pass(String.format("Clicked to element: {%s}", element));
            logger.info("Clicked to element: {}", element);
        } catch (Exception e) {
            highlightElement(element);
            if (extentTest != null){
                extentTest.fail(e);
            }
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
            await(2);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(xpath));
            await(2);
            element = driver.findElement(xpath);
            await(1);
            js.executeScript("arguments[0].scrollIntoView(false);", element);
            await(1);
            js.executeScript("arguments[0].click()", element);
            if (extentTest != null)
                extentTest.pass(String.format("Clicked to element: {%s}", element));
            logger.info("Clicked to element: {}", element);
        } catch (Exception e) {
            highlightElement(element);
            if (extentTest != null){
                extentTest.fail(e);
            }
            logger.error(e);
        }
    }

}
