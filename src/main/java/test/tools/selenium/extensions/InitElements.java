package test.tools.selenium.extensions;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.tools.selenium.interactions.*;

import java.time.Duration;

public class InitElements {

    public WebDriver driver;
    public ExtentTest extentTest;
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

    public InitElements(WebDriver driver, ExtentTest extentTest) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.waitingActions = new WaitingActions(driver, wait,extentTest);
        this.clickActions = new ClickActions(driver, wait,extentTest);
        this.sendKeysActions = new SendKeysActions(driver, wait,extentTest);
        this.selectActions = new SelectActions(driver, wait,extentTest);
        this.actionsAPI = new ActionsAPI(driver, wait,extentTest);
        this.getElementProperties = new GetElementProperties(driver, wait,extentTest);
        this.is = new IsActions(driver, wait,extentTest);
        this.simpleActions = new SimpleActions(driver,wait,extentTest);
        this.extentTest = extentTest;
    }

    public <TPage> TPage getPage(Class<TPage> pageClass) {
        return PageFactory.initElements(driver, pageClass);
    }

}
