package test.tools.selenium.interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SelectActions {

    public WebDriver driver;
    public WebDriverWait wait;
    public ActionsAPI actionsAPI;

    public SelectActions(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        actionsAPI = new ActionsAPI(driver,wait);
    }


    /**
     * @param element
     */
    public WebElement getFirstSelectedOptionFromElement(WebElement element) {
        actionsAPI.scrollToVisibleElement(element);
        return new Select(element).getFirstSelectedOption();
    }

    /**
     * @param element
     */
    public List<WebElement> getAllSelectedOptionsFromElement(WebElement element) {
        actionsAPI.scrollToVisibleElement(element);
        return new Select(element).getAllSelectedOptions();
    }

    /**
     * @param element
     */
    public List<WebElement> getOptionsFromElement(WebElement element) {
        actionsAPI.scrollToVisibleElement(element);
        return new Select(element).getOptions();
    }

    /**
     * @param element
     * @param value
     */
    public void selectByValue(WebElement element, String value) {
        actionsAPI.scrollToVisibleElement(element);
        new Select(element).selectByValue(value);
        wait.until(ExpectedConditions.attributeToBe(element, "value", value));
    }

    /**
     * @param xpath
     * @param value
     */
    public void selectByValue(By xpath, String value) {
        new Select(actionsAPI.scrollToVisibleElement(xpath)).selectByValue(value);
        wait.until(ExpectedConditions.attributeToBe(xpath, "value", value));
    }

    /**
     * @param element
     * @param value
     */
    public void selectByValueByJs(WebElement element, String value) {
        actionsAPI.scrollToInvisibleElement(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '" + value + "'", element);
        wait.until(ExpectedConditions.attributeToBe(element, "value", value));
    }


    /**
     * @param xpath
     * @param value
     */
    public void selectByValueByJs(By xpath, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '" + value + "'", actionsAPI.scrollToInvisibleElement(xpath));
        wait.until(ExpectedConditions.attributeToBe(xpath, "value", value));
    }

    /**
     * @param element
     * @param text
     */
    public void selectByVisibleText(WebElement element, String text) {
        actionsAPI.scrollToVisibleElement(element);
        new Select(element).selectByVisibleText(text);
        wait.until(ExpectedConditions.attributeToBe(element, "value", element.getAttribute("value")));
    }

    /**
     * @param xpath
     * @param text
     */
    public void selectByVisibleText(By xpath, String text) {
        WebElement element = actionsAPI.scrollToVisibleElement(xpath);
        new Select(element).selectByVisibleText(text);
        wait.until(ExpectedConditions.attributeToBe(xpath, "value", element.getAttribute("value")));
    }

    /**
     * @param element
     * @param index
     */
    public void selectByIndex(WebElement element, int index) {
        actionsAPI.scrollToVisibleElement(element);
        new Select(element).selectByIndex(index);
        wait.until(ExpectedConditions.attributeToBe(element, "value", element.getAttribute("value")));
    }

    /**
     * @param xpath
     * @param index
     */
    public void selectByIndex(By xpath, int index) {
        WebElement element = actionsAPI.scrollToVisibleElement(xpath);
        new Select(element).selectByIndex(index);
        wait.until(ExpectedConditions.attributeToBe(xpath, "value", element.getAttribute("value")));
    }

    /**
     * @param element
     * @param optionText
     */
    public void selectByVisibleContainText(WebElement element, String optionText) {
        actionsAPI.scrollToVisibleElement(element);
        List<WebElement> options = getOptionsFromElement(element);
        for (WebElement option : options) {
            if (option.getText().contains(optionText)) {
                option.click();
                break;
            }
        }
    }


    /**
     * @param xpath
     * @param optionText
     */
    public void selectByVisibleContainText(By xpath, String optionText) {
        WebElement element = actionsAPI.scrollToVisibleElement(xpath);
        List<WebElement> options = getOptionsFromElement(element);
        for (WebElement option : options) {
            if (option.getText().contains(optionText)) {
                option.click();
                break;
            }
        }
    }

    /**
     * @param element
     * @param value
     */
    public void deselectElementByValue(WebElement element, String value) {
        actionsAPI.scrollToVisibleElement(element);
        new Select(element).deselectByValue(value);
    }

    /**
     * @param element
     * @param text
     */
    public void deselectByVisibleText(WebElement element, String text) {
        actionsAPI.scrollToVisibleElement(element);
        new Select(element).deselectByVisibleText(text);
    }

    /**
     * @param element
     * @param index
     */
    public void deselectByIndex(WebElement element, int index) {
        actionsAPI.scrollToVisibleElement(element);
        new Select(element).deselectByIndex(index);
    }

    /**
     * @param element
     */
    public void deselectElementAllOptions(WebElement element) {
        actionsAPI.scrollToVisibleElement(element);
        new Select(element).deselectAll();
    }

    /**
     * @param element
     */
    public void selectMapPoint(WebElement element) {
        actionsAPI.scrollToVisibleElement(element);
        String lat = getFirstSelectedOptionFromElement(element).getAttribute("lat");
        String lon = getFirstSelectedOptionFromElement(element).getAttribute("lon");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', arguments[1]);", element,
                lat + "," + lon);
    }

}
