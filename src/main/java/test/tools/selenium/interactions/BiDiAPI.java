package test.tools.selenium.interactions;

import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URI;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;

public class BiDiAPI extends SimpleActions {

    final static Logger logger = LogManager.getLogger(BiDiAPI.class);

    public BiDiAPI(WebDriver driver, WebDriverWait wait, ExtentTest extentTest) {
        super(driver, wait, extentTest);
    }

    /**
     * Some applications make use of browser authentication to secure pages.
     * With Selenium, you can automate the input of basic auth credentials whenever they arise.
     *
     * @param domain
     * @param username
     * @param password
     */
    public void registerBasicAuth(String domain, String username, String password) {
        try {
            Predicate<URI> uriPredicate = uri -> uri.getHost().contains(domain);
            ((HasAuthentication) driver).register(uriPredicate, UsernameAndPassword.of(username, password));
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * Some applications make use of browser authentication to secure pages.
     * With Selenium, you can automate the input of basic auth credentials whenever they arise.
     *
     * @param webdriver
     * @param username
     * @param password
     */
    public void registerBasicAuthOnRemoteWebdriver(String webdriver, String username, String password) {
        try {
            AtomicReference<DevTools> devToolsAtomicReference = new AtomicReference<>();

            driver = new Augmenter().addDriverAugmentation(webdriver,
                    HasAuthentication.class,
                    (caps, exec) -> (whenThisMatches, useTheseCredentials) -> {
                        devToolsAtomicReference.get()
                                .createSessionIfThereIsNotOne();
                        devToolsAtomicReference.get().getDomains()
                                .network()
                                .addAuthHandler(whenThisMatches,
                                        useTheseCredentials);

                    }).augment(driver);

            DevTools devTools = ((HasDevTools) driver).getDevTools();
            devTools.createSession();
            devToolsAtomicReference.set(devTools);
            ((HasAuthentication) driver).register(UsernameAndPassword.of(username, password));
        } catch (Exception e) {
            logger.error(e);
        }
    }

}
