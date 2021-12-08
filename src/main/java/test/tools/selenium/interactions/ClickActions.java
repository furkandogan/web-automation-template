package test.tools.selenium.interactions;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClickActions {

    public WebDriver driver;
    public WebDriverWait wait;

    public ClickActions(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /**
     * Click to element that is visible
     *
     * @param element
     */
    public void click(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (StaleElementReferenceException e) {
            click(element);
        }
    }

    /**
     * Click to element that is visible
     *
     * @param xpath
     */
    public void click(By xpath) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
            wait.until(ExpectedConditions.elementToBeClickable(xpath));
            driver.findElement(xpath).click();
        } catch (StaleElementReferenceException e) {
            click(xpath);
        }
    }

    /**
     * Click to element that is invisible
     *
     * @param element
     */
    public void clickByJs(WebElement element) {
        try {
            wait.until(ExpectedConditions.invisibilityOf(element));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
        } catch (StaleElementReferenceException e) {
            clickByJs(element);
        }
    }

    /**
     * Click to element that is invisible
     *
     * @param xpath
     */
    public void clickByJs(By xpath) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(xpath));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", driver.findElement(xpath));
        } catch (StaleElementReferenceException e) {
            clickByJs(xpath);
        }
    }

}
