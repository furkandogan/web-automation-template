package test.tools.selenium;

import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.tools.selenium.config.PropertyNames;
import test.tools.selenium.instances.ConfigurationInstance;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public abstract class TestCaseFrame {
    private WebDriverManager webDriverManager = null;
    private WebDriver webDriver = null;
    private WebDriverWait wait = null;
    private int numberOfBrowser = 1;
    private String startPage = null;

    private boolean isBrowserInDocker = false;
    private String appiumHubUrl = null;
    private Duration timeOutInSeconds = Duration.ofSeconds(60);
    private Duration implicitlyWaitTimeOut = Duration.ofSeconds(60);


    public Duration getTimeOutInSeconds() {
        return timeOutInSeconds;
    }

    public void setTimeOutInSeconds(Duration timeOutInSeconds) {
        this.timeOutInSeconds = timeOutInSeconds;
    }

    public WebDriverManager getWebDriverManager() {
        return webDriverManager;
    }

    public void setWebDriverManager(WebDriverManager webDriverManager) {
        this.webDriverManager = webDriverManager;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(WebDriver newWebDriver) {
        this.webDriver = newWebDriver;
    }

    public int getNumberOfBrowser() {
        return numberOfBrowser;
    }

    public void setNumberOfBrowser(int numberOfBrowser) {
        this.numberOfBrowser = numberOfBrowser;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void setWait(WebDriverWait wait) {
        this.wait = wait;
    }

    public String getStartPage() {
        return startPage;
    }

    public void setStartPage(String startPage) {
        this.startPage = startPage;
    }

    public void setAppiumHubUrl(String appiumHubUrl) {
        this.appiumHubUrl = appiumHubUrl;
    }

    public String getAppiumHubUrl() {
        return appiumHubUrl;
    }

    public boolean isBrowserInDocker() {
        return isBrowserInDocker;
    }

    public void setBrowserInDocker(boolean browserInDocker) {
        isBrowserInDocker = browserInDocker;
    }

    public Duration getImplicitlyWaitTimeOut() {
        return implicitlyWaitTimeOut;
    }

    public void setImplicitlyWaitTimeOut(Duration implicitlyWaitTimeOut) {
        this.implicitlyWaitTimeOut = implicitlyWaitTimeOut;
    }


    public TestCaseFrame() {
        try {
            setStartPage(getConfigProperty(PropertyNames.BASE_URL));
            setAppiumHubUrl(getConfigProperty(PropertyNames.APPIUM_HUB_URL));
            setBrowserInDocker(Boolean.parseBoolean(getConfigProperty(PropertyNames.BROWSER_IN_DOCKER)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getConfigProperty(String key) throws Exception {

        return ConfigurationInstance.getInstance().getConfigProperty(key);
    }


    public WebDriverManager createWebDriverManager() throws Exception {
        switch (getConfigProperty(PropertyNames.BROWSER_TYPE)) {
            case "chrome":
                webDriverManager = WebDriverManager.chromedriver().capabilities(createChromeOptions());
                break;
            case "firefox":
                webDriverManager = WebDriverManager.firefoxdriver().capabilities(createFirefoxOptions());
                break;
            case "opera":
                webDriverManager = WebDriverManager.operadriver().capabilities(createOperaOptions());
                break;
            case "edge":
                webDriverManager = WebDriverManager.edgedriver().capabilities(createEdgeOptions());
                break;
            case "chromium":
                webDriverManager = WebDriverManager.chromiumdriver();
                break;
            case "safari":
                webDriverManager = WebDriverManager.safaridriver().capabilities(createSafariOptions());
                break;
            case "ie":
                webDriverManager = WebDriverManager.iedriver();
                break;
        }
        if (isBrowserInDocker()) {
            webDriverManager.browserInDocker();
        }
        webDriverManager.config().setProperties("wdm.properties");
        return webDriverManager;
    }

    private ChromiumOptions createChromiumOptions(String browserName, String browserCapabilityName) throws Exception {
        ChromiumOptions chromiumOptions = new ChromiumOptions("browserName", browserName, browserCapabilityName);
        chromiumOptions.addArguments("test-type");
        chromiumOptions.addArguments("disable-popup-blocking");
        chromiumOptions.addArguments("ignore-certificate-errors");
        chromiumOptions.addArguments("disable-translate");
        chromiumOptions.addArguments("start-maximized");
        if (Boolean.parseBoolean(getConfigProperty(PropertyNames.HEADLESS))) {
            chromiumOptions.addArguments("--headless=new");
        }
        chromiumOptions.setImplicitWaitTimeout(Duration.ofSeconds(Long.parseLong(getConfigProperty("browser.implicit.wait.timeOut"))));
        chromiumOptions.setScriptTimeout(Duration.ofSeconds(Long.parseLong(getConfigProperty("browser.set.script.timeOut"))));
        chromiumOptions.setPageLoadTimeout(Duration.ofSeconds(Long.parseLong(getConfigProperty("browser.page.load.timeOut"))));
        chromiumOptions.setPageLoadStrategy(PageLoadStrategy.fromString(getConfigProperty("page.load.strategy")));
        return chromiumOptions;
    }

    private ChromiumOptions createChromeOptions() throws Exception {
        return createChromiumOptions(Browser.CHROME.browserName(), "goog:chromeOptions");
    }

    private ChromiumOptions createFirefoxOptions() throws Exception {
        return createChromiumOptions(Browser.FIREFOX.browserName(), "moz:firefoxOptions");
    }

    private ChromiumOptions createEdgeOptions() throws Exception {
        return createChromiumOptions(Browser.EDGE.browserName(), "ms:edgeOptions");
    }

    private ChromiumOptions createOperaOptions() throws Exception {
        return createChromiumOptions(Browser.OPERA.browserName(), null);
    }

    private ChromiumOptions createSafariOptions() throws Exception {
        return createChromiumOptions(Browser.SAFARI.browserName(), null);
    }

    /**
     * create web driver based on default browser values
     *
     * @return
     * @throws Exception
     */
    public WebDriver createWebDriver() throws Exception {
        return this.createWebDriver(getNumberOfBrowser(), getStartPage());
    }

    /**
     * create web driver based on default browser values
     *
     * @param pageUrl
     * @return
     * @throws Exception
     */
    public WebDriver createWebDriver(String pageUrl) throws Exception {
        return this.createWebDriver(getNumberOfBrowser(), pageUrl);
    }

    public WebDriver createWebDriver(int numberOfBrowser) throws Exception {
        return this.createWebDriver(numberOfBrowser, getStartPage());
    }

    /**
     * @param pageUrl
     * @return
     * @throws Exception
     * @throws IOException
     */
    public WebDriver createWebDriver(int numberOfBrowser, String pageUrl) throws Exception, IOException {

        return this.createWebDriver(numberOfBrowser, pageUrl, 0);
    }

    public WebDriver createWebDriver(int numberOfBrowser, String pageUrl, long timeOutInSeconds) throws Exception, IOException {

        System.out.println(String.format("URL :*%s*", new Object[]{pageUrl}));

        setNumberOfBrowser(numberOfBrowser);

        //set start page
        setStartPage(pageUrl);

        //create web driver
        setWebDriver(webDriverManager.create());

        //set file detector
        if (isBrowserInDocker())
            setFileDetector();

        //set timeout
        createWebDriverWait();

        return getWebDriver();
    }

    private WebDriverWait createWebDriverWait() throws Exception {
        Duration tm = Duration.ofSeconds(Long.valueOf(this.getConfigProperty("browser.wait.timeout")));
        setTimeOutInSeconds(tm);
        WebDriverWait driverWait = new WebDriverWait(getWebDriver(), getTimeOutInSeconds());
        setWait(driverWait);

        return driverWait;
    }

    public void openStartPage() {
        getWebDriver().get(getStartPage());
    }

    public void setFileDetector() {
        ((RemoteWebDriver) getWebDriver()).setFileDetector(new LocalFileDetector());
    }

    public WebDriver createChromeAndroidDriver(String browserVersion, String deviceName) throws MalformedURLException {
        // Resolve driver and get its path
        WebDriverManager wdm = WebDriverManager.chromedriver()
                .browserVersion(browserVersion);
        wdm.setup();
        String chromedriverPath = wdm.getDownloadedDriverPath();

        // Create WebDriver instance using the driver path
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("version", browserVersion);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("chromedriverExecutable", chromedriverPath);

        setWebDriver(new AndroidDriver(new URL(getAppiumHubUrl()), capabilities));
        return getWebDriver();
    }

    public void cleanUpWebDriver(WebDriver driver) throws Exception {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

}
