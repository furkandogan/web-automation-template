package test.tools.selenium.report.extent;

import test.tools.selenium.config.ConfigurationManager;
import test.tools.selenium.config.PropertyNames;

import java.io.File;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ReportManager {

    private static ReportManager instance;

    private long instanceId = 0L;

    private  String reportBaseFolder = null;
    private  String reportLogFolder = null;
    private  String reportImageFolder = null;
    private  String reportCastFolder = null;

    public  String getReportBaseFolder() {
        return reportBaseFolder;
    }

    public String getReportLogFolder() {
        return reportLogFolder;
    }

    public  String getReportImageFolder() {
        return reportImageFolder;
    }

    public  String getReportCastFolder() {
        return reportCastFolder;
    }


    /***
     *
     * @return
     * @throws Exception
     */
    public static synchronized ReportManager getInstance() throws Exception {

        if (instance == null) {
            instance =  new ReportManager();

            instance.instanceId = System.currentTimeMillis();

            instance.reportBaseFolder = ConfigurationManager.getConfigProperty(PropertyNames.REPORT_FOLDER);
            if(initReportFolders(instance.reportBaseFolder)){

            } else {
                throw new Exception(String.format("report interactions folder [%s] could not created!!",instance.reportBaseFolder));
            }

            instance.reportLogFolder = String.format("%s%s%s",instance.reportBaseFolder,File.separator,"logs");
            if(initReportFolders(instance.reportLogFolder)){

            } else {
                throw new Exception(String.format("report image folder [%s] could not created!!",instance.reportLogFolder));
            }

            instance.reportImageFolder = String.format("%s%s%s",instance.reportBaseFolder,File.separator,"images");
            if(initReportFolders(instance.reportImageFolder)){

            } else {
                throw new Exception(String.format("report image folder [%s] could not created!!",instance.reportImageFolder));
            }

            instance.reportCastFolder = String.format("%s%s%s",instance.reportBaseFolder,File.separator,"videos");
            if(initReportFolders(instance.reportCastFolder)){

            } else {
                throw new Exception(String.format("report cast folder [%s] could not created!!",instance.reportImageFolder));
            }

            /*String reportTitle = ConfigurationInstance.getInstance().getConfigProperty("report.title");
            instance.extentTest = instance.extentReports.startTest(reportTitle);*/

        }

        return instance;
    }


    /***
     *
     * @return
     */
    public long getInstanceId() {
        return instanceId;
    }

    /***
     *
     * @param folders
     * @return
     * @throws Exception
     */
    private static boolean initReportFolders(String folders) throws Exception {
        boolean reportFolderCreated= false;
        if(! new File(folders).exists()) {
             reportFolderCreated = new File(folders).mkdirs();
        } else {
            reportFolderCreated = true;
        }
        return reportFolderCreated;
    }

    private String usingDateAndCalendar(long input) throws ParseException {
        Date date = new Date(input);
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);

        if ((cal.get(Calendar.MONTH) +1)<10) {
            if ((cal.get(Calendar.DATE))<10) {
                return(cal.get(Calendar.YEAR)
                        + "-0" + (cal.get(Calendar.MONTH) +1)
                        + "-0" + cal.get(Calendar.DATE)
                        + "T" + cal.get(Calendar.HOUR_OF_DAY)
                        + "-" + cal.get(Calendar.MINUTE)
                );

            }
            else {
                return(cal.get(Calendar.YEAR)
                        + "-0" + (cal.get(Calendar.MONTH) +1)
                        + "-" + cal.get(Calendar.DATE)
                        + "T" + cal.get(Calendar.HOUR_OF_DAY)
                        + "-" + cal.get(Calendar.MINUTE)
                );
            }

        }
        else if ((cal.get(Calendar.DATE))<10) {
            return(cal.get(Calendar.YEAR)
                    + "-" + (cal.get(Calendar.MONTH) +1)
                    + "-0" + cal.get(Calendar.DATE)
                    + "T" + cal.get(Calendar.HOUR_OF_DAY)
                    + "-" + cal.get(Calendar.MINUTE)
            );
        }
        else {
            return(cal.get(Calendar.YEAR)
                    + "-" + (cal.get(Calendar.MONTH) +1)
                    + "-" + cal.get(Calendar.DATE)
                    + "T" + cal.get(Calendar.HOUR_OF_DAY)
                    + "-" + cal.get(Calendar.MINUTE)
            );
        }
    }
}
