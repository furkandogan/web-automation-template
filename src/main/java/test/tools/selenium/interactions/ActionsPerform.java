package test.tools.selenium.interactions;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionsPerform {

    public WebDriver driver;
    public WebDriverWait wait;

    public ActionsPerform(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /**
     * Move To Element
     *
     * @param element
     */
    public void moveToElement(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    /**
     * Move To Element
     *
     * @param element
     */
    public void moveAndClickToElement(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).click().perform();
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

    /**
     * Sendkeys input element by action perform
     *
     * @param element
     * @param value
     *
     */
    public void sendKeysByActions(WebElement element, String value) {
        String browser = System.getProperty("browser");
        if (!StringUtils.isEmpty(browser) && browser.contains("safari")) {
            ActionsPerform actionsPerform = new ActionsPerform(driver, wait);
            actionsPerform.moveToElement(element);
            ClickActions clickActions = new ClickActions(driver, wait);
            clickActions.clickByJs(element);
            element.sendKeys(value);
        } else {
            Actions actions = new Actions(driver);
            actions.moveToElement(element);
            actions.click();
            actions.sendKeys(value);
            actions.build().perform();
        }
    }

}
