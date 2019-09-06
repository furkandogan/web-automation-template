package test.tools.selenium.report.extent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentedDateUtil {

    public static Date getDate(String date, String pattern) {
        SimpleDateFormat sdfDate = new SimpleDateFormat(pattern);

        try {
            return sdfDate.parse(date);
        } catch (ParseException var4) {
            var4.printStackTrace();
            return null;
        }
    }
}
