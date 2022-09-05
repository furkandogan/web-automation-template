package test.tools.selenium.interactions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClickActions {

    final static Logger logger = LogManager.getLogger(ClickActions.class);

    public WebDriver driver;
    public WebDriverWait wait;
    public ActionsAPI actionsAPI;

    public ClickActions(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        actionsAPI = new ActionsAPI(driver, wait);
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
            logger.info("Clicked to element: {}", element);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * Click to element that is visible
     *
     * @param xpath
     */
    public void click(By xpath) {
        try {
            WebElement element = actionsAPI.scrollToVisibleElement(xpath);
            element.click();
            logger.info("Clicked to element: {}", element);
        } catch (Exception e) {
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
            actionsAPI.scrollToInvisibleElement(element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
            logger.info("Clicked to element: {}", element);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * Click to element that is invisible
     *
     * @param xpath
     */
    public void clickByJs(By xpath) {
        try {
            WebElement element = actionsAPI.scrollToVisibleElement(xpath);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
            logger.info("Clicked to element: {}", element);
        } catch (Exception e) {
            logger.error(e);
        }
    }

}
