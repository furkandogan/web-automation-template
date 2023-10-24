package test.tools.selenium.interactions;

import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GetElementProperties extends ActionsAPI{

    final static Logger logger = LogManager.getLogger(GetElementProperties.class);

    public GetElementProperties(WebDriver driver, WebDriverWait wait, ExtentTest extentTest) {
        super(driver, wait, extentTest);
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
            js.executeScript("arguments[0].scrollIntoView(false);", element);
        } else {
            scrollToVisibleElement(element);
        }
        String elementAttr = element.getAttribute(attr).trim().toLowerCase();
        extentTest.pass(String.format("Attribute value: {%s} of element: {%s}", elementAttr, element));
        logger.info("Attribute value: {} of element: {}", elementAttr, element);
        return elementAttr;
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
            wait.until(ExpectedConditions.invisibilityOfElementLocated(xpath));
            element = driver.findElement(xpath);
            js.executeScript("arguments[0].scrollIntoView(false);", element);
        } else {
            element = scrollToVisibleElement(xpath);
        }
        String elementAttr = element.getAttribute(attr).trim().toLowerCase();
        extentTest.pass(String.format("Attribute value: {%s} of element: {%s}", elementAttr, element));
        logger.info("Attribute value: {} of element: {}", elementAttr, element);
        return elementAttr;
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
            wait.until(ExpectedConditions.invisibilityOf(element));
            js.executeScript("arguments[0].scrollIntoView(false);", element);
        } else {
            scrollToVisibleElement(element);
        }
        String elementText = element.getText().trim().toLowerCase();
        extentTest.pass(String.format("Text: {%s} of element: {%s}", elementText, element));
        logger.info("Text: {} of element: {}", elementText, element);
        return elementText;
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
            wait.until(ExpectedConditions.invisibilityOfElementLocated(xpath));
            element = driver.findElement(xpath);
            js.executeScript("arguments[0].scrollIntoView(false);", element);
        } else {
            element = scrollToVisibleElement(xpath);
        }
        String elementText = element.getText().trim().toLowerCase();
        extentTest.pass(String.format("Text: {%s} of element: {%s}", elementText, element));
        logger.info("Text: {} of element: {}", elementText, element);
        return elementText;
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
            wait.until(ExpectedConditions.invisibilityOf(element));
            js.executeScript("arguments[0].scrollIntoView(false);", element);
        } else {
            scrollToVisibleElement(element);
        }
        String elementTagName = element.getTagName().trim().toLowerCase();
        extentTest.pass(String.format("Tag name: {%s} of element: {%s}", elementTagName, element));
        logger.info("Tag name: {} of element: {}", elementTagName, element);
        return elementTagName;
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
            wait.until(ExpectedConditions.invisibilityOfElementLocated(xpath));
            element = driver.findElement(xpath);
            js.executeScript("arguments[0].scrollIntoView(false);", element);
        } else {
            element = scrollToVisibleElement(xpath);
        }
        String elementTagName = element.getTagName().trim().toLowerCase();
        extentTest.pass(String.format("Tag name: {%s} of element: {%s}", elementTagName, element));
        logger.info("Tag name: {} of element: {}", elementTagName, element);
        return elementTagName;
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
            wait.until(ExpectedConditions.invisibilityOf(element));
            js.executeScript("arguments[0].scrollIntoView(false);", element);
        } else {
            scrollToVisibleElement(element);
        }
        String elementCssValue = element.getCssValue(value).trim().toLowerCase();
        extentTest.pass(String.format("Css value: {%s} of element: {%s}", elementCssValue, element));
        logger.info("Css value: {} of element: {}", elementCssValue, element);
        return elementCssValue;
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
            wait.until(ExpectedConditions.invisibilityOfElementLocated(xpath));
            element = driver.findElement(xpath);
            js.executeScript("arguments[0].scrollIntoView(false);", element);
        } else {
            element = scrollToVisibleElement(xpath);
        }
        String elementCssValue = element.getCssValue(value).trim().toLowerCase();
        extentTest.pass(String.format("Css value: {%s} of element: {%s}", elementCssValue, element));
        logger.info("Css value: {} of element: {}", elementCssValue, element);
        return elementCssValue;
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
        Integer elementsSize = elements.size();
        extentTest.pass(String.format("Size: {%s} of elements: {%s}", elementsSize, elements));
        logger.info("Size: {} of elements: {}", elementsSize, elements);
        return elementsSize;
    }

    /**
     * Get element list size
     *
     * @param xpath
     * @return
     */
    public Integer getSize(By xpath, boolean hidden) {
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xpath));
        if (hidden) {
            wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
        } else {
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        }
        Integer elementsSize = elements.size();
        extentTest.pass(String.format("Size: {%s} of elements: {%s}", elementsSize, elements));
        logger.info("Size: {} of elements: {}", elementsSize, elements);
        return elementsSize;
    }

    /**
     * Get page url
     *
     * @return
     */
    public String getCurrentUrl() {
        extentTest.pass(String.format("Current url: {%s}", driver.getCurrentUrl().trim()));
        logger.info("Current url: {}", driver.getCurrentUrl().trim());
        return driver.getCurrentUrl().trim();
    }

    /**
     * Get page source
     *
     * @return
     */
    public String getPageSource() {
        extentTest.pass(String.format("Page source: {%s}", driver.getPageSource()));
        logger.info("Page source: {}", driver.getPageSource());
        return driver.getPageSource();
    }

    /**
     * Get page title
     *
     * @return
     */
    public String getTitle() {
        extentTest.pass(String.format("Title: {%s}", driver.getTitle()));
        logger.info("Title: {}", driver.getTitle());
        return driver.getTitle();
    }

    /**
     * Get length
     *
     * @param value
     * @return
     */
    public Integer getLenght(String value) {
        extentTest.pass(String.format("Value length: {%s}", value.length()));
        logger.info("Value length: {}", value.length());
        return value.length();
    }

}
