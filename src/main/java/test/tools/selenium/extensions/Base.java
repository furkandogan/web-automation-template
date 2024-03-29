package test.tools.selenium.extensions;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.tools.selenium.interactions.*;

import java.time.Duration;

public class Base {

    public WebDriver driver;
    public WebDriverWait wait;
    public ExtentTest extentTest;
    public JavascriptExecutor js;
    public SimpleActions simpleActions;
    public ActionsAPI actionsAPI;
    public WaitingActions waitingActions;
    public ClickActions clickActions;
    public SendKeysActions sendKeysActions;
    public SelectActions selectActions;
    public GetElementProperties getElementProperties;
    public IsActions is;
    public ExpectedConditionHandle handle;
    public StoreElementProperties storeElementProperties;
    public BiDiAPI biDiAPI;

    public Base(WebDriver driver, ExtentTest extentTest) {
        this.driver = driver;
        this.extentTest = extentTest;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.js = (JavascriptExecutor) driver;
        this.simpleActions = new SimpleActions(driver, wait, extentTest);
        this.actionsAPI = new ActionsAPI(driver, wait, extentTest);
        this.waitingActions = new WaitingActions(driver, wait, extentTest);
        this.getElementProperties = new GetElementProperties(driver, wait, extentTest);
        this.is = new IsActions(driver, wait, extentTest);
        this.clickActions = new ClickActions(driver, wait, extentTest);
        this.sendKeysActions = new SendKeysActions(driver, wait, extentTest);
        this.selectActions = new SelectActions(driver, wait, extentTest);
        this.handle = new ExpectedConditionHandle(driver, wait, extentTest);
        this.storeElementProperties = new StoreElementProperties(driver, wait, extentTest);
        this.biDiAPI = new BiDiAPI(driver, wait, extentTest);
        PageFactory.initElements(driver, this);
    }

}
