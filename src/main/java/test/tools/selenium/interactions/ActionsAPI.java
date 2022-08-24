package test.tools.selenium.interactions;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;

public class ActionsAPI {

    public WebDriver driver;
    public WebDriverWait wait;

    public ActionsAPI(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /**
     * This is the most common scenario. Unlike traditional click and send keys methods,
     * the actions class does not automatically scroll the target element into view,
     * so this method will need to be used if elements are not already inside the viewport.
     * This method takes a web element as the sole argument.
     * Regardless of whether the element is above or below the current viewscreen,
     * the viewport will be scrolled so the bottom of the element is at the bottom of the screen.
     *
     * @param element
     */
    public void scrollToVisibleElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        new Actions(driver)
                .scrollToElement(element)
                .perform();
    }

    /**
     * @param xpath
     */
    public WebElement scrollToVisibleElement(By xpath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(xpath));
        WebElement element = driver.findElement(xpath);
        new Actions(driver)
                .scrollToElement(element)
                .perform();
        return element;
    }

    /**
     * @param element
     */
    public void scrollToInvisibleElement(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
        new Actions(driver)
                .scrollToElement(element)
                .perform();
    }

    /**
     * @param xpath
     */
    public WebElement scrollToInvisibleElement(By xpath) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(xpath));
        WebElement element = driver.findElement(xpath);
        new Actions(driver)
                .scrollToElement(element)
                .perform();
        return element;
    }

    /**
     * This is the second most common scenario for scrolling.
     * Pass in an delta x and a delta y value for how much to scroll in the right and down directions.
     * Negative values represent left and up, respectively.
     *
     * @param element
     * @param deltaX
     */
    public void scrollByGivenAmount(WebElement element, int deltaX) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        int deltaY = element.getRect().y;
        new Actions(driver)
                .scrollByAmount(deltaX, deltaY)
                .perform();
    }

    /**
     * This scenario is effectively a combination of the above two methods.
     * To execute this use the “Scroll From” method, which takes 3 arguments.
     * The first represents the origination point, which we designate as the element
     * and the second two are the delta x and delta y values.
     * If the element is out of the viewport, it will be scrolled to the bottom of the screen,
     * then the page will be scrolled by the provided delta x and delta y values.
     *
     * @param element
     * @param deltaX
     * @param deltaY
     */
    public void scrollFromAnElementByAGivenAmount(WebElement element, int deltaX, int deltaY) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(element);
        new Actions(driver)
                .scrollFromOrigin(scrollOrigin, deltaX, deltaY)
                .perform();
    }

    /**
     * This scenario is used when you need to scroll only a portion of the screen, and it is outside the viewport.
     * Or is inside the viewport and the portion of the screen that must be scrolled is a known offset away from a specific element.
     * This uses the “Scroll From” method again, and in addition to specifying the element,
     * an offset is specified to indicate the origin point of the scroll. The offset is calculated from the center of the provided element.
     * If the element is out of the viewport, it first will be scrolled to the bottom of the screen,
     * then the origin of the scroll will be determined by adding the offset to the coordinates of the center of the element,
     * and finally the page will be scrolled by the provided delta x and delta y values.
     * Note that if the offset from the center of the element falls outside of the viewport, it will result in an exception.
     *
     * @param element
     * @param xOffset
     * @param yOffset
     * @param deltaX
     * @param deltaY
     */
    public void scrollFromAnElementWithAnOffset(WebElement element, int xOffset, int yOffset, int deltaX, int deltaY) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(element, xOffset, yOffset);
        new Actions(driver)
                .scrollFromOrigin(scrollOrigin, deltaX, deltaY)
                .perform();
    }

    /**
     * The final scenario is used when you need to scroll only a portion of the screen, and it is already inside the viewport.
     * This uses the “Scroll From” method again, but the viewport is designated instead of an element.
     * An offset is specified from the upper left corner of the current viewport. After the origin point is determined,
     * the page will be scrolled by the provided delta x and delta y values.
     * Note that if the offset from the upper left corner of the viewport falls outside of the screen, it will result in an exception.
     */
    public void scrollFromAnOffsetOfOriginByGivenAmount(int xOffset, int yOffset) {
        WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromViewport(xOffset, yOffset);
        new Actions(driver)
                .scrollFromOrigin(scrollOrigin, 0, 200)
                .perform();
    }

    /**
     * This method combines moving the mouse to the center of an element with pressing the left mouse button.
     * This is useful for focusing a specific element
     *
     * @param element
     */
    public void clickAndHold(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        new Actions(driver)
                .clickAndHold(element)
                .perform();
    }

    /**
     * @param xpath
     */
    public void clickAndHold(By xpath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(xpath));
        WebElement element = driver.findElement(xpath);
        new Actions(driver)
                .clickAndHold(element)
                .perform();
    }

    /**
     * This method combines moving to the center of an element with pressing and releasing the left mouse button.
     * This is otherwise known as “clicking”
     *
     * @param element
     */
    public void clickAndRelease(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        new Actions(driver)
                .click(element)
                .perform();
    }

    /**
     * @param xpath
     */
    public void clickAndRelease(By xpath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(xpath));
        WebElement element = driver.findElement(xpath);
        new Actions(driver)
                .click(element)
                .perform();
    }

    /**
     * This method combines moving to the center of an element with pressing and releasing the right mouse button (button 2).
     * This is otherwise known as “right-clicking”
     *
     * @param element
     */
    public void contextClick(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        new Actions(driver)
                .contextClick(element)
                .perform();
    }

    /**
     * @param xpath
     */
    public void contextClick(By xpath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(xpath));
        WebElement element = driver.findElement(xpath);
        new Actions(driver)
                .contextClick(element)
                .perform();
    }

    /**
     * There is no convenience method for this, it is just pressing and releasing mouse button 3
     */
    public void backClick() {
        PointerInput mouse = new PointerInput(PointerInput.Kind.MOUSE, "default mouse");

        Sequence actions = new Sequence(mouse, 0)
                .addAction(mouse.createPointerDown(PointerInput.MouseButton.BACK.asArg()))
                .addAction(mouse.createPointerUp(PointerInput.MouseButton.BACK.asArg()));

        ((RemoteWebDriver) driver).perform(Collections.singletonList(actions));
    }

    /**
     * There is no convenience method for this, it is just pressing and releasing mouse button 4
     */
    public void forwardClick() {
        PointerInput mouse = new PointerInput(PointerInput.Kind.MOUSE, "default mouse");

        Sequence actions = new Sequence(mouse, 0)
                .addAction(mouse.createPointerDown(PointerInput.MouseButton.FORWARD.asArg()))
                .addAction(mouse.createPointerUp(PointerInput.MouseButton.FORWARD.asArg()));

        ((RemoteWebDriver) driver).perform(Collections.singletonList(actions));
    }

    /**
     * This method combines moving to the center of an element with pressing and releasing the left mouse button twice.
     *
     * @param element
     */
    public void doubleClick(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        new Actions(driver)
                .doubleClick(element)
                .perform();
    }

    /**
     * @param xpath
     */
    public void doubleClick(By xpath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(xpath));
        WebElement element = driver.findElement(xpath);
        new Actions(driver)
                .doubleClick(element)
                .perform();
    }

    /**
     * This method moves the mouse to the in-view center point of the element.
     * This is otherwise known as “hovering.”
     * Note that the element must be in the viewport or else the command will error.
     *
     * @param element
     */
    public void moveToElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        new Actions(driver)
                .moveToElement(element)
                .perform();
    }

    /**
     * @param xpath
     */
    public void moveToElement(By xpath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(xpath));
        WebElement element = driver.findElement(xpath);
        new Actions(driver)
                .moveToElement(element)
                .perform();
    }

    /**
     * This method moves to the in-view center point of the element, then moves the mouse by the provided offset
     * This is the default behavior in Java as of Selenium 4.0,
     * and will be the default for the remaining languages as of Selenium 4.3.
     *
     * @param element
     * @param xOffset
     * @param yOffset
     */
    public void moveByOffset(WebElement element, int xOffset, int yOffset) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        new Actions(driver)
                .moveToElement(element, xOffset, yOffset)
                .perform();
    }

    /**
     * @param xpath
     */
    public void moveByOffset(By xpath, int xOffset, int yOffset) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(xpath));
        WebElement element = driver.findElement(xpath);
        new Actions(driver)
                .moveToElement(element, xOffset, yOffset)
                .perform();
    }

    /**
     * This method moves the mouse from the upper left corner of the current viewport by the provided offset.
     *
     * @param xOffset
     * @param yOffset
     */
    public void moveByOffsetFromViewport(int xOffset, int yOffset) {
        PointerInput mouse = new PointerInput(PointerInput.Kind.MOUSE, "default mouse");

        Sequence actions = new Sequence(mouse, 0)
                .addAction(mouse.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), xOffset, yOffset));

        ((RemoteWebDriver) driver).perform(Collections.singletonList(actions));
    }

    /**
     * This method moves the mouse from its current position by the offset provided by the user.
     * If the mouse has not previously been moved, the position will be in the upper left corner of the viewport.
     * Note that the pointer position does not change when the page is scrolled.
     * Note that the first argument X specifies to move right when positive,
     * while the second argument Y specifies to move down when positive.
     * So moveByOffset(30, -10) moves right 30 and up 10 from the current mouse position.
     *
     * @param xOffset
     * @param yOffset
     */
    public void moveByOffsetFromCurrentPointerLocation(int xOffset, int yOffset) {
        new Actions(driver)
                .moveByOffset(xOffset, yOffset)
                .perform();
    }

    /**
     * This method firstly performs a click-and-hold on the source element,
     * moves to the location of the target element and then releases the mouse.
     *
     * @param draggable
     * @param droppable
     */
    public void dragAndDropOnElement(WebElement draggable, WebElement droppable) {
        wait.until(ExpectedConditions.visibilityOf(draggable));
        wait.until(ExpectedConditions.elementToBeClickable(draggable));
        wait.until(ExpectedConditions.visibilityOf(droppable));
        wait.until(ExpectedConditions.elementToBeClickable(droppable));
        new Actions(driver)
                .dragAndDrop(draggable, droppable)
                .perform();
    }

    /**
     * This method firstly performs a click-and-hold on the source element,
     * moves to the given offset and then releases the mouse
     *
     * @param draggable
     * @param droppable
     */
    public void dragElement(WebElement draggable, WebElement droppable) {
        wait.until(ExpectedConditions.visibilityOf(draggable));
        wait.until(ExpectedConditions.elementToBeClickable(draggable));
        wait.until(ExpectedConditions.visibilityOf(droppable));
        wait.until(ExpectedConditions.elementToBeClickable(droppable));
        Rectangle start = draggable.getRect();
        Rectangle finish = droppable.getRect();
        new Actions(driver)
                .dragAndDropBy(draggable, finish.getX() - start.getX(), finish.getY() - start.getY())
                .perform();
    }

    /**
     * This is a convenience method in the Actions API that combines keyDown and keyUp commands in one action.
     * Executing this command differs slightly from using the element method,
     * but primarily this gets used when needing to type multiple characters in the middle of other actions.
     *
     * @param value
     */
    public void sendKeys(String value) {
        new Actions(driver)
                .sendKeys(value)
                .perform();
    }

    /**
     * This is a convenience method in the Actions API that combines keyDown and keyUp commands in one action.
     * Executing this command differs slightly from using the element method,
     * but primarily this gets used when needing to type multiple characters in the middle of other actions.
     *
     * @param element
     * @param value
     */
    public void sendKeys(WebElement element, String value) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        new Actions(driver)
                .sendKeys(element, value)
                .perform();
    }

    /**
     * @param xpath
     * @param value
     */
    public void sendKeys(By xpath, String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(xpath));
        WebElement element = driver.findElement(xpath);
        new Actions(driver)
                .sendKeys(element, value)
                .perform();
    }

    /**
     * Pointer movements and Wheel scrolling allow the user to set a duration for the action,
     * but sometimes you just need to wait a beat between actions for things to work correctly.
     *
     * @param element
     * @param value
     */
    public void sendKeysWithPause(WebElement element, String value) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        new Actions(driver)
                .moveToElement(element)
                .pause(Duration.ofSeconds(1))
                .clickAndHold()
                .pause(Duration.ofSeconds(1))
                .sendKeys(value)
                .perform();
    }

    /**
     * @param xpath
     * @param value
     */
    public void sendKeysWithPause(By xpath, String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(xpath));
        WebElement element = driver.findElement(xpath);
        new Actions(driver)
                .moveToElement(element)
                .pause(Duration.ofSeconds(1))
                .clickAndHold()
                .pause(Duration.ofSeconds(1))
                .sendKeys(value)
                .perform();
    }

    /**
     * Here’s an example of using all of the above methods to conduct a copy / paste action.
     * Note that the key to use for this operation will be different depending on if it is a Mac OS or not.
     *
     * @param element
     * @param value
     */
    public void copyAndPaste(WebElement element, String value) {
        Platform platformName = ((HasCapabilities) driver).getCapabilities().getPlatformName();
        Keys cmdCtrl = platformName.is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;

        new Actions(driver)
                .sendKeys(element, value)
                .sendKeys(Keys.ARROW_LEFT)
                .keyDown(Keys.SHIFT)
                .sendKeys(Keys.ARROW_UP)
                .keyUp(Keys.SHIFT)
                .keyDown(cmdCtrl)
                .sendKeys("xvv")
                .keyUp(cmdCtrl)
                .perform();
    }

    /**
     * @param xpath
     * @param value
     */
    public void copyAndPaste(By xpath, String value) {
        Platform platformName = ((HasCapabilities) driver).getCapabilities().getPlatformName();
        Keys cmdCtrl = platformName.is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(xpath));
        WebElement element = driver.findElement(xpath);
        new Actions(driver)
                .sendKeys(element, value)
                .sendKeys(Keys.ARROW_LEFT)
                .keyDown(Keys.SHIFT)
                .sendKeys(Keys.ARROW_UP)
                .keyUp(Keys.SHIFT)
                .keyDown(cmdCtrl)
                .sendKeys("xvv")
                .keyUp(cmdCtrl)
                .perform();
    }

    /**
     * In addition to the keys represented by regular unicode,
     * unicode values have been assigned to other keyboard keys for use with Selenium.
     * Each language has its own way to reference these keys;the full list can be found https://www.w3.org/TR/webdriver/#keyboard-actions.
     */
    public void keyDown(){
        new Actions(driver)
                .keyDown(Keys.SHIFT)
                .perform();
    }

    public void keyUp(){
        new Actions(driver)
                .keyUp(Keys.SHIFT)
                .perform();
    }

}
