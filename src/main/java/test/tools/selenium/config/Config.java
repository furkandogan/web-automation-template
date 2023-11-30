package test.tools.selenium.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.slf4j.Logger;
import test.tools.selenium.extensions.SeleniumJupiterException;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;
import static test.tools.selenium.report.OutputHandler.*;

public class Config {

    final Logger log = getLogger(lookup().lookupClass());

    WebDriverManager manager;

    ConfigKey<String> properties = new ConfigKey<>("sel.jup.properties",
            String.class, "selenium-jupiter.properties");

    ConfigKey<String> outputFolder = new ConfigKey<>("sel.jup.output.folder",
            String.class);

    ConfigKey<Boolean> outputFolderPerClass = new ConfigKey<>("sel.jup.output.folder.per.class",
            Boolean.class);

    ConfigKey<String> seleniumServerUrl = new ConfigKey<>(
            "sel.jup.selenium.server.url", String.class);

    ConfigKey<Boolean> vnc = new ConfigKey<>("sel.jup.vnc", Boolean.class);

    ConfigKey<Boolean> recording = new ConfigKey<>("sel.jup.recording",
            Boolean.class);

    ConfigKey<String> dockerScreenResolution = new ConfigKey<>("sel.jup.dockerScreenResolution",
            String.class);

    ConfigKey<Boolean> recordingWhenFailure = new ConfigKey<>(
            "sel.jup.recording.when.failure", Boolean.class);

    ConfigKey<Boolean> screenshot = new ConfigKey<>("sel.jup.screenshot",
            Boolean.class);
    ConfigKey<Boolean> screenshotWhenFailure = new ConfigKey<>(
            "sel.jup.screenshot.when.failure", Boolean.class);
    ConfigKey<String> screenshotFormat = new ConfigKey<>(
            "sel.jup.screenshot.format", String.class);

    ConfigKey<String> browserTemplateJsonFile = new ConfigKey<>(
            "sel.jup.browser.template.json.file", String.class);
    ConfigKey<String> browserTemplateJsonContent = new ConfigKey<>(
            "sel.jup.browser.template.json.content", String.class);

    ConfigKey<Boolean> sparkEnabled = new ConfigKey<>("sel.jup.spark",
            Boolean.class);

    ConfigKey<String> sparkPath = new ConfigKey<>("sel.jup.spark.path",
            String.class);

    ConfigKey<Boolean> klovEnabled = new ConfigKey<>("sel.jup.klov",
            Boolean.class);

    ConfigKey<String> klovDb = new ConfigKey<>("sel.jup.klov.db",
            String.class);

    ConfigKey<Integer> klovPort = new ConfigKey<>("sel.jup.klov.port",
            Integer.class);

    ConfigKey<String> klovUrl = new ConfigKey<>("sel.jup.klov.url",
            String.class);

    ConfigKey<String> title = new ConfigKey<>("sel.jup.report.title",
            String.class);

    ConfigKey<String> build = new ConfigKey<>("sel.jup.build",
            String.class);

    private <T> T resolve(ConfigKey<T> configKey) {
        String strValue = null;
        String name = configKey.getName();
        T tValue = configKey.getValue();
        Class<T> type = configKey.getType();

        strValue = System.getenv(name.toUpperCase().replace(".", "_"));
        if (strValue == null) {
            strValue = System.getProperty(name);
        }
        if (strValue == null && tValue != null) {
            return tValue;
        }
        if (strValue == null) {
            strValue = getProperty(name);
        }
        return parse(type, strValue);
    }

    @SuppressWarnings("unchecked")
    private <T> T parse(Class<T> type, String strValue) {
        T output = null;
        if (type.equals(String.class)) {
            output = (T) strValue;
        } else if (type.equals(Integer.class)) {
            output = (T) Integer.valueOf(strValue);
        } else if (type.equals(Boolean.class)) {
            output = (T) Boolean.valueOf(strValue);
        } else {
            throw new SeleniumJupiterException(
                    "Type " + type.getTypeName() + " cannot be parsed");
        }
        return output;
    }

    private String getProperty(String key) {
        String value = null;
        Properties props = new Properties();
        try {
            InputStream inputStream = Config.class
                    .getResourceAsStream("/" + getProperties());
            props.load(inputStream);
            value = props.getProperty(key);
        } catch (Exception e) {
            throw new SeleniumJupiterException(e);
        } finally {
            if (value == null) {
                log.trace("Property key {} not found, using default value",
                        key);
                value = "";
            }
        }
        return value;
    }

    public void reset() {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.getType() == ConfigKey.class) {
                try {
                    ((ConfigKey<?>) field.get(this)).reset();
                } catch (Exception e) {
                    log.warn("Exception reseting {}", field);
                }
            }
        }
    }

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    // Getters and setters

    public String getProperties() {
        return resolve(properties);
    }

    public void setProperties(String properties) {
        this.properties.setValue(properties);
    }

    public String getSeleniumServerUrl() {
        String url = resolve(seleniumServerUrl);
        if (isNullOrEmpty(url)) {
            url = System.getProperty("webdriver.remote.server");
        }
        return url;
    }

    public void setSeleniumServerUrl(String value) {
        this.seleniumServerUrl.setValue(value);
    }

    public String getOutputFolder() {
        return resolve(outputFolder);
    }

    public void setOutputFolder(String value) {
        this.outputFolder.setValue(value);
    }

    public boolean isOutputFolderPerClass() {
        return resolve(outputFolderPerClass);
    }

    public void setOutputFolderPerClass(boolean value) {
        this.outputFolderPerClass.setValue(value);
    }

    public boolean isVnc() {
        return resolve(vnc);
    }

    public void setVnc(boolean value) {
        this.vnc.setValue(value);
    }

    public boolean isRecording() {
        return resolve(recording);
    }

    public void setRecording(boolean value) {
        this.recording.setValue(value);
    }

    public String getDockerScreenResolution() {
        return resolve(dockerScreenResolution);
    }

    public void setDockerScreenResolution(String dockerScreenResolution) {
        this.dockerScreenResolution.setValue(dockerScreenResolution);
    }

    public boolean isRecordingWhenFailure() {
        return resolve(recordingWhenFailure);
    }

    public void setRecordingWhenFailure(boolean value) {
        this.recordingWhenFailure.setValue(value);
    }

    public boolean isScreenshot() {
        return resolve(screenshot);
    }

    public void setScreenshot(boolean value) {
        this.screenshot.setValue(value);
    }

    public boolean isScreenshotWhenFailure() {
        return resolve(screenshotWhenFailure);
    }

    public void setScreenshotWhenFailure(boolean value) {
        this.screenshotWhenFailure.setValue(value);
    }

    public String getScreenshotFormat() {
        return resolve(screenshotFormat);
    }

    public void setScreenshotFormat(String value) {
        this.screenshotFormat.setValue(value);
    }

    public String getBrowserTemplateJsonFile() {
        return resolve(browserTemplateJsonFile);
    }

    public void setBrowserTemplateJsonFile(String value) {
        this.browserTemplateJsonFile.setValue(value);
    }

    public String getBrowserTemplateJsonContent() {
        return resolve(browserTemplateJsonContent);
    }

    public void setBrowserTemplateJsonContent(String value) {
        this.browserTemplateJsonContent.setValue(value);
    }

    public boolean getKlovEnabled() {
        return resolve(klovEnabled);
    }

    public void setKlovEnabled(boolean klovEnabled) {
        this.klovEnabled.setValue(klovEnabled);
    }

    public boolean getSparkEnabled() {
        return resolve(sparkEnabled);
    }

    public void setSparkEnabled(boolean sparkEnabled) {
        this.sparkEnabled.setValue(sparkEnabled);
    }

    public String getSparkPath() {
        return resolve(sparkPath);
    }

    public void setSparkPath(String sparkPath) {
        this.sparkPath.setValue(sparkPath);
    }

    public String getKlovDb() {
        return resolve(klovDb);
    }

    public void setKlovDb(String klovDb) {
        this.klovDb.setValue(klovDb);
    }

    public int getKlovPort() {
        return resolve(klovPort);
    }

    public void setKlovPort(int klovPort) {
        this.klovPort.setValue(klovPort);
    }

    public String getKlovUrl() {
        return resolve(klovUrl);
    }

    public void setKlovUrl(String klovUrl) {
        this.klovUrl.setValue(klovUrl);
    }

    public String getTitle() {
        return resolve(title);
    }

    public void setTitle(String title) {
        this.title.setValue(title);
    }

    public String getBuild() {
        return resolve(build);
    }

    public void setBuild(String build) {
        this.klovUrl.setValue(build);
    }

    public WebDriverManager getManager() {
        return manager;
    }

    public void setManager(WebDriverManager manager) {
        this.manager = manager;
    }

    // Readable API methods

    public void enableVnc() {
        setVnc(true);
    }

    public void enableScreenshot() {
        setScreenshot(true);
    }

    public void enableRecording() {
        setRecording(true);
    }

    public void customScreenResolution() {
        setDockerScreenResolution("1920x1080x24");
    }

    public void enableRecordingWhenFailure() {
        setRecordingWhenFailure(true);
    }

    public void enableScreenshotWhenFailure() {
        setScreenshotWhenFailure(true);
    }

    public void useSurefireOutputFolder() {
        setOutputFolder(SUREFIRE_REPORTS_KEY);
    }

    public void takeScreenshotAsBase64() {
        setScreenshotFormat(BASE64_KEY);
    }

    public void takeScreenshotAsPng() {
        setScreenshotFormat(PNG_KEY);
    }

    public void takeScreenshotAsBase64AndPng() {
        setScreenshotFormat(BASE64_AND_PNG_KEY);
    }

    public void enableSpark(){
        setKlovEnabled(true);
    }

    public void customSparkPath(String path){
        setSparkPath(path);
    }

    public void enableKlov(){
        setKlovEnabled(true);
    }

    public void customKlovDb(String ip){
        setKlovDb(ip);
    }

    public void customKlovPort(int port){
        setKlovPort(port);
    }

    public void customKlovURL(String url){
        setKlovUrl(url);
    }

    public void customTitle(String title){
        setTitle(title);
    }

    public void customBuild(String build){
        setBuild(build);
    }

}
