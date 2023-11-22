package test.tools.selenium.browser;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Locale.ROOT;
import static test.tools.selenium.browser.BrowserType.CHROME_MOBILE;

public class BrowsersTemplate {

    static final String IN_DOCKER = "-in-docker";
    static final String IN_SELENIDE = "-in-selenide";

    List<List<Browser>> browsers;

    public Stream<List<Browser>> getStream() {
        return browsers.stream();
    }

    public static class Browser {
        String type;
        String version;
        String remoteUrl;
        String[] arguments;
        String[] preferences;
        Object capabilities;

        public Browser(String type, String version, String remoteUrl,
                       String[] arguments, String[] preferences, Object capabilities) {
            this.type = type;
            this.version = version;
            this.remoteUrl = remoteUrl;
            this.arguments = arguments;
            this.preferences = preferences;
            this.capabilities = capabilities;
        }

        public Browser() {
        }

        public String getType() {
            return type;
        }

        public String getVersion() {
            return version;
        }

        public String getRemoteUrl() {
            return remoteUrl;
        }

        public void setRemoteUrl(String url) {
            this.remoteUrl = url;
        }

        public String[] getArguments() {
            return arguments;
        }

        public void setArguments(String[] arguments) {
            this.arguments = arguments;
        }

        public String[] getPreferences() {
            return preferences;
        }

        public void setPreferences(String[] preferences) {
            this.preferences = preferences;
        }

        public Object getCapabilities() {
            return capabilities;
        }

        public void setCapabilities(Object capabilities) {
            this.capabilities = capabilities;
        }

        public BrowserType toBrowserType() {
            return toBrowserType(getType());
        }

        public static BrowserType toBrowserType(String browser) {
            return BrowserType.valueOf(
                    browser.replace(IN_DOCKER, "").replace(IN_SELENIDE, "")
                            .replace("-", "_").toUpperCase(ROOT));
        }

        public boolean isAndroidBrowser() {
            return toBrowserType() == CHROME_MOBILE;
        }

        public boolean isDockerBrowser() {
            return getType().contains(IN_DOCKER);
        }

        public boolean isInSelenide() {
            return getType().contains(IN_SELENIDE);
        }

        @Override
        public String toString() {
            String versionMessage = getVersion() != null
                    ? ", version=" + getVersion()
                    : "";
            return "Browser [type=" + getType() + versionMessage + "]";
        }

    }

}
