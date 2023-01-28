package org.example.driver_manager;


import org.apache.log4j.Logger;
import org.example.capabilities.BrowserCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.example.config.GlobalConfig.browserType;
import static org.example.config.GlobalConfig.currentOS;


public class DriverManager {

    private static Logger log = Logger.getLogger(DriverManager.class);

    private static WebDriver webDriver;

    private DriverManager() {}

    public static boolean initDriver() {

        try {
            switch (browserType) {
                case CHROME:
                    webDriver = new ChromeDriver(new BrowserCapabilities().getChromeOptions(currentOS.startsWith("Windows")));
                    break;
                case EDGE:
                    webDriver = new EdgeDriver(new BrowserCapabilities().getEdgeOptions(currentOS.startsWith("Windows")));
                    break;
                case FIREFOX:
                    webDriver = new FirefoxDriver(new BrowserCapabilities().getFirefoxOptions(currentOS.startsWith("Windows")));
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public static WebDriver getDriver() {
        return webDriver;
    }

    public static void killDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
