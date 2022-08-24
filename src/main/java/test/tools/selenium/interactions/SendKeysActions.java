package test.tools.selenium.interactions;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SendKeysActions {

    public WebDriver driver;
    public WebDriverWait wait;
    public ActionsAPI actionsAPI;

    public SendKeysActions(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        actionsAPI = new ActionsAPI(driver,wait);
    }

    /**
     * Send texts or keys in input element
     *
     * @param element
     * @param value
     */
    public void sendKeys(WebElement element, String value) {
        actionsAPI.scrollToVisibleElement(element);
        element.clear();
        element.sendKeys(value);
        wait.until(ExpectedConditions.attributeToBe(element, "value", value));
    }

    /**
     * Send texts or keys in input element
     *
     * @param xpath
     * @param value
     */
    public void sendKeys(By xpath, String value) {
        WebElement element = actionsAPI.scrollToVisibleElement(xpath);
        element.clear();
        element.sendKeys(value);
        wait.until(ExpectedConditions.attributeToBe(xpath, "value", value));
    }

    /**
     * Send texts or keys in input element by js
     *
     * @param element
     * @param value
     */
    public void sendKeysByJs(WebElement element, String value) {
        actionsAPI.scrollToInvisibleElement(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
        wait.until(ExpectedConditions.attributeToBe(element, "value", value));
    }

    /**
     * Send texts or keys in input element by js
     *
     * @param xpath
     * @param value
     */
    public void sendKeysByJs(By xpath, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", actionsAPI.scrollToInvisibleElement(xpath));
        wait.until(ExpectedConditions.attributeToBe(xpath, "value", value));
    }

}
