package test.tools.selenium.interactions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;

public class StoreElementProperties {
    
    public WebDriver driver;
    public WebDriverWait wait;

    public StoreElementProperties(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private HashMap<String, String> stringList = null;

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
        GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
        attrTextList.put(element.toString(), getElementProperties.getText(element, hidden));
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
        GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
        attrTextList.put(element.toString(), getElementProperties.getAttribute(element, attr, hidden));
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
