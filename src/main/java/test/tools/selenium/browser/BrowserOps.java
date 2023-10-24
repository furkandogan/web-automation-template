package test.tools.selenium.browser;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserOps {
    public ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("test-type");
        chromeOptions.addArguments("disable-popup-blocking");
        chromeOptions.addArguments("ignore-certificate-errors");
        chromeOptions.addArguments("disable-translate");
        chromeOptions.addArguments("start-maximized");
        return chromeOptions;
    }

    public FirefoxOptions getFireFoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("test-type");
        firefoxOptions.addArguments("disable-popup-blocking");
        firefoxOptions.addArguments("ignore-certificate-errors");
        firefoxOptions.addArguments("disable-translate");
        firefoxOptions.addArguments("start-maximized");
        return firefoxOptions;
    }

    public EdgeOptions getEdgeOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("test-type");
        edgeOptions.addArguments("disable-popup-blocking");
        edgeOptions.addArguments("ignore-certificate-errors");
        edgeOptions.addArguments("disable-translate");
        edgeOptions.addArguments("start-maximized");
        return edgeOptions;
    }
}
