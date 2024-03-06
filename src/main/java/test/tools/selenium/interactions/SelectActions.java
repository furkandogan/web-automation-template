package test.tools.selenium.interactions;

import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SelectActions extends ActionsAPI {

    final static Logger logger = LogManager.getLogger(SelectActions.class);

    public SelectActions(WebDriver driver, WebDriverWait wait, ExtentTest extentTest) {
        super(driver, wait, extentTest);
    }

    /**
     * @param element
     */
    public WebElement getFirstSelectedOptionFromElement(WebElement element) {
        scrollToVisibleElement(element);
        await(2);
        return new Select(element).getFirstSelectedOption();
    }

    /**
     * @param element
     */
    public List<WebElement> getAllSelectedOptionsFromElement(WebElement element) {
        scrollToVisibleElement(element);
        await(2);
        return new Select(element).getAllSelectedOptions();
    }

    /**
     * @param element
     */
    public List<WebElement> getOptionsFromElement(WebElement element) {
        scrollToVisibleElement(element);
        await(2);
        return new Select(element).getOptions();
    }

    /**
     * @param element
     * @param value
     */
    public void selectByValue(WebElement element, String value) {
        try {
            await(2);
            scrollToVisibleElement(element);
            await(2);
            new Select(element).selectByValue(value);
            await(2);
            wait.until(ExpectedConditions.attributeToBe(element, "value", value));
            if (extentTest != null)
                extentTest.pass(String.format("Selected value: {%s} from element: {%s} ", value, element));
            logger.info("Selected value: {} from element: {} ", value, element);
        } catch (Exception e) {
            highlightElement(element);
            if (extentTest != null){
                extentTest.fail(e);
            }
            logger.error(e);
        }
    }

    /**
     * @param xpath
     * @param value
     */
    public void selectByValue(By xpath, String value) {
        WebElement element = null;
        try {
            await(2);
            element = scrollToVisibleElement(xpath);
            await(2);
            new Select(element).selectByValue(value);
            await(2);
            wait.until(ExpectedConditions.attributeToBe(xpath, "value", value));
            if (extentTest != null)
                extentTest.pass(String.format("Selected value: {%s} from element: {%s} ", value, element));
            logger.info("Selected value: {} from element: {} ", value, element);
        } catch (Exception e) {
            highlightElement(element);
            if (extentTest != null){
                extentTest.fail(e);
            }
            logger.error(e);
        }
    }

    /**
     * @param element
     * @param value
     */
    public void selectByValueByJs(WebElement element, String value) {
        try {
            await(1);
            js.executeScript("arguments[0].scrollIntoView(false);", element);
            await(1);
            js.executeScript("arguments[0].value = '" + value + "'", element);
            await(1);
            wait.until(ExpectedConditions.attributeToBe(element, "value", value));
            if (extentTest != null)
                extentTest.pass(String.format("Selected value: {%s} from element: {%s} ", value, element));
            logger.info("Selected value: {} from element: {} ", value, element);
        } catch (Exception e) {
            highlightElement(element);
            if (extentTest != null){
                extentTest.fail(e);
            }
            logger.error(e);
        }
    }


    /**
     * @param xpath
     * @param value
     */
    public void selectByValueByJs(By xpath, String value) {
        WebElement element = null;
        try {
            await(2);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(xpath));
            await(2);
            element = driver.findElement(xpath);
            await(1);
            js.executeScript("arguments[0].scrollIntoView(false);", element);
            await(1);
            js.executeScript("arguments[0].value = '" + value + "'", element);
            await(1);
            wait.until(ExpectedConditions.attributeToBe(xpath, "value", value));
            if (extentTest != null)
                extentTest.pass(String.format("Selected value: {%s} from element: {%s} ", value, element));
            logger.info("Selected value: {} from element: {} ", value, element);
        } catch (Exception e) {
            highlightElement(element);
            if (extentTest != null){
                extentTest.fail(e);
            }
            logger.error(e);
        }
    }

    /**
     * @param element
     * @param text
     */
    public void selectByVisibleText(WebElement element, String text) {
        try {
            await(2);
            scrollToVisibleElement(element);
            await(2);
            new Select(element).selectByVisibleText(text);
            await(2);
            wait.until(ExpectedConditions.attributeToBe(element, "value", element.getAttribute("value")));
            if (extentTest != null)
                extentTest.pass(String.format("Selected text: {%s} from element: {%s} ", text, element));
            logger.info("Selected text: {} from element: {} ", text, element);
        } catch (Exception e) {
            highlightElement(element);
            if (extentTest != null){
                extentTest.fail(e);
            }
            logger.error(e);
        }
    }

    /**
     * @param xpath
     * @param text
     */
    public void selectByVisibleText(By xpath, String text) {
        WebElement element = null;
        try {
            await(2);
            element = scrollToVisibleElement(xpath);
            await(2);
            new Select(element).selectByVisibleText(text);
            await(2);
            wait.until(ExpectedConditions.attributeToBe(xpath, "value", element.getAttribute("value")));
            if (extentTest != null)
                extentTest.pass(String.format("Selected text: {%s} from element: {%s} ", text, element));
            logger.info("Selected text: {} from element: {} ", text, element);
        } catch (Exception e) {
            highlightElement(element);
            if (extentTest != null){
                extentTest.fail(e);
            }
            logger.error(e);
        }
    }

    /**
     * @param element
     * @param index
     */
    public void selectByIndex(WebElement element, int index) {
        try {
            await(2);
            scrollToVisibleElement(element);
            await(2);
            new Select(element).selectByIndex(index);
            await(2);
            wait.until(ExpectedConditions.attributeToBe(element, "value", element.getAttribute("value")));
            if (extentTest != null)
                extentTest.pass(String.format("Selected index: {%s} from element: {%s} ", index, element));
            logger.info("Selected index: {} from element: {} ", index, element);
        } catch (Exception e) {
            highlightElement(element);
            if (extentTest != null){
                extentTest.fail(e);
            }
            logger.error(e);
        }
    }

    /**
     * @param xpath
     * @param index
     */
    public void selectByIndex(By xpath, int index) {
        WebElement element = null;
        try {
            await(2);
            element = scrollToVisibleElement(xpath);
            await(2);
            new Select(element).selectByIndex(index);
            await(2);
            wait.until(ExpectedConditions.attributeToBe(xpath, "value", element.getAttribute("value")));
            if (extentTest != null)
                extentTest.pass(String.format("Selected index: {%s} from element: {%s} ", index, element));
            logger.info("Selected index: {} from element: {} ", index, element);
        } catch (Exception e) {
            highlightElement(element);
            if (extentTest != null){
                extentTest.fail(e);
            }
            logger.error(e);
        }
    }

    /**
     * @param element
     * @param optionText
     */
    public void selectByVisibleContainText(WebElement element, String optionText) {
        await(2);
        scrollToVisibleElement(element);
        await(2);
        List<WebElement> options = getOptionsFromElement(element);
        await(2);
        for (WebElement option : options) {
            try {
                if (option.getText().contains(optionText)) {
                    await(1);
                    option.click();
                    break;
                }
            if (extentTest != null)
                if (extentTest != null)
                extentTest.pass(String.format("Selected text: {%s} from element: {%s} ", optionText, element));
                logger.info("Selected text: {} from element: {} ", optionText, element);
            } catch (Exception e) {
                highlightElement(element);
                if (extentTest != null){
                extentTest.fail(e);
            }
            logger.error(e);
            }
        }
    }


    /**
     * @param xpath
     * @param optionText
     */
    public void selectByVisibleContainText(By xpath, String optionText) {
        await(2);
        WebElement element = scrollToVisibleElement(xpath);
        await(2);
        List<WebElement> options = getOptionsFromElement(element);
        await(2);
        for (WebElement option : options) {
            try {
                if (option.getText().contains(optionText)) {
                    await(1);
                    option.click();
                    break;
                }
            if (extentTest != null)
                if (extentTest != null)
                extentTest.pass(String.format("Selected text: {%s} from element: {%s} ", optionText, element));
                logger.info("Selected text: {} from element: {} ", optionText, element);
            } catch (Exception e) {
                highlightElement(element);
                if (extentTest != null){
                extentTest.fail(e);
            }
            logger.error(e);
            }
        }
    }

    /**
     * @param element
     * @param value
     */
    public void deselectElementByValue(WebElement element, String value) {
        try {
            await(2);
            scrollToVisibleElement(element);
            await(2);
            new Select(element).deselectByValue(value);
            if (extentTest != null)
                extentTest.pass(String.format("Deselected value: {%s} from element: {%s} ", value, element));
            logger.info("Deselected value: {} from element: {} ", value, element);
        } catch (Exception e) {
            highlightElement(element);
            if (extentTest != null){
                extentTest.fail(e);
            }
            logger.error(e);
        }
    }

    /**
     * @param element
     * @param text
     */
    public void deselectByVisibleText(WebElement element, String text) {
        try {
            await(2);
            scrollToVisibleElement(element);
            await(2);
            new Select(element).deselectByVisibleText(text);
            if (extentTest != null)
                extentTest.pass(String.format("Deselected text: {%s} from element: {%s} ", text, element));
            logger.info("Deselected text: {} from element: {} ", text, element);
        } catch (Exception e) {
            highlightElement(element);
            if (extentTest != null){
                extentTest.fail(e);
            }
            logger.error(e);
        }
    }

    /**
     * @param element
     * @param index
     */
    public void deselectByIndex(WebElement element, int index) {
        try {
            await(2);
            scrollToVisibleElement(element);
            await(2);
            new Select(element).deselectByIndex(index);
            if (extentTest != null)
                extentTest.pass(String.format("Deselected index: {%s} from element: {%s} ", index, element));
            logger.info("Deselected index: {} from element: {} ", index, element);
        } catch (Exception e) {
            highlightElement(element);
            if (extentTest != null){
                extentTest.fail(e);
            }
            logger.error(e);
        }
    }

    /**
     * @param element
     */
    public void deselectElementAllOptions(WebElement element) {
        try {
            await(2);
            scrollToVisibleElement(element);
            await(2);
            new Select(element).deselectAll();
            if (extentTest != null)
                extentTest.pass(String.format("Deselected all options from element: {%s} ", element));
            logger.info("Deselected all options from element: {} ", element);
        } catch (Exception e) {
            highlightElement(element);
            if (extentTest != null){
                extentTest.fail(e);
            }
            logger.error(e);
        }
    }

    /**
     * @param element
     */
    public void selectMapPoint(WebElement element) {
        try {
            await(2);
            scrollToVisibleElement(element);
            await(1);
            String lat = getFirstSelectedOptionFromElement(element).getAttribute("lat");
            await(1);
            String lon = getFirstSelectedOptionFromElement(element).getAttribute("lon");
            await(1);
            js.executeScript("arguments[0].setAttribute('value', arguments[1]);", element,
                    lat + "," + lon);
            if (extentTest != null)
                extentTest.pass(String.format("Selected coordinates lat: {%s} - lon: {%s} from element: {%s} ", lat, lon, element));
            logger.info("Selected coordinates lat: {} - lon: {} from element: {} ", lat, lon, element);
        } catch (Exception e) {
            highlightElement(element);
            if (extentTest != null){
                extentTest.fail(e);
            }
            logger.error(e);
        }
    }

}
