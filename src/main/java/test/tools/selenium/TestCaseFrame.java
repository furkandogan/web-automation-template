package test.tools.selenium;

import cucumber.api.Scenario;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.tools.selenium.config.PropertyNames;
import test.tools.selenium.instances.ConfigurationInstance;
import test.tools.selenium.util.Browser;
import test.tools.selenium.util.OsUtility;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public abstract class TestCaseFrame {

    private Browser browser = null;
    private WebDriver webDriver = null;
    private WebDriverWait wait = null;
    private String startPage = null;
    private String seleniumHubUrl = null;
    private String appiumHubUrl = null;

    private boolean isRemote = false;
    private boolean isMobile = false;

    private long timeOutInSeconds = 60;
    private long implicitlyWaitTimeOut = 30;


    public long getTimeOutInSeconds() {
        return timeOutInSeconds;
    }

    public void setTimeOutInSeconds(long timeOutInSeconds) {
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

    public long getImplicitlyWaitTimeOut() {
        return implicitlyWaitTimeOut;
    }

    public void setImplicitlyWaitTimeOut(long implicitlyWaitTimeOut) {
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

    public boolean isMobile() {
        return isMobile;
    }

    public void setMobile(boolean mobile) {
        isMobile = mobile;
    }

    public TestCaseFrame() {
        try {
            setRemote(Boolean.valueOf(getConfigProperty("browser.remote.driver")));
            setStartPage(getConfigProperty("site.url"));
            setMobile(Boolean.valueOf(getConfigProperty("browser.mobile.type")));
            setSeleniumHubUrl(getConfigProperty(PropertyNames.SELENIUM_HUB_URL));
            setAppiumHubUrl(getConfigProperty(PropertyNames.APPIUM_HUB_URL));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * create web driver based on default browser values
     * */
    public WebDriver createWebDriver(Scenario scenario) throws Exception {
        //Read default browser type and version which will be used during test
        createBrowserFromConfiguration();
        return this.createWebDriver(scenario, getBrowser(), getStartPage());
    }

    /**
     * ,
     *
     * @throws Exception
     */
    private void createBrowserFromConfiguration() throws Exception {
        setBrowser(new Browser(this.getConfigProperty("browser.type"), null, (Platform) null));
    }

    /***
     *
     * @param scenario
     * @param baseUrl
     * @return
     * @throws Exception
     * @throws IOException
     */
    public WebDriver createWebDriver(Scenario scenario, String baseUrl) throws Exception, IOException {

        return this.createWebDriver(scenario, getBrowser(), baseUrl);
    }

    /***
     *
     * @param scenario
     * @param browser
     * @return
     * @throws Exception
     * @throws IOException
     */
    public WebDriver createWebDriver(Scenario scenario, Browser browser) throws Exception, IOException {

        createWebDriver(scenario, browser, getStartPage(), 0);

        return this.webDriver;
    }

    /***
     *
     * @param scenario
     * @param browser
     * @param pageUrl
     * @return
     * @throws Exception
     * @throws IOException
     */
    public WebDriver createWebDriver(Scenario scenario, Browser browser, String pageUrl) throws Exception, IOException {

        createWebDriver(scenario, browser, pageUrl, 0);

        return this.webDriver;
    }

    /***
     *
     * @param scenario
     * @param browser
     * @param pageUrl
     * @param timeOutInSeconds
     * @return
     * @throws Exception
     * @throws IOException
     */
    public WebDriver createWebDriver(Scenario scenario, Browser browser, String pageUrl, long timeOutInSeconds) throws Exception, IOException {

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

    /***
     *
     * @return
     * @throws Exception
     */
    private WebDriverWait createWebDriverWait() throws Exception {
        //set
        Long tm = Long.valueOf(this.getConfigProperty("browser.driver.wait.timeout"));
        setTimeOutInSeconds(tm.longValue());
        WebDriverWait driverWait = new WebDriverWait(getWebDriver(), getTimeOutInSeconds());
        setWait(driverWait);

        return driverWait;
    }

    /***
     *
     */
    public void openStartPage() {
        //getWebDriver().manage().window().maximize();
        //open the requested main page
        getWebDriver().get(getStartPage());
    }

    /***
     *
     */
    public void setFileDetector() {
        ((RemoteWebDriver) getWebDriver()).setFileDetector(new LocalFileDetector());
    }


    /***
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


    /***
     *
     * @param capabilities
     */
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


    /***
     *
     * @return
     * @throws Exception
     */
    private WebDriver createFirefoxDriver(Scenario scenario) throws Exception {

        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("print.always_print_silent", true);
        firefoxProfile.setPreference("print.show print progress", false);
        //firefoxProfile.setEnableNativeEvents(false);

        if (isRemote()) {

            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setBrowserName("firefox");
            capabilities.setCapability("firefox_profile", firefoxProfile.toJson());
            capabilities.setCapability("build", getConfigProperty("build"));
            capabilities.setCapability("name", scenario.getName());
            capabilities.setJavascriptEnabled(true);

            setWebDriver(new RemoteWebDriver(new URL(getSeleniumHubUrl()), capabilities));
            setFileDetector();
        } else {
            setFirefoxBrowserDriverLocation();
            FirefoxDriver firefoxDriver = new FirefoxDriver();
            setWebDriver(firefoxDriver);
        }

        return getWebDriver();
    }

    /**
     * @return
     * @throws Exception
     */
    private WebDriver createChromeDriver(Scenario scenario) throws Exception {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("disable-popup-blocking");
        options.addArguments("ignore-certificate-errors");
        options.addArguments("disable-translate");
        options.addArguments("start-maximized");
        options.addArguments("--window-size=1920,1080");
        //options.addArguments("headless");
        //options.addArguments("--kiosk");

        if (isRemote()) {

            if (isMobile()) {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                updateBrowserCapsFromConfig(capabilities);
                capabilities.setCapability("browserName", getConfigProperty(PropertyNames.BROWSER_TYPE));
                capabilities.setCapability("deviceName", getConfigProperty(PropertyNames.BROWSER_DEVICE));
                capabilities.setCapability("version", getConfigProperty(PropertyNames.BROWSER_DEVICE_VERSION));
                capabilities.setCapability("platformName", getConfigProperty(PropertyNames.BROWSER_PLATFORM));
                capabilities.setJavascriptEnabled(true);

                setWebDriver(new RemoteWebDriver(new URL(getAppiumHubUrl()), capabilities));
            } else {
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                capabilities.setCapability("build", getConfigProperty("build"));
                capabilities.setCapability("name", scenario.getName());
                capabilities.setJavascriptEnabled(true);

                updateBrowserCapsFromConfig(capabilities);

                setWebDriver(new RemoteWebDriver(new URL(getSeleniumHubUrl()), capabilities));
            }

        } else {
            if (isMobile()) {
                Map<String, String> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceName", "Galaxy S5");
                options.setExperimentalOption("mobileEmulation", mobileEmulation);
            }
            setChromeBrowserDriverLocation();
            ChromeDriver chromeDriver = new ChromeDriver(options);
            setWebDriver(chromeDriver);
        }

        return getWebDriver();
    }

    private void setChromeBrowserDriverLocation() throws Exception {
        String location = null;
        if (OsUtility.isWindows()) {
            location = getConfigProperty(PropertyNames.CHROME_DRV_LOC_WINDOWS);
        } else if (OsUtility.isUnix()) {
            location = getConfigProperty(PropertyNames.CHROME_DRV_LOC_LINUX);
        } else if (OsUtility.isMac()) {
            location = getConfigProperty(PropertyNames.CHROME_DRV_LOC_MAC);
        }
        System.setProperty("webdriver.chrome.driver", location);
    }

    private void setFirefoxBrowserDriverLocation() throws Exception {
        String location = null;
        if (OsUtility.isWindows()) {
            location = getConfigProperty(PropertyNames.FIREFOX_DRV_LOC_WINDOWS);
        } else if (OsUtility.isUnix()) {
            location = getConfigProperty(PropertyNames.FIREFOX_DRV_LOC_LINUX);
        } else if (OsUtility.isMac()) {
            location = getConfigProperty(PropertyNames.FIREFOX_DRV_LOC_MAC);
        }
        System.setProperty("webdriver.gecko.driver", location);
    }

    private void setIEBrowserDriverLocation() throws Exception {
        String location = null;
        if (OsUtility.isWindows()) {
            location = getConfigProperty(PropertyNames.IE_DRV_LOC_WINDOWS);
        }
        System.setProperty("webdriver.ie.driver", location);
    }

    /***
     *
     * @return
     * @throws Exception
     */
    private WebDriver createIEDriver(Scenario scenario) throws Exception {

        if (isRemote()) {

            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

            updateBrowserCapsFromConfig(capabilities);
            //capabilities.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
            //capabilities.setCapability(InternetExplorerDriver.IE_SWITCHES,"-private");
            //capabilities.setCapability("ignoreProtectedModeSettings",true);
            // capabilities.setCapability("ie.forceCreateProcessApi", true);
            // capabilities.setCapability("ie.ensureCleanSession", true);
            // capabilities.setCapability("ie.setProxyByServer", true);
            // capabilities.setCapability("logFile", "/tmp/server.log")
            // capabilities.setCapability("initialBrowserUrl",
            capabilities.setCapability("logLevel", "TRACE");
            capabilities.setCapability("build", getConfigProperty("build"));
            capabilities.setCapability("name", scenario.getName());
            capabilities.setJavascriptEnabled(true);

            setWebDriver(new RemoteWebDriver(new URL(getSeleniumHubUrl()), capabilities));
        } else {
            setIEBrowserDriverLocation();
            InternetExplorerDriver ieDriver = new InternetExplorerDriver();
            setWebDriver(ieDriver);
        }

        return getWebDriver();
    }

    public String getConfigProperty(String key) throws Exception {

        return ConfigurationInstance.getInstance().getConfigProperty(key);
    }

    /***
     *
     * @return
     * @throws Exception
     */
    private WebDriver buildWebdriver(Scenario scenario) throws Exception {
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
        if (PropertyNames.BROWSER_CHROME.equalsIgnoreCase(browser.getName())) {
            //test on Chrome
            createChromeDriver(scenario);
        } else if ("firefox".equalsIgnoreCase(browser.getName())
                || "gecko".equalsIgnoreCase(browser.getName())
                || "geckodriver".equalsIgnoreCase(browser.getName())
                || "ff".equalsIgnoreCase(browser.getName())) {
            createFirefoxDriver(scenario);
        } else if ("iexplorer".equalsIgnoreCase(browser.getName())
                || "ie".equalsIgnoreCase(browser.getName())
                || "internet explorer".equalsIgnoreCase(browser.getName())) {
            createIEDriver(scenario);
        } else {
            // TODO :got unknown browser type!
            System.out.println(String.format("Browser type [%s] could not verified!", browser.getName()));
            System.exit(-1);
        }
        return getWebDriver();
    }


    /***
     *
     * @param driver
     */
    public void cleanUpWebDriver(WebDriver driver) throws Exception {

        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

}