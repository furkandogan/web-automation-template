package test.tools.selenium.interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClickActions extends WaitingActions {

    public WebDriver driver;
    public WebDriverWait wait;

    public ClickActions(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    /**
     * Click to element that is visible
     *
     * @param element
     */
    public void click(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    /**
     * Click to element that is visible
     *
     * @param xpath
     */
    public void click(By xpath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(xpath));
        driver.findElement(xpath).click();
    }

    /**
     * Click to element that is invisible
     *
     * @param element
     */
    public void clickByJs(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
    }

    /**
     * Click to element that is invisible
     *
     * @param xpath
     */
    public void clickByJs(By xpath) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", driver.findElement(xpath));
    }

}
