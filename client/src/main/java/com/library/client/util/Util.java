package com.library.client.util;

import com.library.client.Constant;
import com.library.client.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
// import org.apache.commons.io.input.IOUtils;
// import org.apache.commons.io.StandardCharsets;

public class Util {

    private static HttpClient httpClient = new HttpClient();
    private static String tmpcookies = "";

    // Configure
	private final static String HTTP_Host = "127.0.0.1";
    private final static String HTTP_Port = "8080";
	private final static String HTTP_User = "/user";

    private final static String getUrl(String group, String work) {
		return String.format("http://%s:%s%s%s", HTTP_Host, HTTP_Port, group, work);
	}

	// User
	private final static String User_Add = getUrl(HTTP_User, "/add");
	private final static String User_Reset = getUrl(HTTP_User, "/reset");
	private final static String User_ChangeInfo = getUrl(HTTP_User, "/changeinfo");
	private final static String User_ChangePhone = getUrl(HTTP_User, "/changephone");
	private final static String User_ChangePassword = getUrl(HTTP_User, "/changepassword");
	private final static String User_Delete = getUrl(HTTP_User, "/delete");
	private final static String User_GetByName = getUrl(HTTP_User, "/getbyname");
	private final static String User_GetById = getUrl(HTTP_User, "/getbyid");
	private final static String User_All = getUrl(HTTP_User, "/all");
	private final static String User_Login = getUrl(HTTP_User, "/login");
	private final static String User_Logout = getUrl(HTTP_User, "/logout");
    
    public final static Integer addUser(String name, String sex, String phone, String password) throws LibraryException, JSONException, IOException {
        PostMethod postMethod = new PostMethod(User_Add);

        NameValuePair[] reqBody = {
            new NameValuePair(User.UserName, name),
            new NameValuePair(User.UserSex, sex),
            new NameValuePair(User.UserPhone, phone),
            new NameValuePair(User.UserPassword, password),
        };

        postMethod.setRequestBody(reqBody);

        try {
            httpClient.executeMethod(postMethod);

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);
            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                System.out.println(status != Constant.HTTP_OK);
                throw new LibraryException(status);
            }
            return status;
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static Integer resetPassword(String name, String phone) throws LibraryException, JSONException, IOException {
        PostMethod postMethod = new PostMethod(User_Reset);

        NameValuePair[] reqBody = {
            new NameValuePair(User.UserName, name),
            new NameValuePair(User.UserPhone, phone),
        };

        postMethod.setRequestBody(reqBody);

        try {
            httpClient.executeMethod(postMethod);

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);
            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                    throw new LibraryException(status);
            }
            return status;
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static Integer changeInfo(String name, String sex)  throws LibraryException, JSONException, IOException {
        PostMethod postMethod = new PostMethod(User_ChangeInfo);

        NameValuePair[] reqBody = {
            new NameValuePair(User.UserName, name),
            new NameValuePair(User.UserSex, sex),
        };

        postMethod.setRequestBody(reqBody);

        try {
            postMethod.setRequestHeader("cookie", tmpcookies);
            httpClient.executeMethod(postMethod);

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);
            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                    throw new LibraryException(status);
            }
            return status;
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static Integer changePhone(String newPhone)  throws LibraryException, JSONException, IOException {
        PostMethod postMethod = new PostMethod(User_ChangePhone);

        NameValuePair[] reqBody = {
            new NameValuePair("newPhone", newPhone),
        };

        postMethod.setRequestBody(reqBody);

        try {
            postMethod.setRequestHeader("cookie", tmpcookies);
            httpClient.executeMethod(postMethod);

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);
            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                    throw new LibraryException(status);
            }
            return status;
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static Integer changePassword(String oldPassword, String newPassword)  throws LibraryException, JSONException, IOException {
        PostMethod postMethod = new PostMethod(User_ChangePassword);

        NameValuePair[] reqBody = {
            new NameValuePair("oldPassword", oldPassword),
            new NameValuePair("newPassword", newPassword),
        };

        postMethod.setRequestBody(reqBody);

        try {
            postMethod.setRequestHeader("cookie", tmpcookies);
            httpClient.executeMethod(postMethod);

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);
            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            }
            return status;
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static User login(String phone, String password) throws LibraryException, JSONException, IOException {
        PostMethod postMethod = new PostMethod(User_Login);

        NameValuePair[] reqBody = {
            new NameValuePair(User.UserPhone, phone),
            new NameValuePair(User.UserPassword, password),
        };

        postMethod.setRequestBody(reqBody);

        try {
            httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
            httpClient.executeMethod(postMethod);

            Cookie[] cookies = httpClient.getState().getCookies();
            for(Cookie c:cookies){
                tmpcookies += c.toString()+";";
            }

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);
            Integer status = json.getInt(Constant.Status);
            // if (status != Constant.HTTP_OK) {
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            } else {
                        JSONObject body = json.getJSONObject(Constant.Body);
                        User user = new User(
                                body.getLong(User.UserId),
                                body.getString(User.UserName),
                                body.getInt(User.UserSex),
                                body.getString(User.UserPhone),
                                body.getInt(User.UserIsAdmin),
                                body.getInt(User.UserAmount)
                        );
                        return user;
                    }
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }
}
