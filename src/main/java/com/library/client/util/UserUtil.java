package com.library.client.util;

import com.github.kevinsawicki.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.library.client.Constant;
import com.library.client.models.User;;

public class UserUtil {

    public final static Integer addUser(String name, Integer sex, String phone, String password) throws JSONException, LibraryException {
        Map map = new HashMap();
        map.put(User.UserName, name);
        map.put(User.UserSex, sex);
        map.put(User.UserPhone, phone);
        map.put(User.UserPassword, password);

        String resp = new HttpRequest(URL.getURL(URL.HTTP_User, URL.User_Add),HttpRequest.METHOD_POST)
        .form(map).connectTimeout(URL.Timeout).readTimeout(URL.Timeout).body();

        JSONObject json;
        try {
            json = new JSONObject(resp);
            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            } else {
                return status;
            }
        }
        catch (JSONException e) {
            throw e;
        }
    }

    public final static User login(String phone, String password) throws JSONException, LibraryException {
        Map map = new HashMap();
        map.put(User.UserPhone, phone);
        map.put(User.UserPassword, password);

        String resp = new HttpRequest(URL.getURL(URL.HTTP_User, URL.User_Login),HttpRequest.METHOD_POST)
        .form(map).connectTimeout(URL.Timeout).readTimeout(URL.Timeout).body();

        JSONObject json;
        try {
            json = new JSONObject(resp);
            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            } else {
                JSONObject body =  json.getJSONObject(Constant.Body);
                User user = new User(
                    body.getLong(User.UserId),
                    body.getString (User.UserName),
                    body.getInt(User.UserSex),
                    body.getString(User.UserPhone),
                    body.getInt(User.UserIsAdmin),
                    body.getInt(User.UserAmount)
                );
                return user;
            }
        }
        catch (JSONException e) {
            throw e;
        }
    }
}
