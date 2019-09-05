package test.tools.selenium.cucumber.TR;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.tr.Diyelimki;
import cucumber.api.java.tr.Ozaman;
import cucumber.api.java.tr.Ve;

public interface CucumberBaseTRInterface {
    void setUp(Scenario scenario) throws Exception;

    @Diyelimki("^Anasayfaya girilir$")
    void enterHomePage();

    @Diyelimki("^\"(.+)\" adresine girilir$")
    void enterRandomPage(String pageName);

    @Ve("^Açılan yeni sekmeye geçilir$")
    void switchToNewTab();

    @Ve("^Sayfa yeniden yüklenir$")
    void pageReload();

    @Ozaman("^\"(.+)\"'nın yüklendiği görülür$")
    void pageLoaded(String pageName);

    @Diyelimki("^\"(.+)\" formatına göre sayfa düzenine bakılır$")
    void checkLayout(String specName);

    @Diyelimki("^Sayfa üzerindeki \"(.+)\" öğesine tıklanır$")
    void clickObject(String keyword);

    @Diyelimki("^Mouse \"(.+)\" öğesinin üzerine getirilir$")
    void mouseOverObject(String keyword);

    @Diyelimki("^Sayfa üzerindeki \"(.+)\" alanı üzerinde klavyeden enter tuşuna basılır$")
    void clickEnter(String keyword);

    @Diyelimki("^Sayfa üzerindeki \"(.+)\" öğesi sürüklenir$")
    void dragObject(String keyword);

    @Diyelimki("^Sayfa üzerindeki metin alanları tablodaki verilerle doldurulur$")
    void fillInputField(DataTable dataTable);

    @Diyelimki("^Sayfa üzerindeki metin alanları önbellekteki verilerle doldurulur$")
    void fillInputFieldByList(DataTable dataTable);

    @Diyelimki("^Sayfa üzerindeki dosya ekleme alanlarına tablodaki dosya verileri eklenir$")
    void uploadFileInputField(DataTable dataTable);

    @Diyelimki("^Sayfa üzerindeki seçim alanından tablodaki seçenekler seçilir$")
    void selectOptionField(DataTable dataTable);

    @Diyelimki("^Sayfa üzerindeki seçim alanından tablodaki verileri içeren seçenekler seçilir$")
    void selectOptionFieldByContainText(DataTable dataTable);

    @Ve("^Sayfada yer alan web öğelerinin metin veya nitelik değerleri önbelleğe kaydedilir$")
    void storeElementData(DataTable dataTable);

    @Ve("^Sayfada yer alan web öğelerinin metin veya nitelik değerleri, tabloda belirtilen değerle aynı olup olmadığı kontrol edilir$")
    void checkElementDataIsExistOnSite(DataTable dataTable);

    @Ve("^Sayfada yer alan web öğelerinin metin veya nitelik değerleri, tablodaki verileri içerip içermediği kontrol edilir$")
    void checkElementDataIsContainsOnSite(DataTable dataTable);

    @Ve("^Sayfada yer alan web öğelerinin metin veya nitelik değerleri, önbellekteki değer ile aynı olup olmadığı kontrol edilir$")
    void checkStoreElementDataIsExistOnSite(DataTable dataTable);

    @Ve("^Sayfada yer alan web öğelerinin metin veya nitelik değerleri, tabloda belirtilen aralıkta olup olmadığı kontrol edilir$")
    void rangeElementDataBetweenGivenValues(DataTable dataTable);

    @Diyelimki("^Tabloda verilen değerler ile haritadan lokasyon seçilir$")
    void selectMapPoint(DataTable dataTable);

    @Ve("^Tablodaki verilerin sayfa üzerinde olup olmadığı kontrol edilir$")
    void isDeleted(DataTable dataTable);

    @Ve("^Önbellekteki verilerin sayfa üzerinde olup olmadığı kontrol edilir$")
    void isStoreElementExist(DataTable dataTable);

    @Diyelimki("^Sayfada yer alan bağlantı adresinin yanıt verip vermediği kontrol edilir$")
    void isResponseStatusOk(DataTable dataTable);

    void teardown(Scenario scenario) throws Exception;
}
