package org.example.operation;

import org.example.config.GlobalConfig;
import org.example.driver_manager.DriverManager;
import org.example.element_manager.ElementManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;

public class WaitOperation {

    private WebDriver driver = null;
    private String errorMessage;
    private WebElement webElement = null;
    private By locator = null;
    private Logger log = Logger.getLogger(WaitOperation.class);
    private ElementManager elementManager;

    public WaitOperation() {
        elementManager = new ElementManager();
    }
    
    public WebElement waitPresence(String key) {
        return waitPresence(key, GlobalConfig.DEFAULT_WAIT);
    }

    public WebElement waitPresence(String key, int waitSecond) {

        try {
            webElement = null;
            locator = elementManager.getLocator(key);
            driver = DriverManager.getDriver();
            WebDriverWait webDriverWait = new WebDriverWait(driver, waitSecond, GlobalConfig.SLEEP_IN_MILLIS);
            webElement = (WebElement) webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception ex) {
            errorMessage = String.format("'%s' objesi sayfa üzerinde var olması beklenirken sorun oluştu!", key);
            log.error(errorMessage);
            Assert.fail(errorMessage);
        }
        return webElement;
    }

    public WebElement waitVisible(String key) {
        return waitVisible(key, GlobalConfig.DEFAULT_WAIT);
    }

    public WebElement waitVisible(String key, int waitSecond) {

        try {
            webElement = null;
            locator = elementManager.getLocator(key);
            driver = DriverManager.getDriver();
            WebDriverWait webDriverWait = new WebDriverWait(driver, waitSecond, GlobalConfig.SLEEP_IN_MILLIS);
            webElement = (WebElement) webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception ex) {
            errorMessage = String.format("'%s' objesi sayfa üzerinde görünür olması beklenirken sorun oluştu!", key);
            log.error(errorMessage);
            Assert.fail(errorMessage);
        }
        return webElement;
    }

    public WebElement waitClickable(String key) {
        return waitClickable(key, GlobalConfig.DEFAULT_WAIT);
    }

    public WebElement waitClickable(String key, int defaultWait) {

        try {
            webElement = null;
            locator = elementManager.getLocator(key);
            driver = DriverManager.getDriver();
            WebDriverWait webDriverWait = new WebDriverWait(driver, defaultWait, GlobalConfig.SLEEP_IN_MILLIS);
            webElement = (WebElement) webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception ex) {
            errorMessage = String.format("'%s' objesi sayfa üzerinde tıklanabilir olması beklenirken sorun oluştu!", key);
            log.error(errorMessage);
            Assert.fail(errorMessage);
        }
        return webElement;
    }

    public WebElement until(ExpectedCondition<?> condition, int second) {
        driver = DriverManager.getDriver();
        WebDriverWait webDriverWait = new WebDriverWait(driver, second, GlobalConfig.SLEEP_IN_MILLIS);
        return (WebElement) webDriverWait.until(condition);
    }

    public void waitDoNotWriteToLogFile(int second) {

        try {
            Thread.sleep((long) second * 1000);
        } catch (Exception ex) {
            log.error(String.format("'%s' saniye beklerken sorun oluştu!", second));
        }
    }

    public boolean isPresence(String key, int second) {

        try {
            locator = elementManager.getLocator(key);
            webElement = null;
            webElement = until(ExpectedConditions.presenceOfElementLocated(locator), second);
            return webElement != null;
        } catch (Exception ex) {
        }
        return false;
    }

    public boolean isVisible(String key, int second) {

        try {
            locator = elementManager.getLocator(key);
            webElement = null;
            webElement = until(ExpectedConditions.visibilityOfElementLocated(locator), second);
            return webElement != null;
        } catch (Exception ex) {
        }
        return false;
    }

    public boolean isClickable(String key, int second) {

        try {
            locator = elementManager.getLocator(key);
            webElement = null;
            webElement = until(ExpectedConditions.elementToBeClickable(locator), second);
            return webElement != null;
        } catch (Exception ex) {
        }
        return false;
    }

    public boolean isExist(String key, int waitSecond) {

        try {
            boolean presence = isPresence(key, waitSecond);
            boolean visible = isVisible(key, 1);
            boolean clickable = isClickable(key, 1);
            return presence && visible && clickable;
        } catch (Exception ex) {
        }
        return false;
    }

    public void waitUntilTextIsLoadedInToPageSource(String text) {

        try {
            driver = DriverManager.getDriver();
            WebDriverWait webDriverWait = new WebDriverWait(driver, GlobalConfig.DEFAULT_WAIT, GlobalConfig.SLEEP_IN_MILLIS);
            webDriverWait.until(waitUntilTextIsLoaded(text));
        }
        catch (Exception ex) {
            Assert.fail(String.format("Sayfa kaynağına '%s' değeri yüklenemedi! Hata kodu: %s", text, ex.getMessage()));
        }
    }

    private ExpectedCondition<Boolean> waitUntilTextIsLoaded(String text) {

        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(@Nullable WebDriver webDriver) {
                try {
                    return driver.getPageSource().contains(text);
                }catch (Exception ex) {
                }
                return false;
            }
        };
    }
}
