package org.example.operation;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class ClickOperation {

    private WaitOperation waitOperation;
    private Logger log = Logger.getLogger(ClickOperation.class);

    public ClickOperation() {

        waitOperation = new WaitOperation();
    }

    public void click(String key) {

        try {
            waitOperation.waitPresence(key);
            waitOperation.waitVisible(key);
            WebElement webElement = waitOperation.waitClickable(key);
            webElement.click();
        } catch (StaleElementReferenceException | ElementClickInterceptedException exception) {
            String logMessage = String.format("'%s' objesine tıklanırken sorun oluştu!", key);
            log.error(logMessage);
            click(key);
        }
    }

    public void clickIfExists(String key, int waitSecond) {

        if (waitOperation.isExist(key, waitSecond)) {
            click(key);
            log.info(String.format("'%s' objesine tıklandı.", key));
        }
    }
}
