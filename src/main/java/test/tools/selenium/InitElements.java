package test.tools.selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.tools.selenium.interactions.*;

import java.time.Duration;

public class InitElements {

    public WebDriver driver;
    public JavascriptExecutor js;
    public WebDriverWait wait;
    public WaitingActions waitingActions;
    public ClickActions clickActions;
    public SendKeysActions sendKeysActions;
    public SelectActions selectActions;
    public ActionsAPI actionsAPI;
    public GetElementProperties getElementProperties;
    public IsActions is;
    public SimpleActions simpleActions;

    public InitElements(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.waitingActions = new WaitingActions(driver, wait);
        this.clickActions = new ClickActions(driver, wait);
        this.sendKeysActions = new SendKeysActions(driver, wait);
        this.selectActions = new SelectActions(driver, wait);
        this.actionsAPI = new ActionsAPI(driver, wait);
        this.getElementProperties = new GetElementProperties(driver, wait);
        this.is = new IsActions(driver, wait);
        this.simpleActions = new SimpleActions(driver,wait);
    }

    public <TPage> TPage getPage(Class<TPage> pageClass) {
        return PageFactory.initElements(driver, pageClass);
    }

}
