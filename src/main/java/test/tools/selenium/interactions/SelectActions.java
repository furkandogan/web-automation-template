package test.tools.selenium.interactions;

import test.tools.selenium.constants.XpathInjection;
import test.tools.selenium.mapping.MapMethodType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static test.tools.selenium.constants.XpathInjection.createXpath;

public class SelectActions extends FindActions {

    MapMethodType mapMethodType = MapMethodType.SELECT_OPTION;

    public SelectActions(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    /**
     * @param attr
     */
    public Select selectElement(String attr) {
        WebElement element = findElement(XpathInjection.createXpath(attr, mapMethodType));
        waitElementToBeSelected(element);
        return new Select(element);
    }


    /**
     * @param attr
     */
    public WebElement getFirstSelectedOptionFromElement(String attr) {
        return selectElement(attr).getFirstSelectedOption();
    }

    /**
     * @param attr
     */
    public List<WebElement> getAllSelectedOptionsFromElement(String attr) {
        return selectElement(attr).getAllSelectedOptions();
    }

    /**
     * @param attr
     */
    public List<WebElement> getOptionsFromElement(String attr) {
        return selectElement(attr).getOptions();
    }

    /**
     * @param attr
     * @param option
     */
    public void selectElementByVisibleText(String attr, String option) {
        selectElement(attr).selectByVisibleText(option);
    }

    /**
     * @param attr
     * @param option
     */
    public void deselectElementByVisibleText(String attr, String option) {
        selectElement(attr).deselectByVisibleText(option);
    }

    /**
     * @param attr
     * @param index
     */
    public void selectElementByIndex(String attr, int index) {
        selectElement(attr).selectByIndex(index);
    }

    /**
     * @param attr
     * @param index
     */
    public void deselectElementByIndex(String attr, int index) {
        selectElement(attr).deselectByIndex(index);
    }

    /**
     * @param attr
     * @param value
     */
    public void selectElementByIndex(String attr, String value) {
        selectElement(attr).selectByValue(value);
    }

    /**
     * @param attr
     * @param value
     */
    public void deselectElementByIndex(String attr, String value) {
        selectElement(attr).deselectByValue(value);
    }

    /**
     * @param attr
     */
    public void deselectElementAllOptions(String attr) {
        selectElement(attr).deselectAll();
    }

    /**
     * @param attr
     * @param optionText
     */
    public void selectByVisibleContainText(String attr, String optionText) {
        List<WebElement> options = getOptionsFromElement(attr);
        for (WebElement option : options) {
            if (options.contains(optionText)) {
                option.click();
                break;
            }
        }
    }

    /**
     * @param attr
     * @param mapLocationPointAttr
     */
    public void selectMapPoint(String attr, String mapLocationPointAttr) {
        String lat = getFirstSelectedOptionFromElement(attr).getAttribute("lat");
        String lon = getFirstSelectedOptionFromElement(attr).getAttribute("lon");
        WebElement element = findElement(XpathInjection.createXpath(mapLocationPointAttr, mapMethodType));
        JavaScriptActions jsActions = new JavaScriptActions(driver);
        jsActions.executeJS("arguments[0].setAttribute('value', arguments[1]);", element,
                lat + "," + lon);
    }

}
