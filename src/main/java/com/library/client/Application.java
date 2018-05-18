package com.library.client;

import com.library.client.page.LoginPage;
import com.library.client.page.*;
import com.library.client.models.*;

public class Application {

    public static void main(String[] args) {
        // new LoginPage("图书馆管理系统");
        new AdminPage("图书馆管理系统", new User(new Long(11), "111", 1, "17300000000", 1, 0));
    }
}
