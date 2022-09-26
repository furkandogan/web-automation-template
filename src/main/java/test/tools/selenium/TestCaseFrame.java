package test.tools.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v103.emulation.Emulation;
import org.openqa.selenium.devtools.v103.performance.Performance;
import org.openqa.selenium.devtools.v103.performance.model.Metric;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.tools.selenium.config.PropertyNames;
import test.tools.selenium.instances.ConfigurationInstance;
import test.tools.selenium.util.Browser;
import test.tools.selenium.util.OsUtility;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

public abstract class TestCaseFrame {

    private Browser browser = null;
    private WebDriver webDriver = null;
    private WebDriverWait wait = null;
    private FluentWait fluentWait;
    private String startPage = null;
    private String seleniumHubUrl = null;
    private String appiumHubUrl = null;

    private boolean isRemote = false;
    private boolean isMobileDevice = false;
    private boolean isMobileEmulation = false;

    private Duration timeOutInSeconds = Duration.ofSeconds(60);
    private Duration implicitlyWaitTimeOut = Duration.ofSeconds(60);

    private List<Metric> metricList;


    public Duration getTimeOutInSeconds() {
        return timeOutInSeconds;
    }

    public void setTimeOutInSeconds(Duration timeOutInSeconds) {
        this.timeOutInSeconds = timeOutInSeconds;
    }

    public Browser getBrowser() {
        return browser;
    }

    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(WebDriver newWebDriver) {
        this.webDriver = newWebDriver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void setWait(WebDriverWait wait) {
        this.wait = wait;
    }

    public Duration getImplicitlyWaitTimeOut() {
        return implicitlyWaitTimeOut;
    }

    public void setImplicitlyWaitTimeOut(Duration implicitlyWaitTimeOut) {
        implicitlyWaitTimeOut = implicitlyWaitTimeOut;
    }

    public boolean isRemote() {
        return isRemote;
    }

    public void setRemote(boolean remote) {
        isRemote = remote;
    }

    public String getStartPage() {
        return startPage;
    }

    public void setStartPage(String startPage) {
        this.startPage = startPage;
    }

    public String getSeleniumHubUrl() {
        return seleniumHubUrl;
    }

    public void setSeleniumHubUrl(String seleniumHubUrl) {
        this.seleniumHubUrl = seleniumHubUrl;
    }

    public String getAppiumHubUrl() {
        return appiumHubUrl;
    }

    public void setAppiumHubUrl(String appiumHubUrl) {
        this.appiumHubUrl = appiumHubUrl;
    }

    public boolean isMobileDevice() {
        return isMobileDevice;
    }

    public void setMobileDevice(boolean mobileDevice) {
        isMobileDevice = mobileDevice;
    }

    public boolean isMobileEmulation() {
        return isMobileEmulation;
    }

    public void setMobileEmulation(boolean mobileEmulation) {
        isMobileEmulation = mobileEmulation;
    }

    public TestCaseFrame() {
        try {
            setRemote(Boolean.parseBoolean(getConfigProperty(PropertyNames.BROWSER_REMOTE_DRIVER)));
            setStartPage(getConfigProperty(PropertyNames.BASE_URL));
            setMobileDevice(Boolean.parseBoolean(getConfigProperty(PropertyNames.BROWSER_MOBILE_DEVICE)));
            setMobileEmulation(Boolean.parseBoolean(getConfigProperty(PropertyNames.BROWSER_MOBILE_EMULATION)));
            setSeleniumHubUrl(getConfigProperty(PropertyNames.SELENIUM_HUB_URL));
            setAppiumHubUrl(getConfigProperty(PropertyNames.APPIUM_HUB_URL));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * create web driver based on default browser values
     *
     * @param scenario
     * @return
     * @throws Exception
     */
    public WebDriver createWebDriver(String scenario) throws Exception {
        createBrowserFromConfiguration();
        return this.createWebDriver(scenario, getBrowser(), getStartPage());
    }

    /**
     * @throws Exception
     */
    private void createBrowserFromConfiguration() throws Exception {
        setBrowser(new Browser(this.getConfigProperty(PropertyNames.BROWSER_TYPE), null, (Platform) null));
    }

    /**
     * @param scenario
     * @param baseUrl
     * @return
     * @throws Exception
     * @throws IOException
     */
    public WebDriver createWebDriver(String scenario, String baseUrl) throws Exception, IOException {

        return this.createWebDriver(scenario, getBrowser(), baseUrl);
    }

    /**
     * @param scenario
     * @param browser
     * @return
     * @throws Exception
     * @throws IOException
     */
    public WebDriver createWebDriver(String scenario, Browser browser) throws Exception, IOException {

        createWebDriver(scenario, browser, getStartPage(), 0);

        return this.webDriver;
    }

    /**
     * @param scenario
     * @param browser
     * @param pageUrl
     * @return
     * @throws Exception
     * @throws IOException
     */
    public WebDriver createWebDriver(String scenario, Browser browser, String pageUrl) throws Exception, IOException {

        createWebDriver(scenario, browser, pageUrl, 0);

        return this.webDriver;
    }

    /**
     * @param scenario
     * @param browser
     * @param pageUrl
     * @param timeOutInSeconds
     * @return
     * @throws Exception
     * @throws IOException
     */
    public WebDriver createWebDriver(String scenario, Browser browser, String pageUrl, long timeOutInSeconds) throws Exception, IOException {

        System.out.println(String.format("URL :*%s*", new Object[]{pageUrl}));

        //set browser info
        setBrowser(browser);

        //set start page
        setStartPage(pageUrl);

        //create web driver
        setWebDriver(buildWebdriver(scenario));

        //set file detector
        if (isRemote())
            setFileDetector();

        //set timeout
        createWebDriverWait();

        return getWebDriver();
    }

    /**
     * @return
     * @throws Exception
     */
    private WebDriverWait createWebDriverWait() throws Exception {
        Duration tm = Duration.ofSeconds(Long.valueOf(this.getConfigProperty("browser.wait.timeout")));
        setTimeOutInSeconds(tm);
        WebDriverWait driverWait = new WebDriverWait(getWebDriver(), getTimeOutInSeconds());
        setWait(driverWait);

        return driverWait;
    }

    /**
     * open the homepage
     */
    public void openStartPage() {
        //getWebDriver().manage().window().maximize();
        //open the requested main page
        getWebDriver().get(getStartPage());
    }

    /**
     * set file detector for remote desktop
     */
    public void setFileDetector() {
        ((RemoteWebDriver) getWebDriver()).setFileDetector(new LocalFileDetector());
    }


    /**
     * Driver end
     *
     * @param startTime
     */
    public void endDriverSession(long startTime) {

        System.out.println(String.format("'%s' finished (%s ms.) .", this.getClass()
                .getSimpleName(), DurationFormatUtils
                .formatDurationHMS(System.currentTimeMillis()
                        - startTime)));

        if (getWebDriver() != null) {
            getWebDriver().quit();
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
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


    /**
     * @param scenario
     * @return
     * @throws Exception
     */
    private WebDriver createFirefoxDriver(String scenario) throws Exception {

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("test-type");
        firefoxOptions.addArguments("disable-popup-blocking");
        firefoxOptions.addArguments("ignore-certificate-errors");
        firefoxOptions.addArguments("disable-translate");
        firefoxOptions.addArguments("start-maximized");
        firefoxOptions.setImplicitWaitTimeout(Duration.ofSeconds(Long.parseLong(getConfigProperty("browser.implicit.wait.timeOut"))));
        firefoxOptions.setScriptTimeout(Duration.ofSeconds(Long.parseLong(getConfigProperty("browser.set.script.timeOut"))));
        firefoxOptions.setPageLoadTimeout(Duration.ofSeconds(Long.parseLong(getConfigProperty("browser.page.load.timeOut"))));
        firefoxOptions.setHeadless(Boolean.parseBoolean(getConfigProperty(PropertyNames.CHROME_HEADLESS)));
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.fromString(getConfigProperty("page.load.strategy")));
        firefoxOptions.setLogLevel(FirefoxDriverLogLevel.fromString(getConfigProperty("chrome.log.level")));

        if (isRemote()) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("firefox");
            capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
            capabilities.setCapability("build", getConfigProperty("build"));
            capabilities.setCapability("name", scenario);

            setWebDriver(new RemoteWebDriver(new URL(getSeleniumHubUrl()), capabilities));
        } else {
            WebDriverManager.firefoxdriver().setup();
            FirefoxDriver firefoxDriver = new FirefoxDriver();
            setWebDriver(firefoxDriver);
        }

        return getWebDriver();
    }

    /**
     * @param scenario
     * @return
     * @throws Exception
     */
    private WebDriver createChromeDriver(String scenario) throws Exception {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("test-type");
        chromeOptions.addArguments("disable-popup-blocking");
        chromeOptions.addArguments("ignore-certificate-errors");
        chromeOptions.addArguments("disable-translate");
        chromeOptions.addArguments("start-maximized");
        chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(Long.parseLong(getConfigProperty("browser.implicit.wait.timeOut"))));
        chromeOptions.setScriptTimeout(Duration.ofSeconds(Long.parseLong(getConfigProperty("browser.set.script.timeOut"))));
        chromeOptions.setPageLoadTimeout(Duration.ofSeconds(Long.parseLong(getConfigProperty("browser.page.load.timeOut"))));
        chromeOptions.setHeadless(Boolean.parseBoolean(getConfigProperty(PropertyNames.CHROME_HEADLESS)));
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.fromString(getConfigProperty("page.load.strategy")));
        chromeOptions.setLogLevel(ChromeDriverLogLevel.fromString(getConfigProperty("chrome.log.level")));
        /*Browser start maximize for mac os*/
        //options.addArguments("--kiosk");

        if (isRemote()) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            if (isMobileDevice()) {
                capabilities.setCapability("platformName", getConfigProperty(PropertyNames.BROWSER_PLATFORM));
                capabilities.setCapability("automationName", getConfigProperty(PropertyNames.BROWSER_AUTOMATION_NAME));
                capabilities.setCapability("deviceName", getConfigProperty(PropertyNames.BROWSER_DEVICE));
                if (getConfigProperty(PropertyNames.BROWSER_PLATFORM).equalsIgnoreCase(String.valueOf(Platform.IOS))){
                    //for ios
                    capabilities.setCapability("udid", getConfigProperty(PropertyNames.UDID));
                    capabilities.setCapability("bundledId", getConfigProperty(PropertyNames.BUNDLE_ID));
                }else if (getConfigProperty(PropertyNames.BROWSER_PLATFORM).equalsIgnoreCase(String.valueOf(Platform.ANDROID))){
                    //for android
                    capabilities.setCapability("app", getConfigProperty(PropertyNames.UDID));
                    capabilities.setCapability("appPackage", getConfigProperty(PropertyNames.BUNDLE_ID));
                    capabilities.setCapability("appActivity", getConfigProperty(PropertyNames.BUNDLE_ID));
                }

                setWebDriver(new RemoteWebDriver(new URL(getAppiumHubUrl()), capabilities));
            } else {
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                //for zalenium
                //capabilities.setCapability("build", getConfigProperty("build"));
                //capabilities.setCapability("name", scenario);

                setWebDriver(new RemoteWebDriver(new URL(getSeleniumHubUrl()), chromeOptions));
            }
        } else {
            WebDriverManager.chromedriver().setup();
            ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);
            setWebDriver(chromeDriver);
        }

        if (isMobileEmulation()) {
            setWebDriver(new Augmenter().augment(getWebDriver()));
            DevTools devTools = ((HasDevTools) getWebDriver()).getDevTools();
            devTools.createSession();
            devTools.send(Emulation.setDeviceMetricsOverride(Integer.valueOf(getConfigProperty("chrome.width")),
                    Integer.valueOf(getConfigProperty("chrome.height")),
                    Integer.valueOf(getConfigProperty("device.scale.factor")),
                    true,
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty()));
        }

        if (Boolean.parseBoolean(getConfigProperty("chrome.performance.metrics"))) {
            setWebDriver(new Augmenter().augment(getWebDriver()));
            DevTools devTools = ((HasDevTools) getWebDriver()).getDevTools();
            devTools.createSession();
            devTools.send(Performance.enable(Optional.empty()));
            metricList = devTools.send(Performance.getMetrics());
        }

        if (Boolean.parseBoolean(getConfigProperty("chrome.geo.location"))) {
            setWebDriver(new Augmenter().augment(getWebDriver()));
            DevTools devTools = ((HasDevTools) getWebDriver()).getDevTools();
            devTools.createSession();
            devTools.send(Emulation.setGeolocationOverride(Optional.of(Long.parseLong(getConfigProperty("chrome.geo.latitude"))),
                    Optional.of(Long.parseLong(getConfigProperty("chrome.geo.longitude"))),
                    Optional.of(Long.parseLong(getConfigProperty("chrome.geo.accuracy")))));
        }
        return getWebDriver();
    }

    public static String getConfigProperty(String key) throws Exception {

        return ConfigurationInstance.getInstance().getConfigProperty(key);
    }

    /**
     * @param scenario
     * @return
     * @throws Exception
     */
    private WebDriver buildWebdriver(String scenario) throws Exception {
        String chromeDriverLoc = null;
        WebDriver webDriver = null;
        DesiredCapabilities capability = null;
        URL seleniumHub = null;
        Boolean isRemote = Boolean.valueOf(getConfigProperty("browser.remote.driver"));

        //print browser settings
        browser.print();
        System.out.println(String.format("OS     :*%s*", OsUtility.getOsInfo()));
        System.out.println(String.format("REMOTE :*%s*", isRemote));

        //test on which browser?
        if ("chrome".equalsIgnoreCase(browser.getName())) {
            //test on Chrome
            createChromeDriver(scenario);
        } else if ("firefox".equalsIgnoreCase(browser.getName())
                || "gecko".equalsIgnoreCase(browser.getName())
                || "geckodriver".equalsIgnoreCase(browser.getName())
                || "ff".equalsIgnoreCase(browser.getName())) {
            createFirefoxDriver(scenario);
        } else {
            // TODO :got unknown browser type!
            System.out.println(String.format("Browser type [%s] could not verified!", browser.getName()));
            System.exit(-1);
        }
        return getWebDriver();
    }


    /**
     * @param driver
     * @throws Exception
     */
    public void cleanUpWebDriver(WebDriver driver) throws Exception {

        if (driver != null) {
            driver.close();
            driver.quit();
        }
        if (Boolean.parseBoolean(getConfigProperty("chrome.performance.metrics"))) {
            for (Metric m : metricList) {
                System.out.println(m.getName() + " = " + m.getValue());
            }
        }
    }

}
