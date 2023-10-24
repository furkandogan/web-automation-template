package test.tools.selenium.interactions;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;

public class StoreElementProperties extends GetElementProperties{

    private HashMap<String, String> stringList = null;

    public StoreElementProperties(WebDriver driver, WebDriverWait wait, ExtentTest extentTest) {
        super(driver, wait, extentTest);
    }

    public HashMap<String, String> getStringList() {
        return stringList;
    }

    private void setStringList(HashMap<String, String> stringList) {
        if (this.stringList == null)
            this.stringList = stringList;
        else
            this.stringList.putAll(stringList);
    }

    /**
     * @param element
     * @return
     */
    public HashMap<String, String> storeElementData(WebElement element, boolean hidden) {
        HashMap<String, String> attrTextList = new HashMap<>();
        attrTextList.put(element.toString(), getText(element, hidden));
        setStringList(attrTextList);
        return getStringList();
    }

    /**
     * @param element
     * @param attr
     * @return
     */
    public HashMap<String, String> storeElementData(WebElement element, String attr, boolean hidden) {
        HashMap<String, String> attrTextList = new HashMap<>();
        attrTextList.put(element.toString(), getAttribute(element, attr, hidden));
        setStringList(attrTextList);
        return getStringList();
    }

    /**
     * @param element
     * @return
     */
    public String getStoreElementValue(String element) {
        return getStringList().get(element);
    }
}
