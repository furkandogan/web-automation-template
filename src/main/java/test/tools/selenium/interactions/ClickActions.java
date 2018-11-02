package test.tools.selenium.interactions;

import test.tools.selenium.constants.XpathInjection;
import test.tools.selenium.mapping.MapMethodType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static test.tools.selenium.constants.XpathInjection.createXpath;

public class ClickActions extends FindActions {

    MapMethodType mapMethodType = MapMethodType.CLICK_OBJECT;

    public ClickActions(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    /**
     * Click to element that is visible or invisible
     *
     * @param attr
     */
    public void clickToElement(String attr) {
        WebElement element = findElement(XpathInjection.createXpath(attr, mapMethodType));
        try {
            if (XpathInjection.mapValue.getIsJsEnabled() != null && XpathInjection.mapValue.getIsJsEnabled() == true) {
                clickByJs(element);
                waitUntilJSReady();
            } else {
                clickByElement(element);
                waitUntilJSReady();
            }
        } catch (WebDriverException e) {
            javaScriptClicker(element);
        }
    }

    /**
     * Click to element that is visible
     *
     * @param element
     */
    public void clickByElement(WebElement element) {
        waitForElementClickable(element);
        element.click();
    }


    /**
     * Click to element that is invisible
     *
     * @param element
     */
    public void clickByJs(WebElement element) {
        JavaScriptActions jsActions = new JavaScriptActions(driver);
        jsActions.executeJS("arguments[0].click();", element);
    }

    /**
     * Click to element that is invisible
     *
     * @param element
     */
    public void javaScriptClicker(WebElement element) {
        JavaScriptActions jsActions = new JavaScriptActions(driver);
        jsActions.executeJS("var evt = document.createEvent('MouseEvents');"
                + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
                + "arguments[0].dispatchEvent(evt);", element);
    }

}
