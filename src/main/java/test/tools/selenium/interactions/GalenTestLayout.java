package test.tools.selenium.interactions;

import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.model.LayoutReport;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

import static java.util.Arrays.asList;

public class GalenTestLayout {

    public WebDriver driver;
    public WebDriverWait wait;

    public GalenTestLayout(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /**
     * @param specFileName
     * @return
     * @throws IOException
     */
    public GalenTestInfo testLayout(String specFileName, String device) throws IOException {
        LayoutReport layoutReport;
        String specPath = "/specs/" + specFileName + ".spec";
        layoutReport = Galen.checkLayout(driver, specPath,
                asList(device));
        GalenTestInfo galenTestInfo = GalenTestInfo
                .fromString(specFileName + "Layout Test");
        galenTestInfo.getReport().layout(layoutReport,
                "check layout on desktop");
        return galenTestInfo;
    }
}
