package test.tools.selenium.interactions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScrollingActions extends WaitingActions {

    public ScrollingActions(WebDriver driver) {
        super(driver);
    }

    /**
     * scrollTo WebElement location
     *
     * @param x
     * @param y
     */
    public void scrollTo(int x, int y) {
        String jsStmt = String.format("window.scrollTo(%d, %d);", x, y);
        JavaScriptActions jsActions = new JavaScriptActions(driver);
        jsActions.executeJS(jsStmt, true);
    }

    /**
     * scrollTo WebElement scope location
     *
     * @param element
     */
    public void scrollToElementLocation(WebElement element) {
        if (element != null) {
            scrollTo(element.getLocation().getX(), element.getLocation().getY());
        }
    }

    /**
     * scrollTo WebElement scrollIntoView
     *
     * @param scroll
     */
    public void scrollToElement(WebElement scroll) {
        JavaScriptActions jsActions = new JavaScriptActions(driver);
        jsActions.executeJS("arguments[0].scrollIntoView(false);", scroll);
    }

    /**
     * scrollTo WebElement scope location +x +y
     *
     * @param element
     * @param x
     * @param y
     */
    public void scrollToElement(WebElement element, int x, int y) {
        if (element != null) {
            scrollTo(element.getLocation().getX() + x, element.getLocation().getY() + y);
        }
    }

    /**
     * scrollTo Page End
     */
    public void scrollToPageEnd() {
        JavaScriptActions jsActions = new JavaScriptActions(driver);
        jsActions.executeJS("window.scrollTo(0, document.body.scrollHeight)", true);
    }

    /**
     * scrollTo Page Up
     */
    public void scrollToPageUp() {
        JavaScriptActions jsActions = new JavaScriptActions(driver);
        jsActions.executeJS("window.scrollTo(document.body.scrollHeight, 0)", true);
    }


}
