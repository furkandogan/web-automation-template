package test.tools.selenium.interactions;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SelectActions {

    public WebDriver driver;
    public WebDriverWait wait;

    public SelectActions(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }


    /**
     * @param element
     */
    public WebElement getFirstSelectedOptionFromElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return new Select(element).getFirstSelectedOption();
        } catch (StaleElementReferenceException e) {
            return getFirstSelectedOptionFromElement(element);
        }
    }

    /**
     * @param element
     */
    public List<WebElement> getAllSelectedOptionsFromElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return new Select(element).getAllSelectedOptions();
        } catch (StaleElementReferenceException e) {
            return getAllSelectedOptionsFromElement(element);
        }
    }

    /**
     * @param element
     */
    public List<WebElement> getOptionsFromElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return new Select(element).getOptions();
        } catch (StaleElementReferenceException e) {
            return getOptionsFromElement(element);
        }
    }

    /**
     * @param element
     * @param value
     */
    public void selectByValue(WebElement element, String value) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            new Select(element).selectByValue(value);
            wait.until(ExpectedConditions.attributeToBe(element, "value", value));
        } catch (StaleElementReferenceException e) {
            selectByValue(element,value);
        }
    }

    /**
     * @param xpath
     * @param value
     */
    public void selectByValue(By xpath, String value) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
            new Select(driver.findElement(xpath)).selectByValue(value);
            wait.until(ExpectedConditions.attributeToBe(xpath, "value", value));
        } catch (StaleElementReferenceException e) {
            selectByValue(xpath,value);
        }
    }

    /**
     * @param element
     * @param value
     */
    public void selectByValueByJs(WebElement element, String value) {
        try {
            wait.until(ExpectedConditions.invisibilityOf(element));
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = '" + value + "'", element);
            wait.until(ExpectedConditions.attributeToBe(element, "value", value));
        } catch (StaleElementReferenceException e) {
            selectByValueByJs(element,value);
        }
    }


    /**
     * @param xpath
     * @param value
     */
    public void selectByValueByJs(By xpath, String value) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(xpath));
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = '" + value + "'", driver.findElement(xpath));
            wait.until(ExpectedConditions.attributeToBe(xpath, "value", value));
        } catch (StaleElementReferenceException e) {
            selectByValueByJs(xpath,value);
        }
    }

    /**
     * @param element
     * @param text
     */
    public void selectByVisibleText(WebElement element, String text) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            new Select(element).selectByVisibleText(text);
            wait.until(ExpectedConditions.attributeToBe(element, "value", element.getAttribute("value")));
        } catch (StaleElementReferenceException e) {
            selectByVisibleText(element,text);
        }
    }

    /**
     * @param xpath
     * @param text
     */
    public void selectByVisibleText(By xpath, String text) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
            WebElement element = driver.findElement(xpath);
            new Select(element).selectByVisibleText(text);
            wait.until(ExpectedConditions.attributeToBe(xpath, "value", element.getAttribute("value")));
        } catch (StaleElementReferenceException e) {
            selectByVisibleText(xpath,text);
        }
    }

    /**
     * @param element
     * @param index
     */
    public void selectByIndex(WebElement element, int index) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            new Select(element).selectByIndex(index);
            wait.until(ExpectedConditions.attributeToBe(element, "value", element.getAttribute("value")));
        } catch (StaleElementReferenceException e) {
            selectByIndex(element,index);
        }
    }

    /**
     * @param xpath
     * @param index
     */
    public void selectByIndex(By xpath, int index) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
            WebElement element = driver.findElement(xpath);
            new Select(element).selectByIndex(index);
            wait.until(ExpectedConditions.attributeToBe(xpath, "value", element.getAttribute("value")));
        } catch (StaleElementReferenceException e) {
            selectByIndex(xpath,index);
        }
    }

    /**
     * @param element
     * @param optionText
     */
    public void selectByVisibleContainText(WebElement element, String optionText) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            List<WebElement> options = getOptionsFromElement(element);
            for (WebElement option : options) {
                if (option.getText().contains(optionText)) {
                    option.click();
                    break;
                }
            }
        } catch (StaleElementReferenceException e) {
            selectByVisibleContainText(element,optionText);
        }
    }


    /**
     * @param xpath
     * @param optionText
     */
    public void selectByVisibleContainText(By xpath, String optionText) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(xpath));
            WebElement element = driver.findElement(xpath);
            List<WebElement> options = getOptionsFromElement(element);
            for (WebElement option : options) {
                if (option.getText().contains(optionText)) {
                    option.click();
                    break;
                }
            }
        } catch (StaleElementReferenceException e) {
            selectByVisibleContainText(xpath,optionText);
        }
    }

    /**
     * @param element
     * @param value
     */
    public void deselectElementByValue(WebElement element, String value) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            new Select(element).deselectByValue(value);
        } catch (StaleElementReferenceException e) {
            deselectElementByValue(element,value);
        }
    }

    /**
     * @param element
     * @param text
     */
    public void deselectByVisibleText(WebElement element, String text) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            new Select(element).deselectByVisibleText(text);
        } catch (StaleElementReferenceException e) {
            deselectByVisibleText(element,text);
        }
    }

    /**
     * @param element
     * @param index
     */
    public void deselectByIndex(WebElement element, int index) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            new Select(element).deselectByIndex(index);
        } catch (StaleElementReferenceException e) {
            deselectByIndex(element,index);
        }
    }

    /**
     * @param element
     */
    public void deselectElementAllOptions(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            new Select(element).deselectAll();
        } catch (StaleElementReferenceException e) {
            deselectElementAllOptions(element);
        }
    }

    /**
     * @param element
     */
    public void selectMapPoint(WebElement element) {
        String lat = getFirstSelectedOptionFromElement(element).getAttribute("lat");
        String lon = getFirstSelectedOptionFromElement(element).getAttribute("lon");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', arguments[1]);", element,
                lat + "," + lon);
    }

}
