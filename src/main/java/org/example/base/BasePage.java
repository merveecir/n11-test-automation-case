package org.example.base;


import org.apache.log4j.Logger;
import org.example.driver_manager.DriverManager;
import org.example.operation.WaitOperation;
import org.openqa.selenium.WebElement;

public class BasePage {

    private Logger log = Logger.getLogger(BasePage.class);

    public void clear(String key) {

        new WaitOperation().waitPresence(key);
        new WaitOperation().waitVisible(key);
        WebElement webElement = new WaitOperation().waitClickable(key);
        try {
            webElement.clear();
        }catch (Exception ex) {
            log.error(String.format("'%s' alanı temizlenirken sorun oluştu! Hata mesajı: %s", key, ex.getMessage()));
        }
    }
    public void navigateBack() {
        DriverManager.getDriver().navigate().back();
        log.info("Bir onceki sayfaya donuldu");
    }


}
