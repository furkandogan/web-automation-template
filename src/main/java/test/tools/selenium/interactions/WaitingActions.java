package test.tools.selenium.interactions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class WaitingActions {

    public WebDriver driver;
    public WebDriverWait wait;
    public FluentWait fluentWait;


    public WaitingActions(WebDriver driver) {
        this.driver = driver;
    }

    public WaitingActions(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    public void setFluentWait() {
        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class);
    }

    public void waitForAngularLoad() {
        wait.until((ExpectedCondition<Boolean>) driver -> {
            assert driver != null;
            return Boolean.valueOf(((JavascriptExecutor) driver)
                    .executeScript("return angular.element(document).injector().get('$http').pendingRequests.length === 0").toString());
        });
    }

    public void waitUntilAngular5Ready() {
        wait.until((ExpectedCondition<Boolean>) driver -> {
            assert driver != null;
            return Boolean.valueOf(((JavascriptExecutor) driver)
                    .executeScript("return window.getAllAngularTestabilities().filter(x=>!x.isStable()).length === 1").toString());
        });
    }

    public void waitForValueIsPresent(WebElement element, String value) {
        wait.until((ExpectedCondition<Boolean>) driver -> {
            assert driver != null;
            return Boolean.valueOf(((JavascriptExecutor) driver)
                    .executeScript("return arguments[0].value === '" + value + "'", element).toString());
        });
    }

    public void waitForIsChecked(WebElement element) {
        wait.until((ExpectedCondition<Boolean>) driver -> {
            assert driver != null;
            return Boolean.valueOf(((JavascriptExecutor) driver)
                    .executeScript("return arguments[0].checked", element).toString());
        });
    }

    public void waitUntilJSReady() {
        wait.until((ExpectedCondition<Boolean>) driver -> {
            assert driver != null;
            return ((JavascriptExecutor) driver)
                    .executeScript("return document.readyState").toString().equals("complete");
        });
    }

    public void waitUntilAngularReady() {
        Boolean angularUnDefined = (Boolean) ((JavascriptExecutor) driver)
                .executeScript("return window.angular === undefined");
        if (!angularUnDefined) {
            Boolean angularInjectorUnDefined = (Boolean) ((JavascriptExecutor) driver)
                    .executeScript("return angular.element(document).injector() === undefined");
            if (!angularInjectorUnDefined) {
                waitForAngularLoad();
            }
        }
    }

    public static void checkPendingRequests(final String context, final EventFiringWebDriver driver) {
        final int timeoutInNumberOfTries = 50;
        try {
            if (driver != null) {
                System.out.println("Number of active calls " + context + ": ");
                boolean timeout = true;
                for (int i = 0; i < timeoutInNumberOfTries; i++) {
                    Thread.sleep(100);
                    final Object numberOfAjaxConnections = ((JavascriptExecutor) driver).executeScript("return window.openHTTPs");
                    // return should be a number
                    if (numberOfAjaxConnections instanceof Long) {
                        final Long n = (Long) numberOfAjaxConnections;
                        System.out.println(" " + n);
                        if (n == 0L) {
                            timeout = false;
                            break;
                        }
                    } else {
                        // If it's not a number, the page might have been freshly loaded indicating the monkey
                        // patch is replaced or we haven't yet done the patch.
                        monkeyPatchXMLHttpRequest(driver);
                    }
                }
                if (timeout) {
                    throw new RuntimeException("Pending XHR requests even after 50 times checking (100 msec) for:" + context);
                }
            } else {
                System.out.println("Web driver: " + driver + " cannot execute javascript");
            }
        } catch (final InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void monkeyPatchXMLHttpRequest(final EventFiringWebDriver driver) {
        try {
            if (driver != null) {
                final Object numberOfAjaxConnections = ((JavascriptExecutor) driver).executeScript("return window.openHTTPs");
                if (numberOfAjaxConnections instanceof Long) {
                    return;
                }
                final String script = "  (function() {" + "var oldOpen = XMLHttpRequest.prototype.open;" + "window.openHTTPs = 0;" +
                        "XMLHttpRequest.prototype.open = function(method, url, async, user, pass) {" + "window.openHTTPs++;" +
                        "this.addEventListener('readystatechange', function() {" + "if(this.readyState == 4) {" + "window.openHTTPs--;" + "}" +
                        "}, false);" + "oldOpen.call(this, method, url, async, user, pass);" + "}" + "})();";
                ((JavascriptExecutor) driver).executeScript(script);
            } else {
                System.out.println("Web driver: " + null + " cannot execute javascript");
            }
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void waitForAngular8Load() {
        String angularReadyScript = "let pendingRequestCount = 0;\n" +
                "let oldXHROpen = window.XMLHttpRequest.prototype.open;\n" +
                "window.XMLHttpRequest.prototype.open = function (method, url, async, user, password) {\n" +
                "  this.addEventListener('loadstart', function() {\n" +
                "    pendingRequestCount++;\n" +
                "  });\n" +
                "  this.addEventListener('loadend', function() {\n" +
                "    if(pendingRequestCount < 0) {\n" +
                "      pendingRequestCount = 0;\n" +
                "      return;\n" +
                "    }\n" +
                "    pendingRequestCount--;\n" +
                "  });\n" +
                "  return oldXHROpen.apply(this, arguments);\n" +
                "}\n" +
                "const getPendingRequestCount = async () => {\n" +
                "  return new Promise((resolve, reject) => {\n" +
                "    const timer = setInterval(() => {\n" +
                "      if(pendingRequestCount === 0) {\n" +
                "        clearInterval(timer);\n" +
                "        resolve(true);\n" +
                "      }\n" +
                "    }, 100);\n" +
                "  });\n" +
                "}\n" +
                "await getPendingRequestCount();\n" +
                "return pendingRequestCount === 0 ? true : false;";
        ExpectedCondition<Boolean> angularLoad = driver -> {
            assert driver != null;
            return Boolean.valueOf(((JavascriptExecutor) driver)
                    .executeScript(angularReadyScript).toString());
        };
        boolean angularReady = Boolean.parseBoolean(((JavascriptExecutor) driver)
                .executeScript(angularReadyScript).toString());
        if (!angularReady) {
            wait.until(angularLoad);
        }
    }

}

