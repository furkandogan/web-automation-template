package test.tools.selenium.junit;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import org.junit.Assert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.tools.selenium.config.PropertyNames;
import test.tools.selenium.interactions.*;
import test.tools.selenium.report.extent.ExtentReportTestCaseFrame;

import java.util.LinkedList;
import java.util.List;

public class SeleniumBaseTR extends ExtentReportTestCaseFrame implements SeleniumBaseInterface {

    private WebDriver driver = null;
    private WebDriverWait wait = null;
    private ExtentTest extTest = null;
    private List<GalenTestInfo> galenTestInfos = null;
    private StoreElementProperties elementProperties = null;

    @Override
    public void setUp(String scenario) throws Exception {
        driver = createWebDriver(scenario);
        wait = getWait();
        setExtentReports(createExtentsReportInstance());
        extTest = getExtentReports().createTest(scenario);
        PageFactory.initElements(driver, WaitingActions.class);
        if (Boolean.valueOf(getConfigProperty(PropertyNames.GALEN_TEST_LAYOUT))) {
            galenTestInfos = new LinkedList<GalenTestInfo>();
        }
    }

    @Override
    public void enterHomePage() {
        try {
            openStartPage();
            extTest.log(Status.PASS, "Anasayfanın yüklendiği görüldü");
            Cookie cookie = new Cookie("zaleniumMessage", "Anasayfanın yüklendiği görüldü");
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void enterRandomPage(String pageName) {
        try {
            BaseUtil baseUtil = new BaseUtil(driver, wait);
            baseUtil.openCustomUrlPage(pageName);
            extTest.log(Status.PASS, String.format("%s adresine girildi", pageName));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("%s adresine girildi", pageName));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void switchToNewTab() {
        try {
            BaseUtil baseUtil = new BaseUtil(driver, wait);
            baseUtil.changeTab();
            extTest.log(Status.PASS, "Açılan yeni sekmeye geçildi");
            Cookie cookie = new Cookie("zaleniumMessage", "Açılan yeni sekmeye geçildi");
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void pageReload() {
        try {
            BaseUtil baseUtil = new BaseUtil(driver, wait);
            baseUtil.pageRefresh();
            extTest.log(Status.PASS, "Sayfa yeniden yüklendi");
            Cookie cookie = new Cookie("zaleniumMessage", "Sayfa yeniden yüklendi");
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void pageLoaded(String pageName) {
        try {
            PresenceOfQualification presence = new PresenceOfQualification(driver, wait);
            presence.isPageLoaded(pageName);
            extTest.log(Status.PASS, String.format("%s'nın yüklendiği görüldü", pageName));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("%s'nın yüklendiği görüldü", pageName));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void checkLayout(String specName) {
        try {
            GalenTestLayout galenTestLayout = new GalenTestLayout(driver);
            galenTestInfos.add(galenTestLayout.testLayout(specName));
            extTest.log(Status.PASS, String.format("%s formatına göre sayfa düzeni kontrol edildi", specName));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("%s formatına göre sayfa düzenine bakıldı", specName));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void clickObject(String attrName) {
        try {
            ClickActions clickActions = new ClickActions(driver, wait);
            clickActions.clickToElement(attrName);
            extTest.log(Status.PASS, String.format("Sayfa üzerindeki %s öğesine tıklandı", attrName));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfa üzerindeki %s öğesine tıklandı", attrName));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void mouseOverObject(String attrName) {
        try {
            ActionsPerform actionsPerform = new ActionsPerform(driver, wait);
            actionsPerform.moveMouse(attrName, 0);
            extTest.log(Status.PASS, String.format("Mouse %s öğesinin üzerine getirildi", attrName));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("Mouse %s öğesinin üzerine getirildi", attrName));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void clickEnter(String attrName) {
        try {
            SendKeysActions sendKeysActions = new SendKeysActions(driver, wait);
            sendKeysActions.sendKeysReturn(attrName);
            Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfa üzerindeki %s alanı üzerinde klavyeden enter tuşuna basıldı", attrName));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void dragObject(String attrName) {
        try {
            ActionsPerform actionsPerform = new ActionsPerform(driver, wait);
            actionsPerform.dragAndDrop(attrName);
            extTest.log(Status.PASS, String.format("Sayfa üzerindeki %s öğesi sürüklendi", attrName));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfa üzerindeki %s öğesi sürüklendi", attrName));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void fillInputField(String attrName, String inputValue) {
        try {
            SendKeysActions sendKeysActions = new SendKeysActions(driver, wait);
            sendKeysActions.sendKeysToElement(attrName, inputValue);
            extTest.log(Status.PASS, String.format("Sayfa üzerindeki %s alanına %s değeri girildi", attrName, inputValue));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfa üzerindeki %s alanına tablodaki %s değeri girildi", attrName, inputValue));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void fillInputFieldByList(String attrName, String inputValue) {
        try {
            String storeDataItem = elementProperties.getStoreElementValue(inputValue);
            SendKeysActions sendKeysActions = new SendKeysActions(driver, wait);
            sendKeysActions.sendKeysToElement(attrName, storeDataItem);
            extTest.log(Status.PASS, String.format("Sayfa üzerindeki %s alanına önbellekteki %s değeri girildi", attrName, storeDataItem));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfa üzerindeki %s alanına önbellekteki %s değeri girildi", attrName, storeDataItem));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void uploadFileInputField(String attrName, String filePath) {
        try {
            SendKeysActions sendKeysActions = new SendKeysActions(driver, wait);
            sendKeysActions.uploadFile(attrName, filePath);
            extTest.log(Status.PASS, String.format("Sayfa üzerindeki %s alanına %s dosyası eklendi", attrName, filePath));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfa üzerindeki %s alanına %s dosyası eklendi", attrName, filePath));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void selectOptionFieldByVisibleText(String attrName, String visibleText) {
        try {
            SelectActions selectActions = new SelectActions(driver, wait);
            selectActions.selectElementByVisibleText(attrName, visibleText);
            extTest.log(Status.PASS, String.format("Sayfa üzerindeki %s alanından %s değeri seçildi", attrName, visibleText));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfa üzerindeki %s alanından %s değeri seçildi", attrName, visibleText));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void selectOptionValueFieldByValue(String attrName, String value) {
        try {
            SelectActions selectActions = new SelectActions(driver, wait);
            selectActions.selectElementByValue(attrName, value);
            extTest.log(Status.PASS, String.format("Sayfa üzerindeki %s alanından %s değeri seçildi", attrName, value));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfa üzerindeki %s alanından %s değeri seçildi", attrName, value));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void selectOptionValueFieldByIndex(String attrName, int index) {
        try {
            SelectActions selectActions = new SelectActions(driver, wait);
            selectActions.selectElementByIndex(attrName, index);
            extTest.log(Status.PASS, String.format("Sayfa üzerindeki %s alanından %s değeri seçildi", attrName, index));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfa üzerindeki %s alanından %s değeri seçildi", attrName, index));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void selectOptionFieldByContainText(String attrName, String visibleContainText) {
        try {
            SelectActions selectActions = new SelectActions(driver, wait);
            selectActions.selectByVisibleContainText(attrName, visibleContainText);
            extTest.log(Status.PASS, String.format("Sayfa üzerindeki %s alanından %s değerini içeren değer seçildi", attrName, visibleContainText));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfa üzerindeki %s alanından %s değerini içeren değer seçildi", attrName, visibleContainText));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void storeElementData(String attrName, String storableAttrData) {
        elementProperties = new StoreElementProperties(driver, wait);
        try {
            if (storableAttrData.equalsIgnoreCase("text")) {
                elementProperties.storeElementData(attrName);
            } else {
                elementProperties.storeElementData(attrName, storableAttrData);
            }
            extTest.log(Status.PASS, String.format("Sayfada yer alan %s elementinin %s değeri önbelleğe kaydedildi", attrName, storableAttrData));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfada yer alan %s elementinin %s değeri önbelleğe kaydedildi", attrName, storableAttrData));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void checkElementDataIsExistOnSite(String attrName, String patternAttr, String dataToBeVerified) {
        PresenceOfQualification presence = new PresenceOfQualification(driver, wait);
        try {
            if (patternAttr.equalsIgnoreCase("text")) {
                Assert.assertTrue(presence.isTextEquals(attrName, dataToBeVerified.trim().toLowerCase()));
            } else {
                Assert.assertTrue(presence.isAttrValueEquals(attrName, patternAttr, dataToBeVerified.trim().toLowerCase()));
            }
            extTest.log(Status.PASS, String.format("Sayfada yer alan %s elementinin %s değeri, %s değeri ile aynı olduğu görüldü", attrName, patternAttr, dataToBeVerified));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfada yer alan %s elementinin %s değeri, %s değeri ile aynı olduğu görüldü", attrName, patternAttr, dataToBeVerified));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }


    @Override
    public void checkElementDataIsContainsOnSite(String attrName, String patternAttr, String dataToContainVerified) {
        try {
            PresenceOfQualification presence = new PresenceOfQualification(driver, wait);
            if (patternAttr.equalsIgnoreCase("text")) {
                Assert.assertTrue(presence.isTextContains(attrName, dataToContainVerified.trim().toLowerCase()));
            } else {
                Assert.assertTrue(presence.isAttrValueContains(attrName, patternAttr, dataToContainVerified.trim().toLowerCase()));
            }
            extTest.log(Status.PASS, String.format("Sayfada yer alan %s elementinin %s değeri, %s değerini içerdiği görüldü", attrName, patternAttr, dataToContainVerified));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfada yer alan %s elementinin %s değeri, %s değerini içerdiği görüldü", attrName, patternAttr, dataToContainVerified));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void checkStoreElementDataIsExistOnSite(String attrName, String patternAttr, String dataToContainVerified) {
        try {
            String storeDataItem = elementProperties.getStoreElementValue(dataToContainVerified);
            PresenceOfQualification presence = new PresenceOfQualification(driver, wait);
            if (patternAttr.equalsIgnoreCase("text")) {
                Assert.assertTrue(presence.isTextEquals(attrName, storeDataItem));
            } else {
                Assert.assertTrue(presence.isAttrValueEquals(attrName, patternAttr, storeDataItem));
            }
            extTest.log(Status.PASS, String.format("Sayfada yer alan %s elementinin %s değeri, önbelleğe kaydedilen %s elementinin %s değeri ile aynı olduğu görüldü", attrName, patternAttr, dataToContainVerified, patternAttr));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfada yer alan %s elementinin %s değeri, önbelleğe kaydedilen %s elementinin %s değeri ile aynı olduğu görüldü", attrName, patternAttr, dataToContainVerified, patternAttr));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void rangeElementDataBetweenGivenValues(String attrName, String patternAttr, int lowValue, int highValue) {
        try {
            PresenceOfQualification presence = new PresenceOfQualification(driver, wait);
            if (patternAttr.equalsIgnoreCase("text")) {
                Assert.assertTrue(presence.rangeText(attrName, lowValue, highValue));
            } else {
                Assert.assertTrue(presence.rangeAttrValue(attrName, patternAttr, lowValue, highValue));
            }
            extTest.log(Status.PASS, String.format("Sayfada yer alan %s elementinin %s değeri, %s ve %s aralığında olduğu görüldü", attrName, patternAttr, lowValue, highValue));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfada yer alan %s elementinin %s değeri, %s ve %s aralığında olduğu görüldü", attrName, patternAttr, lowValue, highValue));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void selectMapPoint(String attrName, String patternAttr) {
        try {
            SelectActions selectActions = new SelectActions(driver, wait);
            selectActions.selectMapPoint(attrName, patternAttr);
            extTest.log(Status.PASS, "Haritadan lokasyon seçildi");
            Cookie cookie = new Cookie("zaleniumMessage", "Haritadan lokasyon seçildi");
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void isDeleted(String attrName, String patternAttr, String dataToContainDeleted) {
        try {
            PresenceOfQualification presence = new PresenceOfQualification(driver, wait);
            boolean isElementPresent = presence.isElementExists(attrName);
            Assert.assertFalse(isElementPresent);
            if (isElementPresent == true) {
                if (patternAttr.equalsIgnoreCase("text")) {
                    Assert.assertTrue(presence.isTextEquals(attrName, dataToContainDeleted));
                } else {
                    Assert.assertTrue(presence.isAttrValueEquals(attrName, patternAttr, dataToContainDeleted));
                }
            }
            extTest.log(Status.PASS, String.format("%s elementinin sayfa üzerinde varolmadığı görüldü", attrName));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("%s elementinin sayfa üzerinde varolmadığı görüldü", attrName));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void isStoreElementExist(String attrName, String patternAttr) {
        try {
            PresenceOfQualification presence = new PresenceOfQualification(driver, wait);
            boolean isElementPresent = presence.isElementExists(attrName);
            if (isElementPresent == true) {
                String storeDataItem = elementProperties.getStoreElementValue(attrName);
                if (patternAttr.equalsIgnoreCase("text")) {
                    Assert.assertTrue(presence.isTextEquals(attrName, storeDataItem));
                } else {
                    Assert.assertTrue(presence.isAttrValueEquals(attrName, patternAttr, storeDataItem));
                }
            } else {
                Assert.assertFalse(isElementPresent);
            }
            extTest.log(Status.PASS, String.format("%s elementinin sayfa üzerinde varolmadığı görüldü", attrName));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("%s elementinin sayfa üzerinde varolmadığı görüldü", attrName));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void isResponseStatusOk(String attrName, String patternAttr) {
        try {
            PresenceOfQualification presence = new PresenceOfQualification(driver, wait);
            GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
            Assert.assertTrue(presence.linkExists(getElementProperties.getAttribute(attrName, patternAttr)));
            extTest.log(Status.PASS, "Sayfada yer alan bağlantı adresinin yanıt verdiği görüldü");
            Cookie cookie = new Cookie("zaleniumMessage", "Sayfada yer alan bağlantı adresinin yanıt verdiği görüldü");
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void teardown(String scenario) throws Exception {
        if (Boolean.valueOf(getConfigProperty(PropertyNames.GALEN_TEST_LAYOUT))) {
            new HtmlReportBuilder().build(galenTestInfos,
                    "target/galen-html-reports");
        }
        if (extTest != null) {
            boolean scenarioIsFailed = extTest.getStatus().equals(Status.PASS);
            if (scenarioIsFailed == false) {
                extTest.log(Status.FAIL, scenario + " senaryosu hata almıştır");
                logScreenCapture(extTest, scenario);
                cleanUp(getExtentReports(), extTest);
                Cookie cookie = new Cookie("zaleniumTestPassed", "false");
                driver.manage().addCookie(cookie);
                cleanUpWebDriver(driver);
            } else {
                extTest.log(Status.PASS, scenario + " senaryosu başarılı olmuştur");
                logScreenCapture(extTest, scenario);
                cleanUp(getExtentReports(), extTest);
                Cookie cookie = new Cookie("zaleniumTestPassed", "true");
                driver.manage().addCookie(cookie);
                cleanUpWebDriver(driver);
            }
        }
    }
}
