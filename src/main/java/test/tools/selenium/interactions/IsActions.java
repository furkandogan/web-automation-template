package test.tools.selenium.interactions;

import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.HttpURLConnection;
import java.net.URL;

public class IsActions extends GetElementProperties {

    final static Logger logger = LogManager.getLogger(SelectActions.class);

    public IsActions(WebDriver driver, WebDriverWait wait, ExtentTest extentTest) {
        super(driver, wait, extentTest);
    }

    /**
     * Qualification to control the visibility
     *
     * @param element
     * @return
     */
    public boolean isElementDisplayed(WebElement element) {
        await(1);
        return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

    public boolean isElementDisplayed(By xpath) {
        await(1);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(xpath)).isDisplayed();
    }

    /**
     * Qualification to control the enabled
     *
     * @param element
     * @return
     */
    public boolean isElementEnabled(WebElement element) {
        await(1);
        return wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
    }

    public boolean isElementEnabled(By xpath) {
        await(1);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(xpath)).isEnabled();
    }

    /**
     * Qualification to control the exist
     *
     * @param element
     * @return
     */
    public boolean isElementExist(WebElement element) {
        try {
            await(1);
            element.isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Qualification to control the exist
     *
     * @param xpath
     * @return
     */
    public boolean isElementExist(By xpath) {
        try {
            await(1);
            driver.findElement(xpath).isDisplayed();
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
     * @param xpath
     * @param text
     * @return
     */
    public boolean isTextEquals(By xpath, String text) {
        return this.isTextEquals(xpath, text, false);
    }

    /**
     * Text Equals Control
     *
     * @param element
     * @param text
     * @return
     */
    public boolean isTextEquals(WebElement element, String text, boolean hidden) {
        String elementText = getText(element, hidden);
        if (elementText == null || elementText.equalsIgnoreCase("")) {
            return false;
        } else {
            boolean isEquals = elementText.equalsIgnoreCase(text);
            if (extentTest != null)
                extentTest.pass(String.format("Is text: {%s} of element: {%s} equals expected text: {%s} ? - Result: {%s}", elementText, element, text, isEquals));
            logger.info("Is text: {} of element: {} equals expected text: {} ? - Result: {}", elementText, element, text, isEquals);
            return isEquals;
        }
    }

    /**
     * Text Equals Control
     *
     * @param xpath
     * @param text
     * @return
     */
    public boolean isTextEquals(By xpath, String text, boolean hidden) {
        String elementText = getText(xpath, hidden);
        if (elementText == null || elementText.equalsIgnoreCase("")) {
            return false;
        } else {
            boolean isEquals = elementText.equalsIgnoreCase(text);
            if (extentTest != null)
                extentTest.pass(String.format("Is text: {%s} of element: {%s} equals expected text: {%s} ? - Result: {%s}", elementText, xpath, text, isEquals));
            logger.info("Is text: {} of element: {} equals expected text: {} ? - Result: {}", elementText, xpath, text, isEquals);
            return isEquals;
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
     * @param xpath
     * @param text
     * @return
     */
    public boolean isTextContains(By xpath, String text) {
        return this.isTextContains(xpath, text, false);
    }

    /**
     * Text Contains Control
     *
     * @param element
     * @param text
     * @return
     */
    public boolean isTextContains(WebElement element, String text, boolean hidden) {
        String elementText = getText(element, hidden);
        if (elementText == null || elementText.equalsIgnoreCase("")) {
            return false;
        } else {
            boolean isContains = elementText.contains(text);
            if (extentTest != null)
                extentTest.pass(String.format("Is text: {%s} of element: {%s} contains expected text: {%s} ? - Result: {%s}", elementText, element, text, isContains));
            logger.info("Is text: {} of element: {} contains expected text: {} ? - Result: {}", elementText, element, text, isContains);
            return isContains;
        }
    }

    /**
     * Text Contains Control
     *
     * @param xpath
     * @param text
     * @return
     */
    public boolean isTextContains(By xpath, String text, boolean hidden) {
        String elementText = getText(xpath, hidden);
        if (elementText == null || elementText.equalsIgnoreCase("")) {
            return false;
        } else {
            boolean isContains = elementText.contains(text);
            if (extentTest != null)
                extentTest.pass(String.format("Is text: {%s} of element: {%s} contains expected text: {%s} ? - Result: {%s}", elementText, xpath, text, isContains));
            logger.info("Is text: {} of element: {} contains expected text: {} ? - Result: {}", elementText, xpath, text, isContains);
            return isContains;
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
     * @param xpath
     * @param value
     * @return
     */
    public boolean isValueEquals(By xpath, String value) {
        return this.isValueEquals(xpath, value, false);
    }

    /**
     * Value Equals Control
     *
     * @param element
     * @param value
     * @return
     */
    public boolean isValueEquals(WebElement element, String value, boolean hidden) {
        String elementValue = getValue(element, hidden);
        if (elementValue == null || elementValue.equalsIgnoreCase("")) {
            return false;
        } else {
            boolean isEquals = elementValue.equalsIgnoreCase(value);
            if (extentTest != null)
                extentTest.pass(String.format("Is value: {%s} of element: {%s} equals expected value: {%s} ? - Result: {%s}", elementValue, element, value, isEquals));
            logger.info("Is value: {} of element: {} equals expected value: {} ? - Result: {}", elementValue, element, value, isEquals);
            return isEquals;
        }
    }

    /**
     * Value Equals Control
     *
     * @param xpath
     * @param value
     * @return
     */
    public boolean isValueEquals(By xpath, String value, boolean hidden) {
        String elementValue = getValue(xpath, hidden);
        if (elementValue == null || elementValue.equalsIgnoreCase("")) {
            return false;
        } else {
            boolean isEquals = elementValue.equalsIgnoreCase(value);
            if (extentTest != null)
                extentTest.pass(String.format("Is value: {%s} of element: {%s} equals expected value: {%s} ? - Result: {%s}", elementValue, xpath, value, isEquals));
            logger.info("Is value: {} of element: {} equals expected value: {} ? - Result: {}", elementValue, xpath, value, isEquals);
            return isEquals;
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
     * @param xpath
     * @param attr
     * @param value
     * @return
     */
    public boolean isAttrValueEquals(By xpath, String attr, String value) {
        return this.isAttrValueEquals(xpath, attr, value, false);
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
        String elementAttrValue = getAttribute(element, attr, hidden);
        if (elementAttrValue == null || elementAttrValue.equalsIgnoreCase("")) {
            return false;
        } else {
            boolean isEquals = elementAttrValue.equalsIgnoreCase(value);
            if (extentTest != null)
                extentTest.pass(String.format("Is attribute value: {%s} of element: {%s} equals expected value: {%s} ? - Result: {%s}", elementAttrValue, element, value, isEquals));
            logger.info("Is attribute value: {} of element: {} equals expected value: {} ? - Result: {}", elementAttrValue, element, value, isEquals);
            return isEquals;
        }
    }

    /**
     * Attribute Value Equals Control
     *
     * @param xpath
     * @param attr
     * @param value
     * @return
     */
    public boolean isAttrValueEquals(By xpath, String attr, String value, boolean hidden) {
        String elementAttrValue = getAttribute(xpath, attr, hidden);
        if (elementAttrValue == null || elementAttrValue.equalsIgnoreCase("")) {
            return false;
        } else {
            boolean isEquals = elementAttrValue.equalsIgnoreCase(value);
            if (extentTest != null)
                extentTest.pass(String.format("Is attribute value: {%s} of element: {%s} equals expected value: {%s} ? - Result: {%s}", elementAttrValue, xpath, value, isEquals));
            logger.info("Is attribute value: {} of element: {} equals expected value: {} ? - Result: {}", elementAttrValue, xpath, value, isEquals);
            return isEquals;
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
     * @param xpath
     * @param attr
     * @param attrValue
     * @return
     */
    public boolean isAttrValueContains(By xpath, String attr, String attrValue) {
        return this.isAttrValueContains(xpath, attr, attrValue, false);
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
        String elementAttrValue = getAttribute(element, attr, hidden);
        if (elementAttrValue == null || elementAttrValue.equalsIgnoreCase("")) {
            return false;
        } else {
            boolean isContains = elementAttrValue.contains(attrValue);
            if (extentTest != null)
                extentTest.pass(String.format("Is attribute value: {%s} of element: {%s} contains expected value: {%s} ? - Result: {%s}", elementAttrValue, element, attrValue, isContains));
            logger.info("Is attribute value: {} of element: {} contains expected value: {} ? - Result: {}", elementAttrValue, element, attrValue, isContains);
            return isContains;
        }
    }

    /**
     * Attribute Value Contains Control
     *
     * @param xpath
     * @param attr
     * @param attrValue
     * @return
     */
    public boolean isAttrValueContains(By xpath, String attr, String attrValue, boolean hidden) {
        String elementAttrValue = getAttribute(xpath, attr, hidden);
        if (elementAttrValue == null || elementAttrValue.equalsIgnoreCase("")) {
            return false;
        } else {
            boolean isContains = elementAttrValue.contains(attrValue);
            if (extentTest != null)
                extentTest.pass(String.format("Is attribute value: {%s} of element: {%s} contains expected value: {%s} ? - Result: {%s}", elementAttrValue, xpath, attrValue, isContains));
            logger.info("Is attribute value: {} of element: {} contains expected value: {} ? - Result: {}", elementAttrValue, xpath, attrValue, isContains);
            return isContains;
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
     * @param xpath
     * @param low
     * @param high
     * @return
     */
    public boolean rangeText(By xpath, int low, int high) {
        return this.rangeText(xpath, low, high, false);
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
        String elementText = getText(element, hidden);
        if (elementText == null || elementText.equalsIgnoreCase("")) {
            return false;
        } else {
            int elementTextValue = Integer.parseInt(elementText);
            boolean rangeBetweenExpectedValue = low <= elementTextValue && elementTextValue <= high;
            if (extentTest != null)
                extentTest.pass(String.format("Is text value: {%s} of element: {%s} lower than expected value: {%s} and higher than expected value {%s} ? - Result: {%s}", elementTextValue, element, low, high, rangeBetweenExpectedValue));
            logger.info("Is text value: {} of element: {} lower than expected value: {} and higher than expected value {} ? - Result: {}", elementTextValue, element, low, high, rangeBetweenExpectedValue);
            return rangeBetweenExpectedValue;
        }
    }

    /**
     * Range text
     *
     * @param xpath
     * @param low
     * @param high
     * @return
     */
    public boolean rangeText(By xpath, int low, int high, boolean hidden) {
        String elementText = getText(xpath, hidden);
        if (elementText == null || elementText.equalsIgnoreCase("")) {
            return false;
        } else {
            int elementTextValue = Integer.parseInt(elementText);
            boolean rangeBetweenExpectedValue = low <= elementTextValue && elementTextValue <= high;
            if (extentTest != null)
                extentTest.pass(String.format("Is text value: {%s} of element: {%s} lower than expected value: {%s} and higher than expected value {%s} ? - Result: {%s}", elementTextValue, xpath, low, high, rangeBetweenExpectedValue));
            logger.info("Is text value: {} of element: {} lower than expected value: {} and higher than expected value {} ? - Result: {}", elementTextValue, xpath, low, high, rangeBetweenExpectedValue);
            return rangeBetweenExpectedValue;
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
     * @param xpath
     * @param attr
     * @param low
     * @param high
     * @return
     */
    public boolean rangeAttrValue(By xpath, String attr, int low, int high) {
        return this.rangeAttrValue(xpath, attr, low, high, false);
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
        String elementAttrValueText = getAttribute(element, attr, hidden);
        if (elementAttrValueText == null || elementAttrValueText.equalsIgnoreCase("")) {
            return false;
        } else {
            int elementAttrValue = Integer.parseInt(elementAttrValueText);
            boolean rangeBetweenExpectedValue = low <= elementAttrValue && elementAttrValue <= high;
            if (extentTest != null)
                extentTest.pass(String.format("Is attribute value: {%s} of element: {%s} lower than expected value: {%s} and higher than expected value {%s} ? - Result: {%s}", elementAttrValue, element, low, high, rangeBetweenExpectedValue));
            logger.info("Is attribute value: {} of element: {} lower than expected value: {} and higher than expected value {} ? - Result: {}", elementAttrValue, element, low, high, rangeBetweenExpectedValue);
            return rangeBetweenExpectedValue;
        }
    }

    /**
     * Range attribute value
     *
     * @param xpath
     * @param attr
     * @param low
     * @param high
     * @return
     */
    public boolean rangeAttrValue(By xpath, String attr, int low, int high, boolean hidden) {
        String elementAttrValueText = getAttribute(xpath, attr, hidden);
        if (elementAttrValueText == null || elementAttrValueText.equalsIgnoreCase("")) {
            return false;
        } else {
            int elementAttrValue = Integer.parseInt(elementAttrValueText);
            boolean rangeBetweenExpectedValue = low <= elementAttrValue && elementAttrValue <= high;
            if (extentTest != null)
                extentTest.pass(String.format("Is attribute value: {%s} of element:{%s} lower than expected value: {%s} and higher than expected value {%s} ? - Result: {%s}", elementAttrValue, xpath, low, high, rangeBetweenExpectedValue));
            logger.info("Is attribute value: {} of element:{} lower than expected value: {} and higher than expected value {} ? - Result: {}", elementAttrValue, xpath, low, high, rangeBetweenExpectedValue);
            return rangeBetweenExpectedValue;
        }
    }

    /**
     * Text Present Page Source
     *
     * @param text
     * @return
     */
    public boolean isTextPresentOnPageSource(String text) {
        String pageSource = getPageSource();
        if (pageSource == null || pageSource.equalsIgnoreCase("")) {
            return false;
        } else {
            boolean isContains = pageSource.contains(text);
            if (extentTest != null)
                extentTest.pass(String.format("Is page source: {%s} contains expected text: {%s} ? - Result: {%s}", pageSource, text, isContains));
            logger.info("Is page source: {} contains expected text: {} ? - Result: {}", pageSource, text, isContains);
            return isContains;
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
     * @param xpath
     * @param value
     * @return
     */
    public boolean checkPlaceHolder(By xpath, String value) {
        return this.checkPlaceHolder(xpath, value, false);
    }

    /**
     * Placeholder alanının kontrolü sağlanır.
     *
     * @param element
     * @param value
     * @return
     */
    public boolean checkPlaceHolder(WebElement element, String value, boolean hidden) {
        String placeholder = getAttribute(element, "placeholder", hidden);
        if (placeholder == null || placeholder.equalsIgnoreCase("")) {
            return false;
        } else {
            boolean isEquals = placeholder.equals(value);
            if (extentTest != null)
                extentTest.pass(String.format("Is placeholder: {%s} of element: {%s} equals expected text: {%s} ? - Result: {%s}", placeholder, element, value, isEquals));
            logger.info("Is placeholder: {} of element: {} equals expected text: {} ? - Result: {}", placeholder, element, value, isEquals);
            return isEquals;
        }
    }

    /**
     * Placeholder alanının kontrolü sağlanır.
     *
     * @param xpath
     * @param value
     * @return
     */
    public boolean checkPlaceHolder(By xpath, String value, boolean hidden) {
        String placeholder = getAttribute(xpath, "placeholder", hidden);
        if (placeholder == null || placeholder.equalsIgnoreCase("")) {
            return false;
        } else {
            boolean isEquals = placeholder.equals(value);
            if (extentTest != null)
                extentTest.pass(String.format("Is placeholder: {%s} of element: {%s} equals expected text: {%s} ? - Result: {%s}", placeholder, xpath, value, isEquals));
            logger.info("Is placeholder: {} of element: {} equals expected text: {} ? - Result: {}", placeholder, xpath, value, isEquals);
            return isEquals;
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
            boolean isConnOk = (con.getResponseCode() == HttpURLConnection.HTTP_OK);
            if (extentTest != null)
                extentTest.pass(String.format("Connection is: {%s}", isConnOk));
            logger.info("Connection is: {}", isConnOk);
            return isConnOk;
        } catch (Exception e) {
            if (extentTest != null){
                extentTest.fail(e);
            }
            logger.error(e);
            return false;
        }
    }
}
