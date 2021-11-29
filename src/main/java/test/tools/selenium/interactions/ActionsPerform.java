package test.tools.selenium.interactions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionsPerform extends WaitingActions{

    public WebDriver driver;
    public WebDriverWait wait;

    public ActionsPerform(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    /**
     * Move To Element
     *
     * @param element
     */
    public void moveToElement(WebElement element) {
        String browser = System.getProperty("browser");
        if (browser.contains("safari")) {
            String strJavaScript = "var element = arguments[0];"
                    + "var mouseEventObj = document.createEvent('MouseEvents');"
                    + "mouseEventObj.initEvent( 'mouseover', true, true );" + "element.dispatchEvent(mouseEventObj);";
            ((JavascriptExecutor) driver).executeScript(strJavaScript, element);
        } else {
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();
        }
    }

    /**
     * Drag and Drop Element
     *
     * @param element
     * @param xOffset
     * @param yOffset
     */
    public void dragElement(WebElement element, int xOffset, int yOffset) {
        Actions a = new Actions(driver);
        a.dragAndDropBy(element, xOffset, yOffset).build().perform();
    }


    /**
     * Drag and Drop
     *
     * @param element
     */
    public void dragAndDrop(WebElement element) {
        int width = element.getSize().getWidth();
        Actions builder = new Actions(driver);
        builder.moveToElement(element)
                .clickAndHold(element)
                .dragAndDropBy(element, ((width * 25) / 100), 0)
                .build()
                .perform();
    }

}
