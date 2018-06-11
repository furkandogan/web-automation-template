package test.tools.selenium.interactions;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.tools.selenium.mapping.MapMethodType;

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
    public void sendKeysByElement(String attr, String inputValue) {
        WebElement element = findElement(createXpath(attr, mapMethodType));
        element.clear();
        element.sendKeys(inputValue);
    }


    /**
     * File upload to input element
     *
     * @param attr
     * @param filePath
     */
    public void uploadFile(String attr, String filePath) {
        findElement(createXpath(attr, mapMethodType)).sendKeys(filePath);
    }

    /**
     * Click to enter key on input element
     *
     * @param attr
     */
    public void sendKeysReturn(String attr) {
        WebElement element = findElement(createXpath(attr, mapMethodType));
        element.sendKeys(Keys.RETURN);
    }

}
