package org.example.config;

import org.apache.log4j.Logger;

public class GlobalConfig {

    private static Logger log = Logger.getLogger(GlobalConfig.class);

    public static final int DEFAULT_WAIT = 70;
    public static final int SLEEP_IN_MILLIS = 5000;
    public static String currentOS = System.getProperty("os.name");
    public static String CurrentScenarioName = "";
    public static BrowserList browserType = BrowserList.CHROME;

    public static void writeGlobalConfiguration() {
        log.info("********** KONFİGÜRASYON BİLGİLERİ **********");
        log.info(String.format("Varsayılan bekleme süresi: %s saniye", DEFAULT_WAIT));
        log.info(String.format("Objelerin milisaniye cinsinden kontrol edilme süresi: %s", SLEEP_IN_MILLIS));
        log.info(String.format("Current OS: '%s'", currentOS));
        log.info("********** KONFİGÜRASYON BİLGİLERİ **********");
    }
}
