package test.tools.selenium.instances;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

public class ConfigurationInstance {


    private static ConfigurationInstance instance = null;

    private long instanceId = 0L;
    private Properties configFileProperties = null;


    //public instance creater
    public static synchronized ConfigurationInstance getInstance() throws Exception {

        if (instance == null) {
            instance = new ConfigurationInstance();

            // set rm id
            instance.instanceId = System.currentTimeMillis();
            instance.loadConfigProperties();
            instance.printProperties();
        }

        return instance;
    }

    public String getConfigProperty(String key) {
        return configFileProperties.getProperty(key, "");
    }

    private void loadConfigProperties() {
        configFileProperties = new Properties();
        String configFileResource = "config/config.properties";
        try {

            final InputStream input = ClassLoader.getSystemResourceAsStream(configFileResource);

            configFileProperties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printProperties() {

        Set<String> strings = configFileProperties.stringPropertyNames();
        TreeSet sortedPropNamesSet = new TreeSet(strings);


        String m = null;

        System.out.println("************************************************************************************");
        System.out.println("****PRINTING ALL PROPERTIES");
        System.out.println("************************************************************************************");

        Iterator iterator = sortedPropNamesSet.iterator();
        while (iterator.hasNext()) {

            String key = ( String ) iterator.next();
            if (key.toLowerCase().contains("password")) {
                m = String.format("%s\t:*%s*", key, "*******");
            } else {
                m = String.format("%s\t:*%s*", key, configFileProperties.getProperty(key));
            }

            System.out.println(m);
        }
        System.out.println("************************************************************************************");

    }
}
