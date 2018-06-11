package test.tools.selenium.interactions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.tools.selenium.mapping.MapMethodType;

import static test.tools.selenium.constants.XpathInjection.createXpath;

public class GetElementProperties extends FindActions {

    MapMethodType mapMethodType = MapMethodType.ELEMENT_DISPLAY;

    public GetElementProperties(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    /**
     * Get element attributeValue
     *
     * @param attr
     * @return
     */
    public String getAttribute(String attr, String patternAttr) {
        return findElement(createXpath(attr, mapMethodType)).getAttribute(patternAttr).trim().toLowerCase();
    }

    /**
     * Get element value
     *
     * @param attr
     * @return
     */
    public String getValue(String attr) {
        return getAttribute(attr, "value");
    }

    /**
     * Get element text
     *
     * @param attr
     * @return
     */
    public String getText(String attr) {
        return findElement(createXpath(attr, mapMethodType)).getText().trim().toLowerCase();
    }

    /**
     * Get element tag
     *
     * @param attr
     * @return
     */
    public String getTagName(String attr) {
        return findElement(createXpath(attr, mapMethodType)).getTagName().trim().toLowerCase();
    }

    /**
     * Get element cssValue
     *
     * @param attr
     * @return
     */
    public String getCssValue(String attr, String value) {
        return findElement(createXpath(attr, mapMethodType)).getCssValue(value).trim().toLowerCase();
    }

    /**
     * Get element list size
     *
     * @param attr
     * @return
     */
    public Integer getSize(String attr) {
        return findElements(createXpath(attr, mapMethodType)).size();
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
