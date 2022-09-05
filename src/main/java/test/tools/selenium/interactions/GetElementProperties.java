package test.tools.selenium.interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GetElementProperties {

    public WebDriver driver;
    public WebDriverWait wait;
    public ActionsAPI actionsAPI;

    public GetElementProperties(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        actionsAPI = new ActionsAPI(driver, wait);
    }

    /**
     * Get element attributeValue
     *
     * @param element
     * @return
     */
    public String getAttribute(WebElement element, String attr, boolean hidden) {
        if (hidden) {
            actionsAPI.scrollToInvisibleElement(element);
        } else {
            actionsAPI.scrollToVisibleElement(element);
        }
        return element.getAttribute(attr).trim().toLowerCase();
    }

    /**
     * Get element attributeValue
     *
     * @param xpath
     * @return
     */
    public String getAttribute(By xpath, String attr, boolean hidden) {
        WebElement element;
        if (hidden) {
            element = actionsAPI.scrollToInvisibleElement(xpath);
        } else {
            element = actionsAPI.scrollToVisibleElement(xpath);
        }
        return element.getAttribute(attr).trim().toLowerCase();
    }

    /**
     * Get element text
     *
     * @param element
     * @return
     */
    public String getValue(WebElement element) {
        return this.getAttribute(element, "value", false);
    }

    /**
     * Get element text
     *
     * @param xpath
     * @return
     */
    public String getValue(By xpath) {
        return this.getAttribute(xpath, "value", false);
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
     * @param xpath
     * @return
     */
    public String getValue(By xpath, boolean hidden) {
        return getAttribute(xpath, "value", hidden);
    }

    /**
     * Get element text
     *
     * @param element
     * @return
     */
    public String getText(WebElement element) {
        return this.getText(element, false);
    }


    /**
     * Get element text
     *
     * @param xpath
     * @return
     */
    public String getText(By xpath) {
        return this.getText(xpath, false);
    }

    /**
     * Get element text
     *
     * @param element
     * @return
     */
    public String getText(WebElement element, boolean hidden) {
        if (hidden) {
            actionsAPI.scrollToInvisibleElement(element);
        } else {
            actionsAPI.scrollToVisibleElement(element);
        }
        return element.getText().trim().toLowerCase();
    }

    /**
     * Get element text
     *
     * @param xpath
     * @return
     */
    public String getText(By xpath, boolean hidden) {
        WebElement element;
        if (hidden) {
            element = actionsAPI.scrollToInvisibleElement(xpath);
        } else {
            element = actionsAPI.scrollToVisibleElement(xpath);
        }
        return element.getText().trim().toLowerCase();
    }

    /**
     * Get element tag
     *
     * @param element
     * @return
     */
    public String getTagName(WebElement element) {
        return this.getTagName(element, false);
    }

    /**
     * Get element tag
     *
     * @param xpath
     * @return
     */
    public String getTagName(By xpath) {
        return this.getTagName(xpath, false);
    }

    /**
     * Get element tag
     *
     * @param element
     * @return
     */
    public String getTagName(WebElement element, boolean hidden) {
        if (hidden) {
            actionsAPI.scrollToInvisibleElement(element);
        } else {
            actionsAPI.scrollToVisibleElement(element);
        }
        return element.getTagName().trim().toLowerCase();
    }

    /**
     * Get element tag
     *
     * @param xpath
     * @return
     */
    public String getTagName(By xpath, boolean hidden) {
        WebElement element;
        if (hidden) {
            element = actionsAPI.scrollToInvisibleElement(xpath);
        } else {
            element = actionsAPI.scrollToVisibleElement(xpath);
        }
        return element.getTagName().trim().toLowerCase();
    }

    /**
     * Get element cssValue
     *
     * @param element
     * @return
     */
    public String getCssValue(WebElement element, String value) {
        return this.getCssValue(element, value, false);
    }

    /**
     * Get element cssValue
     *
     * @param xpath
     * @return
     */
    public String getCssValue(By xpath, String value) {
        return this.getCssValue(xpath, value, false);
    }

    /**
     * Get element cssValue
     *
     * @param element
     * @return
     */
    public String getCssValue(WebElement element, String value, boolean hidden) {
        if (hidden) {
            actionsAPI.scrollToInvisibleElement(element);
        } else {
            actionsAPI.scrollToVisibleElement(element);
        }
        return element.getCssValue(value).trim().toLowerCase();
    }

    /**
     * Get element cssValue
     *
     * @param xpath
     * @return
     */
    public String getCssValue(By xpath, String value, boolean hidden) {
        WebElement element;
        if (hidden) {
            element = actionsAPI.scrollToInvisibleElement(xpath);
        } else {
            element = actionsAPI.scrollToVisibleElement(xpath);
        }
        return element.getCssValue(value).trim().toLowerCase();
    }

    /**
     * Get element list size
     *
     * @param elements
     * @return
     */
    public Integer getSize(List<WebElement> elements) {
        return this.getSize(elements, false);
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
