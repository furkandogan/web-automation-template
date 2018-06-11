package test.tools.selenium.interactions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;

import static test.tools.selenium.constants.XpathInjection.mapValue;

public class StoreElementProperties extends FindActions {

    public StoreElementProperties(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
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
     * @param attr
     * @return
     */
    public HashMap<String, String> storeElementData(String attr) {
        HashMap<String, String> attrTextList = new HashMap<>();
        GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
        attrTextList.put(mapValue.getAttrValue(), getElementProperties.getText(attr));
        setStringList(attrTextList);
        return getStringList();
    }

    /**
     * @param attr
     * @param patternAttr
     * @return
     */
    public HashMap<String, String> storeElementData(String attr, String patternAttr) {
        HashMap<String, String> attrTextList = new HashMap<>();
        GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
        attrTextList.put(mapValue.getAttrValue(), getElementProperties.getAttribute(attr, patternAttr));
        setStringList(attrTextList);
        return getStringList();
    }

    /**
     * @param attr
     * @return
     */
    public String getStoreElementValue(String attr) {
        return getStringList().get(attr);
    }
}
