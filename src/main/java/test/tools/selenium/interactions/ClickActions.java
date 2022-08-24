package test.tools.selenium.interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.v6.A;

public class ClickActions {

    public WebDriver driver;
    public WebDriverWait wait;
    public ActionsAPI actionsAPI;

    public ClickActions(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        actionsAPI = new ActionsAPI(driver,wait);
    }

    /**
     * Click to element that is visible
     *
     * @param element
     */
    public void click(WebElement element) {
        actionsAPI.scrollToVisibleElement(element);
        element.click();
    }

    /**
     * Click to element that is visible
     *
     * @param xpath
     */
    public void click(By xpath) {
        actionsAPI.scrollToVisibleElement(xpath).click();
    }

    /**
     * Click to element that is invisible
     *
     * @param element
     */
    public void clickByJs(WebElement element) {
        actionsAPI.scrollToInvisibleElement(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
    }

    /**
     * Click to element that is invisible
     *
     * @param xpath
     */
    public void clickByJs(By xpath) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", actionsAPI.scrollToInvisibleElement(xpath));
    }

}
