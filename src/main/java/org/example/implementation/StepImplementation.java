package org.example.implementation;

import com.thoughtworks.gauge.Step;
import org.apache.log4j.Logger;
import org.example.driver_manager.DriverManager;
import org.example.element_manager.ElementManager;
import org.example.operation.ClickOperation;
import org.example.operation.ScrollOperation;
import org.example.operation.SendKeyOperation;
import org.example.operation.WaitOperation;
import org.junit.Assert;

public class StepImplementation {

    private Logger log = Logger.getLogger(StepImplementation.class);
    ElementManager elementManager = new ElementManager();
    private String logMessage = "";

    @Step("<url> adresine git")
    public void navigateUrl(String url) {
        DriverManager.getDriver().get(url);
    }

    @Step("<key> alanına <value> yaz")
    public void sendKeys(String key, String value) {

        new SendKeyOperation().sendKeys(key, value);
        logMessage = String.format("'%s' alanına '%s' yazıldı.", key, value);
        log.info(logMessage);
    }

    @Step("<key> objesine tıkla")
    public void click(String key) {
        new ClickOperation().click(key);
        logMessage = String.format("'%s' objesine tıklandı.", key);
        log.info(logMessage);
    }

    @Step("<key> objesi görüntülenene kadar bekle")
    public void waitTillTheObjectVisible(String key) {
        new ScrollOperation().scrollToElement(key);
        new WaitOperation().waitVisible(key);
        log.info(String.format("'%s' objesi görüntülenene kadar beklendi.", key));
    }

    @Step("<key> objesi varsa tıkla")
    public void clickIfExist(String key) {

        new ClickOperation().clickIfExists(key, 3);
    }

    @Step("<key> objesi var mı kontrol et")
    public void checkIsTheObjectExist(String key) {

        boolean isExist = new WaitOperation().isExist(key, 30);

        if (!isExist) {
            logMessage = String.format("'%s' objesi sayfa üzerinde bulunamadı!", key);
            log.error(logMessage);
            Assert.fail(logMessage);
        }
        log.info(String.format("'%s' objesi sayfa üzerinde bulundu.", key));

    }

    @Step("<key> elementine kaydir")
    public void scrollToElement(String key){
    new ScrollOperation().scrollToElement(key);

    }


}
