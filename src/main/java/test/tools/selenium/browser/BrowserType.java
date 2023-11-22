package test.tools.selenium.browser;

public enum BrowserType {

    CHROME, FIREFOX, OPERA, EDGE, SAFARI, CHROME_MOBILE;

    public String toBrowserName() {
        if (this == CHROME_MOBILE) {
            return CHROME.name();
        } else {
            return this.name();
        }
    }

    public boolean isChromeBased() {
        return this == BrowserType.CHROME || this == BrowserType.CHROME_MOBILE;
    }

}
