package test.tools.selenium.interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static test.tools.selenium.constants.XpathInjection.mapValue;

public class FindActions extends WaitingActions {

    private WebElement element = null;
    private List<WebElement> elements = null;

    public FindActions(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement getElement() {
        return element;
    }

    public void setElement(WebElement element) {
        this.element = element;
    }

    public List<WebElement> getElements() {
        return elements;
    }

    public void setElements(List<WebElement> elements) {
        this.elements = elements;
    }

    /**
     * Highlight element with red color
     *
     * @param element
     */
    public void highlightElement(WebElement element) {
        if (element != null) {
            JavaScriptActions jsActions = new JavaScriptActions(driver);
            jsActions.executeJS("arguments[0].setAttribute('style', arguments[1]);", element,
                    "color: red; border: 3px solid red;");
        }
    }

    /**
     * Finding Element
     *
     * @param by
     * @return
     */
    public WebElement findElement(By by) {
        try {
            if(mapValue.getMoreThanOne() != null && mapValue.getMoreThanOne() == true){
                setElements(findElements(by));
            }
            if (mapValue.getIndex() != null) {
                setElements(waitForElementsPresence(by));
                setElement(getElements().get(mapValue.getIndex()));
            } else {
                setElement(waitForElementPresence(by));
            }
            ScrollingActions scroll = new ScrollingActions(driver);
            scroll.scrollToElement(element);
            if (mapValue.getIsJsEnabled() != null && mapValue.getIsJsEnabled() == true) {
                waitForElementInVisible(by);
            } else {
                waitForElementVisible(by);
            }
        } catch (Exception e) {
            highlightElement(element);
        }
        return getElement();
    }

    /**
     * Finding List Element
     *
     * @param by
     * @return
     */
    protected List<WebElement> findElements(By by) {
        elements = waitForElementsPresence(by);
        setElements(elements);
        return getElements();
    }

}
