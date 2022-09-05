package test.tools.selenium.interactions;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.HttpURLConnection;
import java.net.URL;

public class PresenceOfQualification {

    public WebDriver driver;
    public WebDriverWait wait;

    public PresenceOfQualification(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /**
     * Qualification to control the visibility
     *
     * @param element
     * @return
     */
    public boolean isElementDisplayed(WebElement element) {
        return wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

    /**
     * Qualification to control the enabled
     *
     * @param element
     * @return
     */
    public boolean isElementEnabled(WebElement element) {
        return wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(element)).isEnabled();
    }

    /**
     * Qualification to control the exist
     *
     * @param element
     * @return
     */
    public boolean isElementExist(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Text Equals Control
     *
     * @param element
     * @param text
     * @return
     */
    public boolean isTextEquals(WebElement element, String text) {
        return this.isTextEquals(element, text, false);
    }

    /**
     * Text Equals Control
     *
     * @param element
     * @param text
     * @return
     */
    public boolean isTextEquals(WebElement element, String text, boolean hidden) {
        GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
        String elementText = getElementProperties.getText(element, hidden);
        if (elementText == null || elementText.equalsIgnoreCase("")) {
            return false;
        } else {
            return elementText.equalsIgnoreCase(text);
        }
    }

    /**
     * Text Contains Control
     *
     * @param element
     * @param text
     * @return
     */
    public boolean isTextContains(WebElement element, String text) {
        return this.isTextContains(element, text, false);
    }

    /**
     * Text Contains Control
     *
     * @param element
     * @param text
     * @return
     */
    public boolean isTextContains(WebElement element, String text, boolean hidden) {
        GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
        String elementText = getElementProperties.getText(element, hidden);
        if (elementText == null || elementText.equalsIgnoreCase("")) {
            return false;
        } else {
            return elementText.contains(text);
        }
    }

    /**
     * Value Equals Control
     *
     * @param element
     * @param value
     * @return
     */
    public boolean isValueEquals(WebElement element, String value) {
        return this.isValueEquals(element, value, false);
    }

    /**
     * Value Equals Control
     *
     * @param element
     * @param value
     * @return
     */
    public boolean isValueEquals(WebElement element, String value, boolean hidden) {
        GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
        String elementValue = getElementProperties.getValue(element, hidden);
        if (elementValue == null || elementValue.equalsIgnoreCase("")) {
            return false;
        } else {
            return elementValue.equalsIgnoreCase(value);
        }
    }

    /**
     * Attribute Value Equals Control
     *
     * @param element
     * @param attr
     * @param value
     * @return
     */
    public boolean isAttrValueEquals(WebElement element, String attr, String value) {
        return this.isAttrValueEquals(element, attr, value, false);
    }

    /**
     * Attribute Value Equals Control
     *
     * @param element
     * @param attr
     * @param value
     * @return
     */
    public boolean isAttrValueEquals(WebElement element, String attr, String value, boolean hidden) {
        GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
        String elementText = getElementProperties.getAttribute(element, attr, hidden);
        if (elementText == null || elementText.equalsIgnoreCase("")) {
            return false;
        } else {
            return elementText.equalsIgnoreCase(value);
        }
    }

    /**
     * Attribute Value Contains Control
     *
     * @param element
     * @param attr
     * @param attrValue
     * @return
     */
    public boolean isAttrValueContains(WebElement element, String attr, String attrValue) {
        return this.isAttrValueContains(element, attr, attrValue, false);
    }

    /**
     * Attribute Value Contains Control
     *
     * @param element
     * @param attr
     * @param attrValue
     * @return
     */
    public boolean isAttrValueContains(WebElement element, String attr, String attrValue, boolean hidden) {
        GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
        String elementText = getElementProperties.getAttribute(element, attr, hidden);
        if (elementText == null || elementText.equalsIgnoreCase("")) {
            return false;
        } else {
            return elementText.contains(attrValue);
        }
    }

    /**
     * Range text
     *
     * @param element
     * @param low
     * @param high
     * @return
     */
    public boolean rangeText(WebElement element, int low, int high) {
        return this.rangeText(element, low, high, false);
    }

    /**
     * Range text
     *
     * @param element
     * @param low
     * @param high
     * @return
     */
    public boolean rangeText(WebElement element, int low, int high, boolean hidden) {
        GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
        String elementText = getElementProperties.getText(element, hidden);
        if (elementText == null || elementText.equalsIgnoreCase("")) {
            return false;
        } else {
            int elementTextValue = Integer.parseInt(elementText);
            return low <= elementTextValue && elementTextValue <= high;
        }
    }

    /**
     * Range attribute value
     *
     * @param element
     * @param attr
     * @param low
     * @param high
     * @return
     */
    public boolean rangeAttrValue(WebElement element, String attr, int low, int high) {
        return this.rangeAttrValue(element, attr, low, high, false);
    }

    /**
     * Range attribute value
     *
     * @param element
     * @param attr
     * @param low
     * @param high
     * @return
     */
    public boolean rangeAttrValue(WebElement element, String attr, int low, int high, boolean hidden) {
        GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
        String elementAttrValueText = getElementProperties.getAttribute(element, attr, hidden);
        if (elementAttrValueText == null || elementAttrValueText.equalsIgnoreCase("")) {
            return false;
        } else {
            int elementAttrValue = Integer.parseInt(elementAttrValueText);
            return low <= elementAttrValue && elementAttrValue <= high;
        }
    }

    /**
     * Text Present Page Source
     *
     * @param text
     * @return
     */
    public boolean isTextPresentOnPageSource(String text) {
        GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
        String pageSource = getElementProperties.getPageSource();
        if (pageSource == null || pageSource.equalsIgnoreCase("")) {
            return false;
        } else {
            return pageSource.contains(text);
        }
    }

    /**
     * Placeholder alanının kontrolü sağlanır.
     *
     * @param element
     * @param value
     * @return
     */
    public boolean checkPlaceHolder(WebElement element, String value) {
        return this.checkPlaceHolder(element, value, false);
    }

    /**
     * Placeholder alanının kontrolü sağlanır.
     *
     * @param element
     * @param value
     * @return
     */
    public boolean checkPlaceHolder(WebElement element, String value, boolean hidden) {
        GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
        String placeholder = getElementProperties.getAttribute(element, "placeholder", hidden);
        if (placeholder == null || placeholder.equalsIgnoreCase("")) {
            return false;
        } else {
            return placeholder.equals(value);
        }
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
            HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            return false;
        }
    }
}
