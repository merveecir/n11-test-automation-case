package org.example.base;


import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.ExecutionContext;
import com.thoughtworks.gauge.Step;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.example.config.GlobalConfig;
import org.example.driver_manager.DriverManager;

public class BaseTest {

    private Logger log = Logger.getLogger(BaseTest.class);

    @BeforeScenario
    public void setUp(ExecutionContext context) {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        GlobalConfig.CurrentScenarioName = context.getCurrentScenario().getName();
        log.info(String.format("********** %s SENARYOSU BAŞLADI **********", GlobalConfig.CurrentScenarioName.toUpperCase()));
    }

    @AfterScenario
    public void tearDown() {

        DriverManager.killDriver();
        log.info(String.format("********** %s SENARYOSU BİTTİ **********", GlobalConfig.CurrentScenarioName.toUpperCase()));
    }

    @Step("Testi başlat")
    public void startTest() {
        DriverManager.initDriver();
        GlobalConfig.writeGlobalConfiguration();
    }
}
