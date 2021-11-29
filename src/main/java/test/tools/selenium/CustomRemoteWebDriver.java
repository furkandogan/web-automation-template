package test.tools.selenium;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.*;

import java.net.URL;

public class CustomRemoteWebDriver extends RemoteWebDriver implements TakesScreenshot {

    public CustomRemoteWebDriver(URL remoteAddress, Capabilities desiredCapabilities) {
        super(( CommandExecutor ) (new HttpCommandExecutor(remoteAddress)), desiredCapabilities);
        System.out.println(String.format("CustomRemoteWebDriver called. Selenium server address :%s", remoteAddress));
    }


    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        if (( Boolean ) getCapabilities().getCapability(CapabilityType.TAKES_SCREENSHOT)) {
            String screenShot = execute(DriverCommand.SCREENSHOT).getValue().toString();
            return target.convertFromBase64Png(screenShot);
        }
        return null;
    }


}
