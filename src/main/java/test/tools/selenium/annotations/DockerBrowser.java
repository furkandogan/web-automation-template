package test.tools.selenium.annotations;

import test.tools.selenium.browser.BrowserType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(PARAMETER)
public @interface DockerBrowser {

    public BrowserType type();

    public String version() default "";

    public int size() default 0;

    public boolean recording() default false;

    public boolean vnc() default false;

    public String lang() default "";

    public String timezone() default "";

    public String[] volumes() default {};

    public String screenResolution() default "";

}
