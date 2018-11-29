package test.tools.selenium.steps;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.tr.Diyelimki;
import cucumber.api.java.tr.Ozaman;
import cucumber.api.java.tr.Ve;
import test.tools.selenium.cucumber.tr.CucumberBaseTR;
import test.tools.selenium.cucumber.tr.CucumberBaseTRInterface;
import test.tools.selenium.report.extent.ExtentReportTestCaseFrame;

public class CucumberSteps extends ExtentReportTestCaseFrame implements CucumberBaseTRInterface {

    private CucumberBaseTRInterface cucumberBaseInterface;

    @Before
    @Override
    public void setUp(Scenario scenario) throws Exception {
        cucumberBaseInterface = new CucumberBaseTR();
        cucumberBaseInterface.setUp(scenario);
    }

    @Override
    @Diyelimki("^Anasayfaya girilir$")
    public void enterHomePage() {
        cucumberBaseInterface.enterHomePage();
    }

    @Override
    @Diyelimki("^\"(.+)\" adresine girilir$")
    public void enterRandomPage(String pageName) {
        cucumberBaseInterface.enterHomePage();
    }

    @Override
    @Ve("^Açılan yeni sekmeye geçilir$")
    public void switchToNewTab() {
        cucumberBaseInterface.switchToNewTab();
    }

    @Override
    @Ve("^Sayfa yeniden yüklenir$")
    public void pageReload() {
        cucumberBaseInterface.pageReload();
    }

    @Override
    @Ozaman("^\"(.+)\"'nın yüklendiği görülür$")
    public void pageLoaded(String pageName) {
        cucumberBaseInterface.pageLoaded(pageName);
    }

    @Override
    @Diyelimki("^\"(.+)\" formatına göre sayfa düzenine bakılır$")
    public void checkLayout(String specName) {
        cucumberBaseInterface.checkLayout(specName);
    }

    @Override
    @Diyelimki("^Sayfa üzerindeki \"(.+)\" öğesine tıklanır$")
    public void clickObject(String keyword) {
        cucumberBaseInterface.clickObject(keyword);
    }

    @Override
    @Diyelimki("^Mouse \"(.+)\" öğesinin üzerine getirilir")
    public void mouseOverObject(String keyword) {
        cucumberBaseInterface.mouseOverObject(keyword);
    }

    @Override
    @Diyelimki("^Sayfa üzerindeki \"(.+)\" alanı üzerinde klavyeden enter tuşuna basılır")
    public void clickEnter(String keyword) {

    }

    @Override
    @Diyelimki("^Sayfa üzerindeki \"(.+)\" öğesi sürüklenir")
    public void dragObject(String keyword) {
        cucumberBaseInterface.dragObject(keyword);
    }

    @Override
    @Diyelimki("^Sayfa üzerindeki metin alanları tablodaki verilerle doldurulur$")
    public void fillInputField(DataTable dataTable) {
        cucumberBaseInterface.fillInputField(dataTable);
    }

    @Override
    @Diyelimki("^Sayfa üzerindeki metin alanları önbellekteki verilerle doldurulur$")
    public void fillInputFieldByList(DataTable dataTable) {
        cucumberBaseInterface.fillInputFieldByList(dataTable);
    }

    @Override
    @Diyelimki("^Sayfa üzerindeki dosya ekleme alanlarına tablodaki dosya verileri eklenir")
    public void uploadFileInputField(DataTable dataTable) {
        cucumberBaseInterface.uploadFileInputField(dataTable);
    }

    @Override
    @Diyelimki("^Sayfa üzerindeki seçim alanından tablodaki seçenekler seçilir$")
    public void selectOptionField(DataTable dataTable) {
        cucumberBaseInterface.selectOptionField(dataTable);
    }

    @Override
    @Diyelimki("^Sayfa üzerindeki seçim alanından tablodaki verileri içeren seçenekler seçilir$")
    public void selectOptionFieldByContainText(DataTable dataTable) {
        cucumberBaseInterface.selectOptionFieldByContainText(dataTable);
    }

    @Override
    @Ve("^Sayfada yer alan web öğelerinin metin veya nitelik değerleri önbelleğe kaydedilir")
    public void storeElementData(DataTable dataTable) {
        cucumberBaseInterface.storeElementData(dataTable);
    }

    @Override
    @Ve("^Sayfada yer alan web öğelerinin metin veya nitelik değerleri, tabloda belirtilen değerle aynı olup olmadığı kontrol edilir")
    public void checkElementDataIsExistOnSite(DataTable dataTable) {
        cucumberBaseInterface.checkElementDataIsExistOnSite(dataTable);
    }

    @Override
    @Ve("^Sayfada yer alan web öğelerinin metin veya nitelik değerleri, tablodaki verileri içerip içermediği kontrol edilir")
    public void checkElementDataIsContainsOnSite(DataTable dataTable) {
        cucumberBaseInterface.checkElementDataIsContainsOnSite(dataTable);
    }

    @Override
    @Ve("^Sayfada yer alan web öğelerinin metin veya nitelik değerleri, önbellekteki değer ile aynı olup olmadığı kontrol edilir")
    public void checkStoreElementDataIsExistOnSite(DataTable dataTable) {
        cucumberBaseInterface.checkStoreElementDataIsExistOnSite(dataTable);
    }

    @Override
    @Ve("^Sayfada yer alan web öğelerinin metin veya nitelik değerleri, tabloda belirtilen aralıkta olup olmadığı kontrol edilir")
    public void rangeElementDataBetweenGivenValues(DataTable dataTable) {
        cucumberBaseInterface.rangeElementDataBetweenGivenValues(dataTable);
    }

    @Override
    @Diyelimki("^Tabloda verilen değerler ile haritadan lokasyon seçilir$")
    public void selectMapPoint(DataTable dataTable) {
        cucumberBaseInterface.selectMapPoint(dataTable);
    }

    @Override
    @Ve("^Tablodaki verilerin sayfa üzerinde olup olmadığı kontrol edilir$")
    public void isDeleted(DataTable dataTable) {
        cucumberBaseInterface.isDeleted(dataTable);
    }

    @Override
    @Ve("^Önbellekteki verilerin sayfa üzerinde olup olmadığı kontrol edilir$")
    public void isStoreElementExist(DataTable dataTable) {
        cucumberBaseInterface.isStoreElementExist(dataTable);
    }

    @Override
    @Diyelimki("^Sayfada yer alan bağlantı adresinin yanıt verip vermediği kontrol edilir$")
    public void isResponseStatusOk(DataTable dataTable) {
        cucumberBaseInterface.isResponseStatusOk(dataTable);
    }

    @After
    @Override
    public void teardown(Scenario scenario) throws Exception {
        cucumberBaseInterface.teardown(scenario);
    }
}
