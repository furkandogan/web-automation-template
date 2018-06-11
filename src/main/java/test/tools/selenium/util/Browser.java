package test.tools.selenium.util;

import org.openqa.selenium.Platform;

public class Browser {

    /**
     *
     */
    private String name;


    /**
     *
     */
    private String version;

    /**
     *
     */
    private Platform platform;

    private long driverWaitTimeout = 60; //second
    private long driverImplicitWaitTimeOut = 10; //second


    public Browser() {

    }

    /**
     * @param name
     * @param version
     * @param platform
     */
    public Browser(String name, String version, Platform platform) {
        super();
        this.name = name;
        this.version = version;
        this.platform = platform;
    }


    public long getDriverWaitTimeout() {
        return driverWaitTimeout;
    }

    public void setDriverWaitTimeout(long driverWaitTimeout) {
        this.driverWaitTimeout = driverWaitTimeout;
    }

    public long getDriverImplicitWaitTimeOut() {
        return driverImplicitWaitTimeOut;
    }

    public void setDriverImplicitWaitTimeOut(long driverImplicitWaitTimeOut) {
        this.driverImplicitWaitTimeOut = driverImplicitWaitTimeOut;
    }


    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the platform
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * @param platform the platform to set
     */
    public void setPlatform(Platform platform) {
        this.platform = platform;
    }


    public void print() {
        System.out.println(String.format("name     :*%s*", getName()));
        System.out.println(String.format("version  :*%s**", getVersion()));
        System.out.println(String.format("platform :*%s*", getPlatform()));
    }
}

