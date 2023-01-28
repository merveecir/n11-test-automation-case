package org.example.operation;

import org.example.driver_manager.DriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;

public class ScrollOperation {
    private WaitOperation waitOperation;
    private WebDriver driver = DriverManager.getDriver();
    private Logger log = Logger.getLogger(ScrollOperation.class);

    public ScrollOperation() {
        waitOperation = new WaitOperation();
    }

    public void scrollToElement(String key) {
        try {
            waitOperation.waitPresence(key);
            waitOperation.waitVisible(key);
            WebElement webElement = waitOperation.waitClickable(key);

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webElement);
            log.info(String.format("'%s' objesine kaydirildi", key));
        } catch (StaleElementReferenceException | ElementNotInteractableException | JavascriptException e) {
            String logMessage = String.format("'%s' objesine kaydirilirken sorun oluştu!", key);
            log.error(logMessage);
        }
    }

    public void scrollToWebElement(WebElement el) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", el);
            log.info("WebElementine kaydirildi");
        } catch (StaleElementReferenceException | ElementNotInteractableException | JavascriptException e) {
            String logMessage = "WebElementine kaydirilirken sorun olustu!";
            log.error(logMessage);
        }
    }

    public void scrollToBottom() {
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
            waitOperation.waitDoNotWriteToLogFile(2);
            log.info("Sayfanin en altina kaydirildi");
        } catch (JavascriptException e) {
            String logMessage = "Sayfanin en altina kaydirilirken sorun olustu!";
            log.error(logMessage);
        }
    }

    public void scrollToTop() {
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
            waitOperation.waitDoNotWriteToLogFile(2);
            log.info("Sayfanin en ustune kaydirildi");
        } catch (JavascriptException e) {
            String logMessage = "Sayfanin en ustune kaydirilirken sorun olustu!";
            log.error(logMessage);
        }
    }

    public void scrollToCoords(String x, String y) {
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy("+ x +", "+ y +")");
            waitOperation.waitDoNotWriteToLogFile(2);
            log.info(String.format("('%s', '%s') kordinatlarina kaydirildi"));
        } catch (JavascriptException e) {
            String logMessage = String.format("('%s', '%s') kordinatlarina kaydirilirken sorun olustu!");
            log.error(logMessage);
        }
    }
}
