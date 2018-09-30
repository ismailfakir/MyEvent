package net.cloudcentrik.myevent.util;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {
    public static String generateRandomEmail(int length) {

        String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "1234567890" + "_-.";
        String email = "";
        String temp = RandomStringUtils.random(length, allowedChars);
        email = temp.substring(0, temp.length() - 9) + "@testdata.com";
        return email;
    }

    public static int getRandomIntBetweenRange(int min, int max){

        int x = (int) (Math.random()*((max-min)+1))+min;

        return x;

    }
}
