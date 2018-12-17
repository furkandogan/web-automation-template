package test.tools.selenium.junit;

public interface SeleniumBaseInterface {
    void setUp(String scenario) throws Exception;

    void enterHomePage();

    void enterRandomPage(String pageName);

    void switchToNewTab();

    void pageReload();

    void pageLoaded(String pageName);

    void checkLayout(String specName);

    void clickObject(String attrName);

    void mouseOverObject(String attrName);

    void clickEnter(String attrName);

    void dragObject(String attrName);

    void fillInputField(String attrName, String inputValue);

    void fillInputFieldByList(String attrName, String inputValue);

    void uploadFileInputField(String attrName, String filePath);

    void selectOptionFieldByVisibleText(String attrName, String visibleText);

    void selectOptionValueFieldByValue(String attrName, String value);

    void selectOptionValueFieldByIndex(String attrName, int index);

    void selectOptionFieldByContainText(String attrName, String visibleContainText);

    void storeElementData(String attrName, String storableAttrData);

    void checkElementDataIsExistOnSite(String attrName, String patternAttr, String dataToBeVerified);

    void checkElementDataIsContainsOnSite(String attrName, String patternAttr, String dataToContainVerified);

    void checkStoreElementDataIsExistOnSite(String attrName, String patternAttr, String dataToContainVerified);

    void rangeElementDataBetweenGivenValues(String attrName, String patternAttr, int lowValue, int highValue);

    void selectMapPoint(String attrName, String patternAttr);

    void isDeleted(String attrName, String patternAttr, String dataToContainDeleted);

    void isStoreElementExist(String attrName, String patternAttr);

    void isResponseStatusOk(String attrName, String patternAttr);

    void teardown(String scenario) throws Exception;
}
