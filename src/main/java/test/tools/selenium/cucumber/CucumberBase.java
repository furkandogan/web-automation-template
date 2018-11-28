package test.tools.selenium.cucumber;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.tr.Diyelimki;
import cucumber.api.java.tr.Ozaman;
import cucumber.api.java.tr.Ve;
import gherkin.formatter.model.DataTableRow;
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

public class CucumberBase extends ExtentReportTestCaseFrame implements CucumberBaseInterface {

    private WebDriver driver = null;
    private WebDriverWait wait = null;
    private ExtentTest extTest = null;
    private List<GalenTestInfo> galenTestInfos = null;
    private StoreElementProperties elementProperties = null;

    /**
     * @param scenario
     * @throws Exception
     */
    @Override
    public void setUp(Scenario scenario) throws Exception {
        driver = createWebDriver(scenario);
        wait = getWait();
        setExtentReports(createExtentsReportInstance());
        extTest = getExtentReports().createTest(scenario.getName());
        PageFactory.initElements(driver, WaitingActions.class);
        if(Boolean.valueOf(getConfigProperty(PropertyNames.GALEN_TEST_LAYOUT))){
            galenTestInfos = new LinkedList<GalenTestInfo>();
        }
    }


    /**
     * Example:
     * Diyelimki Anasayfaya girilir
     */
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

    /**
     * Example:
     * Diyelimki "http://www.google.com" adresine girilir
     */
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

    /**
     * Example:
     * Ve Açılan yeni sekmeye geçilir
     */
    @Override
    @Ve("^Açılan yeni sekmeye geçilir$")
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

    /**
     * Example:
     * Ve Sayfa yeniden yüklenir
     */
    @Override
    @Ve("^Sayfa yeniden yüklenir$")
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

    /**
     * Example:
     * O zaman "http://www.google.com" sayfasının yüklendiği görülür
     */
    @Override
    @Ozaman("^\"(.+)\"'nın yüklendiği görülür$")
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

    /**
     * Example:
     * Diyelimki "/specs/homePage.spec" formatına göre sayfa düzenine bakılır
     */
    @Override
    @Diyelimki("^\"(.+)\" formatına göre sayfa düzenine bakılır$")
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

    /**
     * Example:
     * Diyelimki Sayfa üzerindeki "keyword" öğesine tıklanır
     */
    @Override
    @Diyelimki("^Sayfa üzerindeki \"(.+)\" öğesine tıklanır$")
    public void clickObject(String keyword) {
        try {
            ClickActions clickActions = new ClickActions(driver, wait);
            clickActions.clickToElement(keyword);
            extTest.log(Status.PASS, String.format("Sayfa üzerindeki %s öğesine tıklandı", keyword));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfa üzerindeki %s öğesine tıklandı", keyword));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Example:
     * Diyelimki Mouse "keyword" öğesinin üzerine getirilir
     */
    @Override
    @Diyelimki("^Mouse \"(.+)\" öğesinin üzerine getirilir")
    public void mouseOverObject(String keyword) {
        try {
            ActionsPerform actionsPerform = new ActionsPerform(driver, wait);
            actionsPerform.moveMouse(keyword, 0);
            extTest.log(Status.PASS, String.format("Mouse %s öğesinin üzerine getirildi", keyword));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("Mouse %s öğesinin üzerine getirildi", keyword));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Example:
     * Diyelimki Sayfa üzerindeki "keyword" alanı üzerinde klavyeden enter tuşuna basılır
     */
    @Override
    @Diyelimki("^Sayfa üzerindeki \"(.+)\" alanı üzerinde klavyeden enter tuşuna basılır")
    public void clickEnter(String keyword) {
        try {
            SendKeysActions sendKeysActions = new SendKeysActions(driver, wait);
            sendKeysActions.sendKeysReturn(keyword);
            Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfa üzerindeki %s alanı üzerinde klavyeden enter tuşuna basıldı", keyword));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Example:
     * Diyelimki Sayfa üzerindeki "keyword" öğesi sürüklenir
     */
    @Override
    @Diyelimki("^Sayfa üzerindeki \"(.+)\" öğesi sürüklenir")
    public void dragObject(String keyword) {
        try {
            ActionsPerform actionsPerform = new ActionsPerform(driver, wait);
            actionsPerform.dragAndDrop(keyword);
            extTest.log(Status.PASS, String.format("Sayfa üzerindeki %s öğesi sürüklendi", keyword));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfa üzerindeki %s öğesi sürüklendi", keyword));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Example:
     * Diyelimki Sayfa üzerindeki metin alanları tablodaki verilerle doldurulur
     * | cellItem1 | cellItem2 |
     */
    @Override
    @Diyelimki("^Sayfa üzerindeki metin alanları tablodaki verilerle doldurulur$")
    public void fillInputField(DataTable dataTable) {
        for (DataTableRow dataTableRow : dataTable.getGherkinRows()) {
            String cellItem1 = dataTableRow.getCells().get(0);
            String cellItem2 = dataTableRow.getCells().get(1);
            try {
                SendKeysActions sendKeysActions = new SendKeysActions(driver, wait);
                sendKeysActions.sendKeysToElement(cellItem1, cellItem2);
                extTest.log(Status.PASS, String.format("Sayfa üzerindeki %s alanına tablodaki %s değeri girildi", cellItem1, cellItem2));
                Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfa üzerindeki %s alanına tablodaki %s değeri girildi", cellItem1, cellItem2));
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }

    /**
     * Example:
     * Diyelimki Sayfa üzerindeki metin alanları önbellekteki verilerle doldurulur
     * | cellItem1 | cellItem2 |
     */
    @Override
    @Diyelimki("^Sayfa üzerindeki metin alanları önbellekteki verilerle doldurulur$")
    public void fillInputFieldByList(DataTable dataTable) {
        for (DataTableRow dataTableRow : dataTable.getGherkinRows()) {
            String cellItem1 = dataTableRow.getCells().get(0);
            String cellItem2 = dataTableRow.getCells().get(1);
            try {
                String storeDataItem = elementProperties.getStoreElementValue(cellItem2);
                SendKeysActions sendKeysActions = new SendKeysActions(driver, wait);
                sendKeysActions.sendKeysToElement(cellItem1, storeDataItem);
                extTest.log(Status.PASS, String.format("Sayfa üzerindeki %s alanına önbellekteki %s değeri girildi", cellItem1, storeDataItem));
                Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfa üzerindeki %s alanına önbellekteki %s değeri girildi", cellItem1, storeDataItem));
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }

    /**
     * Example:
     * Diyelimki Sayfa üzerindeki dosya ekleme alanlarına tablodaki dosya verileri eklenir
     * | cellItem1 | cellItem2 |
     */
    @Override
    @Diyelimki("^Sayfa üzerindeki dosya ekleme alanlarına tablodaki dosya verileri eklenir")
    public void uploadFileInputField(DataTable dataTable) {
        for (DataTableRow dataTableRow : dataTable.getGherkinRows()) {
            String cellItem1 = dataTableRow.getCells().get(0);
            String cellItem2 = dataTableRow.getCells().get(1);
            try {
                SendKeysActions sendKeysActions = new SendKeysActions(driver, wait);
                sendKeysActions.uploadFile(cellItem1, cellItem2);
                extTest.log(Status.PASS, String.format("Sayfa üzerindeki %s alanına tablodaki %s dosyası eklendi", cellItem1, cellItem2));
                Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfa üzerindeki %s alanına tablodaki %s dosyası eklendi", cellItem1, cellItem2));
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }

    /**
     * Example:
     * Diyelimki Sayfa üzerindeki seçim alanından tablodaki seçenekler seçilir
     * | cellItem1 | cellItem2 |
     */
    @Override
    @Diyelimki("^Sayfa üzerindeki seçim alanından tablodaki seçenekler seçilir$")
    public void selectOptionField(DataTable dataTable) {
        for (DataTableRow dataTableRow : dataTable.getGherkinRows()) {
            String cellItem1 = dataTableRow.getCells().get(0);
            String cellItem2 = dataTableRow.getCells().get(1);
            try {
                SelectActions selectActions = new SelectActions(driver, wait);
                selectActions.selectElementByVisibleText(cellItem1, cellItem2);
                extTest.log(Status.PASS, String.format("Sayfa üzerindeki %s alanından tablodaki %s değeri seçildi", cellItem1, cellItem2));
                Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfa üzerindeki %s alanından tablodaki %s değeri seçildi", cellItem1, cellItem2));
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }

    /**
     * Example:
     * Diyelimki Sayfa üzerindeki seçim alanından tablodaki verileri içeren seçenekler seçilir
     * | cellItem1 | cellItem2 |
     */
    @Override
    @Diyelimki("^Sayfa üzerindeki seçim alanından tablodaki verileri içeren seçenekler seçilir$")
    public void selectOptionFieldByContainText(DataTable dataTable) {
        for (DataTableRow dataTableRow : dataTable.getGherkinRows()) {
            String cellItem1 = dataTableRow.getCells().get(0);
            String cellItem2 = dataTableRow.getCells().get(1);
            try {
                SelectActions selectActions = new SelectActions(driver, wait);
                selectActions.selectByVisibleContainText(cellItem1, cellItem2);
                extTest.log(Status.PASS, String.format("Sayfa üzerindeki %s alanından tablodaki %s değerini içeren değer seçildi", cellItem1, cellItem2));
                Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfa üzerindeki %s alanından tablodaki %s değerini içeren değer seçildi", cellItem1, cellItem2));
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }

    /**
     * Example:
     * Ve Sayfada yer alan web öğelerinin metin veya nitelik değerleri önbelleğe kaydedilir
     * | cellItem1 | cellItem2 |
     */
    @Override
    @Ve("^Sayfada yer alan web öğelerinin metin veya nitelik değerleri önbelleğe kaydedilir")
    public void storeElementData(DataTable dataTable) {
        for (DataTableRow dataTableRow : dataTable.getGherkinRows()) {
            elementProperties = new StoreElementProperties(driver, wait);
            String cellItem1 = dataTableRow.getCells().get(0);
            String cellItem2 = dataTableRow.getCells().get(1);
            try {
                if (cellItem2.equalsIgnoreCase("text")) {
                    elementProperties.storeElementData(cellItem1);
                } else {
                    elementProperties.storeElementData(cellItem1, cellItem2);
                }
                extTest.log(Status.PASS, String.format("Sayfada yer alan %s elementinin %s değeri önbelleğe kaydedildi", cellItem1, cellItem2));
                Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfada yer alan %s elementinin %s değeri önbelleğe kaydedildi", cellItem1, cellItem2));
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }

    /**
     * Example:
     * Ve Sayfada yer alan web öğelerinin metin veya nitelik değerleri, tablodaki değerle aynı olup olmadığı kontrol edilir
     * | cellItem1 | cellItem2 | cellItem3 |
     */
    @Override
    @Ve("^Sayfada yer alan web öğelerinin metin veya nitelik değerleri, tabloda belirtilen değerle aynı olup olmadığı kontrol edilir")
    public void checkElementDataIsExistOnSite(DataTable dataTable) {
        for (DataTableRow dataTableRow : dataTable.getGherkinRows()) {
            PresenceOfQualification presence = new PresenceOfQualification(driver, wait);
            String cellItem1 = dataTableRow.getCells().get(0);
            String cellItem2 = dataTableRow.getCells().get(1);
            String cellItem3 = dataTableRow.getCells().get(2).trim().toLowerCase();
            try {
                if (cellItem2.equalsIgnoreCase("text")) {
                    Assert.assertTrue(presence.isTextEquals(cellItem1, cellItem3));
                } else {
                    presence.isAttrValueEquals(cellItem1, cellItem2, cellItem3);
                }
                extTest.log(Status.PASS, String.format("Sayfada yer alan %s elementinin %s değeri, tablodaki %s değeri ile aynı olduğu görüldü", cellItem1, cellItem2, cellItem3));
                Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfada yer alan %s elementinin %s değeri, tablodaki %s değeri ile aynı olduğu görüldü", cellItem1, cellItem2, cellItem3));
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }


    /**
     * Example:
     * Ve Sayfada yer alan web öğelerinin metin veya nitelik değerleri, tablodaki verileri içerip içermediği kontrol edilir
     * | cellItem1 | cellItem2 | cellItem3 |
     */
    @Override
    @Ve("^Sayfada yer alan web öğelerinin metin veya nitelik değerleri, tablodaki verileri içerip içermediği kontrol edilir")
    public void checkElementDataIsContainsOnSite(DataTable dataTable) {
        for (DataTableRow dataTableRow : dataTable.getGherkinRows()) {
            String cellItem1 = dataTableRow.getCells().get(0);
            String cellItem2 = dataTableRow.getCells().get(1);
            String cellItem3 = dataTableRow.getCells().get(2).trim().toLowerCase();
            try {
                PresenceOfQualification presence = new PresenceOfQualification(driver, wait);
                if (cellItem2.equalsIgnoreCase("text")) {
                    Assert.assertTrue(presence.isTextContains(cellItem1, cellItem3));
                } else {
                    Assert.assertTrue(presence.isAttrValueContains(cellItem1, cellItem2, cellItem3));
                }
                extTest.log(Status.PASS, String.format("Sayfada yer alan %s elementinin %s değeri, tablodaki %s değerini içerdiği görüldü", cellItem1, cellItem2, cellItem3));
                Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfada yer alan %s elementinin %s değeri, tablodaki %s değerini içerdiği görüldü", cellItem1, cellItem2, cellItem3));
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }

    /**
     * Example:
     * Ve Sayfada yer alan web öğelerinin metin veya nitelik değerleri, önbellekteki değer ile aynı olup olmadığı kontrol edilir
     * | cellItem1 | cellItem2 | cellItem3 |
     */
    @Override
    @Ve("^Sayfada yer alan web öğelerinin metin veya nitelik değerleri, önbellekteki değer ile aynı olup olmadığı kontrol edilir")
    public void checkStoreElementDataIsExistOnSite(DataTable dataTable) {
        for (DataTableRow dataTableRow : dataTable.getGherkinRows()) {
            String cellItem1 = dataTableRow.getCells().get(0);
            String cellItem2 = dataTableRow.getCells().get(1);
            String cellItem3 = dataTableRow.getCells().get(2);
            try {
                String storeDataItem = elementProperties.getStoreElementValue(cellItem3);
                PresenceOfQualification presence = new PresenceOfQualification(driver, wait);
                if (cellItem2.equalsIgnoreCase("text")) {
                    Assert.assertTrue(presence.isTextEquals(cellItem1, storeDataItem));
                } else {
                    Assert.assertTrue(presence.isAttrValueEquals(cellItem1, cellItem2, storeDataItem));
                }
                extTest.log(Status.PASS, String.format("Sayfada yer alan %s elementinin %s değeri, önbelleğe kaydedilen %s elementinin %s değeri ile aynı olduğu görüldü", cellItem1, cellItem2, cellItem3, cellItem2));
                Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfada yer alan %s elementinin %s değeri, önbelleğe kaydedilen %s elementinin %s değeri ile aynı olduğu görüldü", cellItem1, cellItem2, cellItem3, cellItem2));
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }

    /**
     * Example:
     * Ve Sayfada yer alan web öğelerinin metin veya nitelik değerleri, tabloda belirtilen aralıkta olup olmadığı kontrol edilir
     * | cellItem1 | cellItem2 | cellItem3 | cellItem4 |
     */
    @Override
    @Ve("^Sayfada yer alan web öğelerinin metin veya nitelik değerleri, tabloda belirtilen aralıkta olup olmadığı kontrol edilir")
    public void rangeElementDataBetweenGivenValues(DataTable dataTable) {
        for (DataTableRow dataTableRow : dataTable.getGherkinRows()) {
            String cellItem1 = dataTableRow.getCells().get(0);
            String cellItem2 = dataTableRow.getCells().get(1);
            int cellItem3 = Integer.parseInt(dataTableRow.getCells().get(2));
            int cellItem4 = Integer.parseInt(dataTableRow.getCells().get(3));
            try {
                PresenceOfQualification presence = new PresenceOfQualification(driver, wait);
                if (cellItem2.equalsIgnoreCase("text")) {
                    Assert.assertTrue(presence.rangeText(cellItem1, cellItem3, cellItem4));
                } else {
                    Assert.assertTrue(presence.rangeAttrValue(cellItem1, cellItem2, cellItem3, cellItem4));
                }
                extTest.log(Status.PASS, String.format("Sayfada yer alan %s elementinin %s değeri, %s ve %s aralığında olduğu görüldü", cellItem1, cellItem2, cellItem3, cellItem4));
                Cookie cookie = new Cookie("zaleniumMessage", String.format("Sayfada yer alan %s elementinin %s değeri, %s ve %s aralığında olduğu görüldü", cellItem1, cellItem2, cellItem3, cellItem4));
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }


    /**
     * Example:
     * Diyelimki Tabloda verilen değerler ile haritadan lokasyon seçilir
     * | cellItem1 | cellItem2 |
     */
    @Override
    @Diyelimki("^Tabloda verilen değerler ile haritadan lokasyon seçilir$")
    public void selectMapPoint(DataTable dataTable) {
        for (DataTableRow dataTableRow : dataTable.getGherkinRows()) {
            String cellItem1 = dataTableRow.getCells().get(0);
            String cellItem2 = dataTableRow.getCells().get(1);
            try {
                SelectActions selectActions = new SelectActions(driver, wait);
                selectActions.selectMapPoint(cellItem1, cellItem2);
                extTest.log(Status.PASS, "Haritadan lokasyon seçildi");
                Cookie cookie = new Cookie("zaleniumMessage", "Haritadan lokasyon seçildi");
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }

    /**
     * Example:
     * Ve Tablodaki verilerin sayfa üzerinde olup olmadığı kontrol edilir
     * | cellItem1 | cellItem2 | cellItem3 |
     */
    @Override
    @Ve("^Tablodaki verilerin sayfa üzerinde olup olmadığı kontrol edilir$")
    public void isDeleted(DataTable dataTable) {
        for (DataTableRow dataTableRow : dataTable.getGherkinRows()) {
            String cellItem1 = dataTableRow.getCells().get(0);
            String cellItem2 = dataTableRow.getCells().get(1);
            String cellItem3 = dataTableRow.getCells().get(2).trim().toLowerCase();
            try {
                PresenceOfQualification presence = new PresenceOfQualification(driver, wait);
                boolean isElementPresent = presence.isElementExists(cellItem1);
                Assert.assertFalse(isElementPresent);
                if (isElementPresent == true) {
                    if (cellItem2.equalsIgnoreCase("text")) {
                        Assert.assertTrue(presence.isTextEquals(cellItem1, cellItem3));
                    } else {
                        Assert.assertTrue(presence.isAttrValueEquals(cellItem1, cellItem2, cellItem3));
                    }
                }
                extTest.log(Status.PASS, String.format("%s elementinin sayfa üzerinde varolmadığı görüldü", cellItem1));
                Cookie cookie = new Cookie("zaleniumMessage", String.format("%s elementinin sayfa üzerinde varolmadığı görüldü", cellItem1));
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }

    /**
     * Example:
     * Diyelimki Önbellekteki verilerin sayfa üzerinde olup olmadığı kontrol edilir
     * | cellItem1 | cellItem2 |
     */
    @Override
    @Ve("^Önbellekteki verilerin sayfa üzerinde olup olmadığı kontrol edilir$")
    public void isStoreElementExist(DataTable dataTable) {
        for (DataTableRow dataTableRow : dataTable.getGherkinRows()) {
            String cellItem1 = dataTableRow.getCells().get(0);
            String cellItem2 = dataTableRow.getCells().get(1);
            try {
                PresenceOfQualification presence = new PresenceOfQualification(driver, wait);
                boolean isElementPresent = presence.isElementExists(cellItem1);
                if (isElementPresent == true) {
                    String storeDataItem = elementProperties.getStoreElementValue(cellItem1);
                    if (cellItem2.equalsIgnoreCase("text")) {
                        Assert.assertTrue(presence.isTextEquals(cellItem1, storeDataItem));
                    } else {
                        Assert.assertTrue(presence.isAttrValueEquals(cellItem1, cellItem2, storeDataItem));
                    }
                } else {
                    Assert.assertFalse(isElementPresent);
                }
                extTest.log(Status.PASS, String.format("%s elementinin sayfa üzerinde varolmadığı görüldü", cellItem1));
                Cookie cookie = new Cookie("zaleniumMessage", String.format("%s elementinin sayfa üzerinde varolmadığı görüldü", cellItem1));
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }

    /**
     * Example:
     * Diyelimki Sayfada yer alan bağlantı adresinin yanıt verdiği kontrol edilir
     * | cellItem1 | cellItem2 |
     */
    @Override
    @Diyelimki("^Sayfada yer alan bağlantı adresinin yanıt verip vermediği kontrol edilir$")
    public void isResponseStatusOk(DataTable dataTable) {
        for (DataTableRow dataTableRow : dataTable.getGherkinRows()) {
            String cellItem1 = dataTableRow.getCells().get(0);
            String cellItem2 = dataTableRow.getCells().get(0);
            try {
                PresenceOfQualification presence = new PresenceOfQualification(driver, wait);
                GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
                Assert.assertTrue(presence.linkExists(getElementProperties.getAttribute(cellItem1, cellItem2)));
                extTest.log(Status.PASS, "Sayfada yer alan bağlantı adresinin yanıt verdiği görüldü");
                Cookie cookie = new Cookie("zaleniumMessage", "Sayfada yer alan bağlantı adresinin yanıt verdiği görüldü");
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }

    /**
     * @param scenario
     * @throws Exception
     */
    @Override
    public void teardown(Scenario scenario) throws Exception {
        if(Boolean.valueOf(getConfigProperty(PropertyNames.GALEN_TEST_LAYOUT))){
            new HtmlReportBuilder().build(galenTestInfos,
                    "target/galen-html-reports");
        }
        if (scenario.isFailed()) {
            if (extTest != null) {
                extTest.log(Status.FAIL, scenario.getName() + " senaryosu hata almıştır");
                logScreenCapture(extTest, scenario.getName());
                cleanUp(getExtentReports(), extTest);
            }
            Cookie cookie = new Cookie("zaleniumTestPassed", "false");
            driver.manage().addCookie(cookie);
            cleanUpWebDriver(driver);
        } else {
            if (extTest != null) {
                extTest.log(Status.PASS, scenario.getName() + " senaryosu başarılı olmuştur");
                logScreenCapture(extTest, scenario.getName());
                cleanUp(getExtentReports(), extTest);
            }
            Cookie cookie = new Cookie("zaleniumTestPassed", "true");
            driver.manage().addCookie(cookie);
            cleanUpWebDriver(driver);
        }
    }

}