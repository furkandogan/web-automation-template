package test.tools.selenium.interactions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GetElementProperties extends WaitingActions {

    public WebDriver driver;
    public WebDriverWait wait;

    public GetElementProperties(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    /**
     * Get element attributeValue
     *
     * @param element
     * @return
     */
    public String getAttribute(WebElement element, String attr, boolean hidden) {
        if (hidden) {
            wait.until(ExpectedConditions.invisibilityOf(element));
        } else {
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        return element.getAttribute(attr).trim().toLowerCase();
    }

    /**
     * Get element text
     *
     * @param element
     * @return
     */
    public String getValue(WebElement element, boolean hidden) {
        return getAttribute(element, "value", hidden);
    }

    /**
     * Get element text
     *
     * @param element
     * @return
     */
    public String getText(WebElement element, boolean hidden) {
        if (hidden) {
            wait.until(ExpectedConditions.invisibilityOf(element));
        } else {
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        return element.getText().trim().toLowerCase();
    }

    /**
     * Get element tag
     *
     * @param element
     * @return
     */
    public String getTagName(WebElement element, boolean hidden) {
        if (hidden) {
            wait.until(ExpectedConditions.invisibilityOf(element));
        } else {
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        return element.getTagName().trim().toLowerCase();
    }

    /**
     * Get element cssValue
     *
     * @param element
     * @return
     */
    public String getCssValue(WebElement element, String value, boolean hidden) {
        if (hidden) {
            wait.until(ExpectedConditions.invisibilityOf(element));
        } else {
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        return element.getCssValue(value).trim().toLowerCase();
    }

    /**
     * Get element list size
     *
     * @param elements
     * @return
     */
    public Integer getSize(List<WebElement> elements, boolean hidden) {
        if (hidden) {
            wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
        } else {
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        }
        return elements.size();
    }

    /**
     * Get page url
     *
     * @return
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl().trim();
    }

    /**
     * Get page source
     *
     * @return
     */
    public String getPageSource() {
        return driver.getPageSource();
    }

    /**
     * Get page title
     *
     * @return
     */
    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * Get length
     *
     * @param value
     * @return
     */
    public Integer getLenght(String value) {
        return value.length();
    }

}
