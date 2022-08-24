package test.tools.selenium.interactions;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.events.DomMutationEvent;
import org.openqa.selenium.logging.HasLogEvents;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static org.openqa.selenium.devtools.events.CdpEventTypes.domMutation;

public class BiDiAPI {

    public WebDriver driver;
    public WebDriverWait wait;

    public BiDiAPI(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
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
        Predicate<URI> uriPredicate = uri -> uri.getHost().contains(domain);
        ((HasAuthentication) driver).register(uriPredicate, UsernameAndPassword.of(username, password));
    }

    /**
     * Some applications make use of browser authentication to secure pages.
     * With Selenium, you can automate the input of basic auth credentials whenever they arise.
     *
     *
     * @param webdriver
     * @param username
     * @param password
     */
    public void registerBasicAuthOnRemoteWebdriver(String webdriver, String username, String password) {
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
    }

}
