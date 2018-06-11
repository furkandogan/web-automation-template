package test.tools.selenium.interactions;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WaitingActions {

    public WebDriver driver;
    public WebDriverWait wait;


    public WaitingActions(WebDriver driver) {
        this.driver = driver;
    }

    public WaitingActions(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /**
     * @param by
     * @return
     */
    public WebElement waitForElementPresence(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * @param by
     * @return
     */
    public List<WebElement> waitForElementsPresence(By by) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    /**
     * @param by
     */
    public void waitForElementClickable(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    /**
     * @param element
     */
    public void waitForElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * @param element
     */
    public void waitForElementSelectable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeSelected(element));
    }

    /**
     * @param by
     */
    public void waitForElementSelectable(By by) {
        wait.until(ExpectedConditions.elementToBeSelected(by));
    }

    /**
     * @param element
     */
    public void waitForElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * @param by
     */
    public void waitForElementVisible(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * @param element
     */
    public void waitForElementInVisible(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * @param by
     */
    public void waitForElementInVisible(By by) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    /**
     * @param element
     */
    public void waitForFrameAvailableToSwitch(WebElement element) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    /**
     * @param by
     */
    public void waitForFrameAvailableToSwitch(By by) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
    }

    /**
     *
     */
    public void waitForAlertIsPresent() {
        wait.until(ExpectedConditions.alertIsPresent());
    }

    /**
     * @param element
     * @param attr
     * @param attrValue
     */
    public void waitForAttribute(WebElement element, String attr, String attrValue) {
        wait.until(ExpectedConditions.attributeToBe(element, attr, attrValue));
    }

    /**
     * @param by
     * @param attr
     * @param attrValue
     */
    public void waitForAttribute(By by, String attr, String attrValue) {
        wait.until(ExpectedConditions.attributeToBe(by, attr, attrValue));
    }

    /**
     * @param element
     * @param attr
     * @param attrValue
     */
    public void waitForAttributeContains(WebElement element, String attr, String attrValue) {
        wait.until(ExpectedConditions.attributeContains(element, attr, attrValue));
    }

    /**
     * @param by
     * @param attr
     * @param attrValue
     */
    public void waitForAttributeContains(By by, String attr, String attrValue) {
        wait.until(ExpectedConditions.attributeContains(by, attr, attrValue));
    }

    /**
     * @param element
     * @param attr
     */
    public void waitForAttributeToBeNotEmpty(WebElement element, String attr) {
        wait.until(ExpectedConditions.attributeToBeNotEmpty(element, attr));
    }

    /**
     * @param javaScript
     */
    public void waitForJavaScriptThrowsNoExceptions(String javaScript) {
        wait.until(ExpectedConditions.javaScriptThrowsNoExceptions(javaScript));
    }

    /**
     * @param javaScript
     */
    public void waitForJsReturnsValue(String javaScript) {
        wait.until(ExpectedConditions.jsReturnsValue(javaScript));
    }

    /**
     * @param elements
     */
    public void waitForElementsInVisible(List<WebElement> elements) {
        wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
    }

    /**
     * @param by
     * @param number
     */
    public void waitForElementsNumberOf(By by, int number) {
        wait.until(ExpectedConditions.numberOfElementsToBe(by, number));
    }


    /**
     * @param by
     * @param number
     */
    public void waitForElementsNumberOfLessThan(By by, int number) {
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(by, number));
    }

    /**
     * @param by
     * @param number
     */
    public void waitForElementsNumberOfMoreThan(By by, int number) {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, number));
    }

    /**
     * @param element
     */
    public void waitElementToBeSelected(WebElement element) {
        wait.ignoring(StaleElementReferenceException.class, NoSuchElementException.class)
                .until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        Select select = new Select(element);
                        select.getAllSelectedOptions().isEmpty();
                        return select.getAllSelectedOptions().size() != 0;
                    }
                });
    }

    /**
     * @param element
     */
    public void waitTextToBePresent(WebElement element) {
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return element.getAttribute("value").length() != 0;
            }
        });
    }

    /**
     * jQuery is ready
     */
    public void waitForJQueryLoad() {
        JavaScriptActions jsActions = new JavaScriptActions(driver);
        ExpectedCondition<Boolean> jQueryLoad = driver -> (( Long ) (jsActions.getJSExecutor())
                .executeScript("return jQuery.active") == 0);
        boolean jqueryReady = ( Boolean ) jsActions.executeJS("return jQuery.active==0");
        if (!jqueryReady) {
            System.out.println("JQuery is NOT Ready!");
            wait.until(jQueryLoad);
        } else {
            System.out.println("JQuery is Ready!");
        }
    }

    /**
     *
     */
    public void waitForAngularLoad() {
        JavaScriptActions jsActions = new JavaScriptActions(driver);
        String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";
        ExpectedCondition<Boolean> angularLoad = driver -> Boolean.valueOf((( JavascriptExecutor ) driver)
                .executeScript(angularReadyScript).toString());
        boolean angularReady = Boolean.valueOf(jsActions.executeJS(angularReadyScript).toString());
        if (!angularReady) {
            System.out.println("ANGULAR is NOT Ready!");
            wait.until(angularLoad);
        } else {
            System.out.println("ANGULAR is Ready!");
        }
    }

    /**
     * Javascript is ready
     */
    public void waitUntilJSReady() {
        JavaScriptActions jsActions = new JavaScriptActions(driver);
        ExpectedCondition<Boolean> jsLoad = driver -> (jsActions.getJSExecutor())
                .executeScript("return document.readyState").toString().equals("complete");
        boolean jsReady = ( Boolean ) jsActions.executeJS("return document.readyState").toString().equals("complete");
        if (!jsReady) {
            System.out.println("JS in NOT Ready!");
            wait.until(jsLoad);
        } else {
            System.out.println("JS is Ready!");
        }
    }

    /**
     * jQuery is ready
     */
    public void waitUntilJQueryReady() {
        JavaScriptActions jsActions = new JavaScriptActions(driver);
        Boolean jQueryDefined = ( Boolean ) jsActions.executeJS("return typeof jQuery != 'undefined'");
        if (jQueryDefined == true) {
            waitForJQueryLoad();
            waitUntilJSReady();
        } else {
            System.out.println("jQuery is not defined on this site!");
        }
    }

    /**
     * Angular is ready
     */
    public void waitUntilAngularReady() {
        JavaScriptActions jsActions = new JavaScriptActions(driver);
        Boolean angularUnDefined = ( Boolean ) jsActions.executeJS("return window.angular === undefined");
        if (!angularUnDefined) {
            Boolean angularInjectorUnDefined = ( Boolean ) jsActions.executeJS("return angular.element(document).injector() === undefined");
            if (!angularInjectorUnDefined) {
                waitForAngularLoad();
            } else {
                System.out.println("Angular injector is not defined on this site!");
            }
        } else {
            System.out.println("Angular is not defined on this site!");
        }
    }

    /**
     * jQuery and Angular is ready
     */
    public void waitJQueryAngular() {
        waitUntilJQueryReady();
        waitUntilAngularReady();
    }

}

