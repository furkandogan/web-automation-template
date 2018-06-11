package test.tools.selenium.config;

import test.tools.selenium.instances.ConfigurationInstance;

public class ConfigurationManager {

    public static String getConfigProperty(String key) throws Exception {

        return ConfigurationInstance.getInstance().getConfigProperty(key);
    }
}
