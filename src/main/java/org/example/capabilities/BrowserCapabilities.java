package org.example.capabilities;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserCapabilities {
    private ChromeOptions chromeOptions;
    private FirefoxOptions firefoxOptions;
    private EdgeOptions edgeOptions;

    public ChromeOptions getChromeOptions(boolean isWindows) {
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--start-maximized");
        if (!isWindows) {
            System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver");
        } else {
            System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        }

        return chromeOptions;
    }

    public FirefoxOptions getFirefoxOptions(boolean isWindows) {
        firefoxOptions = new FirefoxOptions();

        return firefoxOptions;
    }

    public EdgeOptions getEdgeOptions(boolean isWindows) {
        edgeOptions = new EdgeOptions();

        return edgeOptions;
    }
}
