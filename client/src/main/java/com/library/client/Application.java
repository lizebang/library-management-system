package com.library.client;

import com.library.client.page.*;
import com.library.client.models.*;
import com.library.client.util.*;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        // try {
        //     User user = Util.login("13607258000", "123456");
        //     // System.out.println(user.toMap());
        //     // Util.addUser("name", "1", "13607258000", "111");
        //     // Util.resetPassword("name", "13607258000");
        //     Util.changeInfo("aaa", "2");
        // } catch (LibraryException exception) {

        // } catch (IOException exception) {

        // }
        new LoginPage("图书馆管理系统");
        // new UserPage("图书馆管理系统", new User(new Long(11), "111", 1, "17300000000", 1, 0));
        // new AdminPage("图书馆管理系统", new User(new Long(11), "111", 2, "17300000000", 1, 0));
        // new UserInfoPage("图书馆管理系统", new User(new Long(11), "111", 1, "17300000000", 1, 0));
    }
}
