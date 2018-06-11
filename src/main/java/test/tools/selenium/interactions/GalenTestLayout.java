package test.tools.selenium.interactions;

import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.model.LayoutReport;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static java.util.Arrays.asList;

public class GalenTestLayout extends WaitingActions {

    public GalenTestLayout(WebDriver driver) {
        super(driver);
    }

    /**
     * @param specFileName
     * @return
     * @throws IOException
     */
    public GalenTestInfo testLayout(String specFileName) throws IOException {
        LayoutReport layoutReport;
        String specPath = "/specs/" + specFileName + ".spec";
        layoutReport = Galen.checkLayout(driver, specPath,
                asList("desktop"));
        GalenTestInfo galenTestInfo = GalenTestInfo
                .fromString(specFileName + "Layout Test");
        galenTestInfo.getReport().layout(layoutReport,
                "check layout on desktop");
        return galenTestInfo;
    }
}
