package test.tools.selenium.cucumber.en;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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

public class CucumberBaseEN extends ExtentReportTestCaseFrame implements CucumberBaseENInterface {

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
        if (Boolean.valueOf(getConfigProperty(PropertyNames.GALEN_TEST_LAYOUT))) {
            galenTestInfos = new LinkedList<GalenTestInfo>();
        }
    }


    /**
     * Example:
     * Given Enter the homepage
     */
    @Override
    @Given("^Enter the homepage$")
    public void enterHomePage() {
        try {
            openStartPage();
            extTest.log(Status.PASS, "Homepage is loaded");
            Cookie cookie = new Cookie("zaleniumMessage", "Homepage is loaded");
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Example:
     * Given Enter "http://www.google.com" link address
     */
    @Override
    @Given("^Enter \"(.+)\" link address$")
    public void enterRandomPage(String pageName) {
        try {
            BaseUtil baseUtil = new BaseUtil(driver, wait);
            baseUtil.openCustomUrlPage(pageName);
            extTest.log(Status.PASS, String.format("%s link address is loaded", pageName));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("%s link address is loaded", pageName));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Example:
     * When Switch to the new tab
     */
    @Override
    @When("^Switch to the new tab$")
    public void switchToNewTab() {
        try {
            BaseUtil baseUtil = new BaseUtil(driver, wait);
            baseUtil.changeTab();
            extTest.log(Status.PASS, "New tab opened");
            Cookie cookie = new Cookie("zaleniumMessage", "New tab opened");
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Example:
     * When Reload page
     */
    @Override
    @When("^Reload page$")
    public void pageReload() {
        try {
            BaseUtil baseUtil = new BaseUtil(driver, wait);
            baseUtil.pageRefresh();
            extTest.log(Status.PASS, "Page reloaded");
            Cookie cookie = new Cookie("zaleniumMessage", "Page reloaded");
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Example:
     * Then "http://www.google.com" page to be loaded
     */
    @Override
    @Then("^\"(.+)\" page to be loaded$")
    public void pageLoaded(String pageName) {
        try {
            PresenceOfQualification presence = new PresenceOfQualification(driver, wait);
            presence.isPageLoaded(pageName);
            extTest.log(Status.PASS, String.format("%s page is loaded", pageName));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("%s page is loaded", pageName));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Example:
     * Given Page layout according to "/specs/homePage.spec" format
     */
    @Override
    @Given("^Page layout according to \"(.+)\" format$")
    public void checkLayout(String specName) {
        try {
            GalenTestLayout galenTestLayout = new GalenTestLayout(driver);
            galenTestInfos.add(galenTestLayout.testLayout(specName));
            extTest.log(Status.PASS, String.format("Page layout checked by %s format", specName));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("Page layout checked by %s format", specName));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Example:
     * When Click "keyword" on the page
     */
    @Override
    @When("^Click \"(.+)\" on the page$")
    public void clickObject(String keyword) {
        try {
            ClickActions clickActions = new ClickActions(driver, wait);
            clickActions.clickToElement(keyword);
            extTest.log(Status.PASS, String.format("Clicked %s on the page", keyword));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("Clicked %s on the page", keyword));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Example:
     * And Mouse over "keyword"
     */
    @Override
    @And("^Mouse over \"(.+)\" element$")
    public void mouseOverObject(String keyword) {
        try {
            ActionsPerform actionsPerform = new ActionsPerform(driver, wait);
            actionsPerform.moveMouse(keyword, 0);
            extTest.log(Status.PASS, String.format("Mouse overed %s element", keyword));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("Mouse overed %s element", keyword));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Example:
     * When Press enter key on keyboard on "keyword" field
     */
    @Override
    @When("^Press enter key on keyboard on \"(.+)\" field$")
    public void clickEnter(String keyword) {
        try {
            SendKeysActions sendKeysActions = new SendKeysActions(driver, wait);
            sendKeysActions.sendKeysReturn(keyword);
            extTest.log(Status.PASS, String.format("Pressed enter key on keyboard on %s field", keyword));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("Pressed enter key on keyboard on %s field", keyword));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Example:
     * And Drag "keyword" element on the page
     */
    @Override
    @And("^Drag \"(.+)\" element on the page$")
    public void dragObject(String keyword) {
        try {
            ActionsPerform actionsPerform = new ActionsPerform(driver, wait);
            actionsPerform.dragAndDrop(keyword);
            extTest.log(Status.PASS, String.format("Dragged %s element on the page", keyword));
            Cookie cookie = new Cookie("zaleniumMessage", String.format("Dragged %s element on the page", keyword));
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            extTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Example:
     * And Text fields on the page are filled with data in the table
     * | cellItem1 | cellItem2 |
     */
    @Override
    @And("^Text fields on the page are filled with data in the table$")
    public void fillInputField(DataTable dataTable) {
        for (DataTableRow dataTableRow : dataTable.getGherkinRows()) {
            String cellItem1 = dataTableRow.getCells().get(0);
            String cellItem2 = dataTableRow.getCells().get(1);
            try {
                SendKeysActions sendKeysActions = new SendKeysActions(driver, wait);
                sendKeysActions.sendKeysToElement(cellItem1, cellItem2);
                extTest.log(Status.PASS, String.format("The %s value in the table is entered in the %s field on the page", cellItem2, cellItem1));
                Cookie cookie = new Cookie("zaleniumMessage", String.format("The %s value in the table is entered in the %s field on the page", cellItem2, cellItem1));
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }

    /**
     * Example:
     * And Text fields on the page are filled with cached data
     * | cellItem1 | cellItem2 |
     */
    @Override
    @And("^Text fields on the page are filled with cached data$")
    public void fillInputFieldByList(DataTable dataTable) {
        for (DataTableRow dataTableRow : dataTable.getGherkinRows()) {
            String cellItem1 = dataTableRow.getCells().get(0);
            String cellItem2 = dataTableRow.getCells().get(1);
            try {
                String storeDataItem = elementProperties.getStoreElementValue(cellItem2);
                SendKeysActions sendKeysActions = new SendKeysActions(driver, wait);
                sendKeysActions.sendKeysToElement(cellItem1, storeDataItem);
                extTest.log(Status.PASS, String.format("The cached %s value is entered in the %s field on the page", storeDataItem, cellItem1));
                Cookie cookie = new Cookie("zaleniumMessage", String.format("The cached %s value is entered in the %s field on the page", storeDataItem, cellItem1));
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }

    /**
     * Example:
     * And File data in the table is added to the file insertion fields on the page
     * | cellItem1 | cellItem2 |
     */
    @Override
    @And("^File data in the table is added to the file insertion fields on the page$")
    public void uploadFileInputField(DataTable dataTable) {
        for (DataTableRow dataTableRow : dataTable.getGherkinRows()) {
            String cellItem1 = dataTableRow.getCells().get(0);
            String cellItem2 = dataTableRow.getCells().get(1);
            try {
                SendKeysActions sendKeysActions = new SendKeysActions(driver, wait);
                sendKeysActions.uploadFile(cellItem1, cellItem2);
                extTest.log(Status.PASS, String.format("Uploaded %s file from the table in %s field", cellItem2, cellItem1));
                Cookie cookie = new Cookie("zaleniumMessage", String.format("Uploaded %s file from the table in %s field", cellItem2, cellItem1));
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }

    /**
     * Example:
     * And Select options in the table from the selectbox on the page
     * | cellItem1 | cellItem2 | cellItem3 |
     */
    @Override
    @And("^Select options in the table from the selectbox on the page$")
    public void selectOptionField(DataTable dataTable) {
        for (DataTableRow dataTableRow : dataTable.getGherkinRows()) {
            String cellItem1 = dataTableRow.getCells().get(0);
            String cellItem2 = dataTableRow.getCells().get(1);
            String cellItem3 = dataTableRow.getCells().get(2);
            try {
                SelectActions selectActions = new SelectActions(driver, wait);
                switch (cellItem2) {
                    case "text":
                        selectActions.selectElementByVisibleText(cellItem1, cellItem3);
                        break;
                    case "value":
                        selectActions.selectElementByValue(cellItem1, cellItem3);
                        break;
                    case "index":
                        selectActions.selectElementByIndex(cellItem1, Integer.parseInt(cellItem3));
                        break;
                }
                extTest.log(Status.PASS, String.format("Options that is %s value from the table is selected from the %s selectbox on the page", cellItem3, cellItem1));
                Cookie cookie = new Cookie("zaleniumMessage", String.format("The %s value from the table is selected from the %s field on the page", cellItem3, cellItem1));
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }

    /**
     * Example:
     * And Select options that contain data from the table in the selectbox on the page
     * | cellItem1 | cellItem2 |
     */
    @Override
    @And("^Select options that contain data from the table in the selectbox on the page$")
    public void selectOptionFieldByContainText(DataTable dataTable) {
        for (DataTableRow dataTableRow : dataTable.getGherkinRows()) {
            String cellItem1 = dataTableRow.getCells().get(0);
            String cellItem2 = dataTableRow.getCells().get(1);
            try {
                SelectActions selectActions = new SelectActions(driver, wait);
                selectActions.selectByVisibleContainText(cellItem1, cellItem2);
                extTest.log(Status.PASS, String.format("Options that contain %s value from the table is selected from the %s selectbox on the page", cellItem2, cellItem1));
                Cookie cookie = new Cookie("zaleniumMessage", String.format("Options that contain %s value from the table is selected from the %s selectbox on the page", cellItem2, cellItem1));
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }

    /**
     * Example:
     * And Text or attribute values of web elements on the page are cached
     * | cellItem1 | cellItem2 |
     */
    @Override
    @And("^Text or attribute values of web elements on the page are cached$")
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
                extTest.log(Status.PASS, String.format("The %s value of the %s element on the page is cached", cellItem2, cellItem1));
                Cookie cookie = new Cookie("zaleniumMessage", String.format("The %s value of the %s element on the page is cached", cellItem2, cellItem1));
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }

    /**
     * Example:
     * Then Check whether the text or attribute values of the web elements on the page are the same as those specified in the table
     * | cellItem1 | cellItem2 | cellItem3 |
     */
    @Override
    @Then("^Check whether the text or attribute values of the web elements on the page are the same as those specified in the table$")
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
                    Assert.assertTrue(presence.isAttrValueEquals(cellItem1, cellItem2, cellItem3));
                }
                extTest.log(Status.PASS, String.format("The %s value of the element %s on the page was the same as the %s value in the table", cellItem2, cellItem1, cellItem3));
                Cookie cookie = new Cookie("zaleniumMessage", String.format("The %s value of the element %s on the page was the same as the %s value in the table", cellItem2, cellItem1, cellItem3));
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }


    /**
     * Example:
     * Then Text or attribute values of web elements on the page are checked whether they contain the data in the table
     * | cellItem1 | cellItem2 | cellItem3 |
     */
    @Override
    @Then("^Text or attribute values of web elements on the page are checked whether they contain the data in the table$")
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
                extTest.log(Status.PASS, String.format("The %s value of the %s element on the page contains the %s value in the table", cellItem2, cellItem1, cellItem3));
                Cookie cookie = new Cookie("zaleniumMessage", String.format("The %s value of the %s element on the page contains the %s value in the table", cellItem2, cellItem1, cellItem3));
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }

    /**
     * Example:
     * Then Check whether the text or attribute values of the web elements on the page are the same as the value in the cache
     * | cellItem1 | cellItem2 | cellItem3 |
     */
    @Override
    @Then("^Check whether the text or attribute values of the web elements on the page are the same as the value in the cache$")
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
                extTest.log(Status.PASS, String.format("The %s value of the %s element on the page was the same as the %s value of the cached element %s", cellItem2, cellItem1, cellItem2, cellItem3));
                Cookie cookie = new Cookie("zaleniumMessage", String.format("The %s value of the %s element on the page was the same as the %s value of the cached element %s", cellItem2, cellItem1, cellItem2, cellItem3));
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }

    /**
     * Example:
     * Then Check whether the text or attribute values of the web elements on the page are within the range specified in the table
     * | cellItem1 | cellItem2 | cellItem3 | cellItem4 |
     */
    @Override
    @Then("^Check whether the text or attribute values of the web elements on the page are within the range specified in the table$")
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
                extTest.log(Status.PASS, String.format("The %s value of the element %s on the page was in the range of %s and %s", cellItem2, cellItem1, cellItem3, cellItem4));
                Cookie cookie = new Cookie("zaleniumMessage", String.format("The %s value of the element %s on the page was in the range of %s and %s", cellItem2, cellItem1, cellItem3, cellItem4));
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }


    /**
     * Example:
     * And Select the location from the map with the values given in the table
     * | cellItem1 | cellItem2 |
     */
    @Override
    @And("^Select the location from the map with the values given in the table$")
    public void selectMapPoint(DataTable dataTable) {
        for (DataTableRow dataTableRow : dataTable.getGherkinRows()) {
            String cellItem1 = dataTableRow.getCells().get(0);
            String cellItem2 = dataTableRow.getCells().get(1);
            try {
                SelectActions selectActions = new SelectActions(driver, wait);
                selectActions.selectMapPoint(cellItem1, cellItem2);
                extTest.log(Status.PASS, "Location selected from map");
                Cookie cookie = new Cookie("zaleniumMessage", "Location selected from map");
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }

    /**
     * Example:
     * Then Check whether the data in the table is on the page
     * | cellItem1 | cellItem2 | cellItem3 |
     */
    @Override
    @Then("^Check whether the data in the table is on the page$")
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
                extTest.log(Status.PASS, String.format("%s element not exist on page", cellItem1));
                Cookie cookie = new Cookie("zaleniumMessage", String.format("%s element not exist on page", cellItem1));
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }

    /**
     * Example:
     * Then Check if the cache data is on the page
     * | cellItem1 | cellItem2 |
     */
    @Override
    @Then("^Check if the cache data is on the page$")
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
                extTest.log(Status.PASS, String.format("Cached %s element not exist on page", cellItem1));
                Cookie cookie = new Cookie("zaleniumMessage", String.format("Cached %s element not exist on page", cellItem1));
                driver.manage().addCookie(cookie);
            } catch (Exception e) {
                extTest.log(Status.FAIL, e.getMessage());
                Assert.fail(e.getMessage());
            }
        }
    }

    /**
     * Example:
     * Then Check whether the link address on the page responds
     * | cellItem1 | cellItem2 |
     */
    @Override
    @Then("^Check whether the link address on the page responds$")
    public void isResponseStatusOk(DataTable dataTable) {
        for (DataTableRow dataTableRow : dataTable.getGherkinRows()) {
            String cellItem1 = dataTableRow.getCells().get(0);
            String cellItem2 = dataTableRow.getCells().get(0);
            try {
                PresenceOfQualification presence = new PresenceOfQualification(driver, wait);
                GetElementProperties getElementProperties = new GetElementProperties(driver, wait);
                Assert.assertTrue(presence.linkExists(getElementProperties.getAttribute(cellItem1, cellItem2)));
                extTest.log(Status.PASS, "The link address on the page is found to respond");
                Cookie cookie = new Cookie("zaleniumMessage", "The link address on the page is found to respond");
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
        if (Boolean.valueOf(getConfigProperty(PropertyNames.GALEN_TEST_LAYOUT))) {
            new HtmlReportBuilder().build(galenTestInfos,
                    "target/galen-html-reports");
        }
        if (scenario.isFailed()) {
            if (extTest != null) {
                extTest.log(Status.FAIL, scenario.getName() + "'s scenario is failed");
                logScreenCapture(extTest, scenario.getName());
                cleanUp(getExtentReports(), extTest);
            }
            Cookie cookie = new Cookie("zaleniumTestPassed", "false");
            driver.manage().addCookie(cookie);
            cleanUpWebDriver(driver);
        } else {
            if (extTest != null) {
                extTest.log(Status.PASS, scenario.getName() + "'s scenario is succeed");
                logScreenCapture(extTest, scenario.getName());
                cleanUp(getExtentReports(), extTest);
            }
            Cookie cookie = new Cookie("zaleniumTestPassed", "true");
            driver.manage().addCookie(cookie);
            cleanUpWebDriver(driver);
        }
    }

}
