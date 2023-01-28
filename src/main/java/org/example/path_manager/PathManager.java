package org.example.path_manager;

import org.example.utils.Utils;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.HashMap;

public class PathManager {

    private static Logger log = Logger.getLogger(PathManager.class);
    private static HashMap<PathEnum, String> pathMap;

    private PathManager() { }

    public static String getPath(PathEnum pathEnum) {
        initPathMap();
        String path = pathMap.get(pathEnum);
        if (Utils.isNullOrEmpty(path)) {
            String logMessage = String.format("'%s' keyi pathMap içerisinde bulunamadı!", pathEnum.name());
            log.error(logMessage);
            Assert.fail(logMessage);
        }
        return path;
    }

    private static void initPathMap() {

        if(pathMap == null) {
            pathMap = new HashMap<>();
            pathMap.put(PathEnum.SCENARIO_DATA, "./src/main/resources/Scenario_Data.xlsx");
        }
    }
}
