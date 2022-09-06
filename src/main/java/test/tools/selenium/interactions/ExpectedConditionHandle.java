package test.tools.selenium.interactions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExpectedConditionHandle {

    final static Logger logger = LogManager.getLogger(ExpectedConditionHandle.class);

    public WebDriver driver;
    public WebDriverWait wait;

    public ExpectedConditionHandle(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public static ExpectedCondition<Boolean> absenceOfElementLocated(final By locator) {
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

    public static ExpectedCondition<Boolean> numberOfElementsToBe(int size, final By locator) {
        return new ExpectedCondition<Boolean>() {
            private int elemSize = 0;

            public Boolean apply(WebDriver driver) {
                this.elemSize = driver.findElements(locator).size();
                return this.elemSize != 0 && this.elemSize == size;
            }

            public String toString() {
                return String.format("%s element size: %d is equals %d", locator, this.elemSize, size);
            }
        };
    }

}
