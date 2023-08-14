package test.tools.selenium.interactions;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.Set;

public class SimpleActions {

    public WebDriver driver;
    public WebDriverWait wait;
    public ExtentTest extentTest;

    public SimpleActions(WebDriver driver, WebDriverWait wait,ExtentTest extentTest) {
        this.driver = driver;
        this.wait = wait;
        this.extentTest = extentTest;
    }

    /**
     * To change browser tab
     */
    public void changeTab() {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList tabs = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1).toString());
    }

    /**
     * Switch to frame
     *
     * @param element
     */
    public void switchToFrame(WebElement element) {
        driver.switchTo().defaultContent();
        driver.switchTo().frame(element);
    }

    /**
     * Alert popup handle
     *
     * @param acceptAndDismiss
     */
    public void alertPopup(boolean acceptAndDismiss) {
        wait.until(ExpectedConditions.alertIsPresent());
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
     * @param element
     */
    public void switchWindowAndHandle(WebElement element) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String selectWindow : allWindows) {
            driver.switchTo().window(selectWindow);
            IsActions presence = new IsActions(driver, wait,extentTest);
            if (presence.isElementDisplayed(element)) {
                break;
            }
        }
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
     * Highlight element with red color
     *
     * @param element
     */
    public void highlightElement(WebElement element) {
        if (element != null) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
                    "color: red; border: 3px solid red;");
        }
    }

}
