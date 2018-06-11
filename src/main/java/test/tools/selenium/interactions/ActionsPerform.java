package test.tools.selenium.interactions;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.tools.selenium.mapping.MapMethodType;

import static test.tools.selenium.constants.XpathInjection.createXpath;

public class ActionsPerform extends FindActions {

    MapMethodType mapMethodType = MapMethodType.DRAG_OBJECT;

    public ActionsPerform(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    /**
     * Move To Element
     *
     * @param attr
     */
    public void moveToElement(String attr) {
        WebElement element = findElement(createXpath(attr, mapMethodType));
        String browser = System.getProperty("browser");
        System.out.println("Browser = " + browser);
        if (browser.contains("safari")) {
            String strJavaScript = "var element = arguments[0];"
                    + "var mouseEventObj = document.createEvent('MouseEvents');"
                    + "mouseEventObj.initEvent( 'mouseover', true, true );" + "element.dispatchEvent(mouseEventObj);";
            JavaScriptActions jsActions = new JavaScriptActions(driver);
            jsActions.executeJS(strJavaScript, element);
        } else {
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();
        }
    }

    /**
     * @param attr
     * @param count
     * @throws InterruptedException
     */
    public void moveMouse(String attr, int count) throws InterruptedException {
        int i = 0;
        PresenceOfQualification presence = new PresenceOfQualification(driver, wait);
        do {
            WebElement element = findElement(createXpath(attr, mapMethodType));
            org.openqa.selenium.interactions.internal.Locatable hoverItem = ( org.openqa.selenium.interactions.internal.Locatable ) element;
            Mouse mouse = (( HasInputDevices ) driver).getMouse();
            mouse.mouseMove(hoverItem.getCoordinates());
            Thread.sleep(3000);
            if (count == i++)
                break;
        } while (!presence.isElementDisplayed(attr));
    }

    /**
     * @param attr
     * @param inputValue
     */
    public void sendKeysElementByActions(String attr, String inputValue) {
        String browser = System.getProperty("browser");
        WebElement element = findElement(createXpath(attr, mapMethodType));
        if (!StringUtils.isEmpty(browser) && browser.contains("safari")) {
            moveToElement(attr);
            ClickActions clickActions = new ClickActions(driver, wait);
            clickActions.javaScriptClicker(element);
            element.sendKeys(inputValue);
        } else {
            Actions actions = new Actions(driver);
            actions.moveToElement(element);
            actions.click();
            actions.sendKeys(inputValue);
            actions.build().perform();
        }
    }

    /**
     * Drag and Drop Element
     *
     * @param attr
     * @param xOffset
     * @param yOffset
     * @throws InterruptedException
     */
    public void dragElement(String attr, int xOffset, int yOffset) throws InterruptedException {
        WebElement element = findElement(createXpath(attr, mapMethodType));
        Actions a = new Actions(driver);
        a.dragAndDropBy(element, xOffset, yOffset).build().perform();
    }


    /**
     * Drag and Drop
     *
     * @param attr
     */
    public void dragAndDrop(String attr) {
        WebElement element = findElement(createXpath(attr, mapMethodType));
        int width = element.getSize().getWidth();
        Actions builder = new Actions(driver);
        builder.moveToElement(element)
                .clickAndHold(element)
                .dragAndDropBy(element, ((width * 25) / 100), 0)
                .build()
                .perform();
    }
}
