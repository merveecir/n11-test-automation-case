package org.example.user_variable;

import org.example.utils.Utils;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.HashMap;

public class UserVariable {

    private static HashMap<String, String> userVariableHashMap;
    private static Logger log = Logger.getLogger(UserVariable.class);

    public static void addVariableToUserVariableHashMap(String key, String value) {
        if (Utils.isNullOrEmpty(key) || Utils.isNullOrEmpty(value))
            return;
        initUserVariableHashMap();
        userVariableHashMap.put(key, value);
        log.info(String.format("'%s' değişkenine '%s' değeri atandı", key, value));
    }

    private static void initUserVariableHashMap() {
        if (userVariableHashMap == null)
            userVariableHashMap = new HashMap<>();
    }

    public static String getVarilableValue(String variableName) {

        String variableValue = userVariableHashMap.get(variableName);
        if (Utils.isNullOrEmpty(variableName)) {
            String logMessage = String.format("Kullanıcı değişkenleri içerisinde '%s' değişkeni bulunamadı!", variableName);
            log.error(logMessage);
            Assert.fail(logMessage);
        }
        return variableValue;
    }
}
