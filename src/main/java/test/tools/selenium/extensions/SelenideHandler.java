package test.tools.selenium.extensions;

import com.codeborne.selenide.SelenideConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import test.tools.selenium.annotations.SelenideConfiguration;
import test.tools.selenium.util.AnnotationsReader;

import java.lang.reflect.Parameter;
import java.util.Optional;

public class SelenideHandler {

    final static Logger logger = LogManager.getLogger(SelenideHandler.class);

    static final String SELENIDE_DRIVER_CLASS = "com.codeborne.selenide.SelenideDriver";
    static final String SELENIDE_CONFIG_INTERFACE = "com.codeborne.selenide.Config";
    static final String SELENIDE_CONFIG_CLASS = "com.codeborne.selenide.SelenideConfig";
    static final String SELENIDE_PROXY_CLASS = "com.codeborne.selenide.proxy.SelenideProxyServer";

    AnnotationsReader annotationsReader;

    public SelenideHandler(AnnotationsReader annotationsReader) {
        this.annotationsReader = annotationsReader;
    }

    public boolean isSelenide(Class<?> type) {
        return type.getCanonicalName().equals(SELENIDE_DRIVER_CLASS);
    }

    public boolean useCustomSelenideConfig(Parameter parameter,
                                           Optional<Object> testInstance) {
        SelenideConfiguration selenideConfiguration = parameter
                .getAnnotation(SelenideConfiguration.class);
        SelenideConfig globalConfig = annotationsReader.getFromAnnotatedField(
                testInstance, SelenideConfiguration.class,
                SelenideConfig.class);
        return selenideConfiguration != null || globalConfig != null;
    }

    public Object createSelenideDriver(WebDriver driver, Parameter parameter,
                                       Optional<Object> testInstance) {
        Object object = null;
        try {
            Object config = getSelenideConfig(testInstance, parameter);
            if (driver == null) {
                object = Class.forName(SELENIDE_DRIVER_CLASS)
                        .getDeclaredConstructor(
                                Class.forName(SELENIDE_CONFIG_INTERFACE))
                        .newInstance(config);
            } else {
                object = Class.forName(SELENIDE_DRIVER_CLASS)
                        .getDeclaredConstructor(
                                Class.forName(SELENIDE_CONFIG_INTERFACE),
                                WebDriver.class,
                                Class.forName(SELENIDE_PROXY_CLASS))
                        .newInstance(config, driver, null);
            }

        } catch (Exception e) {
            logger.warn("Exception creating SelenideDriver object", e);
        }

        return object;
    }

    public Object getSelenideConfig(Optional<Object> testInstance,
                                    Parameter parameter) {
        Object config = null;
        try {
            config = Class.forName(SELENIDE_CONFIG_CLASS)
                    .getDeclaredConstructor().newInstance();

            if (parameter != null) {
                SelenideConfiguration selenideConfiguration = parameter
                        .getAnnotation(SelenideConfiguration.class);

                // @SelenideConfiguration as parameter
                if (selenideConfiguration != null) {
                    Class.forName(SELENIDE_CONFIG_CLASS)
                            .getDeclaredMethod("browser", String.class)
                            .invoke(config, selenideConfiguration.browser());
                    Class.forName(SELENIDE_CONFIG_CLASS)
                            .getDeclaredMethod("headless", boolean.class)
                            .invoke(config, selenideConfiguration.headless());
                    Class.forName(SELENIDE_CONFIG_CLASS)
                            .getDeclaredMethod("browserBinary", String.class)
                            .invoke(config,
                                    selenideConfiguration.browserBinary());
                }

                // @SelenideConfiguration as field
                SelenideConfig globalConfig = annotationsReader
                        .getFromAnnotatedField(testInstance,
                                SelenideConfiguration.class,
                                SelenideConfig.class);
                if (globalConfig != null) {
                    config = globalConfig;
                }
            }

        } catch (Exception e) {
            logger.warn("Exception getting Selenide Config", e);
        }

        return config;
    }

}
