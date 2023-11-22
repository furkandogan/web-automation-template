package test.tools.selenium.browser;

import com.google.gson.internal.LinkedTreeMap;
import test.tools.selenium.browser.BrowsersTemplate.Browser;

public class BrowserBuilder {

    String type;
    String version;
    String remoteUrl;
    String[] arguments;
    String[] preferences;
    Object capabilities;

    public BrowserBuilder(String type) {
        this.type = type;
    }

    public static BrowserBuilder chrome() {
        return new BrowserBuilder("chrome");
    }

    public static BrowserBuilder chromeMobile() {
        return new BrowserBuilder("chrome-mobile");
    }

    public static BrowserBuilder firefox() {
        return new BrowserBuilder("firefox");
    }

    public static BrowserBuilder edge() {
        return new BrowserBuilder("edge");
    }

    public static BrowserBuilder opera() {
        return new BrowserBuilder("opera");
    }

    public static BrowserBuilder safari() {
        return new BrowserBuilder("safari");
    }

    public static BrowserBuilder iexplorer() {
        return new BrowserBuilder("iexplorer");
    }

    public static BrowserBuilder chromeInDocker() {
        return new BrowserBuilder("chrome-in-docker");
    }

    public static BrowserBuilder firefoxInDocker() {
        return new BrowserBuilder("firefox-in-docker");
    }

    public static BrowserBuilder operaInDocker() {
        return new BrowserBuilder("opera-in-docker");
    }

    public static BrowserBuilder edgeInDocker() {
        return new BrowserBuilder("edge-in-docker");
    }

    public static BrowserBuilder safariInDocker() {
        return new BrowserBuilder("safari-in-docker");
    }

    public BrowserBuilder version(String version) {
        this.version = version;
        return this;
    }

    public BrowserBuilder remoteUrl(String url) {
        this.remoteUrl = url;
        return this;
    }

    public BrowserBuilder arguments(String[] arguments) {
        this.arguments = arguments;
        return this;
    }

    public BrowserBuilder preferences(String[] preferences) {
        this.preferences = preferences;
        return this;
    }

    public BrowserBuilder capabilities(
            LinkedTreeMap<String, String> capabilities) {
        this.capabilities = capabilities;
        return this;
    }

    public Browser build() {
        return new Browser(type, version, remoteUrl, arguments, preferences,
                capabilities);
    }

}
