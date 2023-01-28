package org.example.operation;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class SendKeyOperation {

    private WaitOperation waitOperation = null;
    private Logger log = Logger.getLogger(SendKeyOperation.class);

    public SendKeyOperation() {

        waitOperation = new WaitOperation();
    }

    public void sendKeys(String key, CharSequence... value) {

        try {
            waitOperation.waitPresence(key);
            waitOperation.waitVisible(key);
            WebElement webElement = waitOperation.waitClickable(key);
            webElement.sendKeys(value);
        } catch (StaleElementReferenceException | ElementNotInteractableException exception) {
            String logMessage = String.format("'%s' objesine sendKey işlemi yaparken sorun oluştu!", key);
            log.error(logMessage);
            waitOperation.waitDoNotWriteToLogFile(1);
            sendKeys(key, value);
        }
    }
}
