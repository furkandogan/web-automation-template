package test.tools.selenium.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({ FIELD, PARAMETER })
public @interface SelenideConfiguration {

    public String browser() default "";

    public boolean headless() default false;

    public String browserBinary() default "";

}
