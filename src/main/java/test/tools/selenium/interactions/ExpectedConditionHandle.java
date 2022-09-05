package test.tools.selenium.interactions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class ExpectedConditionHandle {

    final static Logger logger = LogManager.getLogger(ExpectedConditionHandle.class);

    public WebDriver driver;
    public WebDriverWait wait;

    public ExpectedConditionHandle(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public static ExpectedCondition<Boolean> absenceOfElementLocated(
            final By locator) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    driver.findElement(locator);
                    return false;
                } catch (NoSuchElementException e) {
                    return true;
                }
            }

            public String toString() {
                return "element to not being present: " + locator;
            }
        };
    }

}
