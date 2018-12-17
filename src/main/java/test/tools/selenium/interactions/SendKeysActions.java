package test.tools.selenium.interactions;

import test.tools.selenium.constants.XpathInjection;
import test.tools.selenium.mapping.MapMethodType;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static test.tools.selenium.constants.XpathInjection.createXpath;

public class SendKeysActions extends FindActions {

    MapMethodType mapMethodType = MapMethodType.FILL_INPUT;

    public SendKeysActions(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    /**
     * Send texts or keys in input element
     *
     * @param attr
     * @param inputValue
     */
    public void sendKeysToElement(String attr, String inputValue) {
        WebElement element = findElement(XpathInjection.createXpath(attr, mapMethodType));
        if (XpathInjection.mapValue.getIsJsEnabled() != null && XpathInjection.mapValue.getIsJsEnabled() == true) {
            sendKeysByJsElement(element,inputValue);
            waitUntilJSReady();
        } else {
            sendKeysByElement(element,inputValue);
            waitUntilJSReady();
        }
    }

    /**
     * Send texts or keys in input element
     *
     * @param element
     */
    public void sendKeysByElement(WebElement element, String inputValue) {
        element.clear();
        element.sendKeys(inputValue);
        waitTextToBePresent(element);
    }

    /**
     * Send texts or keys in input element by js
     *
     * @param element
     */
    public void sendKeysByJsElement(WebElement element, String inputValue) {
        JavaScriptActions jsActions = new JavaScriptActions(driver);
        jsActions.executeJS("arguments[0].setAttribute('value', '" + inputValue +"')", element);
    }


    /**
     * File upload to input element
     *
     * @param attr
     * @param filePath
     */
    public void uploadFile(String attr, String filePath) {
        findElement(XpathInjection.createXpath(attr, mapMethodType)).sendKeys(filePath);
    }

    /**
     * Click to enter key on input element
     *
     * @param attr
     */
    public void sendKeysReturn(String attr) {
        WebElement element = findElement(XpathInjection.createXpath(attr, mapMethodType));
        element.sendKeys(Keys.RETURN);
    }

}
