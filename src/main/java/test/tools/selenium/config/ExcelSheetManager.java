package test.tools.selenium.config;

import test.tools.selenium.XlsReader;
import test.tools.selenium.instances.ExcelConfigurationInstance;

public class ExcelSheetManager {

    public static XlsReader getExcelDocument() throws Exception {

        return ExcelConfigurationInstance.getInstance().getXlsReader();
    }
}
