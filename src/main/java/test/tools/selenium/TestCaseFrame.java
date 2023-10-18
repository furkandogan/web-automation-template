package test.tools.selenium;

import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.tools.selenium.config.PropertyNames;
import test.tools.selenium.instances.ConfigurationInstance;
import test.tools.selenium.util.Browser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import static io.github.bonigarcia.wdm.config.DriverManagerType.*;

public abstract class TestCaseFrame {
    private WebDriverManager webDriverManager = null;
    private DriverManagerType driverManagerType = null;

    private String operatingSystem = null;
    private Capabilities capabilities = null;

    private List<WebDriver> webDrivers = null;
    private WebDriver webDriver = null;
    private WebDriverWait wait = null;
    private Browser browser = null;
    private int numberOfBrowser = 1;
    private String startPage = null;
    private boolean browserInDocker = false;
    private boolean customWdmProperties = false;
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

    public DriverManagerType getDriverManagerType() {
        return driverManagerType;
    }

    public void setDriverManagerType(DriverManagerType driverManagerType) {
        this.driverManagerType = driverManagerType;
    }

    public Capabilities getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Capabilities capabilities) {
        this.capabilities = capabilities;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(WebDriver newWebDriver) {
        this.webDriver = newWebDriver;
    }

    public List<WebDriver> getWebDrivers() {
        return webDrivers;
    }

    public void setWebDrivers(List<WebDriver> webDrivers) {
        this.webDrivers = webDrivers;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
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
        return browserInDocker;
    }

    public void setBrowserInDocker(boolean browserInDocker) {
        this.browserInDocker = browserInDocker;
    }

    public Duration getImplicitlyWaitTimeOut() {
        return implicitlyWaitTimeOut;
    }

    public void setImplicitlyWaitTimeOut(Duration implicitlyWaitTimeOut) {
        this.implicitlyWaitTimeOut = implicitlyWaitTimeOut;
    }

    public boolean isCustomWdmProperties() {
        return customWdmProperties;
    }

    public void setCustomWdmProperties(boolean customWdmProperties) {
        this.customWdmProperties = customWdmProperties;
    }


    public TestCaseFrame() {
        try {
            setOperatingSystem(getConfigProperty(PropertyNames.OPERATING_SYSTEM));
            setStartPage(getConfigProperty(PropertyNames.BASE_URL));
            setAppiumHubUrl(getConfigProperty(PropertyNames.APPIUM_HUB_URL));
            setBrowserInDocker(Boolean.parseBoolean(getConfigProperty(PropertyNames.BROWSER_IN_DOCKER)));
            setCustomWdmProperties(Boolean.parseBoolean(getConfigProperty(PropertyNames.CUSTOMER_WDM_PROPERTIES)));
            setNumberOfBrowser(Integer.parseInt(getConfigProperty(PropertyNames.NUMBER_OF_BROWSER)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getConfigProperty(String key) throws Exception {

        return ConfigurationInstance.getInstance().getConfigProperty(key);
    }

    public WebDriverManager createWebDriverManager() throws Exception {
        WebDriverManager driverManager;
        createBrowserFromConfiguration();
        if (isBrowserInDocker()) {
            driverManager = WebDriverManager.getInstance(getDriverManagerType()).operatingSystem(OperatingSystem.valueOf(getOperatingSystem())).capabilities(getCapabilities()).browserInDocker();
            driverManager.config().setProperties("config/wdm-docker.properties");
        } else {
            driverManager = WebDriverManager.getInstance(getDriverManagerType()).operatingSystem(OperatingSystem.valueOf(getOperatingSystem())).capabilities(getCapabilities());
        }
        if (isCustomWdmProperties()) {
            driverManager.config().setProperties("config/custom-wdm.properties");
        }
        setWebDriverManager(driverManager);
        return getWebDriverManager();
    }

    private void createBrowserFromConfiguration() throws Exception {
        switch (getConfigProperty(PropertyNames.BROWSER_TYPE)) {
            case "chrome":
                setDriverManagerType(CHROME);
                setCapabilities(createChromeOptions());
                break;
            case "firefox":
                setDriverManagerType(FIREFOX);
                setCapabilities(createFirefoxOptions());
                break;
            case "edge":
                setDriverManagerType(EDGE);
                setCapabilities(createEdgeOptions());
                break;
            case "opera":
                setDriverManagerType(OPERA);
                break;
            case "chromium":
                setDriverManagerType(CHROMIUM);
                break;
            case "safari":
                setDriverManagerType(SAFARI);
                break;
            case "ie":
                setDriverManagerType(IEXPLORER);
                break;
        }
    }

    private void updateBrowserCapsFromConfig(DesiredCapabilities capabilities) {

        if (browser.getName() != null) {
            capabilities.setBrowserName(browser.getName());
        }

        if (browser.getPlatform() != null) {
            capabilities.setPlatform(browser.getPlatform());
        }

        if (browser.getVersion() != null) {
            capabilities.setVersion(browser.getVersion());
        }
    }

    private ChromeOptions createChromeOptions() throws Exception {
        ChromeOptions chromeOptions = new ChromeOptions();
        if (Boolean.parseBoolean(getConfigProperty(PropertyNames.HEADLESS))) {
            chromeOptions.addArguments("--headless=new");
        }
        return chromeOptions;
    }

    private FirefoxOptions createFirefoxOptions() throws Exception {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (Boolean.parseBoolean(getConfigProperty(PropertyNames.HEADLESS))) {
            firefoxOptions.addArguments("--headless=new");
        }
        return firefoxOptions;
    }

    private EdgeOptions createEdgeOptions() throws Exception {
        EdgeOptions edgeOptions = new EdgeOptions();
        if (Boolean.parseBoolean(getConfigProperty(PropertyNames.HEADLESS))) {
            edgeOptions.addArguments("--headless=new");
        }
        return edgeOptions;
    }

    public WebDriver createWebDriver() throws Exception {
        return this.createWebDriver(getWebDriverManager(), getStartPage());
    }

    public WebDriver createWebDriver(WebDriverManager driverManager) throws Exception {
        return this.createWebDriver(driverManager, getStartPage());
    }

    public WebDriver createWebDriver(WebDriverManager driverManager, String pageUrl) throws Exception {
        return this.createWebDriver(driverManager, pageUrl, 0);
    }

    public List<WebDriver> createWebDrivers() throws
            Exception {
        return this.createWebDrivers(getWebDriverManager(), getNumberOfBrowser());
    }

    public List<WebDriver> createWebDrivers(WebDriverManager driverManager) throws
            Exception {
        return this.createWebDrivers(driverManager, getNumberOfBrowser());
    }

    public List<WebDriver> createWebDrivers(WebDriverManager driverManager, int numberOfBrowser) throws
            Exception, IOException {

        setNumberOfBrowser(numberOfBrowser);
        setWebDrivers(driverManager.create(numberOfBrowser));
        if (isBrowserInDocker())
            setFileDetector();
        createWebDriverWait();

        return getWebDrivers();
    }

    public WebDriver createWebDriver(WebDriverManager driverManager, String pageUrl, long timeOutInSeconds) throws
            Exception, IOException {

        System.out.println(String.format("URL :*%s*", new Object[]{pageUrl}));

        setStartPage(pageUrl);
        setWebDriver(driverManager.create());
        if (isBrowserInDocker())
            setFileDetector();

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

    public WebDriver createChromeAndroidDriver(String browserVersion, String deviceName) throws
            MalformedURLException {
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
