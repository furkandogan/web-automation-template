package test.tools.selenium.cucumber.en;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.tr.Diyelimki;
import cucumber.api.java.tr.Ozaman;
import cucumber.api.java.tr.Ve;

public interface CucumberBaseENInterface {
    void setUp(Scenario scenario) throws Exception;

    @Given("^Enter the homepage$")
    void enterHomePage();

    @Given("^Enter \"(.+)\" link address$")
    void enterRandomPage(String pageName);

    @When("^Switch to the new tab$")
    void switchToNewTab();

    @When("^Reload page$")
    void pageReload();

    @Then("^\"(.+)\" page to be loaded$")
    void pageLoaded(String pageName);

    @Given("^Page layout according to \"(.+)\" format$")
    void checkLayout(String specName);

    @When("^Click \"(.+)\" on the page$")
    void clickObject(String keyword);

    @And("^Mouse over \"(.+)\" element$")
    void mouseOverObject(String keyword);

    @When("^Press enter key on keyboard on \"(.+)\" field$")
    void clickEnter(String keyword);

    @And("^Drag \"(.+)\" element on the page$")
    void dragObject(String keyword);

    @And("^Text fields on the page are filled with data in the table$")
    void fillInputField(DataTable dataTable);

    @And("^Text fields on the page are filled with cached data$")
    void fillInputFieldByList(DataTable dataTable);

    @And("^File data in the table is added to the file insertion fields on the page$")
    void uploadFileInputField(DataTable dataTable);

    @And("^Select options in the table from the selectbox on the page$")
    void selectOptionField(DataTable dataTable);

    @And("^Select options that contain data from the table in the selectbox on the page$")
    void selectOptionFieldByContainText(DataTable dataTable);

    @And("^Text or attribute values of web elements on the page are cached$")
    void storeElementData(DataTable dataTable);

    @Then("^Check whether the text or attribute values of the web elements on the page are the same as those specified in the table$")
    void checkElementDataIsExistOnSite(DataTable dataTable);

    @Then("^Text or attribute values of web elements on the page are checked whether they contain the data in the table$")
    void checkElementDataIsContainsOnSite(DataTable dataTable);

    @Then("^Check whether the text or attribute values of the web elements on the page are the same as the value in the cache$")
    void checkStoreElementDataIsExistOnSite(DataTable dataTable);

    @Then("^Check whether the text or attribute values of the web elements on the page are within the range specified in the table$")
    void rangeElementDataBetweenGivenValues(DataTable dataTable);

    @And("^Select the location from the map with the values given in the table$")
    void selectMapPoint(DataTable dataTable);

    @Then("^Check whether the data in the table is on the page$")
    void isDeleted(DataTable dataTable);

    @Then("^Check if the cache data is on the page$")
    void isStoreElementExist(DataTable dataTable);

    @Then("^Check whether the link address on the page responds$")
    void isResponseStatusOk(DataTable dataTable);

    void teardown(Scenario scenario) throws Exception;
}
