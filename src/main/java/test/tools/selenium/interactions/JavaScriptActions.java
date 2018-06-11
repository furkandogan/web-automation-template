package test.tools.selenium.interactions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JavaScriptActions extends WaitingActions {

    public JavaScriptActions(WebDriver driver) {
        super(driver);
    }

    /**
     * @return
     */
    public JavascriptExecutor getJSExecutor() {
        return ( JavascriptExecutor ) driver;
    }

    /**
     * @param jsStmt
     * @param wait
     * @return
     */
    public Object executeJS(String jsStmt, boolean wait) {
        return wait ? getJSExecutor().executeScript(jsStmt, "") : getJSExecutor().executeAsyncScript(jsStmt, "");
    }

    /**
     * @param script
     * @param obj
     */
    public Object executeJS(String script, Object... obj) {
        return getJSExecutor().executeScript(script, obj);
    }

    /**
     * Javascript executer boolean
     *
     * @param jsStmt
     * @return
     */
    public boolean executeBoolJS(String jsStmt) {
        return "true".equals(executeJS(jsStmt, true).toString());
    }

}
