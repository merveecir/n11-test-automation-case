package org.example.utils;
import java.util.Random;

public class Utils {

    public static boolean isNullOrEmpty(String str) {

        return str == null || "".equals(str);
    }

    public static boolean isNotNull(String str) {

        return str != null && str.length() > 0;
    }
    public static int randomInt(int min, int max){
        Random rnd = new Random();
        return rnd.nextInt((max - min)+1)+min;
    }
}
