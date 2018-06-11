package test.tools.selenium.steps;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.tr.Diyelimki;
import cucumber.api.java.tr.Ozaman;
import cucumber.api.java.tr.Ve;
import test.tools.selenium.report.extent.ExtentReportTestCaseFrame;
import test.tools.selenium.stepsdefinition.CucumberBase;
import test.tools.selenium.stepsdefinition.CucumberBaseInterface;

public class CucumberSteps extends ExtentReportTestCaseFrame implements CucumberBaseInterface {

    private CucumberBase cucumberBase;

    @Before
    @Override
    public void setUp(Scenario scenario) throws Exception {
        cucumberBase = new CucumberBase();
        cucumberBase.setUp(scenario);
    }

    @Override
    @Diyelimki("^Anasayfaya girilir$")
    public void enterHomePage() {
        cucumberBase.enterHomePage();
    }

    @Override
    @Diyelimki("^\"(.+)\" adresine girilir$")
    public void enterRandomPage(String pageName) {
        cucumberBase.enterHomePage();
    }

    @Override
    @Ve("^Açılan yeni sekmeye geçilir$")
    public void switchToNewTab() {
        cucumberBase.switchToNewTab();
    }

    @Override
    @Ve("^Sayfa yeniden yüklenir$")
    public void pageReload() {
        cucumberBase.pageReload();
    }

    @Override
    @Ozaman("^\"(.+)\" sayfasının yüklendiği görülür$")
    public void pageLoaded(String pageName) {
        cucumberBase.pageLoaded(pageName);
    }

    @Override
    @Diyelimki("^\"(.+)\" formatına göre sayfa düzenine bakılır$")
    public void checkLayout(String specName) {
        cucumberBase.checkLayout(specName);
    }

    @Override
    @Diyelimki("^Sayfa üzerindeki \"(.+)\" öğesine tıklanır$")
    public void clickObject(String keyword) {
        cucumberBase.clickObject(keyword);
    }

    @Override
    @Diyelimki("^Mouse \"(.+)\" öğesinin üzerine getirilir")
    public void mouseOverObject(String keyword) {
        cucumberBase.mouseOverObject(keyword);
    }

    @Override
    @Diyelimki("^Sayfa üzerindeki \"(.+)\" alanı üzerinde klavyeden enter tuşuna basılır")
    public void clickEnter(String keyword) {

    }

    @Override
    @Diyelimki("^Sayfa üzerindeki \"(.+)\" öğesi sürüklenir")
    public void dragObject(String keyword) {
        cucumberBase.dragObject(keyword);
    }

    @Override
    @Diyelimki("^Sayfa üzerindeki metin alanları tablodaki verilerle doldurulur$")
    public void fillInputField(DataTable dataTable) {
        cucumberBase.fillInputField(dataTable);
    }

    @Override
    @Diyelimki("^Sayfa üzerindeki metin alanları önbellekteki verilerle doldurulur$")
    public void fillInputFieldByList(DataTable dataTable) {
        cucumberBase.fillInputFieldByList(dataTable);
    }

    @Override
    @Diyelimki("^Sayfa üzerindeki seçim alanından tablodaki seçenekler seçilir$")
    public void selectOptionField(DataTable dataTable) {
        cucumberBase.selectOptionField(dataTable);
    }

    @Override
    @Diyelimki("^Sayfa üzerindeki seçim alanından tablodaki verileri içeren seçenekler seçilir$")
    public void selectOptionFieldByContainText(DataTable dataTable) {
        cucumberBase.selectOptionFieldByContainText(dataTable);
    }

    @Override
    @Ve("^Sayfada yer alan web öğelerinin metin veya nitelik değerleri önbelleğe kaydedilir")
    public void storeElementData(DataTable dataTable) {
        cucumberBase.storeElementData(dataTable);
    }

    @Override
    @Ve("^Sayfada yer alan web öğelerinin metin veya nitelik değerleri, tabloda belirtilen değerle aynı olup olmadığı kontrol edilir")
    public void checkElementDataIsExistOnSite(DataTable dataTable) {
        cucumberBase.checkElementDataIsExistOnSite(dataTable);
    }

    @Override
    @Ve("^Sayfada yer alan web öğelerinin metin veya nitelik değerleri, tablodaki verileri içerip içermediği kontrol edilir")
    public void checkElementDataIsContainsOnSite(DataTable dataTable) {
        cucumberBase.checkElementDataIsContainsOnSite(dataTable);
    }

    @Override
    @Ve("^Sayfada yer alan web öğelerinin metin veya nitelik değerleri, önbellekteki değer ile aynı olup olmadığı kontrol edilir")
    public void checkStoreElementDataIsExistOnSite(DataTable dataTable) {
        cucumberBase.checkStoreElementDataIsExistOnSite(dataTable);
    }

    @Override
    @Ve("^Sayfada yer alan web öğelerinin metin veya nitelik değerleri, tabloda belirtilen aralıkta olup olmadığı kontrol edilir")
    public void rangeElementDataBetweenGivenValues(DataTable dataTable) {
        cucumberBase.rangeElementDataBetweenGivenValues(dataTable);
    }

    @Override
    @Diyelimki("^Tabloda verilen değerler ile haritadan lokasyon seçilir$")
    public void selectMapPoint(DataTable dataTable) {
        cucumberBase.selectMapPoint(dataTable);
    }

    @Override
    @Ve("^Tablodaki verilerin sayfa üzerinde olup olmadığı kontrol edilir$")
    public void isDeleted(DataTable dataTable) {
        cucumberBase.isDeleted(dataTable);
    }

    @Override
    @Ve("^Önbellekteki verilerin sayfa üzerinde olup olmadığı kontrol edilir$")
    public void isStoreElementExist(DataTable dataTable) {
        cucumberBase.isStoreElementExist(dataTable);
    }

    @Override
    @Diyelimki("^Sayfada yer alan bağlantı adresinin yanıt verip vermediği kontrol edilir$")
    public void isResponseStatusOk(DataTable dataTable) {
        cucumberBase.isResponseStatusOk(dataTable);
    }

    @After
    @Override
    public void teardown(Scenario scenario) throws Exception {
        cucumberBase.teardown(scenario);
    }
}
