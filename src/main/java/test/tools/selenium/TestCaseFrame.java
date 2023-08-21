package test.tools.selenium;

import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v115.emulation.Emulation;
import org.openqa.selenium.devtools.v115.performance.Performance;
import org.openqa.selenium.devtools.v115.performance.model.Metric;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.tools.selenium.config.PropertyNames;
import test.tools.selenium.instances.ConfigurationInstance;
import test.tools.selenium.util.Browser;
import test.tools.selenium.util.OsUtility;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

public abstract class TestCaseFrame {

    private Browser browser = null;
    private int numberOfBrowser = 1;
    private WebDriverManager webDriverManager = null;
    private WebDriver webDriver = null;
    private WebDriverWait wait = null;
    private String startPage = null;
    private String seleniumHubUrl = null;
    private String appiumHubUrl = null;
    private boolean isRemote = false;
    private boolean enableRecording = false;
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

    public void setEnableRecording(boolean enableRecording) {
        this.enableRecording = enableRecording;
    }

    public boolean isEnableRecording() {
        return enableRecording;
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
            setEnableRecording(Boolean.parseBoolean(getConfigProperty(PropertyNames.ENABLE_RECORDING)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @throws Exception
     */
    private void createBrowserFromConfiguration() throws Exception {
        setBrowser(new Browser(this.getConfigProperty(PropertyNames.BROWSER_TYPE), null, (Platform) null));
    }

    public WebDriverManager createWebDriverManager() throws Exception {
        createBrowserFromConfiguration();
        if (isRemote()) {
            setWebDriverManager(createWebDriverManager(getBrowser()).browserInDocker().enableVnc());
        } else {
            setWebDriverManager(createWebDriverManager(getBrowser()));
        }
        return getWebDriverManager();
    }

    public WebDriverManager createWebDriverManager(Browser browser) throws Exception {
        setBrowser(browser);
        switch (browser.getName()) {
            case "chrome":
                setWebDriverManager(WebDriverManager.chromedriver());
                break;
            case "firefox":
                setWebDriverManager(WebDriverManager.firefoxdriver());
                break;
            case "opera":
                setWebDriverManager(WebDriverManager.operadriver());
                break;
            case "edge":
                setWebDriverManager(WebDriverManager.edgedriver());
                break;
            case "chromium":
                setWebDriverManager(WebDriverManager.chromiumdriver());
                break;
            case "safari":
                setWebDriverManager(WebDriverManager.safaridriver());
                break;
            case "ie":
                setWebDriverManager(WebDriverManager.iedriver());
                break;
        }
        return getWebDriverManager();
    }

    /**
     * create web driver based on default browser values
     *
     * @param scenario
     * @return
     * @throws Exception
     */
    public WebDriver createWebDriver(String scenario) throws Exception {
        return this.createWebDriver(scenario, getNumberOfBrowser());
    }

    public WebDriver createWebDriver(String scenario, int numberOfBrowser) throws Exception {
        return this.createWebDriver(scenario, numberOfBrowser, getStartPage());
    }

    /**
     * @param scenario
     * @param pageUrl
     * @return
     * @throws Exception
     * @throws IOException
     */
    public WebDriver createWebDriver(String scenario, int numberOfBrowser, String pageUrl) throws Exception, IOException {

        return this.createWebDriver(scenario, numberOfBrowser, pageUrl, 0);
    }


    /**
     * @param scenario
     * @param numberOfBrowser
     * @param pageUrl
     * @param timeOutInSeconds
     * @return
     * @throws Exception
     * @throws IOException
     */
    public WebDriver createWebDriver(String scenario, int numberOfBrowser, String pageUrl, long timeOutInSeconds) throws Exception, IOException {

        System.out.println(String.format("URL :*%s*", new Object[]{pageUrl}));

        setNumberOfBrowser(numberOfBrowser);

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

    private WebDriver createEdgeDriver(String scenario) throws Exception {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("test-type");
        edgeOptions.addArguments("disable-popup-blocking");
        edgeOptions.addArguments("ignore-certificate-errors");
        edgeOptions.addArguments("disable-translate");
        edgeOptions.addArguments("start-maximized");
        edgeOptions.addArguments("--remote-allow-origins=*");
        if (Boolean.parseBoolean(getConfigProperty(PropertyNames.HEADLESS))) {
            edgeOptions.addArguments("--headless=new");
        }
        edgeOptions.setImplicitWaitTimeout(Duration.ofSeconds(Long.parseLong(getConfigProperty("browser.implicit.wait.timeOut"))));
        edgeOptions.setScriptTimeout(Duration.ofSeconds(Long.parseLong(getConfigProperty("browser.set.script.timeOut"))));
        edgeOptions.setPageLoadTimeout(Duration.ofSeconds(Long.parseLong(getConfigProperty("browser.page.load.timeOut"))));
        edgeOptions.setPageLoadStrategy(PageLoadStrategy.fromString(getConfigProperty("page.load.strategy")));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("edge");
        capabilities.setCapability("build", getConfigProperty("build"));
        capabilities.setCapability("name", scenario);
        capabilities.merge(edgeOptions);

        setWebDriver(webDriverManager.capabilities(capabilities).create());
        return getWebDriver();
    }

    private WebDriver createOperaDriver(String scenario) throws Exception {
        return createChromiumDriver(scenario, getBrowser().getName());
    }

    private WebDriver createChromiumDriver(String scenario, String browserName) throws Exception {
        ChromiumOptions chromiumOptions = new ChromiumOptions("name", browserName, scenario);
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
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browserName);
        capabilities.setCapability("build", getConfigProperty("build"));
        chromiumOptions.merge(capabilities);
        setWebDriver(getWebDriverManager().capabilities(chromiumOptions).create());
        return getWebDriver();
    }

    private WebDriver createSafariDriver(String scenario) throws Exception {
        SafariOptions safariOptions = new SafariOptions();
        safariOptions.is("test-type");
        safariOptions.is("disable-popup-blocking");
        safariOptions.is("ignore-certificate-errors");
        safariOptions.is("disable-translate");
        safariOptions.is("start-maximized");
        if (Boolean.parseBoolean(getConfigProperty(PropertyNames.HEADLESS))) {
            safariOptions.is("--headless=new");
        }
        safariOptions.setImplicitWaitTimeout(Duration.ofSeconds(Long.parseLong(getConfigProperty("browser.implicit.wait.timeOut"))));
        safariOptions.setScriptTimeout(Duration.ofSeconds(Long.parseLong(getConfigProperty("browser.set.script.timeOut"))));
        safariOptions.setPageLoadTimeout(Duration.ofSeconds(Long.parseLong(getConfigProperty("browser.page.load.timeOut"))));
        safariOptions.setPageLoadStrategy(PageLoadStrategy.fromString(getConfigProperty("page.load.strategy")));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("safari");
        capabilities.setCapability("build", getConfigProperty("build"));
        capabilities.setCapability("name", scenario);
        safariOptions.merge(capabilities);
        setWebDriver(getWebDriverManager().capabilities(safariOptions).create());
        return getWebDriver();
    }

    private WebDriver createIeDriver(String scenario) throws Exception {
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        internetExplorerOptions.addCommandSwitches("test-type");
        internetExplorerOptions.addCommandSwitches("disable-popup-blocking");
        internetExplorerOptions.addCommandSwitches("ignore-certificate-errors");
        internetExplorerOptions.addCommandSwitches("disable-translate");
        internetExplorerOptions.addCommandSwitches("start-maximized");
        internetExplorerOptions.addCommandSwitches("--remote-allow-origins=*");
        if (Boolean.parseBoolean(getConfigProperty(PropertyNames.HEADLESS))) {
            internetExplorerOptions.addCommandSwitches("--headless=new");
        }
        internetExplorerOptions.setImplicitWaitTimeout(Duration.ofSeconds(Long.parseLong(getConfigProperty("browser.implicit.wait.timeOut"))));
        internetExplorerOptions.setScriptTimeout(Duration.ofSeconds(Long.parseLong(getConfigProperty("browser.set.script.timeOut"))));
        internetExplorerOptions.setPageLoadTimeout(Duration.ofSeconds(Long.parseLong(getConfigProperty("browser.page.load.timeOut"))));
        internetExplorerOptions.setPageLoadStrategy(PageLoadStrategy.fromString(getConfigProperty("page.load.strategy")));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("ie");
        capabilities.setCapability("build", getConfigProperty("build"));
        capabilities.setCapability("name", scenario);
        internetExplorerOptions.merge(capabilities);
        setWebDriver(getWebDriverManager().capabilities(internetExplorerOptions).create());
        return getWebDriver();
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
        if (Boolean.parseBoolean(getConfigProperty(PropertyNames.HEADLESS))) {
            firefoxOptions.addArguments("--headless=new");
        }
        firefoxOptions.setImplicitWaitTimeout(Duration.ofSeconds(Long.parseLong(getConfigProperty("browser.implicit.wait.timeOut"))));
        firefoxOptions.setScriptTimeout(Duration.ofSeconds(Long.parseLong(getConfigProperty("browser.set.script.timeOut"))));
        firefoxOptions.setPageLoadTimeout(Duration.ofSeconds(Long.parseLong(getConfigProperty("browser.page.load.timeOut"))));
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.fromString(getConfigProperty("page.load.strategy")));
        firefoxOptions.setLogLevel(FirefoxDriverLogLevel.fromString(getConfigProperty("chrome.log.level")));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");
        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
        capabilities.setCapability("build", getConfigProperty("build"));
        capabilities.setCapability("name", scenario);
        firefoxOptions.merge(capabilities);

        setWebDriver(webDriverManager.capabilities(firefoxOptions).create());
        //setWebDriver(new RemoteWebDriver(new URL(getSeleniumHubUrl()), capabilities));

        return getWebDriver();
    }

    public WebDriver createChromeAndroidDriver() throws MalformedURLException {
        return createChromeAndroidDriver(PropertyNames.BROWSER_VERSION, PropertyNames.BROWSER_DEVICE);
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
        chromeOptions.addArguments("--remote-allow-origins=*");
        if (Boolean.parseBoolean(getConfigProperty(PropertyNames.HEADLESS))) {
            chromeOptions.addArguments("--headless=new");
        }
        chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(Long.parseLong(getConfigProperty("browser.implicit.wait.timeOut"))));
        chromeOptions.setScriptTimeout(Duration.ofSeconds(Long.parseLong(getConfigProperty("browser.set.script.timeOut"))));
        chromeOptions.setPageLoadTimeout(Duration.ofSeconds(Long.parseLong(getConfigProperty("browser.page.load.timeOut"))));
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.fromString(getConfigProperty("page.load.strategy")));
        /*Browser start maximize for mac os*/
        //options.addArguments("--kiosk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", getConfigProperty("build"));
        capabilities.setCapability("name", scenario);
        chromeOptions.merge(capabilities);

        setWebDriver(getWebDriverManager().capabilities(chromeOptions).create());
        //setWebDriver(new RemoteWebDriver(new URL(getSeleniumHubUrl()), chromeOptions));

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
        } else if ("firefox".equalsIgnoreCase(browser.getName())) {
            createFirefoxDriver(scenario);
        } else if ("opera".equalsIgnoreCase(browser.getName())) {
            createOperaDriver(scenario);
        } else if ("edge".equalsIgnoreCase(browser.getName())) {
            createEdgeDriver(scenario);
        } else if ("chromium".equalsIgnoreCase(browser.getName())) {
            createChromiumDriver(scenario, browser.getName());
        } else if ("safari".equalsIgnoreCase(browser.getName())) {
            createSafariDriver(scenario);
        } else if ("ie".equalsIgnoreCase(browser.getName())) {
            createIeDriver(scenario);
        } else {
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
