package test.tools.selenium.interactions;

import test.tools.selenium.constants.XpathInjection;
import test.tools.selenium.mapping.MapMethodType;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Set;

import static test.tools.selenium.constants.XpathInjection.createXpath;

public class BaseUtil extends FindActions {

    MapMethodType mapMethodType = MapMethodType.FRAME_AND_WINDOW_OBJECT;

    public BaseUtil(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    /**
     * To change browser tab
     */
    public void changeTab() {
        ArrayList tabs = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1).toString());

    }

    /**
     * Sleep seconds
     *
     * @param seconds
     */
    public void sleep(Integer seconds) {
        long secondsLong = ( long ) seconds;
        try {
            Thread.sleep(secondsLong);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * refresh page
     */
    public void pageRefresh() {
        try {
            driver.navigate().refresh();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Open custom url
     *
     * @param url
     */
    public void openCustomUrlPage(String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Navigate to url
     *
     * @param url
     */
    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    /**
     * Refresh page
     */
    public void refreshTo() {
        driver.navigate().refresh();
    }


    /**
     * Go forward backs
     */
    public void goBack() {
        driver.navigate().back();
    }

    /**
     * Call the page
     *
     * @param page
     */
    public void callPage(String page) {
        GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
        driver.get(getElementProperties.getCurrentUrl() + page);
    }

    /**
     * Switch to frame
     *
     * @param attr
     */
    public void switchToFrame(String attr) {
        driver.switchTo().defaultContent();
        driver.switchTo().frame(findElement(XpathInjection.createXpath(attr, mapMethodType)));
    }

    /**
     * Alert popup handle
     *
     * @param acceptAndDismiss
     */
    public void alertPopup(boolean acceptAndDismiss) {
        waitForAlertIsPresent();
        Alert alert = driver.switchTo().alert();
        if (acceptAndDismiss) {
            alert.accept();
        } else {
            alert.dismiss();
        }
    }

    /**
     * Switch to windows
     *
     * @param attr
     */
    public void switchWindowAndHandle(String attr) {
        WebElement element = findElement(XpathInjection.createXpath(attr, mapMethodType));
        Set<String> allWindows = driver.getWindowHandles();
        for (String selectWindow : allWindows) {
            driver.switchTo().window(selectWindow);
            PresenceOfQualification presence = new PresenceOfQualification(driver, wait);
            if (presence.isElementDisplayed(element)) {
                break;
            }
        }
    }


    /**
     * Add cookie by parameter
     *
     * @param name
     * @param value
     * @param domain
     * @param path
     * @param expiry
     */
    public void addCookie(String name, String value, String domain, String path, Date expiry) {
        driver.manage().addCookie(new Cookie(name, value, domain, path, expiry));
    }

    /**
     * Delete cookie
     *
     * @param cookieName
     */
    public void deleteCookie(String cookieName) {
        driver.manage().deleteCookieNamed(cookieName);
    }


    /**
     * Desired length random string
     *
     * @param size
     * @return
     */
    public String createRandomString(int size) {
        return RandomStringUtils.randomAlphanumeric(size);
    }

    /**
     * The desirable range random int
     *
     * @param start
     * @param end
     * @return
     */
    public int createRandomInteger(int start, int end) {
        int randomNumber;
        if (start > end) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        } else {
            Random random = new Random();
            randomNumber = random.nextInt((end - start) + 1) + start;
        }
        return randomNumber;
    }

    /**
     * The remove in text of number
     *
     * @param regexText
     * @return
     */
    public String regexString(String regexText) {
        return regexText.replaceAll("\\d", "");
    }

    /**
     * Convert to month
     *
     * @param month
     * @return
     */
    public String convertToMonth(String month) {
        String[] monthList = {"Month", "Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran", "Temmuz", "Ağustos",
                "Eylül", "Ekim", "Kasım", "Aralık"};
        if (month.length() > 2) {
            for (String selectedMonth : monthList) {
                if (selectedMonth.equals(month)) {
                    return month;
                }
            }
        } else if (month.length() == 1 || month.length() == 2) {
            return monthList[Integer.parseInt(month)];
        }
        return "Does Not Exists Data Date";
    }

    /**
     * Turkish Character Ranking
     *
     * @return
     */
    public RuleBasedCollator getTurkishCollator() {
        String turkish = " < '.' < 0 < 1 < 2 < 3 < 4 < 5 < 6 < 7 < 8 < 9 < a, A < b, B < c, C < ç, Ç < d, D < e, E < f, F"
                + "< g, G < ğ, Ğ < h, H < ı, I < i, İ < j, J < k, K < l, L "
                + "< m, M < n, N < o, O < ö, Ö < p, P < q, Q < r, R < s, S"
                + "< ş, Ş < t, T < u, U < ü, Ü < v, V < w, W < x, X < y, Y < z, Z";
        try {
            return new RuleBasedCollator(turkish);
        } catch (ParseException e) {
        }
        return null;
    }


    /**
     * Element null exception
     *
     * @param by
     * @param index
     */

    public void nullElementException(By by, int... index) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ELEMENT (");
        stringBuilder.append(by);
        stringBuilder.append(",");
        stringBuilder.append(index.length > 0 ? index[0] : "");
        stringBuilder.append(") NOT EXISTS; AUTOMATION DATAS MAY BE INVALID!");
        throw new NullPointerException(stringBuilder.toString());
    }

}
