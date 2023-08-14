package test.tools.selenium;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class CustomRemoteWebDriver extends RemoteWebDriver implements TakesScreenshot {

    public CustomRemoteWebDriver(URL remoteAddress, Capabilities desiredCapabilities) {
        super((CommandExecutor) (new HttpCommandExecutor(remoteAddress)), desiredCapabilities);
        System.out.println(String.format("CustomRemoteWebDriver called. Selenium server address :%s", remoteAddress));
    }

}
