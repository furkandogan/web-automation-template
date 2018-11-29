package test.tools.selenium.junit;

import org.junit.Test;

public class GoogleTest extends SeleniumBaseTR {

    @Test
    public void customerLogin() throws Exception {
        String testCaseName = "customerLogin";
        setUp(testCaseName);
        enterHomePage();
        pageLoaded("GOOGLE.COM SAYFASI");
        fillInputField("ARA", "Fenerbahçe");
        clickObject("GOOGLE'DA ARA BUTONU");
        pageLoaded("SPOR SONUCLARI SAYFASI");
        teardown(testCaseName);
    }

    @Test
    public void customerLogin2() throws Exception {
        String testCaseName = "customerLogin";
        setUp(testCaseName);
        enterHomePage();
        pageLoaded("GOOGLE.COM SAYFASI");
        fillInputField("ARA", "Fenerbahçe");
        clickObject("GOOGLE'DA ARA BUTONU");
        pageLoaded("SPOR SONUCLARI SAYFASI");
        teardown(testCaseName);
    }

}
