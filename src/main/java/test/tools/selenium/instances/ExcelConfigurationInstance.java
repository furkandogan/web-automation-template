package test.tools.selenium.instances;

import test.tools.selenium.XlsReader;
import test.tools.selenium.config.ConfigurationManager;
import test.tools.selenium.config.PropertyNames;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class  ExcelConfigurationInstance {


    private static ExcelConfigurationInstance instance = null;

    private long instanceId = 0L;

    private XlsReader xlsReader = null;


    //public instance creater
    public static synchronized ExcelConfigurationInstance getInstance() throws Exception {

        if (instance == null) {
            instance = new ExcelConfigurationInstance();

            // set rm id
            instance.instanceId = System.currentTimeMillis();
            instance.loadExcelDocument();
        }

        return instance;
    }


    private void loadExcelDocument() throws Exception {

        String excelDoc = ConfigurationManager.getConfigProperty(PropertyNames.EXCEL_FILE_PATH);
        xlsReader = new XlsReader(copyDublicateExcelDocument(excelDoc));
    }

    private void loadExcelDocument(String path) throws Exception {
        xlsReader = new XlsReader(copyDublicateExcelDocument(path));
    }

    public XlsReader getXlsReader() {
        return xlsReader;
    }

    private String copyDublicateExcelDocument(String excelDoc) {

        String srcDocument = excelDoc;
        String destDocument = String.format("%s-%s.%s", srcDocument.substring(0, srcDocument.lastIndexOf(".")), "copy", "xlsx");

        File src = new File(srcDocument);
        File dst = new File(destDocument);

        try {
            if (!dst.exists()) {
                //TODO:check the destination is a file!
                FileUtils.copyFile(src, new File(destDocument));
                System.out.println(String.format("Copy file [%s] does not exists. Creating new copy[%s]", srcDocument, destDocument));
            } else {
                System.out.println(String.format("Already copied file [%s] will be used.", destDocument));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dst.getAbsolutePath();
    }

}
