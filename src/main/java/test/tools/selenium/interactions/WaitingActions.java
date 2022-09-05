package test.tools.selenium.interactions;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class WaitingActions {

    public WebDriver driver;
    public WebDriverWait wait;
    public FluentWait fluentWait;
    public ActionsAPI actionsAPI;


    public WaitingActions(WebDriver driver) {
        this.driver = driver;
    }

    public WaitingActions(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        actionsAPI = new ActionsAPI(driver, wait);
    }

    public void fluentWaitUntil(Object object, final By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring((Class<? extends Throwable>) object);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }

            public String toString() {
                return "element get" + object + locator;
            }
        });
    }

    public void waitForValueIsPresent(WebElement element, String value) {
        wait.until((ExpectedCondition<Boolean>) driver -> {
            assert driver != null;
            return Boolean.valueOf(((JavascriptExecutor) driver)
                    .executeScript("return arguments[0].value === '" + value + "'", element).toString());
        });
    }

    public void waitForValueIsPresent(By xpath, String value) {
        WebElement element = driver.findElement(xpath);
        wait.until((ExpectedCondition<Boolean>) driver -> {
            assert driver != null;
            return Boolean.valueOf(((JavascriptExecutor) driver)
                    .executeScript("return arguments[0].value === '" + value + "'", element).toString());
        });
    }

    public void waitForIsChecked(WebElement element) {
        wait.until((ExpectedCondition<Boolean>) driver -> {
            assert driver != null;
            return Boolean.valueOf(((JavascriptExecutor) driver)
                    .executeScript("return arguments[0].checked", element).toString());
        });
    }

    public void waitForIsChecked(By xpath) {
        WebElement element = driver.findElement(xpath);
        wait.until((ExpectedCondition<Boolean>) driver -> {
            assert driver != null;
            return Boolean.valueOf(((JavascriptExecutor) driver)
                    .executeScript("return arguments[0].checked", element).toString());
        });
    }

}

