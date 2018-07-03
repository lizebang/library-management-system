package com.library.server.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker {
    private final static String Phone_Pattern =
            "^1(3\\d|4[57]|5[^4]|66|7[013678]|8\\d|9[89])\\d{8}$";

    public static Boolean isPhone(String phone) {
        Pattern pattern = Pattern.compile(Phone_Pattern);
        Matcher matcher = pattern.matcher(phone);
        return matcher.find();
    }
}
