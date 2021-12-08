package test.tools.selenium.interactions;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SendKeysActions {

    public WebDriver driver;
    public WebDriverWait wait;

    public SendKeysActions(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /**
     * Send texts or keys in input element
     *
     * @param element
     * @param value
     */
    public void sendKeys(WebElement element, String value) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(value);
            wait.until(ExpectedConditions.attributeToBe(element, "value", value));
            element.sendKeys(Keys.TAB);
        } catch (StaleElementReferenceException e) {
            sendKeys(element,value);
        }
    }

    /**
     * Send texts or keys in input element
     *
     * @param xpath
     * @param value
     */
    public void sendKeys(By xpath, String value) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
            WebElement element = driver.findElement(xpath);
            element.clear();
            element.sendKeys(value);
            wait.until(ExpectedConditions.attributeToBe(xpath, "value", value));
            element.sendKeys(Keys.TAB);
        } catch (StaleElementReferenceException e) {
            sendKeys(xpath,value);
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
            wait.until(ExpectedConditions.invisibilityOf(element));
            ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
            wait.until(ExpectedConditions.attributeToBe(element, "value", value));
        } catch (StaleElementReferenceException e) {
            sendKeysByJs(element,value);
        }
    }

    /**
     * Send texts or keys in input element by js
     *
     * @param xpath
     * @param value
     */
    public void sendKeysByJs(By xpath, String value) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(xpath));
            ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", driver.findElement(xpath));
            wait.until(ExpectedConditions.attributeToBe(xpath, "value", value));
        } catch (StaleElementReferenceException e) {
            sendKeysByJs(xpath,value);
        }
    }

}
