package com.library.server.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker {
    private final static String Phone_Pattern = "^(13\\d|14[57]|15[^4]|166|17[013678]|18\\d|19[89])\\d{8}$";
    
    public static Boolean isPhone(String phone) {
        Pattern pattern = Pattern.compile(Phone_Pattern);
        Matcher matcher = pattern.matcher(phone);
        return matcher.find();
    }
}
