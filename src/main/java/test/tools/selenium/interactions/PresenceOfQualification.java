package test.tools.selenium.interactions;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.tools.selenium.mapping.MapMethodType;

import java.net.HttpURLConnection;
import java.net.URL;

import static test.tools.selenium.constants.XpathInjection.createXpath;
import static test.tools.selenium.constants.XpathInjection.mapValue;

public class PresenceOfQualification extends FindActions {

    MapMethodType mapMethodType = MapMethodType.ELEMENT_DISPLAY;

    public PresenceOfQualification(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    /**
     * Checking the presence of qualification
     *
     * @param attr
     */
    public void isPageLoaded(String attr) {
        waitUntilJQueryReady();
        findElement(createXpath(attr, MapMethodType.PAGE_LOADED));
        JavaScriptActions jsActions = new JavaScriptActions(driver);
        jsActions.executeJS("return window.javascript_errors ");
    }

    /**
     * Checking the presence of qualification
     *
     * @param attr
     * @return
     */
    public boolean isElementExists(String attr) {
        if (findElement(createXpath(attr, mapMethodType)).isDisplayed()) {
            return true;
        }
        return false;
    }

    /**
     * Checking the presence of qualification
     *
     * @param attr
     * @return
     */
    public boolean isElementEmpty(String attr) {
        if (!findElements(createXpath(attr, mapMethodType)).isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Qualification to control the visibility
     *
     * @param attr
     * @return
     */
    public boolean isElementInView(String attr) {
        String jsStmt;
        int index = mapValue.getIndex();
        if (index == 0 || index < 0) {
            jsStmt = String.format("return arguments[%s].size()>0;", createXpath(attr, mapMethodType));
        } else {
            jsStmt = String.format("return arguments[%s%s].size()>0;", createXpath(attr, mapMethodType), index);
        }
        JavaScriptActions jsActions = new JavaScriptActions(driver);
        Object result = jsActions.executeJS(jsStmt, true);
        return result != null && "true".equalsIgnoreCase(result.toString());
    }

    /**
     * Qualification to control the visibility
     *
     * @param attr
     * @return
     */
    public boolean isElementDisplayed(String attr) {
        try {
            return findElement(createXpath(attr, mapMethodType)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Qualification to control the visibility
     *
     * @param element
     * @return
     */
    public boolean isElementDisplayed(WebElement element) {
        if (element.isDisplayed()) {
            return true;
        }
        return false;
    }

    /**
     * Qualification to control the enabled
     *
     * @param attr
     * @return
     */
    public boolean isElementEnabled(String attr) {
        try {
            return findElement(createXpath(attr, mapMethodType)).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checking the presence of qualification
     *
     * @param cssSelector
     * @param index
     * @return
     */
    public boolean isElementExists(String cssSelector, int... index) {
        String jsStmt = index.length == 0 || index[0] < 0 ? String.format("return $('%s').size()>0", cssSelector)
                : String.format("return $('%s').size()>0 && $('%s').eq(%d).size()>0", cssSelector, cssSelector,
                index[0]);
        JavaScriptActions jsActions = new JavaScriptActions(driver);
        Object result = jsActions.executeJS(jsStmt, true);
        return result != null && "true".equalsIgnoreCase(result.toString());
    }

    /**
     * Text Contains Control
     *
     * @param attr
     * @param text contains
     * @return
     */
    public boolean isTextContains(String attr, String text) {
        try {
            GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
            return getElementProperties.getText(attr).contains(text);
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Text Contains Control
     *
     * @param attr
     * @param patternAttr
     * @param patternAttrValue contains
     * @return
     */
    public boolean isAttrValueContains(String attr, String patternAttr, String patternAttrValue) {
        try {
            GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
            return getElementProperties.getAttribute(attr, patternAttr).contains(patternAttrValue);
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Text Equals Control
     *
     * @param attr
     * @param text equals
     * @return
     */
    public boolean isTextEquals(String attr, String text) {
        try {
            GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
            return getElementProperties.getText(attr).equalsIgnoreCase(text);
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Value Equals Control
     *
     * @param attr
     * @param patternAttrValue
     * @return
     */
    public boolean isValueEquals(String attr, String patternAttrValue) {
        try {
            GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
            return getElementProperties.getValue(attr).equalsIgnoreCase(patternAttrValue);
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Attribute Value Equals Control
     *
     * @param attr
     * @param patternAttr
     * @param patternAttrValue
     * @return
     */
    public boolean isAttrValueEquals(String attr, String patternAttr, String patternAttrValue) {
        try {
            GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
            return getElementProperties.getAttribute(attr, patternAttr).equalsIgnoreCase(patternAttrValue);
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Range text
     *
     * @param attr
     * @param low
     * @param high
     * @return
     */
    public boolean rangeText(String attr, int low, int high) {
        try {
            GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
            int elementTextValue = Integer.parseInt(getElementProperties.getText(attr));
            return low <= elementTextValue && elementTextValue <= high;
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Range attribute value
     *
     * @param attr
     * @param low
     * @param high
     * @return
     */
    public boolean rangeAttrValue(String attr, String patternAttr, int low, int high) {
        try {
            GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
            int elementAttrValue = Integer.parseInt(getElementProperties.getAttribute(attr, patternAttr));
            return low <= elementAttrValue && elementAttrValue <= high;
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Text Present Page Source
     *
     * @param text
     * @return
     */
    public boolean isTextPresentOnPageSource(String text) {
        try {
            GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
            return getElementProperties.getPageSource().contains(text);
        } catch (NullPointerException e) {
            return false;
        }
    }


    /**
     * Placeholder alanının kontrolü sağlanır.
     *
     * @param attr
     * @return
     */
    public boolean checkPlaceHolder(String attr, String value) {
        GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
        return getElementProperties.getAttribute(attr, "placeholder").equals(value);
    }

    /**
     * url is exist
     *
     * @param URLName
     * @return
     */
    public boolean linkExists(String URLName) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection con = ( HttpURLConnection ) new URL(URLName).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            return false;
        }
    }
}
