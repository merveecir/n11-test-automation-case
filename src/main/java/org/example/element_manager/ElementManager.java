package org.example.element_manager;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.example.driver_manager.DriverManager;
import org.example.utils.ElementInfo;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ElementManager {

    private static Logger log = Logger.getLogger(ElementManager.class);
    private static final String fileName = "elementValuess";
    private ConcurrentMap<String, Object> elementMapList = new ConcurrentHashMap<>();

    public ElementManager() {
        initMap(getFileList());
    }


    public File[] getFileList() {
        File[] fileList = new File(
                this.getClass().getClassLoader().getResource(fileName).getFile())
                .listFiles(pathname -> !pathname.isDirectory() && pathname.getName().endsWith(".json"));
        if (fileList == null) {
            log.warn(
                    "File Directory Is Not Found! Please Check Directory Location. Default Directory Path = {}");
            throw new NullPointerException();
        }
        return fileList;
    }

    public void initMap(File[] fileList) {
        Type elementType = new TypeToken<List<ElementInfo>>() {
        }.getType();
        Gson gson = new Gson();
        List<ElementInfo> elementInfoList = null;
        for (File file : fileList) {
            try {
                elementInfoList = gson
                        .fromJson(new FileReader(file), elementType);
                elementInfoList.parallelStream()
                        .forEach(elementInfo -> elementMapList.put(elementInfo.getKey(), elementInfo));
            } catch (FileNotFoundException e) {
                log.warn("{} not found", e);
            }
        }
    }

    public ElementInfo findElementInfoByKey(String key) {
        return (ElementInfo) elementMapList.get(key);
    }

    public void saveValue(String key, String value) {
        elementMapList.put(key, value);
        log.info(String.format("'%s' degeri '%s' keye atandi", value, key));
    }

    public String getValue(String key) {
        return elementMapList.get(key).toString();
    }

    public By getLocator(String key) {
        By by = null;
        ElementInfo elementInfo = findElementInfoByKey(key);
        if (elementInfo.getType().equals("css")) {
            by = By.cssSelector(elementInfo.getValue());
        } else if (elementInfo.getType().equals(("name"))) {
            by = By.name(elementInfo.getValue());
        } else if (elementInfo.getType().equals("id")) {
            by = By.id(elementInfo.getValue());
        } else if (elementInfo.getType().equals("xpath")) {
            by = By.xpath(elementInfo.getValue());
        } else if (elementInfo.getType().equals("linkText")) {
            by = By.linkText(elementInfo.getValue());
        } else if (elementInfo.getType().equals(("partialLinkText"))) {
            by = By.partialLinkText(elementInfo.getValue());
        }
        
        if (by == null) {
            String logMessage = String.format("'%s' keyine ait locator bulunamadı!", elementInfo.getKey());
            log.error(logMessage);
            Assert.fail(logMessage);
        }
        return by;
    }

    public List<WebElement> findElementsByKey(String key) {
        WebDriver driver = DriverManager.getDriver();
        List<WebElement> elements = driver.findElements(getLocator(key));
        return elements;
    }

}
